package com.jdcloud.vsr.imaging;

import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes18.dex */
public class Color {
    public int a;
    public int b;

    /* renamed from: g  reason: collision with root package name */
    public int f7275g;
    public int r;
    public static final Color TRANSPARENT_BLACK = new Color(0, 0, 0, 0);
    public static final Color BLACK = new Color(0, 0, 0, 255);
    public static final Color WHITE = new Color(255, 255, 255, 255);
    public static final Color RED = new Color(255, 0, 0, 255);
    public static final Color GREEN = new Color(0, 255, 0, 255);
    public static final Color BLUE = new Color(0, 0, 255, 255);
    public static final Color YELLOW = new Color(255, 255, 0, 255);
    public static final Color PURPLE = new Color(255, 0, 255, 255);
    public static final Color ORANGE = new Color(255, 127, 0, 255);
    public static final Color GRAY = new Color(127, 127, 127, 255);
    public static final Color FECAMP_SKY = new Color(100, 140, R2.anim.slide_in_from_left, 255);

    public Color() {
        this.a = 0;
        this.b = 0;
        this.f7275g = 0;
        this.r = 0;
    }

    public static Color byHue(float f2) {
        double radians = Math.toRadians(f2);
        double max = Math.max(0.0d, ((Math.cos(radians) * 2.0d) + 1.0d) / 3.0d);
        double max2 = Math.max(0.0d, (((Math.sin(radians) * 1.732050807568877d) - Math.cos(radians)) + 1.0d) / 3.0d);
        double max3 = Math.max(0.0d, ((1.0d - (Math.sin(radians) * 1.732050807568877d)) - Math.cos(radians)) / 3.0d);
        double max4 = Math.max(max, Math.max(max2, max3));
        return new Color((int) Math.floor((max * 255.0d) / max4), (int) Math.floor((max2 * 255.0d) / max4), (int) Math.floor((max3 * 255.0d) / max4), 255);
    }

    public static Color parseString(String str) throws IllegalArgumentException {
        if (str.matches("(\\d|[a-fA-F]){6}")) {
            Color color = new Color(Integer.parseInt(str, 16));
            color.a = 255;
            return color;
        } else if (str.matches("(\\d|[a-fA-F]){8}")) {
            return new Color(Integer.parseInt(str, 16));
        } else {
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            if (split.length != 3 && split.length != 4) {
                throw new IllegalArgumentException("Cannot parse color from expression '" + str + "'");
            }
            return new Color(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), split.length > 3 ? Integer.parseInt(split[3]) : 255);
        }
    }

    public int getRgbaCode() {
        return this.r | (this.f7275g << 8) | (this.b << 16) | (this.a << 24);
    }

    public Color scale(float f2, float f3) {
        return new Color(Math.round(this.r * f2), Math.round(this.f7275g * f2), Math.round(this.b * f2), Math.round(this.a * f3));
    }

    public Color(int i2, int i3, int i4, int i5) {
        this.r = i2;
        this.f7275g = i3;
        this.b = i4;
        this.a = i5;
    }

    public Color(int i2) {
        this.b = i2 % 256;
        this.f7275g = (i2 >> 8) % 256;
        this.r = (i2 >> 16) % 256;
        this.a = (i2 >> 24) % 256;
    }
}
