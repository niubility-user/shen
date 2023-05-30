package com.jingdong.manto.t;

import android.content.ContentUris;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.R;
import com.jingdong.manto.pkg.db.entity.StorageEntity;
import com.jingdong.manto.utils.MantoProcessUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class b {

    /* loaded from: classes16.dex */
    public enum a {
        NONE,
        UNKNOWN,
        MISSING_PARAMS,
        NO_SUCH_KEY,
        QUOTA_REACHED
    }

    public static int a(String str, int i2) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Integer.decode(str).intValue();
                }
            } catch (Throwable unused) {
            }
        }
        return i2;
    }

    public static final int a(String... strArr) {
        int i2 = 0;
        for (String str : strArr) {
            if (str != null) {
                i2 += str.length();
            }
        }
        return i2;
    }

    public static final a a(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return a.MISSING_PARAMS;
        }
        String str6 = str + "__" + str2;
        List b = com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).b(StorageEntity.class, "appType=? AND key=?", new String[]{str5, str6}, null);
        int i2 = (b == null || b.size() <= 0) ? 0 : ((StorageEntity) b.get(0)).dataSize;
        int length = (str3 == null ? 0 : str3.length()) + (str2 != null ? str2.length() : 0);
        if (b(str, str5) + length >= 10485760) {
            return a.QUOTA_REACHED;
        }
        if (ContentUris.parseId(com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(new StorageEntity(str6, str3, str4, length, str5))) <= 0) {
            return a.UNKNOWN;
        }
        a(str, length, str5, i2);
        return a.NONE;
    }

    private static final void a(String str, int i2, String str2, int i3) {
        int b = b(str, str2);
        StorageEntity storageEntity = new StorageEntity();
        storageEntity.key = str + "++@@@TOTAL@DATA@SIZE@@@";
        if (i3 >= 4) {
            b -= i3;
        }
        storageEntity.data = String.valueOf(b + i2);
        storageEntity.appType = str2;
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).d(storageEntity);
    }

    public static final void a(String str, String str2) {
        com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(StorageEntity.class, "appType=? AND key LIKE ?", new String[]{str2, str + "%"});
    }

    public static final Object[] a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return new Object[]{a.MISSING_PARAMS};
        }
        StorageEntity storageEntity = (StorageEntity) com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(StorageEntity.class, "appType=? AND key=?", new String[]{str3, str + "__" + str2}, null);
        return storageEntity != null ? new Object[]{a.NONE, storageEntity.data, storageEntity.dataType} : new Object[]{a.NO_SUCH_KEY};
    }

    public static final int b(String str, String str2) {
        StorageEntity storageEntity = (StorageEntity) com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(StorageEntity.class, "appType=? AND key=?", new String[]{str2, str + MantoProcessUtil.getContext().getString(R.string.manto_kv_key_suffix)}, null);
        if (storageEntity != null) {
            return a(storageEntity.data, 0);
        }
        return 0;
    }

    public static final a b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return a.MISSING_PARAMS;
        }
        StorageEntity storageEntity = (StorageEntity) com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(StorageEntity.class, "appType=? AND key=?", new String[]{str3, str + "__" + str2}, null);
        if (storageEntity != null) {
            a(str, -storageEntity.dataSize, str3, 0);
            com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).a(storageEntity);
            return a.NONE;
        }
        return a.NONE;
    }

    public static Object[] c(String str, String str2) {
        String replace = (str + "__").replace(CartConstant.KEY_YB_INFO_LINK, "\\_");
        List b = com.jingdong.manto.provider.db.a.b(MantoProcessUtil.getContext()).b(StorageEntity.class, "appType=? AND key LIKE '" + replace + "%' escape '\\'", new String[]{str2}, null);
        ArrayList arrayList = new ArrayList();
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                arrayList.add(((StorageEntity) it.next()).key.replace(str + "__", ""));
            }
        }
        return new Object[]{arrayList, Integer.valueOf(b(str, str2)), 10485760};
    }
}
