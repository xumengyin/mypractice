package com.example.xumengyin.mypractice.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.xumengyin.mypractice.R;

import java.util.ArrayList;
import java.util.List; 

public class NotificationUtils
{

    private static final String NOTIFICATION_TITLE = "notification_title";
    public static final int INVALID_COLOR = -1; // 无效颜色
    private static int notificationTitleColor = INVALID_COLOR; // 获取到的颜色缓存
    static String CHANEL="chanel";
    public static boolean isDarkNotificationTheme(Context context) {
        return !isSimilarColor(Color.BLACK, getNotificationColor(context));
    }

    private static boolean isSimilarColor(int baseColor, int color) {
        int simpleBaseColor = baseColor | 0xff000000;
        int simpleColor = color | 0xff000000;
        int baseRed = Color.red(simpleBaseColor) - Color.red(simpleColor);
        int baseGreen = Color.green(simpleBaseColor) - Color.green(simpleColor);
        int baseBlue = Color.blue(simpleBaseColor) - Color.blue(simpleColor);
        double value = Math.sqrt(baseRed * baseRed + baseGreen * baseGreen + baseBlue * baseBlue);
        if (value < 180.0) {
            return true;
        }
        return false;
    }

    /**
     * 获取系统通知栏主标题颜色，根据Activity继承自AppCompatActivity或FragmentActivity采取不同策略。
     *
     * @param context 上下文环境
     * @return 系统主标题颜色
     */
    public static int getNotificationColor(Context context) {
        try {
            if (notificationTitleColor == INVALID_COLOR) {
                if (context instanceof AppCompatActivity) {
                    notificationTitleColor = getNotificationColorCompat(context);
                } else {
                    notificationTitleColor = getNotificationColorInternal(context);
                }
            }
        } catch (Exception ignored) {

        }
        return notificationTitleColor;
    }


    /**
     * 通过一个空的Notification拿到Notification.contentView，通过{@link RemoteViews#apply(Context, ViewGroup)}方法返回通知栏消息根布局实例。
     *
     * @param context 上下文
     * @return 系统主标题颜色
     */
    private static int getNotificationColorInternal(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(NOTIFICATION_TITLE);
        Notification notification = builder.build();
        try {
            ViewGroup root = (ViewGroup) notification.contentView.apply(context, new FrameLayout(context));
            TextView titleView = (TextView) root.findViewById(android.R.id.title);
            if (null == titleView) {
                iteratorView(root, new Filter() {
                    @Override
                    public void filter(View view) {
                        if (view instanceof TextView) {
                            TextView textView = (TextView) view;
                            if (NOTIFICATION_TITLE.equals(textView.getText().toString())) {
                                notificationTitleColor = textView.getCurrentTextColor();
                            }
                        }
                    }
                });
                return notificationTitleColor;
            } else {
                return titleView.getCurrentTextColor();
            }
        } catch (Exception e) {
            return getNotificationColorCompat(context);
        }
    }

    /**
     * 使用getNotificationColorInternal()方法，Activity不能继承自AppCompatActivity（实测5.0以下机型可以，5.0及以上机型不行），
     * 大致的原因是默认通知布局文件中的ImageView（largeIcon和smallIcon）被替换成了AppCompatImageView，
     * 而在5.0及以上系统中，AppCompatImageView的setBackgroundResource(int)未被标记为RemotableViewMethod，导致apply时抛异常。
     *
     * @param context 上下文
     * @return 系统主标题颜色
     */
    private static int getNotificationColorCompat(Context context) {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//            builder.setContentText("test");
//            builder.setContentTitle("test");
//            builder.setStyle(new NotificationCompat.InboxStyle());
            Notification notification = builder.build();
            int layoutId = notification.contentView.getLayoutId();
            ViewGroup root = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null);
            TextView titleView = (TextView) root.findViewById(android.R.id.title);
            if (null == titleView) {
                return getTitleColorIteratorCompat(root);
            } else {
                return titleView.getCurrentTextColor();
            }
        } catch (Exception e) {
        }
        return INVALID_COLOR;
    }

    private static void iteratorView(View view, Filter filter) {
        if (view == null || filter == null) {
            return;
        }
        filter.filter(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                iteratorView(child, filter);
            }
        }
    }

    private static int getTitleColorIteratorCompat(View view) {
        if (view == null) {
            return INVALID_COLOR;
        }
        List<TextView> textViews = getAllTextViews(view);
        int maxTextSizeIndex = findMaxTextSizeIndex(textViews);
        if (maxTextSizeIndex != Integer.MIN_VALUE) {
            return textViews.get(maxTextSizeIndex).getCurrentTextColor();
        }
        return INVALID_COLOR;
    }

    private static int findMaxTextSizeIndex(List<TextView> textViews) {
        float max = Integer.MIN_VALUE;
        int maxIndex = Integer.MIN_VALUE;
        int index = 0;
        for (TextView textView : textViews) {
            if (max < textView.getTextSize()) {
                // 找到字号最大的字体，默认把它设置为主标题字号大小
                max = textView.getTextSize();
                maxIndex = index;
            }
            index++;
        }
        return maxIndex;
    }

    /**
     * 实现遍历View树中的TextView，返回包含TextView的集合。
     *
     * @param root 根节点
     * @return 包含TextView的集合
     */
    private static List<TextView> getAllTextViews(View root) {
        final List<TextView> textViews = new ArrayList<>();
        iteratorView(root, new Filter() {
            @Override
            public void filter(View view) {
                if (view instanceof TextView) {
                    textViews.add((TextView) view);
                }
            }
        });
        return textViews;
    }

    private interface Filter {
        void filter(View view);
    }


    public static void pushNotification(Context context, int notificationId, PendingIntent pendingIntent, String title, String content, String channelID, String channelName) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelID);
        }

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        Notification notification = notificationBuilder.build();

        notificationManager.notify(notificationId, notification);
    }

    public static Notification pushNotification(Context context, int notificationId, PendingIntent pendingIntent, String channelID, String channelName, RemoteViews remoteViews) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(channelID);
        }

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContent(remoteViews);

        Notification notification = notificationBuilder.build();

        notificationManager.notify(notificationId, notification);
        return notification;
    }

    public static void pushNotification(Context context, int notificationId, PendingIntent pendingIntent, String title, String content) {
        String chanelName = CHANEL;
        String chanelId = "1";
        pushNotification(context, notificationId, pendingIntent, title, content, chanelId, chanelName);
    }

    public static Notification pushNotification(Context context, int notificationId, PendingIntent pendingIntent, RemoteViews remoteViews) {
        String chanelName =CHANEL;
        String chanelId = "1";
        return pushNotification(context, notificationId, pendingIntent, chanelId, chanelName, remoteViews);
    }

    NotificationManager notificationManager;
    public NotificationCompat.Builder pushProgressNotification(Context context, int notificationId, PendingIntent pendingIntent,String title,String content) {
        String chanelName =CHANEL;
        String chanelId = "1";
        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(chanelId, chanelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            notificationBuilder.setChannelId(chanelId);
        }

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        notificationBuilder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText(content)
                .setContentTitle(title);
        return notificationBuilder;
    }
    public void updateNotification(NotificationCompat.Builder buidBuilder,int notificationId,int max,int progress,boolean indeterminate)
    {
        buidBuilder.setProgress(max,progress,indeterminate);
        notificationManager.notify(notificationId,buidBuilder.build());
    }
}
