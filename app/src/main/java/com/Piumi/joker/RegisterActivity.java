package com.Piumi.joker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText uName;
    EditText uEmail;
    EditText uPassword;
    EditText uPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sharedpreferences = getSharedPreferences( "User", this.MODE_PRIVATE);

        uName = (EditText)findViewById(R.id.regName);
        uEmail = (EditText)findViewById(R.id.regEmail);
        uPassword = (EditText)findViewById(R.id.regPassword);
        uPhoneNo = (EditText)findViewById(R.id.regPhoneNo);

    }

    public void onSubmit(View view){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("uName", uName.getText().toString());
        editor.putString("uEmail", uEmail.getText().toString());
        editor.putString("uPassword", uPassword.getText().toString());
        editor.putString("uPhoneNo", uPhoneNo.getText().toString());
        editor.commit();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.finish();
        startActivity(intent);
    }
}
