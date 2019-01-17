package com.example.xumengyin.mypractice.testactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView; 

import com.example.xumengyin.mypractice.R;


/**
 * Created by Administrator on 2017/12/27.
 */

public class CarStatusFragment extends Fragment
{
	private TextView vLeftTopText, vLeftBottomText, vRightTopText, vRightBottomText;
	private ImageView vLeftTopDoor, vLeftBottomDoor, vRightTopDoor, vRightBottomDoor, vDoorNotClose;
	private SwipeRefreshLayout vRefreshLayout;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_status_car, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initView(view);

	}

	private void initView(View view)
	{
		vLeftTopText = (TextView) view.findViewById(R.id.left_top_text);
		vLeftBottomText = (TextView) view.findViewById(R.id.left_bottom_text);
		vRightTopText = (TextView) view.findViewById(R.id.right_top_text);
		vRightBottomText = (TextView) view.findViewById(R.id.right_bottom_text);

		vLeftTopDoor = (ImageView) view.findViewById(R.id.left_top_door);
		vLeftBottomDoor = (ImageView) view.findViewById(R.id.left_bottom_door);
		vRightTopDoor = (ImageView) view.findViewById(R.id.right_top_door);
		vRightBottomDoor = (ImageView) view.findViewById(R.id.right_bottom_door);

		vDoorNotClose = (ImageView) view.findViewById(R.id.door_not_close);
		vRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
//		vRefreshLayout.setColorSchemeResources(R.color.vpi__bright_foreground_disabled_holo_dark);
//		vRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
//		{
//			@Override
//			public void onRefresh()
//			{
//				vRefreshLayout.setRefreshing(true);
//				selectCarStatus();
//			}
//		});
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

	}





	private void defaultData()
	{
		vLeftTopText.setText("--");
		vLeftBottomText.setText("--");
		vRightBottomText.setText("--");
		vRightTopText.setText("--");
	}


	private String createText(String temperature, String pressure)
	{
		return temperature + " ℃" + "\n" + pressure + " bar";
	}

}
