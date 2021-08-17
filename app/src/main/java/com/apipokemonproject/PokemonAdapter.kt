package com.apipokemonproject

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apipokemonproject.api.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.util.zip.Inflater

/**
 * Create a recyclerview adaptor to pass views inside the recycler view
 */
class PokemonAdapter(private val theResult: List<Result>, private val context: Context):RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {
    inner class MyViewHolder(var myViews:View):RecyclerView.ViewHolder(myViews) {
        var instanceOfImageView: ImageView
        var instanceOfTextView: TextView

        init {
            instanceOfImageView = myViews.findViewById(R.id.imageViewId)
            instanceOfTextView = myViews.findViewById(R.id.textViewId)
        }

        fun bindingFunction(theResult: Result){
            instanceOfTextView.text = theResult.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflaterVariable = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(inflaterVariable)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val functionResult = SplitImageAddress.splitFunction(theResult[position].name)
        holder.bindingFunction(theResult[position])
        /**
         * Implement click function in onBindViewHolder
         */
        holder.myViews.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("NAME", theResult[position].name)
            intent.putExtra("IMAGE", functionResult )
            intent.putExtra("IMAGEPOSITION", theResult[position].name )

            context.startActivity(intent)
        }
        /**
         * Use glide to load images from a network
         */

        Glide
            .with(context)
            .load(functionResult)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade(3000))
            .into(holder.instanceOfImageView)
    }
    override fun getItemCount(): Int {
        return theResult.size
    }
}