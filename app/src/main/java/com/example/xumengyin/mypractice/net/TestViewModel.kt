package com.example.xumengyin.mypractice.net

import android.app.Application
import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.content.Context
import com.example.xumengyin.mypractice.bean.InitBean

class TestViewModel :BaseViewModel{
   var responsity:TestDataResponsity

    var count = 1
    var liveData = MutableLiveData<String>()
    var liveDataMap =Transformations.map(liveData,object:Function<String,Int>
    {
        override fun apply(input: String?): Int {
           return count++
        }

    })
    constructor(application: Application) : super(application)
    {
        //调用父类方法
       // super<BaseViewModel>.equals("ddd")
        responsity= TestDataResponsity(application)
    }


    public fun initCpsdna(data:String): LiveData<LiveDataResponse<InitBean>> {

        liveData.value=data;
      return  responsity.cpsdnaInit(data);
    }

}