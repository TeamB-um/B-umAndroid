package team.bum.ui.main.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import team.bum.R
import team.bum.databinding.FragmentHomeBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.util.StatusBarUtil
import team.bum.util.getColor

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StatusBarUtil.changeColor(context as Activity, getColor(R.color.main_statusbar))
        configureHomeNavigation()
    }

    private fun configureHomeNavigation() {
        binding.arrow.setOnClickListener {
            (activity as MainActivity).navigateHomeToWriting()
        }
    }
}