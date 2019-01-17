package com.example.xumengyin.mypractice.testactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xumengyin.mypractice.R; 
import com.example.xumengyin.mypractice.view.IconPagerAdapter;
import com.example.xumengyin.mypractice.view.TabRelativeFixedPageIndicator;

import butterknife.BindView;

import static android.view.Window.FEATURE_ACTIVITY_TRANSITIONS;
import static android.view.Window.FEATURE_SWIPE_TO_DISMISS;

public class HlistviewBugTestActivity extends BaseActivity
{
	private Fragment[] datas = new Fragment[5];
	@BindView(R.id.pager)
	ViewPager viewpager;
	@BindView(R.id.indicator)
	TabRelativeFixedPageIndicator indicator;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(FEATURE_SWIPE_TO_DISMISS);
	//	requestWindowFeature(FEATURE_ACTIVITY_TRANSITIONS);

		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_hlistview_bug_test);
		viewpager.setAdapter(new TabAdapter(getSupportFragmentManager()));
		viewpager.setOffscreenPageLimit(5);
		indicator.setViewPager(viewpager);
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_hlistview_bug_test;
	}

	class TabAdapter extends FragmentPagerAdapter implements
			IconPagerAdapter
	{


		private String[] CONTENT = getResources().getStringArray(
				R.array.tab_name);


		private final int[] ICONS = new int[]{
				R.drawable.home_page_icon_selector,
				R.drawable.home_page_icon_selector,
				R.drawable.home_page_icon_selector,
				R.drawable.home_page_icon_selector,
				R.drawable.home_page_icon_selector};

		public TabAdapter(FragmentManager fm) {
			super(fm);
			datas[0] = FragmentTestActivity.TestFragment.instance(0);
//            datas[1] = new MyCarMapFragment();
			datas[1] = FragmentTestActivity.TestFragment.instance(1);
			datas[2] =  FragmentTestActivity.TestFragment.instance(2);
			datas[3] =  FragmentTestActivity.TestFragment.instance(3);
			datas[4] =  FragmentTestActivity.TestFragment.instance(4);
		}

		@Override
		public int getIconResId(int index) {

			return ICONS[index];
		}

		@Override
		public String getPageTitle(int position) {

			return CONTENT[position];
		}

		@Override
		public Fragment getItem(int position) {

			return datas[position];
		}

		@Override
		public int getCount() {

			return 5;
		}


		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

	}
}
