package com.example.xumengyin.mypractice.kotlin

import android.util.Log

object DataProvider
{

    var data:String?=null
    fun getDatat():String=""
    fun setDatat(str :String)
    {
        data=str
    }
}
class companionClass
{
    var companyData :Any?=null
    companion object {
        fun companion()
        {
            fuckdata="333"
        }
         var fuckdata="1"
    }
    fun companion()
    {

    }
}
fun Any.logmsg(str:String)
{
    Log.d(this.javaClass.simpleName,str)
}