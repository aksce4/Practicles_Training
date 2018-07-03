package com.example.akshay.module3image

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.security.AccessControlContext

class ListAdapter1(context:Context,var imglist1:ArrayList<Int>) : RecyclerView.Adapter<ListAdapter1.ViewHolder>() {

    lateinit var v2:View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v:View= LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        v2=v
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return imglist1.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image=imglist1[position]

        Picasso.get().load(image).into(holder.img)
        holder.ViewHolder2(v2).Name
    }



    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var img=view.findViewById<ImageView>(R.id.imgview)



        inner class ViewHolder2(view: View) : RecyclerView.ViewHolder(view){
            var Name=view.findViewById<EditText>(R.id.edit1)
        }
    }
}