package com.jingdong.common.model.smarttablayout.utils;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

/* loaded from: classes5.dex */
public class FragmentPagerItem extends PagerItem {
    private static final String KEY_POSITION = "FragmentPagerItem:Position";
    private static final String TAG = "FragmentPagerItem";
    private final Bundle args;
    private final String className;

    protected FragmentPagerItem(CharSequence charSequence, float f2, String str, Bundle bundle) {
        super(charSequence, f2);
        this.className = str;
        this.args = bundle;
    }

    public static int getPosition(Bundle bundle) {
        if (hasPosition(bundle)) {
            return bundle.getInt(KEY_POSITION);
        }
        return 0;
    }

    public static boolean hasPosition(Bundle bundle) {
        return bundle != null && bundle.containsKey(KEY_POSITION);
    }

    public static FragmentPagerItem of(CharSequence charSequence, Class<? extends Fragment> cls) {
        return of(charSequence, 1.0f, cls);
    }

    static void setPosition(Bundle bundle, int i2) {
        bundle.putInt(KEY_POSITION, i2);
    }

    public Fragment instantiate(Context context, int i2) {
        setPosition(this.args, i2);
        return Fragment.instantiate(context, this.className, this.args);
    }

    public static FragmentPagerItem of(CharSequence charSequence, Class<? extends Fragment> cls, Bundle bundle) {
        return of(charSequence, 1.0f, cls, bundle);
    }

    public static FragmentPagerItem of(CharSequence charSequence, float f2, Class<? extends Fragment> cls) {
        return of(charSequence, f2, cls, new Bundle());
    }

    public static FragmentPagerItem of(CharSequence charSequence, float f2, Class<? extends Fragment> cls, Bundle bundle) {
        return new FragmentPagerItem(charSequence, f2, cls.getName(), bundle);
    }
}
