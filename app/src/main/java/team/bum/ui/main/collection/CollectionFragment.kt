package team.bum.ui.main.collection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.databinding.FragmentCollectionBinding
import team.bum.databinding.FragmentSettingBinding
import team.bum.ui.base.BaseFragment
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
}