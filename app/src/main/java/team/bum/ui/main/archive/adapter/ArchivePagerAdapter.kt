package team.bum.ui.main.archive.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import team.bum.ui.main.archive.bin.ArchiveBinFragment
import team.bum.ui.main.archive.reward.ArchiveRewardFragment
import team.bum.ui.main.archive.writing.ArchiveWritingFragment

class ArchivePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ArchiveWritingFragment()
            1 -> ArchiveRewardFragment()
            2 -> ArchiveBinFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}