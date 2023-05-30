package com.jingdong.app.mall.home.o.a;

import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.jdlocation.JDLocation;

/* loaded from: classes4.dex */
public class i {
    public double a;
    public double b;

    /* renamed from: c */
    private JDBusinessAddress f10477c;
    private JDLocation d;

    public i() {
        h();
    }

    public JDBusinessAddress a() {
        return this.f10477c;
    }

    public String b() {
        return String.valueOf(this.a);
    }

    public String c() {
        return String.valueOf(this.b);
    }

    public JDLocation d() {
        return this.d;
    }

    public boolean e() {
        double d = this.a;
        if (d != 0.0d) {
            double d2 = this.b;
            if (d2 != 0.0d && d != d2) {
                return false;
            }
        }
        return true;
    }

    public boolean f(i iVar) {
        return this.a == iVar.a || this.b == iVar.b;
    }

    public boolean g(i iVar, int i2) {
        try {
            if (!e() && !f(iVar)) {
                double d = g.d(iVar, this);
                if (d > 0.0d) {
                    f.Z(true, i.class, "\u9996\u9875\u5b9a\u4f4d\u95f4\u8ddd: " + d + " \u6700\u5927\uff1a" + i2);
                }
                return d < 0.0d || d >= ((double) i2);
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void h() {
        this.d = null;
        this.f10477c = null;
        i(0.0d, 0.0d);
    }

    public void i(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public void j(AddressGlobal addressGlobal) {
        try {
            h();
            double d = 0.0d;
            this.a = addressGlobal == null ? 0.0d : Double.parseDouble(addressGlobal.getLatitude());
            if (addressGlobal != null) {
                d = Double.parseDouble(addressGlobal.getLongitude());
            }
            this.b = d;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void k(JDBusinessAddress jDBusinessAddress) {
        try {
            h();
            this.f10477c = jDBusinessAddress;
            double d = 0.0d;
            this.a = jDBusinessAddress == null ? 0.0d : jDBusinessAddress.getLat();
            if (jDBusinessAddress != null) {
                d = jDBusinessAddress.getLng();
            }
            this.b = d;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void l(JDLocation jDLocation) {
        h();
        if (jDLocation == null) {
            return;
        }
        this.d = jDLocation;
        i(jDLocation.getLat(), jDLocation.getLng());
    }

    public i(JDBusinessAddress jDBusinessAddress) {
        k(jDBusinessAddress);
    }

    public i(double d, double d2) {
        this.a = d;
        this.b = d2;
    }
}
