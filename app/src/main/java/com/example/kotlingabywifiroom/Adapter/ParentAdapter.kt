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
        val node_idView = itemView.findViewById<TextView>(R.id.node_id)
        val priView = itemView.findViewById<TextView>(R.id.pri)
        val nameView = itemView.findViewById<TextView>(R.id.name)
        val full_nameView = itemView.findViewById<TextView>(R.id.full_name)
        val ownerView = itemView.findViewById<TextView>(R.id.owner)

        fun bind(user: Item) {
            idView.setText(user.id.toString())
            node_idView.setText(user.node_id)
            priView.setText(user.pri.toString())
            nameView.setText(user.name)
            full_nameView.setText(user.full_name)
            val ed: String = user.owner.id.toString() + " " + user.owner.login
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

    fun sortUsers() : List<Item> {
        return this.users.sortedWith(compareBy({ it.id }))
    }

    fun unsortUsers() :List<Item> {
        return this.users.sortedWith(compareByDescending({ it.id }))

    }

}

interface OnNoteClickListner {
    fun onItemClick(item: Item, position: Int)
}


