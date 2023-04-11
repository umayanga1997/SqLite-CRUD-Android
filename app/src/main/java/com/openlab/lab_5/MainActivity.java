package com.openlab.lab_5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper database;

    EditText titleTxt;
    EditText idTxt;
    EditText descriptionTxt;

    Button saveBtn;
    Button updateBtn;
    Button deleteBtn;
    Button readBtn;

    TextView dataViewTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTxt = findViewById(R.id.title_txt);
        idTxt = findViewById(R.id.task_id_txt);
        descriptionTxt = findViewById(R.id.description_txt);
        dataViewTxt = findViewById(R.id.readable_data_txt);
        saveBtn = findViewById(R.id.create_btn);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        readBtn = findViewById(R.id.read_btn);

        database = new DatabaseHelper(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idTxt.getText().toString().equals("") || titleTxt.getText().toString().equals("")
                        || descriptionTxt.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }else{
                    boolean value = database.insertData(Integer.parseInt(idTxt.getText().toString()),
                            titleTxt.getText().toString(), descriptionTxt.getText().toString());
                    if(value){
                        Toast.makeText(MainActivity.this, "Data was saved successful!"
                                , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Data was saved unsuccessful!"
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idTxt.getText().toString().equals("") || titleTxt.getText().toString().equals("")
                        || descriptionTxt.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }else{
                    boolean value = database.updateData(idTxt.getText().toString(),
                            titleTxt.getText().toString(), descriptionTxt.getText().toString());
                    if(value){
                        Toast.makeText(MainActivity.this, "Data was updated successful!"
                                , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Data was updated unsuccessful!"
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idTxt.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter the task id",
                            Toast.LENGTH_SHORT).show();
                }else{
                    boolean value = database.deleteData(idTxt.getText().toString());
                    if(value){
                        Toast.makeText(MainActivity.this, "Data was deleted successful!"
                                , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Data was deleted unsuccessful!"
                                , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor value = database.readAllTaskData();

                if(value != null) {
                    if (value.getCount() > 0) {
                        StringBuilder dataBuilder = new StringBuilder();
                        while (value.moveToNext()) {
                            dataBuilder.append("Task ID :: ").append(value.getString(0))
                                    .append("\n");
                            dataBuilder.append("Title :: ").append(value.getString(1))
                                    .append("\n");
                            dataBuilder.append("Description :: ").append(value.getString(2))
                                    .append("\n\n");
                            dataBuilder.append("---------------\n\n");
                        }

                        dataViewTxt.setText(dataBuilder.toString());

                    } else {
                        dataViewTxt.setText("Data not found!");
                        Toast.makeText(MainActivity.this, "Data not found!",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    dataViewTxt.setText("Data not found!");
                }
            }
        });
    }
}