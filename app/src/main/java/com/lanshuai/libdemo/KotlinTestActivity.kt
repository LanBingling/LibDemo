package com.lanshuai.libdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import hugo.weaving.DebugLog

@DebugLog
class KotlinTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        test1()
        test2()
        test3()
    }

    private fun test1() {
        Log.d("KotlinTestActivity", "test1")
    }

    private fun test2() {
        Log.d("KotlinTestActivity", "test2")
    }

    @DebugLog
    private fun test3() {
        Log.d("KotlinTestActivity", "test3")
    }
}