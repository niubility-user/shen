package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class DeleteAddress implements Serializable {
    public boolean Flag;
    public String Message;

    public String getMessage() {
        return TextUtils.isEmpty(this.Message) ? "" : this.Message;
    }
}
