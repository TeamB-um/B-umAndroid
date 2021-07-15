package team.bum.ui.dialog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
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
                tvStatsCategory.text = statsInfo.name
                tvStatsNumber.text = statsInfo.percent.toString()
                when (statsInfo.index) {
                    0 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.blue_2_main
                            )
                        )
                        imageCircle.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.blue_2_main
                            )
                        )
                    }
                    1 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.green_3
                            )
                        )
                        imageCircle.setColorFilter(ContextCompat.getColor(context, R.color.green_3))
                    }
                    2 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.pink_3
                            )
                        )
                        imageCircle.setColorFilter(ContextCompat.getColor(context, R.color.pink_3))
                    }
                    3 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.blue_4
                            )
                        )
                        imageCircle.setColorFilter(ContextCompat.getColor(context, R.color.blue_4))
                    }
                    4 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.green_5
                            )
                        )
                        imageCircle.setColorFilter(ContextCompat.getColor(context, R.color.green_5))
                    }
                    5 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.green_2_main
                            )
                        )
                        imageCircle.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.green_2_main
                            )
                        )
                    }
                    6 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.blue_3
                            )
                        )
                        imageCircle.setColorFilter(ContextCompat.getColor(context, R.color.blue_3))
                    }
                    7 -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.pink_2_main
                            )
                        )
                        imageCircle.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.pink_2_main
                            )
                        )
                    }
                    else -> {
                        tvStatsCategory.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.text_grey
                            )
                        )
                        imageCircle.setColorFilter(
                            ContextCompat.getColor(
                                context,
                                R.color.text_grey
                            )
                        )
                    }
                }
            }
        }
    }
}

