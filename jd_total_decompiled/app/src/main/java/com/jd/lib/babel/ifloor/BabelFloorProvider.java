package com.jd.lib.babel.ifloor;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes13.dex */
public class BabelFloorProvider {
    private static final String TAG = "BabelFloorProvider";
    private static Map<String, Class<BaseFloorModel>> modelMap = new HashMap();
    private static Map<String, Class<View>> viewMap = new HashMap();

    /* loaded from: classes13.dex */
    static class SingletonHolder {
        static BabelFloorProvider INSTANCE = new BabelFloorProvider();

        SingletonHolder() {
        }
    }

    private void dealClass(String str) {
        try {
            Class<?> cls = Class.forName(getClass().getPackage().getName() + ".Babel_Floor_Helper_" + str);
            if (cls != null) {
                cls.getMethod(XView2Constants.XVIEW2_ACTION_INIT, new Class[0]).invoke(null, new Object[0]);
            }
        } catch (Throwable th) {
            String str2 = "Exception       " + th;
            th.printStackTrace();
        }
    }

    public static BabelFloorProvider get() {
        return SingletonHolder.INSTANCE;
    }

    private Object getObj(Class cls) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.newInstance();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void putModel(String str, Class cls) {
        modelMap.put(str, cls);
    }

    public static void putView(String str, Class cls) {
        viewMap.put(str, cls);
    }

    public <S> S getCastObject(@NonNull Class<S> cls, Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public BaseFloorModel getModel(String str) {
        return (BaseFloorModel) getCastObject(BaseFloorModel.class, getObj(modelMap.get(str)));
    }

    public Class<BaseFloorModel> getModelClass(String str) {
        if (!modelMap.containsKey(str)) {
            dealClass(str);
        }
        return modelMap.get(str);
    }

    public View getView(Context context, String str) {
        if (!viewMap.containsKey(str)) {
            dealClass(str);
        }
        return getView(context, viewMap.get(str));
    }

    private BabelFloorProvider() {
    }

    private View getView(Context context, Class<View> cls) {
        if (context != null && cls != null) {
            try {
                return (View) cls.getConstructors()[0].newInstance(context);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }
}
