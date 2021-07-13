package team.bum.ui.main.collection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import team.bum.R
import team.bum.api.data.ResponseCategory
import team.bum.api.data.ResponseTrashCans
import team.bum.api.data.ResponseUserInfo
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentCollectionBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.StatsDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.collection.adapter.CollectionAdapter
import team.bum.ui.main.collection.data.CollectionInfo
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class CollectionFragment : BaseFragment<FragmentCollectionBinding>() {
    private val collectionAdapter = CollectionAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCollectionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCollection.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerCollection.adapter = collectionAdapter

        getCategoryInfo()
        recyclerViewClickEvent()
        statsClickEvent()
        setBackButtonEvent()
    }

    private fun getCategoryInfo() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.getCategoryInfo(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                collectionAdapter.setItems(it.data.categories)
            }
        )
//        collectionAdapter.setItems(
//            listOf<CollectionInfo>(
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                ),
//                CollectionInfo(
//                    image = R.drawable.area_collection_1,
//                    name = "취업"
//                )
//            )

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