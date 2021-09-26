package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class payment extends AppCompatActivity {

    TextView tv_hotel, tv_cusName, tv_checkIn, tv_checkOut, tv_rooms, tv_tot;

    String hotel, cusName, checkIn, checkOut, rooms, tot;

    Button btn_pay;

    DatabaseReference dbRef;


    Booking bk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();
        getCount();

        tv_hotel = findViewById(R.id.tv_HotelName_view);
        tv_cusName = findViewById(R.id.tv_cusName_view);
        tv_checkIn = findViewById(R.id.tv_CheckIn_view);
        tv_checkOut = findViewById(R.id.tv_CheckOut_view);
        tv_rooms = findViewById(R.id.tv_Rooms_view);
        btn_pay = findViewById(R.id.btn_pay);
        tv_tot = findViewById(R.id.tv_tot);

        Intent intent = getIntent();

        hotel = intent.getStringExtra("p_Hotel");
        cusName = intent.getStringExtra("p_Name");
        checkIn = intent.getStringExtra("p_CheckIn");
        checkOut = intent.getStringExtra("p_CheckOut");
        rooms = intent.getStringExtra("p_Rooms");



        tv_hotel.setText(hotel);
        tv_cusName.setText(cusName);
        tv_checkIn.setText(checkIn);
        tv_checkOut.setText(checkOut);
        tv_rooms.setText(rooms);

        bk = new Booking();

    }

    public void insertInfo(View view){

        Intent intent = new Intent(this, My_Bookings.class);
        String dbid;


        if (count==0){
            dbid = "1";
        }else{
            dbid = String.valueOf(count+1);
        }


        dbRef = FirebaseDatabase.getInstance().getReference().child("Bookings");

        try{
            if(TextUtils.isEmpty(hotel) || TextUtils.isEmpty(cusName) || TextUtils.isEmpty(checkIn) || TextUtils.isEmpty(checkOut) || TextUtils.isEmpty(rooms))
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            else {
                bk.setHotel(hotel.trim());
                bk.setName(cusName.trim());
                bk.setCheck_in(checkIn.trim());
                bk.setCheck_out(checkOut.trim());
                bk.setRooms(Integer.parseInt(rooms));
               // bk.setPayment(tv_tot.getText().toString());
                bk.setStatus("PENDING".trim());
                bk.setBid(dbid.trim());


                dbRef.child(dbid).setValue(bk);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        } catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Rooms", Toast.LENGTH_SHORT).show();
        }
    }







    public static long count = 0;

    public void getCount(){

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Bookings");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    count = (snapshot.getChildrenCount());

                    Toast.makeText(getApplicationContext(), "Database has "+ count +" values", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}