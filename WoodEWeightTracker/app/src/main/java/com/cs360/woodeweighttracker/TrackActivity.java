package com.cs360.woodeweighttracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrackActivity extends AppCompatActivity {
    private Button newGoalButton, newWeightButton;
    private String weightText = null;
    private TextView goalWeightDisplay = null;
    DBWeightHelper db;
    ArrayList<String> date, weight;
    Weight_RecyclerViewAdapter weightAdapter;
    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weight_menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        newGoalButton = findViewById(R.id.newGoalButton);
        goalWeightDisplay = findViewById(R.id.goalWeightDisplay);
        newWeightButton = findViewById(R.id.newWeightButton);
        db = new DBWeightHelper(this);
        recyclerView = findViewById(R.id.weightRecyclerView);

        newGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBox = new AlertDialog.Builder(TrackActivity.this);
                dialogBox.setTitle("Enter Goal Weight");

                final EditText weightInput = new EditText(TrackActivity.this);
                weightInput.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                dialogBox.setView(weightInput);

                dialogBox.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        weightText = weightInput.getText().toString();
                        goalWeightDisplay.setText(weightText);
                    }
                });
                dialogBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogBox.show();
            }
        });

        newWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBox = new AlertDialog.Builder(TrackActivity.this);
                dialogBox.setTitle("Enter New Daily Weight");

                LinearLayout layout = new LinearLayout(TrackActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                TextView textView1 = new TextView(TrackActivity.this);
                textView1.setText("Enter Weight");
                EditText weight_text = new EditText(TrackActivity.this);
                TextView textView2 = new TextView(TrackActivity.this);
                textView2.setText("Enter Date");
                EditText date_text = new EditText(TrackActivity.this);

                layout.addView(textView1);
                layout.addView(weight_text);
                layout.addView(textView2);
                layout.addView(date_text);

                dialogBox.setView(layout);

                dialogBox.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String weight = weight_text.getText().toString();
                        String date = date_text.getText().toString();
                        Boolean savedata = db.insertWeightData(date, weight);
                        if (TextUtils.isEmpty(weight) || TextUtils.isEmpty(date)) {
                            Toast.makeText(TrackActivity.this, "Please Enter all fields", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else {
                            if (savedata == true) {
                                Toast.makeText(TrackActivity.this, "Weight Saved Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(TrackActivity.this, TrackActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                });
                dialogBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogBox.show();
            }
        });

        date = new ArrayList<>();
        weight = new ArrayList<>();

        weightAdapter = new Weight_RecyclerViewAdapter(TrackActivity.this, date, weight);
        recyclerView.setAdapter(weightAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(TrackActivity.this));

        displayData();
    }

    private void displayData() {
        Cursor cursor = db.getData();
        if (cursor.getCount() == -1) {
            Toast.makeText(this, "No Weights", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext()) {
                date.add(cursor.getString(0));
                weight.add(cursor.getString(1));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notifications) {
            Intent intent = new Intent(TrackActivity.this, NotificationsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.logout) {
            Intent intent = new Intent(TrackActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.track_weight) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}