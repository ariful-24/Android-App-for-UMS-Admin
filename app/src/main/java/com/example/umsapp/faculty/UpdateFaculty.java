package com.example.umsapp.faculty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView cseDepartment, eeeDepartment, textileDepartment, llbDepartment, englishDepartment, gbsDepartment;
    private LinearLayout cseNoData, eeeNoData, textileNoData, llbNoData, englishNoData, gbsNoData;
    private List<TeacherData> list1, list2, list3, list4, list5, list6;
    private TeacherAdapter adapter;

    private DatabaseReference reference, dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        cseDepartment = findViewById(R.id.cseDepartment);
        eeeDepartment = findViewById(R.id.eeeDepartment);
        textileDepartment = findViewById(R.id.textileDepartment);
        llbDepartment = findViewById(R.id.llbDepartment);
        englishDepartment = findViewById(R.id.englishDepartment);
        gbsDepartment = findViewById(R.id.gbsDepartment);

        cseNoData = findViewById(R.id.cseNoData);
        eeeNoData = findViewById(R.id.eeeNoData);
        textileNoData = findViewById(R.id.textileNoData);
        llbNoData = findViewById(R.id.llbNoData);
        englishNoData = findViewById(R.id.englishNoData);
        gbsNoData = findViewById(R.id.gbsNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("teachers");

        cseDepartment();
        eeeDepartment();
        textileDepartment();
        llbDepartment();
        englishDepartment();
        gbsDepartment();

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> startActivity(new Intent(UpdateFaculty.this, AddTeacher.class)));
    }

    private void cseDepartment() {
        dbRef = reference.child("Dept of CSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    cseNoData.setVisibility(View.VISIBLE);
                    cseDepartment.setVisibility(View.GONE);
                }else{
                    cseNoData.setVisibility(View.GONE);
                    cseDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    cseDepartment.setHasFixedSize(true);
                    cseDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1,UpdateFaculty.this, "Dept of CSE");
                    cseDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eeeDepartment() {
        dbRef = reference.child("Dept of EEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDepartment.setVisibility(View.GONE);
                }else{
                    eeeNoData.setVisibility(View.GONE);
                    eeeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    eeeDepartment.setHasFixedSize(true);
                    eeeDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2, UpdateFaculty.this, "Dept of EEE");
                    eeeDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void textileDepartment() {
        dbRef = reference.child("Dept of Textile");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    textileNoData.setVisibility(View.VISIBLE);
                    textileDepartment.setVisibility(View.GONE);
                }else{
                    textileNoData.setVisibility(View.GONE);
                    textileDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    textileDepartment.setHasFixedSize(true);
                    textileDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3, UpdateFaculty.this, "Dept of Textile");
                    textileDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void llbDepartment() {
        dbRef = reference.child("Dept of LLB");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    llbNoData.setVisibility(View.VISIBLE);
                    llbDepartment.setVisibility(View.GONE);
                }else{
                    llbNoData.setVisibility(View.GONE);
                    llbDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    llbDepartment.setHasFixedSize(true);
                    llbDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4, UpdateFaculty.this, "Dept of LLB");
                    llbDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void englishDepartment() {
        dbRef = reference.child("Dept of English");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    englishNoData.setVisibility(View.VISIBLE);
                    englishDepartment.setVisibility(View.GONE);
                }else{
                    englishNoData.setVisibility(View.GONE);
                    englishDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    englishDepartment.setHasFixedSize(true);
                    englishDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list5, UpdateFaculty.this, "Dept of English");
                    englishDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gbsDepartment() {
        dbRef = reference.child("Green Business School");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    gbsNoData.setVisibility(View.VISIBLE);
                    gbsDepartment.setVisibility(View.GONE);
                }else{
                    gbsNoData.setVisibility(View.GONE);
                    gbsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    gbsDepartment.setHasFixedSize(true);
                    gbsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list6, UpdateFaculty.this, "Green Business School");
                    gbsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}