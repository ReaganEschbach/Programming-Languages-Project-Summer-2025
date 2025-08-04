package com.example.pl_final_project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateNewItem extends AppCompatActivity {

    private ListView itemList;
    private Button addButton, removeButton;
    private EditText itemName;
    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_item);

        itemList = findViewById(R.id.new_item_list);
        addButton = findViewById(R.id.add_button);
        removeButton = findViewById(R.id.remove_button);
        itemName = findViewById(R.id.item_name);

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        itemList.setAdapter(itemsAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(v);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = itemList.getCheckedItemPositions();
                int count = itemList.getCount();
                //if there is only one item
                if(count==1 && items.size()==1){
                    itemsAdapter.remove(items.get(0));
                    items.clear();
                }
                //if there are multiple items in the list
                else {
                    for (int item = count-1; item >=0; item--) {
                        if (positionChecker.get(item)) {
                            itemsAdapter.remove(items.get(item)); //removes from string array
                        }
                    }
                }

                positionChecker.clear();
                itemsAdapter.notifyDataSetChanged();
            }
        });

        itemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if(items.size()>1){
                    items.remove(position);
                }
                else{
                    items.clear();
                }
                itemsAdapter.notifyDataSetChanged();

                return true;
            }
        });
    }

    private void addItem(View v) {
        EditText input = findViewById(R.id.item_name);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please enter text", Toast.LENGTH_SHORT).show();
        }
    }
}
