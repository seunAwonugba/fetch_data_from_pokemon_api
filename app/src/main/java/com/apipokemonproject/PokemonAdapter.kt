package com.apipokemonproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apipokemonproject.api.Result
import com.bumptech.glide.Glide
import java.util.zip.Inflater

class PokemonAdapter(private val theResult: List<Result>, private val context: Context):RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {
    inner class MyViewHolder(myViews:View):RecyclerView.ViewHolder(myViews) {
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
        holder.bindingFunction(theResult[position])

        Glide
            .with(context)
            .load(SplitImageAddress.splitFunction(position+1))
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.instanceOfImageView);
    }

    override fun getItemCount(): Int {
        return theResult.size
    }
}