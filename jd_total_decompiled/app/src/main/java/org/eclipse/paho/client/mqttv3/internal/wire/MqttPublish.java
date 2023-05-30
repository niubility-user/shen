package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* loaded from: classes11.dex */
public class MqttPublish extends MqttPersistableWireMessage {
    private byte[] encodedPayload;
    private MqttMessage message;
    private String topicName;

    public MqttPublish(String str, MqttMessage mqttMessage) {
        super((byte) 3);
        this.encodedPayload = null;
        this.topicName = str;
        this.message = mqttMessage;
    }

    protected static byte[] encodePayload(MqttMessage mqttMessage) {
        return mqttMessage.getPayload();
    }

    public MqttMessage getMessage() {
        return this.message;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte getMessageInfo() {
        byte qos = (byte) (this.message.getQos() << 1);
        if (this.message.isRetained()) {
            qos = (byte) (qos | 1);
        }
        return (this.message.isDuplicate() || this.duplicate) ? (byte) (qos | 8) : qos;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public byte[] getPayload() throws MqttException {
        if (this.encodedPayload == null) {
            this.encodedPayload = encodePayload(this.message);
        }
        return this.encodedPayload;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttPersistableWireMessage, org.eclipse.paho.client.mqttv3.MqttPersistable
    public int getPayloadLength() {
        try {
            return getPayload().length;
        } catch (MqttException unused) {
            return 0;
        }
    }

    public String getTopicName() {
        return this.topicName;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() throws MqttException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            encodeUTF8(dataOutputStream, this.topicName);
            if (this.message.getQos() > 0) {
                dataOutputStream.writeShort(this.msgId);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new MqttException(e2);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return true;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public void setMessageId(int i2) {
        super.setMessageId(i2);
        MqttMessage mqttMessage = this.message;
        if (mqttMessage instanceof MqttReceivedMessage) {
            ((MqttReceivedMessage) mqttMessage).setMessageId(i2);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] payload = this.message.getPayload();
        int min = Math.min(payload.length, 20);
        for (int i2 = 0; i2 < min; i2++) {
            String hexString = Integer.toHexString(payload[i2]);
            if (hexString.length() == 1) {
                StringBuffer stringBuffer2 = new StringBuffer("0");
                stringBuffer2.append(hexString);
                hexString = stringBuffer2.toString();
            }
            stringBuffer.append(hexString);
        }
        try {
            str = new String(payload, 0, min, "UTF-8");
        } catch (Exception unused) {
            str = "?";
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(super.toString());
        stringBuffer3.append(" qos:");
        stringBuffer3.append(this.message.getQos());
        if (this.message.getQos() > 0) {
            stringBuffer3.append(" msgId:");
            stringBuffer3.append(this.msgId);
        }
        stringBuffer3.append(" retained:");
        stringBuffer3.append(this.message.isRetained());
        stringBuffer3.append(" dup:");
        stringBuffer3.append(this.duplicate);
        stringBuffer3.append(" topic:\"");
        stringBuffer3.append(this.topicName);
        stringBuffer3.append("\"");
        stringBuffer3.append(" payload:[hex:");
        stringBuffer3.append(stringBuffer);
        stringBuffer3.append(" utf8:\"");
        stringBuffer3.append(str);
        stringBuffer3.append("\"");
        stringBuffer3.append(" length:");
        stringBuffer3.append(payload.length);
        stringBuffer3.append("]");
        return stringBuffer3.toString();
    }

    public MqttPublish(byte b, byte[] bArr) throws MqttException, IOException {
        super((byte) 3);
        this.encodedPayload = null;
        MqttReceivedMessage mqttReceivedMessage = new MqttReceivedMessage();
        this.message = mqttReceivedMessage;
        mqttReceivedMessage.setQos(3 & (b >> 1));
        if ((b & 1) == 1) {
            this.message.setRetained(true);
        }
        if ((b & 8) == 8) {
            ((MqttReceivedMessage) this.message).setDuplicate(true);
        }
        CountingInputStream countingInputStream = new CountingInputStream(new ByteArrayInputStream(bArr));
        DataInputStream dataInputStream = new DataInputStream(countingInputStream);
        this.topicName = decodeUTF8(dataInputStream);
        if (this.message.getQos() > 0) {
            this.msgId = dataInputStream.readUnsignedShort();
        }
        byte[] bArr2 = new byte[bArr.length - countingInputStream.getCounter()];
        dataInputStream.readFully(bArr2);
        dataInputStream.close();
        this.message.setPayload(bArr2);
    }
}
