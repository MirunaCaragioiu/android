package com.example.facturi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {
    Button mSend;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Feedback");
    long maxId=0;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setTitle(getString(R.string.feedbackTitle1));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color)));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxId=(dataSnapshot.getChildrenCount());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mSend =(Button)findViewById(R.id.BTN_send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=((EditText)findViewById(R.id.ET_name)).getText().toString();
                String mesaj=((EditText)findViewById(R.id.ET_feedback)).getText().toString();
                Float rating=((RatingBar)findViewById(R.id.RB_stars)).getRating();
                FeedbackClass fc=new FeedbackClass(email,mesaj,rating);
                //feedbackDatabase.getFeedbackDAO().insert(fc);
                myRef.child(String.valueOf(maxId+1)).setValue(fc);
                Toast.makeText(Feedback.this, R.string.mesajTrimis,Toast.LENGTH_SHORT).show();
            }
        });


        TextView twHarta=(TextView)findViewById(R.id.TW_isFeed);
        twHarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(Feedback.this, IstoricFeedback.class);
                startActivity(it);
            }
        });


    }

}
