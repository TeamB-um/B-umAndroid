package team.bum.ui.main.archive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import team.bum.R
import team.bum.databinding.ItemCardRewardBinding
import team.bum.ui.main.archive.data.RewardInfo
import team.bum.util.dateFormat
import java.time.LocalDateTime

class ArchiveRewardAdapter : RecyclerView.Adapter<ArchiveRewardAdapter.ArchiveRewardViewHolder>() {

    private val rewardInfo = mutableListOf<RewardInfo>()
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArchiveRewardViewHolder {
        val binding =
            ItemCardRewardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArchiveRewardViewHolder(binding)
    }

    inner class ArchiveRewardViewHolder(
        private val binding: ItemCardRewardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(reward: RewardInfo, position: Int) {
            binding.apply {
                val createdTime = LocalDateTime.parse(reward.created_date.split(".")[0])
                tvRewardDate.text = createdTime.dateFormat
                tvRewardContent.text = reward.sentence
                tvRewardAuthor.text = reward.author
                when (reward.index) {
                    0 -> viewCardReward.setBackgroundResource(R.drawable.gradient1)
                    1 -> viewCardReward.setBackgroundResource(R.drawable.gradient5)
                    2 -> viewCardReward.setBackgroundResource(R.drawable.gradient8)
                    3 -> viewCardReward.setBackgroundResource(R.drawable.gradient3)
                    4 -> viewCardReward.setBackgroundResource(R.drawable.gradient6)
                    5 -> viewCardReward.setBackgroundResource(R.drawable.gradient4)
                    6 -> viewCardReward.setBackgroundResource(R.drawable.gradient2)
                    7 -> viewCardReward.setBackgroundResource(R.drawable.gradient7)
                    else -> viewCardReward.setBackgroundResource(R.color.bg)
                }
                itemView.setOnClickListener {
                    itemClickListener.onClick(rewardInfo[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = rewardInfo.size

    override fun onBindViewHolder(holder: ArchiveRewardViewHolder, position: Int) {
        holder.onBind(rewardInfo[position], position)
    }

    fun setItems(newItems: List<RewardInfo>) {
        rewardInfo.clear()
        rewardInfo.addAll(newItems)
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(rewardInfo: RewardInfo)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}