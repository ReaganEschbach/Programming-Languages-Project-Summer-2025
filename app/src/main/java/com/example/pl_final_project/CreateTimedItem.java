package com.example.pl_final_project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent; // Added import
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class CreateTimedItem extends AppCompatActivity {

    private Button backToMain, remove, add, dateButton;
    private DatePickerDialog datePickerDialog;
    private EditText itemText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_item);
        initDatePicker();
        dateButton = findViewById(R.id.date_picker);
        dateButton.setText(getTodaysDate());

        itemText = findViewById(R.id.item_name_time); // Initialized itemText
        add = findViewById(R.id.add_button_time);     // Initialized add button

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        backToMain = findViewById(R.id.back_to_main_create_time);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close CreateTimedItem and go back to MainActivity
            }
        });

        if (add != null && itemText != null && dateButton != null) {
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemName = itemText.getText().toString();
                    String itemDate = dateButton.getText().toString();

                    if (!itemName.isEmpty()) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("NEW_ITEM_TEXT", itemName);
                        resultIntent.putExtra("NEW_ITEM_DATE", itemDate);
                        setResult(AppCompatActivity.RESULT_OK, resultIntent);
                        finish();
                    } else {
                        itemText.setError("Item name cannot be empty");
                    }
                }
            });
        }

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, dateSetListener, year, month, day);
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month==1)
            return "JAN";
        else if(month==2)
            return "FEB";
        else if(month==3)
            return "MAR";
        else if(month==4)
            return "APR";
        else if(month==5)
            return "MAY";
        else if(month==6)
            return "JUN";
        else if(month==7)
            return "JUL";
        else if(month==8)
            return "AUG";
        else if(month==9)
            return "SEP";
        else if(month==10)
            return "OCT";
        else if(month==11)
            return "NOV";
        else if(month==12)
            return "DEC";

        //default case
        return "MON";
    }

}
