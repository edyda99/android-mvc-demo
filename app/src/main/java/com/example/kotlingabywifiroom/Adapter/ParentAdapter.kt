package com.example.kotlingabywifiroom.Adapter

//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlingabywifiroom.Activity2.SingleSelectActivity
import com.example.kotlingabywifiroom.parentt.Item
import com.example.kotlingabywifiroom.R
import com.example.kotlingabywifiroom.databinding.ListItemLayoutBinding
//import com.example.kotlingabywifiroom.databinding.RecyclerviewLayoutBinding
import org.greenrobot.eventbus.EventBus
import java.util.*


class ParentAdapter(private val users: ArrayList<Item>, val context: Context) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {


    class ParentViewHolder(
        val context: Context,
        val itemView: View
    ) :
        RecyclerView.ViewHolder(itemView) {
        val idItem = itemView.findViewById<AppCompatTextView>(R.id.idd)
        val full_name = itemView.findViewById<AppCompatTextView>(R.id.full_name)
        val owner = itemView.findViewById<AppCompatTextView>(R.id.owner)
        fun bind(user: Item) {
            idItem.setText(user.id.toString())
            full_name.text = user.full_name
            owner.text = user.owner.login
            itemView.setOnClickListener {
                EventBus.getDefault().postSticky(user)
                val intent = Intent(context, SingleSelectActivity::class.java)
                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ParentViewHolder {
        return ParentViewHolder(context,
            LayoutInflater.from(view.context).inflate(
                R.layout.list_item_layout,
                view,
                false
            )

        )
    }
    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        return holder.bind(users[position])
    }


    fun addUsers(users: List<Item>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}



