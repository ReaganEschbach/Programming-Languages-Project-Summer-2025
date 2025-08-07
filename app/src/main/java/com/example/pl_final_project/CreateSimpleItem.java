package com.example.pl_final_project;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

        //setting up array to display items
        for(SimpleTask task: SimpleTask.getSimpleTasks()){
            itemNames.add(task.getText());
        }

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedItemPositions = list.getCheckedItemPositions();

                //if there's only one item checked and one in the list
                if(list.getCount()==1 && SimpleTask.getSimpleTasks().size()==1){
                    adapter.remove(itemNames.get(0));
                    SimpleTask.removeTaskFromArray(0);

                }
                for (int i = checkedItemPositions.size() - 1; i >= 0; i--) {
                    if (checkedItemPositions.valueAt(i)) {
                        int position = checkedItemPositions.keyAt(i);
                        itemNames.remove(i);
                        AbstractItem.removeUniversalItem(itemNames.get(i).toString()); //todo -> check and debug
                    }

                }
                adapter.notifyDataSetChanged();
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
