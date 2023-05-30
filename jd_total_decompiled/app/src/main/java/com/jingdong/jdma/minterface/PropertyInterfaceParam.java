package com.jingdong.jdma.minterface;

import com.tencent.connect.common.Constants;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class PropertyInterfaceParam extends BaseEvent {
    public String cdn_ip;
    public String end_ts;
    public String ldns_ip;
    public HashMap<String, String> map;
    public String mload_endts;
    public String mload_status;
    public String mload_ts;
    public String mload_type;
    public String mload_url;
    public String page_name;
    public String page_param;
    public String page_ts;
    public String pic_endts;
    public String pic_size;
    public String pic_ts;
    public String pic_url;

    @Override // com.jingdong.jdma.minterface.BaseEvent
    protected void addDataToMap(HashMap<String, String> hashMap) {
        hashMap.put("ctm", this.page_ts);
        hashMap.put("ctp", this.page_name);
        hashMap.put("par", this.page_param);
        hashMap.put("end_ts", this.end_ts);
        hashMap.put("pic_ts", this.pic_ts);
        hashMap.put("pic_url", this.pic_url);
        hashMap.put("pic_endts", this.pic_endts);
        hashMap.put("pic_size", this.pic_size);
        hashMap.put("cdn_ip", this.cdn_ip);
        hashMap.put("ldns_ip", this.ldns_ip);
        hashMap.put("mload_ts", this.mload_ts);
        hashMap.put("mload_endts", this.mload_endts);
        hashMap.put("mload_status", this.mload_status);
        hashMap.put("mload_type", this.mload_type);
        hashMap.put("mload_url", this.mload_url);
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLogType() {
        return "sr";
    }

    @Override // com.jingdong.jdma.minterface.BaseEvent
    public final String getLts() {
        return Constants.PARAM_PLATFORM_ID;
    }
}
