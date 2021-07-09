package team.bum.ui.main.home.writing

import android.app.Activity
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
import team.bum.ui.Paper
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.util.*

class HomeWritingFragment : BaseFragment<FragmentHomeWritingBinding>(), CommonDialog.ClickListener {

    private val paperId
        get() = arguments?.getInt("paperId")

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureWritingTheme()
        configureWritingNavigation()
        configureCategory()
        configureTitle()
        configurePostButton()
    }

    private fun configureWritingTheme() {
        Paper.values().forEach {
            if (paperId == it.id) {
                val text = listOf(binding.title, binding.body, binding.count, binding.countTotal)
                binding.root.setBackgroundColor(getColor(it.backgroundColor))
                text.forEach { view -> view.setTextColor(getColor(it.textColor)) }
                binding.title.setHintTextColor(getColor(it.hintColor))
                binding.body.setHintTextColor(getColor(it.hintColor))
                binding.divider1.setBackgroundColor(getColor(it.dividerColor))
                binding.divider2.setBackgroundColor(getColor(it.dividerColor))
                binding.setting.setImageResource(it.img)
            }
        }
        StatusBarUtil.changeColor(context as Activity, getColor(R.color.white))
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
                "삭제 또는 보관",
                "삭제 시 설정 기한 이후 영구 삭제되고,\n보관시 분리수거함에 저장됩니다.",
                "분리수거", true, "삭제", true
            ).show(childFragmentManager, null)
        }
    }

    private fun configureCategory() {
        val category = listOf("인간관계", "취업", "오늘하루", "우울", "건강", "웅앵웅")
        val emptyView = listOf(binding.arrow, binding.emptyText)

        category.forEachIndexed { i, text ->
            if (i == 0) binding.chipGroup.addView(createChip(text, true))
            else binding.chipGroup.addView(createChip(text))
        }
        if (category.isEmpty()) emptyView.forEach { it.setVisible() }
        else emptyView.forEach { it.setInvisible() }
    }

    private fun createChip(text: String, isChecked: Boolean = false): Chip {
        return (layoutInflater.inflate(R.layout.view_chip, binding.chipGroup, false) as Chip).apply {
            this.text = text
            this.isChecked = isChecked
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

    private fun configurePostButton() {
        binding.post.enabled(false)
        binding.body.addTextChangedListener {
            binding.post.enabled(!it.isNullOrBlank())
        }
    }

    override fun onClickYes() {
        (activity as MainActivity).navigateWritingToDrop(isDelete = false)
    }

    override fun onClickCancel() {
        (activity as MainActivity).navigateWritingToDrop(isDelete = true)
    }

    companion object {
        fun newInstance(paperId: Int) = HomeWritingFragment().apply {
            arguments = bundleOf("paperId" to paperId)
        }
    }
}