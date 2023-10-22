package com.cs360.woodeweighttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditActivity extends AppCompatActivity {

    EditText weight, date;
    ImageButton edit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        weight = findViewById(R.id.editTextWeight);
        date = findViewById(R.id.editTextDate);
        edit = findViewById(R.id.imageButtonEdit);
        delete = findViewById(R.id.imageButtonDelete);


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