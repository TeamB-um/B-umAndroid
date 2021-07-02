package team.bum.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.bum.databinding.FragmentHomeBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.ui.main.home.writing.HomeWritingFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureHomeNavigation()
    }

    private fun configureHomeNavigation() {
        binding.writing.setOnClickListener {
            (activity as MainActivity).replaceFragment(HomeWritingFragment())
            (activity as MainActivity).hideBottomNav()
        }
    }
}