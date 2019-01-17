package com.example.xumengyin.mypractice.testactivity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.CoordinatorLayout.Behavior
import android.util.AttributeSet
import android.view.View
import com.example.xumengyin.mypractice.R
import kotlinx.android.synthetic.main.activity_behvior.*

//bottomBehavior和BehaviorDialog
class BehviorActivity : AppCompatActivity() {

   // val behavior by lazy { BottomSheetBehavior.from(bottemLayout) }
    val dialog:BottomSheetDialog by lazy { BottomSheetDialog(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behvior)
        dialog.setContentView(R.layout.bottomsheet_content)
        dialog.show()
    }

}
