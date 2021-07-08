package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.data.ArchiveWritingInfo

class ArchiveWritingFragment : BaseFragment<FragmentArchiveWritingBinding>() {
    private val archiveWritingAdapter = ArchiveWritingAdapter()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMywritingList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerMywritingList.adapter = archiveWritingAdapter

        addArchiveWritingInfo()
        configureChips()
    }

    private fun configureChips() {
        binding.chipSelect.setOnClickListener {
            binding.chipSelect.apply {
                text = if (isChecked) "취소" else "선택"
                archiveWritingAdapter.setViewMode(
                    if (isChecked) ArchiveWritingAdapter.MODE_SELECT
                    else ArchiveWritingAdapter.MODE_NORMAL
                )
            }
        }
    }

    private fun addArchiveWritingInfo() {
        archiveWritingAdapter.setItems(
            listOf<ArchiveWritingInfo>(
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고",
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle =  "글제목1",
                    writingContent = "어쩌고저쩌고"
                )
            )
        )
    }
}