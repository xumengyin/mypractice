package com.example.xumengyin.mypractice.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

public class Province
{
  Path pathdata;
  int color;
  String title;
  public RectF rectF =new RectF();
  Region region =new Region();
  public Province(Path pathdata,String title)
  {
    this.title=title;
    this.pathdata = pathdata;
    pathdata.computeBounds(rectF,true);
    region.setPath(pathdata,new Region((int)rectF.left,(int)rectF.top,(int)rectF.right,(int)rectF.bottom));

  }

  public void setColor(int color)
  {
    this.color = color;
  }

  public void draw(Canvas canvas, Paint paint,boolean select)
  {
    if(select)
    {
      paint.setColor(Color.BLUE);
      paint.setStyle(Paint.Style.FILL);
      paint.setStrokeWidth(1);
      canvas.drawPath(pathdata, paint);

    }else
    {
      paint.setColor(color);
      paint.setStyle(Paint.Style.FILL);
      paint.setStrokeWidth(1);
      canvas.drawPath(pathdata, paint);


    }
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(0xFFD0E8F4);
    canvas.drawPath(pathdata, paint);
    paint.setColor(Color.BLACK);
    canvas.drawText(title,rectF.centerX(),rectF.centerY(),paint);

    Log.d("xuxux","title"+title+"--x:"+rectF.centerX()+"--y:"+rectF.centerY());
  }

  public boolean isContain(float x, float y)
  {
    return region.contains((int) x,(int)y);
  }
}
