package com.example.xumengyin.mypractice.kotlin

import kotlin.reflect.KProperty

class ProexyTest
{
    var kkk="kkk"
    operator fun getValue (ref: Any?, p: KProperty<*>):String
    {
        return kkk
    }
    operator fun setValue (ref: Any?, p: KProperty<*>,value: String)
    {
        kkk="fuck"
    }

    override fun toString(): String {
        return "${super.toString()}+${super.toString()}"
    }
}