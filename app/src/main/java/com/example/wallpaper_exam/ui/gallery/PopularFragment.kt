package com.example.wallpaper_exam.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.wallpaper_exam.R
import com.example.wallpaper_exam.adapters.WallpaperAdapter
import com.example.wallpaper_exam.models.ImageModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_popular.view.*
import kotlinx.android.synthetic.main.tab_item.view.*

class PopularFragment : Fragment() {
    lateinit var categoryList: ArrayList<ImageModel>
    lateinit var imageList: ArrayList<Int>
    lateinit var wallpaperAdapter: WallpaperAdapter


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_popular, container, false)

        imageList = ArrayList()
        for (i in 0..20) {
            imageList.add(R.drawable.cosmos)
            imageList.add(R.drawable.mi)
            imageList.add(R.drawable.nature1)
            imageList.add(R.drawable.people)
            imageList.add(R.drawable.phone)
        }
        categoryList = ArrayList()
        categoryList.add(ImageModel("ALL", imageList))
        categoryList.add(ImageModel("NEW", imageList))
        categoryList.add(ImageModel("ANIMALS", imageList))
        categoryList.add(ImageModel("TECHNOLOGY", imageList))
        categoryList.add(ImageModel("NATURE", imageList))
        wallpaperAdapter = WallpaperAdapter(categoryList, requireActivity())
        root.view_Pager_popular.adapter = wallpaperAdapter

        TabLayoutMediator(root.tabLayoutpopular, root.view_Pager_popular, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = categoryList[position].titleName
            }

        }).attach()

        root.tabLayoutpopular.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tv_indicator?.visibility = View.VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tv_indicator?.visibility = View.INVISIBLE
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.tv_indicator?.visibility = View.VISIBLE
            }

        })
        val tabcount = root.tabLayoutpopular.tabCount
        for (i in 0 until tabcount) {
            val tabView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null, false)
            val tab = root.tabLayoutpopular.getTabAt(i)
            tab?.customView = tabView
            tabView.tv_title.text = categoryList[i].titleName
            if (i == 0) {
                tabView.tv_indicator.visibility = View.VISIBLE

            } else {
                tabView.tv_indicator.visibility = View.INVISIBLE

            }
        }
        return root
    }
}