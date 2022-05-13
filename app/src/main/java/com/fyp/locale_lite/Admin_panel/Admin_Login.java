package com.fyp.locale_lite.Admin_panel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.fyp.locale_lite.R;

public class Admin_Login extends AppCompatActivity {

    Button btnlogin;
    EditText edemail,edpass;
    ProgressBar pbadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        btnlogin=findViewById(R.id.loginadminnow);
        edemail=findViewById(R.id.edemail_admin);
        edpass=findViewById(R.id.edpass_admin);
        pbadmin=findViewById(R.id.adminpb);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edemail.getText().toString().trim().equals("admin@gmail.com") && edpass.getText().toString().trim().equals("admin")){
                    startActivity(new Intent(getApplicationContext(),Home_Page_Admin.class));
                }else
                {
                    edemail.setError("Something Went Wrong");
                    edpass.setError("Something Went Wrong");
                }
            }
        });
    }
}