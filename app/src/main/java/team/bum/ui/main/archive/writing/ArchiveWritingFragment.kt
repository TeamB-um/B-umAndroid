package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
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

        filterBottomSheetEvent()
        configureChips()
        addArchiveWritingInfo()
    }

    private fun filterBottomSheetEvent() {
        binding.chipAllCategory.setOnClickListener {
            val sheetFragment: FilterSheetFragment = FilterSheetFragment()
            sheetFragment.show(requireActivity().supportFragmentManager, sheetFragment.tag)
        }
    }

    private fun configureChips() {
        configureSelectChip()
        configureDeleteChip()
    }

    private fun configureSelectChip() {
        binding.chipSelect.apply {
            setOnClickListener {
                if (isChecked) {
                    text = "취소"
                    binding.chipDelete.setVisible()
                    archiveWritingAdapter.setViewMode(MODE_SELECT)
                } else {
                    text = "선택"
                    binding.chipDelete.setInvisible()
                    archiveWritingAdapter.setViewMode(MODE_NORMAL)
                    archiveWritingAdapter.clearSelectedItem()
                }
            }
        }
    }

    private fun configureDeleteChip() {
        binding.chipDelete.setOnClickListener {
            CommonDialog.newInstance(
                "글 삭제", "글을 삭제하시겠습니까?",
                "삭제", true, "취소"
            ).show(childFragmentManager, null)
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