package com.example.pl_final_project.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
public class SimpleTask extends AbstractItem{

    private static ArrayList<SimpleTask> simpleItems = new ArrayList<>();
    public SimpleTask(String id,String text,boolean isCompleted, Date creationDate, String kanbanColumn) {
        super(id,text,isCompleted, creationDate, kanbanColumn);

        simpleItems.add(this);
    }

    public SimpleTask(String text) {
        super(text,"ToDo");
    }
    public void removeTaskFromArray(String name){
        for(int i = simpleItems.size(); i>=0; i--){
            if(simpleItems.get(i).getText().equals(name)){
                simpleItems.remove(i);
                removeUniversalItem(name);
            }
        }
    }
    public static void removeTaskFromArray(int index){
        simpleItems.remove(index);
        universalItems.remove(simpleItems.get(index).getText());
    }

    public static ArrayList<SimpleTask> getSimpleTasks() {
        return simpleItems;
    }

    @NonNull
    @Override
    public String toString() {
        return "SimpleTask{" +
                "id=" + getId() + '\'' +
                ", text=" + getText() + '\'' +
                ", isCompleted=" + isCompleted() + '\'' +
                ", kanbanColumn=" + getKanbanColumn() + '\'' +
                '}';
    }
}
