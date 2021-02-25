package com.example.kotlingabywifiroom.ParentViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.kotlingabywifiroom.PagedListAdapter.ParentDataSource
//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import com.example.kotlingabywifiroom.parentt.Parentt
import com.example.kotlingabywifiroom.Room.Repository
import com.example.kotlingabywifiroom.parentt.Item
//import com.example.kotlingabywifiroom.Room.getDatabase
import com.example.kotlingabywifiroom.util.Resource

//import kotlinx.coroutines.a

//import com.example.kotlingabywifiroom.Room.getDatabase

class ParentViewModel: ViewModel() {

    private val TAG: String = ParentViewModel::class.java.getSimpleName()
    private val PRepository = Repository()
//getDatabase(application)
fun parents(): LiveData<Resource<Parentt>> {
    Log.d(TAG, "ana bl viewmodel")
    return PRepository.getHeroes()
}
    var postsLiveData  :LiveData<PagedList<Item>>
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getPosts():LiveData<PagedList<Item>> = postsLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Long,Item> {

        val dataSourceFactory = object : DataSource.Factory<Long,Item>() {
            override fun create(): DataSource<Long,Item> {
                return ParentDataSource(viewModelScope)
            }
        }
        return LivePagedListBuilder<Long, Item>(dataSourceFactory, config)
    }

    }


//    init {
//        parents
//    }

//    fun insert(parent: Parentt) {
//        PRepository.insert(parent)
//        Log.d(TAG, "aam baaml insert")
//    }



/**
 * Factory for constructing DevByteViewModel with parameter
 */
class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ParentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ParentViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
