package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;

/* loaded from: classes11.dex */
public class LocalNetworkModule implements NetworkModule {
    static /* synthetic */ Class class$0;
    private String brokerName;
    private Object localAdapter;
    private Class localListener;

    public LocalNetworkModule(String str) {
        this.brokerName = str;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        try {
            return (InputStream) this.localListener.getMethod("getClientInputStream", new Class[0]).invoke(this.localAdapter, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        try {
            return (OutputStream) this.localListener.getMethod("getClientOutputStream", new Class[0]).invoke(this.localAdapter, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        StringBuffer stringBuffer = new StringBuffer("local://");
        stringBuffer.append(this.brokerName);
        return stringBuffer.toString();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        if (ExceptionHelper.isClassAvailable("com.ibm.mqttdirect.modules.local.bindings.localListener")) {
            try {
                Class<?> cls = Class.forName("com.ibm.mqttdirect.modules.local.bindings.localListener");
                this.localListener = cls;
                Class<?>[] clsArr = new Class[1];
                Class<?> cls2 = class$0;
                if (cls2 == null) {
                    try {
                        cls2 = Class.forName("java.lang.String");
                        class$0 = cls2;
                    } catch (ClassNotFoundException e2) {
                        throw new NoClassDefFoundError(e2.getMessage());
                    }
                }
                clsArr[0] = cls2;
                this.localAdapter = cls.getMethod("connect", clsArr).invoke(null, this.brokerName);
            } catch (Exception unused) {
            }
            if (this.localAdapter == null) {
                throw ExceptionHelper.createMqttException(32103);
            }
            return;
        }
        throw ExceptionHelper.createMqttException(32103);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        if (this.localAdapter != null) {
            try {
                this.localListener.getMethod("close", new Class[0]).invoke(this.localAdapter, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }
}
