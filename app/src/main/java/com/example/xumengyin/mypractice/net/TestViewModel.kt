package com.example.xumengyin.mypractice.net

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.xumengyin.mypractice.bean.InitBean

class TestViewModel :BaseViewModel{
   var responsity:TestDataResponsity

    constructor(application: Application) : super(application)
    {
        //调用父类方法
       // super<BaseViewModel>.equals("ddd")
        responsity= TestDataResponsity(application)
    }


    public fun initCpsdna(data:String): LiveData<LiveDataResponse<InitBean>> {

      return  responsity.cpsdnaInit(data);
    }

}