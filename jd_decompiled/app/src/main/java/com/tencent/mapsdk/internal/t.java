package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.tencent.map.tools.Callback;
import com.tencent.tencentmap.mapsdk.maps.BaseMapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;

/* loaded from: classes9.dex */
public final class t {
    private final Context a;

    /* loaded from: classes9.dex */
    public class a implements Callback<Void> {
        public final /* synthetic */ ViewGroup a;
        public final /* synthetic */ TencentMapOptions b;

        /* renamed from: c */
        public final /* synthetic */ Callback f17265c;

        public a(ViewGroup viewGroup, TencentMapOptions tencentMapOptions, Callback callback) {
            t.this = r1;
            this.a = viewGroup;
            this.b = tencentMapOptions;
            this.f17265c = callback;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(Void r4) {
            BaseMapView.MapViewProxy a = t.this.a(u.f().h(), this.a, this.b);
            Callback callback = this.f17265c;
            if (callback != null) {
                callback.callback(a);
            }
        }
    }

    public t(Context context) {
        this.a = context.getApplicationContext();
    }

    public BaseMapView.MapViewProxy a(s sVar, ViewGroup viewGroup, TencentMapOptions tencentMapOptions) {
        return sVar.createDelegate(this.a, tencentMapOptions, viewGroup);
    }

    private String a(TencentMapOptions tencentMapOptions) {
        String mapKey = tencentMapOptions.getMapKey();
        if (e7.b(mapKey)) {
            mapKey = f7.a(this.a, "TencentMapSDK");
        }
        String customUserId = tencentMapOptions.getCustomUserId();
        if (TextUtils.isEmpty(customUserId)) {
            customUserId = "undefined";
        }
        return mapKey + DYConstants.DY_REGEX_COMMA + customUserId;
    }

    public BaseMapView.MapViewProxy a(ViewGroup viewGroup, @NonNull TencentMapOptions tencentMapOptions) {
        u.f().a(this.a, a(tencentMapOptions));
        return a(u.f().h(), viewGroup, tencentMapOptions);
    }

    public void a(ViewGroup viewGroup, @NonNull TencentMapOptions tencentMapOptions, Callback<BaseMapView.MapViewProxy> callback) {
        u.f().a(this.a, a(tencentMapOptions), new a(viewGroup, tencentMapOptions, callback));
    }
}
