package team.bum.ui.main.archive.reward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveRewardBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchiveRewardAdapter
import team.bum.ui.main.archive.data.ArchiveRewardInfo

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
                ),
                ArchiveRewardInfo(
                    rewardDate = "2021.06.30 (수)",
                    rewardContent = "버들가지는 약하나,\n" + "다른 재목을 묶는다.",
                    rewardAuthor = "-조지 허버트-"
                )
            )
        )
    }
}