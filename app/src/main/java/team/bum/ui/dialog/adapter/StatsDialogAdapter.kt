package team.bum.ui.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.databinding.ItemStatsBinding
import team.bum.ui.dialog.data.StatsMonthInfo
import team.bum.ui.dialog.data.StatsTotalInfo
import java.net.BindException

class StatsDialogAdapter : RecyclerView.Adapter<StatsDialogAdapter.StatsDialogViewHolder>() {

    private val statsMonthInfo = mutableListOf<StatsMonthInfo>()
    private val statsTotalInfo = mutableListOf<StatsTotalInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatsDialogAdapter.StatsDialogViewHolder {
        val binding = ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsDialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsDialogAdapter.StatsDialogViewHolder, position: Int) {
        holder.onBind(statsMonthInfo[position])
        holder.Bind(statsTotalInfo[position])
    }

    fun setMonthItems(newItems: List<StatsMonthInfo>) {
        statsMonthInfo.clear()
        statsMonthInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setTotalItems(newItems: List<StatsTotalInfo>) {
        statsTotalInfo.clear()
        statsTotalInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = statsMonthInfo.size

    class StatsDialogViewHolder(
        private val binding: ItemStatsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(statsMonthInfo: StatsMonthInfo) {
            binding.apply {
                tvStatsCategory.text = statsMonthInfo.category
                tvStatsPercent.text = statsMonthInfo.percent
            }
        }
        fun Bind(statsTotalInfo: StatsTotalInfo) {
            binding.apply {
                tvStatsCategory.text = statsTotalInfo.category
                tvStatsPercent.text = statsTotalInfo.percent
            }
        }
    }
}

