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
import team.bum.util.*
import java.time.LocalDateTime

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initHome()
        configureHomeNavigation()

        binding.paper4.setOnClickListener {
            (activity as MainActivity).navigateHomeToWriting(4)
        }
    }

    private fun initHome() {
        val dateString = LocalDateTime.now().dateString
        binding.date.text = dateString
        StatusBarUtil.changeColor(context as Activity, getColor(R.color.main_statusbar))
    }

    private fun configureHomeNavigation() {
        binding.bin.setOnClickListener {
            navigateToSelectPaper()
        }
    }

    private fun navigateToSelectPaper() {
        val selectView =
            listOf(binding.paperLottie, binding.paper1, binding.paper2, binding.paper3, binding.paper4, binding.less, binding.lot)
        binding.title.text = getString(R.string.home_select_title)
        binding.arrow.setInvisible()
        selectView.forEach { it.setVisible() }
    }
}