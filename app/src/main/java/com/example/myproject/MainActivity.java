package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    TextView tv_hotel;
    EditText et_name, et_checkIn, et_checkOut, et_rooms;
    Button btn_save;
    Booking bk;

    String hotel;

    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();




        tv_hotel = findViewById(R.id.tv_hotelName);
        et_name = findViewById(R.id.et_name);
        et_checkIn = findViewById(R.id.et_checkin);
        et_checkOut = findViewById(R.id.et_checkOut);
        et_rooms = findViewById(R.id.et_rooms);

        btn_save = findViewById(R.id.btn_next);

        Intent intent = getIntent();
        hotel = intent.getStringExtra("myObj");

        et_name.setText(hotel);

        bk = new Booking();
    }


    public void next(View view){

        Intent intent = new Intent(this, payment.class);

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

        startActivity(intent);

    }


}