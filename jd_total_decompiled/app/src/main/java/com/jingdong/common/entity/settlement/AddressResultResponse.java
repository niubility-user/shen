package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressResultResponse implements Serializable {
    public String ErrorType;
    public boolean Flag;
    public String Message;
    public String addressUUID;
    public AddressUnknowInfo unKnowInfo;

    public String getMessage() {
        return TextUtils.isEmpty(this.Message) ? "" : this.Message;
    }
}
