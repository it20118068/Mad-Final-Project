package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bookingModify extends AppCompatActivity {

    TextView tv_hotel;
    EditText et_name, et_checkIn, et_checkOut, et_rooms;
    Button btn_next;

    String cusName, hotel, checkIn, checkOut,rooms, bid;

    DatabaseReference dbRef;

    Booking bk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_modify);

        tv_hotel = findViewById(R.id.tv_hotelNameM);
        et_name = findViewById(R.id.et_nameM);
        et_checkIn = findViewById(R.id.et_checkinM);
        et_checkOut = findViewById(R.id.et_checkOutM);
        et_rooms = findViewById(R.id.et_roomsM);

        btn_next = findViewById(R.id.btn_nextM);

        Intent intent = getIntent();

        bid = intent.getStringExtra("bid");
        hotel = intent.getStringExtra("hName");
        cusName = intent.getStringExtra("cusName");
        checkIn = intent.getStringExtra("checkIn");
        checkOut = intent.getStringExtra("checkOut");
        rooms = intent.getStringExtra("rooms");



        tv_hotel.setText(intent.getStringExtra("hName"));
        et_name.setText(intent.getStringExtra("cusName"));
        et_checkIn.setText(intent.getStringExtra("checkIn"));
        et_checkOut.setText(intent.getStringExtra("checkOut"));
        et_rooms.setText(intent.getStringExtra("rooms"));

        bk = new Booking();

    }


    public void modifyBooking(View view){

        Intent intent = new Intent(this, My_Bookings.class);

        String hotelName = tv_hotel.getText().toString();
        String name = et_name.getText().toString();
        String checkIn = et_checkIn.getText().toString();
        String checkOut = et_checkOut.getText().toString();
        String rooms = et_rooms.getText().toString();

        intent.putExtra("p_Hotel",hotelName);
        intent.putExtra("p_Name",name);
        intent.putExtra("p_CheckIn",checkIn);
        intent.putExtra("p_CheckOut",checkOut);
        intent.putExtra("p_Rooms",rooms);




        dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings");

        try{
            if(TextUtils.isEmpty(hotel) || TextUtils.isEmpty(cusName) || TextUtils.isEmpty(checkIn) || TextUtils.isEmpty(checkOut) || TextUtils.isEmpty(rooms))
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            else {
                bk.setHotel(tv_hotel.getText().toString().trim());
                bk.setName(et_name.getText().toString().trim());
                bk.setCheck_in(et_checkIn.getText().toString().trim());
                bk.setCheck_out(et_checkOut.getText().toString().trim());
                bk.setRooms(Integer.parseInt(et_rooms.getText().toString().trim()));
                bk.setBid(bid.trim());
                bk.setStatus("PENDING".trim());

                dbRef.child(bid).setValue(bk);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        } catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Rooms", Toast.LENGTH_SHORT).show();
        }

    }

}