package team.bum.ui.main.collection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCardCollectionBinding
import team.bum.databinding.ItemCollectionBinding
import team.bum.ui.main.collection.data.CollectionInfo

class CollectionAdapter: RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private val collectionInfo = mutableListOf<CollectionInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionAdapter.CollectionViewHolder {
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionAdapter.CollectionViewHolder, position: Int) {
        holder.onBind(collectionInfo[position], holder.itemView.context)
    }

    override fun getItemCount(): Int = collectionInfo.size

    fun setItems(newItems: List<CollectionInfo>) {
        collectionInfo.clear()
        collectionInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class CollectionViewHolder(
        private val binding: ItemCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(collectionInfo: CollectionInfo, context: Context) {
            binding.apply {
                imageCollection.setImageResource(collectionInfo.image)
                tvCategory.text = collectionInfo.name
            }
        }
    }
}
