package com.jd.cashier.app.jdlibcutter.protocol.dynamic;

import android.content.Context;
import android.view.ViewGroup;
import com.jd.cashier.app.jdlibcutter.protocol.callback.DynamicEventListener;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public interface IDynamic {
    boolean asyncLoad(ViewGroup viewGroup, JSONObject jSONObject);

    void bindData(ViewGroup viewGroup, JSONObject jSONObject);

    ViewGroup createDynamicContainer(Context context, String str, String str2, IDynamicListener iDynamicListener);

    ViewGroup createDynamicContainer(Context context, String str, String str2, IDynamicListener iDynamicListener, DynamicEventListener<JSONObject> dynamicEventListener);

    boolean haveBackUp(String str, String str2);

    boolean haveCache(String str, String str2);

    void prepare(String str, String str2);
}
