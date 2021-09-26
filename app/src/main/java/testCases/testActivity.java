package testCases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.Booking;
import com.example.myproject.R;
import com.example.myproject.RecycleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class testActivity extends AppCompatActivity {

    EditText et_name, et_id;
    Button btn_add,btn_show;

    testUser st;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        et_name = findViewById(R.id.et_testName);
        et_id = findViewById(R.id.et_testId);

        btn_add = findViewById(R.id.btn_testAdd);
        btn_show = findViewById(R.id.btn_testShow);

        st = new testUser();

    }


    public void addUser(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("testUser");

        try{
            if(TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_id.getText()))
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            else {

                st.setName(et_name.getText().toString().trim());
               // st.setId(et_id.getText().toString().trim());


                dbRef.push().setValue(st);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

            }
        } catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Rooms", Toast.LENGTH_SHORT).show();
        }
    }



    public void validation(View view){

        String testName = "Pakaya";

        dbRef = FirebaseDatabase.getInstance().getReference("testUser");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        list = new ArrayList<>();
//        adapter = new RecycleAdapter(this,list,this);
//
//        recyclerView.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = "shashika";
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    testUser std = dataSnapshot.getValue(testUser.class);

                    if(name == std.getName()){
                        Toast.makeText(getApplicationContext(), "Pakaya 1", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                Toast.makeText(getApplicationContext(), "Pakaya 2", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        et_id.setText(testName);

    }





}