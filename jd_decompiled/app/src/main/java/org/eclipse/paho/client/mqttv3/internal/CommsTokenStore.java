package org.eclipse.paho.client.mqttv3.internal;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class CommsTokenStore {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private MqttException closedResponse = null;
    private String logContext;
    private Hashtable tokens;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsTokenStore");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public CommsTokenStore(String str) {
        Logger logger = log;
        logger.setResourceName(str);
        this.tokens = new Hashtable();
        this.logContext = str;
        logger.fine(CLASS_NAME, "<Init>", "308");
    }

    public void clear() {
        log.fine(CLASS_NAME, "clear", "305", new Object[]{new Integer(this.tokens.size())});
        synchronized (this.tokens) {
            this.tokens.clear();
        }
    }

    public int count() {
        int size;
        synchronized (this.tokens) {
            size = this.tokens.size();
        }
        return size;
    }

    public MqttDeliveryToken[] getOutstandingDelTokens() {
        MqttDeliveryToken[] mqttDeliveryTokenArr;
        synchronized (this.tokens) {
            log.fine(CLASS_NAME, "getOutstandingDelTokens", "311");
            Vector vector = new Vector();
            Enumeration elements = this.tokens.elements();
            while (elements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) elements.nextElement();
                if (mqttToken != null && (mqttToken instanceof MqttDeliveryToken) && !mqttToken.internalTok.isNotified()) {
                    vector.addElement(mqttToken);
                }
            }
            mqttDeliveryTokenArr = (MqttDeliveryToken[]) vector.toArray(new MqttDeliveryToken[vector.size()]);
        }
        return mqttDeliveryTokenArr;
    }

    public Vector getOutstandingTokens() {
        Vector vector;
        synchronized (this.tokens) {
            log.fine(CLASS_NAME, "getOutstandingTokens", "312");
            vector = new Vector();
            Enumeration elements = this.tokens.elements();
            while (elements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) elements.nextElement();
                if (mqttToken != null) {
                    vector.addElement(mqttToken);
                }
            }
        }
        return vector;
    }

    public MqttToken getToken(MqttWireMessage mqttWireMessage) {
        return (MqttToken) this.tokens.get(mqttWireMessage.getKey());
    }

    public void open() {
        synchronized (this.tokens) {
            log.fine(CLASS_NAME, JshopConst.JSKEY_CATE_OPEN, "310");
            this.closedResponse = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void quiesce(MqttException mqttException) {
        synchronized (this.tokens) {
            log.fine(CLASS_NAME, "quiesce", "309", new Object[]{mqttException});
            this.closedResponse = mqttException;
        }
    }

    public MqttToken removeToken(MqttWireMessage mqttWireMessage) {
        if (mqttWireMessage != null) {
            return removeToken(mqttWireMessage.getKey());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MqttDeliveryToken restoreToken(MqttPublish mqttPublish) {
        MqttDeliveryToken mqttDeliveryToken;
        synchronized (this.tokens) {
            String num = new Integer(mqttPublish.getMessageId()).toString();
            if (this.tokens.containsKey(num)) {
                mqttDeliveryToken = (MqttDeliveryToken) this.tokens.get(num);
                log.fine(CLASS_NAME, "restoreToken", "302", new Object[]{num, mqttPublish, mqttDeliveryToken});
            } else {
                mqttDeliveryToken = new MqttDeliveryToken(this.logContext);
                mqttDeliveryToken.internalTok.setKey(num);
                this.tokens.put(num, mqttDeliveryToken);
                log.fine(CLASS_NAME, "restoreToken", "303", new Object[]{num, mqttPublish, mqttDeliveryToken});
            }
        }
        return mqttDeliveryToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void saveToken(MqttToken mqttToken, MqttWireMessage mqttWireMessage) throws MqttException {
        synchronized (this.tokens) {
            MqttException mqttException = this.closedResponse;
            if (mqttException == null) {
                String key = mqttWireMessage.getKey();
                log.fine(CLASS_NAME, "saveToken", "300", new Object[]{key, mqttWireMessage});
                saveToken(mqttToken, key);
            } else {
                throw mqttException;
            }
        }
    }

    public String toString() {
        String stringBuffer;
        String property = System.getProperty("line.separator", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        StringBuffer stringBuffer2 = new StringBuffer();
        synchronized (this.tokens) {
            Enumeration elements = this.tokens.elements();
            while (elements.hasMoreElements()) {
                StringBuffer stringBuffer3 = new StringBuffer("{");
                stringBuffer3.append(((MqttToken) elements.nextElement()).internalTok);
                stringBuffer3.append("}");
                stringBuffer3.append(property);
                stringBuffer2.append(stringBuffer3.toString());
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }

    public MqttToken removeToken(String str) {
        log.fine(CLASS_NAME, "removeToken", "306", new Object[]{str});
        if (str != null) {
            return (MqttToken) this.tokens.remove(str);
        }
        return null;
    }

    public MqttToken getToken(String str) {
        return (MqttToken) this.tokens.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void saveToken(MqttToken mqttToken, String str) {
        synchronized (this.tokens) {
            log.fine(CLASS_NAME, "saveToken", "307", new Object[]{str, mqttToken.toString()});
            mqttToken.internalTok.setKey(str);
            this.tokens.put(str, mqttToken);
        }
    }
}
