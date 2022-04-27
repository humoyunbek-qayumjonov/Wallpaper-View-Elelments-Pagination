package com.example.homework5_92.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper_exam.R
import com.example.wallpaper_exam.models.PhotoModel
import com.example.wallpaper_exam.models.Result
import com.example.wallpaper_exam.models.inModel.RandomModel
import com.example.wallpaper_exam.models.inModel.RandomModelItem
import com.example.wallpaper_exam.models.keshmodel.KeshPhotoModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class RvAdapters(var list: ArrayList<RandomModelItem>,var onMyItemClickListener: OnMyItemClickListener):RecyclerView.Adapter<RvAdapters.MyViewHolder>() {
    inner class MyViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(model:RandomModelItem){
            Picasso.get().load(model.urls.small).into(itemView.image_view)
//            itemView.image_view.setImageResource(image)
            itemView.image_view.setOnClickListener {
                onMyItemClickListener.onMyItemClick(model.urls.small)
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
        fun onMyItemClick(image: String)
    }
}