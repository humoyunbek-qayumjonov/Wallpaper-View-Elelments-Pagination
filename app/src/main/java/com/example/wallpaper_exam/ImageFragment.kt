package com.example.wallpaper_exam

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework5_92.adapters.RvAdapters
import com.example.wallpaper_exam.adapters.PaginationAdapter
import com.example.wallpaper_exam.models.PhotoModel
import com.example.wallpaper_exam.retrofit.ApiClient
import com.example.wallpaper_exam.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_image2.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        fun someEvent(s: String?)
    }

    var someEventListener: onSomeEventListener? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

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


    lateinit var root: View
    lateinit var paginationAdapter: PaginationAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1
    private var TOTAL_PAGES = 3

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_image2, container, false)
        gridLayoutManager = GridLayoutManager(context, 3)
        paginationAdapter = PaginationAdapter(object:PaginationAdapter.OnMyItemClickListener{
            override fun onMyItemClick(image: String) {
                someEventListener?.someEvent(image)
            }

        })
        root.rv.addOnScrollListener(object : PaginationScrollListener(gridLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 1
                loadNextPage()
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })

        root.rv.layoutManager = gridLayoutManager
        root.rv.adapter = paginationAdapter
        loadFirstPage()
//        rvAdapters = RvAdapters(param1!!, object : RvAdapters.OnMyItemClickListener {
//            override fun onMyItemClick(image: Int) {
//                someEventListener?.someEvent(image)
//            }
//
//        })

//        root.recyclerView.adapter = rvAdapters
        return root
    }

    private fun loadFirstPage() {
        ApiClient.apiService.getPhoto(param1.toString(), currentPage)
            .enqueue(object : Callback<PhotoModel> {
                override fun onResponse(call: Call<PhotoModel>, response: Response<PhotoModel>) {
                    if (response.isSuccessful) {
                        root.progressbar.visibility = View.GONE

                        paginationAdapter.addAll(response.body()?.results ?: emptyList())
                        if (currentPage <= TOTAL_PAGES) {
                            paginationAdapter.editLoading()
                        } else {
                            isLastPage = true
                        }
                    }
                }

                override fun onFailure(call: Call<PhotoModel>, t: Throwable) {

                }

            })
    }

    fun loadNextPage() {
        ApiClient.apiService.getPhoto(param1.toString(), currentPage).enqueue(object : Callback<PhotoModel> {
            override fun onResponse(call: Call<PhotoModel>, response: Response<PhotoModel>) {
                if (response.isSuccessful) {
                    paginationAdapter.removeLoading()
                    isLoading = false
                    paginationAdapter.addAll(response.body()?.results ?: emptyList())
                    if (currentPage <= TOTAL_PAGES) {
                        paginationAdapter.editLoading()
                    } else {
                        isLastPage = true
                    }
                }
            }

            override fun onFailure(call: Call<PhotoModel>, t: Throwable) {

            }

        })
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
        fun newInstance(param1: String) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}