package org.eclipse.paho.client.mqttv3;

/* loaded from: classes11.dex */
public interface MqttCallback {
    void connectionLost(Throwable th);

    void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken);

    void messageArrived(String str, MqttMessage mqttMessage) throws Exception;
}
