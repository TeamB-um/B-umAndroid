package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import team.bum.databinding.FragmentArchiveWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.dialog.WritingDialog
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter.Companion.MODE_NORMAL
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter.Companion.MODE_SELECT
import team.bum.ui.main.archive.data.ArchiveWritingFilterInfo
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.util.setInvisible
import team.bum.util.setVisible

class ArchiveWritingFragment : BaseFragment<FragmentArchiveWritingBinding>() {

    private val archiveWritingAdapter = ArchiveWritingAdapter()
    private val sheetFragment: FilterSheetFragment = FilterSheetFragment()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerMywritingList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerMywritingList.adapter = archiveWritingAdapter

        configureChips()
        addArchiveWritingInfo()
    }

    private fun configureChips() {
        configureAllCategoryChip()
        configureSelectChip()
        configureDeleteChip()
        configureClickEvent()
    }

    private fun configureAllCategoryChip() {
        binding.chipAllCategory.setOnClickListener {
            sheetFragment.show(requireActivity().supportFragmentManager, sheetFragment.tag)
        }
        sheetFragment.setClickYesListener(object : FilterSheetFragment.ClickListener {
            override fun onClickYes(filterData: ArchiveWritingFilterInfo) {
                Log.d("filterData", "$filterData")
                binding.chipAllCategory.apply {
                    isCheckable = true
                    isChecked = true
                    text = filterData.category
                }
            }
        })
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

    private fun configureClickEvent() {
        archiveWritingAdapter.setItemClickListener(object : ArchiveWritingAdapter.ItemClickListener {
            override fun onClick(archiveWritingInfo: ArchiveWritingInfo) {
                category = archiveWritingInfo.writingCategory
                title = archiveWritingInfo.writingTitle
                date = "2021년 07월 10일 (토)"
                content = archiveWritingInfo.writingContent
                showDialog()
            }
        })
    }

    private fun showDialog() {
        val dialog = WritingDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun addArchiveWritingInfo() {
        archiveWritingAdapter.setItems(
            listOf<ArchiveWritingInfo>(
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목2",
                    writingContent = "어쩌고저쩌고",
                ),
                ArchiveWritingInfo(
                    writingCategory = "인간관계",
                    writingTitle = "글제목2",
                    writingContent = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    writingCategory = "이강민",
                    writingTitle = "이강민아",
                    writingContent = "스트레스 안받아?"
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

    companion object {
        var category = ""
        var title = ""
        var date = ""
        var content = ""
    }
}