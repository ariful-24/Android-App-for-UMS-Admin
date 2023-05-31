package com.example.umsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.umsapp.faculty.UpdateFaculty;
import com.example.umsapp.notice.DeleteNoticeActivity;
import com.example.umsapp.notice.UploadNotice;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialCardView addNotice, addGalleryImage, addEbook, updateFaculty, deleteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNotice = findViewById(R.id.addNotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addEbook = findViewById(R.id.addEbook);
        updateFaculty = findViewById(R.id.updateFaculty);
        deleteNotice = findViewById(R.id.deleteNotice);

        addNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        updateFaculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.addNotice:
                intent = new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent = new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent = new Intent(MainActivity.this,UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.updateFaculty:
                intent = new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
       }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}