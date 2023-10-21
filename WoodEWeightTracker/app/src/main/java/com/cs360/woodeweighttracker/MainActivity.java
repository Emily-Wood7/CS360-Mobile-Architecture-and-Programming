package com.cs360.woodeweighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userText = null;
    private EditText passwordText = null;
    private Button buttonSubmit = null;
    private Button buttonNewUser = null;
    DBUserHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userText = findViewById(R.id.userText);
        passwordText = findViewById(R.id.passwordText);

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonNewUser = findViewById(R.id.buttonNewUser);
        buttonSubmit.setEnabled(false);
        buttonNewUser.setEnabled(false);
        DB = new DBUserHelper(this);
        userText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                buttonSubmit.setEnabled(true);
                buttonNewUser.setEnabled(true);
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userText.getText().toString();
                String pass = passwordText.getText().toString();

                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUser = DB.checkUsername(user);
                    Boolean checkPassword = DB.checkUsernamePassword(user, pass);
                    if (checkUser == false) {
                            Toast.makeText(MainActivity.this, "User Does Not Exist. Create New User.", Toast.LENGTH_SHORT).show();
                        }
                    else {
                        if (checkPassword == false) {
                            Toast.makeText(MainActivity.this, "Incorrect Password.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(MainActivity.this, TrackActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
        buttonNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userText.getText().toString();
                String pass = passwordText.getText().toString();

                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkUser = DB.checkUsername(user);
                    if (checkUser == false) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert == true) {
                            Toast.makeText(MainActivity.this, "New User Created Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                    Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}