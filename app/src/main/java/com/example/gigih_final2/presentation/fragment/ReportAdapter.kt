package com.example.gigih_final2.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gigih_final2.databinding.ItemReportBinding
import com.example.gigih_final2.domain.Entity.Entities


class ReportAdapter(
    private var data: List<Entities>
) : RecyclerView.Adapter<ReportAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Entities) {
            binding.tvReportTitle.text = data.title
            binding.tvReportBody.text = data.body
            binding.tvReportDate.text = data.date

            Glide.with(binding.root).load(data.imgUrl).into(binding.imgReport)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReportBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position]) // data
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: List<Entities>) {
        this.data = newData
        notifyDataSetChanged()
    }

}