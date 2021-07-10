package team.bum.ui.main.archive.reward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveRewardBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.RewardDialog
import team.bum.ui.main.archive.adapter.ArchiveRewardAdapter
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.data.ArchiveRewardInfo
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.ui.main.archive.writing.ArchiveWritingFragment

class ArchiveRewardFragment : BaseFragment<FragmentArchiveRewardBinding>() {
    private val archiveRewardAdapter = ArchiveRewardAdapter()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArchiveRewardBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerRewardList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerRewardList.adapter = archiveRewardAdapter

        addArchiveRewardInfo()
        showDialog()
        configureClickEvent()
    }

    private fun configureClickEvent() {
        archiveRewardAdapter.setItemClickListener(object : ArchiveRewardAdapter.ItemClickListener {
            override fun onClick(archiveRewardInfo: ArchiveRewardInfo) {
                header = "hi"
                date = "2021년 07월 10일 (토)"
                content = archiveRewardInfo.rewardContent
                author = archiveRewardInfo.rewardAuthor
                comment = "hello"
                showDialog()
            }
        })
    }

    private fun showDialog() {
        val dialog = RewardDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun addArchiveRewardInfo() {
        archiveRewardAdapter.setItems(
            listOf<ArchiveRewardInfo>(
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "hello",
                    rewardContent = "버들가지 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                )
            )
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