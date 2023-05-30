package com.jingdong.common.web;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes6.dex */
public class WebDebugEntry {
    public String msg;
    public String object;
    public long occurTime;
    public String tag;

    public WebDebugEntry(String str, String str2, long j2) {
        this.msg = str2;
        this.tag = str;
        this.occurTime = j2;
    }

    public String toString() {
        return String.format("%s---%s(%s): %s", SimpleDateFormat.getDateTimeInstance().format(new Date(this.occurTime)), this.tag, this.object, this.msg);
    }

    public WebDebugEntry(String str, String str2, long j2, Object obj) {
        this.msg = str2;
        this.tag = str;
        this.occurTime = j2;
        this.object = obj == null ? "" : obj.getClass().getSimpleName();
    }
}
