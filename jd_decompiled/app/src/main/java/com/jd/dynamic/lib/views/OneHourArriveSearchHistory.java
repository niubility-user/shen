package com.jd.dynamic.lib.views;

/* loaded from: classes13.dex */
public class OneHourArriveSearchHistory {
    private int a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f2487c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private int f2488e;
    public String itemType;

    public OneHourArriveSearchHistory() {
    }

    public OneHourArriveSearchHistory(int i2, String str, long j2, int i3) {
        this(i2, str, "", j2, i3);
    }

    public OneHourArriveSearchHistory(int i2, String str, String str2, long j2, int i3) {
        a(i2, str, str2, j2, i3);
    }

    public OneHourArriveSearchHistory(String str) {
        this.b = str;
        this.d = System.currentTimeMillis() + "";
    }

    private void a(int i2, String str, String str2, long j2, int i3) {
        this.b = str;
        this.f2487c = str2;
        this.a = i2;
        this.f2488e = i3;
        try {
            this.d = System.currentTimeMillis() + "";
        } catch (Exception unused) {
        }
    }

    public int getId() {
        return this.a;
    }

    public String getTag() {
        return this.f2487c;
    }

    public int getType() {
        return this.f2488e;
    }

    public String getWord() {
        return this.b;
    }

    public void setId(int i2) {
        this.a = i2;
    }

    public void setTag(String str) {
        this.f2487c = str;
    }

    public void setType(int i2) {
        this.f2488e = i2;
    }

    public void setWord(String str) {
        this.b = str;
    }

    public String toString() {
        return "OneHourArriveSearchHistory{id=" + this.a + ", word='" + this.b + "', tag='" + this.f2487c + "', searchDate=" + this.d + ", type=" + this.f2488e + '}';
    }
}
