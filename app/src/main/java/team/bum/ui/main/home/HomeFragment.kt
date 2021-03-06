package team.bum.ui.main.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
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
    }

    private fun initHome() {
        val dateString = LocalDateTime.now().dateString
        binding.date.text = dateString
        StatusBarUtil.changeColor(context as Activity, getColor(R.color.main_statusbar))
    }

    private fun configureHomeNavigation() {
        binding.root.setOnClickListener {
            navigateToSelectPaper()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).showFinishToast()
        }
    }

    private fun navigateToSelectPaper() {
        val selectView =
            listOf(binding.paper1, binding.paper2, binding.paper3, binding.paper4)
        binding.title.text = getString(R.string.home_select_title)
        binding.background.setInvisible()
        binding.arrow.setInvisible()
        binding.homeLottie.setVisible()
        selectView.forEach { it.setVisible() }

        configureSelectedPaperId()
    }

    private fun configureSelectedPaperId() {
        binding.paper1.setOnClickListener {
            (activity as MainActivity).navigateHomeToWriting(1)
        }
        binding.paper2.setOnClickListener {
            (activity as MainActivity).navigateHomeToWriting(2)
        }
        binding.paper3.setOnClickListener {
            (activity as MainActivity).navigateHomeToWriting(3)
        }
        binding.paper4.setOnClickListener {
            (activity as MainActivity).navigateHomeToWriting(4)
        }
    }
}