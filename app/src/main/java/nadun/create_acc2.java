package nadun;

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

public class create_acc2 extends AppCompatActivity {

    EditText et_name, et_email, et_mobile, et_uname, et_pass;

    Button btn_CreateAccount;

    User u;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc2);
        getSupportActionBar().hide();

        getCount();

        et_name = findViewById(R.id.et_name_create);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.et_mobileNo);
        et_uname = findViewById(R.id.et_uname);
        et_pass = findViewById(R.id.et_pass);

        btn_CreateAccount = findViewById(R.id.btn_CreateAccount);

        u = new User();

    }

    public void CreateAccount(View view){
        String dbid;


        if (count==0){
            dbid = "1";
        }else{
            dbid = String.valueOf(count+1);
        }


        dbRef = FirebaseDatabase.getInstance().getReference().child("userDetails");

        try{
            if(TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_email.getText()) || TextUtils.isEmpty(et_mobile.getText()) || TextUtils.isEmpty(et_uname.getText()) || TextUtils.isEmpty(et_pass.getText()))
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            else {

                u.setName(et_name.getText().toString().trim());
                u.setEmail(et_email.getText().toString().trim());
                u.setMobileNo(et_mobile.getText().toString().trim());
                u.setUsername(et_uname.getText().toString().trim());
                u.setPass(et_pass.getText().toString().trim());
                u.setId(dbid.trim());

                dbRef.child(dbid).setValue(u);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();

            }
        } catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Rooms", Toast.LENGTH_SHORT).show();
        }


    }


    public static long count = 0;

    public void getCount(){

        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("userDetails");
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