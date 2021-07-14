package team.bum.ui.main.setting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.ui.main.collection.data.CategoryInfo
import team.bum.databinding.ItemCategoryBinding

class SettingBinListAdapter :
    RecyclerView.Adapter<SettingBinListAdapter.SettingBinListViewHolder>() {

    private val categoryInfo = mutableListOf<CategoryInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingBinListViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingBinListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SettingBinListViewHolder,
        position: Int
    ) {
        holder.onBind(categoryInfo[position], holder.itemView.context)
    }

    fun setItems(newItems: List<CategoryInfo>) {
        categoryInfo.clear()
        categoryInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = categoryInfo.size

    class SettingBinListViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(categoryInfo: CategoryInfo, context: Context) {
            binding.apply {
                tvCategory.text = categoryInfo.name
            }
        }
    }
}

