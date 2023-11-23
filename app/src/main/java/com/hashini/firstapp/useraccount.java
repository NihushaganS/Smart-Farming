package com.hashini.firstapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class useraccount extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText id;
    EditText user_name;
    EditText user_loaction;
    Button btnInsert;
    Button btnView;
    Button btnUpdate;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraccount);

        databaseHelper = new DatabaseHelper(this);
        user_name = findViewById(R.id.textname);
        user_loaction=findViewById(R.id.textlocation);
        id= findViewById(R.id.textEditId);
        btnInsert = findViewById(R.id.btninsert);
        btnView = findViewById(R.id.btnViewData);
        btnUpdate = findViewById(R.id.btnUpdateData);
        btnDelete = findViewById(R.id.btndelate);

        insertData();
        getAllData();
        updateData();
        deleteData();

    }
    public void insertData() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = databaseHelper.insertData(toString(), user_name.getText().toString(), user_loaction.getText().toString());
                if (isInserted) {
                    Toast.makeText(useraccount.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(useraccount.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void getAllData () {
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = databaseHelper.getAllData();
                if(result.getCount()== 0) {
                    showMessage("Error Message", "No Data Found!");
                    return;

                }
                StringBuffer resultsBuffer = new StringBuffer();
                while (result.moveToNext()) {
                    resultsBuffer.append("ID: " + result.getString(0) + "\n");
                    resultsBuffer.append("User Name: " + result.getString(1) + "\n");
                    resultsBuffer.append("Location: " + result.getString(2) + "\n");
                }
                showMessage("List of Data", resultsBuffer.toString());
            }
        });
    }

    public void updateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdated = databaseHelper.updateData(id.getText().toString(), user_name.getText().toString(),user_loaction.getText().toString());
                if (isUpdated) {
                    Toast.makeText(useraccount.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else  {
                    Toast.makeText(useraccount.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void deleteData() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int DeletedRecords = databaseHelper.deleteData(id.getText().toString());
                if(DeletedRecords > 0) {
                    Toast.makeText(useraccount.this, "Data Deleted Successful", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(useraccount.this, "Data Deletion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showMessage(String title, String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setCancelable(true);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(message);
        dialogBuilder.show();
    }
}
