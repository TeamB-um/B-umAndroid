package team.bum.ui.main.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import team.bum.api.data.Category
import team.bum.api.data.ResponseCategory
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentCollectionBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.StatsDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.collection.adapter.CollectionAdapter
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class CollectionFragment : BaseFragment<FragmentCollectionBinding>() {

    private val collectionAdapter = CollectionAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCollectionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerCollection.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerCollection.adapter = collectionAdapter

        getCategoryInfo()
        recyclerViewClickEvent()
        statsClickEvent()
        setBackButtonEvent()
    }

    private fun getCategoryInfo() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.getCategory(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                collectionAdapter.setItems(it.data.category)
                collectionAdapter.addItems(
                    listOf<Category>(
                        Category(
                            img = "https://soptseminar5test.s3.ap-northeast-2.amazonaws.com/8_0.png",
                            name = "추가하기",
                            created_date = "123456789",
                            _id = "8",
                            count = 0,
                            index = 8
                        )
                    )
                )
            }
        )
    }

    private fun recyclerViewClickEvent() {
        collectionAdapter.setItemClickListener(object : CollectionAdapter.ItemClickListener {
            override fun onClick(position: Int, categoryName: String) {
                if (position == collectionAdapter.itemCount-1) {
                    (activity as MainActivity).navigateSettingToManagement()
                } else {
                    (activity as MainActivity).navigateCollectionToList(categoryName)
                }
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