package nadun;

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

public class profile extends AppCompatActivity {
    DatabaseReference dbRef;
    TextView tv_name, tv_contactNo, tv_email;
    Button btn_edit, btn_feedHis, btn_deleteAcc;

    String id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        tv_name = findViewById(R.id.tv_pName);
        tv_contactNo = findViewById(R.id.tv_contactNo);
        tv_email = findViewById(R.id.tv_email);

        btn_edit = findViewById(R.id.btn_editAccount);
        btn_feedHis = findViewById(R.id.btn_fdHistory);
        btn_deleteAcc = findViewById(R.id.btn_deleteAccount);

        dataRead();




    }

    public void edit(View view){
        Intent intent = new Intent(this, editProfile.class);
        intent.putExtra("uid", id);
        startActivity(intent);
    }







    public void dataRead(){
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("userDetails").child(id);
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    tv_name.setText(snapshot.child("name").getValue().toString());
                    tv_contactNo.setText(snapshot.child("mobileNo").getValue().toString());
                    tv_email.setText(snapshot.child("email").getValue().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void deleteData(View view){

        Intent intent = new Intent(this, create_acc2.class);

        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("userDetails");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(id)){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("userDetails").child(id);
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