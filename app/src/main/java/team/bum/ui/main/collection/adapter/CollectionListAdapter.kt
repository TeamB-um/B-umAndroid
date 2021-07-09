package team.bum.ui.main.collection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardCollectionBinding
import team.bum.ui.main.collection.data.CollectionListInfo

class CollectionListAdapter :
    RecyclerView.Adapter<CollectionListAdapter.CollectionListViewHolder>() {

    private val collectionListInfo = mutableListOf<CollectionListInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionListAdapter.CollectionListViewHolder {
        val binding =
            ItemCardCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CollectionListAdapter.CollectionListViewHolder,
        position: Int
    ) {
        holder.onBind(collectionListInfo[position], holder.itemView.context)

    }

    override fun getItemCount(): Int = collectionListInfo.size


    fun setItems(newItems: List<CollectionListInfo>) {
        collectionListInfo.clear()
        collectionListInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class CollectionListViewHolder(
        private val binding: ItemCardCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(collectionListInfo: CollectionListInfo, context: Context) {
            binding.apply {
                tvCollectionName.text = collectionListInfo.title
                tvCollectionContext.text = collectionListInfo.context
            }
        }
    }
}