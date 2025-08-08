package com.example.pl_final_project;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pl_final_project.model.AbstractItem;
import com.example.pl_final_project.model.SimpleTask;

import java.util.ArrayList;

public class CreateSimpleItem extends AppCompatActivity {

    Button add, remove, back;
    ListView list;
    EditText itemText;
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_new_item_simple);

        add = findViewById(R.id.add_button_simple);
        remove = findViewById(R.id.remove_button_simple);
        back = findViewById(R.id.back_to_main_simple);
        itemText = findViewById(R.id.item_name);
        list = findViewById(R.id.new_item_list_simple);

        adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_multiple_choice, itemNames);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // Add this line

        //setting up array to display items
        for(SimpleTask task: SimpleTask.getSimpleTasks()){
            itemNames.add(task.getText());
        }

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItemPositions = list.getCheckedItemPositions();

                ArrayList<String> itemsToRemoveFromAdapter = new ArrayList<>();
                ArrayList<SimpleTask> tasksToRemoveFromStatic = new ArrayList<>();

                for (int i = checkedItemPositions.size() - 1; i>=0; i--){
                    if(checkedItemPositions.valueAt(i)){
                        int position = checkedItemPositions.keyAt(i);

                        if(position<itemNames.size() && position < SimpleTask.getSimpleTasks().size()){
                            itemsToRemoveFromAdapter.add(itemNames.get(position));
                            tasksToRemoveFromStatic.add(SimpleTask.getSimpleTasks().get(position));
                        }
                        else{
                            Toast.makeText(CreateSimpleItem.this, "Out of Bounds Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                if(itemsToRemoveFromAdapter.isEmpty()){
                    Toast.makeText(CreateSimpleItem.this, "No items selected to remove", Toast.LENGTH_SHORT).show();
                    return;
                }

                //removing items
                for(String itemName : itemsToRemoveFromAdapter){
                    adapter.remove(itemName); //automatically updates adapter
                }
                for(SimpleTask task : tasksToRemoveFromStatic){
                    SimpleTask.removeTask(task);
                }

                list.clearChoices();
            }

        });

        if(add != null && itemText!= null) {
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        SimpleTask newTask = new SimpleTask(itemText.getText().toString());
                        itemNames.add(itemText.getText().toString());
                        adapter.notifyDataSetChanged();
                        itemText.setText("");
                }
            });
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(CreateSimpleItem.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}