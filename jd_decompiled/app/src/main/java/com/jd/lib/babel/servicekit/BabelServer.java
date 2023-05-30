package com.jd.lib.babel.servicekit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.jd.lib.babel.servicekit.imagekit.BabelDrawableListener;
import com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer;
import com.jd.lib.babel.servicekit.imagekit.ImageArr;
import com.jd.lib.babel.servicekit.networkkit.BabelNetWorkKitServer;
import com.jd.lib.babel.servicekit.networkkit.Request;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class BabelServer {
    private static final String TAG = "BabelServer";
    private static Map<Class, Class> classMap = new HashMap();
    public BabelImageKitServer ImageKitServer;
    public BabelNetWorkKitServer NetWorkKitServer;
    private Map<Class<?>, Object> mServices;

    /* loaded from: classes13.dex */
    public static class SingletonHolder {
        static BabelServer INSTANCE = new BabelServer();

        SingletonHolder() {
        }
    }

    public static BabelServer get() {
        return SingletonHolder.INSTANCE;
    }

    public static void putClass(Class cls, Class cls2) {
        classMap.put(cls, cls2);
    }

    public BabelImageKitServer getImageKitServer() {
        BabelImageKitServer babelImageKitServer = (BabelImageKitServer) getService(BabelImageKitServer.class);
        return babelImageKitServer == null ? this.ImageKitServer : babelImageKitServer;
    }

    public BabelNetWorkKitServer getNetWorkKitServer() {
        BabelNetWorkKitServer babelNetWorkKitServer = (BabelNetWorkKitServer) getService(BabelNetWorkKitServer.class);
        return babelNetWorkKitServer == null ? this.NetWorkKitServer : babelNetWorkKitServer;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0030 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0032 A[Catch: all -> 0x0038, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:36:0x0001, B:54:0x0032, B:49:0x0024, B:50:0x0028), top: B:62:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized <S> S getService(@androidx.annotation.NonNull java.lang.Class<S> r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.Map<java.lang.Class<?>, java.lang.Object> r0 = r3.mServices     // Catch: java.lang.Throwable -> L38
            boolean r0 = r0.containsKey(r4)     // Catch: java.lang.Throwable -> L38
            r1 = 0
            if (r0 != 0) goto L28
            java.util.Map<java.lang.Class, java.lang.Class> r0 = com.jd.lib.babel.servicekit.BabelServer.classMap     // Catch: java.lang.Throwable -> L22
            java.lang.Object r0 = r0.get(r4)     // Catch: java.lang.Throwable -> L22
            java.lang.Class r0 = (java.lang.Class) r0     // Catch: java.lang.Throwable -> L22
            if (r0 == 0) goto L20
            java.lang.Object r0 = r0.newInstance()     // Catch: java.lang.Throwable -> L22
            java.util.Map<java.lang.Class<?>, java.lang.Object> r2 = r3.mServices     // Catch: java.lang.Throwable -> L1e
            r2.put(r4, r0)     // Catch: java.lang.Throwable -> L1e
            goto L2e
        L1e:
            r2 = move-exception
            goto L24
        L20:
            r0 = r1
            goto L2e
        L22:
            r2 = move-exception
            r0 = r1
        L24:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L38
            goto L2e
        L28:
            java.util.Map<java.lang.Class<?>, java.lang.Object> r0 = r3.mServices     // Catch: java.lang.Throwable -> L38
            java.lang.Object r0 = r0.get(r4)     // Catch: java.lang.Throwable -> L38
        L2e:
            if (r0 != 0) goto L32
            monitor-exit(r3)
            return r1
        L32:
            java.lang.Object r4 = r4.cast(r0)     // Catch: java.lang.Throwable -> L38
            monitor-exit(r3)
            return r4
        L38:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.babel.servicekit.BabelServer.getService(java.lang.Class):java.lang.Object");
    }

    public <S> void register(@NonNull Class<S> cls, @NonNull S s) {
        this.mServices.put(cls, cls.cast(s));
    }

    private BabelServer() {
        this.ImageKitServer = new BabelImageKitServer() { // from class: com.jd.lib.babel.servicekit.BabelServer.1
            {
                BabelServer.this = this;
            }

            @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
            public void displayImage(ImageArr imageArr) {
            }

            @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
            public ImageView newImageView(Context context, AttributeSet attributeSet) {
                return new ImageView(context, attributeSet);
            }

            @Override // com.jd.lib.babel.servicekit.imagekit.BabelImageKitServer
            public void obtainDrawable(Context context, String str, BabelDrawableListener babelDrawableListener) {
            }
        };
        this.NetWorkKitServer = new BabelNetWorkKitServer() { // from class: com.jd.lib.babel.servicekit.BabelServer.2
            {
                BabelServer.this = this;
            }

            @Override // com.jd.lib.babel.servicekit.networkkit.BabelNetWorkKitServer
            public void addRequest(Context context, Request request) {
            }
        };
        this.mServices = new ArrayMap();
        try {
            Class.forName(getClass().getPackage().getName() + ".Babel_Helper").getMethod(XView2Constants.XVIEW2_ACTION_INIT, new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable th) {
            String str = "Exception       " + th;
            th.printStackTrace();
        }
    }
}
