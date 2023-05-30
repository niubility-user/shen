package com.jingdong.common.model.smarttablayout.utils;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

/* loaded from: classes5.dex */
public class ViewPagerItems extends PagerItems<ViewPagerItem> {

    /* loaded from: classes5.dex */
    public static class Creator {
        private final ViewPagerItems items;

        public Creator(Context context) {
            this.items = new ViewPagerItems(context);
        }

        public Creator add(@StringRes int i2, @LayoutRes int i3) {
            return add(ViewPagerItem.of(this.items.getContext().getString(i2), i3));
        }

        public ViewPagerItems create() {
            return this.items;
        }

        public Creator add(@StringRes int i2, float f2, @LayoutRes int i3) {
            return add(ViewPagerItem.of(this.items.getContext().getString(i2), f2, i3));
        }

        public Creator add(CharSequence charSequence, @LayoutRes int i2) {
            return add(ViewPagerItem.of(charSequence, i2));
        }

        public Creator add(ViewPagerItem viewPagerItem) {
            this.items.add(viewPagerItem);
            return this;
        }
    }

    public ViewPagerItems(Context context) {
        super(context);
    }

    public static Creator with(Context context) {
        return new Creator(context);
    }
}
