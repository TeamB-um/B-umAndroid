package team.bum.ui.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.bum.api.data.Stat
import team.bum.databinding.ItemStatsBinding

class StatsDialogAdapter : RecyclerView.Adapter<StatsDialogAdapter.StatsDialogViewHolder>() {

    private val statsInfo = mutableListOf<Stat>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatsDialogAdapter.StatsDialogViewHolder {
        val binding = ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsDialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsDialogAdapter.StatsDialogViewHolder, position: Int) {
        holder.onBind(statsInfo[position])
    }

    fun setItems(newItems: List<Stat>) {
        statsInfo.clear()
        statsInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addItems(newItems: List<Stat>) {
        statsInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = statsInfo.size

    class StatsDialogViewHolder(
        private val binding: ItemStatsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(statsInfo: Stat) {
            binding.apply {
                tvStatsCategory.text = statsInfo.name
                tvStatsNumber.text = statsInfo.percent.toString()
            }
        }
    }
}

