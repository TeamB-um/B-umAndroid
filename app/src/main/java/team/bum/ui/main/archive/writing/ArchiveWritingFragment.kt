package team.bum.ui.main.archive.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import team.bum.api.data.ResponseWriting
import team.bum.api.data.Writing
import team.bum.api.ServiceCreator
import team.bum.databinding.FragmentArchiveWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.dialog.WritingDialog
import team.bum.ui.main.MainActivity.Companion.categoryMap
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter.Companion.MODE_NORMAL
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter.Companion.MODE_SELECT
import team.bum.ui.main.archive.data.ArchiveWritingFilterInfo
import team.bum.util.*
import java.time.LocalDateTime

class ArchiveWritingFragment : BaseFragment<FragmentArchiveWritingBinding>(), CommonDialog.ClickListener {

    private val archiveWritingAdapter = ArchiveWritingAdapter()
    private val sheetFragment: FilterSheetFragment = FilterSheetFragment()
    private val sharedPreferences = MyApplication.mySharedPreferences

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerMywritingList.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerMywritingList.adapter = archiveWritingAdapter

        configureChips()
        getWritingInfo()
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
                binding.chipAllCategory.apply {
                    isCheckable = true
                    if ((filterData.startDate == "") && (filterData.endDate == "") && (filterData.categoryName == "")) {
                        isChecked = false
                        text = "전체 카테고리"
                    } else {
                        text = if (filterData.categoryName == "") "전체 카테고리"
                        else filterData.categoryName
                        isChecked = true
                    }
                }
                getFilterWriting(filterData)
            }
        })
    }

    private fun getWritingInfo() {
        val call: Call<ResponseWriting> = ServiceCreator.bumService.getWriting(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                binding.recyclerMywritingList.setVisible()
                binding.emptyImage.setInvisible()
                binding.emptyText.setInvisible()
                archiveWritingAdapter.setItems(it.data.writing)
            },
            onError = {
                binding.recyclerMywritingList.setInvisible()
                binding.emptyImage.setVisible()
                binding.emptyText.setVisible()
                binding.emptyText.text = "아직 글을 작성하지 않았어요!"
            }
        )
    }

    private fun getFilterWriting(filterData: ArchiveWritingFilterInfo) {
        val call: Call<ResponseWriting> = ServiceCreator.bumService.getWriting(
            sharedPreferences.getValue("token", ""),
            filterData.startDate, filterData.endDate, categoryMap[filterData.categoryName]
        )
        call.enqueueUtil(
            onSuccess = {
                binding.recyclerMywritingList.setVisible()
                binding.emptyImage.setInvisible()
                binding.emptyText.setInvisible()
            },
            onError = {
                binding.recyclerMywritingList.setInvisible()
                binding.emptyImage.setVisible()
                binding.emptyText.setVisible()
                binding.emptyText.text = "글을 찾지 못했어요!"
            }
        )
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
            override fun onClick(writingInfo: Writing) {
                val createdTime = LocalDateTime.parse(writingInfo.created_date.split(".")[0])
                category = writingInfo.category.name
                title = writingInfo.title
                date = createdTime.koFormat
                content = writingInfo.text
                colorIndex = writingInfo.category.index
                showDialog()
            }

            override fun onSelect(writingId: List<String>) {
                writingIds = writingId
            }
        })
    }

    private fun showDialog() {
        val dialog = WritingDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
    }

    private fun deleteWriting() {
        val call: Call<ResponseWriting> = ServiceCreator.bumService.deleteWriting(
            sharedPreferences.getValue("token", ""), ids = writingIds
        )
        call.enqueueUtil(
            onSuccess = {
                archiveWritingAdapter.setItems(it.data.writing)
                binding.chipSelect.text = "선택"
                binding.chipSelect.isChecked = false
                binding.chipDelete.setInvisible()
                archiveWritingAdapter.setViewMode(MODE_NORMAL)
                archiveWritingAdapter.clearSelectedItem()
            }
        )
    }

    override fun onClickYes() {
        deleteWriting()
    }

    companion object {
        var category = ""
        var title = ""
        var date = ""
        var content = ""
        var colorIndex = -1
        var writingIds = emptyList<String>()
    }
}