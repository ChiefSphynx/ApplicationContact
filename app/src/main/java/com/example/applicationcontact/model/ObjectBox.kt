package com.example.applicationcontact.model

import android.content.Context
import io.objectbox.BoxStore
        
object ObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()
    }
}
