package com.jd.stat.common.process;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes18.dex */
public class ProcFile extends File implements Parcelable {
    public static final Parcelable.Creator<ProcFile> CREATOR = new Parcelable.Creator<ProcFile>() { // from class: com.jd.stat.common.process.ProcFile.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ProcFile createFromParcel(Parcel parcel) {
            return new ProcFile(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public ProcFile[] newArray(int i2) {
            return new ProcFile[i2];
        }
    };
    public final String b;

    /* JADX INFO: Access modifiers changed from: protected */
    public ProcFile(String str) throws IOException {
        super(str);
        this.b = b(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String b(String str) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
            try {
                String str2 = "";
                for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                    sb.append(str2);
                    sb.append(readLine);
                    str2 = ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
                }
                String sb2 = sb.toString();
                try {
                    bufferedReader2.close();
                } catch (IOException unused) {
                }
                return sb2;
            } catch (Throwable th) {
                th = th;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.io.File
    public long length() {
        return this.b.length();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(getAbsolutePath());
        parcel.writeString(this.b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProcFile(Parcel parcel) {
        super(parcel.readString());
        this.b = parcel.readString();
    }
}
