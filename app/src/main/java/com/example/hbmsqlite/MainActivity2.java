package com.example.hbmsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    String[] sexpense_name, sexpense_price;
    int[] id;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);






        dBmain=new DBmain(this);  // object;
        
        // Methods
        
        findid();
        displayData();
        
    }




    // here we write quey that select * from price(table name) that means select every thing from table.
    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();

        //query
        Cursor cursor = sqLiteDatabase.rawQuery("select *from price", null);

        // here we check that datta is present oor not by counting it.
        if(cursor.getCount()>0){
            id = new int[cursor.getCount()];
            sexpense_name = new String[cursor.getCount()];
            sexpense_price = new String[cursor.getCount()];
            int i=0;

            // if present that store in array.
            while(cursor.moveToNext()){
                id[i] = cursor.getInt(0);
                sexpense_name[i] = cursor.getString(1);
                sexpense_price[i] = cursor.getString(2);
                i++;
            }

            CustAdapter custAdapter = new CustAdapter();
            listView.setAdapter(custAdapter);



        }
    }

    private void findid() {
        listView = findViewById(R.id.lv);
    }

    // main activity calling

    public void mainCall(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------

    private class CustAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return sexpense_name.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {

            TextView txt_expense_name, txt_expense_price;
            ImageButton txt_edit, txt_delete;
            CardView cardview;

            convertView = LayoutInflater.from(MainActivity2.this).inflate(R.layout.singledata,parent,false);

            // finding id for cardview, texttview, imagebutton.

            txt_expense_name = convertView.findViewById(R.id.txt_expense_name);
            txt_expense_price = convertView.findViewById(R.id.txt_expense_price);

            txt_edit = convertView.findViewById(R.id.txt_edit);
            txt_delete = convertView.findViewById(R.id.txt_delete);

            cardview = convertView.findViewById(R.id.cardview);


            // when user clic on cardview it will hide expense and price of that particular view and show two button one for delete and other one for edit button.
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Background Random color

                    Random random = new Random();
                    cardview.setCardBackgroundColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));

                    // visibility tht we are change.

                    txt_edit.setVisibility(View.VISIBLE);
                    txt_delete.setVisibility(View.VISIBLE);

                    txt_expense_name.setVisibility(View.GONE);
                    txt_expense_price.setVisibility(View.GONE);

                }
            });

            // user click on edit button so it take you to activity this two line will get data of that particular cardview that we have to edit.

            txt_expense_name.setText(sexpense_name[i]);
            txt_expense_price.setText(sexpense_price[i]);



            txt_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",id[i]);
                    bundle.putString("expense_name", sexpense_name[i]);
                    bundle.putString("expense_price", sexpense_price[i]);

                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    intent.putExtra("expense_data", bundle);
                    startActivity(intent);
                }
            });


            // it will delete data when user click on delete button and at last we again display data so that it delete data clear from screen.
            txt_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sqLiteDatabase = dBmain.getReadableDatabase();
                    long recremove = sqLiteDatabase.delete("price", "id=" +id[i], null);

                    if(recremove!=-1){
                        Toast.makeText(MainActivity2.this, "Successfully Delete", Toast.LENGTH_SHORT).show();
                        displayData();
                    }

                }
            });

            return convertView;
        }
    }

    // This code is for menu.

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_menu,menu);
        //.setItemIconTintlist(null);
        return super.onCreateOptionsMenu(menu);
    }

    // Here we use swich case.

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.search_icon:
                Toast.makeText(this,"Search Call", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.feedback:
                Toast.makeText(this,"feedback call", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.about:
                Toast.makeText(this,"About Call", Toast.LENGTH_SHORT).show();

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }


    }

}