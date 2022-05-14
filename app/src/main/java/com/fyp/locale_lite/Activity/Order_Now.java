package com.fyp.locale_lite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.fyp.locale_lite.Adapters.Order_Adapter;
import com.fyp.locale_lite.Admin_panel.Admin_Adapter;
import com.fyp.locale_lite.Model.Order_model;
import com.fyp.locale_lite.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Order_Now extends AppCompatActivity {

    Order_Adapter order_adapter;
    List<Order_model> order_modelList=new ArrayList<>();
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_now);
        recyclerView=findViewById(R.id.recy_order);
        progressDialog=new ProgressDialog(getApplicationContext());
        progressDialog.setTitle("Order List");
        progressDialog.setMessage("Loadings........");
        progressDialog.setCancelable(true);
        progressDialog.show();

        GetData();
    }
    public void GetData(){
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        firestore.collection("WorkShopFinder").document("order").collection("customer").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(Order_Now.this, "Record Not Found", Toast.LENGTH_SHORT).show();

                }else {
                    progressDialog.dismiss();
                    List<Order_model> order=queryDocumentSnapshots.toObjects(Order_model.class);
                    order_modelList.addAll(order);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new Order_Adapter(getApplicationContext(),order_modelList));

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Order_Now.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}