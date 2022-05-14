package com.fyp.locale_lite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.locale_lite.Model.Order_model;
import com.fyp.locale_lite.Model.ServiceProviderModel;
import com.fyp.locale_lite.R;

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

    }

    @Override
    public int getItemCount() {
        return order_modelList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView tv_cus_email,tv_number,tv_mec_city,tv_mec_email;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            tv_cus_email=itemView.findViewById(R.id.cus_email);
            tv_mec_email=itemView.findViewById(R.id.mec_name);
            tv_mec_city=itemView.findViewById(R.id.mec_city);
            tv_number=itemView.findViewById(R.id.mec_number);

        }
    }
}
