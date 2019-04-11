package com.example.xumengyin.mypractice.testactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.xumengyin.mypractice.R
import kotlinx.android.synthetic.main.activity_test_bezier.*
import java.util.*

class TestBezierActivity : BaseActivity() {
    override fun getcontentView(): Int {
        return R.layout.activity_test_bezier
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // simpleProgress.setProgressWithAnimation()
        val list=listOf(30f,60f,90f,100f,20f,50f)
        val list2=listOf(-40f,60f,90f,100f,20f,50f)
        val list3=listOf(120f,150f,180f,210f,240f,0f)
        val list4=listOf(11f,12f,13f,24f,15f)
        val list5=listOf(3000f,2000f,1000f,5000f,6000f,7000f,8000f)
        val list6=listOf(0.5f,0.1f,0.2f,0.3f,0.4f,0.6f,0.7f)
        var i=0
        val handler =Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                simpleProgress.setProgressWithAnimation(list[i%list.size],3000)
                temputer_dashboard.setValueData(list2[i%list2.size])
                speed_dashboard.setValueData(list3[i%list3.size])
                voltage_dashboard.setValueData(list4[i%list4.size])
                rpm_dashboard.setValueData(list5[i%list5.size])
                oil_dashboard.setValueData(list6[i%list6.size])
                i++
                handler.postDelayed(this,3000)
            }

        },3000)
    }
}
