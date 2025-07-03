package com.example.timescreeningapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Login extends Activity {
    Button b1;
    EditText et1,et2;
    SQLiteDatabase db1;
    TextView tv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        b1=(Button) findViewById(R.id.login);
        et1=(EditText)findViewById(R.id.uname);
        et2=(EditText)findViewById(R.id.pass);
        tv1=(TextView)findViewById(R.id.textView2);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(Login.this,NewRegistration.class);
                startActivity(i1);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=et1.getText().toString();
                String pass=et2.getText().toString();

                db1=openOrCreateDatabase("AppTime", Context.MODE_PRIVATE,null);

                if(uname.contentEquals("admin")&&pass.contentEquals("admin")){
                 ///   Intent i1 = new Intent(Login.this, AdminDashboard.class);
                    //startActivity(i1);
                    //finish();
                }else {
                    try {
                        Cursor c1 = db1.rawQuery("select * from User", null);
                        int count = 0;
                        while (c1.moveToNext()) {

                            if (uname.trim().contentEquals(c1.getString(2).trim()) && pass.trim().contentEquals(c1.getString(3).trim())) {


                                Intent i1 = new Intent(Login.this, UserDashboard.class);
                                startActivity(i1);
                                finish();
                            }

                        }
                    } catch (Exception e) {
                        Toast.makeText(Login.this, "" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
