package tharuka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nadun.profile;

public class hotelEdit extends AppCompatActivity {

    EditText et_name, et_address, et_desc, et_uname, et_pass;
    Button btn_save;
    String id;

    HotelOwner o;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_edit);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        id = intent.getStringExtra("uid");

        et_name = findViewById(R.id.et_hName_edit);
        et_address = findViewById(R.id.et_hAddress_edit);
        et_desc = findViewById(R.id.et_hDescription_edit);
        et_uname = findViewById(R.id.et_hUname_edit);
        et_pass = findViewById(R.id.et_hPass_edit);


        btn_save = findViewById(R.id.btn_hSave);

        dataRead();

        o = new HotelOwner();

    }

    public void dataRead(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails").child(id);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    et_name.setText(snapshot.child("hotelName").getValue().toString());
                    et_address.setText(snapshot.child("address").getValue().toString());
                    et_desc.setText(snapshot.child("description").getValue().toString());
                    et_uname.setText(snapshot.child("uname").getValue().toString());
                    et_pass.setText(snapshot.child("pass").getValue().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void saveInfo(View view){

        Intent intent = new Intent(this, HotelProfile.class);
        intent.putExtra("uid", id);


        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(id)){
                    try{
                        o.setHotelName(et_name.getText().toString().trim());
                        o.setAddress(et_address.getText().toString().trim());
                        o.setDescription(et_desc.getText().toString().trim());
                        o.setUname(et_uname.getText().toString().trim());
                        o.setPass(et_pass.getText().toString().trim());
                        o.setId(id.trim());

                        dbRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails").child(id);
                        dbRef.setValue(o);

                        Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                    }catch (NumberFormatException e){

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No source to update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        startActivity(intent);
    }

}