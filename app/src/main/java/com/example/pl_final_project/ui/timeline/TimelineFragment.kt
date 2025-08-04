package com.example.pl_final_project.ui.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
// import com.example.pl_final_project.R // If accessing resources like drawables
import com.example.pl_final_project.databinding.FragmentTimelineBinding
import com.example.pl_final_project.model.Item
import com.example.pl_final_project.ui.viewmodels.SharedViewModel

class TimelineFragment : Fragment() {

    private var _binding: FragmentTimelineBinding? = null
    private val binding get() = _binding!!

    //private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimelineBinding.inflate(inflater, container, false)
        // TODO: Any initial setup of views that don't depend on data yet
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        setupUIListeners() // For any buttons/actions directly in the fragment
    }

    private fun setupRecyclerView() {
        // TODO: Initialize timelineAdapter with click handlers
        //  Example: timelineAdapter = TimelineAdapter(
        //      onItemClicked = { item -> handleItemClick(item) },
        //      onItemCheckedChanged = { item, isChecked -> sharedViewModel.toggleItemCompletion(item.id) },
        //      onMoreOptionsClicked = { item -> showMoreOptionsForItem(item) }
        //  )
        // TODO: Set RecyclerView's adapter (binding.timelineRecyclerView.adapter = timelineAdapter)
        // TODO: Set RecyclerView's LayoutManager (e.g., LinearLayoutManager)
        // TODO: (Optional) Add ItemDecorations for spacing, dividers etc.
    }

    private fun observeViewModel() {
        // TODO: Observe sharedViewModel.items
        //  Inside the observer:
        //  1. (Optional) Filter or sort the received list of items specifically for the timeline view
        //  2. timelineAdapter.submitList(processedList)
        //  3. (Optional) Update visibility of emptyViewTimeline based on list size
    }

    private fun handleItemClick(item: Item) {
        // TODO: Logic for when an item in the timeline is clicked
        //  (e.g., navigate to a details screen, show an edit dialog)
    }

    private fun showMoreOptionsForItem(item: Item) {
        // TODO: Logic to show a menu or dialog with more options (edit, delete) for an item
        //  This might involve a PopupMenu or a custom dialog.
    }

    private fun setupUIListeners() {
        // TODO: Set up listeners for any other UI elements in fragment_timeline.xml
        //  (e.g., a sort button if you add one)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // TODO: Set binding.timelineRecyclerView.adapter = null (helps with cleanup)
        _binding = null
    }
}
