package com.example.wallpaper_exam.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.wallpaper_exam.ImageFragment
import com.example.wallpaper_exam.models.PagerModel

class WallpaperAdapter(var list:ArrayList<PagerModel>, fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(list[position].titleName)
    }
}