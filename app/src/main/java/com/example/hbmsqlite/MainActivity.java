package com.example.hbmsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    DBmain dbMain;
    SQLiteDatabase sqLiteDatabase;
    EditText expense_name, expense_price;
    Button submit, edit, display;
    FloatingActionButton fab1,fab2,fab3,fab4;
    boolean isFABOpen=false;

    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        
        
        
        

        dbMain = new DBmain(this); // this is a object
        
        //methods
        findid();
        insertData();
        clearData();
        editData();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFABOpen) {
                    closeFABMenu();

                } else
                    showFABMenu();

            }
        });

    }



    private void showFABMenu() {
        isFABOpen = true;
        fab1.setVisibility(View.VISIBLE);
        fab2.setVisibility(View.VISIBLE);

    }

    private void closeFABMenu() {
        isFABOpen = false;
        fab1.setVisibility(View.INVISIBLE);
        fab2.setVisibility(View.INVISIBLE);


    }




    // edit existing data/
    private void editData() {
        if(getIntent().getBundleExtra("expense_data")!=null){
            Bundle bundle = getIntent().getBundleExtra("expense_data");
            id = bundle.getInt("id");
            expense_name.setText(bundle.getString("expense_name"));
            expense_price.setText(bundle.getString("expense_price"));

            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);

        }
    }

    //clear data in editText after button clicked
    private void clearData() {
        expense_name.setText("");
        expense_price.setText("");
    }


    //  this method is use to insert data it is not predefine .
    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                // here we are getting value from edit text and we insert it into our data base.

                ContentValues cv = new ContentValues();
                cv.put("expense_name",expense_name.getText().toString());
                cv.put("expense_price",expense_price.getText().toString());

                sqLiteDatabase=dbMain.getWritableDatabase();
                //query
                Long recinsert=sqLiteDatabase.insert("price",null, cv);
                if(recinsert!=null){
                    Toast.makeText(MainActivity.this, "successfully Saved Your Note",Toast.LENGTH_SHORT).show();
                    clearData();
                }
                else{
                    Toast.makeText(MainActivity.this, "something wrong try again later", Toast.LENGTH_SHORT).show();

                }
            }
        });

        // this is for display data basically it taking us from one activity tto other activity.
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));

            }
        });

        // this is for edit button once user click on that button it update wha written in edit text in our table which name is price.
        // basically this function for update.
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv=new ContentValues();
                cv.put("expense_name", expense_name.getText().toString());
                cv.put("expense_price", expense_price.getText().toString());

                sqLiteDatabase=dbMain.getWritableDatabase();

                //query
                long recupdate = sqLiteDatabase.update("price",cv,"id=" +id,null);


                // if everything is good then it will toast msg of successful otherwise else part run.
                if(recupdate!=-1){
                    Toast.makeText(MainActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    clearData();
                }
                else{
                    Toast.makeText(MainActivity.this, "something wrong try again later", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void findid(){
        // Finding Id for edit text
        expense_name = (EditText)findViewById(R.id.expense_name);
        expense_price = (EditText)findViewById(R.id.expense_price);

        // Finding id foe button
        submit = (Button)findViewById(R.id.submit_btn);
        edit = (Button)findViewById(R.id.edit_btn);
        display = (Button)findViewById(R.id.display_btn);


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
                Intent intent = new Intent(MainActivity.this, feedback.class);
                startActivity(intent);

                return true;

            case R.id.about:
                Intent intent1 = new Intent(MainActivity.this, alaram.class);
                startActivity(intent1);

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }


    }


    // alaram activity call

    public void alaram(View view) {
        Intent intent = new Intent(MainActivity.this, alaram.class);
        startActivity(intent);
    }

    public void feedback(View view) {
        Intent intent = new Intent(MainActivity.this, feedback.class);
        startActivity(intent);
    }
}