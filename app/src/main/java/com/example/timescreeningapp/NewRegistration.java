package com.example.timescreeningapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NewRegistration extends Activity {

    Button book;
    EditText et1,et2,et3,et4,et5,et6;
    SQLiteDatabase db1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        book=(Button) findViewById(R.id.register_user);
        et1=(EditText)findViewById(R.id.name);
        et2=(EditText)findViewById(R.id.contact);
        et3=(EditText)findViewById(R.id.email) ;
        et4=(EditText)findViewById(R.id.password1);

        db1=openOrCreateDatabase("AppTime",Context.MODE_PRIVATE,null);
        db1.execSQL("create table if not exists User (Name varchar(900),Contact varchar(900),Email varchar(900),Pass varchar(900))");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

try {
    String name = et1.getText().toString();
    String cnum = et2.getText().toString();
    String email = et3.getText().toString();
    String pass = et4.getText().toString();
    ContentValues cv1 = new ContentValues();
    cv1.put("Name", name);
    cv1.put("Contact",cnum);
    cv1.put("Email", email);
    cv1.put("Pass", pass);
    db1.insert("User", null, cv1);
    Toast.makeText(NewRegistration.this, "Registration Done Sucessfully", Toast.LENGTH_SHORT).show();
   finish();
}catch(Exception e){
    Toast.makeText(NewRegistration.this, ""+e, Toast.LENGTH_SHORT).show();
}








            }
        });


    }
}
