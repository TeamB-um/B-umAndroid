package team.bum.ui.main.home.drop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import team.bum.databinding.FragmentHomeDropBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.dialog.CommonDialog
import team.bum.ui.main.MainActivity

class HomeDropFragment : BaseFragment<FragmentHomeDropBinding>(), CommonDialog.ClickListener {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeDropBinding =
        FragmentHomeDropBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureDropNavigation()
    }

    private fun configureDropNavigation() {
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popHomeDrop()
        }
    }
}