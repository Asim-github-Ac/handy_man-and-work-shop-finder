package com.fyp.locale_lite.Admin_panel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class View_Activity_Admins extends AppCompatActivity {

    List<ServiceProviderModel> serviceProviderModelList=new ArrayList<>();
    RecyclerView recyclerView;
    String path = "WorkShopFinder";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admins);
        recyclerView=findViewById(R.id.admin_recy);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("User List Finding");
        progressDialog.setMessage("Loadings.......");
        progressDialog.setCancelable(false);
        progressDialog.show();

        FetchData(path);

    }

    private void FetchData(String path1) {
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        firestore.collection(path1).document("data").collection("category").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots.isEmpty()){
                    progressDialog.dismiss();
                    Toast.makeText(View_Activity_Admins.this, "Record Not FOund", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    List<ServiceProviderModel> models=queryDocumentSnapshots.toObjects(ServiceProviderModel.class);
                    serviceProviderModelList.addAll(models);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(new Admin_Adapter(getApplicationContext(),serviceProviderModelList));

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText(View_Activity_Admins.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}