package com.fyp.locale_lite;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Pending extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_image);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Pending.this,"Logged in",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Pending.this, sp_homepage.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
