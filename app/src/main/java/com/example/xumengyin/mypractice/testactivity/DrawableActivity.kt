package com.example.xumengyin.mypractice.testactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.xumengyin.mypractice.R
import com.example.xumengyin.mypractice.view.GridDrawable
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.activity_drawable.*
import org.jetbrains.anko.Android

class DrawableActivity : BaseActivity() {
    override fun getcontentView() = R.layout.activity_drawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        test_drawable.setImageDrawable(GridDrawable(this@DrawableActivity))
        space.initWithSaveInstanceState(savedInstanceState)
        space.addSpaceItem(SpaceItem("HOME", R.drawable.notification_template_icon_bg));
        space.addSpaceItem(SpaceItem("SEARCH", R.drawable.notification_template_icon_bg));
        space.shouldShowFullBadgeText(true);
        space.setCentreButtonIconColorFilterEnabled(false);
        space.setSpaceOnClickListener(object : SpaceOnClickListener{
            override fun onItemReselected(itemIndex: Int, itemName: String?) {

            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {

            }

            override fun onCentreButtonClick() {

            }

        })

    }
    fun initNavagationBar()
    {

    }
}
