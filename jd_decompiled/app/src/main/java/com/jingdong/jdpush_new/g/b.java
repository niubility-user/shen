package com.jingdong.jdpush_new.g;

import java.io.Serializable;

/* loaded from: classes12.dex */
public class b implements Serializable {
    private static final long serialVersionUID = -4746260107384254289L;
    private short command;
    private String msgBody;

    public b() {
    }

    public short getCommand() {
        return this.command;
    }

    public String getMsgBody() {
        return this.msgBody;
    }

    public void setCommand(short s) {
        this.command = s;
    }

    public void setMsgBody(String str) {
        this.msgBody = str;
    }

    public String toString() {
        return "command = " + ((int) this.command) + " msgBody = " + this.msgBody;
    }

    public b(short s, String str) {
        this.command = s;
        this.msgBody = str;
    }
}
