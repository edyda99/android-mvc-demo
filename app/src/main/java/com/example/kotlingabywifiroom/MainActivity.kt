package com.example.kotlingabywifiroom

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlingabywifiroom.API.ParentNetwork
import com.example.kotlingabywifiroom.Activity2.SingleSelectActivity
import com.example.kotlingabywifiroom.Adapter.OnNoteClickListner
import com.example.kotlingabywifiroom.Adapter.ParentAdapter
import com.example.kotlingabywifiroom.MainActivity.Companion.page
import com.example.kotlingabywifiroom.Parent.Item
import com.example.kotlingabywifiroom.Parent.Parentt
import com.example.kotlingabywifiroom.ParentViewModel.ParentViewModel
import com.example.kotlingabywifiroom.util.Resource
import com.example.kotlingabywifiroom.util.Status
import kotlinx.coroutines.Dispatchers
import org.greenrobot.eventbus.EventBus
import java.util.*

class MainActivity : AppCompatActivity(), OnNoteClickListner {
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ParentAdapter
    private var isLinearLayoutManager = true
    private var mTimeLeftInMillis: Long = 600000
    private lateinit var mTextViewCountDown: TextView
    private lateinit var chrono: Chronometer
    private var ed: Long? = null
    private lateinit var switcher : SwitchCompat
    private val parentViewModel: ParentViewModel by lazy {
        ViewModelProvider(this, ParentViewModel.Factory(application))
            .get(ParentViewModel::class.java)
    }
    private var radioButtn: Boolean = false
    private lateinit var nestedScrollView: NestedScrollView

    companion object {
        var page = MutableLiveData<Int>(0)
        var PAGE = 0
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("millisLeft", mTimeLeftInMillis)
        outState.putLong("chrono", chrono.base)
    }

/*    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft")
        updateCountDownText()
        ed = savedInstanceState.getLong("chrono")
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mTextViewCountDown = findViewById(R.id.timer)
        chrono = findViewById(R.id.chronometer)
        nestedScrollView = findViewById(R.id.scrollView)
        /* Adapting the recycleView */
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

            recyclerView.setLayoutManager(LinearLayoutManager(this))
            adapter = ParentAdapter(arrayListOf(), this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

        Log.d(TAG, "ANA BL MAIN")
        if (savedInstanceState != null) {
//            mTimeLeftInMillis = savedInstanceState.getLong("millisLeft")
            ed = savedInstanceState.getLong("chrono")
        }
        //timer
//        startTimer()
        ed?.let { chrono.setBase(it) }
        chrono.start()
        parentViewModel.parents().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                            liveData(Dispatchers.IO) {
                                emit(
                                    Resource.success(
                                        ParentNetwork.devbytes.getTop(
                                            "created:>2021-02-17",
                                            "stars",
                                            "desc",
                                            "${MainActivity.page}"
                                        )
                                    )
                                )
                            }
                                Log.d(TAG, "bl success")
                                recyclerView.visibility = View.VISIBLE
                                resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun chooselayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }

        recyclerView.adapter = adapter
        nestedScrollView.setOnScrollChangeListener(object :
            NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView?, scrollX: Int, scrollY: Int,
                oldScrollX: Int, oldScrollY: Int
            ) {
                if (scrollY == v?.getChildAt(0)!!.measuredHeight - v.measuredHeight) {
                    PAGE++
                    page.setValue(PAGE)
                }
            }
        })
    }


    private fun retrieveList(users: Parentt) {
        adapter.apply {
            addUsers(users.items)
            chooselayout()
            notifyDataSetChanged()
        }
    }


    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        // Set the drawable for the menu icon based on which LayoutManager is currently in use

        // An if-clause can be used on the right side of an assignment if all paths return a value.
        // The following code is equivalent to
        // if (isLinearLayoutManager)
        //     menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        // else menu.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_switch_layout)
            else ContextCompat.getDrawable(this, R.drawable.ic_launcher_background)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val layoutButton = menu?.findItem(R.id.menu)
        val layoutSwitch = menu!!.findItem(R.id.switchOnOffItem)
        switcher =layoutSwitch.actionView as SwitchCompat

        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooselayout()
                setIcon(item)
                return true
            }
            R.id.switchOnOffItem -> {
                radioButtn = !radioButtn
                sortMyList()
                true
            }

            //  Otherwise, do nothing and use the core event handling

            // when clauses require that all possible paths be accounted for explicitly,
            //  for instance both the true and false cases if the value is a Boolean,
            //  or an else to catch all unhandled cases.
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortMyList() {
        if (radioButtn)
            adapter.apply {
                val edy =sortUsers()
                addUsers(edy)
            }
        else
            adapter.apply {
                addUsers(unsortUsers())
                notifyDataSetChanged()

            }
        recyclerView.adapter= adapter

    }

    override fun onItemClick(item: Item, position: Int) {
        EventBus.getDefault().postSticky(item)
        val intent = Intent(this, SingleSelectActivity::class.java)
        startActivity(intent)
    }


//    fun startTimer() {
//        val timer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText()
//            }
//
//            override fun onFinish() {
//
//            }
//        }
//        timer.start()
//    }

//    private fun updateCountDownText() {
//        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
//        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
//        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
//
//        mTextViewCountDown.setText(timeLeftFormatted)
//    }


}
