package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.RecyclerViewRowBinding
import com.squareup.picasso.Picasso


class RecyclerAdapter (private val items: List<RecyclerCell>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder (private val binding: RecyclerViewRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: RecyclerCell) {
            Picasso.get().load(item.urlPath).fit().centerInside().into(binding.imageView);
            binding.textView.text = item.headerText
            binding.textView2.text = item.leftText
            binding.textView3.text = item.rightText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val binding = RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

