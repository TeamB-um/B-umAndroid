package team.bum.ui.main.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import team.bum.api.data.*
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentCollectionBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.RewardDialog
import team.bum.ui.dialog.StatsDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.MainActivity.Companion.categoryMap
import team.bum.ui.main.archive.reward.ArchiveRewardFragment
import team.bum.ui.main.collection.adapter.CollectionAdapter
import team.bum.util.MyApplication
import team.bum.util.dateFormat
import team.bum.util.enqueueUtil
import java.time.LocalDateTime

class CollectionFragment : BaseFragment<FragmentCollectionBinding>() {

    private val collectionAdapter = CollectionAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCollectionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerCollection.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerCollection.adapter = collectionAdapter

        getCategoryInfo()
        getStatsInfo()
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

    private fun getStatsInfo() {
        val call: Call<ResponseStats> = ServiceCreator.bumService.getStats(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                val statsData = it.data.stat
                val allStatsData = it.data.allStat

                statsItems = listOf<Stat>(
                    Stat(statsData[0].index, statsData[0].name, statsData[0].percent),
                    Stat(statsData[1].index, statsData[1].name, statsData[1].percent),
                    Stat(statsData[2].index, statsData[2].name, statsData[2].percent)
                )
                allStatsItems = listOf<Stat>(
                    Stat(allStatsData[0].index, allStatsData[0].name, allStatsData[0].percent),
                    Stat(allStatsData[1].index, allStatsData[1].name, allStatsData[1].percent),
                    Stat(allStatsData[2].index, allStatsData[2].name, allStatsData[2].percent)
                )
                for (i in 3 until statsData.size) monthPercent += statsData[i].percent
                for (i in 3 until allStatsData.size) totalPercent += allStatsData[i].percent
            }
        )
    }

    private fun recyclerViewClickEvent() {
        collectionAdapter.setItemClickListener(object : CollectionAdapter.ItemClickListener {
            override fun onClick(position: Int, categoryName: String, categoryCount: Int) {
                when {
                    categoryCount >= 5 -> {
                        callRewardDialog(categoryName)
                        (activity as MainActivity).navigateCollectionToList(categoryName)
                    }
                    position == collectionAdapter.itemCount - 1 -> {
                        (activity as MainActivity).navigateSettingToManagement()
                    }
                    else -> {
                        (activity as MainActivity).navigateCollectionToList(categoryName)
                    }
                }
            }
        })
    }

    private fun callRewardDialog(categoryName: String) {
        val call: Call<ResponseCategoryReward> =
            ServiceCreator.bumService.getCategoryRewards(
                sharedPreferences.getValue("token", ""),
                categoryMap[categoryName].toString()
            )
        call.enqueueUtil(
            onSuccess = {
                val createdTime = LocalDateTime.parse(it.data.reward.created_date.split(".")[0])
                ArchiveRewardFragment.date = createdTime.dateFormat
                ArchiveRewardFragment.sentence = it.data.reward.sentence
                ArchiveRewardFragment.author = it.data.reward.author
                ArchiveRewardFragment.content = it.data.reward.context
                showRewardDialog()
            }
        )
    }

    private fun statsClickEvent() {
        binding.imageStats.setOnClickListener {
            showDialog()
        }
    }

    private fun showRewardDialog() {
        val dialog = RewardDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
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

    companion object {
        var statsItems = emptyList<Stat>()
        var allStatsItems = emptyList<Stat>()
        var monthPercent: Int = 0
        var totalPercent: Int = 0
    }
}