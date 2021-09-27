package testCases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myproject.Booking;
import com.example.myproject.R;
import com.example.myproject.RecycleAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class testActivity extends AppCompatActivity {

    EditText et_name, et_id;
    Button btn_add,btn_show;
    ImageView img;
    public Uri imageUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;

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

        img = findViewById(R.id.img_testPic);

        st = new testUser();
    }


    public void uploadPic(View view){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        choosePic();
    }





    private void choosePic() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);


    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData() != null ){
            imageUri = data.getData();
            img.setImageURI(imageUri);
            st.setImgUri(imageUri);

        }
    }

    private void uploadImage() {



        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+randomKey);

     //   riversRef.getFile(imageUri);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), "Image upload failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }




    public void addUser(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("testUser");

        try{
            if(TextUtils.isEmpty(et_name.getText()) || TextUtils.isEmpty(et_id.getText()))
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            else {

                st.setName(et_name.getText().toString().trim());
               st.setId(et_id.getText().toString().trim());


             //    uploadImage();
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