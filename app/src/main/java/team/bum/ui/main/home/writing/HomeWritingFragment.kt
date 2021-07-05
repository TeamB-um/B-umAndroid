package team.bum.ui.main.home.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import team.bum.R
import team.bum.databinding.FragmentHomeWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.util.getColor
import team.bum.util.setInvisible
import team.bum.util.setVisible

class HomeWritingFragment : BaseFragment<FragmentHomeWritingBinding>(), CommonDialog.ClickListener {

    private val paperIndex
        get() = arguments?.getInt("paperIndex")

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureWritingNavigation()
        configureCategory()
        configureTitle()

        if (paperIndex == 4) {
            binding.root.setBackgroundColor(getColor(R.color.paper_4))
        }
    }

    private fun configureWritingNavigation() {
        binding.back.setOnClickListener {
            (activity as MainActivity).popHomeWriting()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popHomeWriting()
        }
        binding.post.setOnClickListener {
            CommonDialog.newInstance(
                "스트레스의 운명",
                "작성한 스트레스는 삭제 휴지통\n또는 보관 분리수거로 보낼 수 있습니다.",
                "분리수거", true, "삭제", true
            ).show(childFragmentManager, null)
        }
    }

    private fun configureCategory() {
        val category = listOf("인간관계", "취업", "오늘하루", "우울", "건강", "웅앵웅")
        val emptyView = listOf(binding.arrow, binding.emptyText)

        category.forEach {
            binding.chipGroup.addView(createChip(it))
        }
        if (category.isEmpty()) emptyView.forEach { it.setVisible() }
        else emptyView.forEach { it.setInvisible() }
    }

    private fun createChip(text: String): Chip {
        return (layoutInflater.inflate(R.layout.view_chip, binding.chipGroup, false) as Chip).apply {
            this.text = text
            layoutParams =
                ChipGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    private fun configureTitle() {
        binding.title.addTextChangedListener {
            if (!it.isNullOrBlank()) binding.count.text = it.length.toString()
            else binding.count.text = "0"
        }
    }

    override fun onClickYes() {
        (activity as MainActivity).navigateWritingToDropCollection()
    }

    override fun onClickCancel() {
        (activity as MainActivity).navigateWritingToDropDelete()
    }

    companion object {
        fun newInstance(paperIndex: Int) = HomeWritingFragment().apply {
            arguments = bundleOf("paperIndex" to paperIndex)
        }
    }
}