package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.iservice.IEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes14.dex */
public class MtaManager {
    private Map<String, Set<String>> expoMap = new ArrayMap();
    private Map<String, Integer> expoMaxLengths = new HashMap();

    private String getExpoEventParam(Set<String> set, String str) {
        if (set.isEmpty()) {
            return null;
        }
        Iterator<String> it = set.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            if (!TextUtils.isEmpty(sb)) {
                sb.append(CartConstant.KEY_YB_INFO_LINK);
            }
            sb.append(it.next());
        }
        set.clear();
        return sb.toString();
    }

    private void sendExpo(Context context, String str, String str2, String str3, String str4) {
        IEvent iEvent = (IEvent) BabelServer.get().getService(IEvent.class);
        if (iEvent != null) {
            iEvent.sendExpo(context, MtaData.Builder.from(str3, str4).page(str, "").pageId(str2).build());
        }
    }

    private void sendExposureDataByGroup(Set<String> set, String str, Context context, String str2, String str3, Integer num) {
        if (set.isEmpty()) {
            return;
        }
        int intValue = num == null ? 0 : num.intValue();
        int size = (intValue <= 0 || intValue >= 20000) ? set.size() : 20000 / (intValue + 1);
        Iterator<String> it = set.iterator();
        StringBuilder sb = new StringBuilder();
        loop0: while (true) {
            int i2 = 0;
            while (it.hasNext()) {
                if (!TextUtils.isEmpty(sb)) {
                    sb.append(CartConstant.KEY_YB_INFO_LINK);
                }
                sb.append(it.next());
                i2++;
                if (i2 >= size) {
                    break;
                }
            }
            sendExpo(context, str2, str3, str, sb.toString());
            sb = new StringBuilder();
        }
        if (!TextUtils.isEmpty(sb)) {
            sendExpo(context, str2, str3, str, sb.toString());
        }
        set.clear();
    }

    public void sendAllExpoMta(Context context, String str, String str2) {
        for (Map.Entry<String, Set<String>> entry : this.expoMap.entrySet()) {
            String key = entry.getKey();
            Set<String> value = entry.getValue();
            if (!value.isEmpty()) {
                if (this.expoMaxLengths.containsKey(key)) {
                    sendExposureDataByGroup(value, key, context, str, str2, this.expoMaxLengths.get(key));
                } else {
                    sendExpo(context, str, str2, key, getExpoEventParam(value, key));
                }
            }
        }
    }

    public void sendExposureData(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Set<String> set = this.expoMap.get(str);
        if (set == null) {
            set = new HashSet<>();
            this.expoMap.put(str, set);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        set.add(str2);
        if (z) {
            Integer num = 0;
            if (this.expoMaxLengths.containsKey(str)) {
                num = this.expoMaxLengths.get(str);
            }
            this.expoMaxLengths.put(str, Integer.valueOf(num == null ? str2.length() : Math.max(num.intValue(), str2.length())));
        }
    }
}
