package team.bum.ui.main.archive.bin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveBinBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchiveBinAdapter
import team.bum.ui.main.archive.data.ArchiveBinInfo

class ArchiveBinFragment : BaseFragment<FragmentArchiveBinBinding>() {
    private val archiveBinAdapter = ArchiveBinAdapter()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArchiveBinBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerBinList.layoutManager = GridLayoutManager(activity, 1)
        binding.recyclerBinList.adapter = archiveBinAdapter

        addArchiveRewardInfo()
    }

    private fun addArchiveRewardInfo() {
        archiveBinAdapter.setItems(
            listOf<ArchiveBinInfo>(
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                ),
                ArchiveBinInfo(
                    binCategory = "인간관계",
                    binTitle = "글제목1",
                    binContent = "글 내용 미리보기 미리보기 미리보기 미리보기",
                    binDday = "D-1"
                )
            )
        )
    }
}