package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import com.jingdong.common.entity.AutoCompleteAddress;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class AddressSearchAllData implements Serializable {
    private List<AutoCompleteAddress> data;
    public String key;
    public String request_id;

    public List<AutoCompleteAddress> getData() {
        List<AutoCompleteAddress> list = this.data;
        return list == null ? new ArrayList() : list;
    }

    public String getKey() {
        return TextUtils.isEmpty(this.key) ? "" : this.key;
    }

    public String getRequest_id() {
        return TextUtils.isEmpty(this.request_id) ? "" : this.request_id;
    }

    public void setData(List<AutoCompleteAddress> list) {
        this.data = list;
    }
}
