package com.fyp.locale_lite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.RequestAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.myHolder> {
    Context context;
    List<ServiceProviderModel> serviceProviderModelList;

    public CustomerAdapter(Context context, List<ServiceProviderModel> serviceProviderModelList) {
        this.context = context;
        this.serviceProviderModelList = serviceProviderModelList;
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

        ServiceProviderModel serviceProviderModel=serviceProviderModelList.get(position);
        holder.fname.setText("Name "+serviceProviderModel.getFname());
        holder.lname.setText("LName "+serviceProviderModel.getLname());
        holder.emailid.setText("Email "+serviceProviderModel.getEmailid());
        holder.city.setText("City "+serviceProviderModel.getCity());
        holder.pnumber.setText("Phone"+serviceProviderModel.getPhonenumber());
        holder.confirmOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceProviderModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView fname,lname,pnumber,city,emailid;
        Button confirmOder;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            fname=itemView.findViewById(R.id.fname);
            lname=itemView.findViewById(R.id.lname);
            pnumber=itemView.findViewById(R.id.phonenum);
            city= itemView.findViewById(R.id.city);
            emailid=itemView.findViewById(R.id.emailid);
            confirmOder=itemView.findViewById(R.id.ordernow);

        }
    }
    public void ConfirmOrderNow(String orderid){
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();

    }
}
