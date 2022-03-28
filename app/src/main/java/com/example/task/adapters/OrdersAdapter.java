package com.example.task.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.models.BillData;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {


    public ArrayList<BillData.BillsModel> billsList = new ArrayList<>();
    private Context mContext;

    private onOrderClick onOrderClick;

    BillData.BillsModel model;

    public OrdersAdapter(Context mContext, OrdersAdapter.onOrderClick onOrderClick) {
        this.mContext = mContext;
        this.onOrderClick = onOrderClick;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new
                OrdersAdapter.OrdersViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {

        model = billsList.get(position);


        switch (model.getDelivery_status_flag()){
            case "1":

                holder.tv_status.setText("Delivered");
                holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.colorPrimary2));
                holder.go_details.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary2));
                break;

            case "2":
                holder.tv_status.setText("Partial");
                holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.gray1));
                holder.go_details.setBackgroundColor(mContext.getResources().getColor(R.color.gray1));
                break;

            case "3":
                holder.tv_status.setText("Returned");
                holder.tv_status.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                holder.go_details.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                break;
        }



        holder.order_num.setText("#"+model.getBill_number());
        holder.tv_date.setText(model.getBill_date());
//        String  = model.getBill_amount();
        Float amt = Float.valueOf(model.getBill_amount());

        holder.tv_total_price.setText(String.format("%.1f",amt));




    }

    @Override
    public int getItemCount() {
        return billsList == null ? 0 : billsList.size();

    }

    public void setList(ArrayList<BillData.BillsModel> billsList) {
        this.billsList = billsList;
        notifyDataSetChanged();
    }


    class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView order_num, tv_status,tv_total_price, tv_date, txt_go;
        RelativeLayout go_details;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            order_num = itemView.findViewById(R.id.order_num);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_total_price = itemView.findViewById(R.id.tv_total_price);
            txt_go = itemView.findViewById(R.id.txt_go);
            go_details = itemView.findViewById(R.id.go_details);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            model = billsList.get(getAdapterPosition());
            onOrderClick.onClickListener(model, getAdapterPosition(),"SHOW_DETAILS");

        }
    }


    public interface onOrderClick{
        void onClickListener(BillData.BillsModel billsModel, int position, String action);
    }

}
