package team.bum.ui.main.archive.reward

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import team.bum.api.data.ResponseRewards
import team.bum.api.ServiceCreator
import team.bum.databinding.FragmentArchiveRewardBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.RewardDialog
import team.bum.ui.main.archive.adapter.ArchiveRewardAdapter
import team.bum.ui.main.archive.data.RewardInfo
import team.bum.util.MyApplication
import team.bum.util.dateFormat
import team.bum.util.enqueueUtil
import java.time.LocalDateTime

class ArchiveRewardFragment : BaseFragment<FragmentArchiveRewardBinding>() {

    private val archiveRewardAdapter = ArchiveRewardAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveRewardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerRewardList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerRewardList.adapter = archiveRewardAdapter

        getArchiveRewardInfo()
        configureClickEvent()
    }

    private fun configureClickEvent() {
        archiveRewardAdapter.setItemClickListener(object : ArchiveRewardAdapter.ItemClickListener {
            override fun onClick(rewardInfo: RewardInfo) {
                val createdTime = LocalDateTime.parse(rewardInfo.created_date.split(".")[0])
                date = createdTime.dateFormat
                sentence = rewardInfo.sentence
                author = rewardInfo.author
                content = rewardInfo.context
                bgColorIndex = rewardInfo.index
                showDialog()
            }
        })
    }

    private fun showDialog() {
        val dialog = RewardDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun getArchiveRewardInfo() {
        val call: Call<ResponseRewards> = ServiceCreator.bumService.getReward(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                Log.d("test", it.success.toString() + "rewards")
                archiveRewardAdapter.setItems(it.data.rewards)
            }
        )
    }

    companion object {
        var date = ""
        var sentence = ""
        var author = ""
        var content = ""
        var bgColorIndex = -1
    }
}