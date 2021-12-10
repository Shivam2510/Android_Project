package com.example.hbmsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {

    RatingBar ratingbar, ratingBar1, ratingBar2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingbar = findViewById(R.id.ratingbar);
        ratingbar.setRating(1.5f);

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratingbar.setRating(rating);
                Toast.makeText(feedback.this,"SERVICE RATING : "+String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });

        ratingBar1 = findViewById(R.id.ratingbar1);
        ratingBar1.setRating(1.5f);

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratingBar1.setRating(rating);
                Toast.makeText(feedback.this,"QUALITY RATING : "+String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });
        ratingBar2 = findViewById(R.id.ratingbar2);
        ratingBar2.setRating(1.5f);

        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratingBar2.setRating(rating);
                Toast.makeText(feedback.this,"APP RATING : "+String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });
        // Button for custom toast.

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
    }

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastText.setText("Thanks for your FeedBack");
        toastImage.setImageResource(R.drawable.ic_baseline_insert_emoticon_24);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
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
                Intent intent = new Intent(feedback.this, feedback.class);
                startActivity(intent);

                return true;

            case R.id.about:
                Intent intent1 = new Intent(feedback.this, alaram.class);
                startActivity(intent1);

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }


    }
}