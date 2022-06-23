package com.fyp.locale_lite.PendingDashBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.fyp.locale_lite.Adapters.CustomerAdapter;
import com.fyp.locale_lite.Adapters.Order_Adapter;
import com.fyp.locale_lite.Model.Order_model;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.ServiceProviders;
import com.fyp.locale_lite.ShowPendingRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PendingDashBoard extends AppCompatActivity {
    List<Order_model> serviceProvidersList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_dash_board);
        serviceProvidersList=new ArrayList<>();
        recyclerView=findViewById(R.id.tvrecy);
        GetData();


    }




    public void GetData(){
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        firestore.collection("WorkShopFinder").document("data").collection("approve").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()){
                    Toast.makeText(PendingDashBoard.this, "Record Not Found", Toast.LENGTH_SHORT).show();
                }else {
                    List<Order_model> serviceProviders=queryDocumentSnapshots.toObjects(Order_model.class);
                    serviceProvidersList.addAll(serviceProviders);
                    System.out.println("data_______________"+serviceProvidersList.get(0).getMechanic_city());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new Order_Adapter(getApplicationContext(),serviceProvidersList));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(PendingDashBoard.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}