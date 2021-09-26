package nadun;

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

public class editProfile extends AppCompatActivity {

    EditText et_name, et_email, et_mobile, et_uname, et_pass;
    Button btn_saveEdit;
    String id;

    User u;

    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();


        Intent intent = getIntent();
        id = intent.getStringExtra("uid");


        et_name = findViewById(R.id.et_name_edit);
        et_email = findViewById(R.id.et_email_edit);
        et_mobile = findViewById(R.id.et_mobileNo_edit);
        et_uname = findViewById(R.id.et_uname_edit);
        et_pass = findViewById(R.id.et_pass_edit);

        dataRead();

        btn_saveEdit = findViewById(R.id.btn_saveEdit);

        u = new User();
    }



    public void dataRead(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("userDetails").child(id);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    et_name.setText(snapshot.child("name").getValue().toString());
                    et_mobile.setText(snapshot.child("mobileNo").getValue().toString());
                    et_email.setText(snapshot.child("email").getValue().toString());
                    et_uname.setText(snapshot.child("username").getValue().toString());
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

        Intent intent = new Intent(this, profile.class);
        intent.putExtra("uid", id);


        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("userDetails");
        upRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(id)){
                    try{
                        u.setName(et_name.getText().toString().trim());
                        u.setEmail(et_email.getText().toString().trim());
                        u.setMobileNo(et_mobile.getText().toString().trim());
                        u.setUsername(et_uname.getText().toString().trim());
                        u.setPass(et_pass.getText().toString().trim());
                        u.setId(id.trim());

                        dbRef = FirebaseDatabase.getInstance().getReference().child("userDetails").child(id);
                        dbRef.setValue(u);

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