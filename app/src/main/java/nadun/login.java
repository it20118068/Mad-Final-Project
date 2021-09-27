package nadun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myproject.R;

import tharuka.HotelCreation;

public class login extends AppCompatActivity {

    Button btn_user, btn_hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_user = findViewById(R.id.btn_lg_user);
        btn_hotel = findViewById(R.id.btn_lg_hotel);

    }


    public void user(View view){
        Intent intent = new Intent(this, create_acc2.class);
        startActivity(intent);
    }

    public void hotel(View view){
        Intent intent = new Intent(this, HotelCreation.class);
        startActivity(intent);
    }




}