package org.eclipse.paho.client.mqttv3.internal;

import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class CommsSender implements Runnable {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private ClientComms clientComms;
    private ClientState clientState;
    private MqttOutputStream out;
    private CommsTokenStore tokenStore;
    private boolean running = false;
    private Object lifecycle = new Object();
    private Thread sendThread = null;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsSender");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public CommsSender(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, OutputStream outputStream) {
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.out = new MqttOutputStream(clientState, outputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = commsTokenStore;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    private void handleRunException(MqttWireMessage mqttWireMessage, Exception exc) {
        MqttException mqttException;
        log.fine(CLASS_NAME, "handleRunException", JDNetworkConstant.DOMAIN_REQUEST_EXP_ERRCODE, null, exc);
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        this.running = false;
        this.clientComms.shutdownConnection(null, mqttException);
    }

    @Override // java.lang.Runnable
    public void run() {
        MqttWireMessage mqttWireMessage = null;
        while (this.running && this.out != null) {
            try {
                mqttWireMessage = this.clientState.get();
                if (mqttWireMessage != null) {
                    log.fine(CLASS_NAME, "run", JDNetworkConstant.HTTPDNS_EXP_ERRCODE, new Object[]{mqttWireMessage.getKey(), mqttWireMessage});
                    if (mqttWireMessage instanceof MqttAck) {
                        this.out.write(mqttWireMessage);
                        this.out.flush();
                    } else {
                        MqttToken token = this.tokenStore.getToken(mqttWireMessage);
                        if (token != null) {
                            synchronized (token) {
                                this.out.write(mqttWireMessage);
                                try {
                                    this.out.flush();
                                } catch (IOException e2) {
                                    if (!(mqttWireMessage instanceof MqttDisconnect)) {
                                        throw e2;
                                        break;
                                    }
                                }
                                this.clientState.notifySent(mqttWireMessage);
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    log.fine(CLASS_NAME, "run", JDNetworkConstant.IP_REQUEST_EXP_ERRCODE);
                    this.running = false;
                }
            } catch (MqttException e3) {
                handleRunException(mqttWireMessage, e3);
            } catch (Exception e4) {
                handleRunException(mqttWireMessage, e4);
            }
        }
        log.fine(CLASS_NAME, "run", "805");
    }

    public void start(String str) {
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.running = true;
                Thread thread = new Thread(this, str);
                this.sendThread = thread;
                thread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            log.fine(CLASS_NAME, "stop", "800");
            if (this.running) {
                this.running = false;
                if (!Thread.currentThread().equals(this.sendThread)) {
                    while (this.sendThread.isAlive()) {
                        try {
                            this.clientState.notifyQueueLock();
                            this.sendThread.join(100L);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            }
            this.sendThread = null;
            log.fine(CLASS_NAME, "stop", "801");
        }
    }
}
