package com.jingdong.common.model.smarttablayout.utils;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

/* loaded from: classes5.dex */
public class FragmentPagerItems extends PagerItems<FragmentPagerItem> {

    /* loaded from: classes5.dex */
    public static class Creator {
        private final FragmentPagerItems items;

        public Creator(Context context) {
            this.items = new FragmentPagerItems(context);
        }

        public Creator add(@StringRes int i2, Class<? extends Fragment> cls) {
            return add(FragmentPagerItem.of(this.items.getContext().getString(i2), cls));
        }

        public FragmentPagerItems create() {
            return this.items;
        }

        public Creator add(@StringRes int i2, Class<? extends Fragment> cls, Bundle bundle) {
            return add(FragmentPagerItem.of(this.items.getContext().getString(i2), cls, bundle));
        }

        public Creator add(@StringRes int i2, float f2, Class<? extends Fragment> cls) {
            return add(FragmentPagerItem.of(this.items.getContext().getString(i2), f2, cls));
        }

        public Creator add(@StringRes int i2, float f2, Class<? extends Fragment> cls, Bundle bundle) {
            return add(FragmentPagerItem.of(this.items.getContext().getString(i2), f2, cls, bundle));
        }

        public Creator add(CharSequence charSequence, Class<? extends Fragment> cls) {
            return add(FragmentPagerItem.of(charSequence, cls));
        }

        public Creator add(CharSequence charSequence, Class<? extends Fragment> cls, Bundle bundle) {
            return add(FragmentPagerItem.of(charSequence, cls, bundle));
        }

        public Creator add(FragmentPagerItem fragmentPagerItem) {
            this.items.add(fragmentPagerItem);
            return this;
        }
    }

    public FragmentPagerItems(Context context) {
        super(context);
    }

    public static Creator with(Context context) {
        return new Creator(context);
    }
}
