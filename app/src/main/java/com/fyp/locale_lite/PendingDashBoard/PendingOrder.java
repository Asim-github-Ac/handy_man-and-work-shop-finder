package com.fyp.locale_lite.PendingDashBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.locale_lite.Adapters.CustomerAdapter;
import com.fyp.locale_lite.R;
import com.fyp.locale_lite.ServiceProviders;

import java.util.List;

public class PendingOrder extends RecyclerView.Adapter<PendingOrder.myHolder> {
    Context context;
    List<ServiceProviders> serviceProviders;

    public PendingOrder(Context context, List<ServiceProviders> serviceProviders) {
        this.context = context;
        this.serviceProviders = serviceProviders;
    }

    @NonNull
    @Override
    public PendingOrder.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_customer, parent, false);
        PendingOrder.myHolder viewHolder = new PendingOrder.myHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrder.myHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return serviceProviders.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        public myHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
