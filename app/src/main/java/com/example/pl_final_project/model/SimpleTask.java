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

   public static void removeTask(SimpleTask taskToRemove){
        if(taskToRemove==null){return;}
        else{
            simpleItems.remove(taskToRemove);
            AbstractItem.removeUniversalItem(taskToRemove);
        }
   }
   public static void removeByIndex(int index){
        if(index >= 0 && index < simpleItems.size()){
            SimpleTask taskToRemove = simpleItems.get(index);
            simpleItems.remove(index);
            AbstractItem.removeUniversalItem(taskToRemove);
        }
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
