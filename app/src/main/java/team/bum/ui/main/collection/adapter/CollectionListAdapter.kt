package team.bum.ui.main.collection.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.api.data.Writing
import team.bum.databinding.ItemCardCollectionBinding
import team.bum.util.setInvisible
import team.bum.util.setVisible

class CollectionListAdapter : RecyclerView.Adapter<CollectionListAdapter.CollectionListViewHolder>() {

    private val writing = mutableListOf<Writing>()
    private var itemViewMode = MODE_NORMAL
    private var selectedStatus = SparseBooleanArray(0)
    private lateinit var itemClickListener: ItemClickListener

    inner class CollectionListViewHolder(
        private val binding: ItemCardCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(writingInfo: Writing, position: Int) {
            binding.apply {
                tvCollectionName.text = writingInfo.title
                tvCollectionContext.text = writingInfo.text
                if (itemViewMode == MODE_SELECT) {
                    itemView.setOnClickListener {
                        val adapterPosition = adapterPosition
                        toggleItemSelected(adapterPosition)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionListViewHolder {
        val binding = ItemCardCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.checkCircle.apply {
            if (viewType == MODE_NORMAL) setInvisible()
            if (viewType == MODE_SELECT) setVisible()
        }
        return CollectionListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionListViewHolder, position: Int) {
        holder.onBind(writing[position], position)
    }

    override fun getItemCount(): Int = writing.size

    override fun getItemViewType(position: Int): Int = itemViewMode

    fun setItems(newItems: List<Writing>) {
        writing.clear()
        writing.addAll(newItems)
        notifyDataSetChanged()
    }

    private fun toggleItemSelected(position: Int) {
        if (selectedStatus.get(position, false)) {
            selectedStatus.delete(position)
            notifyItemInserted(position)
        } else {
            selectedStatus.put(position, true)
            notifyItemChanged(position)
        }
    }

    private fun isItemSelected(position: Int) : Boolean = selectedStatus.get(position, false)

    fun setViewMode(mode: Int) {
        itemViewMode = mode
        notifyDataSetChanged()
    }

    fun clearSelectedItem() {
        var position: Int
        for (i: Int in 0 until selectedStatus.size() step (1)) {
            position = selectedStatus.keyAt(i)
            selectedStatus.put(position, false)
            notifyItemChanged(position)
        }
        selectedStatus.clear()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onClick(writingInfo: Writing)
    }

    companion object {
        const val MODE_NORMAL = 0
        const val MODE_SELECT = 1
    }
}