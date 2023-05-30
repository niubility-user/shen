package com.jingdong.common.messagecenter;

/* loaded from: classes5.dex */
public class MIPushMsg {
    public static final String APP_ID = "APPID";
    public static final String DEVTYPE = "DEVTYPE";
    public static final String ECHO = "ECHO";
    public static final String MSGTYPE = "MSGTYPE";
    public static final String MSG_BODY = "MSG";
    public static final String MSG_ID = "MSGID";
    public static final String MSG_SEQ = "MSGSEQ";
    public static final String SERIAL_NO = "SERIAL_NO";
    public static final String SET_ID = "SETID";
    private int appId;
    private int devType;
    private String echo;
    private String msg;
    private String msgId;
    private String msgSeq;
    private int msgType;
    private int serialNo;
    private int setId;

    public int getAppId() {
        return this.appId;
    }

    public int getDevType() {
        return this.devType;
    }

    public String getEcho() {
        return this.echo;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public String getMsgSeq() {
        return this.msgSeq;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public int getSerialNo() {
        return this.serialNo;
    }

    public int getSetId() {
        return this.setId;
    }

    public void setAppId(int i2) {
        this.appId = i2;
    }

    public void setDevType(int i2) {
        this.devType = i2;
    }

    public void setEcho(String str) {
        this.echo = str;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public void setMsgSeq(String str) {
        this.msgSeq = str;
    }

    public void setMsgType(int i2) {
        this.msgType = i2;
    }

    public void setSerialNo(int i2) {
        this.serialNo = i2;
    }

    public void setSetId(int i2) {
        this.setId = i2;
    }
}
