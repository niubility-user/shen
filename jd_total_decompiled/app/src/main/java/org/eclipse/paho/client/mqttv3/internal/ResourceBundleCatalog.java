package org.eclipse.paho.client.mqttv3.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* loaded from: classes11.dex */
public class ResourceBundleCatalog extends MessageCatalog {
    private ResourceBundle bundle = ResourceBundle.getBundle("org.eclipse.paho.client.mqttv3.internal.nls.messages");

    @Override // org.eclipse.paho.client.mqttv3.internal.MessageCatalog
    protected String getLocalizedMessage(int i2) {
        try {
            return this.bundle.getString(Integer.toString(i2));
        } catch (MissingResourceException unused) {
            return "MqttException";
        }
    }
}
