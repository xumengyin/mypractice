package com.example.xumengyin.mypractice.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.xumengyin.mypractice.MainActivity;
import com.example.xumengyin.mypractice.R;
import com.example.xumengyin.mypractice.behavior.CardBehavior;

public class CardListView extends FrameLayout
{
	int headHeight;
	TextView textView;
	public CardListView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		View v = LayoutInflater.from(context).inflate(R.layout.card_layout, this);
		RecyclerView recyclerView = v.findViewById(R.id.recycleview);
		textView = v.findViewById(R.id.title);
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CardListView);
		textView.setBackground(array.getDrawable(R.styleable.CardListView_bgcolor));
		textView.setText(array.getString(R.styleable.CardListView_android_text));
		recyclerView.setAdapter(new Myadapter(context));
		recyclerView.setLayoutManager(new LinearLayoutManager(context));
		setBackgroundColor(Color.WHITE);
	}

	public int getHeadHeight()
	{
		return headHeight;
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		headHeight=textView.getMeasuredHeight();
	}

	private class MyHolder extends RecyclerView.ViewHolder
	{
		Button button;

		public MyHolder(View itemView)
		{
			super(itemView);
			button = itemView.findViewById(R.id.button);
		}
	}

	private class Myadapter extends RecyclerView.Adapter<MyHolder>
	{
		LayoutInflater inflater;

		public Myadapter(Context context)
		{
			inflater = LayoutInflater.from(context);
		}

		@Override
		public MyHolder onCreateViewHolder(ViewGroup parent, int viewType)
		{

			MyHolder holder = new MyHolder(inflater.inflate(R.layout.menu_item, parent, false));
			return holder;
		}

		@Override
		public void onBindViewHolder(final MyHolder holder, final int position)
		{
			holder.button.setText(data[position]);
			holder.button.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					//holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),listdata.get(position).activity));
				}
			});

		}

		@Override
		public int getItemCount()
		{
			return data.length;
		}
	}

	static String[] data = {"test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10", "test11", "test12", "test13", "test14", "test15"};
}
