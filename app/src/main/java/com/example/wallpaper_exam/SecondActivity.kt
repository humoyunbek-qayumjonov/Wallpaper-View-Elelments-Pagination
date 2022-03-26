package com.example.wallpaper_exam

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent = intent
        val image = intent.getIntExtra("image1",0)
        image_second.setImageResource(image)

        back_button.setOnClickListener {
            finish()
        }
        info_button.setOnClickListener {
            back_button.visibility = View.INVISIBLE
            info_button.visibility = View.INVISIBLE
            share_button.visibility = View.INVISIBLE
            save_button.visibility = View.INVISIBLE
            right_button.visibility = View.INVISIBLE
            star_button.visibility = View.INVISIBLE
            liked_button.visibility = View.INVISIBLE


            val alertDialog =Dialog(this,R.style.BottomSheetDialogTheme)
            val viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_view,null,false)
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