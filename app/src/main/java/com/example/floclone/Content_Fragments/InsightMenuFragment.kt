package com.example.floclone.Content_Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.floclone.R
import java.util.ArrayList


class InsightMenuFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: List<TipsModel>
    lateinit var adapter:TipsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_insight_menu, container, false)

        recyclerView=view.findViewById(R.id.tipsRecyclerView)
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)


        list= listOf(

            TipsModel(1,"Causes of vaginal bleeding between periods","Bleeding between periods isn’t a normal part of the menstrual cycle.\n" +
                    "\n" +
                    "The average cycle lasts 21 to 35 days. Normal vaginal bleeding, also known as your period, can happen for a few days to a week. Any bleeding outside of this is considered abnormal and can be caused by a variety of factors. These include:\n" +
                    "1. Hormonal imbalance\n" +
                    "As well, some women spot during ovulation as a result of hormonal changes","https://i.pinimg.com/564x/12/8b/68/128b68efe03f84a457ef33a4fa2d36a4.jpg"),

            TipsModel(2,"Home Remedies for Irregular Periods","Yoga has been shown to be an effective treatment for different menstrual issues. A 2013 study with 126 participants found that 35 to 40 minutes of yoga, 5 days a week for 6 months lowered hormone levels related to irregular menstruation (3Trusted Source).\n" + "If you’re new to yoga, look for a studio that offers beginner or level 1 yoga. Once you’ve learned how to properly do several moves, you can continue going to classes, or you can practice yoga from home using videos or routines you find online.","https://i.pinimg.com/564x/ac/70/9f/ac709f120e13de874064b353d0c17910.jpg"),
            TipsModel(3,"Why Is My Period Late: 8 Possible Reasons","You may experience a change in your cycle when you go on or off birth control. Birth control pills contain the hormones estrogen and progestin, which prevent your ovaries from releasing eggs. It can take up to six months for your cycle to become consistent again after stopping the pill. Other types of contraceptives that are implanted or injected can cause missed periods as well.", "https://i.pinimg.com/564x/7d/76/f5/7d76f549045c6a20051e698aca3e66d7.jpg"),
            TipsModel(4,"What Causes Painful Menstrual ","Painful menstruation is also called dysmenorrhea. There are two types of dysmenorrhea: primary and secondary.\n" +
                    "\n" +
                    "Primary dysmenorrhea occurs in people who experience pain before and during menstruation. If you’ve had normal periods that become painful later in life, it may be secondary dysmenorrhea. A condition affecting the uterus or other pelvic organs, such as endometriosis or uterine fibroids, can cause this.", "https://i.pinimg.com/564x/d2/9b/e5/d29be5322e9d4b1df756ab1674ac9ec6.jpg")


        )

        adapter= TipsAdapter(container!!.context,list)
        recyclerView.adapter=adapter
        adapter.notifyDataSetChanged()











        return view





    }


}
