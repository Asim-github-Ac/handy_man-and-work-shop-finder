package com.fyp.locale_lite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.locale_lite.Model.Order_model;
import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.RequestAdapter;
import com.fyp.locale_lite.ServiceProviders;
import com.fyp.locale_lite.SharedPrefrence.PrefManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.myHolder> {
    Context context;
    List<ServiceProviders> serviceProviderModelList;
    String status;

    public CustomerAdapter(Context context, List<ServiceProviders> serviceProviderModelList, String status) {
        this.context = context;
        this.serviceProviderModelList = serviceProviderModelList;
        this.status = status;
    }

    @NonNull
    @Override
    public CustomerAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_customer, parent, false);
        CustomerAdapter.myHolder viewHolder = new CustomerAdapter.myHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.myHolder holder, int position) {

        ServiceProviders serviceProviderModel=serviceProviderModelList.get(position);
        holder.fname.setText("Name : "+serviceProviderModel.getFirstname());
        holder.lname.setText("LName : "+serviceProviderModel.getLastname());
        holder.emailid.setText("Email : "+serviceProviderModel.getEmailid());
        holder.city.setText("City : "+serviceProviderModel.getCity());
        holder.pnumber.setText("Phone : "+serviceProviderModel.getPhonenum());
        Picasso.with(context).load(serviceProviderModel.getProfilePicUrl()).into(holder.imgcircle);
        holder.confirmOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrefManager prefManager=new PrefManager(context);
                holder.progressBar.setVisibility(View.VISIBLE);
                ConfirmOrderNow("approve",context,"id",holder,prefManager.getUserEmail(),serviceProviderModel.getEmailid(),serviceProviderModel.getCity(),serviceProviderModel.getPhonenum());

            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceProviderModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView fname,lname,pnumber,city,emailid, longitude, latitude;
        Button confirmOder;
        ImageView imgcircle;

        ProgressBar progressBar;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            fname=itemView.findViewById(R.id.fname);
            lname=itemView.findViewById(R.id.lname);
            pnumber=itemView.findViewById(R.id.phonenum);
            city= itemView.findViewById(R.id.city);
            emailid=itemView.findViewById(R.id.emailid);
            longitude = itemView.findViewById(R.id.longitude);
            latitude = itemView.findViewById(R.id.latitude);
            imgcircle=itemView.findViewById(R.id.circleimg);
            confirmOder=itemView.findViewById(R.id.ordernow);
            progressBar=itemView.findViewById(R.id.pbarorder);

        }
    }
    public void ConfirmOrderNow(String path,Context context,String orderid,CustomerAdapter.myHolder holder,String emailid,String mechanic_email,String mechanic_city,String mechanic_phone){
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        Order_model orderModel=new Order_model(emailid,mechanic_email,mechanic_city,mechanic_phone);
         firestore.collection("WorkShopFinder").document("data").collection(path).add(orderModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
             @Override
             public void onSuccess(DocumentReference documentReference) {
                 Toast.makeText(context, "Order Deliverd", Toast.LENGTH_SHORT).show();
                 holder.progressBar.setVisibility(View.GONE);
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {

                 holder.progressBar.setVisibility(View.INVISIBLE);
                 Toast.makeText(context, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });



    }
}
