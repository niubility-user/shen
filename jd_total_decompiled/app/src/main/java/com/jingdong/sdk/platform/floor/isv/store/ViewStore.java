package com.jingdong.sdk.platform.floor.isv.store;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.platform.floor.isv.DefaultDynBaseView;
import com.jingdong.sdk.platform.floor.isv.IBaseView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes10.dex */
public class ViewStore {
    public HashMap<String, Class<?>> clazzInfos = new HashMap<>();
    public HashMap<String, String> classNames = new HashMap<>();

    private IBaseView tryLoadFromName(Context context, String str) {
        if (context == null || context.getApplicationContext() == null || this.classNames.isEmpty()) {
            return null;
        }
        ClassLoader classLoader = context.getApplicationContext().getClassLoader();
        Iterator<Map.Entry<String, String>> it = this.classNames.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String value = next.getValue();
            if (TextUtils.equals(str, next.getKey())) {
                try {
                    Object newInstance = classLoader.loadClass(value).newInstance();
                    if (newInstance instanceof IBaseView) {
                        return (IBaseView) newInstance;
                    }
                    it.remove();
                    return null;
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (IllegalAccessException e3) {
                    e3.printStackTrace();
                    return null;
                } catch (InstantiationException e4) {
                    e4.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0034 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public IBaseView getInstance(Context context, String str) {
        Object obj;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.clazzInfos.containsKey(str)) {
            try {
                obj = this.clazzInfos.get(str).newInstance();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            }
            if (obj == null) {
                obj = tryLoadFromName(context, str);
            }
            if (obj instanceof IBaseView) {
                return null;
            }
            return (IBaseView) obj;
        }
        obj = null;
        if (obj == null) {
        }
        if (obj instanceof IBaseView) {
        }
    }

    public void preLoad(Context context) {
        if (context == null || context.getApplicationContext() == null || this.classNames.isEmpty()) {
            return;
        }
        ClassLoader classLoader = context.getApplicationContext().getClassLoader();
        for (Map.Entry<String, String> entry : this.classNames.entrySet()) {
            try {
                this.clazzInfos.put(entry.getKey(), classLoader.loadClass(entry.getValue()));
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        this.classNames.clear();
    }

    public void registDynView(String str) {
        registView(str, DefaultDynBaseView.class);
    }

    public void registView(String str, Class<? extends IBaseView> cls) {
        this.clazzInfos.put(str, cls);
    }

    public void registView(String str, String str2) {
        this.classNames.put(str, str2);
    }

    public void registView(HashMap<String, String> hashMap) {
        this.classNames.putAll(hashMap);
    }
}
