package com.example.xumengyin.mypractice.net

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel

open class BaseViewModel : AndroidViewModel {

    constructor(application: Application) : super(application)

}