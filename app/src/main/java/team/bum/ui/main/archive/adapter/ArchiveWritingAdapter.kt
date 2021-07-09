package team.bum.ui.main.archive.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.databinding.ItemCardMywritingBinding
import team.bum.ui.main.archive.data.ArchiveWritingInfo
import team.bum.util.setInvisible
import team.bum.util.setVisible

class ArchiveWritingAdapter: RecyclerView.Adapter<ArchiveWritingAdapter.ArchiveWritingViewHolder>() {

    private val archiveWritingInfo = mutableListOf<ArchiveWritingInfo>()
    private var itemViewMode = MODE_NORMAL
    private var selectedStatus = SparseBooleanArray(0)

    inner class ArchiveWritingViewHolder(
        private val binding: ItemCardMywritingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(archiveInfo: ArchiveWritingInfo, position: Int) {
            binding.apply {
                tvMywritingCategory.text = archiveInfo.writingCategory
                tvMywritingTitle.text = archiveInfo.writingTitle
                tvMywritingContent.text = archiveInfo.writingContent
                itemView.setOnClickListener {
                    val mposition = adapterPosition
                    toggleItemSelected(mposition)
                }
                if (selectedStatus.get(position, false)) {
                    binding.checkCircle.setImageResource(R.drawable.btn_circle_checked)
                } else {
                    binding.checkCircle.setImageResource(R.drawable.btn_circle_unchecked)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveWritingViewHolder {
        val binding = ItemCardMywritingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.checkCircle.apply {
            if (viewType == MODE_NORMAL) setInvisible()
            if (viewType == MODE_SELECT) setVisible()
         }
        return ArchiveWritingViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int = itemViewMode

    override fun getItemCount(): Int = archiveWritingInfo.size

    override fun onBindViewHolder(holder: ArchiveWritingViewHolder, position: Int) {
        holder.onBind(archiveWritingInfo[position], position)
    }

    fun setItems(newItems: List<ArchiveWritingInfo>) {
        archiveWritingInfo.clear()
        archiveWritingInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setViewMode(mode: Int) {
        itemViewMode = mode
        notifyDataSetChanged()
    }

    private fun toggleItemSelected(position: Int) {
        if (selectedStatus.get(position, false)) {
            selectedStatus.delete(position)
            notifyItemChanged(position)
        } else {
            selectedStatus.put(position, true)
            notifyItemChanged(position)
        }
    }

    fun clearSelectedItem() {
        var position: Int
        for (i: Int in 0..selectedStatus.size() step(1)) {
            position = selectedStatus.keyAt(i)
            selectedStatus.put(position, false)
            notifyItemChanged(position)
        }
        selectedStatus.clear()
    }

    fun ItemCardMywritingBinding.changeMode(isSelectMode: Boolean = false) {
        this.checkCircle.apply {
            if (isSelectMode) setVisible() else setInvisible()
        }
    }

    companion object {
        const val MODE_NORMAL = 0
        const val MODE_SELECT = 1
    }
}

