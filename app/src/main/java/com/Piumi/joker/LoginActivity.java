package com.Piumi.joker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText lEmail;
    EditText lPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences( "User", this.MODE_PRIVATE);
        lEmail = (EditText)findViewById(R.id.logEmail);
        lPassword = (EditText)findViewById(R.id.logPassword);
    }

    public void onLogin(View view){

        if(sharedpreferences.contains("uEmail") && lEmail.getText().toString().toLowerCase().equals(sharedpreferences.getString("uEmail","").toLowerCase()) &&
                lPassword.getText().toString().equals(sharedpreferences.getString("uPassword","")))
        {
            Toast.makeText(this, "User Authenticate Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.finish();
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "User name or Password incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegister(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.finish();
        startActivity(intent);
    }
}
