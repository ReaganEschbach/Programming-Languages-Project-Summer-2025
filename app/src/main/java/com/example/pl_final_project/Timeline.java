package com.example.pl_final_project;

import static com.example.pl_final_project.model.TaskWithDeadline.getDatedItems;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pl_final_project.model.TaskWithDeadline;

import java.util.ArrayList;
import java.util.Comparator;

public class Timeline extends AppCompatActivity{

    private static ArrayList<TaskWithDeadline> timeline;
    Button createNewItemNav, todoNav, createSimpleItemNav, createTimedItemNav;
    ListView actualTimeline;
    Dialog createDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.timeline_view);

        //copying static array and sorting
        timeline.addAll(getDatedItems());
        timeline.sort(Comparator.comparing(TaskWithDeadline::getDeadline));

        //popup for when you want to create new item
        createDialog = new Dialog(Timeline.this);
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
                Intent intent = new Intent(Timeline.this, CreateSimpleItem.class);
                startActivity(intent);
                createDialog.dismiss();
            }
        });
        createSimpleItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Timeline.this, CreateSimpleItem.class);
                startActivity(intent);
                createDialog.dismiss();
            }
        });

        //initiate create new item popup to new activity
        createNewItemNav.findViewById(R.id.create_new);
        createNewItemNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.show();
            }
        });


    }



}
