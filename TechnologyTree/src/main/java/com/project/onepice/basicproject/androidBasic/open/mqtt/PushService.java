package com.project.onepice.basicproject.androidBasic.open.mqtt;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.ibm.mqtt.IMqttClient;
import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttPersistence;
import com.ibm.mqtt.MqttPersistenceException;
import com.ibm.mqtt.MqttSimpleCallback;
import com.project.onepice.basicproject.MainActivity;
import com.project.onepice.basicproject.R;
public class PushService extends Service {

    /**
     * PendingIntent flag说明：
     * <p>
     * FLAG_CANCEL_CURRENT:如果当前系统中已经存在一个相同的PendingIntent对象，那么就将先将已有的PendingIntent取消，然后重新生成一个PendingIntent对象。
     * <p>
     * FLAG_NO_CREATE:如果当前系统中不存在相同的PendingIntent对象，系统将不会创建该PendingIntent对象而是直接返回null。
     * <p>
     * FLAG_ONE_SHOT:该PendingIntent只作用一次。
     * <p>
     * FLAG_UPDATE_CURRENT:如果系统中已存在该PendingIntent对象，那么系统将保留该PendingIntent对象，
     * 但是会使用新的Intent来更新之前PendingIntent中的Intent对象数据，例如更新Intent中的Extras
     * <p>
     * PendingInget 创建说明:
     * <p>
     * PendingIntent.getActivity (context, requestCode, broadIntent, flags)
     * <p>
     * PendingIntent.getBroadcast(context,requestCode, broadIntent, flags)
     * <p>
     * PendingIntent.getService (context, requestCode, broadIntent, flags)
     * <p>
     * PendingIntent 主要使用的地方包括:
     * 通知Notificatio的发送，短消息SmsManager的发送和警报器AlarmManager的执行等等。
     */

    public final String TAG = PushService.class.getName();

    /**
     * client_id 服务端依据这个id表示是哪些客户端在请求网络
     **/
    public static String MQTT_CLIENT_ID = "ONEPICE";
    /***
     * MqttPersistence mqtt的一个管理持久类
     */
    private static MqttPersistence MQTT_PERSISTENCE = null;

    /**
     * @ACTION_START 开始MQTT连接
     * @ACTION_STOP 停止MQTT连接
     * @ACTION_KEEPALIVE 保持MQTT连接
     * @ACTION_RECONNECT 重新MQTT连接
     */
    private static final String ACTION_START = MQTT_CLIENT_ID + ".START";
    private static final String ACTION_STOP = MQTT_CLIENT_ID + ".STOP";
    private static final String ACTION_KEEPALIVE = MQTT_CLIENT_ID + ".KEEP_ALIVE";
    private static final String ACTION_RECONNECT = MQTT_CLIENT_ID + ".RECONNECT";

    private static final String MQTT_HOST = "209.124.50.174";
    private static int MQTT_BROKER_PORT_NUM = 1883;
    private final static String USER_NAME = "admin";
    private final static String PASSWORD = "password";
    private final static String SHARE_TOPIC_DEFAULT = "CLIENT-MESSAGE.MQTT";

    private static boolean MQTT_CLEAN_START = true;

    private static short MQTT_KEEP_ALIVE = 60 * 15;

    private static int[] MQTT_QUALITIES_OF_SERVICE = {0};

    private static final long KEEP_ALIVE_INTERVAL = 1000 * 60 * 28;

    private static int MQTT_QUALITY_OF_SERVICE = 0;

    private static boolean MQTT_RETAINED_PUBLISH = false;

    private NotificationManager mNotifMan;

    String deviceId = "D0T6HIjiQzLbqddF";

    private MQTTConnection mConnection;

    private ConnectivityManager mConnMan;
    /**
     * 检测当前mqtt服务连接状态
     */
    private boolean mStarted;

