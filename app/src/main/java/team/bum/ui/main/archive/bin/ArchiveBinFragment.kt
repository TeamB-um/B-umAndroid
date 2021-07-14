package team.bum.ui.main.archive.bin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import team.bum.api.data.ResponseCategory
import team.bum.api.data.ResponseTrashCans
import team.bum.api.retrofit.ServiceCreator
import team.bum.databinding.FragmentArchiveBinBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchiveBinAdapter
import team.bum.ui.main.archive.data.ArchiveBinInfo
import team.bum.util.MyApplication
import team.bum.util.enqueueUtil

class ArchiveBinFragment : BaseFragment<FragmentArchiveBinBinding>() {
    private val archiveBinAdapter = ArchiveBinAdapter()
    private val sharedPreferences = MyApplication.mySharedPreferences


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentArchiveBinBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerBinList.layoutManager = LinearLayoutManager(activity)
        binding.recyclerBinList.adapter = archiveBinAdapter

        bottomSheetDeleteEvent()
        addArchiveRewardInfo()
    }

    private fun bottomSheetDeleteEvent() {
        binding.chipSetting.setOnClickListener {
            val deleteFragment: ArchiveBinDeleteFragment = ArchiveBinDeleteFragment()
            deleteFragment.show(requireActivity().supportFragmentManager, deleteFragment.tag)
        }
    }

    private fun addArchiveRewardInfo() {
        val call: Call<ResponseTrashCans> = ServiceCreator.bumService.getTrashCans(
            sharedPreferences.getValue("token", ""))
        call.enqueueUtil(
            onSuccess = {
                Log.d("test", it.message)
                archiveBinAdapter.setItems(it.data.trashResult)
            }
        )
    }
}