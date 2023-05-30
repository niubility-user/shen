package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;

/* loaded from: classes18.dex */
public final class Status extends ProcFile {
    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() { // from class: com.jd.stat.common.process.Status.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Status createFromParcel(Parcel parcel) {
            return new Status(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Status[] newArray(int i2) {
            return new Status[i2];
        }
    };

    public static Status a(int i2) throws IOException {
        return new Status(String.format("/proc/%d/status", Integer.valueOf(i2)));
    }

    public String c(String str) {
        for (String str2 : this.b.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) {
            if (str2.startsWith(str + ":")) {
                return str2.split(str + ":")[1].trim();
            }
        }
        return null;
    }

    private Status(String str) throws IOException {
        super(str);
    }

    public static String a(String str) {
        try {
            String c2 = a(Process.myPid()).c(str);
            return c2 == null ? "" : c2;
        } catch (IOException unused) {
            return "";
        }
    }

    private Status(Parcel parcel) {
        super(parcel);
    }

    public int a() {
        try {
            return Integer.parseInt(c("Uid").split("\\s+")[0]);
        } catch (Exception unused) {
            return -1;
        }
    }
}
