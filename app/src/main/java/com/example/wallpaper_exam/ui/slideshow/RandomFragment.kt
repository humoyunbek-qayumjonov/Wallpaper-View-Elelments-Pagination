package com.example.wallpaper_exam.ui.slideshow

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework5_92.adapters.RvAdapters
import com.example.wallpaper_exam.ImageFragment
import com.example.wallpaper_exam.R
import com.example.wallpaper_exam.adapters.PaginationAdapter
import com.example.wallpaper_exam.models.PhotoModel
import com.example.wallpaper_exam.models.Result
import com.example.wallpaper_exam.models.inModel.RandomModel
import com.example.wallpaper_exam.models.inModel.RandomModelItem
import com.example.wallpaper_exam.retrofit.ApiClient
import com.example.wallpaper_exam.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_image2.view.*
import kotlinx.android.synthetic.main.fragment_random.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var root:View
    lateinit var list:ArrayList<RandomModelItem>
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_random, container, false)
        list = ArrayList()
        gridLayoutManager = GridLayoutManager(context,3)
        ApiClient.apiService2.getRandomPhoto(30).enqueue(object : Callback<RandomModel> {
            override fun onResponse(call: Call<RandomModel>, response: Response<RandomModel>) {
                if (response.isSuccessful){
                    root.progressbarRandom.visibility = View.GONE
                    val results = response.body()
                    list = results!!
                    rvAdapters = RvAdapters(list, object : RvAdapters.OnMyItemClickListener {
                        override fun onMyItemClick(image: String) {

                        }

                    })
                    root.recyclerViewRandom.layoutManager = gridLayoutManager
                    root.recyclerViewRandom.adapter = rvAdapters
                }
            }

            override fun onFailure(call: Call<RandomModel>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })


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