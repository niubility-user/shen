package com.jingdong.jdma.minterface;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.jdma.common.utils.c;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.HashMap;
import java.util.UUID;

/* loaded from: classes12.dex */
public abstract class BaseEvent {
    public static final String EP_FLAG = "true_exp";
    public static final String EP_TRUE = "1";
    public static final String LBS_SCENE_ID = "lbs_scene_id";
    public static final String QUICK = "quick";
    public static final String SCENE = "scene";
    public String ctm;
    public String ep_flag;
    public String lat;
    public String lon;
    public String pin;
    public String scene;

    protected abstract void addDataToMap(HashMap<String, String> hashMap);

    public abstract String getLogType();

    public abstract String getLts();

    public final HashMap<String, String> toMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("jdma_log_uuid", UUID.randomUUID().toString());
        hashMap.put("pin", this.pin);
        hashMap.put(JDMtaUtils.LON, this.lon);
        hashMap.put("lat", this.lat);
        hashMap.put(SCENE, this.scene);
        if (!TextUtils.isEmpty(this.ep_flag)) {
            hashMap.put(EP_FLAG, this.ep_flag);
        }
        if (!TextUtils.isEmpty(getLts())) {
            hashMap.put("lts", getLts());
        }
        hashMap.put(EtModelMaker.KEY_TYP, getLogType());
        if (!TextUtils.isEmpty(this.ctm)) {
            hashMap.put("ctm", this.ctm);
        } else {
            hashMap.put("ctm", System.currentTimeMillis() + "");
        }
        hashMap.put("imei_tag", c.f12684n);
        hashMap.put("mode_tag", c.q);
        hashMap.put("areaCode", c.D);
        hashMap.put("ma_is_sparse", c.r);
        hashMap.put("ma_b_group", c.s);
        hashMap.put("ma_ab_test", c.t);
        addDataToMap(hashMap);
        return hashMap;
    }
}
