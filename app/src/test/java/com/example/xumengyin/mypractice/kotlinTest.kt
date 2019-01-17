package com.example.xumengyin.mypractice

import com.example.xumengyin.mypractice.kotlin.User
import com.example.xumengyin.mypractice.kotlin.formatthis
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

}