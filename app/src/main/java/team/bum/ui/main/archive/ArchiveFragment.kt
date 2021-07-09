package team.bum.ui.main.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import team.bum.databinding.FragmentArchiveBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchivePagerAdapter

class ArchiveFragment : BaseFragment<FragmentArchiveBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setArchivePager()
    }

    private fun setArchivePager() {
        binding.vp.apply {
            adapter = ArchivePagerAdapter(this@ArchiveFragment)
        }
        TabLayoutMediator(binding.tabLayout, binding.vp) { tab, position ->
            when (position) {
                0 -> { tab.text = "글"}
                1 -> { tab.text = "리워드"}
                2 -> { tab.text = "휴지통"}
            }
        }.attach()
    }
}