package com.jingdong.common.entity;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class BaseMessage implements Serializable {
    public static final String MSG_ID_KEY = "msgId";
    public static final int READED = 1;
    public static final String STATUS_KEY = "status";
    public static final int UN_READ = 0;
    private static final long serialVersionUID = 2432931587235996394L;
    protected String msgId;
    protected Integer status;

    public BaseMessage(String str, Integer num) {
        this.status = num;
        this.msgId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BaseMessage baseMessage = (BaseMessage) obj;
            String str = this.msgId;
            if (str == null) {
                if (baseMessage.msgId != null) {
                    return false;
                }
            } else if (!str.equals(baseMessage.msgId)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getMsgId() {
        String str = this.msgId;
        return str == null ? "" : str;
    }

    public Integer getStatus() {
        if (this.status == null) {
            this.status = 0;
        }
        return this.status;
    }

    public int hashCode() {
        String str = this.msgId;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public BaseMessage() {
    }
}
