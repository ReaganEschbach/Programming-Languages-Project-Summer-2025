package com.example.pl_final_project;

import static com.example.pl_final_project.model.AbstractItem.universalItems;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable; // Added for onActivityResult
import androidx.appcompat.app.AppCompatActivity;

import com.example.pl_final_project.model.AbstractItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button createNewItemNav, timelineNav, createSimpleItemNav, createTimedItemNav, toDoNav;
    ListView toDo, inProgress, complete;
    Dialog createDialog, startTaskDialog, finishTaskDialog;
    View kanbanBoardView, timelineViewContent;

    // Added for Timed Items
    private ArrayList<TimedItem> timedItemsList;
    private ArrayAdapter<TimedItem> timedItemsAdapter;
    private ListView timelineListView;
    private static final int ADD_TIMED_ITEM_REQUEST = 1;

    // Inner class for TimedItem
    public static class TimedItem {
        String text;
        String date;

        public TimedItem(String text, String date) {
            this.text = text;
            this.date = date;
        }

        public String getText() { return text; }
        public String getDate() { return date; }

        @Override
        public String toString() {
            return date + ": " + text; // Simple representation for ArrayAdapter
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views for switching content
        kanbanBoardView = findViewById(R.id.kanban_board_view);
        timelineViewContent = findViewById(R.id.timeline_view_content);
        toDoNav = findViewById(R.id.kanban_to_do);
        timelineNav = findViewById(R.id.kanban_timeline);

        // Initialize Timed Items list
        timedItemsList = new ArrayList<>();

        //popup for when you want to create new item
        createDialog = new Dialog(MainActivity.this);
        createDialog.setContentView(R.layout.item_choice_pop_up);
        createDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        createDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_rectangle_background));
        createDialog.setCancelable(false);
            //popup views
        createSimpleItemNav = createDialog.findViewById(R.id.create_simple_item_pop_up);
        createTimedItemNav = createDialog.findViewById(R.id.create_timeline_item_pop_up);
            //popup buttons
        createTimedItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTimedItem.class);
                startActivityForResult(intent, ADD_TIMED_ITEM_REQUEST); // Changed to startActivityForResult
                createDialog.dismiss();
            }
        });
        createSimpleItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateSimpleItem.class);
                // If CreateSimpleItem also needs to return data, use startActivityForResult here too
                startActivity(intent);
                createDialog.dismiss();
            }
        });

        //initiate create new item popup to new activity
        createNewItemNav = findViewById(R.id.create_new);
        createNewItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.show();
            }
        });

        // Switch to Timeline View
        if(timelineNav != null) {
            timelineNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (timelineViewContent != null && kanbanBoardView != null) {
                        timelineViewContent.setVisibility(View.VISIBLE);
                        kanbanBoardView.setVisibility(View.GONE);
                        // Initialize timeline ListView and Adapter here, if not already
                        setupTimelineListView(); 
                    } else {
                        Log.e("MainActivity", "Timeline or Kanban view is null and cannot be switched.");
                    }
                }
            });
        } else{
            Log.e("MainActivity", "timelineNav button not found in layout!");
        }

        // Switch back to To-Do/Kanban View
        if(toDoNav != null) {
            toDoNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (kanbanBoardView != null && timelineViewContent != null) {
                        kanbanBoardView.setVisibility(View.VISIBLE);
                        timelineViewContent.setVisibility(View.GONE);
                    } else {
                        Log.e("MainActivity", "Kanban or Timeline view is null and cannot be switched.");
                    }
                }
            });
        } else{
            Log.e("MainActivity", "toDoNav button (R.id.kanban_to_do) not found in layout!");
        }

        //kanban
        toDo = findViewById(R.id.to_do);
        inProgress = findViewById(R.id.in_progress);
        complete = findViewById(R.id.completed);

        //strings
        ArrayList<String> col1String = new ArrayList<>();
        ArrayList<String> col2String = new ArrayList<>();
        ArrayList<String> col3String = new ArrayList<>();


        for(AbstractItem task : universalItems){
            if("ToDo".equals(task.getKanbanColumn())){
                col1String.add(task.getText());
            }
            else if("InProgress".equals(task.getKanbanColumn())){
                col2String.add(task.getText());
            }
            else if("Done".equals(task.getKanbanColumn())){
                col3String.add(task.getText());
            }
        }

            //adding data to the columns
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, col1String);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, col2String);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, col3String);

        toDo.setAdapter(adapter1);
        inProgress.setAdapter(adapter2);
        complete.setAdapter(adapter3);

            //pop ups
        startTaskDialog = new Dialog(MainActivity.this);
        startTaskDialog.setContentView(R.layout.task_click_pop_up);
        startTaskDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        startTaskDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_rectangle_background));
        startTaskDialog.setCancelable(false);

        finishTaskDialog = new Dialog(MainActivity.this);
        finishTaskDialog.setContentView(R.layout.move_to_completed_pop_up);
        finishTaskDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        finishTaskDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_rectangle_background));
        finishTaskDialog.setCancelable(false);

            //pop up buttons
        Button toDoYes = startTaskDialog.findViewById(R.id.to_do_yes);
        Button toDoNo = startTaskDialog.findViewById(R.id.to_do_no);
        Button completedYes = finishTaskDialog.findViewById(R.id.completed_yes);
        Button completedNo = finishTaskDialog.findViewById(R.id.completed_no);

        //putting tasks in different columns

        toDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //started task popup
                startTaskDialog.show();
                toDoYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position >= 0 && position < col1String.size()){
                            String itemToMove = col1String.get(position);
                            AbstractItem taskObject = AbstractItem.getItemByName(itemToMove);

                            //moving item
                            if(taskObject != null){
                             taskObject.setKanbanColumn("InProgress");
                            }
                            else{
                                Log.w("MainActivity", "Could not find task object for: " + itemToMove);

                            }

                            //removing item from list and adapter
                            col1String.remove(position);
                            adapter1.notifyDataSetChanged();

                            //adding item to new list
                            col2String.add(itemToMove);
                            adapter2.notifyDataSetChanged();
                        }
                        startTaskDialog.dismiss(); // Dismiss dialog after action
                    }
                });
                toDoNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startTaskDialog.dismiss();
                    }
                });


            }
        });
        inProgress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //completed task popup
                finishTaskDialog.show();
                completedYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position >= 0 && position < col2String.size()){
                            String itemToMove = col2String.get(position);
                            AbstractItem taskObject = AbstractItem.getItemByName(itemToMove);

                            //moving item
                            if(taskObject != null){
                                taskObject.setKanbanColumn("Done"); // Corrected from "InProgress"
                            }
                            else{
                                Log.w("MainActivity", "Could not find task object for: " + itemToMove);

                            }

                            //removing item from list and adapter
                            col2String.remove(position);
                            adapter2.notifyDataSetChanged();

                            //adding item to new list
                            col3String.add(itemToMove);
                            adapter3.notifyDataSetChanged();
                        }
                        finishTaskDialog.dismiss(); // Dismiss dialog after action
                    }
                });
                completedNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishTaskDialog.dismiss();
                    }
                });
            }
        });

        complete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String completedItem = col3String.get(position);
                AbstractItem itemToMove = AbstractItem.getItemByName(completedItem);
                if (itemToMove != null) { // Added null check for safety
                    itemToMove.setKanbanColumn("Done");
                }

                col3String.remove(position);
                adapter3.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, completedItem + " marked as complete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Add this new method to setup timeline list view
    private void setupTimelineListView() {
        if (timelineListView == null) { // Initialize if not already done
            timelineListView = findViewById(R.id.timeline_list); // Make sure this ID is in timeline_view.xml
        }

        if (timelineListView != null && timedItemsAdapter == null) { // Setup adapter if not already done
            timedItemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, timedItemsList);
            timelineListView.setAdapter(timedItemsAdapter);
        } else if (timelineListView == null) {
            Log.e("MainActivity", "timeline_list_view not found in the current layout. Ensure it's in timeline_view.xml and timelineViewContent is visible.");
        }
        // If adapter already exists, just notify it if the list changed (handled in onActivityResult)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TIMED_ITEM_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                String itemText = data.getStringExtra("NEW_ITEM_TEXT");
                String itemDate = data.getStringExtra("NEW_ITEM_DATE");

                if (itemText != null && itemDate != null) {
                    TimedItem newItem = new TimedItem(itemText, itemDate);
                    timedItemsList.add(newItem);
                    // TODO: Optionally sort timedItemsList by date here
                    if (timedItemsAdapter != null) {
                        timedItemsAdapter.notifyDataSetChanged();
                    }
                    Toast.makeText(this, "Timed item: " + itemText + " added!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
