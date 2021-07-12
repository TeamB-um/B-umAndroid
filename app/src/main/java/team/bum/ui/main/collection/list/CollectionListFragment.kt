package team.bum.ui.main.collection.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import team.bum.databinding.FragmentCollectionListBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.dialog.WritingDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.ui.main.archive.writing.ArchiveWritingFragment
import team.bum.ui.main.collection.adapter.CollectionListAdapter
import team.bum.util.setInvisible
import team.bum.util.setVisible

class CollectionListFragment : BaseFragment<FragmentCollectionListBinding>() {
    private val collectionListAdapter = CollectionListAdapter()
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCollectionListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerCollectionList.adapter = collectionListAdapter

        configureChips()
        addCollectionListInfo()
        configureCollectionNavigation()
    }

    private fun configureChips() {
        configureSelectChip()
        configureDeleteChip()
        configureClickEvent()
    }

    private fun addCollectionListInfo() {
        collectionListAdapter.setItems(
            listOf<ArchiveWritingInfo>(
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목2",
                    content = "어쩌고저쩌고",
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목2",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "이강민",
                    title = "이강민아",
                    content = "스트레스 안받아?"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                ),
                ArchiveWritingInfo(
                    category = "인간관계",
                    title = "글제목1",
                    content = "어쩌고저쩌고"
                )
            )
        )
    }

    private fun configureCollectionNavigation() {
        binding.imageBack.setOnClickListener {
            (activity as MainActivity).popCollection()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popCollection()
        }
    }

    private fun configureSelectChip() {
        binding.chipSelect.apply {
            setOnClickListener {
                if (isChecked) {
                    text = "취소"
                    binding.chipDelete.setVisible()
                    collectionListAdapter.setViewMode(ArchiveWritingAdapter.MODE_SELECT)
                } else {
                    text = "선택"
                    binding.chipDelete.setInvisible()
                    collectionListAdapter.setViewMode(ArchiveWritingAdapter.MODE_NORMAL)
                    collectionListAdapter.clearSelectedItem()
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
        collectionListAdapter.setItemClickListener(object : CollectionListAdapter.ItemClickListener {
            override fun onClick(archiveWritingInfo: ArchiveWritingInfo) {
                ArchiveWritingFragment.category = archiveWritingInfo.category
                ArchiveWritingFragment.title = archiveWritingInfo.title
                ArchiveWritingFragment.date = "2021년 07월 10일 (토)"
                ArchiveWritingFragment.content = archiveWritingInfo.content
                showDialog()
            }
        })
    }

    private fun showDialog() {
        val dialog = WritingDialog.CustomDialogBuilder().create()
        dialog.isCancelable = false
        dialog.show(parentFragmentManager, "dialog")
    }
}