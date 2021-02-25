package com.example.kotlingabywifiroom.PagedListAdapter

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.PageKeyedDataSource
import com.example.kotlingabywifiroom.API.Api
import com.example.kotlingabywifiroom.API.ParentNetwork
import com.example.kotlingabywifiroom.API.ParentNetwork.FIRST_PAGE
import com.example.kotlingabywifiroom.MainActivity
import com.example.kotlingabywifiroom.Room.Repository
import com.example.kotlingabywifiroom.parentt.Item
import com.example.kotlingabywifiroom.util.NetworkState
import com.example.kotlingabywifiroom.util.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import kotlin.coroutines.CoroutineContext

class ParentDataSource(
    coroutineContext: CoroutineContext
) : PageKeyedDataSource<Long, Item>() {

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    private var page: Int = FIRST_PAGE
    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Item>
    ) {
        scope.launch {
            try {
                val response = ParentNetwork.devbytes.getTop(
                    "created:>2021-02-17",
                    "stars",
                    "desc",
                    "$FIRST_PAGE"
                )
                when {
                    response.incomplete_results -> {
                        val listing = response.items
//                        val redditPosts = listing?.map { it.id }
                        callback.onResult(listing, null, (page + 1).toLong())
                        networkState.postValue(NetworkState.LOADED)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }

    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        scope.launch {

            try {
                val response = ParentNetwork.devbytes.getTop(
                    "created:>2021-02-17",
                    "stars",
                    "desc",
                    "${params.key}"
                )
                when {
                    response.incomplete_results -> {
                        val listing = response.items
//                        val redditPosts = listing?.map { it.id }
                        callback.onResult(listing, (page + 1).toLong())
                        networkState.postValue(NetworkState.LOADED)
                    }
                }

            } catch (exception: Exception) {
                Log.e("PostsDataSource", "Failed to fetch data!")
            }

        }
    }


    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Item>) {
        TODO("Not yet implemented")
    }

}
