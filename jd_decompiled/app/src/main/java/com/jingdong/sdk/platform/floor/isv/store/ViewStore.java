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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.sdk.platform.floor.isv.IBaseView getInstance(android.content.Context r3, java.lang.String r4) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r0 = r2.clazzInfos
            boolean r0 = r0.containsKey(r4)
            if (r0 == 0) goto L26
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r0 = r2.clazzInfos
            java.lang.Object r0 = r0.get(r4)
            java.lang.Class r0 = (java.lang.Class) r0
            java.lang.Object r0 = r0.newInstance()     // Catch: java.lang.InstantiationException -> L1d java.lang.IllegalAccessException -> L22
            goto L27
        L1d:
            r0 = move-exception
            r0.printStackTrace()
            goto L26
        L22:
            r0 = move-exception
            r0.printStackTrace()
        L26:
            r0 = r1
        L27:
            if (r0 != 0) goto L2d
            com.jingdong.sdk.platform.floor.isv.IBaseView r0 = r2.tryLoadFromName(r3, r4)
        L2d:
            boolean r3 = r0 instanceof com.jingdong.sdk.platform.floor.isv.IBaseView
            if (r3 == 0) goto L34
            com.jingdong.sdk.platform.floor.isv.IBaseView r0 = (com.jingdong.sdk.platform.floor.isv.IBaseView) r0
            return r0
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.platform.floor.isv.store.ViewStore.getInstance(android.content.Context, java.lang.String):com.jingdong.sdk.platform.floor.isv.IBaseView");
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
