package org.eclipse.paho.client.mqttv3.internal;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class DisconnectedMessageBuffer implements Runnable {
    private static final String CLASS_NAME = "DisconnectedMessageBuffer";
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private Object bufLock = new Object();
    private ArrayList buffer = new ArrayList();
    private DisconnectedBufferOptions bufferOpts;
    private IDisconnectedBufferCallback callback;

    public DisconnectedMessageBuffer(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.bufferOpts = disconnectedBufferOptions;
    }

    public void deleteMessage(int i2) {
        synchronized (this.bufLock) {
            this.buffer.remove(i2);
        }
    }

    public BufferedMessage getMessage(int i2) {
        BufferedMessage bufferedMessage;
        synchronized (this.bufLock) {
            bufferedMessage = (BufferedMessage) this.buffer.get(i2);
        }
        return bufferedMessage;
    }

    public int getMessageCount() {
        int size;
        synchronized (this.bufLock) {
            size = this.buffer.size();
        }
        return size;
    }

    public void putMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        BufferedMessage bufferedMessage = new BufferedMessage(mqttWireMessage, mqttToken);
        synchronized (this.bufLock) {
            if (this.buffer.size() < this.bufferOpts.getBufferSize()) {
                this.buffer.add(bufferedMessage);
            } else if (this.bufferOpts.isDeleteOldestMessages()) {
                this.buffer.remove(0);
                this.buffer.add(bufferedMessage);
            } else {
                throw new MqttException(32203);
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        log.fine(CLASS_NAME, "run", "516");
        while (getMessageCount() > 0) {
            try {
                this.callback.publishBufferedMessage(getMessage(0));
                deleteMessage(0);
            } catch (MqttException unused) {
                log.warning(CLASS_NAME, "run", "517");
                return;
            }
        }
    }

    public void setPublishCallback(IDisconnectedBufferCallback iDisconnectedBufferCallback) {
        this.callback = iDisconnectedBufferCallback;
    }
}
