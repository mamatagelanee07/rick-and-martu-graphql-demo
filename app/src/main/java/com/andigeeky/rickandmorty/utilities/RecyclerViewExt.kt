package com.andigeeky.rickandmorty.utilities

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@OptIn(ExperimentalCoroutinesApi::class)
fun RecyclerView.scrollWithDebounce(): Flow<Int> = callbackFlow {
    val listener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
            val lastPosition = layoutManager?.findLastVisibleItemPosition()
            lastPosition?.let {
                this@callbackFlow.offer(it)
            }
        }
    }
    this@scrollWithDebounce.addOnScrollListener(listener)
    awaitClose {
        this@scrollWithDebounce.removeOnScrollListener(listener)
    }
}