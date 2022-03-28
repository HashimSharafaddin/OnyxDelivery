package com.example.task.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.models.BillData;
import com.example.task.models.BillItemsData;

import java.util.ArrayList;

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.BillsViewHolder> {


    public ArrayList<BillItemsData.BillsItemsModel> billsList = new ArrayList<>();
    private Context mContext;

    public BillsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    BillItemsData.BillsItemsModel model;


    @NonNull
    @Override
    public BillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                BillsAdapter.BillsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bill, parent, false));    }

    @Override
    public void onBindViewHolder(@NonNull BillsViewHolder holder, int position) {

        model = billsList.get(position);

        holder.tv_name.setText(model.getItem_name());
        holder.tv_qty.setText("x"+model.getItem_quantity());
        holder.tv_price.setText(model.getItem_price());


        Float amt = Float.valueOf(model.getItem_price());

        holder.tv_price.setText(String.format("%.1f",amt));


    }

    @Override
    public int getItemCount() {
        return billsList == null ? 0 : billsList.size();

    }

    public void setList(ArrayList<BillItemsData.BillsItemsModel> billsList) {
        this.billsList = billsList;
        notifyDataSetChanged();
    }

    class BillsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_qty, tv_price;

        public BillsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_qty = itemView.findViewById(R.id.tv_qty);
            tv_price = itemView.findViewById(R.id.tv_price);

        }



    }

}
