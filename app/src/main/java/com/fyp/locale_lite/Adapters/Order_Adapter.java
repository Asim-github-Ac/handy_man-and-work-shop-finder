package com.fyp.locale_lite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.locale_lite.Model.Order_model;
import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.SharedPrefrence.PrefManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.myHolder> {
    Context context;
    List<Order_model> order_modelList;

    public Order_Adapter(Context context, List<Order_model> order_modelList) {
        this.context = context;
        this.order_modelList = order_modelList;
    }

    @NonNull
    @Override
    public Order_Adapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_list_item, parent, false);
        Order_Adapter.myHolder viewHolder = new Order_Adapter.myHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Order_Adapter.myHolder holder, int position) {
        Order_model orderModel=order_modelList.get(position);
        holder.tv_cus_email.setText("Customer Email : "+orderModel.getCustomer_email());
        holder.tv_number.setText("Mechanic Number : "+orderModel.getMechanic_Phone());
        holder.tv_mec_email.setText("Mechanic Email : "+orderModel.getMechanic_email());
        holder.tv_mec_city.setText("Mechanic City : "+orderModel.getMechanic_city());
        holder.btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager prefManager=new PrefManager(context);
                holder.progressBar.setVisibility(View.VISIBLE);
                ConfirmOrderNow("active",context,"id",holder,prefManager.getUserEmail(),orderModel.getCustomer_email(),orderModel.getMechanic_email(),orderModel.getMechanic_Phone());
            }
        });

    }

    @Override
    public int getItemCount() {
        return order_modelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView tv_cus_email,tv_number,tv_mec_city,tv_mec_email;
        Button btnorder;
        ProgressBar progressBar;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            tv_cus_email=itemView.findViewById(R.id.cus_email);
            tv_mec_email=itemView.findViewById(R.id.mec_name);
            tv_mec_city=itemView.findViewById(R.id.mec_city);
            tv_number=itemView.findViewById(R.id.mec_number);
            btnorder=itemView.findViewById(R.id.corders);
            progressBar=itemView.findViewById(R.id.cbarorder);

        }
    }
    public void ConfirmOrderNow(String path,Context context,String orderid,Order_Adapter.myHolder holder,String emailid,String mechanic_email,String mechanic_city,String mechanic_phone){
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
