package mob.dau.memehunter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mob.dau.memehunter.databinding.GridListItemBinding
import mob.dau.memehunter.network.MemeItem

class MemesGridAdapter : ListAdapter<MemeItem,
        MemesGridAdapter.MemeViewHolder>(DiffCallback) {
    inner class MemeViewHolder(private var binding: GridListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memeItem: MemeItem) {
            binding.meme = memeItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        return MemeViewHolder(GridListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val memeItem = getItem(position)
        holder.bind(memeItem)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MemeItem>() {
        override fun areItemsTheSame(oldItem: MemeItem, newItem: MemeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MemeItem, newItem: MemeItem): Boolean {
            return oldItem.url == newItem.url
        }
    }
}