package com.jingdong.common.video.cache;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class TalentBaseTEntity implements Serializable {
    private static final long serialVersionUID = 3654908490459042853L;
    public String businessCode;
    public String code;
    public String msg;
    public int source;

    public int getSource() {
        return this.source;
    }

    public boolean isSuccessfully() {
        String str = this.code;
        if (str == null) {
            return false;
        }
        return "0".equals(str.trim());
    }
}
