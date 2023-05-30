package com.jingdong.common.widget.videosmallwindow;

/* loaded from: classes12.dex */
public class SmallWindowConfig {
    public static final String DEFAULT_BORDER_COLOR = "#F5273C";
    public static final int DEFAULT_BORDER_RADIUS = 12;
    public static final int DEFAULT_BORDER_WIDTH = 2;
    private final String borderColor;
    private final int borderRadius;
    private final int borderWidth;
    private final int locationX;
    private final int locationY;

    /* loaded from: classes12.dex */
    public static class Build {
        private String borderColor;
        private int borderRadius;
        private int borderWidth;
        private int locationX;
        private int locationY;

        public Build borderColor(String str) {
            this.borderColor = str;
            return this;
        }

        public Build borderRadius(int i2) {
            this.borderRadius = i2;
            return this;
        }

        public Build borderWidth(int i2) {
            this.borderWidth = i2;
            return this;
        }

        public SmallWindowConfig builder() {
            return new SmallWindowConfig(this);
        }

        public Build location(int i2, int i3) {
            this.locationX = i2;
            this.locationY = i3;
            return this;
        }
    }

    public String getBorderColor() {
        return this.borderColor;
    }

    public int getBorderRadius() {
        return this.borderRadius;
    }

    public int getBorderWidth() {
        return this.borderWidth;
    }

    public int getLocationX() {
        return this.locationX;
    }

    public int getLocationY() {
        return this.locationY;
    }

    private SmallWindowConfig(Build build) {
        this.locationX = build.locationX;
        this.locationY = build.locationY;
        this.borderColor = build.borderColor;
        this.borderWidth = build.borderWidth;
        this.borderRadius = build.borderRadius;
    }
}
