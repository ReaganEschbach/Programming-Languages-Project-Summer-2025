package com.example.pl_final_project.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
// import android.view.View // If needed for visibility changes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pl_final_project.databinding.TimelineItemViewBinding
import com.example.pl_final_project.model.Item
import com.example.pl_final_project.model.TaskWithDeadline // If checking instance type

class TimelineAdapter(
    private val onItemClicked: (Item) -> Unit,
    private val onItemCheckedChanged: (Item, Boolean) -> Unit,
    private val onMoreOptionsClicked: (Item) -> Unit
) : ListAdapter<Item, TimelineAdapter.TimelineViewHolder>(TimelineDiffCallback()) {

    inner class TimelineViewHolder(private val binding: TimelineItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // TODO: Set up click listener for the whole item view (binding.root)
            //  Call onItemClicked(getItem(adapterPosition))
            // TODO: Set up checked change listener for binding.itemCompletedCheckbox
            //  Call onItemCheckedChanged(getItem(adapterPosition), isChecked)
            //  Be careful with programmatic changes vs. user changes.
            // TODO: Set up click listener for binding.itemMoreOptions
            //  Call onMoreOptionsClicked(getItem(adapterPosition))
        }

        fun bind(item: Item) {
            // TODO: Bind item.text to binding.itemText
            // TODO: Bind item.isCompleted to binding.itemCompletedCheckbox.isChecked
            // TODO: If item is TaskWithDeadline, format and display item.deadline in binding.itemDeadlineText
            //  and manage its visibility. Otherwise, hide binding.itemDeadlineText.
            // TODO: Set any other visual properties (e.g., strike-through for completed items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        // TODO: Inflate TimelineItemViewBinding
        // TODO: Return a new TimelineViewHolder instance with the binding
        return TimelineViewHolder(TimelineItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)) // Replace with actual logic
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
        // TODO: Get the item at the current position using getItem(position)
        // TODO: Call holder.bind(item)
    }
}

class TimelineDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        // TODO: Return oldItem.id == newItem.id
        return false // Replace
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        // TODO: Return oldItem == newItem (relies on data class equals or custom implementation)
        return false // Replace
    }
}
