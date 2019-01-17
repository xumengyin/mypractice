package com.example.xumengyin.mypractice;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

public interface LifecycleO extends LifecycleObserver
{
	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	void oncreate(LifecycleOwner owner);
	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	void onPause(LifecycleOwner owner);
}
