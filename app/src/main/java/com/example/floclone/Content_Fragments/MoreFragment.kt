package com.example.floclone.Content_Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import com.example.floclone.R

/**
 * A simple [Fragment] subclass.
 */
class MoreFragment : Fragment() {

    lateinit var imageview:ImageView
    lateinit var linearLayout: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view=inflater.inflate(R.layout.fragment_more, container, false)


        imageview=view.findViewById(R.id.unveilButton)
        linearLayout=view.findViewById(R.id.notificationsLayout)

        imageview.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

               linearLayout.visibility=View.VISIBLE


            }


        })



        return view
    }


}
