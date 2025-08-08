package com.example.pl_final_project.model;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class AbstractItem {
    private final String id;
    private String text;
    private boolean isCompleted;
    private final Date creationDate;
    private  String kanbanColumn;

    //list to store all items (both SimpleTask and TaskWithDeadline)
    public static ArrayList<AbstractItem> universalItems = new ArrayList<>();

    //Constructor for full initialization
    public AbstractItem(String id,String text, boolean isCompleted, Date creationDate, String kanbanColumn) {
        this.id= (id==null) ? UUID.randomUUID().toString() : id;
        this.text=text;
        this.isCompleted=isCompleted;
        this.creationDate = (creationDate==null) ? new Date(): creationDate;
        this.kanbanColumn=(kanbanColumn==null || kanbanColumn.isEmpty()) ? "ToDo" : kanbanColumn;

        universalItems.add(this);
    }

    //Constructor for new item creation
    public AbstractItem(String text, String kanbanColumn) {
        this.id= UUID.randomUUID().toString();
        this.text=text;
        this.isCompleted=false;
        this.creationDate = new Date();
        this.kanbanColumn=(kanbanColumn==null || kanbanColumn.isEmpty()) ? "ToDo" : kanbanColumn;

        universalItems.add(this);
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text=text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getKanbanColumn() {
        return kanbanColumn;
    }

    public void setKanbanColumn(String kanbanColumn) {
        this.kanbanColumn=kanbanColumn;
    }

    public void toggleCompleteStatus() {
        this.isCompleted=!this.isCompleted;
    }

    public AbstractItem getItem(String taskName){
        for(AbstractItem task : universalItems){
            if(task.getText().equals(taskName)){
                return task;
            }
        }
        return null;
    }

    public static void removeUniversalItem(AbstractItem item){
        universalItems.remove(item);
    }

    public static AbstractItem getItemByName(String name){
        for(AbstractItem task : universalItems){
            if(task.getText().equals(name)){
                return task;
            }
        }
        return null;
    }

    public static ArrayList<TaskWithDeadline> getDatedItems() {
        ArrayList<TaskWithDeadline> datedItems = new ArrayList<>();
        for (AbstractItem item : universalItems) {
            if (item instanceof TaskWithDeadline) {
                datedItems.add((TaskWithDeadline) item);
            }
        }
        return datedItems;
    }
}
