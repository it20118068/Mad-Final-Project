package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewBooking extends AppCompatActivity {

    TextView tv_hotel, tv_cusName, tv_checkIn, tv_checkOut, tv_rooms, tv_status;
    Button btn_modify, btn_cancel;
    String id;

    DatabaseReference dbRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);
        getSupportActionBar().hide();



        tv_hotel = findViewById(R.id.tv_HotelName_view);
        tv_cusName = findViewById(R.id.tv_cusName_view);
        tv_checkIn = findViewById(R.id.tv_CheckIn_view);
        tv_checkOut = findViewById(R.id.tv_CheckOut_view);
        tv_rooms = findViewById(R.id.tv_Rooms_view);
       // tv_tot = findViewById(R.id.tv_payment_view);
        tv_status = findViewById(R.id.tv_status_view);

        btn_modify = findViewById(R.id.btn_modify);
        btn_cancel = findViewById(R.id.btn_delete);


        Intent intent = getIntent();

        id = intent.getStringExtra("bid");
        tv_hotel.setText(intent.getStringExtra("hName"));
        tv_cusName.setText(intent.getStringExtra("cusName"));
        tv_checkIn.setText(intent.getStringExtra("checkIn"));
        tv_checkOut.setText(intent.getStringExtra("checkOut"));
        tv_rooms.setText(intent.getStringExtra("rooms"));
       // tv_tot.setText(intent.getStringExtra("pay"));
        tv_status.setText(intent.getStringExtra("status"));

    }


    public void modify(View view){

        Intent intent = new Intent(this, bookingModify.class);

        String hName = tv_hotel.getText().toString();
        String cusName = tv_cusName.getText().toString();
        String checkIn = tv_checkIn.getText().toString();
        String checkOut = tv_checkOut.getText().toString();
        String rooms = tv_rooms.getText().toString();

        intent.putExtra("bid", id);
        intent.putExtra("hName", hName);
        intent.putExtra("cusName", cusName);
        intent.putExtra("checkIn", checkIn);
        intent.putExtra("checkOut", checkOut);
        intent.putExtra("rooms", rooms);

        startActivity(intent);

    }


    public void deleteData(View view){

        Intent intent = new Intent(this, My_Bookings.class);

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Bookings");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.hasChild(id)){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings").child(id);
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(), "Data has successfully deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        startActivity(intent);



    }



}