package com.example.xumengyin.mypractice

import com.example.xumengyin.mypractice.kotlin.User
import com.example.xumengyin.mypractice.kotlin.User2
import com.example.xumengyin.mypractice.kotlin.formatthis
import com.example.xumengyin.mypractice.testactivity.logContent
import com.example.xumengyin.mypractice.testactivity.logitself
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class  kotlinTest
{
    @Test fun testhaha()
    {
        var model =User("haha",999)
        System.out.println("---"+1526266243000L.formatthis())
        System.out.println("---"+model.name)
    }
    @Test fun test解构()
    {
        var (haha,ff) = User2("haha",999)
        System.out.println("---"+1526266243000L.formatthis())
        System.out.println("---"+haha)
        println("---${ff is Int}--")
        var map=mapOf("xu" to "1","meng" to "2","yin" to "3")
        mutableListOf(1,3).add(11)
        val kkk=map.map { User2(it.key) }
        val jjj=map.mapKeys { it.key+"313" }
        jjj.toString()
        //下划线代表没用到的变量
        for ((_,b) in  mapOf(1 to 3,2 to 4))
        {
            println("---map----${b}")
        }
        kkk.logContent()
        //println("--LL---"+kkk.logContent())
        println("--jjj---"+jjj.toString())
    }

}