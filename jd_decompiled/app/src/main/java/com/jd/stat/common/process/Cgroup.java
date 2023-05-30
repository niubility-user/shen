package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes18.dex */
public final class Cgroup extends ProcFile {
    public static final Parcelable.Creator<Cgroup> CREATOR = new Parcelable.Creator<Cgroup>() { // from class: com.jd.stat.common.process.Cgroup.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Cgroup createFromParcel(Parcel parcel) {
            return new Cgroup(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Cgroup[] newArray(int i2) {
            return new Cgroup[i2];
        }
    };
    public final ArrayList<ControlGroup> a;

    public static Cgroup a(int i2) throws IOException {
        return new Cgroup(String.format("/proc/%d/cgroup", Integer.valueOf(i2)));
    }

    @Override // com.jd.stat.common.process.ProcFile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeTypedList(this.a);
    }

    private Cgroup(String str) throws IOException {
        super(str);
        String[] split = this.b.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        this.a = new ArrayList<>();
        for (String str2 : split) {
            try {
                this.a.add(new ControlGroup(str2));
            } catch (Exception unused) {
            }
        }
    }

    public ControlGroup a(String str) {
        Iterator<ControlGroup> it = this.a.iterator();
        while (it.hasNext()) {
            ControlGroup next = it.next();
            for (String str2 : next.b.split(DYConstants.DY_REGEX_COMMA)) {
                if (str2.equals(str)) {
                    return next;
                }
            }
        }
        return null;
    }

    private Cgroup(Parcel parcel) {
        super(parcel);
        this.a = parcel.createTypedArrayList(ControlGroup.CREATOR);
    }
}
