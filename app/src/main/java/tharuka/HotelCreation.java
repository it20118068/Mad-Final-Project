package tharuka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
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

public class HotelCreation extends AppCompatActivity {

    EditText et_name, et_address, et_desc, et_uname, et_pass;
    Button btn_Create;

    HotelOwner o;

    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_creation);
        getSupportActionBar().hide();

        getCount();

        et_name = findViewById(R.id.et_hName);
        et_address = findViewById(R.id.et_hAddress);
        et_desc = findViewById(R.id.et_hDescription);
        et_uname = findViewById(R.id.et_hUname);
        et_pass = findViewById(R.id.et_hPass);

        btn_Create = findViewById(R.id.btn_hCreate);

        o = new HotelOwner();

    }


    //Insert
    public void Create(View view){

        getCount();
        String dbid;
        if (count==0){
            dbid = "1";
        }else{
            dbid = String.valueOf(count+1);
        }

        dbRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails").child("HotelName");

        try{
            if(TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_address.getText()) || TextUtils.isEmpty(et_desc.getText()) || TextUtils.isEmpty(et_uname.getText()) || TextUtils.isEmpty(et_pass.getText()))
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            else {

                o.setHotelName(et_name.getText().toString().trim());
                o.setAddress(et_address.getText().toString().trim());
                o.setDescription(et_desc.getText().toString().trim());
                o.setUname(et_uname.getText().toString().trim());
                o.setPass(et_pass.getText().toString().trim());
                o.setId(dbid.trim());

                dbRef.child("1").setValue(o);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e){

        }


    }








    public static long count = 0;

    public void getCount(){

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("hotelDetails");
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