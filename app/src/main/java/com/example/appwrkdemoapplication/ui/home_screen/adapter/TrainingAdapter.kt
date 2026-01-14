package com.example.appwrkdemoapplication.ui.home_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appwrkdemoapplication.data.local.entity.TrainingModule
import com.example.appwrkdemoapplication.databinding.RvTrainingItemBinding

class TrainingAdapter : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {

    private var list = listOf<TrainingModule>()

    fun submitList(newList: List<TrainingModule>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(RvTrainingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    inner class ViewHolder(val binding: RvTrainingItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvItemTitle.text = item.title
        holder.binding.tvItemDesc.text = item.description
        holder.binding.tvStatus.text =
            if (item.isCompleted) "Completed" else "Pending"

        holder.itemView.setOnClickListener { onClick?.invoke(item) }
    }

    override fun getItemCount() = list.size

    var onClick : ((TrainingModule) -> Unit) ? = null
}
