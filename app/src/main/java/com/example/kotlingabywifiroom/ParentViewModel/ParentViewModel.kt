package com.example.kotlingabywifiroom.ParentViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.kotlingabywifiroom.MainActivity
//import com.example.kotlingabywifiroom.Parent.Items
//import com.example.kotlingabywifiroom.Parent.Parent
import com.example.kotlingabywifiroom.Parent.Parentt
import com.example.kotlingabywifiroom.Room.Repository
//import com.example.kotlingabywifiroom.Room.getDatabase
import com.example.kotlingabywifiroom.util.Resource
import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.a

//import com.example.kotlingabywifiroom.Room.getDatabase

class ParentViewModel(application: Application) : AndroidViewModel(application) {


    private val TAG: String = ParentViewModel::class.java.getSimpleName()
    private val PRepository = Repository()
//getDatabase(application)


      fun parents(): LiveData<Resource<Parentt>> {
        Log.d(TAG, "ana bl viewmodel")
            return PRepository.getHeroes()
    }



//    init {
//        parents
//    }

//    fun insert(parent: Parentt) {
//        PRepository.insert(parent)
//        Log.d(TAG, "aam baaml insert")
//    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
//    fun refreshDataFromRepository() :LiveData<List<Parent>> {
//       viewModelScope.launch {
//            PRepository.getH  eroes()
//        }
//
//    }


    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ParentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ParentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}