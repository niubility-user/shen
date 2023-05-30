package com.googlecode.mp4parser.h264;

/* loaded from: classes12.dex */
public class CharCache {
    private char[] cache;
    private int pos;

    public CharCache(int i2) {
        this.cache = new char[i2];
    }

    public void append(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = this.cache;
        int length = cArr.length;
        int i2 = this.pos;
        int i3 = length - i2;
        if (charArray.length < i3) {
            i3 = charArray.length;
        }
        System.arraycopy(charArray, 0, cArr, i2, i3);
        this.pos += i3;
    }

    public void clear() {
        this.pos = 0;
    }

    public int length() {
        return this.pos;
    }

    public String toString() {
        return new String(this.cache, 0, this.pos);
    }

    public void append(char c2) {
        int i2 = this.pos;
        char[] cArr = this.cache;
        if (i2 < cArr.length - 1) {
            cArr[i2] = c2;
            this.pos = i2 + 1;
        }
    }
}
