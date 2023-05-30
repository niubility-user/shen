package org.eclipse.paho.client.mqttv3.internal;

import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.meizu.cloud.pushsdk.platform.message.BasicPushStatus;
import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class ClientComms {
    public static String BUILD_LEVEL = "L${build.level}";
    private static final String CLASS_NAME;
    private static final byte CLOSED = 4;
    private static final byte CONNECTED = 0;
    private static final byte CONNECTING = 1;
    private static final byte DISCONNECTED = 3;
    private static final byte DISCONNECTING = 2;
    public static String VERSION = "${project.version}";
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private CommsCallback callback;
    private IMqttAsyncClient client;
    private ClientState clientState;
    private MqttConnectOptions conOptions;
    private byte conState;
    private DisconnectedMessageBuffer disconnectedMessageBuffer;
    private int networkModuleIndex;
    private NetworkModule[] networkModules;
    private MqttClientPersistence persistence;
    private MqttPingSender pingSender;
    private CommsReceiver receiver;
    private CommsSender sender;
    private CommsTokenStore tokenStore;
    private boolean stoppingComms = false;
    private Object conLock = new Object();
    private boolean closePending = false;
    private boolean resting = false;

    /* loaded from: classes11.dex */
    public class ConnectBG implements Runnable {
        Thread cBg;
        ClientComms clientComms;
        MqttConnect conPacket;
        MqttToken conToken;

        ConnectBG(ClientComms clientComms, MqttToken mqttToken, MqttConnect mqttConnect) {
            ClientComms.this = r2;
            this.clientComms = null;
            this.cBg = null;
            this.clientComms = clientComms;
            this.conToken = mqttToken;
            this.conPacket = mqttConnect;
            StringBuffer stringBuffer = new StringBuffer("MQTT Con: ");
            stringBuffer.append(r2.getClient().getClientId());
            this.cBg = new Thread(this, stringBuffer.toString());
        }

        @Override // java.lang.Runnable
        public void run() {
            ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "220");
            MqttException e2 = null;
            try {
                for (MqttDeliveryToken mqttDeliveryToken : ClientComms.this.tokenStore.getOutstandingDelTokens()) {
                    mqttDeliveryToken.internalTok.setException(null);
                }
                ClientComms.this.tokenStore.saveToken(this.conToken, this.conPacket);
                NetworkModule networkModule = ClientComms.this.networkModules[ClientComms.this.networkModuleIndex];
                networkModule.start();
                ClientComms.this.receiver = new CommsReceiver(this.clientComms, ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getInputStream());
                CommsReceiver commsReceiver = ClientComms.this.receiver;
                StringBuffer stringBuffer = new StringBuffer("MQTT Rec: ");
                stringBuffer.append(ClientComms.this.getClient().getClientId());
                commsReceiver.start(stringBuffer.toString());
                ClientComms.this.sender = new CommsSender(this.clientComms, ClientComms.this.clientState, ClientComms.this.tokenStore, networkModule.getOutputStream());
                CommsSender commsSender = ClientComms.this.sender;
                StringBuffer stringBuffer2 = new StringBuffer("MQTT Snd: ");
                stringBuffer2.append(ClientComms.this.getClient().getClientId());
                commsSender.start(stringBuffer2.toString());
                CommsCallback commsCallback = ClientComms.this.callback;
                StringBuffer stringBuffer3 = new StringBuffer("MQTT Call: ");
                stringBuffer3.append(ClientComms.this.getClient().getClientId());
                commsCallback.start(stringBuffer3.toString());
                ClientComms.this.internalSend(this.conPacket, this.conToken);
            } catch (MqttException e3) {
                e2 = e3;
                ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "212", null, e2);
            } catch (Exception e4) {
                ClientComms.log.fine(ClientComms.CLASS_NAME, "connectBG:run", "209", null, e4);
                e2 = ExceptionHelper.createMqttException(e4);
            }
            if (e2 != null) {
                ClientComms.this.shutdownConnection(this.conToken, e2);
            }
        }

        void start() {
            this.cBg.start();
        }
    }

    /* loaded from: classes11.dex */
    public class DisconnectBG implements Runnable {
        Thread dBg = null;
        MqttDisconnect disconnect;
        long quiesceTimeout;
        MqttToken token;

        DisconnectBG(MqttDisconnect mqttDisconnect, long j2, MqttToken mqttToken) {
            ClientComms.this = r1;
            this.disconnect = mqttDisconnect;
            this.quiesceTimeout = j2;
            this.token = mqttToken;
        }

        @Override // java.lang.Runnable
        public void run() {
            ClientComms.log.fine(ClientComms.CLASS_NAME, "disconnectBG:run", "221");
            ClientComms.this.clientState.quiesce(this.quiesceTimeout);
            try {
                ClientComms.this.internalSend(this.disconnect, this.token);
                this.token.internalTok.waitUntilSent();
            } catch (MqttException unused) {
            } catch (Throwable th) {
                this.token.internalTok.markComplete(null, null);
                ClientComms.this.shutdownConnection(this.token, null);
                throw th;
            }
            this.token.internalTok.markComplete(null, null);
            ClientComms.this.shutdownConnection(this.token, null);
        }

        void start() {
            StringBuffer stringBuffer = new StringBuffer("MQTT Disc: ");
            stringBuffer.append(ClientComms.this.getClient().getClientId());
            Thread thread = new Thread(this, stringBuffer.toString());
            this.dBg = thread;
            thread.start();
        }
    }

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public ClientComms(IMqttAsyncClient iMqttAsyncClient, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this.conState = (byte) 3;
        this.conState = (byte) 3;
        this.client = iMqttAsyncClient;
        this.persistence = mqttClientPersistence;
        this.pingSender = mqttPingSender;
        mqttPingSender.init(this);
        this.tokenStore = new CommsTokenStore(getClient().getClientId());
        this.callback = new CommsCallback(this);
        ClientState clientState = new ClientState(mqttClientPersistence, this.tokenStore, this.callback, this, mqttPingSender);
        this.clientState = clientState;
        this.callback.setClientState(clientState);
        log.setResourceName(getClient().getClientId());
    }

    private MqttToken handleOldTokens(MqttToken mqttToken, MqttException mqttException) {
        log.fine(CLASS_NAME, "handleOldTokens", "222");
        MqttToken mqttToken2 = null;
        if (mqttToken != null) {
            try {
                if (this.tokenStore.getToken(mqttToken.internalTok.getKey()) == null) {
                    this.tokenStore.saveToken(mqttToken, mqttToken.internalTok.getKey());
                }
            } catch (Exception unused) {
            }
        }
        Enumeration elements = this.clientState.resolveOldTokens(mqttException).elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken3 = (MqttToken) elements.nextElement();
            if (!mqttToken3.internalTok.getKey().equals(MqttDisconnect.KEY) && !mqttToken3.internalTok.getKey().equals("Con")) {
                this.callback.asyncOperationComplete(mqttToken3);
            }
            mqttToken2 = mqttToken3;
        }
        return mqttToken2;
    }

    private void handleRunException(Exception exc) {
        MqttException mqttException;
        log.fine(CLASS_NAME, "handleRunException", JDNetworkConstant.DOMAIN_REQUEST_EXP_ERRCODE, null, exc);
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        shutdownConnection(null, mqttException);
    }

    public MqttToken checkForActivity() {
        return checkForActivity(null);
    }

    public void close() throws MqttException {
        synchronized (this.conLock) {
            if (!isClosed()) {
                if (!isDisconnected()) {
                    log.fine(CLASS_NAME, "close", "224");
                    if (!isConnecting()) {
                        if (!isConnected()) {
                            if (isDisconnecting()) {
                                this.closePending = true;
                                return;
                            }
                        } else {
                            throw ExceptionHelper.createMqttException(32100);
                        }
                    } else {
                        throw new MqttException(32110);
                    }
                }
                this.conState = (byte) 4;
                this.clientState.close();
                this.clientState = null;
                this.callback = null;
                this.persistence = null;
                this.sender = null;
                this.pingSender = null;
                this.receiver = null;
                this.networkModules = null;
                this.conOptions = null;
                this.tokenStore = null;
            }
        }
    }

    public void connect(MqttConnectOptions mqttConnectOptions, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (isDisconnected() && !this.closePending) {
                log.fine(CLASS_NAME, "connect", "214");
                this.conState = (byte) 1;
                this.conOptions = mqttConnectOptions;
                MqttConnect mqttConnect = new MqttConnect(this.client.getClientId(), this.conOptions.getMqttVersion(), this.conOptions.isCleanSession(), this.conOptions.getKeepAliveInterval(), this.conOptions.getUserName(), this.conOptions.getPassword(), this.conOptions.getWillMessage(), this.conOptions.getWillDestination());
                this.clientState.setKeepAliveSecs(this.conOptions.getKeepAliveInterval());
                this.clientState.setCleanSession(this.conOptions.isCleanSession());
                this.clientState.setMaxInflight(this.conOptions.getMaxInflight());
                this.tokenStore.open();
                new ConnectBG(this, mqttToken, mqttConnect).start();
            } else {
                log.fine(CLASS_NAME, "connect", "207", new Object[]{new Byte(this.conState)});
                if (!isClosed() && !this.closePending) {
                    if (!isConnecting()) {
                        if (isDisconnecting()) {
                            throw new MqttException(32102);
                        }
                        throw ExceptionHelper.createMqttException(32100);
                    }
                    throw new MqttException(32110);
                }
                throw new MqttException(32111);
            }
        }
    }

    public void connectComplete(MqttConnack mqttConnack, MqttException mqttException) throws MqttException {
        int returnCode = mqttConnack.getReturnCode();
        synchronized (this.conLock) {
            if (returnCode == 0) {
                log.fine(CLASS_NAME, "connectComplete", "215");
                this.conState = (byte) 0;
                return;
            }
            log.fine(CLASS_NAME, "connectComplete", "204", new Object[]{new Integer(returnCode)});
            throw mqttException;
        }
    }

    public void deleteBufferedMessage(int i2) {
        this.disconnectedMessageBuffer.deleteMessage(i2);
    }

    public void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.clientState.deliveryComplete(mqttPublish);
    }

    public void disconnect(MqttDisconnect mqttDisconnect, long j2, MqttToken mqttToken) throws MqttException {
        synchronized (this.conLock) {
            if (!isClosed()) {
                if (!isDisconnected()) {
                    if (!isDisconnecting()) {
                        if (Thread.currentThread() != this.callback.getThread()) {
                            log.fine(CLASS_NAME, "disconnect", "218");
                            this.conState = (byte) 2;
                            new DisconnectBG(mqttDisconnect, j2, mqttToken).start();
                        } else {
                            log.fine(CLASS_NAME, "disconnect", "210");
                            throw ExceptionHelper.createMqttException(32107);
                        }
                    } else {
                        log.fine(CLASS_NAME, "disconnect", "219");
                        throw ExceptionHelper.createMqttException(32102);
                    }
                } else {
                    log.fine(CLASS_NAME, "disconnect", "211");
                    throw ExceptionHelper.createMqttException(32101);
                }
            } else {
                log.fine(CLASS_NAME, "disconnect", "223");
                throw ExceptionHelper.createMqttException(32111);
            }
        }
    }

    public void disconnectForcibly(long j2, long j3) throws MqttException {
        this.clientState.quiesce(j2);
        MqttToken mqttToken = new MqttToken(this.client.getClientId());
        try {
            internalSend(new MqttDisconnect(), mqttToken);
            mqttToken.waitForCompletion(j3);
        } catch (Exception unused) {
        } catch (Throwable th) {
            mqttToken.internalTok.markComplete(null, null);
            shutdownConnection(mqttToken, null);
            throw th;
        }
        mqttToken.internalTok.markComplete(null, null);
        shutdownConnection(mqttToken, null);
    }

    public MqttMessage getBufferedMessage(int i2) {
        return ((MqttPublish) this.disconnectedMessageBuffer.getMessage(i2).getMessage()).getMessage();
    }

    public int getBufferedMessageCount() {
        return this.disconnectedMessageBuffer.getMessageCount();
    }

    public IMqttAsyncClient getClient() {
        return this.client;
    }

    public ClientState getClientState() {
        return this.clientState;
    }

    public MqttConnectOptions getConOptions() {
        return this.conOptions;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("conState", new Integer(this.conState));
        properties.put("serverURI", getClient().getServerURI());
        properties.put("callback", this.callback);
        properties.put("stoppingComms", new Boolean(this.stoppingComms));
        return properties;
    }

    public long getKeepAlive() {
        return this.clientState.getKeepAlive();
    }

    public int getNetworkModuleIndex() {
        return this.networkModuleIndex;
    }

    public NetworkModule[] getNetworkModules() {
        return this.networkModules;
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.tokenStore.getOutstandingDelTokens();
    }

    CommsReceiver getReceiver() {
        return this.receiver;
    }

    protected MqttTopic getTopic(String str) {
        return new MqttTopic(str, this);
    }

    public void internalSend(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "internalSend", BasicPushStatus.SUCCESS_CODE, new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        if (mqttToken.getClient() == null) {
            mqttToken.internalTok.setClient(getClient());
            try {
                this.clientState.send(mqttWireMessage, mqttToken);
                return;
            } catch (MqttException e2) {
                if (mqttWireMessage instanceof MqttPublish) {
                    this.clientState.undo((MqttPublish) mqttWireMessage);
                }
                throw e2;
            }
        }
        logger.fine(str, "internalSend", "213", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        throw new MqttException(32201);
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 4;
        }
        return z;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 0;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = true;
            if (this.conState != 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDisconnected() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 3;
        }
        return z;
    }

    public boolean isDisconnecting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.conState == 2;
        }
        return z;
    }

    public boolean isResting() {
        boolean z;
        synchronized (this.conLock) {
            z = this.resting;
        }
        return z;
    }

    public void messageArrivedComplete(int i2, int i3) throws MqttException {
        this.callback.messageArrivedComplete(i2, i3);
    }

    public void notifyReconnect() {
        if (this.disconnectedMessageBuffer != null) {
            log.fine(CLASS_NAME, "notifyReconnect", "509");
            this.disconnectedMessageBuffer.setPublishCallback(new IDisconnectedBufferCallback() { // from class: org.eclipse.paho.client.mqttv3.internal.ClientComms.1
                {
                    ClientComms.this = this;
                }

                @Override // org.eclipse.paho.client.mqttv3.internal.IDisconnectedBufferCallback
                public void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException {
                    if (ClientComms.this.isConnected()) {
                        while (ClientComms.this.clientState.getActualInFlight() >= ClientComms.this.clientState.getMaxInFlight() - 1) {
                            Thread.yield();
                        }
                        ClientComms.log.fine(ClientComms.CLASS_NAME, "notifyReconnect", "510", new Object[]{bufferedMessage.getMessage().getKey()});
                        ClientComms.this.internalSend(bufferedMessage.getMessage(), bufferedMessage.getToken());
                        ClientComms.this.clientState.unPersistBufferedMessage(bufferedMessage.getMessage());
                        return;
                    }
                    ClientComms.log.fine(ClientComms.CLASS_NAME, "notifyReconnect", "208");
                    throw ExceptionHelper.createMqttException(32104);
                }
            });
            new Thread(this.disconnectedMessageBuffer).start();
        }
    }

    public void removeMessageListener(String str) {
        this.callback.removeMessageListener(str);
    }

    public void sendNoWait(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (!isConnected() && ((isConnected() || !(mqttWireMessage instanceof MqttConnect)) && (!isDisconnecting() || !(mqttWireMessage instanceof MqttDisconnect)))) {
            if (this.disconnectedMessageBuffer != null && isResting()) {
                log.fine(CLASS_NAME, "sendNoWait", "508", new Object[]{mqttWireMessage.getKey()});
                this.clientState.persistBufferedMessage(mqttWireMessage);
                this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
                return;
            }
            log.fine(CLASS_NAME, "sendNoWait", "208");
            throw ExceptionHelper.createMqttException(32104);
        }
        DisconnectedMessageBuffer disconnectedMessageBuffer = this.disconnectedMessageBuffer;
        if (disconnectedMessageBuffer != null && disconnectedMessageBuffer.getMessageCount() != 0) {
            log.fine(CLASS_NAME, "sendNoWait", "507", new Object[]{mqttWireMessage.getKey()});
            this.clientState.persistBufferedMessage(mqttWireMessage);
            this.disconnectedMessageBuffer.putMessage(mqttWireMessage, mqttToken);
            return;
        }
        internalSend(mqttWireMessage, mqttToken);
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.callback.setCallback(mqttCallback);
    }

    public void setDisconnectedMessageBuffer(DisconnectedMessageBuffer disconnectedMessageBuffer) {
        this.disconnectedMessageBuffer = disconnectedMessageBuffer;
    }

    public void setManualAcks(boolean z) {
        this.callback.setManualAcks(z);
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.callback.setMessageListener(str, iMqttMessageListener);
    }

    public void setNetworkModuleIndex(int i2) {
        this.networkModuleIndex = i2;
    }

    public void setNetworkModules(NetworkModule[] networkModuleArr) {
        this.networkModules = networkModuleArr;
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.callback.setReconnectCallback(mqttCallbackExtended);
    }

    public void setRestingState(boolean z) {
        this.resting = z;
    }

    public void shutdownConnection(MqttToken mqttToken, MqttException mqttException) {
        CommsCallback commsCallback;
        MqttClientPersistence mqttClientPersistence;
        NetworkModule networkModule;
        synchronized (this.conLock) {
            if (!this.stoppingComms && !this.closePending && !isClosed()) {
                this.stoppingComms = true;
                log.fine(CLASS_NAME, "shutdownConnection", "216");
                boolean z = isConnected() || isDisconnecting();
                this.conState = (byte) 2;
                if (mqttToken != null && !mqttToken.isComplete()) {
                    mqttToken.internalTok.setException(mqttException);
                }
                CommsCallback commsCallback2 = this.callback;
                if (commsCallback2 != null) {
                    commsCallback2.stop();
                }
                try {
                    NetworkModule[] networkModuleArr = this.networkModules;
                    if (networkModuleArr != null && (networkModule = networkModuleArr[this.networkModuleIndex]) != null) {
                        networkModule.stop();
                    }
                } catch (Exception unused) {
                }
                CommsReceiver commsReceiver = this.receiver;
                if (commsReceiver != null) {
                    commsReceiver.stop();
                }
                this.tokenStore.quiesce(new MqttException(32102));
                MqttToken handleOldTokens = handleOldTokens(mqttToken, mqttException);
                try {
                    this.clientState.disconnected(mqttException);
                    if (this.clientState.getCleanSession()) {
                        this.callback.removeMessageListeners();
                    }
                } catch (Exception unused2) {
                }
                CommsSender commsSender = this.sender;
                if (commsSender != null) {
                    commsSender.stop();
                }
                MqttPingSender mqttPingSender = this.pingSender;
                if (mqttPingSender != null) {
                    mqttPingSender.stop();
                }
                try {
                    if (this.disconnectedMessageBuffer == null && (mqttClientPersistence = this.persistence) != null) {
                        mqttClientPersistence.close();
                    }
                } catch (Exception unused3) {
                }
                synchronized (this.conLock) {
                    log.fine(CLASS_NAME, "shutdownConnection", "217");
                    this.conState = (byte) 3;
                    this.stoppingComms = false;
                }
                boolean z2 = handleOldTokens != null;
                CommsCallback commsCallback3 = this.callback;
                if (z2 & (commsCallback3 != null)) {
                    commsCallback3.asyncOperationComplete(handleOldTokens);
                }
                if (z && (commsCallback = this.callback) != null) {
                    commsCallback.connectionLost(mqttException);
                }
                synchronized (this.conLock) {
                    if (this.closePending) {
                        try {
                            close();
                        } catch (Exception unused4) {
                        }
                    }
                }
            }
        }
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) {
        try {
            return this.clientState.checkForActivity(iMqttActionListener);
        } catch (MqttException e2) {
            handleRunException(e2);
            return null;
        } catch (Exception e3) {
            handleRunException(e3);
            return null;
        }
    }

    public void deliveryComplete(int i2) throws MqttPersistenceException {
        this.clientState.deliveryComplete(i2);
    }
}
