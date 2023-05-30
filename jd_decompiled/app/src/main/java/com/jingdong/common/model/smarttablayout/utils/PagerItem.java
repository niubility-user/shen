package com.jingdong.common.model.smarttablayout.utils;

/* loaded from: classes5.dex */
public abstract class PagerItem {
    protected static final float DEFAULT_WIDTH = 1.0f;
    private final CharSequence title;
    private final float width;

    /* JADX INFO: Access modifiers changed from: protected */
    public PagerItem(CharSequence charSequence, float f2) {
        this.title = charSequence;
        this.width = f2;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public float getWidth() {
        return this.width;
    }
}
