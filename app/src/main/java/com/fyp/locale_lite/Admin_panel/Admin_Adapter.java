package com.fyp.locale_lite.Admin_panel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.locale_lite.Adapters.CustomerAdapter;
import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Admin_Adapter extends RecyclerView.Adapter<Admin_Adapter.myHolder> {

    Context context;
    List<ServiceProviderModel> serviceProviderModelList;

    public Admin_Adapter(Context context, List<ServiceProviderModel> serviceProviderModelList) {
        this.context = context;
        this.serviceProviderModelList = serviceProviderModelList;
    }

    @NonNull
    @Override
    public Admin_Adapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_admins_activity, parent, false);
        Admin_Adapter.myHolder viewHolder = new Admin_Adapter.myHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Admin_Adapter.myHolder holder, int position) {

        ServiceProviderModel serviceProviderModel=serviceProviderModelList.get(position);
        holder.tvfname.setText("FName : "+serviceProviderModel.getFname());
        holder.tvlname.setText("LName : "+serviceProviderModel.getLname());
        holder.tvemail.setText("Email : "+serviceProviderModel.getEmailid());
        holder.tvnumber.setText("Number : "+serviceProviderModel.getPhonenumber());
        holder.btndell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                serviceProviderModelList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
//                FirebaseFirestore firestore=FirebaseFirestore.getInstance();
//                firestore.collection("WorkShopFinder").document("data").collection("category").document().delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(context, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
                }
        });
    }

    @Override
    public int getItemCount() {
        return serviceProviderModelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView tvfname,tvlname,tvnumber,tvemail;
        Button btndell;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            tvfname=itemView.findViewById(R.id.item_fname);
            tvlname=itemView.findViewById(R.id.item_lname);
            tvemail=itemView.findViewById(R.id.item_email);
            tvnumber=itemView.findViewById(R.id.item_pnumber);
            btndell=itemView.findViewById(R.id.delete_users);

        }
    }
}
