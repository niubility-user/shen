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

    /* JADX WARN: Removed duplicated region for block: B:85:0x0030 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0032 A[Catch: all -> 0x0038, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:69:0x0001, B:87:0x0032, B:82:0x0024, B:83:0x0028), top: B:95:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized <S> S getService(@NonNull Class<S> cls) {
        Object obj;
        if (!this.mServices.containsKey(cls)) {
            try {
                Class cls2 = classMap.get(cls);
                if (cls2 != null) {
                    obj = cls2.newInstance();
                    try {
                        this.mServices.put(cls, obj);
                    } catch (Throwable th) {
                        th = th;
                        th.printStackTrace();
                        if (obj != null) {
                        }
                    }
                } else {
                    obj = null;
                }
            } catch (Throwable th2) {
                th = th2;
                obj = null;
            }
        } else {
            obj = this.mServices.get(cls);
        }
        if (obj != null) {
            return null;
        }
        return cls.cast(obj);
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
