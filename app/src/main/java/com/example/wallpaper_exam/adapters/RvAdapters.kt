package com.example.homework5_92.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper_exam.R
import kotlinx.android.synthetic.main.list_item.view.*

class RvAdapters(var list: ArrayList<Int>,var onMyItemClickListener: OnMyItemClickListener):RecyclerView.Adapter<RvAdapters.MyViewHolder>() {
    inner class MyViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(image:Int){
            itemView.image_view.setImageResource(image)

            itemView.setOnClickListener {
                onMyItemClickListener.onMyItemClick(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView:View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnMyItemClickListener{
        fun onMyItemClick(image: Int)
    }
}