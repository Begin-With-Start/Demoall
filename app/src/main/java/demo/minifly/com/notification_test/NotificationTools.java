package demo.minifly.com.notification_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import demo.minifly.com.R;

/**
 * author ：minifly
 * date: 2017/6/16
 * time: 09:18
 * desc:
 */
public class NotificationTools {

    private static NotificationTools mNotification;
    private int i = 0;
    private Context mContext = null;
    private NotificationManager manager = null;
    private NotificationCompat.Builder builder = null;


    static NotificationTools getInstance(Context context) {
        if (null == mNotification) {
            synchronized (NotificationTools.class) {
                if (null == mNotification) {
                    mNotification = new NotificationTools(context);
                }
            }
        }
        return mNotification;
    }

    private NotificationTools(Context context) {
        this.mContext = context;
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_ok)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_ok))
                .setDefaults(Notification.DEFAULT_ALL);
    }

    /**
     * @param title   标题
     * @param content 内容
     */
    void sendNotification(String title, String content) {
        Intent intent = new Intent(mContext, NotificationViewActivity.class);
        intent.putExtra("content", "通知过来的消息");
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setFullScreenIntent(pendingIntent, false)
                .setContentTitle(title)
                .setContentText(content);
        manager.notify(1, builder.build());
    }


    public void sendNotification() {
        Intent intent = new Intent(mContext, NotificationViewActivity.class);
        intent.putExtra("content", "通知过来的消息");
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle("通知")
                .setContentText("通知内容")
                .setContentIntent(pendingIntent)
                .setFullScreenIntent(pendingIntent, false);
        manager.notify(1, builder.build());
    }


    /**
     * 自定义Notification
     *
     * @param text    通知内容
     * @param clazz   跳转的页面
     */
    void sendCustomNotification(String text, Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtra("content", "sendCustomNotification");
        int currentI = i++;
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, currentI, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews mRemoteView = new RemoteViews(mContext.getPackageName(), R.layout.layout_notification_latyou);
        mRemoteView.setTextViewText(R.id.tv_text, text);
        mRemoteView.setImageViewResource(R.id.iv_icon, R.drawable.icon_ok);
        builder.setContent(mRemoteView)
                .setAutoCancel(true)
//                .setContentIntent(pendingIntent);
                .setFullScreenIntent(pendingIntent, true);
        manager.notify(currentI, builder.build());
//        pendingIntent.cancel();
    }

}
