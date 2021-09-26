package com.example.myproject;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {



    Context context;
    ArrayList<Booking> list;
    onBookingsListner mOnBookListner;


    public RecycleAdapter(Context context, ArrayList<Booking> list, onBookingsListner onBookListner) {
        this.context = context;
        this.list = list;
        this.mOnBookListner = onBookListner;
    }



    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.booking_recycle,parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnBookListner);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {

        Booking bk = list.get(position);

        holder.tv_listCheckIn.setText(bk.getCheck_in());
        holder.tv_listCheckOut.setText(bk.getCheck_out());
        holder.tv_hotel_name.setText(bk.getHotel());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_hotel_name, tv_listCheckIn, tv_listCheckOut;
        onBookingsListner onBookListner;

        public ViewHolder(@NonNull View itemView, onBookingsListner onBookListner) {
            super(itemView);
            tv_hotel_name = itemView.findViewById(R.id.tv_hotel_name);
            tv_listCheckIn = itemView.findViewById(R.id.tv_listCheckIn);
            tv_listCheckOut = itemView.findViewById(R.id.tv_listCheckOut);
            this.onBookListner = onBookListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onBookListner.onBookingClick(getAdapterPosition());
        }
    }


    public interface onBookingsListner{
        void onBookingClick(int position);
    }


}
