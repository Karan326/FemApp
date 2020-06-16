package com.example.floclone.Content_Fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.floclone.R

class TipsAdapter(val context: Context, private val list: List<TipsModel>):RecyclerView.Adapter<TipsAdapter.viewholder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.tips_layout,parent,false)


        return viewholder(view)

    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val model=list[position]

       /* Glide's disk cache strategies:
        Glide 3.x & 4.x: DiskCacheStrategy.NONE caches nothing, as discussed
        Glide 4.x: DiskCacheStrategy.DATA, Glide 3.x: DiskCacheStrategy.SOURCE caches only the original full-resolution image. In our example above that would be the 1000x1000 pixel one
        Glide 4.x: DiskCacheStrategy.RESOURCE Glide 3.x: DiskCacheStrategy.RESULT caches only the final image, after reducing the resolution (and possibly transformations) (default behavior of Glide 3.x)
        Glide 4.x only: DiskCacheStrategy.AUTOMATIC intelligently chooses a cache strategy based on the resource (default behavior of Glide 4.x)
        Glide 3.x & 4.x: DiskCacheStrategy.ALL caches all versions of the image As a last example,
        if you've an image which you know you'll manipulate often and make a bunch of different versions of it,
        it makes sense to only cache the original resolution. Thus, we'd tell Glide to only keep the original:*/


        holder.title.text=model.title
        holder.content.text=model.content
        Glide.with(context).load(model.image).diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.pinkybackground).into(holder.imageView)



    }

    class viewholder(view:View):RecyclerView.ViewHolder(view) {

        val title:TextView=view.findViewById(R.id.tipTitle)
        val content:TextView=view.findViewById(R.id.tipContent)
        val imageView:ImageView=view.findViewById(R.id.tipImageView)


    }


}
