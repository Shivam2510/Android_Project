package com.example.hbmsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class alaram extends AppCompatActivity {

    EditText mHourEditText;
    EditText mMinuteEditText;

    Button msetAlaramButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alaram);

        mHourEditText = (EditText)findViewById(R.id.hour);
        mMinuteEditText = (EditText)findViewById(R.id.min);
        msetAlaramButton = (Button)findViewById(R.id.setAlaram_btn);
        msetAlaramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = Integer.parseInt(mHourEditText.getText().toString());
                int minute = Integer.parseInt(mMinuteEditText.getText().toString());

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR,hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES,minute);


                if(hour <= 24 && minute <=60){
                    startActivity(intent);
                }
                else{
                    Toast.makeText(alaram.this,"Enter Wrong Time", Toast.LENGTH_SHORT).show();
                }



            }
        });
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
                Intent intent = new Intent(alaram.this, feedback.class);
                startActivity(intent);

                return true;

            case R.id.about:
                Intent intent1 = new Intent(alaram.this, alaram.class);
                startActivity(intent1);

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }


    }
}