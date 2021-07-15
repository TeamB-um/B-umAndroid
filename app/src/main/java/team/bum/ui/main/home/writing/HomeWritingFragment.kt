package team.bum.ui.main.home.writing

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import retrofit2.Call
import team.bum.R
import team.bum.api.data.*
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentHomeWritingBinding
import team.bum.ui.Paper
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.MainActivity.Companion.categoryMap
import team.bum.ui.main.home.drop.HomeDropFragment
import team.bum.ui.main.home.drop.HomeDropFragment.Companion.COLLECTION
import team.bum.ui.main.home.drop.HomeDropFragment.Companion.DELETE
import team.bum.util.*

class HomeWritingFragment : BaseFragment<FragmentHomeWritingBinding>(), CommonDialog.ClickListener {

    private val sharedPreferences = MyApplication.mySharedPreferences
    private val category = mutableMapOf<String, String>()
    private val paperId
        get() = arguments?.getInt("paperId")

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureWritingTheme()
        configureWritingNavigation()
        getCategory()
        configureTitle()
        configurePostButton()
    }

    private fun configureWritingTheme() {
        Paper.values().forEach {
            if (paperId == it.id) {
                val text = listOf(binding.title, binding.content, binding.count, binding.countTotal)
                binding.root.setBackgroundColor(getColor(it.backgroundColor))
                text.forEach { view -> view.setTextColor(getColor(it.textColor)) }
                binding.title.setHintTextColor(getColor(it.hintColor))
                binding.content.setHintTextColor(getColor(it.hintColor))
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
        binding.setting.setOnClickListener {
            (activity as MainActivity).navigateSettingToManagement()
        }
        binding.post.setOnClickListener {
            CommonDialog.newInstance(
                "삭제 또는 보관",
                "삭제 시 설정 기한 이후 영구 삭제되고,\n보관시 분리수거함에 저장됩니다.",
                "분리수거", true, "삭제", true
            ).show(childFragmentManager, null)
        }
    }

    private fun getCategory() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.getCategory(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = { response ->
                response.data.category.forEach {
                    categoryMap[it.name] = it._id
                }
                configureCategory(categoryMap)
            }
        )
    }

    private fun configureCategory(category: MutableMap<String, String>) {
        val categoryName = mutableListOf<String>()
        category.forEach { categoryName.add(it.key) }
        val emptyView = listOf(binding.arrow, binding.emptyText)

        categoryName.forEachIndexed { i, text ->
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
        binding.content.addTextChangedListener {
            binding.post.enabled(!it.isNullOrBlank())
        }
    }

    private fun getSelectedCategory(): String {
        var selectedCategory = ""
        binding.chipGroup.forEach {
            if ((it as Chip).isChecked) selectedCategory = it.text.toString()
        }
        return selectedCategory
    }

    private fun sendWritingData(dropTo: Boolean) {
        val categoryId = categoryMap[getSelectedCategory()].toString()
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()
        HomeDropFragment.newInstance(categoryId, title, content, dropTo)
        (activity as MainActivity).navigateWritingToDrop(categoryId, title, content, dropTo)
    }

    override fun onClickYes() {
        sendWritingData(COLLECTION)
    }

    override fun onClickCancel() {
        sendWritingData(DELETE)
    }

    companion object {
        fun newInstance(paperId: Int) = HomeWritingFragment().apply {
            arguments = bundleOf("paperId" to paperId)
        }
    }
}