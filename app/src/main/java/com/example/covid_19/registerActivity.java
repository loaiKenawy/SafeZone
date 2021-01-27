package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerActivity extends AppCompatActivity {

    User user = new User();

    EditText fName, lName, rPhone, rPassword, rvPassword , rage ;

    Button RegisterButton;

    DatabaseReference insertReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisterButton = findViewById(R.id.registerButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    public void register(){
        fName = findViewById(R.id.rFname);
        lName = findViewById(R.id.rLname);;
        rPhone = findViewById(R.id.remailAddress);;
        rPassword = findViewById(R.id.rPassword);;
        rvPassword = findViewById(R.id.rvPassword);;
        rage = findViewById(R.id.rAge);

        if(fName.getText().toString().isEmpty()){
            fName.setError("First name filed is empty");
        }
        else if(lName.getText().toString().isEmpty()){
            lName.setError("Last name filed is empty");
        }
        else if(rPhone.getText().toString().isEmpty()){
            rPhone.setError("Email filed is empty");
        }
        else if(rPassword.getText().toString().isEmpty())
        {
            rPassword.setError("Password filed is empty");
        }
        else if(rvPassword.getText().toString().isEmpty()){
            rvPassword.setError("Verify password filed is empty");
        }
        else if(rage.getText().toString().isEmpty()){
            rage.setError("Age filed is empty");
        }
        else{
            String s1 , s2 , s3 , s4 , userID;
            int s5;
            s1 = fName.getText().toString();
            s2 = lName.getText().toString();
            s3 = rPhone.getText().toString();
            s4 = rPassword.getText().toString();
            userID = s3;
            s5 = 0;

            user.Register(s1,s2,s3,s4,s5);
            insertReference = FirebaseDatabase.getInstance().getReference("User");
            insertReference.child(userID).setValue(user);
            Toast.makeText(registerActivity.this,"Done",Toast.LENGTH_LONG).show();
        }
    }
}