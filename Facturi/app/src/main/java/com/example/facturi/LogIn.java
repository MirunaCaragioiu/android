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


public class LogIn extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogIn;
    TextView mTextViewRegister;
    private UserDatabase database;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().setTitle(getString(R.string.intraInCont));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        database= Room.databaseBuilder(this,UserDatabase.class,"usersDB").allowMainThreadQueries().build();
        userDAO = database.getUserDAO();


        mTextUsername=(EditText)findViewById(R.id.ET_username);
        mTextPassword=(EditText)findViewById(R.id.ET_password);
        mButtonLogIn=(Button) findViewById(R.id.BTN_login);
        mTextViewRegister=(TextView) findViewById(R.id.TW_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(LogIn.this,Register.class);
                startActivity(it);
            }
        });

        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!emptyValidation()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user=userDAO.getUser(mTextUsername.getText().toString(),mTextPassword.getText().toString());
                            if(user!=null){
                                Intent i = new Intent(LogIn.this, MainActivity.class);
                                i.putExtra("User", user);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(LogIn.this, R.string.trebuieSaTeInregistrezi, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },1000);
                }else{
                    Toast.makeText(LogIn.this, R.string.completeazaToateCampurile, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(mTextUsername.getText().toString()) || TextUtils.isEmpty(mTextPassword.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
