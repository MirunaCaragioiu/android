package com.example.facturi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextUsername2;
    EditText mTextEmail;
    EditText mTextPassword;
    Button mButtonRegister;
    TextView mTextViewLogIn;

    private UserDatabase  database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle(getString(R.string.TitluInregistrare));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));


        mTextUsername = (EditText) findViewById(R.id.ET_username);
        mTextUsername2 = (EditText) findViewById(R.id.ET_username2);
        mTextEmail = (EditText) findViewById(R.id.ET_emailR);
        mTextPassword = (EditText) findViewById(R.id.ET_password);
        mButtonRegister = (Button) findViewById(R.id.BTN_register);
        mTextViewLogIn = (TextView) findViewById(R.id.TW_login);
        mTextViewLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(Register.this, LogIn.class);
                startActivity(it2);
            }
        });

        database = Room.databaseBuilder(this, UserDatabase.class, "usersDB").allowMainThreadQueries().build();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty()) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                User user = new User(mTextUsername.getText().toString(), mTextUsername2.getText().toString(),
                                        mTextEmail.getText().toString(), mTextPassword.getText().toString());
                                database.getUserDAO().insert(user);
                                startActivity(new Intent(Register.this, LogIn.class));
                            }
                        }, 1000);
                } else {
                    Toast.makeText(Register.this, R.string.trebuieCompletatTot, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(mTextEmail.getText().toString()) ||
                TextUtils.isEmpty(mTextPassword.getText().toString()) ||
                TextUtils.isEmpty(mTextUsername.getText().toString()) ||
                TextUtils.isEmpty(mTextUsername2.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }
}
