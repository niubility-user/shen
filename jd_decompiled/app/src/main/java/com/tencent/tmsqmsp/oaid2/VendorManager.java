package com.tencent.tmsqmsp.oaid2;

/* loaded from: classes9.dex */
public class VendorManager implements IVendorCallback {
    public b vendorInfo = null;
    public IVendorCallback userCallback = null;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            d.values();
            int[] iArr = new int[17];
            a = iArr;
            try {
                d dVar = d.XIAOMI;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                d dVar2 = d.BLACKSHARK;
                iArr2[14] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                d dVar3 = d.VIVO;
                iArr3[3] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = a;
                d dVar4 = d.HUA_WEI;
                iArr4[1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = a;
                d dVar5 = d.OPPO;
                iArr5[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                int[] iArr6 = a;
                d dVar6 = d.ONEPLUS;
                iArr6[13] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                int[] iArr7 = a;
                d dVar7 = d.MOTO;
                iArr7[5] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                int[] iArr8 = a;
                d dVar8 = d.LENOVO;
                iArr8[6] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                int[] iArr9 = a;
                d dVar9 = d.ASUS;
                iArr9[7] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                int[] iArr10 = a;
                d dVar10 = d.SAMSUNG;
                iArr10[8] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                int[] iArr11 = a;
                d dVar11 = d.MEIZU;
                iArr11[9] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                int[] iArr12 = a;
                d dVar12 = d.ALPS;
                iArr12[10] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                int[] iArr13 = a;
                d dVar13 = d.NUBIA;
                iArr13[11] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                int[] iArr14 = a;
                d dVar14 = d.ZTE;
                iArr14[12] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                int[] iArr15 = a;
                d dVar15 = d.FREEMEOS;
                iArr15[15] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                int[] iArr16 = a;
                d dVar16 = d.SSUIOS;
                iArr16[16] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getVendorInfo(android.content.Context r4, com.tencent.tmsqmsp.oaid2.IVendorCallback r5) {
        /*
            r3 = this;
            r3.userCallback = r5
            java.lang.String r5 = com.jingdong.sdk.baseinfo.BaseInfo.getDeviceManufacture()
            com.tencent.tmsqmsp.oaid2.d r5 = com.tencent.tmsqmsp.oaid2.d.a(r5)
            com.tencent.tmsqmsp.oaid2.d r0 = com.tencent.tmsqmsp.oaid2.d.UNSUPPORT
            r1 = 0
            java.lang.String r2 = "null"
            if (r5 != r0) goto L17
            r3.onResult(r1, r2, r2)
            int r4 = com.tencent.tmsqmsp.oaid2.a.a
            return r4
        L17:
            int[] r0 = com.tencent.tmsqmsp.oaid2.VendorManager.a.a
            int r5 = r5.ordinal()
            r5 = r0[r5]
            switch(r5) {
                case 1: goto L59;
                case 2: goto L59;
                case 3: goto L53;
                case 4: goto L4d;
                case 5: goto L47;
                case 6: goto L47;
                case 7: goto L41;
                case 8: goto L41;
                case 9: goto L3b;
                case 10: goto L35;
                case 11: goto L2f;
                case 12: goto L2f;
                case 13: goto L29;
                case 14: goto L23;
                case 15: goto L23;
                case 16: goto L23;
                default: goto L22;
            }
        L22:
            goto L60
        L23:
            com.tencent.tmsqmsp.oaid2.u r5 = new com.tencent.tmsqmsp.oaid2.u
            r5.<init>()
            goto L5e
        L29:
            com.tencent.tmsqmsp.oaid2.b0 r5 = new com.tencent.tmsqmsp.oaid2.b0
            r5.<init>()
            goto L5e
        L2f:
            com.tencent.tmsqmsp.oaid2.a0 r5 = new com.tencent.tmsqmsp.oaid2.a0
            r5.<init>()
            goto L5e
        L35:
            com.tencent.tmsqmsp.oaid2.k0 r5 = new com.tencent.tmsqmsp.oaid2.k0
            r5.<init>()
            goto L5e
        L3b:
            com.tencent.tmsqmsp.oaid2.h r5 = new com.tencent.tmsqmsp.oaid2.h
            r5.<init>()
            goto L5e
        L41:
            com.tencent.tmsqmsp.oaid2.o r5 = new com.tencent.tmsqmsp.oaid2.o
            r5.<init>()
            goto L5e
        L47:
            com.tencent.tmsqmsp.oaid2.f0 r5 = new com.tencent.tmsqmsp.oaid2.f0
            r5.<init>()
            goto L5e
        L4d:
            com.tencent.tmsqmsp.oaid2.l r5 = new com.tencent.tmsqmsp.oaid2.l
            r5.<init>()
            goto L5e
        L53:
            com.tencent.tmsqmsp.oaid2.m0 r5 = new com.tencent.tmsqmsp.oaid2.m0
            r5.<init>()
            goto L5e
        L59:
            com.tencent.tmsqmsp.oaid2.n0 r5 = new com.tencent.tmsqmsp.oaid2.n0
            r5.<init>()
        L5e:
            r3.vendorInfo = r5
        L60:
            com.tencent.tmsqmsp.oaid2.b r5 = r3.vendorInfo
            if (r5 != 0) goto L6a
            r3.onResult(r1, r2, r2)
            int r4 = com.tencent.tmsqmsp.oaid2.a.b
            return r4
        L6a:
            java.lang.String r5 = "init"
            com.tencent.tmsqmsp.oaid2.c.b(r5)
            com.tencent.tmsqmsp.oaid2.b r5 = r3.vendorInfo     // Catch: java.lang.Exception -> La7
            r5.a(r4, r3)     // Catch: java.lang.Exception -> La7
            com.tencent.tmsqmsp.oaid2.b r4 = r3.vendorInfo
            boolean r4 = r4.k()
            if (r4 != 0) goto L88
            com.tencent.tmsqmsp.oaid2.b r4 = r3.vendorInfo     // Catch: java.lang.Exception -> L82
            r4.j()     // Catch: java.lang.Exception -> L82
            goto La6
        L82:
            r3.onResult(r1, r2, r2)
            int r4 = com.tencent.tmsqmsp.oaid2.a.f18065c
            return r4
        L88:
            java.lang.String r4 = "sync"
            com.tencent.tmsqmsp.oaid2.c.b(r4)
            com.tencent.tmsqmsp.oaid2.b r4 = r3.vendorInfo     // Catch: java.lang.Exception -> La3
            boolean r4 = r4.e()     // Catch: java.lang.Exception -> La3
            com.tencent.tmsqmsp.oaid2.b r5 = r3.vendorInfo     // Catch: java.lang.Exception -> La3
            java.lang.String r5 = r5.d()     // Catch: java.lang.Exception -> La3
            com.tencent.tmsqmsp.oaid2.b r0 = r3.vendorInfo     // Catch: java.lang.Exception -> La3
            java.lang.String r0 = r0.a()     // Catch: java.lang.Exception -> La3
            r3.onResult(r4, r5, r0)     // Catch: java.lang.Exception -> La3
            goto La6
        La3:
            r3.onResult(r1, r2, r2)
        La6:
            return r1
        La7:
            r3.onResult(r1, r2, r2)
            int r4 = com.tencent.tmsqmsp.oaid2.a.f18065c
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tmsqmsp.oaid2.VendorManager.getVendorInfo(android.content.Context, com.tencent.tmsqmsp.oaid2.IVendorCallback):int");
    }

    @Override // com.tencent.tmsqmsp.oaid2.IVendorCallback
    public void onResult(boolean z, String str, String str2) {
        c.c("vm onResult " + z);
        IVendorCallback iVendorCallback = this.userCallback;
        if (iVendorCallback != null) {
            iVendorCallback.onResult(z, str, str2);
        }
        b bVar = this.vendorInfo;
        if (bVar != null) {
            bVar.l();
        }
    }
}
