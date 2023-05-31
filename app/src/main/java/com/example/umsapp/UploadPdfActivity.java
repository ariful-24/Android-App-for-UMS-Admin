package com.example.umsapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class UploadPdfActivity extends AppCompatActivity {

    private EditText pdfTitle;
    private TextView pdfTextView;
    private String pdfName, title;
    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    String downloadurl = "";
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        pd = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        CardView addPdf = findViewById(R.id.addPdf);
        pdfTitle = findViewById(R.id.pdfTitle);
        Button btnuploadPdf = findViewById(R.id.btnuploadPdf);
        pdfTextView = findViewById(R.id.pdfTextView);

        addPdf.setOnClickListener(view -> openMedia());

        btnuploadPdf.setOnClickListener(view -> {
            title = pdfTitle.getText().toString();
            if(title.isEmpty()){
                pdfTitle.setError("Empty");
                pdfTitle.requestFocus();
            }else if (pdfData == null){
                Toast.makeText(UploadPdfActivity.this, "Please upload pdf", Toast.LENGTH_SHORT).show();
            }else {
                uploadPdf();
            }
        });
    }

    private void uploadPdf() {
        pd.setTitle("Please wait...");
        pd.setMessage("Uploading pdf");
        pd.show();
        StorageReference reference = storageReference.child("pdf/"+ pdfName+": "+System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
                .addOnSuccessListener(taskSnapshot -> {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete());
                    Uri uri = uriTask.getResult();
                    uploadData(String.valueOf(uri));
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String valueOf) {
        String uniqueKey = databaseReference.child("pdf").push().getKey();

        HashMap data = new HashMap();
        data.put("pdfTitle", title);
        data.put("pdfUrl", downloadurl);

        databaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Pdf uploaded successfully.", Toast.LENGTH_SHORT).show();
                pdfTitle.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(UploadPdfActivity.this, "Failed to upload pdf!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMedia(){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf File"), REQ);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK){
            pdfData = data.getData();

            if(pdfData.toString().startsWith("content://")){
                Cursor cursor = null;
                try {
                    cursor = UploadPdfActivity.this.getContentResolver().query(pdfData, null, null, null, null);
                    if(cursor != null && cursor.moveToFirst()){
                        pdfName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (pdfData.toString().startsWith("file://")){
                pdfName = new File(pdfName.toString()).getName();
            }
            pdfTextView.setText(pdfName);
        }

    }
}