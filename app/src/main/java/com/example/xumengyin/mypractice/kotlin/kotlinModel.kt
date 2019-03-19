package com.example.xumengyin.mypractice.kotlin

import com.example.xumengyin.mypractice.util.L
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

/**
 * Created by Administrator on 2018/5/9.
 */
class User(var name:String="",var age:Int=0)
{
    init {
        //1先执行
        L.logE("kotlin","kotlin init")
    }
    constructor(sex:Boolean) : this() {
        //2后执行
        L.logE("kotlin","kotlin constructor")
    }
    constructor(sex:Boolean,level:Int=1) : this() {

    }
}

class Feng<T : Any>(var name:T)

fun Long.formatthis():String
{
    return SimpleDateFormat().format(Date(this))
}
data class User2(var name: String="",var age: Int=10)
{
    override fun toString(): String {
        return super.toString()+"name--"+name+"age:"+age
    }
}
