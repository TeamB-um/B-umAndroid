package team.bum.ui.main.home.drop

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import team.bum.R
import team.bum.databinding.FragmentHomeDropBinding
import team.bum.ui.base.BaseFragment
import team.bum.ui.main.MainActivity
import team.bum.util.*

class HomeDropFragment : BaseFragment<FragmentHomeDropBinding>() {

    private val isDelete
        get() = arguments?.getBoolean("isDelete") ?: false

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeDropBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDrop()
        configureDropNavigation()
        configureImageDrag()
    }

    private fun initDrop() {
        if (isDelete) {
            binding.root.setBackgroundResource(R.drawable.bg_home_delete)
            binding.headerTitle.text = "삭제 휴지통"
            binding.toastText.text = buildSpannedString {
                append("작성된 글은 ")
                color(getColor(R.color.blue_3)) {
                    append("삭제 휴지통")
                }
                append("으로 이동합니다.")
            }
            binding.completeImage.setBackgroundResource(R.drawable.img_deletepaper_1)
            binding.completeText.text = "삭제되었습니다"
        } else {
            binding.root.setBackgroundResource(R.drawable.bg_home_collection)
            binding.headerTitle.text = "분리수거함"
            binding.toastText.text = buildSpannedString {
                append("작성된 글은 ")
                color(getColor(R.color.blue_3)) {
                    append("분리수거함")
                }
                append("으로 이동합니다.")
            }
            binding.completeImage.setBackgroundResource(R.drawable.img_air_1)
            binding.completeText.text = "분리수거 되었습니다"
        }
        StatusBarUtil.changeColor(context as Activity, getColor(R.color.main_statusbar))
    }

    private fun configureDropNavigation() {
        binding.back.setOnClickListener {
            (activity as MainActivity).popHomeDrop()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            (activity as MainActivity).popHomeDrop()
        }
        binding.complete.setOnClickListener {
            (activity as MainActivity).popHomeDrop()
            (activity as MainActivity).popHomeWriting()
        }
    }

    private fun configureImageDrag() {
        binding.startArea.setOnDragListener(dragListener)
        binding.destination.setOnDragListener(dragListener)
        binding.paper.setOnLongClickListener {
            val clipText = "clip text"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(clipText, mimeTypes, item)
            val dragShadowBuilder = View.DragShadowBuilder(it)

            it.startDragAndDrop(data, dragShadowBuilder, it, 0)
            it.setInvisible()
            true
        }
    }

    private val dragListener = View.OnDragListener { view, event ->
        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val v = event.localState as View
                val owner = v.parent as ViewGroup
                val destination = view as ConstraintLayout

                view.invalidate()
                owner.removeView(v)
                destination.addView(v)
                v.setInvisible()
                binding.complete.setVisible()
                binding.guide.setInvisible()
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }

    companion object {
        fun newInstance(isDelete: Boolean) = HomeDropFragment().apply {
            arguments = bundleOf("isDelete" to isDelete)
        }
    }
}