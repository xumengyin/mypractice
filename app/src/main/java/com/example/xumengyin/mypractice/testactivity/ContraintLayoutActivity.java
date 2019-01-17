package com.example.xumengyin.mypractice.testactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.xumengyin.mypractice.R;
 
/**
 * Created by Administrator on 2017/12/27.
 */

public class ContraintLayoutActivity extends BaseActivity
{
	ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewPager=findViewById(R.id.viewpager);
		viewPager.setAdapter(new ControlAndStatusAdapter(getSupportFragmentManager()));
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.refresh_layout;
	}
	public static int[] title = {R.string.app_name, R.string.app_name};

	private class ControlAndStatusAdapter extends FragmentPagerAdapter
	{


		public ControlAndStatusAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public int getItemPosition(Object object)
		{
			return POSITION_NONE;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			return getResources().getString(title[position]);
		}

		@Override
		public Fragment getItem(int position)
		{
			Fragment fragment;
			if (position == 0)
				fragment = new CarStatusFragment();
			else
				fragment = new CarStatusFragment();
			return fragment;
		}

		@Override
		public int getCount()
		{
			return 3;
		}
	}

}
