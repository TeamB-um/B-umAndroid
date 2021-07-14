package team.bum.ui.main.setting.bin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import team.bum.api.data.Category
import team.bum.api.data.RequestCategory
import team.bum.api.data.ResponseCategory
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentSettingBinBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.setting.adapter.SettingBinListAdapter
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class SettingBinFragment : BaseFragment<FragmentSettingBinBinding>(), CommonDialog.ClickListener {

    private val settingBinListAdapter = SettingBinListAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences
    private var from = false
    private var deleteCategoryId = ""
    private var editCategoryId = ""

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSettingBinBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerBinList.layoutManager = LinearLayoutManager(activity)
        binding.recyclerBinList.adapter = settingBinListAdapter

        getCategoryList()
        totalNumberEvent()
        configureSettingNavigation()
        configureClickEvent()
    }

    private fun configureClickEvent() {
        binding.imagePlus.setOnClickListener {
            from = ADD
            CommonDialog.newInstance(
                "분리수거함 추가",
                "분리수거함을 추가해 스트레스를 분류하세요.",
                "확인", true, "취소", true, showEdit = true
            ).show(childFragmentManager, null)
        }

        settingBinListAdapter.setClickListener(object : SettingBinListAdapter.CliCkListener {
            override fun onClickDelete(categoryInfo: Category) {
                CommonDialog.newInstance(
                    "분리수거함 삭제",
                    "분리수거함을 삭제하면 글도 모두 지워져요.\n정말 삭제하시겠어요?",
                    "확인", true, "취소", true
                ).show(childFragmentManager, null)
                deleteCategoryId = categoryInfo._id
            }

            override fun onClickText(categoryInfo: Category) {
                from = EDIT
                CommonDialog.newInstance(
                    "분리수거함 수정",
                    "분리수거함 이름을 수정해보세요.",
                    "확인", true, "취소", true, showEdit = true
                ).show(childFragmentManager, null)
                editCategoryId = categoryInfo._id
                Log.d("tag000", "$editCategoryId")
            }
        })
    }

    private fun getCategoryList() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.getCategory(
            sharedPreferences.getValue("token", "")
        )
        call.enqueueUtil(
            onSuccess = {
                settingBinListAdapter.setItems(it.data.category)
                binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
            }
        )
    }

    private fun totalNumberEvent() {
        binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
    }

    private fun configureSettingNavigation() {
        binding.imageBack.setOnClickListener {
            (activity as MainActivity).popSetting()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popSetting()
        }
    }

    private fun addCategory(text: String) {
        val requestCategory = RequestCategory(text)
        val call: Call<ResponseCategory> = ServiceCreator.bumService.addCategory(
            sharedPreferences.getValue("token", ""), requestCategory)
        call.enqueueUtil(
            onSuccess = {
                Log.d("test", it.success.toString())
                settingBinListAdapter.setItems(it.data.category)
                binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
            }
        )
    }

    private fun editCategory(text: String) {
        Log.d("tagdddd", "찍히냐")
        val categoryName = RequestCategory(text)
        val call: Call<ResponseCategory> = ServiceCreator.bumService.editCategory(
            sharedPreferences.getValue("token", ""), editCategoryId, categoryName
        )
        call.enqueueUtil(
            onSuccess = {
                Log.d("tag-edit", "${it.data.category}")
                settingBinListAdapter.setItems(it.data.category)
                binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
            }
        )
    }

    private fun deleteCategory() {
        val call: Call<ResponseCategory> = ServiceCreator.bumService.deleteCategory(
            sharedPreferences.getValue("token", ""), deleteCategoryId
        )
        call.enqueueUtil(
            onSuccess = {
                Log.d("tag-delete", "${it.data.category}")
                settingBinListAdapter.setItems(it.data.category)
                binding.tvNumber.text = settingBinListAdapter.itemCount.toString()
            }
        )
    }

    override fun onClickYes(text: String, from: Boolean) {
        Log.d("tag-from", "$from")
        if (from == ADD) addCategory(text)
        else editCategory(text)
    }

    override fun onClickYes() {
        deleteCategory()
    }

    companion object {
        const val ADD = true
        const val EDIT = false
    }
}