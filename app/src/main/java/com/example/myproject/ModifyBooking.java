package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ModifyBooking extends AppCompatActivity {

    TextView tv_hotel;
    EditText et_name, et_checkIn, et_checkOut, et_rooms;
    Button btn_save;
    Booking bk;

    String hotel;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_booking);
        getSupportActionBar().hide();

        tv_hotel = findViewById(R.id.tv_hotelName);
        et_name = findViewById(R.id.et_name);
        et_checkIn = findViewById(R.id.et_checkin);
        et_checkOut = findViewById(R.id.et_checkOut);
        et_rooms = findViewById(R.id.et_rooms);

        btn_save = findViewById(R.id.btn_next);

        Intent intent = getIntent();

        tv_hotel.setText(intent.getStringExtra("hName"));
        et_name.setText(intent.getStringExtra("cusName"));
        et_checkIn.setText(intent.getStringExtra("checkIn"));
        et_checkOut.setText(intent.getStringExtra("checkOut"));
        et_rooms.setText(intent.getStringExtra("rooms"));







        bk = new Booking();
    }





}