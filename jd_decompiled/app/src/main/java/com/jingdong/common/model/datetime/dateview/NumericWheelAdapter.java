package com.jingdong.common.model.datetime.dateview;

/* loaded from: classes5.dex */
public class NumericWheelAdapter implements WheelAdapter {
    public static final int DEFAULT_MAX_VALUE = 9;
    private static final int DEFAULT_MIN_VALUE = 0;
    private String format;
    private int maxValue;
    private int minValue;

    public NumericWheelAdapter() {
        this(0, 9);
    }

    @Override // com.jingdong.common.model.datetime.dateview.WheelAdapter
    public String getItem(int i2) {
        if (i2 < 0 || i2 >= getItemsCount()) {
            return null;
        }
        int i3 = this.minValue + i2;
        String str = this.format;
        return str != null ? String.format(str, Integer.valueOf(i3)) : Integer.toString(i3);
    }

    @Override // com.jingdong.common.model.datetime.dateview.WheelAdapter
    public int getItemsCount() {
        return (this.maxValue - this.minValue) + 1;
    }

    @Override // com.jingdong.common.model.datetime.dateview.WheelAdapter
    public int getMaximumLength() {
        int length = Integer.toString(Math.max(Math.abs(this.maxValue), Math.abs(this.minValue))).length();
        return this.minValue < 0 ? length + 1 : length;
    }

    public NumericWheelAdapter(int i2, int i3) {
        this(i2, i3, null);
    }

    public NumericWheelAdapter(int i2, int i3, String str) {
        this.minValue = i2;
        this.maxValue = i3;
        this.format = str;
    }
}
