package team.bum.ui.main.setting.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemCategoryBinding
import team.bum.ui.main.setting.data.BinListInfo

class SettingBinListAdapter :
    RecyclerView.Adapter<SettingBinListAdapter.SettingBinListViewHolder>() {

    private val binListInfo = mutableListOf<BinListInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingBinListAdapter.SettingBinListViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingBinListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SettingBinListAdapter.SettingBinListViewHolder,
        position: Int
    ) {
        holder.onBind(binListInfo[position], holder.itemView.context)
    }

    fun setItems(newItems: List<BinListInfo>) {
        binListInfo.clear()
        binListInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = binListInfo.size

    class SettingBinListViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(binListInfo: BinListInfo, context: Context) {
            binding.apply {
                tvCategory.text = binListInfo.category
            }
        }
    }
}

