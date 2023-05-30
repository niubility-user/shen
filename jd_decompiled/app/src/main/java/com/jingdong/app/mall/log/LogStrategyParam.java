package com.jingdong.app.mall.log;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.JshopConst;

/* loaded from: classes4.dex */
class LogStrategyParam {
    public String alc;
    public boolean create;
    private boolean d;
    public boolean destroy;

    /* renamed from: e  reason: collision with root package name */
    private boolean f11184e;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11185i;
    public String level;
    public boolean pause;
    public boolean resume;
    public boolean sIS;
    public boolean start;
    public boolean stop;
    private boolean v;
    private boolean w;

    LogStrategyParam() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isReportable(int i2) {
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 != 4) {
                    if (i2 != 5) {
                        if (i2 != 6) {
                            return false;
                        }
                        return this.f11184e;
                    }
                    return this.w;
                }
                return this.f11185i;
            }
            return this.d;
        }
        return this.v;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void parseParams() {
        if (!TextUtils.isEmpty(this.level)) {
            this.v = this.level.contains("v");
            this.d = this.level.contains("d");
            this.f11185i = this.level.contains("i");
            this.w = this.level.contains(JshopConst.JSHOP_PROMOTIO_W);
            this.f11184e = this.level.contains(com.jingdong.app.mall.e.a);
        }
        if (TextUtils.isEmpty(this.alc) || this.alc.length() < 7) {
            return;
        }
        this.create = '1' == this.alc.charAt(0);
        this.start = '1' == this.alc.charAt(1);
        this.resume = '1' == this.alc.charAt(2);
        this.pause = '1' == this.alc.charAt(3);
        this.stop = '1' == this.alc.charAt(4);
        this.sIS = '1' == this.alc.charAt(5);
        this.destroy = '1' == this.alc.charAt(6);
    }

    public String toString() {
        return "LogStrategyParam{alc=" + this.alc + ", create=" + this.create + ", start=" + this.start + ", resume=" + this.resume + ", pause=" + this.pause + ", stop=" + this.stop + ", sIS=" + this.sIS + ", destroy=" + this.destroy + ", level='" + this.level + "', v=" + this.v + ", d=" + this.d + ", i=" + this.f11185i + ", w=" + this.w + ", e=" + this.f11184e + '}';
    }
}
