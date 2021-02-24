package com.example.kotlingabywifiroom.Activity2

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlingabywifiroom.Parent.Item
import com.example.kotlingabywifiroom.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

public class SingleSelectActivity : AppCompatActivity() {
    companion object {
        const val Extra_ID = "com.example.kotlingabywifiroom.Activity2.EXTRA_ID"
        const val Extra_FULL_NAME = "com.example.kotlingabywifiroom.Activity2.EXTRA_FULL_NAME"
        const val Extra_NAME = "com.example.kotlingabywifiroom.Activity2.EXTRA_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_select)

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    fun onMessageEventMainThread(messageEvent: Item) {
        Log.d("Main Thread", Thread.currentThread().name)
        val idView = findViewById<TextView>(R.id.id2)
        val nameView = findViewById<TextView>(R.id.node_id2)
        val full_name = findViewById<TextView>(R.id.pri2)
        idView.setText("Message from Second Activtiy: " + messageEvent.id.toString())
        nameView.setText("Message from Second Activtiy: " + messageEvent.name)
        full_name.setText("Message from Second Activtiy: " + messageEvent.full_name)
    }
}