package com.jingdong.common.utils;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class MessageId implements Serializable {
    private static final long serialVersionUID = -2136109042439866077L;
    public String msgId;

    public MessageId(String str) {
        this.msgId = str;
    }

    public String getMsgId() {
        return this.msgId;
    }
}
