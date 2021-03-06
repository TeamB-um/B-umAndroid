package team.bum.ui.main.collection.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import retrofit2.Call
import team.bum.api.data.ResponseWriting
import team.bum.api.data.Writing
import team.bum.api.ServiceCreator
import team.bum.databinding.FragmentCollectionListBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.dialog.WritingDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.MainActivity.Companion.categoryMap
import team.bum.ui.main.archive.adapter.ArchiveWritingAdapter
import team.bum.ui.main.archive.writing.ArchiveWritingFragment
import team.bum.ui.main.collection.adapter.CollectionListAdapter
import team.bum.util.*
import java.time.LocalDateTime

class CollectionListFragment : BaseFragment<FragmentCollectionListBinding>(), CommonDialog.ClickListener {

    private val collectionListAdapter = CollectionListAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences
    private val categoryName
        get() = arguments?.getString("categoryName")

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCollectionListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerCollectionList.adapter = collectionListAdapter
        binding.tvCategory.text = categoryName

        configureChips()
        configureCollectionNavigation()
        getCategoryWriting()
    }

    private fun configureChips() {
        configureSelectChip()
        configureDeleteChip()
        configureClickEvent()
    }

    private fun configureCollectionNavigation() {
        binding.imageBack.setOnClickListener {
            (activity as MainActivity).popCollectionList()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popCollectionList()
        }
    }

    private fun getCategoryWriting() {
        val call: Call<ResponseWriting> = ServiceCreator.bumService.getWriting(
            sharedPreferences.getValue("token", ""), category_ids = categoryMap[categoryName]
        )
        call.enqueueUtil(
            onSuccess = {
                binding.recyclerCollectionList.setVisible()
                binding.emptyImage.setInvisible()
                binding.emptyText.setInvisible()
                collectionListAdapter.setItems(it.data.writing)
            },
            onError = {
                binding.recyclerCollectionList.setInvisible()
                binding.emptyImage.setVisible()
                binding.emptyText.setVisible()
            }
        )
    }

    private fun configureSelectChip() {
        binding.chipSelect.apply {
            setOnClickListener {
                if (isChecked) {
                    text = "??????"
                    binding.chipDelete.setVisible()
                    collectionListAdapter.setViewMode(ArchiveWritingAdapter.MODE_SELECT)
                } else {
                    text = "??????"
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
                "??? ??????", "?????? ?????????????????????????",
                "??????", true, "??????"
            ).show(childFragmentManager, null)
        }
    }

    private fun configureClickEvent() {
        collectionListAdapter.setItemClickListener(object : CollectionListAdapter.ItemClickListener {
            override fun onClick(writingInfo: Writing) {
                val createdTime = LocalDateTime.parse(writingInfo.created_date.split(".")[0])
                ArchiveWritingFragment.category = writingInfo.category.name
                ArchiveWritingFragment.title = writingInfo.title
                ArchiveWritingFragment.date = createdTime.koFormat
                ArchiveWritingFragment.content = writingInfo.text
                ArchiveWritingFragment.colorIndex = writingInfo.category.index
                showDialog()
            }

            override fun onSelect(writingId: List<String>) {
                categoryWritingIds = writingId
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
            sharedPreferences.getValue("token", ""), ids = categoryWritingIds
        )
        call.enqueueUtil(
            onSuccess = { responseWriting ->
                val responseData = responseWriting.data.writing
                val categoryWriting = mutableListOf<Writing>()
                responseData.forEach {
                    if (it.category._id == categoryMap[categoryName]) categoryWriting.add(it)
                }
                collectionListAdapter.setItems(categoryWriting)
                binding.chipSelect.text = "??????"
                binding.chipSelect.isChecked = false
                binding.chipDelete.setInvisible()
                collectionListAdapter.setViewMode(ArchiveWritingAdapter.MODE_NORMAL)
                collectionListAdapter.clearSelectedItem()
            },
            onError = {
                binding.recyclerCollectionList.setInvisible()
                binding.emptyImage.setVisible()
                binding.emptyText.setVisible()
            }
        )
    }

    override fun onClickYes() {
        deleteWriting()
    }

    companion object {
        fun newInstance(categoryName: String) = CollectionListFragment().apply {
            arguments = bundleOf("categoryName" to categoryName)
        }
        var categoryWritingIds = emptyList<String>()
    }
}