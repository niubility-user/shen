package com.jingdong.common.cart;

import android.content.Context;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes5.dex */
public class CartBaseTool {
    private static final String TAG = "CartBaseTool";

    public static String getAddressId() {
        try {
            return AddressUtil.getAddressGlobal() != null ? String.valueOf(AddressUtil.getAddressGlobal().getId()) : "";
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(TAG, "getAddressId----->", e2);
                return "";
            }
            return "";
        }
    }

    public static boolean isSpecial(long j2, int i2) {
        return ((j2 >> i2) & 1) == 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0062, code lost:
        com.jingdong.sdk.oklog.OKLog.e(com.jingdong.common.cart.CartBaseTool.TAG, " readCartConfigInfo --->  : ", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0085, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L54;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0094 A[Catch: Exception -> 0x0090, TRY_LEAVE, TryCatch #0 {Exception -> 0x0090, blocks: (B:57:0x008c, B:61:0x0094), top: B:69:0x008c }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x008c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JDJSONObject readCartConfigInfo(Context context, String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, " readCartConfigInfo");
        }
        try {
            fileInputStream = context.openFileInput(str);
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            byteArrayOutputStream = null;
            th = th2;
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e3) {
                    e = e3;
                }
            }
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Exception e4) {
            e = e4;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            if (byteArrayOutputStream != null) {
            }
            if (fileInputStream != null) {
            }
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.close();
                fileInputStream.close();
                JDJSONObject parseObject = JDJSON.parseObject(byteArrayOutputStream.toString("UTF-8"));
                try {
                    byteArrayOutputStream.close();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Exception e5) {
                    if (OKLog.E) {
                        OKLog.e(TAG, " readCartConfigInfo --->  : ", e5);
                    }
                }
                return parseObject;
            } catch (Throwable th4) {
                th = th4;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e6) {
                        if (OKLog.E) {
                            OKLog.e(TAG, " readCartConfigInfo --->  : ", e6);
                        }
                        throw th;
                    }
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Exception e7) {
            e = e7;
            if (OKLog.E) {
                OKLog.e(TAG, " readCartConfigInfo --->  : ", e);
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e8) {
                    e = e8;
                }
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return null;
        }
    }

    public static boolean saveCartConfigInfo(Context context, String str, String str2) {
        if (OKLog.D) {
            OKLog.d(TAG, " saveCartConfigInfo ---> json : " + str2);
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                fileOutputStream = context.openFileOutput(str, 0);
                fileOutputStream.write(str2.getBytes("UTF-8"));
                fileOutputStream.close();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, " saveCartConfigInfo --->  : ", e2);
                        }
                    }
                }
                return true;
            } catch (Throwable th) {
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e3) {
                        if (OKLog.E) {
                            OKLog.e(TAG, " saveCartConfigInfo --->  : ", e3);
                        }
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            if (OKLog.E) {
                OKLog.e(TAG, " saveCartConfigInfo --->  : ", e4);
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e5) {
                    if (OKLog.E) {
                        OKLog.e(TAG, " saveCartConfigInfo --->  : ", e5);
                    }
                }
            }
            return false;
        }
    }

    public static int setSpecialBit(int i2, int i3) {
        if (i2 < 0 || i3 >= 32) {
            return -1;
        }
        return i2 | ((int) Math.pow(2.0d, i3));
    }
}