    @Override
    public void onCreate() {
        super.onCreate();
        mConnMan = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        mNotifMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart...");
        super.onStart(intent, startId);

        if (intent.getAction().equals(ACTION_STOP)) {
            stop();
            stopSelf();//停止服务
        } else if (intent.getAction().equals(ACTION_START)) {
            start();
        } else if (intent.getAction().equals(ACTION_KEEPALIVE)) {
            keepAlive();
        } else if (intent.getAction().equals(ACTION_RECONNECT)) {
            if (isNetworkAvailable()) {
                reconnectIfNecessary();
            }
        }
    }

    // Static method to stop the service
    public static void actionStop(Context ctx) {
        Intent i = new Intent(ctx, PushService.class);
        i.setAction(ACTION_STOP);
        ctx.startService(i);
    }

    // Static method to start the service
    public static void actionStart(Context ctx) {
        Intent i = new Intent(ctx, PushService.class);
        i.setAction(ACTION_START);
        ctx.startService(i);
    }

    private synchronized void stop() {
        unregisterReceiver(mConnectivityChanged);
        cancelReconnect();
        if (mConnection != null) {
            mConnection.disconnect();
            mConnection = null;
        }
    }

    /**
     * 启动服务，连接mqtt
     */
    private synchronized void start() {
        Log.i(TAG, "start...");
        //如果服务已经运行了，那就什么都不做
        if (mStarted) {
            Log.i(TAG, "mqtt服务正在使用中...");
            return;
        }
        connect();

        registerReceiver(mConnectivityChanged, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private synchronized void keepAlive() {
        try {
            if (mStarted == true && mConnection != null) {
                mConnection.sendKeepAlive();
            }
        } catch (MqttException e) {

            mConnection.disconnect();
            mConnection = null;
            cancelReconnect();
        }
    }


    /**
     * 创建mqtt连接服务
     */
    private synchronized void connect() {
        Log.i(TAG, "mqtt start connect");
        //deviceId 是客服端和服务器之间沟通的表示，服务器通过deviceId来标记客户端的相应服务
        //左右类似于tokenId等deviceId，且deviceId必须是服务器返回
        mConnection = new MQTTConnection(MQTT_HOST, deviceId);
        if (isNetworkAvailable()) {
            scheduleReconnect(mStarted);
        }
    }

    private void scheduleReconnect(boolean mStarted) {

        // Calculate the elapsed time since the start
        long now = System.currentTimeMillis();
        long elapsed = now;

        Intent i = new Intent();
        i.setClass(this, PushService.class);
        i.setAction(ACTION_RECONNECT);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, now, pi);
    }

    private BroadcastReceiver mConnectivityChanged = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkInfo info = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            boolean hasConnectivity = (info != null && info.isConnected()) ? true : false;
            Log.i(TAG, "Connectivity changed: connected=" + hasConnectivity);

            if (hasConnectivity) {
                reconnectIfNecessary();
            } else if (mConnection != null) {
                // if there no connectivity, make sure MQTT connection is destroyed
                mConnection.disconnect();
                cancelReconnect();
                mConnection = null;
            }
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand...");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MQTTConnection implements MqttSimpleCallback {
        IMqttClient mqttClient = null;

        public MQTTConnection(String brokeHostName, String initTopic) {
            String mqttconnSpec = "tcp://" + brokeHostName + "@" + MQTT_BROKER_PORT_NUM;
            try {
                mqttClient = MqttClient.createMqttClient(mqttconnSpec, MQTT_PERSISTENCE);
                mqttClient.connect(initTopic, MQTT_CLEAN_START, MQTT_KEEP_ALIVE);
                subscribeToTopic(initTopic);
                Log.i(TAG, "Connection established to " + brokeHostName + " on topic" + initTopic);
                startKeepAlives();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void connectionLost() throws Exception {
            //连接丢失
            Log.i(TAG, "Loss of connection" + "connection downed");
            stopKeepAlives();
            // null itself
            mConnection = null;
            if (isNetworkAvailable() == true) {
                reconnectIfNecessary();
            }
        }


        @Override
        public void publishArrived(String s, byte[] bytes, int i, boolean b) throws Exception {
            //推送的消息到达
            // Show a notification
            String sS = new String(bytes);
            showNotification(sS);
            Log.i(TAG, "Got message: " + s);
        }


        private void subscribeToTopic(String initTopic) throws MqttException {
            if ((mqttClient == null) || (mqttClient.isConnected() == false)) {
                System.out.println("mqttClient error No connection");
            } else {
                String[] topics = {initTopic};
                mqttClient.subscribe(topics, MQTT_QUALITIES_OF_SERVICE);
            }
        }

        public void sendKeepAlive() throws MqttException {
            // publish to a keep-alive topic
            publishToTopic(MQTT_CLIENT_ID + "/keepalive", deviceId);
        }

        private void publishToTopic(String topicName, String message) throws MqttException {
            if ((mqttClient == null) || (mqttClient.isConnected() == false)) {

                Log.i(TAG, "No connection to public to");
            } else {
                mqttClient.publish(topicName,
                        message.getBytes(),
                        MQTT_QUALITY_OF_SERVICE,
                        MQTT_RETAINED_PUBLISH);
            }
        }

        // Disconnect
        public void disconnect() {
            try {
                stopKeepAlives();
                mqttClient.disconnect();
            } catch (MqttPersistenceException e) {
                Log.i("MqttException" + (e.getMessage() != null ? e.getMessage() : " NULL"), e.toString());
            }
        }

    }

    private void startKeepAlives() {
        Intent i = new Intent();
        i.setClass(this, PushService.class);
        i.setAction(ACTION_KEEPALIVE);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + KEEP_ALIVE_INTERVAL,
                KEEP_ALIVE_INTERVAL, pi);
    }

    // Remove all scheduled keep alives
    private void stopKeepAlives() {
        Intent i = new Intent();
        i.setClass(this, PushService.class);
        i.setAction(ACTION_KEEPALIVE);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmMgr.cancel(pi);
    }

    // Remove the scheduled reconnect
    public void cancelReconnect() {
        Intent i = new Intent();
        i.setClass(this, PushService.class);
        i.setAction(ACTION_RECONNECT);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        AlarmManager alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmMgr.cancel(pi);
    }

    // Check if we are online
    private boolean isNetworkAvailable() {
        NetworkInfo info = mConnMan.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        return info.isConnected();
    }

    private synchronized void reconnectIfNecessary() {
        if (mStarted == true && mConnection == null) {
            Log.i(TAG, "Reconnecting...");
            connect();
        }
    }


    // Display the topbar notification
    private void showNotification(String text) {
//        Notification n = new Notification();
//
//        n.flags |= Notification.FLAG_SHOW_LIGHTS;
//        n.flags |= Notification.FLAG_AUTO_CANCEL;
//
//        n.defaults = Notification.DEFAULT_ALL;
//
//        n.icon = R.mipmap.ic_launcher;
//        n.when = System.currentTimeMillis();
//
//        // Simply open the parent activity
//        PendingIntent pi = PendingIntent.getActivity(this, 0,
//                new Intent(this, MainActivity.class), 0);
//
//        // Change the name of the notification here
////        n.setLatestEventInfo(this, "", text, pi);
//        mNotifMan.notify(0, n);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("notification push");

        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);

        /**
         *
         * 以往在notification中我们都是通过paddingIntent启动APP的activity，当用户想退出通过消息打开的activity时，APP回退到的
         * 是APP主页
         *
         * 用TaskStackBuilder管理intent ，进行界面回退的时候回退到的是手机主界面
         * */

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent reslutPadingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(reslutPadingIntent);

        NotificationManager mNotificationManger = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManger.notify(1000,mBuilder.build());


    }

}