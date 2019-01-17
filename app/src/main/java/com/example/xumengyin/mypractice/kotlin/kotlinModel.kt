package com.example.xumengyin.mypractice.kotlin

import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

/**
 * Created by Administrator on 2018/5/9.
 */
class User(var name:String="",var age:Int=0) 

class Feng<T : Any>(var name:T)

fun Long.formatthis():String
{
    return SimpleDateFormat().format(Date(this))
}
data class User2(var name: String="",var age: Int=10)
