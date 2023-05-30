package com.jingdong.manto.sdk.api;

import android.app.Activity;
import android.graphics.Bitmap;
import com.jingdong.manto.sdk.IMantoSdkBase;

@Deprecated
/* loaded from: classes16.dex */
public interface IShortcutManager extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public static class a {
        public String a;
        public Bitmap b;

        /* renamed from: c  reason: collision with root package name */
        public String f14183c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public String f14184e;

        /* renamed from: f  reason: collision with root package name */
        public String f14185f;
    }

    boolean sendToDesktop(Activity activity, a aVar);
}
