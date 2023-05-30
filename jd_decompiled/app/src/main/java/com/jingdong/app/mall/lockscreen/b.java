package com.jingdong.app.mall.lockscreen;

import android.app.AppOpsManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class b {
    public static String a() {
        return BaseInfo.getDeviceBrand();
    }

    public static boolean b(Context context) {
        try {
            Cursor query = context.getContentResolver().query(Uri.parse("content://com.vivo.permissionmanager.provider.permission/control_locked_screen_action"), null, "pkgname = ?", new String[]{context.getPackageName()}, null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int i2 = query.getInt(query.getColumnIndex("currentstate"));
                    query.close();
                    return i2 == 0;
                }
                query.close();
                return false;
            }
        } catch (Throwable th) {
            if (Log.D) {
                Log.e("LockScreen", "activity-->getVivoLockStatus--" + th.toString());
            }
        }
        return false;
    }

    public static boolean c(Context context) {
        try {
            AppOpsManager appOpsManager = Build.VERSION.SDK_INT >= 19 ? (AppOpsManager) context.getSystemService("appops") : null;
            Class<?> cls = appOpsManager.getClass();
            Class<?> cls2 = Integer.TYPE;
            Integer num = (Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf((int) R2.drawable.libpdstyleinfoview_pd_style_service_discount_selector), Integer.valueOf(Process.myUid()), context.getPackageName());
            if (Log.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("activity-->getXiaomiLockStatus--");
                sb.append(num);
                sb.append("---");
                sb.append(num.intValue() == 0);
                sb.append("---");
                sb.append(0);
                Log.e("LockScreen", sb.toString());
            }
            return num.intValue() == 0;
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "activity-->getXiaomiLockStatus--" + e2.toString());
            }
            return false;
        }
    }

    public static boolean d(Context context) {
        if (context != null) {
            try {
                if (a().toLowerCase().equals("xiaomi")) {
                    if (Log.D) {
                        Log.e("LockScreen", "activity---xiaomi->>" + c(context));
                    }
                    return !c(context);
                } else if (!a().toLowerCase().equals("vivo")) {
                    return a().toLowerCase().equals("oppo");
                } else {
                    if (Log.D) {
                        Log.e("LockScreen", "activity---vivo->>" + b(context));
                    }
                    return !b(context);
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.e("LockScreen", "activity-->isNeedShowTip--" + e2.toString());
                    return false;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean e(Context context) {
        if (context != null) {
            try {
                if (a().toLowerCase().equals("xiaomi")) {
                    if (Log.D) {
                        Log.e("LockScreen", "activity---xiaomi->>" + c(context));
                    }
                    return c(context);
                } else if (a().toLowerCase().equals("vivo")) {
                    if (Log.D) {
                        Log.e("LockScreen", "activity---vivo->>" + b(context));
                    }
                    return b(context);
                } else {
                    return true;
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.e("LockScreen", "activity-->isNeedShowTip--" + e2.toString());
                    return false;
                }
                return false;
            }
        }
        return false;
    }

    public static boolean f() {
        try {
            return a().toLowerCase().equals("oppo");
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "activity-->isNeedShowTip--" + e2.toString());
                return false;
            }
            return false;
        }
    }
}
