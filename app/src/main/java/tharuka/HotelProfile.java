package tharuka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nadun.create_acc2;
import nadun.editProfile;

public class HotelProfile extends AppCompatActivity {

    DatabaseReference dbRef;
    TextView tv_hotelName, tv_Address, tv_desc;
    Button btn_edit, btn_delete;

    String id = "1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_profile);
        getSupportActionBar().hide();

        tv_hotelName = findViewById(R.id.tv_hp_name);
        tv_Address = findViewById(R.id.tv_hp_address);
        tv_desc = findViewById(R.id.tv_hp_desc);

        btn_edit = findViewById(R.id.btn_editHotel);
        btn_delete = findViewById(R.id.btn_deleteHotel);

        dataRead();

    }


    public void dataRead(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails").child(id);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    tv_hotelName.setText(snapshot.child("hotelName").getValue().toString());
                    tv_Address.setText(snapshot.child("address").getValue().toString());
                    tv_desc.setText(snapshot.child("description").getValue().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void edit(View view){
        Intent intent = new Intent(this, hotelEdit.class);
        intent.putExtra("uid", id);
        startActivity(intent);
    }


    public void deleteData(View view){

        Intent intent = new Intent(this, HotelCreation.class);

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(id)){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails").child(id);
                    dbRef.removeValue();

                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Data has Successfully Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }









}