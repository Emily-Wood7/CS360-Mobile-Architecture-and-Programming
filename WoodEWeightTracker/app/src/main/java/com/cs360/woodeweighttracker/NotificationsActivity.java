package com.cs360.woodeweighttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotificationsActivity extends AppCompatActivity {

    EditText editTextPhone;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSubmit = findViewById(R.id.buttonSubmitPhone);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(NotificationsActivity.this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weight_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notifications) {
            return true;
        }
        else if (id == R.id.logout) {
            Intent intent = new Intent(NotificationsActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.track_weight) {
            Intent intent = new Intent(NotificationsActivity.this, TrackActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}