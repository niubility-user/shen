package com.jingdong.service;

import android.text.TextUtils;
import com.jingdong.service.entity.ServiceEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes10.dex */
public class ServiceBind {
    private static final String TAG = "ServiceBind";
    private static Map<String, String> mServiceBindMap = new HashMap();

    public static void bindService(ServiceEntity serviceEntity) {
        PointServiceEnum pointServiceEnum;
        if (serviceEntity == null || (pointServiceEnum = serviceEntity.pointServiceEnum) == null || serviceEntity.baseService == null || TextUtils.isEmpty(pointServiceEnum.getName()) || TextUtils.isEmpty(serviceEntity.baseService.getName())) {
            return;
        }
        mServiceBindMap.put(serviceEntity.pointServiceEnum.getName(), serviceEntity.baseService.getName());
    }

    public static String getServiceByPointNmae(PointServiceEnum pointServiceEnum) {
        return mServiceBindMap.get(pointServiceEnum.getName());
    }

    public static void bindService(ArrayList<ServiceEntity> arrayList) {
        PointServiceEnum pointServiceEnum;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<ServiceEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            ServiceEntity next = it.next();
            if (next != null && (pointServiceEnum = next.pointServiceEnum) != null && next.baseService != null && !TextUtils.isEmpty(pointServiceEnum.getName()) && !TextUtils.isEmpty(next.baseService.getName())) {
                mServiceBindMap.put(next.pointServiceEnum.getName(), next.baseService.getName());
            }
        }
    }
}
