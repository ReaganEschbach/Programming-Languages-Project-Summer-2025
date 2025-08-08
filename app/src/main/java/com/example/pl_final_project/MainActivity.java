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
import androidx.appcompat.app.AppCompatActivity;

import com.example.pl_final_project.model.AbstractItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button createNewItemNav, timelineNav, createSimpleItemNav, createTimedItemNav;
    ListView toDo, inProgress, complete;
    Dialog createDialog, startTaskDialog, finishTaskDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        timelineNav = findViewById(R.id.kanban_timeline);


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
                finish();
                Intent intent = new Intent(MainActivity.this, CreateTimedItem.class);
                startActivity(intent);
                createDialog.dismiss();
            }
        });
        createSimpleItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, CreateSimpleItem.class);
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

        //go to timeline
        if(timelineNav != null) {
            timelineNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(MainActivity.this, Timeline.class);
                    startActivity(intent);
                }
            });
        } else{
            Log.e("MainActivity", "timelinenav button not found in layout!");
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

}