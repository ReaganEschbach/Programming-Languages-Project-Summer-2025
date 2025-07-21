package com.example.pl_final_project.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.pl_final_project.model.Item
import com.example.pl_final_project.model.SimpleTask
import com.example.pl_final_project.model.TaskWithDeadline


import java.util.Date

class SharedViewModel : ViewModel() {

    //The internal mutable array for the items list
    private val _items = MutableLiveData<List<Item>>(emptyList())

    // the public or read only part for outside this class
    val items: LiveData<List<Item>> = _items

    //initializer to execute whenever there is an instance of this class.
    init {
        loadSampleItems()
    }

    //provides initial placeholder data for the application
    private fun loadSampleItems() {
        val sampleList = mutableListOf<Item>(
            SimpleTask(text = "Review project requirements", creationDate = Date(), kanbanColumn = "ToDo"),
            TaskWithDeadline(
                text = "Design database schema",
                creationDate = Date(),
                kanbanColumn = "InProgress",
                isCompleted = false,
                deadline = Date(System.currentTimeMillis() + 86400000 * 2) // Deadline in 2 days
            ),
            SimpleTask(text = "Implement basic UI", creationDate = Date(), kanbanColumn = "Done", isCompleted = true)
        )
        _items.value = sampleList
    }

//    public function for the add task button
    fun addItem(text: String, column: String = "ToDo", deadline: Date? = null) {
        val newItem: Item = if (deadline != null) {
            TaskWithDeadline(text = text, kanbanColumn = column, deadline = deadline)
        } else {
            SimpleTask(text = text, kanbanColumn = column)
        }
        val currentList = _items.value?.toMutableList() ?: mutableListOf()
        currentList.add(newItem)
        _items.value = currentList

    }

//    public function to edit an item
    fun updateItem(updatedItem: Item) {
        val currentList = _items.value?.toMutableList() ?: return
        val itemIndex = currentList.indexOfFirst { it.id == updatedItem.id }
        if (itemIndex != -1) {
            currentList[itemIndex] = updatedItem
            _items.value = currentList

        }
    }

    // toggle functionality to complete/uncomplete a task
    fun toggleItemCompletion(itemId: String) {
        val currentList = _items.value?.toMutableList() ?: return
        val itemIndex = currentList.indexOfFirst { it.id == itemId }
        if (itemIndex != -1) {
            val item = currentList[itemIndex]
            item.toggleCompleteStatus()

            _items.value = ArrayList(currentList)
        }
    }
    // function to move an item to a different Kanban column
    fun updateItemKanbanColumn(itemId: String, newColumn: String) {
        val currentList = _items.value?.toMutableList() ?: return
        val itemIndex = currentList.indexOfFirst { it.id == itemId }
        if (itemIndex != -1) {
            val item = currentList[itemIndex]
            item.kanbanColumn = newColumn
            _items.value = ArrayList(currentList)
        }
    }

    //function to delete an item from the task list
    fun deleteItem(itemId: String) {
        val currentList = _items.value?.toMutableList() ?: return
        currentList.removeAll { it.id == itemId }
        _items.value = currentList
    }
}
