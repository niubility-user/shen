package com.jd.manto.center.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jingdong.common.utils.ImageUtil;

/* loaded from: classes17.dex */
public class b {
    private final SparseArray<View> a = new SparseArray<>();
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private View f6601c;

    private b(Context context, ViewGroup viewGroup, int i2, int i3) {
        this.b = i3;
        View inflate = ImageUtil.inflate(context, i2, (ViewGroup) null);
        this.f6601c = inflate;
        inflate.setTag(this);
    }

    public static b a(Context context, View view, ViewGroup viewGroup, int i2, int i3) {
        if (view == null) {
            return new b(context, viewGroup, i2, i3);
        }
        return (b) view.getTag();
    }

    public View b() {
        return this.f6601c;
    }

    public int c() {
        return this.b;
    }

    public <T extends View> T d(int i2) {
        T t = (T) this.a.get(i2);
        if (t == null) {
            T t2 = (T) this.f6601c.findViewById(i2);
            this.a.put(i2, t2);
            return t2;
        }
        return t;
    }

    public b e(int i2, String str) {
        TextView textView = (TextView) d(i2);
        if (textView != null && !TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        return this;
    }
}
