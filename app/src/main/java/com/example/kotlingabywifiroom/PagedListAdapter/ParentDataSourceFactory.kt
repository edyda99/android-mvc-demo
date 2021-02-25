//package com.example.kotlingabywifiroom.PagedListAdapter//package com.example.pagedlistexample_kotlin.PagedListAdapter
//
//import android.app.Application
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import com.example.kotlingabywifiroom.API.Api
//import com.example.kotlingabywifiroom.PagedListAdapter.ParentDataSource
//import com.example.kotlingabywifiroom.parentt.Item
//import io.reactivex.disposables.CompositeDisposable
//
//
//class ParentDataSourceFactory(private val compositeDisposable: CompositeDisposable) :
//  DataSource.Factory<Long, Item>() {
//
//
//  val mutableLiveData = MutableLiveData<ParentDataSource>()
//
//
//  override fun create(): DataSource<Long, Item> {
//   val parentDataSource= ParentDataSource(compositeDisposable)
//    mutableLiveData.postValue(parentDataSource)
//    return parentDataSource
//  }
//}