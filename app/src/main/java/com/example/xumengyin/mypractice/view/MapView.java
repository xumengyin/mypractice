package com.example.xumengyin.mypractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.xumengyin.mypractice.R;
import com.example.xumengyin.mypractice.bean.Province; 

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MapView extends View
{
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	List<Province> dataList = new ArrayList<>();
	Province selectP;
	float scale = 1;

	public MapView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//	{
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		//int width = MeasureSpec.getSize(widthMeasureSpec);
//		if (!dataList.isEmpty()&&width>0)
//		{
//			float dataWidth=getProvinceWidth();
//			scale=width/dataWidth;
//			invalidate();
//		}
//	}
	int width;

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		if (w > 0)
		{
			Log.d("haha", "onSizeChanged");
			width = w;

		}
	}

	private float getProvinceWidth()
	{
		float left = Integer.MAX_VALUE, right = 0;
		for (Province province : dataList)
		{
			if (province.rectF.left < left)
			{
				left = province.rectF.left;
			}
			if (province.rectF.right > right)
			{
				right = province.rectF.right;
			}
		}
		return right - left;
	}

	private void init(final Context context)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				InputStream stream = context.getResources().openRawResource(R.raw.chinahigh2);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				try
				{
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document document = builder.parse(stream);
					Element root = document.getDocumentElement();
					NodeList listg = root.getElementsByTagName("g");
					NodeList lists = ((Element) (listg.item(0))).getElementsByTagName("path");
					for (int i = 0; i < lists.getLength(); i++)
					{
						Element node = (Element) lists.item(i);
						String pathdata = node.getAttribute("d");
						String title = node.getAttribute("title");
						Province p = new Province(PathParser.createPathFromPathData(pathdata), title);
						p.setColor(Color.RED);
						dataList.add(p);
					}
					//requestLayout();
					float dataWidth = getProvinceWidth();
					scale = width / dataWidth;
					postInvalidate();
					Log.d("haha", "postInvalidate");
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		Log.d("haha", "ondraw:" + dataList.size());
		super.onDraw(canvas);
		if (!dataList.isEmpty())
		{
			canvas.save();
			canvas.scale(scale, scale);
			for (int i = 0; i < dataList.size(); i++)
			{
				if (selectP != null && dataList.get(i) == selectP)
					dataList.get(i).draw(canvas, paint, true);
				else
					dataList.get(i).draw(canvas, paint, false);

			}
			canvas.restore();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if((event.getAction()&MotionEvent.ACTION_MASK)==MotionEvent.ACTION_DOWN)
		{
			for (int i = 0; i < dataList.size(); i++)
			{
				if (dataList.get(i).isContain(event.getX()/scale, event.getY()/scale))
				{
					selectP = dataList.get(i);
					invalidate();
					break;
				}

			}
		}

		return true;
	}
}
