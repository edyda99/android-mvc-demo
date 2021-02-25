package com.example.kotlingabywifiroom.Adapter

//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlingabywifiroom.Parent.Item
import com.example.kotlingabywifiroom.R
import java.util.*
import java.util.Arrays.sort
import java.util.Collections.sort
import kotlin.Comparator
import kotlin.collections.ArrayList


class ParentAdapter(private val users: ArrayList<Item>, var clickListner: OnNoteClickListner) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView = itemView.findViewById<TextView>(R.id.id)
        val full_nameView = itemView.findViewById<TextView>(R.id.full_name)
        val ownerView = itemView.findViewById<TextView>(R.id.owner)

        fun bind(user: Item) {
            idView.setText(user.id.toString())
            full_nameView.setText(user.full_name)
            val ed: String = user.owner.login
            ownerView.setText(ed)
        }

        fun initialize(item: Item, action: OnNoteClickListner) {
            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ParentViewHolder =
        ParentViewHolder(
            LayoutInflater.from(view.context).inflate(
                R.layout.recyclerview_layout,
                view,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.initialize(users.get(position), clickListner)
        holder.bind(users[position])
    }


    fun addUsers(users: List<Item>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    fun sortUsers()  {
        this.users.sortedWith(compareBy({ it.id }))
        notifyDataSetChanged()
    }

    fun unsortUsers()  {
         this.users.sortedWith(compareByDescending({ it.id }))
    }

}

interface OnNoteClickListner {
    fun onItemClick(item: Item, position: Int)
}


