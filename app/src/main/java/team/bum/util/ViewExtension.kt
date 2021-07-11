package team.bum.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.chip.Chip
import team.bum.R

fun LinearLayout.addChip(chipText: String, chipColorState: Int) {
    val chip = LayoutInflater.from(context).inflate(R.layout.view_chip, null) as Chip

    val layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.MarginLayoutParams.WRAP_CONTENT,
        ViewGroup.MarginLayoutParams.WRAP_CONTENT
    )
    chip.text = chipText
    layoutParams.rightMargin = context.dpToPixel(4)
    addView(chip)
}

fun View.setVisible() {
    visibility = View.VISIBLE
}

fun View.setInvisible() {
    visibility = View.INVISIBLE
}

fun View.setGone() {
    visibility = View.GONE
}

fun ImageView.enabled(enable: Boolean) {
    if (isEnabled == enable) return

    isEnabled = enable
    this.setColorFilter(getColor(if (enable) R.color.header else R.color.disable))
}