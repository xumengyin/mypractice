package com.example.xumengyin.mypractice.testactivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RemoteViews;
 
import com.eftimoff.androipathview.PathView;
import com.example.xumengyin.mypractice.R;
import com.example.xumengyin.mypractice.util.NotificationUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationActivity extends BaseActivity
{
	@BindView(R.id.normal_btn)
	Button normalBtn;
	@BindView(R.id.custom_btn)
	Button customBtn;
	@BindView(R.id.pathView)
	PathView pathView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		pathView.useNaturalColors();
		pathView.setFillAfter(true);

	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_notification;
	}

	@OnClick(R.id.normal_btn)
	public void normal()
	{
		Intent notificationIntent = new Intent();
		PendingIntent mContentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		NotificationUtils.pushNotification(this, 1, mContentIntent, "haha", "content");
	}

	@OnClick(R.id.custom_btn)
	public void custom()
	{
		RemoteViews contentView;
		boolean isBoolean = NotificationUtils.isDarkNotificationTheme(this);
		if (isBoolean)
		{
			contentView = new RemoteViews(this.getPackageName(),
					R.layout.notification_white);
		} else
		{
			contentView = new RemoteViews(this.getPackageName(),
					R.layout.notification_black);
		}
		Intent notificationIntent = new Intent();
		PendingIntent mContentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		NotificationUtils.pushNotification(this, 1, mContentIntent, contentView);
	}

	@OnClick(R.id.progress_btn)
	public void progress_btn()
	{

		Intent notificationIntent = new Intent();
		PendingIntent mContentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		final NotificationUtils utils = new NotificationUtils();
		final NotificationCompat.Builder b = utils.pushProgressNotification(this, 1, mContentIntent, "progress", "content");
		final NotificationCompat.Builder b2 = utils.pushProgressNotification(this, 2, mContentIntent, "progress", "content");
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						utils.updateNotification(b, 1, 100, i, false);
						utils.updateNotification(b2, 2, 0, 0, true);
						Thread.sleep(500);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}

				}
			}
		}).start();

	}

	@OnClick(R.id.pathView)
	public void pathview()
	{

		pathView.getPathAnimator().
				//pathView.getSequentialPathAnimator().
						delay(100).
				duration(1500).
				interpolator(new AccelerateDecelerateInterpolator()).
				start();
	}
}
