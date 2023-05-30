package org.eclipse.paho.client.mqttv3.internal;

import com.jd.dynamic.DYConstants;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes11.dex */
public class SSLNetworkModule extends TCPNetworkModule {
    private static final String CLASS_NAME;
    static /* synthetic */ Class class$0;
    private static final Logger log;
    private String[] enabledCiphers;
    private int handshakeTimeoutSecs;
    private String host;
    private int port;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule");
                class$0 = cls;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public SSLNetworkModule(SSLSocketFactory sSLSocketFactory, String str, int i2, String str2) {
        super(sSLSocketFactory, str, i2, str2);
        this.host = str;
        this.port = i2;
        log.setResourceName(str2);
    }

    public String[] getEnabledCiphers() {
        return this.enabledCiphers;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        StringBuffer stringBuffer = new StringBuffer("ssl://");
        stringBuffer.append(this.host);
        stringBuffer.append(":");
        stringBuffer.append(this.port);
        return stringBuffer.toString();
    }

    public void setEnabledCiphers(String[] strArr) {
        this.enabledCiphers = strArr;
        if (this.socket == null || strArr == null) {
            return;
        }
        if (log.isLoggable(5)) {
            String str = "";
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (i2 > 0) {
                    StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
                    stringBuffer.append(DYConstants.DY_REGEX_COMMA);
                    str = stringBuffer.toString();
                }
                StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str));
                stringBuffer2.append(strArr[i2]);
                str = stringBuffer2.toString();
            }
            log.fine(CLASS_NAME, "setEnabledCiphers", "260", new Object[]{str});
        }
        ((SSLSocket) this.socket).setEnabledCipherSuites(strArr);
    }

    public void setSSLhandshakeTimeout(int i2) {
        super.setConnectTimeout(i2);
        this.handshakeTimeoutSecs = i2;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        super.start();
        setEnabledCiphers(this.enabledCiphers);
        int soTimeout = this.socket.getSoTimeout();
        if (soTimeout == 0) {
            this.socket.setSoTimeout(this.handshakeTimeoutSecs * 1000);
        }
        ((SSLSocket) this.socket).startHandshake();
        this.socket.setSoTimeout(soTimeout);
    }
}
