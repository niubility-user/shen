package org.eclipse.paho.client.mqttv3;

import com.jd.dynamic.DYConstants;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.util.Debug;

/* loaded from: classes11.dex */
public class MqttConnectOptions {
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    public static final int MAX_INFLIGHT_DEFAULT = 10;
    public static final int MQTT_VERSION_3_1 = 3;
    public static final int MQTT_VERSION_3_1_1 = 4;
    public static final int MQTT_VERSION_DEFAULT = 0;
    protected static final int URI_TYPE_LOCAL = 2;
    protected static final int URI_TYPE_SSL = 1;
    protected static final int URI_TYPE_TCP = 0;
    protected static final int URI_TYPE_WS = 3;
    protected static final int URI_TYPE_WSS = 4;
    private char[] password;
    private SocketFactory socketFactory;
    private String userName;
    private int keepAliveInterval = 60;
    private int maxInflight = 10;
    private String willDestination = null;
    private MqttMessage willMessage = null;
    private Properties sslClientProps = null;
    private boolean cleanSession = true;
    private int connectionTimeout = 30;
    private String[] serverURIs = null;
    private int MqttVersion = 0;
    private boolean automaticReconnect = false;

    public static int validateURI(String str) {
        try {
            URI uri = new URI(str);
            if (uri.getScheme().equals("ws")) {
                return 3;
            }
            if (uri.getScheme().equals("wss")) {
                return 4;
            }
            if (uri.getPath().equals("")) {
                if (uri.getScheme().equals("tcp")) {
                    return 0;
                }
                if (uri.getScheme().equals("ssl")) {
                    return 1;
                }
                if (uri.getScheme().equals("local")) {
                    return 2;
                }
                throw new IllegalArgumentException(str);
            }
            throw new IllegalArgumentException(str);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException(str);
        }
    }

    private void validateWill(String str, Object obj) {
        if (str != null && obj != null) {
            MqttTopic.validate(str, false);
            return;
        }
        throw new IllegalArgumentException();
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("MqttVersion", new Integer(getMqttVersion()));
        properties.put("CleanSession", Boolean.valueOf(isCleanSession()));
        properties.put("ConTimeout", new Integer(getConnectionTimeout()));
        properties.put("KeepAliveInterval", new Integer(getKeepAliveInterval()));
        properties.put("UserName", getUserName() == null ? DYConstants.DY_NULL_STR : getUserName());
        properties.put("WillDestination", getWillDestination() == null ? DYConstants.DY_NULL_STR : getWillDestination());
        if (getSocketFactory() == null) {
            properties.put("SocketFactory", DYConstants.DY_NULL_STR);
        } else {
            properties.put("SocketFactory", getSocketFactory());
        }
        if (getSSLProperties() == null) {
            properties.put("SSLProperties", DYConstants.DY_NULL_STR);
        } else {
            properties.put("SSLProperties", getSSLProperties());
        }
        return properties;
    }

    public int getKeepAliveInterval() {
        return this.keepAliveInterval;
    }

    public int getMaxInflight() {
        return this.maxInflight;
    }

    public int getMqttVersion() {
        return this.MqttVersion;
    }

    public char[] getPassword() {
        return this.password;
    }

    public Properties getSSLProperties() {
        return this.sslClientProps;
    }

    public String[] getServerURIs() {
        return this.serverURIs;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getWillDestination() {
        return this.willDestination;
    }

    public MqttMessage getWillMessage() {
        return this.willMessage;
    }

    public boolean isAutomaticReconnect() {
        return this.automaticReconnect;
    }

    public boolean isCleanSession() {
        return this.cleanSession;
    }

    public void setAutomaticReconnect(boolean z) {
        this.automaticReconnect = z;
    }

    public void setCleanSession(boolean z) {
        this.cleanSession = z;
    }

    public void setConnectionTimeout(int i2) {
        if (i2 >= 0) {
            this.connectionTimeout = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setKeepAliveInterval(int i2) throws IllegalArgumentException {
        if (i2 >= 0) {
            this.keepAliveInterval = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setMaxInflight(int i2) {
        if (i2 >= 0) {
            this.maxInflight = i2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setMqttVersion(int i2) throws IllegalArgumentException {
        if (i2 != 0 && i2 != 3 && i2 != 4) {
            throw new IllegalArgumentException();
        }
        this.MqttVersion = i2;
    }

    public void setPassword(char[] cArr) {
        this.password = cArr;
    }

    public void setSSLProperties(Properties properties) {
        this.sslClientProps = properties;
    }

    public void setServerURIs(String[] strArr) {
        for (String str : strArr) {
            validateURI(str);
        }
        this.serverURIs = strArr;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    public void setUserName(String str) {
        if (str != null && str.trim().equals("")) {
            throw new IllegalArgumentException();
        }
        this.userName = str;
    }

    public void setWill(MqttTopic mqttTopic, byte[] bArr, int i2, boolean z) {
        String name = mqttTopic.getName();
        validateWill(name, bArr);
        setWill(name, new MqttMessage(bArr), i2, z);
    }

    public String toString() {
        return Debug.dumpProperties(getDebug(), "Connection options");
    }

    public void setWill(String str, byte[] bArr, int i2, boolean z) {
        validateWill(str, bArr);
        setWill(str, new MqttMessage(bArr), i2, z);
    }

    protected void setWill(String str, MqttMessage mqttMessage, int i2, boolean z) {
        this.willDestination = str;
        this.willMessage = mqttMessage;
        mqttMessage.setQos(i2);
        this.willMessage.setRetained(z);
        this.willMessage.setMutable(false);
    }
}
