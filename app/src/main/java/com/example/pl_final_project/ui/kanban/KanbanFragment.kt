package com.example.pl_final_project.ui.kanban

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
// import androidx.recyclerview.widget.ItemTouchHelper
import com.example.pl_final_project.databinding.FragmentKanbanBinding
import com.example.pl_final_project.model.Item
import com.example.pl_final_project.ui.viewmodels.SharedViewModel

class KanbanFragment : Fragment() {

    private var _binding: FragmentKanbanBinding? = null
    private val binding get() = _binding!!

    //private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var todoAdapter: KanbanColumnAdapter
    private lateinit var inProgressAdapter: KanbanColumnAdapter
    private lateinit var doneAdapter: KanbanColumnAdapter

    // Define column names (match with Item.kanbanColumn values)
    private val COL_TODO = "ToDo"
    private val COL_IN_PROGRESS = "InProgress"
    private val COL_DONE = "Done"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKanbanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        setupRecyclerViews()
        observeViewModel()
        // setupDragAndDrop() // Call this if implementing drag and drop
    }

    private fun setupAdapters() {
        // TODO: Initialize todoAdapter, inProgressAdapter, doneAdapter
        //  Pass lambdas for item clicks, checkbox changes, and potentially drag start
        //  Example for one adapter:
        //  todoAdapter = KanbanColumnAdapter(
        //      onItemClicked = { item -> handleItemClick(item) },
        //      onItemCheckedChanged = { item, isChecked -> sharedViewModel.toggleItemCompletion(item.id) }
        //      // onDragStarted = { viewHolder -> itemTouchHelper.startDrag(viewHolder) } // For drag & drop
        //  )
    }

    private fun setupRecyclerViews() {
        // TODO: For binding.recyclerViewToDo:
        //  Set adapter (todoAdapter)
        //  Set layoutManager (LinearLayoutManager)
        // TODO: For binding.recyclerViewInProgress:
        //  Set adapter (inProgressAdapter)
        //  Set layoutManager (LinearLayoutManager)
        // TODO: For binding.recyclerViewDone:
        //  Set adapter (doneAdapter)
        //  Set layoutManager (LinearLayoutManager)
    }

    private fun observeViewModel() {
        // TODO: Observe sharedViewModel.items
        //  Inside the observer:
        //  1. Filter the received 'allItems' list into three separate lists:
        //     val todoItems = allItems.filter { it.kanbanColumn == COL_TODO }
        //     val inProgressItems = allItems.filter { it.kanbanColumn == COL_IN_PROGRESS }
        //     val doneItems = allItems.filter { it.kanbanColumn == COL_DONE }
        //  2. Submit these filtered lists to their respective adapters:
        //     todoAdapter.submitList(todoItems)
        //     inProgressAdapter.submitList(inProgressItems)
        //     doneAdapter.submitList(doneItems)
        //  3. (Optional) Update visibility of empty state views for each column if they are empty
    }

    private fun handleItemClick(item: Item) {
        // TODO: Logic for when an item (card) in a Kanban column is clicked
        //  (e.g., show details, allow editing)
    }

    // private fun setupDragAndDrop() {
    //    // TODO: Implement ItemTouchHelper for drag and drop between columns
    //    // This is more complex and involves:
    //    // 1. Creating an ItemTouchHelper.Callback
    //    // 2. Overriding onMove, getMovementFlags, etc.
    //    // 3. In onMove or onClearView (after a drop), determine the target column
    //    // 4. Call sharedViewModel.updateItemKanbanColumn(draggedItem.id, newColumnName)
    //    // 5. Attach the ItemTouchHelper to EACH RecyclerView (or a coordinating parent)
    // }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Set adapters of all RecyclerViews to null
        _binding = null
    }
}
