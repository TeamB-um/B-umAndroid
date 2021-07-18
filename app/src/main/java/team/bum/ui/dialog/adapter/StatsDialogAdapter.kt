package team.bum.ui.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.api.data.Stat
import team.bum.databinding.ItemStatsBinding
import team.bum.model.Category
import team.bum.ui.main.archive.writing.ArchiveWritingFragment
import team.bum.util.getColor
import team.bum.util.setInvisible
import team.bum.util.setVisible

class StatsDialogAdapter : RecyclerView.Adapter<StatsDialogAdapter.StatsDialogViewHolder>() {

    private val statsInfo = mutableListOf<Stat>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatsDialogViewHolder {
        val binding = ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsDialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsDialogViewHolder, position: Int) {
        holder.onBind(statsInfo[position], holder.itemView.context)
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
        fun onBind(statsInfo: Stat, context: Context) {
            binding.apply {
                if (statsInfo.percent == 0) {
                    tvStatsCategory.text = ""
                    tvStatsPercent.text = ""
                    imageCircle.setInvisible()
                } else {
                    tvStatsCategory.text = statsInfo.name
                    tvStatsPercent.text = "${statsInfo.percent}%"
                    imageCircle.setVisible()
                }
                Category.values().forEach {
                    if (statsInfo.index == it.id) {
                        tvStatsCategory.setTextColor(ContextCompat.getColor(context, it.textColor))
                        imageCircle.setColorFilter(ContextCompat.getColor(context, it.textColor))
                    }
                }
            }
        }
    }
}

