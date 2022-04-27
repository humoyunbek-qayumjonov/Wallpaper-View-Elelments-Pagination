package com.example.wallpaper_exam

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.wallpaper_exam.kesh.MySharedPrefarance
import com.example.wallpaper_exam.models.keshmodel.KeshPhotoModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent = intent
        val image = intent.getStringExtra("image1")
        Picasso.get().load(image).into(image_second)

        back_button.setOnClickListener {
            finish()
        }
        save_button.setOnClickListener {
            Toast.makeText(this, "Save Button clicked", Toast.LENGTH_SHORT).show()
        }
        star_button.setOnClickListener {
            Toast.makeText(this, "Edit Button clicked", Toast.LENGTH_SHORT).show()
        }

        right_button.setOnClickListener {
            Toast.makeText(this, "Download Button clicked", Toast.LENGTH_SHORT).show()
        }
        share_button.setOnClickListener {
            Toast.makeText(this, "Share Button clicked", Toast.LENGTH_SHORT).show()
        }

        MySharedPrefarance.init(this)
        var likeList = MySharedPrefarance.obektString
        var index = 0
        var buttonindex = 0
        var indexOf = -1
        if (likeList.isNotEmpty()){
            for (keshPhotoModel in likeList) {
                if (keshPhotoModel.imageUrl == image) {
                    index = 1
                    indexOf = likeList.indexOf(keshPhotoModel)
                }
            }
        }

        if (index == 1){
            liked_button.setImageResource(R.drawable.love_red)
        }else{
            liked_button.setImageResource(R.drawable.ic_vector__10_)
        }
        liked_button.setOnClickListener {
            if (index == 1){
                liked_button.setImageResource(R.drawable.ic_vector__10_)
                likeList.removeAt(indexOf)
                index = 0
                MySharedPrefarance.obektString = likeList
            }else{
                liked_button.setImageResource(R.drawable.love_red)
                likeList.add(KeshPhotoModel(image.toString()))
                MySharedPrefarance.obektString = likeList
                indexOf = likeList.size-1
                index = 1
            }
        }
//        liked_button.setOnClickListener {
//          if (buttonindex == 0){
//              liked_button.setImageResource(R.drawable.love_red)
//              buttonindex = 1
//              likeList.add(KeshPhotoModel(image.toString()))
//              MySharedPrefarance.obektString = likeList
//          }else{
//              liked_button.setImageResource(R.drawable.ic_vector__10_)
//              likeList.removeAt(indexOf)
//              MySharedPrefarance.obektString = likeList
//              buttonindex = 0
//          }
//        }
        info_button.setOnClickListener {
            back_button.visibility = View.INVISIBLE
            info_button.visibility = View.INVISIBLE
            share_button.visibility = View.INVISIBLE
            save_button.visibility = View.INVISIBLE
            right_button.visibility = View.INVISIBLE
            star_button.visibility = View.INVISIBLE
            liked_button.visibility = View.INVISIBLE


            val alertDialog = Dialog(this, R.style.BottomSheetDialogTheme)
            val viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_view, null, false)
            alertDialog.setContentView(viewDialog)
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            alertDialog.window?.setLayout(width, height)
            val dialog_button = viewDialog.findViewById<ImageView>(R.id.exit_button)
            dialog_button.setOnClickListener {
                alertDialog.dismiss()
                back_button.visibility = View.VISIBLE
                info_button.visibility = View.VISIBLE
                share_button.visibility = View.VISIBLE
                save_button.visibility = View.VISIBLE
                right_button.visibility = View.VISIBLE
                star_button.visibility = View.VISIBLE
                liked_button.visibility = View.VISIBLE

            }
            alertDialog.setOnDismissListener {
                back_button.visibility = View.VISIBLE
                info_button.visibility = View.VISIBLE
                share_button.visibility = View.VISIBLE
                save_button.visibility = View.VISIBLE
                right_button.visibility = View.VISIBLE
                star_button.visibility = View.VISIBLE
                liked_button.visibility = View.VISIBLE
            }
            alertDialog.show()
        }
    }


}