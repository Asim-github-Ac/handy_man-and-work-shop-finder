package com.fyp.locale_lite.Admin_panel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fyp.locale_lite.R;

public class CarigarActivity extends AppCompatActivity implements View.OnClickListener {

    CardView card_ar, card_c, card_cr, card_o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carigar);

        findViewById(R.id.card_ar).setOnClickListener(this);
        findViewById(R.id.card_c).setOnClickListener(this);
        findViewById(R.id.card_cr).setOnClickListener(this);
        findViewById(R.id.card_o).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                Intent intent=new Intent(getApplicationContext(),View_Activity_Admins.class);
                intent.putExtra("key","Mechanics");
                startActivity(intent);
                break;
        }
    }
}