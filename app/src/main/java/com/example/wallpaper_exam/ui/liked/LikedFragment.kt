package com.example.wallpaper_exam.ui.liked

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.homework5_92.adapters.RvAdapters
import com.example.wallpaper_exam.ImageFragment
import com.example.wallpaper_exam.R
import com.example.wallpaper_exam.SecondActivity
import com.example.wallpaper_exam.adapters.LikedAdapter
import com.example.wallpaper_exam.kesh.MySharedPrefarance
import com.example.wallpaper_exam.models.keshmodel.KeshPhotoModel
import kotlinx.android.synthetic.main.fragment_liked.view.*
import kotlinx.android.synthetic.main.fragment_random.view.*
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LikedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LikedFragment : Fragment() {

    interface onSomeEventListener {
        fun someEvent(s: Int?)
    }

    var someEventListener: ImageFragment.onSomeEventListener? = null
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        someEventListener = try {
            activity as ImageFragment.onSomeEventListener
        } catch (e: Exception) {
            throw Exception("$activity must implement onSomeEventListener")
        }
    }
    lateinit var root:View
    lateinit var likedAdapter: LikedAdapter
    lateinit var imageList: ArrayList<KeshPhotoModel>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_liked, container, false)
        imageList = ArrayList()
        MySharedPrefarance.init(context)
        imageList = MySharedPrefarance.obektString

        likedAdapter = LikedAdapter(imageList, object : LikedAdapter.OnMyItemClickListener {
            override fun onMyItemClick(image: String) {
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("image1",image)
                startActivity(intent)
            }

        })
        root.recyclerViewLiked.adapter = likedAdapter

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu2,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        val id = item.itemId
        if (id == R.id.action_like){

        }

    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LikedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                LikedFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}