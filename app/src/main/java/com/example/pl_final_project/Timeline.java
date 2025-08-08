package com.example.pl_final_project;

import static com.example.pl_final_project.model.AbstractItem.getDatedItems; // Corrected static import if getDatedItems is in AbstractItem

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// Import TaskWithDeadline if it's in a different package (e.g., model)
import com.example.pl_final_project.model.TaskWithDeadline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date; // Make sure Date is imported if used in TaskWithDeadline or sorting

public class Timeline extends AppCompatActivity {

    private ArrayList<TaskWithDeadline> timelineData; // Renamed for clarity, was 'timeline'

    // Buttons from timeline_view.xml
    private Button mainCreateNewButton;
    private Button mainToDoButton;
    // Removed mainTimelineButton as its functionality is now in onResume and refresh

    private ListView actualTimelineListView; // Renamed for clarity, was 'actualTimeline'
    private Dialog createDialog;

    // Buttons inside the createDialog (from item_choice_pop_up.xml)
    private Button dialogCreateSimpleItemNav;
    private Button dialogCreateTimedItemNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.timeline_view);

        // Initialize UI elements from timeline_view.xml
        actualTimelineListView = findViewById(R.id.timeline_list); // Assuming this ID exists in timeline_view.xml
        mainCreateNewButton = findViewById(R.id.timeline_create_new); // Assuming this ID exists for the main "Create New" button
        mainToDoButton = findViewById(R.id.timeline_to_do);           // Assuming this ID exists for the "To-Do" button

        // Initialize data and adapter
        timelineData = new ArrayList<>();
        // getDatedItems() should return ArrayList<TaskWithDeadline>
        // Ensure TaskWithDeadline has a getDeadline() method that returns a Date or comparable type.
        timelineData.addAll(getDatedItems());
        timelineData.sort(Comparator.comparing(TaskWithDeadline::getDeadline, Comparator.nullsLast(Comparator.naturalOrder())));




        // --- Setup for the "Create New Item Choice" Dialog ---
        createDialog = new Dialog(Timeline.this);
        createDialog.setContentView(R.layout.item_choice_pop_up);
        if (createDialog.getWindow() != null) {
            createDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            createDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_rectangle_background));
        }
        createDialog.setCancelable(true); // Allow dismissing by clicking outside or back button

        // Find buttons inside the dialog
        dialogCreateSimpleItemNav = createDialog.findViewById(R.id.create_simple_item_pop_up);
        dialogCreateTimedItemNav = createDialog.findViewById(R.id.create_timeline_item_pop_up);

        // Set listeners for buttons INSIDE the dialog
        dialogCreateTimedItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decide if you want to finish Timeline activity:
                // finish();
                Intent intent = new Intent(Timeline.this, CreateTimedItem.class); // Corrected
                startActivity(intent);
                createDialog.dismiss();
            }
        });

        dialogCreateSimpleItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decide if you want to finish Timeline activity:
                // finish();
                Intent intent = new Intent(Timeline.this, CreateSimpleItem.class);
                startActivity(intent);
                createDialog.dismiss();
            }
        });

        // Set listener for the MAIN "Create New" button (on Timeline screen)
        mainCreateNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.show();
            }
        });

        // Set listener for the MAIN "To-Do" button (on Timeline screen)
        mainToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Timeline.this, MainActivity.class);
                startActivity(intent);
                // finish(); // Optional: if you want to close Timeline when going to To-Do
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data and update adapter
        if (timelineData != null) {
            timelineData.clear();
            timelineData.addAll(getDatedItems());
            // Ensure sorting is consistent if deadlines can be null
            timelineData.sort(Comparator.comparing(TaskWithDeadline::getDeadline, Comparator.nullsLast(Comparator.naturalOrder())));
        }
    }
}
