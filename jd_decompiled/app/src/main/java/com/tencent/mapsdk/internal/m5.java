package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.internal.a7;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;

/* loaded from: classes9.dex */
public class m5 implements BitmapDescriptor.BitmapFormator {
    private final int b;

    /* renamed from: h */
    private Bitmap f16865h;

    /* renamed from: i */
    private String f16866i;

    /* renamed from: j */
    private String f16867j;

    /* renamed from: k */
    private a f16868k;

    /* renamed from: m */
    private int f16870m;

    /* renamed from: n */
    private int f16871n;
    private Bitmap[] o;
    private int p;
    private oc q;
    private final Context r;
    private final String a = "marker_default.png";

    /* renamed from: c */
    private int f16861c = -1;
    private String d = "";

    /* renamed from: e */
    private String f16862e = "";

    /* renamed from: f */
    private String f16863f = "";

    /* renamed from: g */
    private float f16864g = -1.0f;

    /* renamed from: l */
    private int f16869l = 1;

    /* loaded from: classes9.dex */
    public static class a implements Parcelable {
        public static final Parcelable.Creator<a> CREATOR = new C0804a();
        public String a;
        public int b;

        /* renamed from: c */
        public int f16872c;
        public float d;

        /* renamed from: e */
        public int f16873e;

        /* renamed from: f */
        public Typeface f16874f;

        /* renamed from: g */
        public float f16875g;

        /* renamed from: com.tencent.mapsdk.internal.m5$a$a */
        /* loaded from: classes9.dex */
        public static class C0804a implements Parcelable.Creator<a> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public a createFromParcel(Parcel parcel) {
                return new a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public a[] newArray(int i2) {
                return new a[i2];
            }
        }

        public a(Parcel parcel) {
            this.f16875g = 1.0f;
            this.a = parcel.readString();
            this.b = parcel.readInt();
            this.f16872c = parcel.readInt();
            this.d = parcel.readFloat();
            this.f16873e = parcel.readInt();
            this.f16875g = parcel.readFloat();
        }

        public a(String str, int i2, int i3) {
            this.f16875g = 1.0f;
            this.a = str;
            this.b = i2;
            this.f16872c = i3;
        }

        public float a() {
            return this.f16875g;
        }

        public void a(float f2) {
            this.f16875g = f2;
        }

        public void a(int i2) {
            this.f16873e = i2;
        }

        public void a(Typeface typeface) {
            this.f16874f = typeface;
        }

        public int b() {
            return this.f16873e;
        }

        public void b(float f2) {
            this.d = f2;
        }

