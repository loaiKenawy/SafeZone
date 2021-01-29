package com.example.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {


    DatabaseReference getReference;
    TextView askForRegister;
    Button loginButton;
    EditText lEmail , lPassword;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lEmail = findViewById(R.id.Lemailaddress);
        lPassword = findViewById(R.id.LPassword);
        loginButton = findViewById(R.id.loginButton);

        AskForRegister();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LOGIN(lEmail.getText().toString() , lPassword.getText().toString() );

            }
        });
    }

    public void LOGIN(String mail , String password){
        
        getReference = FirebaseDatabase.getInstance().getReference().child("User").child(mail);

        getReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                     String DBPassword = snapshot.child("password").getValue().toString();

                    if(password.equals(DBPassword)){

                        String  fName ,lName ,Status;
                        fName = snapshot.child("firstName").getValue().toString();
                        lName = snapshot.child("lastName").getValue().toString();
                        Status = snapshot.child("Status").getValue().toString();

                      intent = new Intent(loginActivity.this,HomePage.class);
                      intent.putExtra("fName",fName);
                      intent.putExtra("lName",lName);
                      intent.putExtra("Status",Status);
                      startActivity(intent);
                    }
                    else if(DBPassword == null){
                        Toast.makeText(loginActivity.this,"DATABASE ERROR",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(loginActivity.this,"Wrong Password",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(loginActivity.this,"Wrong Phone number",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void AskForRegister(){
        askForRegister = findViewById(R.id.askForRegister);
        String askForRegisterString = "Don't have an account?Register";
        SpannableString ss = new SpannableString(askForRegisterString);
        ClickableSpan clickableSpan1 = new ClickableSpan() {

            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(loginActivity.this,"Register",Toast.LENGTH_SHORT);
                intent = new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(254,89,87));
                ds.setUnderlineText(true);
                ds.setFakeBoldText(true);
            }
        };

        ss.setSpan(clickableSpan1,22,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        askForRegister.setText(ss);
        askForRegister.setMovementMethod(LinkMovementMethod.getInstance());
    }

}