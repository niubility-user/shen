package org.eclipse.paho.client.mqttv3;

/* loaded from: classes11.dex */
public class MqttMessage {
    private int messageId;
    private byte[] payload;
    private boolean mutable = true;
    private int qos = 1;
    private boolean retained = false;
    private boolean dup = false;

    public MqttMessage() {
        setPayload(new byte[0]);
    }

    public static void validateQos(int i2) {
        if (i2 < 0 || i2 > 2) {
            throw new IllegalArgumentException();
        }
    }

    protected void checkMutable() throws IllegalStateException {
        if (!this.mutable) {
            throw new IllegalStateException();
        }
    }

    public void clearPayload() {
        checkMutable();
        this.payload = new byte[0];
    }

    public int getId() {
        return this.messageId;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public int getQos() {
        return this.qos;
    }

    public boolean isDuplicate() {
        return this.dup;
    }

    public boolean isRetained() {
        return this.retained;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDuplicate(boolean z) {
        this.dup = z;
    }

    public void setId(int i2) {
        this.messageId = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setMutable(boolean z) {
        this.mutable = z;
    }

    public void setPayload(byte[] bArr) {
        checkMutable();
        bArr.getClass();
        this.payload = bArr;
    }

    public void setQos(int i2) {
        checkMutable();
        validateQos(i2);
        this.qos = i2;
    }

    public void setRetained(boolean z) {
        checkMutable();
        this.retained = z;
    }

    public String toString() {
        return new String(this.payload);
    }

    public MqttMessage(byte[] bArr) {
        setPayload(bArr);
    }
}
