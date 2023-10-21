package com.cs360.woodeweighttracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackActivity extends AppCompatActivity {
    private Button newGoalButton, newWeightButton;
    private String weightText = null;
    private TextView goalWeightDisplay = null;

    ArrayList<WeightModel> weightModel = new ArrayList<>();

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

        RecyclerView recyclerView = findViewById(R.id.weightRecyclerView);

        setUpWeightModels();

        Weight_RecyclerViewAdapter adapter = new Weight_RecyclerViewAdapter(this, weightModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpWeightModels() {
        String[] weightNumber = getResources().getStringArray(R.array.weight_number);
        String[] weightDate = getResources().getStringArray(R.array.weight_date);

        for (int i = 0; i < weightNumber.length; i++) {
            weightModel.add(new WeightModel(weightNumber[i],
                    weightDate[i]));
        }
    }

    private void showEditTextDialog() {

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