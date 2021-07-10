package team.bum.ui.main.collection.adapter

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.databinding.ItemCardCollectionBinding
import team.bum.ui.main.collection.data.CollectionListInfo
import team.bum.util.setInvisible
import team.bum.util.setVisible

class CollectionListAdapter :
    RecyclerView.Adapter<CollectionListAdapter.CollectionListViewHolder>() {

    private val collectionListInfo = mutableListOf<CollectionListInfo>()
    private var itemViewMode = MODE_NORMAL
    private var selectedStatus = SparseBooleanArray(0)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionListAdapter.CollectionListViewHolder {
        val binding =
            ItemCardCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.checkCircle.apply {
            if (viewType == MODE_NORMAL) setInvisible()
            if (viewType == MODE_SELECT) setVisible()
        }
        return CollectionListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CollectionListAdapter.CollectionListViewHolder,
        position: Int
    ) {
        holder.onBind(collectionListInfo[position], holder.itemView.context)

    }

    override fun getItemCount(): Int = collectionListInfo.size

    override fun getItemViewType(position: Int): Int = itemViewMode


    fun setItems(newItems: List<CollectionListInfo>) {
        collectionListInfo.clear()
        collectionListInfo.addAll(newItems)
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

    fun setViewMode(mode: Int) {
        itemViewMode = mode
        notifyDataSetChanged()
    }

    fun clearSelectedItem() {
        var position: Int
        for (i: Int in 0..selectedStatus.size() step (1)) {
            position = selectedStatus.keyAt(i)
            selectedStatus.put(position, false)
            notifyItemChanged(position)
        }
        selectedStatus.clear()
    }

    inner class CollectionListViewHolder(
        private val binding: ItemCardCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(collectionListInfo: CollectionListInfo, context: Context) {
            binding.apply {
                tvCollectionName.text = collectionListInfo.title
                tvCollectionContext.text = collectionListInfo.context
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

    companion object {
        const val MODE_NORMAL = 0
        const val MODE_SELECT = 1
    }
}