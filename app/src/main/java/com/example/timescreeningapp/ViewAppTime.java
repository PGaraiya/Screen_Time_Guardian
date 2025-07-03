package com.example.timescreeningapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ViewAppTime extends Activity {
    SQLiteDatabase db1;
    String title[]=new String[20];
    String pdf[]=new String[20];
    String video[]=new String[20];
    String desc[]=new String[20];
    String type[]=new String[20];

    ListView lst1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewapptime);
        lst1=(ListView)findViewById(R.id.lst);
        db1=openOrCreateDatabase("AppTime",Context.MODE_PRIVATE,null);

        try{
            Cursor c1= db1.rawQuery("select * from  AddRes",null);
            int count=0;
            while(c1.moveToNext()){

                title[count]= c1.getString(0);
                pdf[count]=c1.getString(1);
                video[count]=c1.getString(2);
                desc[count]=c1.getString(3);
                type[count]=c1.getString(4);
                //cnum[count]=c1.getString(5);

                count=count+1;

            }

            LevelAdapter lv1= new LevelAdapter(ViewAppTime.this,title,pdf,desc);
            lst1.setAdapter(lv1);

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

/*
                    Intent i1= new Intent(ViewAllRecipe.this,ViewRecipeDetails.class);
                    i1.putExtra("title",title[position]);
                    i1.putExtra("pdf",pdf[position]);
                    i1.putExtra("video",video[position]);
                    i1.putExtra("desc",desc[position]);
                    i1.putExtra("type" ,type[position]);

                    startActivity(i1);

*/

                }
            });




        }catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }



    }
}
