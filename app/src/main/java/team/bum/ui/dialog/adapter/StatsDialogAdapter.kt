package team.bum.ui.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.api.data.AllStat
import team.bum.api.data.MonthStat
import team.bum.databinding.ItemStatsBinding
import team.bum.ui.dialog.data.StatsMonthInfo
import team.bum.ui.dialog.data.StatsTotalInfo
import java.net.BindException

class StatsDialogAdapter : RecyclerView.Adapter<StatsDialogAdapter.StatsDialogViewHolder>() {

    private val statsMonthInfo = mutableListOf<MonthStat>()
    private val statsTotalInfo = mutableListOf<AllStat>()

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

    fun setMonthItems(newItems: List<MonthStat>) {
        statsMonthInfo.clear()
        statsMonthInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setTotalItems(newItems: List<AllStat>) {
        statsTotalInfo.clear()
        statsTotalInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = statsMonthInfo.size

    class StatsDialogViewHolder(
        private val binding: ItemStatsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(statsMonthInfo: MonthStat) {
            binding.apply {
                tvStatsCategory.text = statsMonthInfo.name
                tvStatsNumber.text = statsMonthInfo.percent.toString()
            }
        }
        fun Bind(statsTotalInfo: AllStat) {
            binding.apply {
                tvStatsCategory.text = statsTotalInfo.name
                tvStatsNumber.text = statsTotalInfo.percent.toString()
            }
        }
    }
}

