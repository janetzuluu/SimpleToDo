package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Bridge that tells recyuler view how to display the data we give it
class taskitemAdapter(val listOfitems:List<String>,val longClickListener: onLongClickListener) : RecyclerView.Adapter<taskitemAdapter.ViewHolder>()  {
interface onLongClickListener{
    fun onItemLongClicked(position: Int)
}
    //tell the recycler view how to layout each item in the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskitemAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: taskitemAdapter.ViewHolder, position: Int) {


        val item=listOfitems.get(position)
        //setting textview to whatever the string item is
        holder.textView.text=item
    }

    override fun getItemCount(): Int {
        return listOfitems.size

    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store referneces to elemts in our layout
        val textView: TextView
        init{
            textView=itemView.findViewById(android.R.id.text1)
            itemView.setOnClickListener{
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }

}