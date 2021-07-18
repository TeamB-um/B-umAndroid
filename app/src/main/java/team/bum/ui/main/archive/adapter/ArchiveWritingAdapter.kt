package team.bum.ui.main.archive.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.api.data.Writing
import team.bum.databinding.ItemCardMywritingBinding
import team.bum.model.Category
import team.bum.util.setInvisible
import team.bum.util.setVisible

class ArchiveWritingAdapter: RecyclerView.Adapter<ArchiveWritingAdapter.ArchiveWritingViewHolder>() {

    private val writing = mutableListOf<Writing>()
    private val writingId = mutableListOf<String>()
    private var itemViewMode = MODE_NORMAL
    private var selectedStatus = SparseBooleanArray(0)
    private lateinit var itemClickListener: ItemClickListener

    inner class ArchiveWritingViewHolder(
        private val binding: ItemCardMywritingBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(writingInfo: Writing, context: Context, position: Int) {
            binding.apply {
                tvMywritingCategory.text = writingInfo.category.name
                tvMywritingTitle.text = writingInfo.title
                tvMywritingContent.text = writingInfo.text
                Category.values().forEach {
                    if (writingInfo.category.index == it.id)
                        tvMywritingCategory.setTextColor(ContextCompat.getColor(context, it.textColor))
                }
                if (itemViewMode == MODE_SELECT) {
                    itemView.setOnClickListener {
                        val adapterPosition = adapterPosition
                        toggleItemSelected(adapterPosition)
                        itemClickListener.onSelect(writingId)
                    }
                    if (isItemSelected(position)) {
                        binding.checkCircle.setImageResource(R.drawable.btn_circle_checked)
                    } else {
                        binding.checkCircle.setImageResource(R.drawable.btn_circle_unchecked)
                    }
                } else {
                    itemView.setOnClickListener {
                        itemClickListener.onClick(writing[position])
                    }
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

    override fun getItemCount(): Int = writing.size

    override fun onBindViewHolder(holder: ArchiveWritingViewHolder, position: Int) {
        holder.onBind(writing[position], holder.itemView.context, position)
    }

    fun setItems(newItems: List<Writing>) {
        writing.clear()
        writing.addAll(newItems)
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
            writingId.remove(writing[position]._id)
        } else {
            selectedStatus.put(position, true)
            notifyItemChanged(position)
            writingId.add(writing[position]._id)
        }
    }

    private fun isItemSelected(position: Int) : Boolean = selectedStatus.get(position, false)

    fun clearSelectedItem() {
        var position: Int
        for (i: Int in 0 until selectedStatus.size() step(1)) {
            position = selectedStatus.keyAt(i)
            selectedStatus.put(position, false)
            notifyItemChanged(position)
        }
        selectedStatus.clear()
        writingId.clear()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onClick(writingInfo: Writing)
        fun onSelect(writingId: List<String>)
    }

    companion object {
        const val MODE_NORMAL = 0
        const val MODE_SELECT = 1
    }
}

