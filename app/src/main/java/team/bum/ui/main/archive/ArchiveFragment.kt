package team.bum.ui.main.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.bum.databinding.FragmentArchiveBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.archive.adapter.ArchivePagerAdapter

class ArchiveFragment : BaseFragment<FragmentArchiveBinding>() {
    // private val archiveAdapter = ArchiveAdapter()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentArchiveBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setArchivePager()
        // addArchiveInfo()
    }

    private fun setArchivePager() {
        binding.vp.adapter = ArchivePagerAdapter(this@ArchiveFragment)
    }

//    private fun addArchiveInfo() {
//        archiveAdapter.setItems(
//            listOf<ArchiveInfo>(
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                ),
//                ArchiveInfo(
//                    category = "인간관계",
//                    title = "취업",
//                    content = "어쩌고저쩌고"
//                )
//            )
//        )
//    }
}