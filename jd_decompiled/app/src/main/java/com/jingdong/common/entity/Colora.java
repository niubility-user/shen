package com.jingdong.common.entity;

/* loaded from: classes5.dex */
public class Colora {
    public int bitmapIndex;
    public int directionTimes;
    public int lastDirection;
    public int speed;
    public int x;
    public int y;

    public Colora(int i2, int i3, int i4, int i5, int i6) {
        this.x = i2;
        this.y = i3;
        this.speed = i4;
        if (i4 == 0) {
            this.speed = 1;
        }
        this.directionTimes = i5;
        this.lastDirection = i5;
        this.bitmapIndex = i6;
    }
}
