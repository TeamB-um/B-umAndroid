package team.bum.ui.main.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.R
import team.bum.databinding.FragmentCollectionBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.StatsDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.collection.adapter.CollectionAdapter
import team.bum.ui.main.collection.data.CollectionInfo

class CollectionFragment : BaseFragment<FragmentCollectionBinding>() {
    private val collectionAdapter = CollectionAdapter()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCollectionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCollection.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerCollection.adapter = collectionAdapter

        addCollectionInfo()
        recyclerViewClickEvent()
        statsClickEvent()
        setBackButtonEvent()
    }

    private fun addCollectionInfo() {
        collectionAdapter.setItems(
            listOf<CollectionInfo>(
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                ),
                CollectionInfo(
                    image = R.drawable.area_collection_1,
                    name = "취업"
                )
            )
        )
    }

    private fun recyclerViewClickEvent() {
        collectionAdapter.setItemClickListener(object : CollectionAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                (activity as MainActivity).navigateCollectionList()
            }
        })
    }

    private fun statsClickEvent() {
        binding.imageStats.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = StatsDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun setBackButtonEvent() {
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).showFinishToast()
        }
    }
}