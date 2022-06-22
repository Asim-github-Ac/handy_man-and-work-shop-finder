package com.fyp.locale_lite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fyp.locale_lite.Activity.Customer_DashBoard;
import com.fyp.locale_lite.Activity.Google_Authentication;
import com.fyp.locale_lite.Admin_panel.Admin_Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    FirebaseUser firebaseUser;
    String userid;
    LinearLayout mainact1,mainact2;

Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.startPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
        mainact1=findViewById(R.id.mainact1);
        mainact2=findViewById(R.id.mainact2);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            mainact1.setVisibility(View.INVISIBLE);
            mainact2.setVisibility(View.VISIBLE);
            userid = firebaseUser.getUid();
            DatabaseReference database = FirebaseDatabase.getInstance().getReference("Customers");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int count=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Customers c = snapshot.getValue(Customers.class);
                        if (c.getId().equals(userid)) {
                            count=1;
                        }
                    }
                    if(count==1)
                    {
                        Intent intent = new Intent(MainActivity.this, Customer_DashBoard.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        {
                            DatabaseReference database1 = FirebaseDatabase.getInstance().getReference("ServiceProviders");
                            database1.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    int cont =0;
                                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                                        ServiceProviders sp = dataSnapshot1.getValue(ServiceProviders.class);
                                        if(sp.getId().equals(userid) && sp.getPending().equals(true)){
                                            cont=1;
                                            break;
                                        }
                                    }
                                    if(cont==1)
                                    {
                                        Toast.makeText(MainActivity.this,"Logged in",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, sp_homepage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Intent intent = new Intent(MainActivity.this, Google_Authentication.class);
                                        startActivity(intent);
                                        finish();


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });


        }

        Button createNew = (Button) findViewById(R.id.createnew);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Google_Authentication.class);
                startActivity(intent);
            }
        });
        Button btnadmin=findViewById(R.id.adminloginmain);
        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admin_Login.class));
            }
        });
        Button logIn = (Button) findViewById(R.id.login);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this, Login.class);
                startActivity(i2);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_optional_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.emp:
                startActivity(new Intent(getApplicationContext(), Admin_Login.class));
                break;
            default:
                Toast.makeText(getApplicationContext(), "Item not found", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                moveTaskToBack(true);
            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }

        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}
