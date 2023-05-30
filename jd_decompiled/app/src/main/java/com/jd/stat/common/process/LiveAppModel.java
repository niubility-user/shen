package com.jd.stat.common.process;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.web.managers.PerformanceManager;
import java.io.File;
import java.io.IOException;

/* loaded from: classes18.dex */
public class LiveAppModel extends LiveProcessModel {
    public boolean a;
    public int b;

    /* renamed from: f  reason: collision with root package name */
    private static final boolean f7011f = new File("/dev/cpuctl/tasks").exists();
    public static final Parcelable.Creator<LiveAppModel> CREATOR = new Parcelable.Creator<LiveAppModel>() { // from class: com.jd.stat.common.process.LiveAppModel.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LiveAppModel createFromParcel(Parcel parcel) {
            return new LiveAppModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LiveAppModel[] newArray(int i2) {
            return new LiveAppModel[i2];
        }
    };

    /* loaded from: classes18.dex */
    public static final class NotAndroidAppProcessException extends Exception {
        public NotAndroidAppProcessException(int i2) {
            super(String.format("The process %d does not belong to any application", Integer.valueOf(i2)));
        }
    }

    public LiveAppModel() {
    }

    public String a() {
        return this.f7012c.split(":")[0];
    }

    public String toString() {
        return this.b + "###" + this.f7013e + "###" + this.f7012c + "###" + this.d;
    }

    @Override // com.jd.stat.common.process.LiveProcessModel, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeByte(this.a ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.b);
    }

    public LiveAppModel(int i2) throws IOException, NotAndroidAppProcessException {
        super(i2);
        int a;
        boolean z;
        boolean z2 = com.jd.stat.common.b.b.a;
        String str = this.f7012c;
        if (str != null && str.contains(OrderISVUtil.MONEY_DECIMAL) && !this.f7012c.contains("/") && this.f7012c.matches("^([\\p{L}]{1}[\\p{L}\\p{N}_]*[\\.:])*[\\p{L}][\\p{L}\\p{N}_]*$")) {
            if (f7011f) {
                Cgroup b = b();
                ControlGroup a2 = b.a("cpuacct");
                ControlGroup a3 = b.a(PerformanceManager.CUP);
                if (Build.VERSION.SDK_INT >= 21) {
                    if (a3 != null && a2 != null && a2.f7010c.contains("pid_")) {
                        z = !a3.f7010c.contains("bg_non_interactive");
                        try {
                            a = Integer.parseInt(a2.f7010c.split("/")[1].replace("uid_", ""));
                        } catch (Exception unused) {
                            a = d().a();
                        }
                    } else {
                        throw new NotAndroidAppProcessException(i2);
                    }
                } else if (a3 != null && a2 != null && a3.f7010c.contains("apps")) {
                    z = !a3.f7010c.contains("bg_non_interactive");
                    try {
                        String str2 = a2.f7010c;
                        a = Integer.parseInt(str2.substring(str2.lastIndexOf("/") + 1));
                    } catch (Exception unused2) {
                        a = d().a();
                    }
                } else {
                    throw new NotAndroidAppProcessException(i2);
                }
            } else {
                Stat c2 = c();
                Status d = d();
                boolean z3 = c2.b() == 0;
                a = d.a();
                z = z3;
            }
            this.a = z;
            this.b = a;
            return;
        }
        if (com.jd.stat.common.b.b.a) {
            com.jd.stat.common.b.b.b("LiveAppModel.name = " + this.f7012c);
        }
        throw new NotAndroidAppProcessException(i2);
    }

    protected LiveAppModel(Parcel parcel) {
        super(parcel);
        this.a = parcel.readByte() != 0;
        this.b = parcel.readInt();
    }
}
