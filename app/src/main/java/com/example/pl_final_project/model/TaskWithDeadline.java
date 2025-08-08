package com.example.pl_final_project.model;
import androidx.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
public class TaskWithDeadline extends AbstractItem {
    private final Date deadline;
    private static ArrayList<TaskWithDeadline> datedItems = new ArrayList<>();
    public TaskWithDeadline(String id, String text, boolean isCompleted, Date creationDate, String kanbanColumn, Date deadline) {
        super(id,text,isCompleted,creationDate, kanbanColumn);
        this.deadline=deadline;

        datedItems.add(this);
    }

    public static ArrayList<TaskWithDeadline> getDatedItems() {
        return datedItems;
    }
    public int getDatedItemsSize(){
        int size = datedItems.size();
        return size;
    }

    //overloaded
    public TaskWithDeadline(String text,Date deadline) {
        super(text,"ToDo");
        this.deadline=deadline;
        datedItems.add(this);
    }

    public Date getDeadline() {
        return deadline;
    }

    public boolean isOverdue() {
        return !isCompleted() && deadline !=null && deadline.before(new Date());
    }

    @NonNull
    @Override
    public String toString() {
        return "TaskWithDeadline{" +
                "id=" + getId() + '\'' +
                ", text=" + getText() + '\'' +
                ", isCompleted=" + isCompleted() + '\'' +
                ", creationDate=" + getId() + '\'' +
                ", kanbanColumn=" + getKanbanColumn() + '\'' +
                ", deadline=" + getDeadline() + '\'' +
                '}';
    }


}