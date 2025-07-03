package com.example.timescreeningapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AddAppTime extends Activity {

    Button book;
    EditText et1,et2,et3,et4,et5,et6;
    SQLiteDatabase db1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addapptime);
        book=(Button) findViewById(R.id.addrec);
        et1=(EditText)findViewById(R.id.title);
        et2=(EditText)findViewById(R.id.pdf);
        et3=(EditText)findViewById(R.id.video) ;
        et4=(EditText)findViewById(R.id.desc);

        //et6=(EditText)findViewById(R.id.contact);
        db1=openOrCreateDatabase("AppTime",Context.MODE_PRIVATE,null);
        db1.execSQL("create table if not exists AddApp (Title varchar(900),PackageName varchar(900),STime varchar(900),StopTime varchar(1900),TTime varchar(900))");

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

try {
    String name = et1.getText().toString();
    String cnum = et2.getText().toString();
    String email = et3.getText().toString();
    String pcon = et4.getText().toString();
   // String type=sp1.getSelectedItem().toString();
    ContentValues cv1 = new ContentValues();
    cv1.put("Title", name);
    cv1.put("PackageName",cnum);
    cv1.put("STime", email);
    cv1.put("StopTime ", pcon);
 //   cv1.put("TTime", type);
    db1.insert("AddApp", null, cv1);
    Toast.makeText(AddAppTime.this, "App Added Sucessfully", Toast.LENGTH_SHORT).show();
    finish();
}catch(Exception e){
    Toast.makeText(AddAppTime.this, ""+e, Toast.LENGTH_SHORT).show();
}








            }
        });


    }
}
