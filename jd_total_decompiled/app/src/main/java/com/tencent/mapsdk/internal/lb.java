package com.tencent.mapsdk.internal;

import com.jingdong.common.utils.LangUtils;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes9.dex */
public class lb extends pb {

    /* renamed from: c  reason: collision with root package name */
    private String f16828c;

    public String c() {
        return this.f16828c;
    }

    @Override // com.tencent.mapsdk.internal.pb, com.tencent.mapsdk.internal.hb
    public InputStream f(String str) {
        try {
            String file = new URL(str).getFile();
            if (!e7.b(file)) {
                this.f16828c = file.substring(file.lastIndexOf("/") + 1).replace("%20", LangUtils.SINGLE_SPACE);
            }
        } catch (MalformedURLException e2) {
            e2.printStackTrace();
        }
        return super.f(str);
    }
}
