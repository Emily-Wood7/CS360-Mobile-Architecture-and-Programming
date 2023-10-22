package com.cs360.woodeweighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText weight;
    TextView date;
    ImageButton edit, delete;
    DBWeightHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        weight = findViewById(R.id.editTextWeight);
        date = findViewById(R.id.textViewDate);
        edit = findViewById(R.id.imageButtonEdit);
        delete = findViewById(R.id.imageButtonDelete);
        db = new DBWeightHelper(this);

        Intent intent = getIntent();

        weight.setText(intent.getStringExtra("weight"));
        date.setText(intent.getStringExtra("date"));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date_text = date.getText().toString();
                Boolean deleteData = db.deleteWeightData(date_text);
                if (deleteData == true) {
                    Toast.makeText(EditActivity.this, "Weight Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditActivity.this, "Weight Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String date_text = date.getText().toString();
                String weight_text = weight.getText().toString();
                Boolean updateData = db.updateWeightData(date_text, weight_text);
                if (updateData == true) {
                    Toast.makeText(EditActivity.this, "Weight Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditActivity.this, "Weight Not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weight_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notifications) {
            Intent intent = new Intent(EditActivity.this, NotificationsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.logout) {
            Intent intent = new Intent(EditActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.track_weight) {
            Intent intent = new Intent(EditActivity.this, TrackActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}