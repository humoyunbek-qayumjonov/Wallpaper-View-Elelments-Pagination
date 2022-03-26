package com.example.wallpaper_exam.ui.slideshow

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.homework5_92.adapters.RvAdapters
import com.example.wallpaper_exam.ImageFragment
import com.example.wallpaper_exam.R
import kotlinx.android.synthetic.main.fragment_random.view.*
import java.lang.Exception

class RandomFragment : Fragment() {
    interface onSomeEventListener {
        fun someEvent(s: Int?)
    }
    var someEventListener: ImageFragment.onSomeEventListener? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        someEventListener = try {
            activity as ImageFragment.onSomeEventListener
        } catch (e: Exception) {
            throw Exception("$activity must implement onSomeEventListener")
        }
    }

    lateinit var rvAdapters: RvAdapters
    lateinit var imageList: ArrayList<Int>
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_random, container, false)
        imageList = ArrayList()
        for (i in 0..20) {
            imageList.add(R.drawable.cosmos)
            imageList.add(R.drawable.mi)
            imageList.add(R.drawable.nature1)
            imageList.add(R.drawable.people)
            imageList.add(R.drawable.phone)
        }
        rvAdapters = RvAdapters(imageList, object : RvAdapters.OnMyItemClickListener {
            override fun onMyItemClick(image: Int) {
                someEventListener?.someEvent(image)
            }

        })

        root.recyclerViewRandom.adapter = rvAdapters
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_random,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}