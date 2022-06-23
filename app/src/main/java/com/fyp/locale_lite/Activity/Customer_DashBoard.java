package com.fyp.locale_lite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fyp.locale_lite.Adapters.CustomerAdapter;
import com.fyp.locale_lite.MainActivity;
import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.sp_homepage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Customer_DashBoard extends AppCompatActivity {

    Button btnorder,logout,rating;
    RecyclerView recyclerView;
    CustomerAdapter customerAdapter;
    List<ServiceProviderModel >serviceProviderModel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dash_board);
        btnorder=findViewById(R.id.addorder);
        logout=findViewById(R.id.logout);
        rating=findViewById(R.id.ratings);

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Customer_Orders.class));
//                GetData();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(Customer_DashBoard.this);
                builder.setMessage("Do you want to Logout ?");
                builder.setCancelable(true);
                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(Customer_DashBoard.this, MainActivity.class);
                        startActivity(intent);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    }
                });

                builder.setPositiveButton("No",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){

                        dialogInterface.cancel();
                    }

                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Ratings.class));
            }
        });
    }


}