package com.example.xumengyin.mypractice.testactivity;

import android.graphics.Outline;
import android.graphics.Path;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.xumengyin.mypractice.R;
import com.example.xumengyin.mypractice.clipTest.ClippingBasicFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class TestShapeActivity extends BaseActivity
{
	@BindView(R.id.linearLayout)
	View linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		linearLayout.setOutlineProvider(new ViewOutlineProvider()
		{
			@Override
			public void getOutline(View view, Outline outline)
			{
//				Path path=new Path();
//				path.moveTo(view.getWidth()/2,0);
//				path.lineTo(0,view.getHeight()/2);
//				path.lineTo(view.getWidth(),view.getHeight()/2);
//				path.close();
//				outline.setConvexPath(path);
				outline.setRoundRect(10, 10, view.getWidth() - 10,
						view.getHeight() - 10, 10);
			}
		});
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		ClippingBasicFragment fragment = new ClippingBasicFragment();
		transaction.replace(R.id.fragment, fragment);
		transaction.commit();
	}

	@OnClick(R.id.linearLayout)
	public void openClip(View view)
	{
		if (linearLayout.getClipToOutline()) {
			/* The Outline is set for the View, but disable clipping. */
			linearLayout.setClipToOutline(false);

		} else {
			/* Enables clipping on the View. */
			linearLayout.setClipToOutline(true);
		}
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_test_shape;
	}
}
