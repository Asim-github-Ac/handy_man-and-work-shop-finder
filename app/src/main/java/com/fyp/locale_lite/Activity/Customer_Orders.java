package com.fyp.locale_lite.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fyp.locale_lite.Adapters.CustomerAdapter;
import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.ServiceProviders;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Customer_Orders extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomerAdapter customerAdapter;
    ProgressDialog progressDialog;
    List<ServiceProviders> serviceProvider=new ArrayList<>();
    List<ServiceProviderModel >serviceProviderModel=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);
        progressDialog=new ProgressDialog(getApplicationContext());
        recyclerView=findViewById(R.id.recyclervieworder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetCarigars();
      //  GetData();
    }
    private void GetData() {
//        recyclerView.setVisibility(View.VISIBLE);
//        FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
//        firebaseFirestore.collection("WorkShopFinder").document("data").collection("approve").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                if (queryDocumentSnapshots.isEmpty()){
//                    progressDialog.dismiss();
//                    Toast.makeText(Customer_Orders.this, "Record Not Found", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    List<ServiceProviderModel> serviceProviderModelList=queryDocumentSnapshots.toObjects(ServiceProviderModel.class);
//                    serviceProviderModel.addAll(serviceProviderModelList);
//                    customerAdapter=new CustomerAdapter(getApplicationContext(),serviceProviderModelList);
//                    recyclerView.setAdapter(customerAdapter);
//
//                    progressDialog.dismiss();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                progressDialog.dismiss();
//                Toast.makeText(Customer_Orders.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }
public void GetCarigars(){
    progressDialog.setTitle("Orders List");
    progressDialog.setMessage("Loadings................");
   // progressDialog.show();
    FirebaseDatabase.getInstance().getReference().child("ServiceProviders").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot snapshot1:snapshot.getChildren()){
                ServiceProviders serviceProviders=snapshot1.getValue(ServiceProviders.class);
                serviceProvider.add(serviceProviders);
                customerAdapter=new CustomerAdapter(getApplicationContext(),serviceProvider,"pending");
                recyclerView.setAdapter(customerAdapter);
                progressDialog.dismiss();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

            Toast.makeText(Customer_Orders.this, "error"+ error.getMessage(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    });

    }
}