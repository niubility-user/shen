package com.jingdong.sdk.dialingtest.c.a;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;

/* loaded from: classes7.dex */
public class a {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f14711c = -9999;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f14712e;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.a)) {
            sb.append("output: ");
            sb.append(this.a);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.f14711c != 0) {
            sb.append("exitCode: ");
            sb.append("" + this.f14711c);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (!TextUtils.isEmpty(this.b)) {
            sb.append("errmsg: ");
            sb.append(this.b);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (!TextUtils.isEmpty(this.f14712e)) {
            sb.append("exMsg: ");
            sb.append(this.f14712e);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }
}
