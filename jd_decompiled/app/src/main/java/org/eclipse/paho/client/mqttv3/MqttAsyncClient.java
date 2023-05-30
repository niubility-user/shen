package org.eclipse.paho.client.mqttv3;

import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.jdreactFramework.SpecialMtaConstants;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.LocalNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* loaded from: classes11.dex */
public class MqttAsyncClient implements IMqttAsyncClient {
    private static final String CLASS_NAME;
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000;
    private static final char MAX_HIGH_SURROGATE = '\udbff';
    private static final char MIN_HIGH_SURROGATE = '\ud800';
    private static final long QUIESCE_TIMEOUT = 30000;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private static int reconnectDelay;
    private String clientId;
    protected ClientComms comms;
    private MqttConnectOptions connOpts;
    private MqttCallback mqttCallback;
    private MqttClientPersistence persistence;
    private Timer reconnectTimer;
    private boolean reconnecting;
    private String serverURI;
    private Hashtable topics;
    private Object userContext;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class ReconnectTask extends TimerTask {
        private static final String methodName = "ReconnectTask.run";

        private ReconnectTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, methodName, "506");
            MqttAsyncClient.this.attemptReconnect();
        }

        /* synthetic */ ReconnectTask(MqttAsyncClient mqttAsyncClient, ReconnectTask reconnectTask) {
            this();
        }
    }

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.MqttAsyncClient");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        reconnectDelay = 1000;
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    protected static boolean Character_isHighSurrogate(char c2) {
        return c2 >= '\ud800' && c2 <= '\udbff';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptReconnect() {
        log.fine(CLASS_NAME, "attemptReconnect", "500", new Object[]{this.clientId});
        try {
            connect(this.connOpts, this.userContext, new IMqttActionListener() { // from class: org.eclipse.paho.client.mqttv3.MqttAsyncClient.2
                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "502", new Object[]{iMqttToken.getClient().getClientId()});
                    if (MqttAsyncClient.reconnectDelay < 128000) {
                        MqttAsyncClient.reconnectDelay *= 2;
                    }
                    MqttAsyncClient.this.rescheduleReconnectCycle(MqttAsyncClient.reconnectDelay);
                }

                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                public void onSuccess(IMqttToken iMqttToken) {
                    MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "501", new Object[]{iMqttToken.getClient().getClientId()});
                    MqttAsyncClient.this.comms.setRestingState(false);
                    MqttAsyncClient.this.stopReconnectCycle();
                }
            });
        } catch (MqttSecurityException e2) {
            log.fine(CLASS_NAME, "attemptReconnect", JDNetworkConstant.DOMAIN_REQUEST_EXP_ERRCODE, null, e2);
        } catch (MqttException e3) {
            log.fine(CLASS_NAME, "attemptReconnect", JDNetworkConstant.DOMAIN_REQUEST_EXP_ERRCODE, null, e3);
        }
    }

    private NetworkModule createNetworkModule(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        String[] enabledCipherSuites;
        SSLSocketFactoryFactory sSLSocketFactoryFactory2;
        String[] enabledCipherSuites2;
        log.fine(CLASS_NAME, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        int validateURI = MqttConnectOptions.validateURI(str);
        if (validateURI == 0) {
            String substring = str.substring(6);
            String hostName = getHostName(substring);
            int port = getPort(substring, R2.attr.stl_dividerColors);
            if (socketFactory == null) {
                socketFactory = SocketFactory.getDefault();
            } else if (socketFactory instanceof SSLSocketFactory) {
                throw ExceptionHelper.createMqttException(32105);
            }
            TCPNetworkModule tCPNetworkModule = new TCPNetworkModule(socketFactory, hostName, port, this.clientId);
            tCPNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
            return tCPNetworkModule;
        } else if (validateURI == 1) {
            String substring2 = str.substring(6);
            String hostName2 = getHostName(substring2);
            int port2 = getPort(substring2, R2.drawable.general_six_square_last_item_bg);
            if (socketFactory == null) {
                SSLSocketFactoryFactory sSLSocketFactoryFactory3 = new SSLSocketFactoryFactory();
                Properties sSLProperties = mqttConnectOptions.getSSLProperties();
                if (sSLProperties != null) {
                    sSLSocketFactoryFactory3.initialize(sSLProperties, null);
                }
                sSLSocketFactoryFactory = sSLSocketFactoryFactory3;
                socketFactory = sSLSocketFactoryFactory3.createSocketFactory(null);
            } else if (!(socketFactory instanceof SSLSocketFactory)) {
                throw ExceptionHelper.createMqttException(32105);
            } else {
                sSLSocketFactoryFactory = null;
            }
            SSLNetworkModule sSLNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, hostName2, port2, this.clientId);
            sSLNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
            if (sSLSocketFactoryFactory != null && (enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null)) != null) {
                sSLNetworkModule.setEnabledCiphers(enabledCipherSuites);
            }
            return sSLNetworkModule;
        } else if (validateURI != 2) {
            if (validateURI == 3) {
                String substring3 = str.substring(5);
                String hostName3 = getHostName(substring3);
                int port3 = getPort(substring3, 80);
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, str, hostName3, port3, this.clientId);
                webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return webSocketNetworkModule;
            } else if (validateURI != 4) {
                return null;
            } else {
                String substring4 = str.substring(6);
                String hostName4 = getHostName(substring4);
                int port4 = getPort(substring4, 443);
                if (socketFactory == null) {
                    SSLSocketFactoryFactory sSLSocketFactoryFactory4 = new SSLSocketFactoryFactory();
                    Properties sSLProperties2 = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties2 != null) {
                        sSLSocketFactoryFactory4.initialize(sSLProperties2, null);
                    }
                    sSLSocketFactoryFactory2 = sSLSocketFactoryFactory4;
                    socketFactory = sSLSocketFactoryFactory4.createSocketFactory(null);
                } else if (!(socketFactory instanceof SSLSocketFactory)) {
                    throw ExceptionHelper.createMqttException(32105);
                } else {
                    sSLSocketFactoryFactory2 = null;
                }
                WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule((SSLSocketFactory) socketFactory, str, hostName4, port4, this.clientId);
                webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory2 != null && (enabledCipherSuites2 = sSLSocketFactoryFactory2.getEnabledCipherSuites(null)) != null) {
                    webSocketSecureNetworkModule.setEnabledCiphers(enabledCipherSuites2);
                }
                return webSocketSecureNetworkModule;
            }
        } else {
            return new LocalNetworkModule(str.substring(8));
        }
    }

    public static String generateClientId() {
        StringBuffer stringBuffer = new StringBuffer(CLIENT_ID_PREFIX);
        stringBuffer.append(System.nanoTime());
        return stringBuffer.toString();
    }

    private String getHostName(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            indexOf = str.indexOf(47);
        }
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    private int getPort(String str, int i2) {
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf == -1) {
            return i2;
        }
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return Integer.parseInt(str.substring(lastIndexOf + 1, indexOf));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rescheduleReconnectCycle(int i2) {
        log.fine(CLASS_NAME, "rescheduleReconnectCycle", "505", new Object[]{this.clientId, new Long(reconnectDelay)});
        this.reconnectTimer.schedule(new ReconnectTask(this, null), reconnectDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReconnectCycle() {
        log.fine(CLASS_NAME, "startReconnectCycle", "503", new Object[]{this.clientId, new Long(reconnectDelay)});
        StringBuffer stringBuffer = new StringBuffer("MQTT Reconnect: ");
        stringBuffer.append(this.clientId);
        Timer timer = new Timer(stringBuffer.toString());
        this.reconnectTimer = timer;
        timer.schedule(new ReconnectTask(this, null), reconnectDelay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopReconnectCycle() {
        log.fine(CLASS_NAME, "stopReconnectCycle", "504", new Object[]{this.clientId});
        this.reconnectTimer.cancel();
        reconnectDelay = 1000;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "ping", "117");
        MqttToken checkForActivity = this.comms.checkForActivity();
        logger.fine(str, "ping", "118");
        return checkForActivity;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void close() throws MqttException {
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "close", "113");
        this.comms.close();
        logger.fine(str, "close", "114");
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        log.fine(CLASS_NAME, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i2 = 0; i2 < serverURIs.length; i2++) {
            networkModuleArr[i2] = createNetworkModule(serverURIs[i2], mqttConnectOptions);
        }
        log.fine(CLASS_NAME, "createNetworkModules", CartBaseUtil.AB_KEY_108);
        return networkModuleArr;
    }

    public void deleteBufferedMessage(int i2) {
        this.comms.deleteBufferedMessage(i2);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(30000L, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(30000L, 10000L);
    }

    public MqttMessage getBufferedMessage(int i2) {
        return this.comms.getBufferedMessage(i2);
    }

    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getClientId() {
        return this.clientId;
    }

    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }

    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getServerURI() {
        return this.serverURI;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.topics.get(str);
        if (mqttTopic == null) {
            MqttTopic mqttTopic2 = new MqttTopic(str, this.comms);
            this.topics.put(str, mqttTopic2);
            return mqttTopic2;
        }
        return mqttTopic;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean isConnected() {
        return this.comms.isConnected();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void messageArrivedComplete(int i2, int i3) throws MqttException {
        this.comms.messageArrivedComplete(i2, i3);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i2, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i2);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    public void reconnect() throws MqttException {
        log.fine(CLASS_NAME, "reconnect", "500", new Object[]{this.clientId});
        if (!this.comms.isConnected()) {
            if (!this.comms.isConnecting()) {
                if (!this.comms.isDisconnecting()) {
                    if (!this.comms.isClosed()) {
                        stopReconnectCycle();
                        attemptReconnect();
                        return;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw ExceptionHelper.createMqttException(32100);
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setManualAcks(boolean z) {
        this.comms.setManualAcks(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i2, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i2}, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect() throws MqttException, MqttSecurityException {
        return connect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j2) throws MqttException {
        disconnectForcibly(30000L, j2);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i2) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i2}, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, (Object) null, (IMqttActionListener) null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this.reconnecting = false;
        log.setResourceName(str2);
        if (str2 != null) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < str2.length() - 1) {
                if (Character_isHighSurrogate(str2.charAt(i2))) {
                    i2++;
                }
                i3++;
                i2++;
            }
            if (i3 <= 65535) {
                MqttConnectOptions.validateURI(str);
                this.serverURI = str;
                this.clientId = str2;
                this.persistence = mqttClientPersistence;
                if (mqttClientPersistence == null) {
                    this.persistence = new MemoryPersistence();
                }
                log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
                this.persistence.open(str2, str);
                this.comms = new ClientComms(this, this.persistence, mqttPingSender);
                this.persistence.close();
                this.topics = new Hashtable();
                return;
            }
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        throw new IllegalArgumentException("Null clientId");
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        return connect(mqttConnectOptions, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j2) throws MqttException {
        return disconnect(j2, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j2, long j3) throws MqttException {
        this.comms.disconnectForcibly(j2, j3);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        if (!this.comms.isConnected()) {
            if (!this.comms.isConnecting()) {
                if (!this.comms.isDisconnecting()) {
                    if (!this.comms.isClosed()) {
                        this.connOpts = mqttConnectOptions;
                        this.userContext = obj;
                        mqttConnectOptions.isAutomaticReconnect();
                        Logger logger = log;
                        String str = CLASS_NAME;
                        Object[] objArr = new Object[8];
                        objArr[0] = Boolean.valueOf(mqttConnectOptions.isCleanSession());
                        objArr[1] = new Integer(mqttConnectOptions.getConnectionTimeout());
                        objArr[2] = new Integer(mqttConnectOptions.getKeepAliveInterval());
                        objArr[3] = mqttConnectOptions.getUserName();
                        objArr[4] = mqttConnectOptions.getPassword() == null ? "[null]" : "[notnull]";
                        objArr[5] = mqttConnectOptions.getWillMessage() != null ? "[notnull]" : "[null]";
                        objArr[6] = obj;
                        objArr[7] = iMqttActionListener;
                        logger.fine(str, "connect", SpecialMtaConstants.JDReact_ModuleUpgradeFailed, objArr);
                        this.comms.setNetworkModules(createNetworkModules(this.serverURI, mqttConnectOptions));
                        this.comms.setReconnectCallback(new MqttCallbackExtended
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0097: INVOKE 
                              (wrap: org.eclipse.paho.client.mqttv3.internal.ClientComms : 0x0090: IGET (r13v0 'this' org.eclipse.paho.client.mqttv3.MqttAsyncClient A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:20) org.eclipse.paho.client.mqttv3.MqttAsyncClient.comms org.eclipse.paho.client.mqttv3.internal.ClientComms)
                              (wrap: org.eclipse.paho.client.mqttv3.MqttCallbackExtended : 0x0094: CONSTRUCTOR 
                              (r13v0 'this' org.eclipse.paho.client.mqttv3.MqttAsyncClient A[IMMUTABLE_TYPE, THIS])
                              (r0 I:boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                             A[MD:(org.eclipse.paho.client.mqttv3.MqttAsyncClient, boolean):void (m), WRAPPED] call: org.eclipse.paho.client.mqttv3.MqttAsyncClient.1.<init>(org.eclipse.paho.client.mqttv3.MqttAsyncClient, boolean):void type: CONSTRUCTOR)
                             type: VIRTUAL call: org.eclipse.paho.client.mqttv3.internal.ClientComms.setReconnectCallback(org.eclipse.paho.client.mqttv3.MqttCallbackExtended):void A[MD:(org.eclipse.paho.client.mqttv3.MqttCallbackExtended):void (m)] (LINE:20) in method: org.eclipse.paho.client.mqttv3.MqttAsyncClient.connect(org.eclipse.paho.client.mqttv3.MqttConnectOptions, java.lang.Object, org.eclipse.paho.client.mqttv3.IMqttActionListener):org.eclipse.paho.client.mqttv3.IMqttToken, file: classes11.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                            Caused by: java.lang.NullPointerException
                            */
                        /*
                            Method dump skipped, instructions count: 238
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.MqttAsyncClient.connect(org.eclipse.paho.client.mqttv3.MqttConnectOptions, java.lang.Object, org.eclipse.paho.client.mqttv3.IMqttActionListener):org.eclipse.paho.client.mqttv3.IMqttToken");
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken disconnect(long j2, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
                        Logger logger = log;
                        String str = CLASS_NAME;
                        logger.fine(str, "disconnect", "104", new Object[]{new Long(j2), obj, iMqttActionListener});
                        MqttToken mqttToken = new MqttToken(getClientId());
                        mqttToken.setActionCallback(iMqttActionListener);
                        mqttToken.setUserContext(obj);
                        try {
                            this.comms.disconnect(new MqttDisconnect(), j2, mqttToken);
                            logger.fine(str, "disconnect", CartBaseUtil.AB_KEY_108);
                            return mqttToken;
                        } catch (MqttException e2) {
                            log.fine(CLASS_NAME, "disconnect", "105", null, e2);
                            throw e2;
                        }
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
                        if (strArr.length == iArr.length) {
                            for (String str : strArr) {
                                this.comms.removeMessageListener(str);
                            }
                            String str2 = "";
                            for (int i2 = 0; i2 < strArr.length; i2++) {
                                if (i2 > 0) {
                                    StringBuffer stringBuffer = new StringBuffer(String.valueOf(str2));
                                    stringBuffer.append(", ");
                                    str2 = stringBuffer.toString();
                                }
                                StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str2));
                                stringBuffer2.append("topic=");
                                stringBuffer2.append(strArr[i2]);
                                stringBuffer2.append(" qos=");
                                stringBuffer2.append(iArr[i2]);
                                str2 = stringBuffer2.toString();
                                MqttTopic.validate(strArr[i2], true);
                            }
                            Logger logger = log;
                            String str3 = CLASS_NAME;
                            logger.fine(str3, "subscribe", "106", new Object[]{str2, obj, iMqttActionListener});
                            MqttToken mqttToken = new MqttToken(getClientId());
                            mqttToken.setActionCallback(iMqttActionListener);
                            mqttToken.setUserContext(obj);
                            mqttToken.internalTok.setTopics(strArr);
                            this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
                            logger.fine(str3, "subscribe", "109");
                            return mqttToken;
                        }
                        throw new IllegalArgumentException();
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
                        String str = "";
                        for (int i2 = 0; i2 < strArr.length; i2++) {
                            if (i2 > 0) {
                                StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
                                stringBuffer.append(", ");
                                str = stringBuffer.toString();
                            }
                            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str));
                            stringBuffer2.append(strArr[i2]);
                            str = stringBuffer2.toString();
                            MqttTopic.validate(strArr[i2], true);
                        }
                        log.fine(CLASS_NAME, "unsubscribe", "107", new Object[]{str, obj, iMqttActionListener});
                        for (String str2 : strArr) {
                            this.comms.removeMessageListener(str2);
                        }
                        MqttToken mqttToken = new MqttToken(getClientId());
                        mqttToken.setActionCallback(iMqttActionListener);
                        mqttToken.setUserContext(obj);
                        mqttToken.internalTok.setTopics(strArr);
                        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
                        log.fine(CLASS_NAME, "unsubscribe", "110");
                        return mqttToken;
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttDeliveryToken publish(String str, byte[] bArr, int i2, boolean z) throws MqttException, MqttPersistenceException {
                        return publish(str, bArr, i2, z, null, null);
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
                        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
                        Logger logger = log;
                        String str2 = CLASS_NAME;
                        logger.fine(str2, "publish", "111", new Object[]{str, obj, iMqttActionListener});
                        MqttTopic.validate(str, false);
                        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
                        mqttDeliveryToken.setActionCallback(iMqttActionListener);
                        mqttDeliveryToken.setUserContext(obj);
                        mqttDeliveryToken.setMessage(mqttMessage);
                        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
                        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
                        logger.fine(str2, "publish", "112");
                        return mqttDeliveryToken;
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken subscribe(String str, int i2, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) throws MqttException {
                        return subscribe(new String[]{str}, new int[]{i2}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken subscribe(String str, int i2, IMqttMessageListener iMqttMessageListener) throws MqttException {
                        return subscribe(new String[]{str}, new int[]{i2}, (Object) null, (IMqttActionListener) null, new IMqttMessageListener[]{iMqttMessageListener});
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
                        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
                    }

                    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
                    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
                        if (iMqttMessageListenerArr.length == iArr.length && iArr.length == strArr.length) {
                            IMqttToken subscribe = subscribe(strArr, iArr, obj, iMqttActionListener);
                            for (int i2 = 0; i2 < strArr.length; i2++) {
                                this.comms.setMessageListener(strArr[i2], iMqttMessageListenerArr[i2]);
                            }
                            return subscribe;
                        }
                        throw new IllegalArgumentException();
                    }
                }
