package team.bum.ui.main.collection.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.bum.api.data.CategoryInfo
import team.bum.databinding.ItemCollectionBinding

class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    private val categoryInfo = mutableListOf<CategoryInfo>()

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionAdapter.CollectionViewHolder {
        val binding =
            ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionAdapter.CollectionViewHolder, position: Int) {
        holder.onBind(categoryInfo[position], holder.itemView.context)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = categoryInfo.size

    fun setItems(newItems: List<CategoryInfo>) {
        categoryInfo.clear()
        categoryInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    class CollectionViewHolder(
        private val binding: ItemCollectionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(categoryInfoInfo: CategoryInfo, context: Context) {
            Glide.with(context).load(categoryInfoInfo.img).into(binding.imageCollection)
            binding.apply {
                tvCategory.text = categoryInfoInfo.name
            }
        }
    }
}