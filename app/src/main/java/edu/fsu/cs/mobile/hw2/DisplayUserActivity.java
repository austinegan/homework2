package edu.fsu.cs.mobile.hw2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        Intent intent = getIntent();
        String myEmail = intent.getStringExtra("email");
        final String myPassword = intent.getStringExtra("password");
        String myName = intent.getStringExtra("name");
        String myClass = intent.getStringExtra("class");
        String myRole = intent.getStringExtra("role");

        TextView emailView = findViewById(R.id.emailView);
        final TextView passwordView = findViewById(R.id.passwordView);
        TextView nameView = findViewById(R.id.nameView);
        TextView classView = findViewById(R.id.classView);
        TextView roleView = findViewById(R.id.roleView);

        emailView.setText(myEmail);
        nameView.setText(myName);
        classView.setText(myClass);
        roleView.setText(myRole);

        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordView.setText(myPassword);
            }
        });
    }
}
