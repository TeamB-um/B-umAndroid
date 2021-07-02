package team.bum.ui.main.home.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import team.bum.databinding.FragmentHomeWritingBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity
import team.bum.ui.main.home.HomeFragment
import team.bum.ui.main.home.drop.HomeDropFragment

class HomeWritingFragment : BaseFragment<FragmentHomeWritingBinding>(), CommonDialog.ClickListener {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeWritingBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureWritingNavigation()
    }

    private fun configureWritingNavigation() {
        binding.back.setOnClickListener {
            navigateToHome()
        }
        binding.post.setOnClickListener {
            CommonDialog.newInstance(
                "스트레스의 운명",
                "작성한 스트레스는 삭제 휴지통\n또는 보관 분리수거로 보낼 수 있습니다.",
                "분리수거", true, "삭제", true
            ).show(childFragmentManager, null)
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            navigateToHome()
        }
    }

    private fun navigateToHome() {
        (activity as MainActivity).replaceFragment(HomeFragment())
        (activity as MainActivity).showBottomNav()
    }

    private fun navigateToDrop() {
        (activity as MainActivity).replaceFragment(HomeDropFragment())
        (activity as MainActivity).hideBottomNav()
    }

    override fun onClickYes() {
        navigateToDrop()
    }

    override fun onClickCancel() {
        navigateToDrop()
    }
}