        public float c() {
            return this.d;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("FontText{");
            stringBuffer.append("text='");
            stringBuffer.append(this.a);
            stringBuffer.append('\'');
            stringBuffer.append(", textSize=");
            stringBuffer.append(this.b);
            stringBuffer.append(", textColor=");
            stringBuffer.append(this.f16872c);
            stringBuffer.append(", strokeWith=");
            stringBuffer.append(this.d);
            stringBuffer.append(", strokeColor=");
            stringBuffer.append(this.f16873e);
            stringBuffer.append(", typeface=");
            stringBuffer.append(this.f16874f);
            stringBuffer.append(", scale=");
            stringBuffer.append(this.f16875g);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.f16872c);
            parcel.writeFloat(this.d);
            parcel.writeInt(this.f16873e);
            parcel.writeFloat(this.f16875g);
        }
    }

    public m5(Context context, int i2) {
        this.r = context;
        this.b = i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x0035, code lost:
        if (r3 != null) goto L68;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap a(android.content.Context r2, int r3) {
        /*
            r1 = this;
            switch(r3) {
                case 1: goto L6e;
                case 2: goto L4d;
                case 3: goto L46;
                case 4: goto L3f;
                case 5: goto L38;
                case 6: goto L2f;
                case 7: goto L2c;
                case 8: goto L1d;
                case 9: goto L14;
                case 10: goto L5;
                default: goto L3;
            }
        L3:
            goto L75
        L5:
            android.graphics.Bitmap[] r2 = r1.o
            if (r2 == 0) goto L75
            int r3 = r2.length
            int r0 = r1.p
            if (r3 <= r0) goto L75
            if (r0 < 0) goto L75
            r2 = r2[r0]
            goto L76
        L14:
            com.tencent.mapsdk.internal.m5$a r3 = r1.f16868k
            if (r3 == 0) goto L75
            android.graphics.Bitmap r2 = r1.a(r2, r3)
            goto L76
        L1d:
            java.lang.String r2 = r1.f16867j
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L75
            java.lang.String r2 = r1.f16867j
            android.graphics.Bitmap r2 = r1.a(r2)
            goto L76
        L2c:
            android.graphics.Bitmap r2 = r1.f16865h
            goto L76
        L2f:
            float r3 = r1.f16864g
            java.lang.String r3 = r1.a(r3)
            if (r3 == 0) goto L75
            goto L3a
        L38:
            java.lang.String r3 = "marker_default.png"
        L3a:
            android.graphics.Bitmap r2 = com.tencent.mapsdk.internal.a7.c(r2, r3)
            goto L76
        L3f:
            java.lang.String r2 = r1.f16863f
            android.graphics.Bitmap r2 = com.tencent.mapsdk.internal.a7.a(r2)
            goto L76
        L46:
            java.lang.String r3 = r1.f16862e
            android.graphics.Bitmap r2 = com.tencent.mapsdk.internal.a7.a(r2, r3)
            goto L76
        L4d:
            java.lang.String r3 = r1.d
            android.graphics.Bitmap r3 = com.tencent.mapsdk.internal.a7.b(r2, r3)
            if (r3 != 0) goto L6c
            java.lang.String r3 = r1.d
            android.graphics.Bitmap r2 = com.tencent.mapsdk.internal.a7.c(r2, r3)
            if (r2 == 0) goto L76
            java.lang.String r3 = r1.d
            java.lang.String r0 = "color_texture_flat_style.png"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L76
            android.graphics.Bitmap r2 = com.tencent.mapsdk.internal.a7.a(r2)
            goto L76
        L6c:
            r2 = r3
            goto L76
        L6e:
            int r3 = r1.f16861c
            android.graphics.Bitmap r2 = com.tencent.mapsdk.internal.a7.a(r2, r3)
            goto L76
        L75:
            r2 = 0
        L76:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.m5.a(android.content.Context, int):android.graphics.Bitmap");
    }

    private Bitmap a(Context context, a aVar) {
        if (this.q == null) {
            this.q = new oc(context);
        }
        oc ocVar = this.q;
        ocVar.setText(aVar.a);
        ocVar.setTextSize(0, aVar.b * aVar.f16875g);
        ocVar.setTextColor(aVar.f16872c);
        ocVar.setStrokeColor(aVar.f16873e);
        ocVar.setStrokeWidth(aVar.d * aVar.f16875g);
        ocVar.setTypeface(aVar.f16874f);
        return a7.a(ocVar);
    }

    private Bitmap a(String str) {
        NetResponse doGet = NetManager.getInstance().builder().url(str).doGet();
        if (doGet != null && doGet.available()) {
            try {
                byte[] bArr = doGet.data;
                return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            } catch (OutOfMemoryError unused) {
                System.gc();
                try {
                    byte[] bArr2 = doGet.data;
                    return BitmapFactory.decodeByteArray(bArr2, 0, bArr2.length);
                } catch (OutOfMemoryError unused2) {
                }
            }
        }
        return null;
    }

    private String a() {
        StringBuilder sb;
        String str;
        String str2;
        String str3;
        Bitmap bitmap;
        if (TextUtils.isEmpty(this.f16866i) || this.b == 10) {
            switch (this.b) {
                case 1:
                    sb = new StringBuilder();
                    sb.append("res_");
                    sb.append(this.f16861c);
                    str2 = sb.toString();
                    this.f16866i = str2;
                    break;
                case 2:
                    sb = new StringBuilder();
                    sb.append("asset_");
                    str = this.d;
                    sb.append(str);
                    str2 = sb.toString();
                    this.f16866i = str2;
                    break;
                case 3:
                    sb = new StringBuilder();
                    sb.append("file_");
                    str = this.f16862e;
                    sb.append(str);
                    str2 = sb.toString();
                    this.f16866i = str2;
                    break;
                case 4:
                    sb = new StringBuilder();
                    sb.append("path_");
                    str = this.f16863f;
                    sb.append(str);
                    str2 = sb.toString();
                    this.f16866i = str2;
                    break;
                case 5:
                    str2 = "asset_marker_default.png";
                    this.f16866i = str2;
                    break;
                case 6:
                    String a2 = a(this.f16864g);
                    if (a2 != null) {
                        str2 = "asset_" + a2;
                        this.f16866i = str2;
                        break;
                    }
                    break;
                case 7:
                    Bitmap bitmap2 = this.f16865h;
                    if (bitmap2 != null && !bitmap2.isRecycled()) {
                        sb = new StringBuilder();
                        sb.append("bitmap_");
                        str = a(this.f16865h);
                        sb.append(str);
                        str2 = sb.toString();
                        this.f16866i = str2;
                        break;
                    }
                    break;
                case 8:
                    if (!TextUtils.isEmpty(this.f16867j)) {
                        sb = new StringBuilder();
                        sb.append("url_");
                        str3 = this.f16867j;
                        str = va.a(str3);
                        sb.append(str);
                        str2 = sb.toString();
                        this.f16866i = str2;
                        break;
                    }
                    break;
                case 9:
                    if (this.f16868k != null) {
                        sb = new StringBuilder();
                        sb.append("fonttext_");
                        str3 = this.f16868k.toString();
                        str = va.a(str3);
                        sb.append(str);
                        str2 = sb.toString();
                        this.f16866i = str2;
                        break;
                    }
                    break;
                case 10:
                    Bitmap[] bitmapArr = this.o;
                    if (bitmapArr != null) {
                        int length = bitmapArr.length;
                        int i2 = this.p;
                        if (length > i2 && i2 >= 0 && (bitmap = bitmapArr[i2]) != null && !bitmap.isRecycled()) {
                            str2 = "bitmaps_" + a(bitmap);
                            this.f16866i = str2;
                            break;
                        }
                    }
                    break;
            }
            return this.f16866i;
        }
        return this.f16866i;
    }

    private String a(float f2) {
        if (f2 < 30.0f) {
            return "RED.png";
        }
        if (f2 < 30.0f || f2 >= 60.0f) {
            if (f2 < 60.0f || f2 >= 120.0f) {
                if (f2 < 120.0f || f2 >= 180.0f) {
                    if (f2 < 180.0f || f2 >= 210.0f) {
                        if (f2 < 210.0f || f2 >= 240.0f) {
                            if (f2 < 240.0f || f2 >= 270.0f) {
                                if (f2 < 270.0f || f2 >= 300.0f) {
                                    if (f2 < 300.0f || f2 >= 330.0f) {
                                        if (f2 >= 330.0f) {
                                            return "ROSE.png";
                                        }
                                        return null;
                                    }
                                    return "MAGENTAV.png";
                                }
                                return "VIOLET.png";
                            }
                            return "BLUE.png";
                        }
                        return "AZURE.png";
                    }
                    return "CYAN.png";
                }
                return "GREEN.png";
            }
            return "YELLOW.png";
        }
        return "ORANGE.png";
    }

    private String a(Bitmap bitmap) {
        return a7.e(bitmap);
    }

    private String b() {
        if (this.f16869l <= 1) {
            return "";
        }
        return DYConstants.DY_REGEX_AT + this.f16869l + JshopConst.JSHOP_PROMOTIO_X;
    }

    public BitmapDescriptor.BitmapFormator a(int i2) {
        this.f16861c = i2;
        return this;
    }

    public BitmapDescriptor.BitmapFormator a(a aVar) {
        this.f16868k = aVar;
        return this;
    }

    public void a(Bitmap[] bitmapArr) {
        this.o = bitmapArr;
        getBitmap(this.r);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public int activeSize() {
        Bitmap[] bitmapArr = this.o;
        return bitmapArr != null ? bitmapArr.length : this.f16865h != null ? 1 : 0;
    }

    public BitmapDescriptor.BitmapFormator b(float f2) {
        this.f16864g = f2;
        return this;
    }

    public BitmapDescriptor.BitmapFormator b(Bitmap bitmap) {
        this.f16865h = bitmap;
        getBitmap(this.r);
        return this;
    }

    public BitmapDescriptor.BitmapFormator b(String str) {
        this.d = str;
        return this;
    }

    public BitmapDescriptor.BitmapFormator c(String str) {
        this.f16862e = str;
        return this;
    }

    public BitmapDescriptor.BitmapFormator d(String str) {
        this.f16863f = str;
        return this;
    }

    public BitmapDescriptor.BitmapFormator e(String str) {
        this.f16867j = str;
        return this;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public Bitmap getBitmap(Context context) {
        Bitmap bitmap = this.f16865h;
        if (bitmap == null || this.f16866i == null || this.b == 10) {
            if (context == null) {
                return null;
            }
            a7.a aVar = a7.f16233e;
            Bitmap a2 = aVar != null ? aVar.a(getBitmapId()) : null;
            if (a2 == null) {
                a2 = a(context, this.b);
                a7.a aVar2 = a7.f16233e;
                if (aVar2 != null && a2 != null) {
                    aVar2.a(getBitmapId(), a2);
                }
            }
            if (a2 != null) {
                this.f16870m = a2.getWidth();
                this.f16871n = a2.getHeight();
                this.f16865h = a2;
            }
            return a2;
        }
        return bitmap;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public String getBitmapId() {
        return a() + b();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public int getFormateType() {
        return this.b;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public int getHeight() {
        getBitmap(this.r);
        return this.f16871n;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public int getWidth() {
        getBitmap(this.r);
        return this.f16870m;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public int nextActiveIndex() {
        Bitmap[] bitmapArr = this.o;
        if (bitmapArr == null || bitmapArr.length <= 1) {
            this.p = 0;
        } else {
            int i2 = this.p + 1;
            this.p = i2;
            this.p = i2 % bitmapArr.length;
        }
        return this.p;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public void recycle() {
        ma.a(la.r, "remove on format recycle");
        if (a7.f16233e.b(getBitmapId())) {
            ga.a(this.o);
            ga.a(this.f16865h);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor.BitmapFormator
    public void setScale(int i2) {
        this.f16869l = i2;
    }
}
