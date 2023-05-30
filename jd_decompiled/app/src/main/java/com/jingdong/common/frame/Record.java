package com.jingdong.common.frame;

import android.content.ComponentName;
import android.content.Intent;

/* loaded from: classes5.dex */
public class Record {
    private String component;
    private String id;
    private Intent intent;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Record record = (Record) obj;
            String str = this.component;
            if (str == null) {
                if (record.component != null) {
                    return false;
                }
            } else if (!str.equals(record.component)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public int hashCode() {
        String str = this.component;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setIntent(Intent intent) {
        ComponentName component;
        this.intent = intent;
        if (intent == null || (component = intent.getComponent()) == null) {
            return;
        }
        this.component = component.getClassName();
    }

    public String toString() {
        return "Record [id=" + this.id + ", intent=" + this.intent + ", compentName=" + this.component + "]";
    }
}
