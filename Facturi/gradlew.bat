package com.example.evidentafacturideachitat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mTextUsername=(EditText)findViewById(R.id.ET_username);
        mTextPassword=(EditText)findViewById(R.id.ET_password);
        mButtonLogin=(Button) findViewById(R.id.BTN_login);
        mTextViewRegister=(TextView) findViewById(R.id.TW_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent =new Intent(LogIn.this,Register.class);
                startActivity(registerIntent);
            }
        });
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          