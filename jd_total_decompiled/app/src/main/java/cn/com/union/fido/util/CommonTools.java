package cn.com.union.fido.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Base64;
import cn.com.union.fido.bean.Extension;
import cn.com.union.fido.common.Constance;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdreact.plugin.viewshot.ViewShot;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class CommonTools {
    public static synchronized Drawable byteToDrawable(String str) {
        synchronized (CommonTools.class) {
            byte[] decode = Base64.decode(str.getBytes(), 0);
            if (decode != null) {
                return new BitmapDrawable(BitmapFactory.decodeByteArray(decode, 0, decode.length));
            }
            return null;
        }
    }

    public static String[] getClientPackageList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Constance.UAF_CLIENT_INTENT_ACTION);
        intent.setType("application/fido.uaf_client+json");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 64);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            try {
                if (isClientValid(packageManager, resolveInfo)) {
                    arrayList.add(resolveInfo.activityInfo.applicationInfo.packageName);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static Drawable iconToDrawable(String str) {
        String[] split = str.split(DYConstants.DY_REGEX_COMMA);
        if (split.length == 2 && ViewShot.Results.BASE_64.equalsIgnoreCase(split[0].split(";")[1])) {
            return byteToDrawable(split[1]);
        }
        return null;
    }

    public static boolean ifElementContains(List list, List list2) {
        if (isValidateList(list) && isValidateList(list2)) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (list.contains(list2.get(i2))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean ifElementContains(List list, List list2, List<Extension> list3, List<Extension> list4) {
        if (isValidateList(list) && isValidateList(list2)) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list2.contains(list.get(i2))) {
                    list4.add(list3.get(i2));
                }
            }
            if (list4.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean intEqualAccepted(int i2, int i3) {
        return i2 == 0 || i2 == i3;
    }

    public static boolean intEqualDisallowed(int i2, int i3) {
        return i2 != 0 && i2 == i3;
    }

    public static boolean intLowerOrEqual(int i2, int i3) {
        return i2 != 0 && i2 <= i3;
    }

    public static boolean isClientValid(PackageManager packageManager, ResolveInfo resolveInfo) {
        if (Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        PermissionInfo[] permissionInfoArr = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 4096).permissions;
        if (permissionInfoArr == null) {
            return false;
        }
        for (PermissionInfo permissionInfo : permissionInfoArr) {
            if (permissionInfo.name.matches("org.fidoalliance.uaf.permissions.FIDO_CLIENT")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isScreenOriatationPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static final boolean isValidateByteArray(byte[] bArr) {
        return bArr != null && bArr.length > 0;
    }

    public static final boolean isValidateList(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean longEqualAccepted(long j2, long j3) {
        return j2 == 0 || j2 == j3;
    }

    public static boolean longEqualDisallowed(long j2, long j3) {
        return j2 != 0 && j2 == j3;
    }

    public static boolean longNullorEqual(long j2, long j3) {
        return j2 == j3 || j2 == 0;
    }

    public static void playMusic(Context context, int i2) {
        context.getSystemService("vibrator");
        if (i2 > 0) {
            MediaPlayer.create(context, i2).start();
        }
    }

    public static boolean shortEqualAccepted(short s, short s2) {
        return s == 0 || s == s2;
    }

    public static boolean shortEqualDisallowed(short s, short s2) {
        return s != 0 && s == s2;
    }

    public static void showInfoDialog(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (StringTools.isValidateString(str2)) {
            builder.setTitle(str2);
        }
        builder.setMessage(str);
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() { // from class: cn.com.union.fido.util.CommonTools.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                }
            };
        }
        if (onClickListener2 == null) {
            onClickListener2 = new DialogInterface.OnClickListener() { // from class: cn.com.union.fido.util.CommonTools.2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                }
            };
        }
        builder.setPositiveButton(str3, onClickListener);
        builder.setNegativeButton(str4, onClickListener2);
        builder.show();
    }
}
