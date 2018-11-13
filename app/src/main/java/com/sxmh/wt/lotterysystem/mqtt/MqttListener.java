package com.sxmh.wt.lotterysystem.mqtt;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MqttListener {

    /**
     * mqtt监听服务请求地址
     */
    public static final String MQTT_HOST_ADDR = "tcp://47.105.134.86:1883";
//    public static final String MQTT_HOST_ADDR = "tcp://172.16.160.48:1883";

    /**
     * mqtt监听服务使用的用户名
     */
    public static final String MQTT_USER_NAME = "admin";

    /**
     * mqtt监听服务使用的密码
     */
    public static final String MQTT_USER_PASSWD = "EC3E9EB0BEFE9453";
//    public static final String MQTT_USER_PASSWD = "admin";

    /**
     * mqtt监听服务连接成功
     */
    public static final int MQTT_CONNECT_SUCESSFUL = 4;
    /**
     * mqtt监听服务连接失败
     */
    public static final int MQTT_CONNECT_FAILED = 5;
    /**
     * mqtt监听服务收到消息
     */
    public static final int MQTT_GET_MESSAGE = 6;

    private final String TAG = "MqttListener";

    /**
     * 主题，不同的myTopic代表订阅不同的主题，并且只能接收该主题的推送内容
     */
    private String mMyTopic;
    /**
     * 客户端ID，用于区分不同的客户端，相同ID的客户端同时运行时将导致两个客户端因相互排挤而掉线
     */
    private String mClientID;

    private MqttClient mMqttClient;
    private MqttConnectOptions mMqttConnectOptions;
    private ScheduledExecutorService mScheduledExecutorServic;

    private Handler mHandler;

    public MqttListener(Handler handler, String clientId, String topic) {
        this.mHandler = handler;
        this.mClientID = "GID_video@@@" + clientId;
        this.mMyTopic = topic;
    }

    public void init() {
        try {
            mMqttClient = new MqttClient(MQTT_HOST_ADDR, mClientID,
                    new MemoryPersistence());
            mMqttConnectOptions = new MqttConnectOptions();
            mMqttConnectOptions.setCleanSession(true);
            mMqttConnectOptions.setUserName(MQTT_USER_NAME);
            mMqttConnectOptions.setPassword(MQTT_USER_PASSWD.toCharArray());
            mMqttConnectOptions.setConnectionTimeout(10);
            mMqttConnectOptions.setKeepAliveInterval(20);
            mMqttClient.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    Log.e(TAG, "---------connectionLost---------");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.e(TAG, "---------deliveryComplete:" + token.isComplete());
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    Message msg = new Message();
                    Log.e(TAG, "---------888888888888888888888888---------");
                    msg.what = MQTT_GET_MESSAGE;
                    msg.obj = message.toString();
                    mHandler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "---------init error---------");
        }
    }

    public void startReconnect() {
        mScheduledExecutorServic = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorServic.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!mMqttClient.isConnected()) {
                    connect();
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    private void connect() {
        try {
            mMqttClient.connect(mMqttConnectOptions);
            mMqttClient.subscribe(mMyTopic, 1);
            Message msg = new Message();
            msg.what = MQTT_CONNECT_SUCESSFUL;
            mHandler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = MQTT_CONNECT_FAILED;
            mHandler.sendMessage(msg);
        }
    }

    public void disconnect() throws MqttException {
        if (mMqttClient != null) {
            mMqttClient.disconnect();
        }
    }

    public void destory() throws MqttException {
        if (mMqttClient != null) {
            mScheduledExecutorServic.shutdown();
            mMqttClient.disconnect();
        }
    }
}

//    public static final String MQTT_HOST_ADDR = "tcp://172.16.160.48:1883";
