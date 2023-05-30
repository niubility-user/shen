package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class CommsReceiver implements Runnable {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private ClientComms clientComms;
    private ClientState clientState;
    private MqttInputStream in;
    private volatile boolean receiving;
    private CommsTokenStore tokenStore;
    private boolean running = false;
    private Object lifecycle = new Object();
    private Thread recThread = null;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsReceiver");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public CommsReceiver(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, InputStream inputStream) {
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.in = new MqttInputStream(clientState, inputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = commsTokenStore;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    public boolean isReceiving() {
        return this.receiving;
    }

    public boolean isRunning() {
        return this.running;
    }

    @Override // java.lang.Runnable
    public void run() {
        MqttToken mqttToken = null;
        while (this.running && this.in != null) {
            try {
                try {
                    try {
                        log.fine(CLASS_NAME, "run", "852");
                        this.receiving = this.in.available() > 0;
                        MqttWireMessage readMqttWireMessage = this.in.readMqttWireMessage();
                        this.receiving = false;
                        if (readMqttWireMessage instanceof MqttAck) {
                            mqttToken = this.tokenStore.getToken(readMqttWireMessage);
                            if (mqttToken != null) {
                                synchronized (mqttToken) {
                                    this.clientState.notifyReceivedAck((MqttAck) readMqttWireMessage);
                                }
                            } else {
                                throw new MqttException(6);
                            }
                        } else {
                            this.clientState.notifyReceivedMsg(readMqttWireMessage);
                        }
                    } catch (MqttException e2) {
                        log.fine(CLASS_NAME, "run", "856", null, e2);
                        this.running = false;
                        this.clientComms.shutdownConnection(mqttToken, e2);
                    }
                } catch (IOException e3) {
                    log.fine(CLASS_NAME, "run", "853");
                    this.running = false;
                    if (!this.clientComms.isDisconnecting()) {
                        this.clientComms.shutdownConnection(mqttToken, new MqttException(32109, e3));
                    }
                }
            } finally {
                this.receiving = false;
            }
        }
        log.fine(CLASS_NAME, "run", "854");
    }

    public void start(String str) {
        log.fine(CLASS_NAME, "start", "855");
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.running = true;
                Thread thread = new Thread(this, str);
                this.recThread = thread;
                thread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            log.fine(CLASS_NAME, "stop", "850");
            if (this.running) {
                this.running = false;
                this.receiving = false;
                if (!Thread.currentThread().equals(this.recThread)) {
                    try {
                        this.recThread.join();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        this.recThread = null;
        log.fine(CLASS_NAME, "stop", "851");
    }
}
