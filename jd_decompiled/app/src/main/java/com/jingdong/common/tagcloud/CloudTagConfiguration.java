package com.jingdong.common.tagcloud;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.jingdong.common.R;

/* loaded from: classes6.dex */
public class CloudTagConfiguration {
    private static final int DEFAULT_FIXED_COLUMN_SIZE = 0;
    private static final int DEFAULT_LINE_SPACING = 5;
    private static final int DEFAULT_TAG_SPACING = 10;
    private int columnSize;
    private boolean isFixed;
    private boolean isRight;
    private int lineSpacing;
    private int tagSpacing;

    public CloudTagConfiguration(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CloudTagLayout);
        try {
            this.lineSpacing = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CloudTagLayout_lineSpacing, 5);
            this.tagSpacing = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CloudTagLayout_tagSpacing, 10);
            this.columnSize = obtainStyledAttributes.getInteger(R.styleable.CloudTagLayout_columnSize, 0);
            this.isFixed = obtainStyledAttributes.getBoolean(R.styleable.CloudTagLayout_isFixed, false);
            this.isRight = obtainStyledAttributes.getBoolean(R.styleable.CloudTagLayout_isRight, false);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    public int getLineSpacing() {
        return this.lineSpacing;
    }

    public int getTagSpacing() {
        return this.tagSpacing;
    }

    public boolean isFixed() {
        return this.isFixed;
    }

    public boolean isRight() {
        return this.isRight;
    }

    public void setColumnSize(int i2) {
        this.columnSize = i2;
    }

    public void setIsFixed(boolean z) {
        this.isFixed = z;
    }

    public void setIsRight(boolean z) {
        this.isRight = z;
    }

    public void setLineSpacing(int i2) {
        this.lineSpacing = i2;
    }

    public void setTagSpacing(int i2) {
        this.tagSpacing = i2;
    }
}
