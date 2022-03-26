package com.example.wallpaper_exam

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homework5_92.adapters.RvAdapters
import com.example.wallpaper_exam.models.ImageModel
import kotlinx.android.synthetic.main.fragment_image2.view.*
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    interface onSomeEventListener {
        fun someEvent(s: Int?)
    }

    var someEventListener: onSomeEventListener? = null
    // TODO: Rename and change types of parameters
    private var param1: ArrayList<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getIntegerArrayList(ARG_PARAM1)

        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        someEventListener = try {
            activity as onSomeEventListener
        } catch (e: Exception) {
            throw Exception("$activity must implement onSomeEventListener")
        }
    }


    lateinit var root:View
    lateinit var rvAdapters: RvAdapters
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_image2, container, false)
        rvAdapters = RvAdapters(param1!!, object : RvAdapters.OnMyItemClickListener {
            override fun onMyItemClick(image: Int) {
                someEventListener?.someEvent(image)
            }

        })

        root.recyclerView.adapter = rvAdapters
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ImageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Int>) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putIntegerArrayList(ARG_PARAM1, param1)
                }
            }
    }
}