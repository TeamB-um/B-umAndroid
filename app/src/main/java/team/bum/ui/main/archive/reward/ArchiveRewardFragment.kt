package team.bum.ui.main.archive.reward

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import team.bum.api.data.ResponseRewards
import team.bum.api.data.ResponseUserInfo
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentArchiveRewardBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.RewardDialog
import team.bum.ui.main.archive.adapter.ArchiveRewardAdapter
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.data.ArchiveRewardInfo
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.ui.main.archive.writing.ArchiveWritingFragment
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class ArchiveRewardFragment : BaseFragment<FragmentArchiveRewardBinding>() {
    private val archiveRewardAdapter = ArchiveRewardAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArchiveRewardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRewardList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerRewardList.adapter = archiveRewardAdapter

        getArchiveRewardInfo()
//        configureClickEvent()
    }

//    private fun configureClickEvent() {
//        archiveRewardAdapter.setItemClickListener(object : ArchiveRewardAdapter.ItemClickListener {
//            override fun onClick(archiveRewardInfo: ArchiveRewardInfo) {
//                header = "hi"
//                date = "2021년 07월 10일 (토)"
//                content = archiveRewardInfo.rewardContent
//                author = archiveRewardInfo.rewardAuthor
//                comment = "hello"
//                showDialog()
//            }
//        })
//    }

//    private fun showDialog() {
//        val dialog = RewardDialog.CustomDialogBuilder().create()
//        dialog.isCancelable = false
//        dialog.show(parentFragmentManager, "dialog")
//    }

    private fun getArchiveRewardInfo() {
        val call: Call<ResponseRewards> = ServiceCreator.bumService.getRewards(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                Log.d("test", it.message)
                archiveRewardAdapter.setItems(it.data)
            }
        )
    }

    companion object {
        var header = ""
        var date = ""
        var content = ""
        var author = ""
        var comment = ""
    }
}