package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class EggConfigInfo {
    private HashMap<String, ArrayList<EggDataConfigInfo>> dataMap;

    public static EggConfigInfo parseData(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || jDJSONObject.size() <= 0) {
            return null;
        }
        EggConfigInfo eggConfigInfo = new EggConfigInfo();
        HashMap<String, ArrayList<EggDataConfigInfo>> hashMap = new HashMap<>(jDJSONObject.size());
        Iterator<String> it = jDJSONObject.keySet().iterator();
        while (it.hasNext()) {
            String obj = it.next().toString();
            ArrayList<EggDataConfigInfo> parseArray = EggDataConfigInfo.parseArray(jDJSONObject.optJSONArray(obj));
            if (parseArray != null && !parseArray.isEmpty()) {
                hashMap.put(obj, parseArray);
            }
        }
        eggConfigInfo.setDataMap(hashMap);
        return eggConfigInfo;
    }

    public HashMap<String, ArrayList<EggDataConfigInfo>> getDataConfigMap() {
        return this.dataMap;
    }

    public ArrayList<EggDataConfigInfo> getTodayConfig() {
        if (getDataConfigMap() != null && !getDataConfigMap().isEmpty()) {
            String format = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(System.currentTimeMillis()));
            if (getDataConfigMap().containsKey(format)) {
                return getDataConfigMap().get(format);
            }
        }
        return null;
    }

    public void setDataMap(HashMap<String, ArrayList<EggDataConfigInfo>> hashMap) {
        this.dataMap = hashMap;
    }
}
