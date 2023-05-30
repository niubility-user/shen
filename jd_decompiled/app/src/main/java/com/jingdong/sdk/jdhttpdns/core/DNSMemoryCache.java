package com.jingdong.sdk.jdhttpdns.core;

import android.text.TextUtils;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class DNSMemoryCache {
    private ConcurrentHashMap<String, IpModel> cache = new ConcurrentHashMap<>();

    public void clear() {
        this.cache.clear();
    }

    public IpModel getIpModelByHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.cache.get(str);
    }

    public void updataCache(List<IpModel> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!TextUtils.isEmpty(list.get(i2).host)) {
                if (this.cache.containsKey(list.get(i2).host)) {
                    IpModel ipModel = this.cache.get(list.get(i2).host);
                    ipModel.merge(list.get(i2));
                    DNSLog.d("update to cache:" + ipModel);
                } else {
                    DNSLog.d("add to cache:" + list.get(i2).host + ":" + list.get(i2).toString());
                    this.cache.put(list.get(i2).host, list.get(i2));
                }
            }
        }
    }

    public void clear(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.cache.remove(str);
    }
}
