package team.bum.ui.main.collection.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import team.bum.R
import team.bum.databinding.FragmentCollectionListBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.ui.main.collection.adapter.CollectionListAdapter
import team.bum.ui.main.collection.data.CollectionInfo
import team.bum.ui.main.collection.data.CollectionListInfo

class CollectionListFragment : BaseFragment<FragmentCollectionListBinding>() {
    private val collectionListAdapter = CollectionListAdapter()
    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCollectionListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerCollectionList.adapter = collectionListAdapter

        addCollectionListInfo()
        configureCollectionNavigation()
    }

    private fun addCollectionListInfo() {
        collectionListAdapter.setItems(
            listOf<CollectionListInfo>(
                CollectionListInfo(
                    title = "글 제목1",
                    context = "글 내용 미리보기 미리보기 미리보기 미리보기"
                ),
                CollectionListInfo(
                    title = "글 제목1",
                    context = "글 내용 미리보기 미리보기 미리보기 미리보기"
                ),
                CollectionListInfo(
                    title = "글 제목1",
                    context = "글 내용 미리보기 미리보기 미리보기 미리보기"
                ),
                CollectionListInfo(
                    title = "글 제목1",
                    context = "글 내용 미리보기 미리보기 미리보기 미리보기"
                ),
                CollectionListInfo(
                    title = "글 제목1",
                    context = "글 내용 미리보기 미리보기 미리보기 미리보기"
                ),
                CollectionListInfo(
                    title = "글 제목1",
                    context = "글 내용 미리보기 미리보기 미리보기 미리보기"
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
}