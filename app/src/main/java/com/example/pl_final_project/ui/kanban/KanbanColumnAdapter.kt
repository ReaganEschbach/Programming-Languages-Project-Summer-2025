package com.example.pl_final_project.ui.kanban

import android.view.LayoutInflater
import android.view.ViewGroup
// import android.view.MotionEvent // For drag handle
// import androidx.core.view.MotionEventCompat // For drag handle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pl_final_project.databinding.KanbanCardViewBinding
import com.example.pl_final_project.model.Item
// import com.example.pl_final_project.model.TaskWithDeadline // If checking instance type

class KanbanColumnAdapter(
    private val onItemClicked: (Item) -> Unit,
    private val onItemCheckedChanged: (Item, Boolean) -> Unit
    // private val onDragStarted: (RecyclerView.ViewHolder) -> Unit // For drag & drop
) : ListAdapter<Item, KanbanColumnAdapter.KanbanCardViewHolder>(KanbanDiffCallback()) {

    inner class KanbanCardViewHolder(private val binding: KanbanCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // TODO: Set up click listener for binding.root
            //  Call onItemClicked(getItem(adapterPosition))
            // TODO: Set up checked change listener for binding.itemCompletedCheckbox
            //  Call onItemCheckedChanged(getItem(adapterPosition), isChecked)

            // TODO: (For Drag & Drop with a handle)
            //  If you have a specific drag handle view within kanban_card_view.xml:
            //  binding.dragHandleView.setOnTouchListener { _, event ->
            //      if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
            //          onDragStarted(this) // 'this' refers to the ViewHolder
            //      }
            //      false
            //  }
        }

        fun bind(item: Item) {
            // TODO: Bind item.text to binding.itemText
            // TODO: Bind item.isCompleted to binding.itemCompletedCheckbox.isChecked
            // TODO: Bind any other relevant data (e.g., item.creationDate to binding.itemCreationDateText)
            // TODO: Set visual styles (e.g., strike-through for completed items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KanbanCardViewHolder {
        // TODO: Inflate KanbanCardViewBinding
        // TODO: Return a new KanbanCardViewHolder instance
        return KanbanCardViewHolder(KanbanCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)) // Replace
    }

    override fun onBindViewHolder(holder: KanbanCardViewHolder, position: Int) {
        // TODO: Get item at position
        // TODO: Call holder.bind(item)
    }
}

class KanbanDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        // TODO: Return oldItem.id == newItem.id
        return false // Replace
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        // TODO: Return oldItem == newItem
        return false // Replace
    }
}
