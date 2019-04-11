package com.example.xumengyin.mypractice.testactivity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import com.example.xumengyin.mypractice.R
import com.example.xumengyin.mypractice.kotlin.logmsg
import kotlinx.android.synthetic.main.activity_test_live_data.*
import org.jetbrains.anko.startActivity

class TestLiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_live_data)

        image.setOnTouchListener(object : View.OnTouchListener
        {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
               val bitmap= image.drawable as BitmapDrawable
                logmsg("viewwidth:${p0?.width}   viewHeight:${p0?.height}","TestLiveDataActivity")
                logmsg("src:${bitmap.bitmap.width}   src:${bitmap.bitmap.height}","TestLiveDataActivity")
                logmsg("srcsc:${(bitmap.bitmap.width * resources.displayMetrics.densityDpi + (resources.displayMetrics.density /2)) / resources.displayMetrics.density}   src:${bitmap.bitmap.getScaledHeight(resources.displayMetrics)}","TestLiveDataActivity")
               return true
            }

        })
        image2.setOnTouchListener(object : View.OnTouchListener
        {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
               val bitmap= image2.drawable as BitmapDrawable
                logmsg("viewwidth:${p0?.width}   viewHeight:${p0?.height}","TestLiveDataActivity")
                logmsg("src:${bitmap.bitmap.width}   src:${bitmap.bitmap.height}","TestLiveDataActivity")
                logmsg("srcsc:${bitmap.bitmap.getScaledWidth(resources.displayMetrics)}   src:${bitmap.bitmap.getScaledHeight(resources.displayMetrics)}","TestLiveDataActivity")
               return true
            }
        })











        val liveData =MutableLiveData<String>()
        liveData.value="new data"
        liveData.value="new data2"
        liveData.value="new data3"
        jumpButton2.setOnClickListener {
            startActivity(Intent(this,TouchTestActivity::class.java))

        }
        val mapLiveData =Transformations.map(liveData){
            it.length
        }
        mapLiveData.observe(this, Observer {
            logmsg("mapLiveData data value=${it} current Thread:${Thread.currentThread()}")
        })


        Handler().postDelayed(object :Runnable{
            override fun run() {
                liveData.observe(this@TestLiveDataActivity, Observer {
                    logmsg("data value=${it} current Thread:${Thread.currentThread()}","TestLiveDataActivity ")
                })
            }
        },3000)

        liveButton1.setOnClickListener {
            liveData.value="main thread"
        }
        //子线程post
        liveButton2.setOnClickListener {
            Thread(Runnable {
                liveData.postValue("work thread")
            }).start()

        }
    }
}
