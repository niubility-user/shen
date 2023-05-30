package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.baseinfo.BaseInfo;

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
    */
    public int getVendorInfo(Context context, IVendorCallback iVendorCallback) {
        b n0Var;
        this.userCallback = iVendorCallback;
        d a2 = d.a(BaseInfo.getDeviceManufacture());
        if (a2 != d.UNSUPPORT) {
            switch (a.a[a2.ordinal()]) {
                case 1:
                case 2:
                    n0Var = new n0();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                        onResult(false, DYConstants.DY_NULL_STR, DYConstants.DY_NULL_STR);
                        return com.tencent.tmsqmsp.oaid2.a.b;
                    }
                    c.b(XView2Constants.XVIEW2_ACTION_INIT);
                    try {
                        this.vendorInfo.a(context, this);
                        if (this.vendorInfo.k()) {
                            c.b("sync");
                            try {
                                onResult(this.vendorInfo.e(), this.vendorInfo.d(), this.vendorInfo.a());
                            } catch (Exception unused) {
                                onResult(false, DYConstants.DY_NULL_STR, DYConstants.DY_NULL_STR);
                            }
                        } else {
                            try {
                                this.vendorInfo.j();
                            } catch (Exception unused2) {
                                onResult(false, DYConstants.DY_NULL_STR, DYConstants.DY_NULL_STR);
                                return com.tencent.tmsqmsp.oaid2.a.f18065c;
                            }
                        }
                        return 0;
                    } catch (Exception unused3) {
                        onResult(false, DYConstants.DY_NULL_STR, DYConstants.DY_NULL_STR);
                        return com.tencent.tmsqmsp.oaid2.a.f18065c;
                    }
                case 3:
                    n0Var = new m0();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 4:
                    n0Var = new l();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 5:
                case 6:
                    n0Var = new f0();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 7:
                case 8:
                    n0Var = new o();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 9:
                    n0Var = new h();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 10:
                    n0Var = new k0();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 11:
                case 12:
                    n0Var = new a0();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 13:
                    n0Var = new b0();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                case 14:
                case 15:
                case 16:
                    n0Var = new u();
                    this.vendorInfo = n0Var;
                    if (this.vendorInfo == null) {
                    }
                    break;
                default:
                    if (this.vendorInfo == null) {
                    }
                    break;
            }
        } else {
            onResult(false, DYConstants.DY_NULL_STR, DYConstants.DY_NULL_STR);
            return com.tencent.tmsqmsp.oaid2.a.a;
        }
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
