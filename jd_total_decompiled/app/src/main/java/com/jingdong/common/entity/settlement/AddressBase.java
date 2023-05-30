package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class AddressBase implements Serializable {
    public int id;
    public String name;

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }
}
