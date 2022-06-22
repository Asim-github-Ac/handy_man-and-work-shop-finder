package com.fyp.locale_lite.Admin_panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fyp.locale_lite.Activity.Order_Now;
import com.fyp.locale_lite.MainActivity;
import com.fyp.locale_lite.R;

public class Home_Page_Admin extends AppCompatActivity {

    Button btnmechaincs,btncustomer,btnlogouts,btnOrder_Customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_admin);
        btnlogouts=findViewById(R.id.logOut_admin);
        btncustomer=findViewById(R.id.view_Customer_admin);
        btnmechaincs=findViewById(R.id.view_mechanics_admin);
        btnOrder_Customer=findViewById(R.id.view_customer_order);


        btnOrder_Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Order_Now.class);
                intent.putExtra("key","Mechanics");
                startActivity(intent);
            }
        });
        btnmechaincs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CarigarActivity.class));

/*
                Intent intent=new Intent(getApplicationContext(),View_Activity_Admins.class);
                intent.putExtra("key","Mechanics");
                startActivity(intent);
*/
            }
        });
        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),View_Activity_Admins.class);
                intent.putExtra("key","Customers");
                startActivity(intent);
            }
        });

        btnlogouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}