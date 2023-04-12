package com.example.sql_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_add , btn_viewAll;
    EditText et_Name,et_Age;
    ListView lv_StudentList;

    ArrayAdapter StudentArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add=findViewById(R.id.btn_add);
        btn_viewAll=findViewById(R.id.btn_viewAll);
        et_Name=findViewById(R.id.et_Name);
        et_Age=findViewById(R.id.et_Age);
        lv_StudentList=findViewById(R.id.lv_StudentList);

        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        ShowStudentOnListView(dataBaseHelper);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentMod studentModel;
                try {
                    studentModel = new StudentMod(-1,et_Name.getText().toString(),Integer.parseInt(et_Age.getText().toString()));
                    Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();

                }
                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error Creating Student", Toast.LENGTH_SHORT).show();
                    studentModel= new StudentMod(-1,"error",0);
                }
                DataBaseHelper datBaseHelper =new DataBaseHelper(MainActivity.this);

                boolean success = datBaseHelper.addOne(studentModel);
                Toast.makeText(MainActivity.this, "Success="+success, Toast.LENGTH_SHORT).show();
                ShowStudentOnListView(dataBaseHelper);


            }
        });


        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dataBaseHelper = new DataBaseHelper(MainActivity.this);

                ShowStudentOnListView(dataBaseHelper);


                // Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void ShowStudentOnListView(DataBaseHelper dataBaseHelper2) {
        StudentArrayAdapter = new ArrayAdapter<StudentMod>(MainActivity.this, android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
        lv_StudentList.setAdapter(StudentArrayAdapter);
    }


}