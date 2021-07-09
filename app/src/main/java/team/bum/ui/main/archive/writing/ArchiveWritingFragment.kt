package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter.Companion.MODE_NORMAL
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter.Companion.MODE_SELECT
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.util.setInvisible
import team.bum.util.setVisible

class ArchiveWritingFragment : BaseFragment<FragmentArchiveWritingBinding>() {
    private val archiveWritingAdapter = ArchiveWritingAdapter()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerMywritingList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerMywritingList.adapter = archiveWritingAdapter
        addArchiveWritingInfo()
        configureWritingList()
    }

    private fun configureWritingList() {
        binding.chipSelect.apply {
            setOnClickListener {
                if (isChecked) {
                    text = "취소"
                    binding.chipRemove.setVisible()
                    archiveWritingAdapter.setViewMode(MODE_SELECT)
                } else {
                    text = "선택"
                    binding.chipRemove.setInvisible()
                    archiveWritingAdapter.setViewMode(MODE_NORMAL)
                    archiveWritingAdapter.clearSelectedItem()
                }
            }
        }
    }

    private fun addArchiveWritingInfo() {
        archiveWritingAdapter.setItems(
            listOf<ArchiveWritingInfo>(
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고",
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목1",
                    writingContent = "어쩌고저쩌고"
                )
            )
        )
    }
}