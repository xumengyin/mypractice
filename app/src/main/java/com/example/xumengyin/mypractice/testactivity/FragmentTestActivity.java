package com.example.xumengyin.mypractice.testactivity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity; 
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xumengyin.mypractice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragmentTestActivity extends BaseActivity
{
	private static final String TAG = "xuxu";
	@BindView(R.id.viewpager)
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		viewPager.setAdapter(new MyAdapterPager(getSupportFragmentManager()));
		Fragment f=TestFragment.instance(444);
		getSupportFragmentManager().beginTransaction().add(R.id.f_layout,f)
				//.show(f)
				.commitNowAllowingStateLoss();
		ViewGroup group=findViewById(R.id.f_layout);

		logMsg("childCount "+group.getChildCount());
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_fragment_test2;
	}

	static class MyAdapterPager extends FragmentPagerAdapter
	{
		List<Fragment>data =new ArrayList<>();
		FragmentManager fm;
		int length =10;
		public MyAdapterPager(FragmentManager fm)
		{
			super(fm);
			this.fm=fm;
			for (int i = 0; i < length; i++)
			{
				data.add(TestFragment.instance(i));
			}
		}

		@Override
		public Fragment getItem(int position)
		{
			return data.get(position);
		}

		@Override
		public int getCount()
		{
			return length;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			Fragment fragment = (Fragment)super.instantiateItem(container,position);
			fm.beginTransaction().show(fragment).commit();
			return fragment;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
			Fragment fragment = data.get(position);
			fm.beginTransaction().hide(fragment).commit();
		}
	}
	public static class TestFragment extends Fragment
	{
		int value =-1;
		public static TestFragment instance(int value)
		{
			TestFragment f = new TestFragment();
			Bundle b = new Bundle();
			b.putInt("value", value);
			f.setArguments(b);
			return f;
		}
		private void logmsg(String msg)
		{
			Log.d(TAG,msg);
		}
		@Override
		public void onCreate(@Nullable Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			logmsg("onCreate--"+value);
		}

		@Nullable
		@Override
		public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
		{
			View view=inflater.inflate(R.layout.fragment_test,null,false);
			TextView textView=view.findViewById(R.id.text);
			textView.setText("---"+value+"fragment");
			textView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					setUserVisibleHint(!getUserVisibleHint());
				}
			});
			logmsg("onCreateView--"+value);
			return view;
		}

		@Override
		public void onAttach(Context context)
		{
			super.onAttach(context);
			value=getArguments().getInt("value", -1);
			logmsg("onAttach--"+value);

		}

		@Override
		public void onActivityCreated(@Nullable Bundle savedInstanceState)
		{
			super.onActivityCreated(savedInstanceState);
			logmsg("onActivityCreated--"+value);
		}

		@Override
		public void onDestroy()
		{
			super.onDestroy();
			logmsg("onDestroy--"+value);
		}

		@Override
		public void onDestroyView()
		{
			super.onDestroyView();
			logmsg("onDestroyView--"+value);
		}

		@Override
		public void onDetach()
		{
			super.onDetach();
			logmsg("onDetach--"+value);
		}

		@Override
		public void onPause()
		{
			super.onPause();
			logmsg("onPause--"+value);
		}

		@Override
		public void onResume()
		{
			super.onResume();
			logmsg("onResume--"+value);
		}

		@Override
		public void onStop()
		{
			super.onStop();
			logmsg("onStop--"+value);
		}

		@Override
		public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
		{
			super.onViewCreated(view, savedInstanceState);
			logmsg("onViewCreated--"+value);
		}

	}
}
