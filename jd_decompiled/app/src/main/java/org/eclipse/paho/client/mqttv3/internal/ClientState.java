package org.eclipse.paho.client.mqttv3.internal;

import com.jd.framework.network.filedown.JDFileService;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class ClientState {
    private static final String CLASS_NAME;
    private static final int MAX_MSG_ID = 65535;
    private static final int MIN_MSG_ID = 1;
    private static final String PERSISTENCE_CONFIRMED_PREFIX = "sc-";
    private static final String PERSISTENCE_RECEIVED_PREFIX = "r-";
    private static final String PERSISTENCE_SENT_BUFFERED_PREFIX = "sb-";
    private static final String PERSISTENCE_SENT_PREFIX = "s-";
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private int actualInFlight;
    private CommsCallback callback;
    private boolean cleanSession;
    private ClientComms clientComms;
    private int inFlightPubRels;
    private Hashtable inUseMsgIds;
    private Hashtable inboundQoS2;
    private long keepAlive;
    private Hashtable outboundQoS0;
    private Hashtable outboundQoS1;
    private Hashtable outboundQoS2;
    private volatile Vector pendingFlows;
    private volatile Vector pendingMessages;
    private MqttClientPersistence persistence;
    private MqttWireMessage pingCommand;
    private MqttPingSender pingSender;
    private CommsTokenStore tokenStore;
    private int nextMsgId = 0;
    private int maxInflight = 0;
    private Object queueLock = new Object();
    private Object quiesceLock = new Object();
    private boolean quiescing = false;
    private long lastOutboundActivity = 0;
    private long lastInboundActivity = 0;
    private long lastPing = 0;
    private Object pingOutstandingLock = new Object();
    private int pingOutstanding = 0;
    private boolean connected = false;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientState");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public ClientState(MqttClientPersistence mqttClientPersistence, CommsTokenStore commsTokenStore, CommsCallback commsCallback, ClientComms clientComms, MqttPingSender mqttPingSender) throws MqttException {
        this.clientComms = null;
        this.callback = null;
        this.actualInFlight = 0;
        this.inFlightPubRels = 0;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.pingSender = null;
        Logger logger = log;
        logger.setResourceName(clientComms.getClient().getClientId());
        logger.finer(CLASS_NAME, "<Init>", "");
        this.inUseMsgIds = new Hashtable();
        this.pendingFlows = new Vector();
        this.outboundQoS2 = new Hashtable();
        this.outboundQoS1 = new Hashtable();
        this.outboundQoS0 = new Hashtable();
        this.inboundQoS2 = new Hashtable();
        this.pingCommand = new MqttPingReq();
        this.inFlightPubRels = 0;
        this.actualInFlight = 0;
        this.persistence = mqttClientPersistence;
        this.callback = commsCallback;
        this.tokenStore = commsTokenStore;
        this.clientComms = clientComms;
        this.pingSender = mqttPingSender;
        restoreState();
    }

    private void decrementInFlight() {
        synchronized (this.queueLock) {
            this.actualInFlight--;
            log.fine(CLASS_NAME, "decrementInFlight", "646", new Object[]{new Integer(this.actualInFlight)});
            if (!checkQuiesceLock()) {
                this.queueLock.notifyAll();
            }
        }
    }

    private synchronized int getNextMessageId() throws MqttException {
        int i2 = this.nextMsgId;
        int i3 = 0;
        do {
            int i4 = this.nextMsgId + 1;
            this.nextMsgId = i4;
            if (i4 > 65535) {
                this.nextMsgId = 1;
            }
            if (this.nextMsgId == i2 && (i3 = i3 + 1) == 2) {
                throw ExceptionHelper.createMqttException(32001);
            }
        } while (this.inUseMsgIds.containsKey(new Integer(this.nextMsgId)));
        Integer num = new Integer(this.nextMsgId);
        this.inUseMsgIds.put(num, num);
        return this.nextMsgId;
    }

    private String getReceivedPersistenceKey(MqttWireMessage mqttWireMessage) {
        StringBuffer stringBuffer = new StringBuffer(PERSISTENCE_RECEIVED_PREFIX);
        stringBuffer.append(mqttWireMessage.getMessageId());
        return stringBuffer.toString();
    }

    private String getSendBufferedPersistenceKey(MqttWireMessage mqttWireMessage) {
        StringBuffer stringBuffer = new StringBuffer(PERSISTENCE_SENT_BUFFERED_PREFIX);
        stringBuffer.append(mqttWireMessage.getMessageId());
        return stringBuffer.toString();
    }

    private String getSendConfirmPersistenceKey(MqttWireMessage mqttWireMessage) {
        StringBuffer stringBuffer = new StringBuffer(PERSISTENCE_CONFIRMED_PREFIX);
        stringBuffer.append(mqttWireMessage.getMessageId());
        return stringBuffer.toString();
    }

    private String getSendPersistenceKey(MqttWireMessage mqttWireMessage) {
        StringBuffer stringBuffer = new StringBuffer(PERSISTENCE_SENT_PREFIX);
        stringBuffer.append(mqttWireMessage.getMessageId());
        return stringBuffer.toString();
    }

    private void insertInOrder(Vector vector, MqttWireMessage mqttWireMessage) {
        int messageId = mqttWireMessage.getMessageId();
        for (int i2 = 0; i2 < vector.size(); i2++) {
            if (((MqttWireMessage) vector.elementAt(i2)).getMessageId() > messageId) {
                vector.insertElementAt(mqttWireMessage, i2);
                return;
            }
        }
        vector.addElement(mqttWireMessage);
    }

    private Vector reOrder(Vector vector) {
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < vector.size()) {
            int messageId = ((MqttWireMessage) vector.elementAt(i2)).getMessageId();
            int i6 = messageId - i3;
            if (i6 > i4) {
                i5 = i2;
                i4 = i6;
            }
            i2++;
            i3 = messageId;
        }
        int i7 = (65535 - i3) + ((MqttWireMessage) vector.elementAt(0)).getMessageId() > i4 ? 0 : i5;
        for (int i8 = i7; i8 < vector.size(); i8++) {
            vector2.addElement(vector.elementAt(i8));
        }
        for (int i9 = 0; i9 < i7; i9++) {
            vector2.addElement(vector.elementAt(i9));
        }
        return vector2;
    }

    private synchronized void releaseMessageId(int i2) {
        this.inUseMsgIds.remove(new Integer(i2));
    }

    private void restoreInflightMessages() {
        this.pendingMessages = new Vector(this.maxInflight);
        this.pendingFlows = new Vector();
        Enumeration keys = this.outboundQoS2.keys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            MqttWireMessage mqttWireMessage = (MqttWireMessage) this.outboundQoS2.get(nextElement);
            if (mqttWireMessage instanceof MqttPublish) {
                log.fine(CLASS_NAME, "restoreInflightMessages", "610", new Object[]{nextElement});
                mqttWireMessage.setDuplicate(true);
                insertInOrder(this.pendingMessages, (MqttPublish) mqttWireMessage);
            } else if (mqttWireMessage instanceof MqttPubRel) {
                log.fine(CLASS_NAME, "restoreInflightMessages", "611", new Object[]{nextElement});
                insertInOrder(this.pendingFlows, (MqttPubRel) mqttWireMessage);
            }
        }
        Enumeration keys2 = this.outboundQoS1.keys();
        while (keys2.hasMoreElements()) {
            Object nextElement2 = keys2.nextElement();
            MqttPublish mqttPublish = (MqttPublish) this.outboundQoS1.get(nextElement2);
            mqttPublish.setDuplicate(true);
            log.fine(CLASS_NAME, "restoreInflightMessages", "612", new Object[]{nextElement2});
            insertInOrder(this.pendingMessages, mqttPublish);
        }
        Enumeration keys3 = this.outboundQoS0.keys();
        while (keys3.hasMoreElements()) {
            Object nextElement3 = keys3.nextElement();
            log.fine(CLASS_NAME, "restoreInflightMessages", "512", new Object[]{nextElement3});
            insertInOrder(this.pendingMessages, (MqttPublish) this.outboundQoS0.get(nextElement3));
        }
        this.pendingFlows = reOrder(this.pendingFlows);
        this.pendingMessages = reOrder(this.pendingMessages);
    }

    private MqttWireMessage restoreMessage(String str, MqttPersistable mqttPersistable) throws MqttException {
        MqttWireMessage mqttWireMessage;
        try {
            mqttWireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        } catch (MqttException e2) {
            log.fine(CLASS_NAME, "restoreMessage", "602", new Object[]{str}, e2);
            if (!(e2.getCause() instanceof EOFException)) {
                throw e2;
            }
            if (str != null) {
                this.persistence.remove(str);
            }
            mqttWireMessage = null;
        }
        log.fine(CLASS_NAME, "restoreMessage", "601", new Object[]{str, mqttWireMessage});
        return mqttWireMessage;
    }

    /* JADX WARN: Code restructure failed: missing block: B:75:0x0095, code lost:
        if ((r4 - r16.lastOutboundActivity) >= (r16.keepAlive * 2)) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0098, code lost:
        r2.severe(r3, "checkForActivity", "642", new java.lang.Object[]{new java.lang.Long(r16.keepAlive), new java.lang.Long(r16.lastOutboundActivity), new java.lang.Long(r16.lastInboundActivity), new java.lang.Long(r4), new java.lang.Long(r16.lastPing)});
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00d7, code lost:
        throw org.eclipse.paho.client.mqttv3.internal.ExceptionHelper.createMqttException(32002);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.eclipse.paho.client.mqttv3.MqttToken checkForActivity(org.eclipse.paho.client.mqttv3.IMqttActionListener r17) throws org.eclipse.paho.client.mqttv3.MqttException {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientState.checkForActivity(org.eclipse.paho.client.mqttv3.IMqttActionListener):org.eclipse.paho.client.mqttv3.MqttToken");
    }

    public boolean checkQuiesceLock() {
        int count = this.tokenStore.count();
        if (this.quiescing && count == 0 && this.pendingFlows.size() == 0 && this.callback.isQuiesced()) {
            log.fine(CLASS_NAME, "checkQuiesceLock", "626", new Object[]{new Boolean(this.quiescing), new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), Boolean.valueOf(this.callback.isQuiesced()), new Integer(count)});
            synchronized (this.quiesceLock) {
                this.quiesceLock.notifyAll();
            }
            return true;
        }
        return false;
    }

    protected void clearState() throws MqttException {
        log.fine(CLASS_NAME, "clearState", ">");
        this.persistence.clear();
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
    }

    public void close() {
        this.inUseMsgIds.clear();
        this.pendingMessages.clear();
        this.pendingFlows.clear();
        this.outboundQoS2.clear();
        this.outboundQoS1.clear();
        this.outboundQoS0.clear();
        this.inboundQoS2.clear();
        this.tokenStore.clear();
        this.inUseMsgIds = null;
        this.pendingMessages = null;
        this.pendingFlows = null;
        this.outboundQoS2 = null;
        this.outboundQoS1 = null;
        this.outboundQoS0 = null;
        this.inboundQoS2 = null;
        this.tokenStore = null;
        this.callback = null;
        this.clientComms = null;
        this.persistence = null;
        this.pingCommand = null;
    }

    public void connected() {
        log.fine(CLASS_NAME, "connected", "631");
        this.connected = true;
        this.pingSender.start();
    }

    public void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{new Integer(mqttPublish.getMessageId())});
        this.persistence.remove(getReceivedPersistenceKey(mqttPublish));
        this.inboundQoS2.remove(new Integer(mqttPublish.getMessageId()));
    }

    public void disconnected(MqttException mqttException) {
        log.fine(CLASS_NAME, "disconnected", "633", new Object[]{mqttException});
        this.connected = false;
        try {
            if (this.cleanSession) {
                clearState();
            }
            this.pendingMessages.clear();
            this.pendingFlows.clear();
            synchronized (this.pingOutstandingLock) {
                this.pingOutstanding = 0;
            }
        } catch (MqttException unused) {
        }
    }

    public MqttWireMessage get() throws MqttException {
        synchronized (this.queueLock) {
            MqttWireMessage mqttWireMessage = null;
            while (mqttWireMessage == null) {
                if ((this.pendingMessages.isEmpty() && this.pendingFlows.isEmpty()) || (this.pendingFlows.isEmpty() && this.actualInFlight >= this.maxInflight)) {
                    try {
                        Logger logger = log;
                        String str = CLASS_NAME;
                        logger.fine(str, IMantoServerRequester.GET, FileService.FILE_MODE_WORLD_READABLE);
                        this.queueLock.wait();
                        logger.fine(str, IMantoServerRequester.GET, "647");
                    } catch (InterruptedException unused) {
                    }
                }
                if (!this.connected && (this.pendingFlows.isEmpty() || !(((MqttWireMessage) this.pendingFlows.elementAt(0)) instanceof MqttConnect))) {
                    log.fine(CLASS_NAME, IMantoServerRequester.GET, "621");
                    return null;
                } else if (!this.pendingFlows.isEmpty()) {
                    mqttWireMessage = (MqttWireMessage) this.pendingFlows.remove(0);
                    if (mqttWireMessage instanceof MqttPubRel) {
                        this.inFlightPubRels++;
                        log.fine(CLASS_NAME, IMantoServerRequester.GET, "617", new Object[]{new Integer(this.inFlightPubRels)});
                    }
                    checkQuiesceLock();
                } else if (!this.pendingMessages.isEmpty()) {
                    if (this.actualInFlight < this.maxInflight) {
                        mqttWireMessage = (MqttWireMessage) this.pendingMessages.elementAt(0);
                        this.pendingMessages.removeElementAt(0);
                        this.actualInFlight++;
                        log.fine(CLASS_NAME, IMantoServerRequester.GET, "623", new Object[]{new Integer(this.actualInFlight)});
                    } else {
                        log.fine(CLASS_NAME, IMantoServerRequester.GET, FileService.FILE_MODE_WORLD_WRITEABLE);
                    }
                }
            }
            return mqttWireMessage;
        }
    }

    public int getActualInFlight() {
        return this.actualInFlight;
    }

    public boolean getCleanSession() {
        return this.cleanSession;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("In use msgids", this.inUseMsgIds);
        properties.put("pendingMessages", this.pendingMessages);
        properties.put("pendingFlows", this.pendingFlows);
        properties.put("maxInflight", new Integer(this.maxInflight));
        properties.put("nextMsgID", new Integer(this.nextMsgId));
        properties.put("actualInFlight", new Integer(this.actualInFlight));
        properties.put("inFlightPubRels", new Integer(this.inFlightPubRels));
        properties.put("quiescing", Boolean.valueOf(this.quiescing));
        properties.put("pingoutstanding", new Integer(this.pingOutstanding));
        properties.put("lastOutboundActivity", new Long(this.lastOutboundActivity));
        properties.put("lastInboundActivity", new Long(this.lastInboundActivity));
        properties.put("outboundQoS2", this.outboundQoS2);
        properties.put("outboundQoS1", this.outboundQoS1);
        properties.put("outboundQoS0", this.outboundQoS0);
        properties.put("inboundQoS2", this.inboundQoS2);
        properties.put("tokens", this.tokenStore);
        return properties;
    }

    public long getKeepAlive() {
        return this.keepAlive;
    }

    public int getMaxInFlight() {
        return this.maxInflight;
    }

    public void notifyComplete(MqttToken mqttToken) throws MqttException {
        MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage == null || !(wireMessage instanceof MqttAck)) {
            return;
        }
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "notifyComplete", "629", new Object[]{new Integer(wireMessage.getMessageId()), mqttToken, wireMessage});
        MqttAck mqttAck = (MqttAck) wireMessage;
        if (mqttAck instanceof MqttPubAck) {
            this.persistence.remove(getSendPersistenceKey(wireMessage));
            this.outboundQoS1.remove(new Integer(mqttAck.getMessageId()));
            decrementInFlight();
            releaseMessageId(wireMessage.getMessageId());
            this.tokenStore.removeToken(wireMessage);
            logger.fine(str, "notifyComplete", "650", new Object[]{new Integer(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubComp) {
            this.persistence.remove(getSendPersistenceKey(wireMessage));
            this.persistence.remove(getSendConfirmPersistenceKey(wireMessage));
            this.outboundQoS2.remove(new Integer(mqttAck.getMessageId()));
            this.inFlightPubRels--;
            decrementInFlight();
            releaseMessageId(wireMessage.getMessageId());
            this.tokenStore.removeToken(wireMessage);
            logger.fine(str, "notifyComplete", "645", new Object[]{new Integer(mqttAck.getMessageId()), new Integer(this.inFlightPubRels)});
        }
        checkQuiesceLock();
    }

    public void notifyQueueLock() {
        synchronized (this.queueLock) {
            log.fine(CLASS_NAME, "notifyQueueLock", "638");
            this.queueLock.notifyAll();
        }
    }

    public void notifyReceivedAck(MqttAck mqttAck) throws MqttException {
        this.lastInboundActivity = System.currentTimeMillis();
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "notifyReceivedAck", "627", new Object[]{new Integer(mqttAck.getMessageId()), mqttAck});
        MqttToken token = this.tokenStore.getToken(mqttAck);
        if (token == null) {
            logger.fine(str, "notifyReceivedAck", JDFileService.FILE_MODE_WORLD_WRITEABLE, new Object[]{new Integer(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubRec) {
            send(new MqttPubRel((MqttPubRec) mqttAck), token);
        } else if (!(mqttAck instanceof MqttPubAck) && !(mqttAck instanceof MqttPubComp)) {
            if (mqttAck instanceof MqttPingResp) {
                synchronized (this.pingOutstandingLock) {
                    this.pingOutstanding = Math.max(0, this.pingOutstanding - 1);
                    notifyResult(mqttAck, token, null);
                    if (this.pingOutstanding == 0) {
                        this.tokenStore.removeToken(mqttAck);
                    }
                }
                logger.fine(str, "notifyReceivedAck", "636", new Object[]{new Integer(this.pingOutstanding)});
            } else if (mqttAck instanceof MqttConnack) {
                MqttConnack mqttConnack = (MqttConnack) mqttAck;
                int returnCode = mqttConnack.getReturnCode();
                if (returnCode == 0) {
                    synchronized (this.queueLock) {
                        if (this.cleanSession) {
                            clearState();
                            this.tokenStore.saveToken(token, mqttAck);
                        }
                        this.inFlightPubRels = 0;
                        this.actualInFlight = 0;
                        restoreInflightMessages();
                        connected();
                    }
                    this.clientComms.connectComplete(mqttConnack, null);
                    notifyResult(mqttAck, token, null);
                    this.tokenStore.removeToken(mqttAck);
                    synchronized (this.queueLock) {
                        this.queueLock.notifyAll();
                    }
                } else {
                    throw ExceptionHelper.createMqttException(returnCode);
                }
            } else {
                notifyResult(mqttAck, token, null);
                releaseMessageId(mqttAck.getMessageId());
                this.tokenStore.removeToken(mqttAck);
            }
        } else {
            notifyResult(mqttAck, token, null);
        }
        checkQuiesceLock();
    }

    public void notifyReceivedBytes(int i2) {
        if (i2 > 0) {
            this.lastInboundActivity = System.currentTimeMillis();
        }
        log.fine(CLASS_NAME, "notifyReceivedBytes", "630", new Object[]{new Integer(i2)});
    }

    public void notifyReceivedMsg(MqttWireMessage mqttWireMessage) throws MqttException {
        this.lastInboundActivity = System.currentTimeMillis();
        log.fine(CLASS_NAME, "notifyReceivedMsg", "651", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (this.quiescing) {
            return;
        }
        if (mqttWireMessage instanceof MqttPublish) {
            MqttPublish mqttPublish = (MqttPublish) mqttWireMessage;
            int qos = mqttPublish.getMessage().getQos();
            if (qos == 0 || qos == 1) {
                CommsCallback commsCallback = this.callback;
                if (commsCallback != null) {
                    commsCallback.messageArrived(mqttPublish);
                }
            } else if (qos != 2) {
            } else {
                this.persistence.put(getReceivedPersistenceKey(mqttWireMessage), mqttPublish);
                this.inboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                send(new MqttPubRec(mqttPublish), null);
            }
        } else if (mqttWireMessage instanceof MqttPubRel) {
            MqttPublish mqttPublish2 = (MqttPublish) this.inboundQoS2.get(new Integer(mqttWireMessage.getMessageId()));
            if (mqttPublish2 != null) {
                CommsCallback commsCallback2 = this.callback;
                if (commsCallback2 != null) {
                    commsCallback2.messageArrived(mqttPublish2);
                    return;
                }
                return;
            }
            send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
        }
    }

    protected void notifyResult(MqttWireMessage mqttWireMessage, MqttToken mqttToken, MqttException mqttException) {
        mqttToken.internalTok.markComplete(mqttWireMessage, mqttException);
        mqttToken.internalTok.notifyComplete();
        if (mqttWireMessage != null && (mqttWireMessage instanceof MqttAck) && !(mqttWireMessage instanceof MqttPubRec)) {
            log.fine(CLASS_NAME, "notifyResult", "648", new Object[]{mqttToken.internalTok.getKey(), mqttWireMessage, mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            log.fine(CLASS_NAME, "notifyResult", "649", new Object[]{mqttToken.internalTok.getKey(), mqttException});
            this.callback.asyncOperationComplete(mqttToken);
        }
    }

    public void notifySent(MqttWireMessage mqttWireMessage) {
        this.lastOutboundActivity = System.currentTimeMillis();
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "notifySent", "625", new Object[]{mqttWireMessage.getKey()});
        MqttToken token = this.tokenStore.getToken(mqttWireMessage);
        token.internalTok.notifySent();
        if (mqttWireMessage instanceof MqttPingReq) {
            synchronized (this.pingOutstandingLock) {
                long currentTimeMillis = System.currentTimeMillis();
                synchronized (this.pingOutstandingLock) {
                    this.lastPing = currentTimeMillis;
                    this.pingOutstanding++;
                }
                logger.fine(str, "notifySent", "635", new Object[]{new Integer(this.pingOutstanding)});
            }
        } else if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() == 0) {
            token.internalTok.markComplete(null, null);
            this.callback.asyncOperationComplete(token);
            decrementInFlight();
            releaseMessageId(mqttWireMessage.getMessageId());
            this.tokenStore.removeToken(mqttWireMessage);
            checkQuiesceLock();
        }
    }

    public void notifySentBytes(int i2) {
        if (i2 > 0) {
            this.lastOutboundActivity = System.currentTimeMillis();
        }
        log.fine(CLASS_NAME, "notifySentBytes", "643", new Object[]{new Integer(i2)});
    }

    public void persistBufferedMessage(MqttWireMessage mqttWireMessage) {
        String sendBufferedPersistenceKey = getSendBufferedPersistenceKey(mqttWireMessage);
        try {
            mqttWireMessage.setMessageId(getNextMessageId());
            try {
                this.persistence.put(sendBufferedPersistenceKey, (MqttPublish) mqttWireMessage);
            } catch (MqttPersistenceException unused) {
                log.fine(CLASS_NAME, "persistBufferedMessage", "515");
                this.persistence.open(this.clientComms.getClient().getClientId(), this.clientComms.getClient().getClientId());
                this.persistence.put(sendBufferedPersistenceKey, (MqttPublish) mqttWireMessage);
            }
            log.fine(CLASS_NAME, "persistBufferedMessage", "513", new Object[]{sendBufferedPersistenceKey});
        } catch (MqttException unused2) {
            log.warning(CLASS_NAME, "persistBufferedMessage", "513", new Object[]{sendBufferedPersistenceKey});
        }
    }

    public void quiesce(long j2) {
        if (j2 > 0) {
            Logger logger = log;
            String str = CLASS_NAME;
            logger.fine(str, "quiesce", "637", new Object[]{new Long(j2)});
            synchronized (this.queueLock) {
                this.quiescing = true;
            }
            this.callback.quiesce();
            notifyQueueLock();
            synchronized (this.quiesceLock) {
                try {
                    int count = this.tokenStore.count();
                    if (count > 0 || this.pendingFlows.size() > 0 || !this.callback.isQuiesced()) {
                        logger.fine(str, "quiesce", "639", new Object[]{new Integer(this.actualInFlight), new Integer(this.pendingFlows.size()), new Integer(this.inFlightPubRels), new Integer(count)});
                        this.quiesceLock.wait(j2);
                    }
                } catch (InterruptedException unused) {
                }
            }
            synchronized (this.queueLock) {
                this.pendingMessages.clear();
                this.pendingFlows.clear();
                this.quiescing = false;
                this.actualInFlight = 0;
            }
            log.fine(CLASS_NAME, "quiesce", "640");
        }
    }

    public Vector resolveOldTokens(MqttException mqttException) {
        log.fine(CLASS_NAME, "resolveOldTokens", "632", new Object[]{mqttException});
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector outstandingTokens = this.tokenStore.getOutstandingTokens();
        Enumeration elements = outstandingTokens.elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken = (MqttToken) elements.nextElement();
            synchronized (mqttToken) {
                if (!mqttToken.isComplete() && !mqttToken.internalTok.isCompletePending() && mqttToken.getException() == null) {
                    mqttToken.internalTok.setException(mqttException);
                }
            }
            if (!(mqttToken instanceof MqttDeliveryToken)) {
                this.tokenStore.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }

    protected void restoreState() throws MqttException {
        Enumeration keys = this.persistence.keys();
        int i2 = this.nextMsgId;
        Vector vector = new Vector();
        log.fine(CLASS_NAME, "restoreState", "600");
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            MqttWireMessage restoreMessage = restoreMessage(str, this.persistence.get(str));
            if (restoreMessage != null) {
                if (str.startsWith(PERSISTENCE_RECEIVED_PREFIX)) {
                    log.fine(CLASS_NAME, "restoreState", "604", new Object[]{str, restoreMessage});
                    this.inboundQoS2.put(new Integer(restoreMessage.getMessageId()), restoreMessage);
                } else if (str.startsWith(PERSISTENCE_SENT_PREFIX)) {
                    MqttPublish mqttPublish = (MqttPublish) restoreMessage;
                    i2 = Math.max(mqttPublish.getMessageId(), i2);
                    if (this.persistence.containsKey(getSendConfirmPersistenceKey(mqttPublish))) {
                        MqttPubRel mqttPubRel = (MqttPubRel) restoreMessage(str, this.persistence.get(getSendConfirmPersistenceKey(mqttPublish)));
                        if (mqttPubRel != null) {
                            log.fine(CLASS_NAME, "restoreState", "605", new Object[]{str, restoreMessage});
                            this.outboundQoS2.put(new Integer(mqttPubRel.getMessageId()), mqttPubRel);
                        } else {
                            log.fine(CLASS_NAME, "restoreState", "606", new Object[]{str, restoreMessage});
                        }
                    } else {
                        mqttPublish.setDuplicate(true);
                        if (mqttPublish.getMessage().getQos() == 2) {
                            log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, restoreMessage});
                            this.outboundQoS2.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        } else {
                            log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, restoreMessage});
                            this.outboundQoS1.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        }
                    }
                    this.tokenStore.restoreToken(mqttPublish).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessageId()));
                } else if (str.startsWith(PERSISTENCE_SENT_BUFFERED_PREFIX)) {
                    MqttPublish mqttPublish2 = (MqttPublish) restoreMessage;
                    i2 = Math.max(mqttPublish2.getMessageId(), i2);
                    if (mqttPublish2.getMessage().getQos() == 2) {
                        log.fine(CLASS_NAME, "restoreState", "607", new Object[]{str, restoreMessage});
                        this.outboundQoS2.put(new Integer(mqttPublish2.getMessageId()), mqttPublish2);
                    } else if (mqttPublish2.getMessage().getQos() == 1) {
                        log.fine(CLASS_NAME, "restoreState", "608", new Object[]{str, restoreMessage});
                        this.outboundQoS1.put(new Integer(mqttPublish2.getMessageId()), mqttPublish2);
                    } else {
                        log.fine(CLASS_NAME, "restoreState", "511", new Object[]{str, restoreMessage});
                        this.outboundQoS0.put(new Integer(mqttPublish2.getMessageId()), mqttPublish2);
                        this.persistence.remove(str);
                    }
                    this.tokenStore.restoreToken(mqttPublish2).internalTok.setClient(this.clientComms.getClient());
                    this.inUseMsgIds.put(new Integer(mqttPublish2.getMessageId()), new Integer(mqttPublish2.getMessageId()));
                } else if (str.startsWith(PERSISTENCE_CONFIRMED_PREFIX) && !this.persistence.containsKey(getSendPersistenceKey((MqttPubRel) restoreMessage))) {
                    vector.addElement(str);
                }
            }
        }
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            String str2 = (String) elements.nextElement();
            log.fine(CLASS_NAME, "restoreState", "609", new Object[]{str2});
            this.persistence.remove(str2);
        }
        this.nextMsgId = i2;
    }

    public void send(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttWireMessage.isMessageIdRequired() && mqttWireMessage.getMessageId() == 0) {
            mqttWireMessage.setMessageId(getNextMessageId());
        }
        if (mqttToken != null) {
            try {
                mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
            } catch (Exception unused) {
            }
        }
        if (mqttWireMessage instanceof MqttPublish) {
            synchronized (this.queueLock) {
                if (this.actualInFlight < this.maxInflight) {
                    MqttMessage message = ((MqttPublish) mqttWireMessage).getMessage();
                    log.fine(CLASS_NAME, "send", "628", new Object[]{new Integer(mqttWireMessage.getMessageId()), new Integer(message.getQos()), mqttWireMessage});
                    int qos = message.getQos();
                    if (qos == 1) {
                        this.outboundQoS1.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                    } else if (qos == 2) {
                        this.outboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.persistence.put(getSendPersistenceKey(mqttWireMessage), (MqttPublish) mqttWireMessage);
                    }
                    this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                    this.pendingMessages.addElement(mqttWireMessage);
                    this.queueLock.notifyAll();
                } else {
                    log.fine(CLASS_NAME, "send", "613", new Object[]{new Integer(this.actualInFlight)});
                    throw new MqttException(32202);
                }
            }
            return;
        }
        log.fine(CLASS_NAME, "send", "615", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (mqttWireMessage instanceof MqttConnect) {
            synchronized (this.queueLock) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
                this.pendingFlows.insertElementAt(mqttWireMessage, 0);
                this.queueLock.notifyAll();
            }
            return;
        }
        if (mqttWireMessage instanceof MqttPingReq) {
            this.pingCommand = mqttWireMessage;
        } else if (mqttWireMessage instanceof MqttPubRel) {
            this.outboundQoS2.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
            this.persistence.put(getSendConfirmPersistenceKey(mqttWireMessage), (MqttPubRel) mqttWireMessage);
        } else if (mqttWireMessage instanceof MqttPubComp) {
            this.persistence.remove(getReceivedPersistenceKey(mqttWireMessage));
        }
        synchronized (this.queueLock) {
            if (!(mqttWireMessage instanceof MqttAck)) {
                this.tokenStore.saveToken(mqttToken, mqttWireMessage);
            }
            this.pendingFlows.addElement(mqttWireMessage);
            this.queueLock.notifyAll();
        }
    }

    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    public void setKeepAliveInterval(long j2) {
        this.keepAlive = j2;
    }

    public void setKeepAliveSecs(long j2) {
        this.keepAlive = j2 * 1000;
    }

    public void setMaxInflight(int i2) {
        this.maxInflight = i2;
        this.pendingMessages = new Vector(this.maxInflight);
    }

    public void unPersistBufferedMessage(MqttWireMessage mqttWireMessage) throws MqttPersistenceException {
        log.fine(CLASS_NAME, "unPersistBufferedMessage", "513", new Object[]{mqttWireMessage.getKey()});
        this.persistence.remove(getSendBufferedPersistenceKey(mqttWireMessage));
    }

    public void undo(MqttPublish mqttPublish) throws MqttPersistenceException {
        synchronized (this.queueLock) {
            log.fine(CLASS_NAME, "undo", "618", new Object[]{new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessage().getQos())});
            if (mqttPublish.getMessage().getQos() == 1) {
                this.outboundQoS1.remove(new Integer(mqttPublish.getMessageId()));
            } else {
                this.outboundQoS2.remove(new Integer(mqttPublish.getMessageId()));
            }
            this.pendingMessages.removeElement(mqttPublish);
            this.persistence.remove(getSendPersistenceKey(mqttPublish));
            this.tokenStore.removeToken(mqttPublish);
            checkQuiesceLock();
        }
    }

    private String getReceivedPersistenceKey(int i2) {
        StringBuffer stringBuffer = new StringBuffer(PERSISTENCE_RECEIVED_PREFIX);
        stringBuffer.append(i2);
        return stringBuffer.toString();
    }

    public void deliveryComplete(int i2) throws MqttPersistenceException {
        log.fine(CLASS_NAME, "deliveryComplete", "641", new Object[]{new Integer(i2)});
        this.persistence.remove(getReceivedPersistenceKey(i2));
        this.inboundQoS2.remove(new Integer(i2));
    }
}
