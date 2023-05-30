package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.content.Context;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.ArrayMap;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.R;
import com.jingdong.sdk.lib.puppetlayout.d.a;
import com.jingdong.sdk.lib.puppetlayout.d.b;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.d;
import com.jingdong.sdk.lib.puppetlayout.view.ui.builder.g;
import com.jingdong.sdk.lib.puppetlayout.ylayout.callback.CallbackManager;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes8.dex */
public class PuppetContext {
    public Context context;
    public ParentHandler parentHandler;
    public String styleId;
    public String styleVersion;
    public WeakHashMap<d, Integer> weakHashMap;
    public static final int KEY_VALUES = R.id.com_jd_sdk_lib_puppetlayout_key_values;
    private static final int KEY_DATAS = R.id.com_jd_sdk_lib_puppetlayout_key_datas;
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Class<?>[] sConstructorSignature = {Context.class};
    private static final Map<String, Constructor<? extends g>> sConstructorMap = new ArrayMap();
    public boolean isEditing = false;
    public CallbackManager manager = new CallbackManager();
    public boolean isRequestLayoutOnBind = false;
    public long dataTimestamp = -1;
    private final Object[] mConstructorArgs = new Object[1];

    public PuppetContext(String str, String str2) {
        this.styleId = str;
        this.styleVersion = str2;
    }

    public static void bindView(View view, JDJSONObject jDJSONObject) {
        b bVar;
        if (view != null) {
            int i2 = KEY_VALUES;
            if (!(view.getTag(i2) instanceof b) || jDJSONObject == null || (bVar = (b) view.getTag(i2)) == null) {
                return;
            }
            bVar.f(jDJSONObject);
        }
    }

    private g create(String str) throws ClassNotFoundException, InflateException {
        Map<String, Constructor<? extends g>> map = sConstructorMap;
        Constructor<? extends g> constructor = map.get(str);
        if (constructor == null) {
            try {
                constructor = this.context.getClassLoader().loadClass(str).asSubclass(g.class).getConstructor(new Class[0]);
                map.put(str, constructor);
            } catch (Exception unused) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(new Object[0]);
    }

    public static HashMap<View, HashMap<String, String>> getDatas(View view) {
        if (view != null) {
            int i2 = KEY_DATAS;
            if (view.getTag(i2) == null || !(view.getTag(i2) instanceof a)) {
                return null;
            }
            return ((a) view.getTag(i2)).b();
        }
        return null;
    }

    public void cancelAllCountdown() {
        WeakHashMap<d, Integer> weakHashMap = this.weakHashMap;
        if (weakHashMap == null || weakHashMap.size() == 0) {
            return;
        }
        for (Map.Entry<d, Integer> entry : this.weakHashMap.entrySet()) {
            if (entry.getKey() != null) {
                entry.getKey().b();
            }
        }
    }

    public g createFromClass(String str) {
        try {
            this.mConstructorArgs[0] = this.context;
            if (-1 != str.indexOf(46)) {
                return create(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        } finally {
            this.mConstructorArgs[0] = null;
        }
    }

    public void expo(ViewGroup viewGroup) {
        b bVar;
        if (viewGroup == null || (bVar = (b) viewGroup.getTag(KEY_VALUES)) == null) {
            return;
        }
        bVar.h(this);
    }

    public View inflate(Context context, com.jingdong.sdk.lib.puppetlayout.f.a aVar, ViewGroup viewGroup, boolean z) {
        b bVar = new b();
        a aVar2 = new a();
        View b = aVar.a().b(context, viewGroup, z, bVar, aVar2, this);
        b.setTag(KEY_VALUES, bVar);
        b.setTag(KEY_DATAS, aVar2);
        return b;
    }

    public void notifyEditingChanged(ViewGroup viewGroup) {
        b bVar;
        if (viewGroup == null || (bVar = (b) viewGroup.getTag(KEY_VALUES)) == null) {
            return;
        }
        bVar.g();
    }

    public void putCountdown(d dVar) {
        if (dVar != null) {
            if (this.weakHashMap == null) {
                this.weakHashMap = new WeakHashMap<>();
            }
            this.weakHashMap.put(dVar, 1);
        }
    }
}
