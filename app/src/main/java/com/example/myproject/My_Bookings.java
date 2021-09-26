package com.example.myproject;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class My_Bookings extends AppCompatActivity implements  RecycleAdapter.onBookingsListner{

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    RecyclerView recyclerView;
    RecycleAdapter adapter;

    DatabaseReference dbRef;

    ArrayList<Booking> list;

    String hotelList[] = {"Galadari","Shangri-la","Galleface"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recycleview);
        dbRef = FirebaseDatabase.getInstance().getReference("Bookings");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new RecycleAdapter(this,list,this);

        recyclerView.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Booking std = dataSnapshot.getValue(Booking.class);
                    list.add(std);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBookingClick(int position) {
        Booking b = list.get(position);

        String hName = b.getHotel();
        String cusName = b.getName();
        String checkIn = b.getCheck_in();
        String checkOut = b.getCheck_out();
       // String pay = b.getPayment();
        String rooms = Integer.toString(b.getRooms());
        String status = b.getStatus();
        String bid = b.getBid();




        Intent intent = new Intent(this, ViewBooking.class);
        intent.putExtra("hName", hName);
        intent.putExtra("cusName", cusName);
        intent.putExtra("checkIn", checkIn);
        intent.putExtra("checkOut", checkOut);
        intent.putExtra("bid", bid);
        intent.putExtra("rooms", rooms);
        intent.putExtra("status", status);
        startActivity(intent);

    }


}