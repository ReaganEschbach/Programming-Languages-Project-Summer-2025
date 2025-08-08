package com.example.pl_final_project;

import static com.example.pl_final_project.model.AbstractItem.getDatedItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
// import android.widget.ArrayAdapter; // No longer needed for the generic one
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pl_final_project.model.TaskWithDeadline;
// MainActivity is already imported implicitly if it's in the same effective package for navigation
// import com.example.pl_final_project.MainActivity; 

import java.util.ArrayList;
import java.util.Comparator;

public class Timeline extends AppCompatActivity {

    private static ArrayList<TaskWithDeadline> timelineData; // Renamed to avoid confusion with the activity class name
    private TimelineAdapter adapter;
    private ListView timelineList;
    private Button timelineCreateNew, timelineToDo, timelineTimelineButton; // Renamed button to avoid confusion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.timeline_view);

        // Initialize views
        timelineCreateNew = findViewById(R.id.timeline_create_new);
        timelineToDo = findViewById(R.id.timeline_to_do);
        timelineTimelineButton = findViewById(R.id.timeline_timeline); // Corrected variable name
        timelineList = findViewById(R.id.timeline_list);

        // Prepare data for the list
        if (timelineData == null) { // Ensure static list is initialized only once or as needed
            timelineData = new ArrayList<>();
        } else {
            timelineData.clear(); // Clear if repopulating
        }
        // Assuming getDatedItems() is the correct method to get tasks with deadlines
        timelineData.addAll(getDatedItems()); 
        timelineData.sort(Comparator.comparing(TaskWithDeadline::getDeadline));

        // Initialize and set custom adapter
        adapter = new TimelineAdapter(this, timelineData);
        timelineList.setAdapter(adapter);
        // adapter.notifyDataSetChanged(); // Adapter will be notified when data is ready by its nature

        // Set OnClickListeners
        timelineCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Timeline.this, CreateTimedItem.class);
                startActivity(intent);
            }
        });

        timelineToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Timeline.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: finish Timeline activity
            }
        });

        timelineTimelineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder action: Refresh the list
                if (timelineData != null) {
                    timelineData.clear();
                    timelineData.addAll(getDatedItems());
                    timelineData.sort(Comparator.comparing(TaskWithDeadline::getDeadline));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Timeline.this, "Timeline refreshed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when returning to this activity
        if (timelineData != null && adapter != null) {
            timelineData.clear();
            timelineData.addAll(getDatedItems());
            timelineData.sort(Comparator.comparing(TaskWithDeadline::getDeadline));
            adapter.notifyDataSetChanged();
        }
    }
}
