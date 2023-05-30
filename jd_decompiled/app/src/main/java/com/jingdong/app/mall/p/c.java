package com.jingdong.app.mall.p;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.R;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class c {
    private Context a;
    private ShortcutManager b;

    /* renamed from: c  reason: collision with root package name */
    private ArrayList<ShortcutInfo> f11462c = new ArrayList<>();

    @RequiresApi(api = 25)
    public c(Context context) {
        this.a = context;
        this.b = (ShortcutManager) context.getSystemService(ShortcutManager.class);
    }

    @RequiresApi(api = 25)
    public void a(ArrayList<b> arrayList) {
        try {
            c(arrayList);
            StringBuilder sb = new StringBuilder();
            Iterator<b> it = arrayList.iterator();
            while (it.hasNext()) {
                b next = it.next();
                int a = next.a();
                String c2 = next.c();
                next.b();
                String e2 = next.e();
                String d = next.d();
                if (a != 0 && a <= 4 && !TextUtils.isEmpty(c2) && !TextUtils.isEmpty(e2) && !TextUtils.isEmpty(d)) {
                    sb.append(d);
                    sb.append(DYConstants.DY_REGEX_COMMA);
                    if (JumpUtil.VAULE_DES_PRODUCT_LIST.equals(d)) {
                        this.f11462c.add(new ShortcutInfo.Builder(this.a, d).setShortLabel(c2).setRank(a).setIcon(Icon.createWithResource(this.a, (int) R.drawable.icon_shortcuts_search)).setIntent(new Intent("android.intent.action.VIEW", Uri.parse(e2))).build());
                    }
                    if ("seckill".equals(d)) {
                        this.f11462c.add(new ShortcutInfo.Builder(this.a, d).setShortLabel(c2).setRank(a).setIcon(Icon.createWithResource(this.a, (int) R.drawable.icon_shortcuts_spike)).setIntent(new Intent("android.intent.action.VIEW", Uri.parse(e2))).build());
                    }
                    if (JumpUtil.VAULE_DES_ORDER_LIST.equals(d)) {
                        this.f11462c.add(new ShortcutInfo.Builder(this.a, d).setShortLabel(c2).setRank(a).setIcon(Icon.createWithResource(this.a, (int) R.drawable.icon_shortcuts_orderlist)).setIntent(new Intent("android.intent.action.VIEW", Uri.parse(e2))).build());
                    }
                    if ("saoasao".equals(d)) {
                        this.f11462c.add(new ShortcutInfo.Builder(this.a, d).setShortLabel(c2).setRank(a).setIcon(Icon.createWithResource(this.a, (int) R.drawable.icon_shortcuts_saoasao)).setIntent(new Intent("android.intent.action.VIEW", Uri.parse(e2))).build());
                    }
                    if ("dayticket".equals(d)) {
                        this.f11462c.add(new ShortcutInfo.Builder(this.a, d).setShortLabel(c2).setRank(a).setIcon(Icon.createWithResource(this.a, (int) R.drawable.icon_shortcuts_dayticket)).setIntent(new Intent("android.intent.action.VIEW", Uri.parse(e2))).build());
                    }
                    if ("jddou".equals(d)) {
                        this.f11462c.add(new ShortcutInfo.Builder(this.a, d).setShortLabel(c2).setRank(a).setIcon(Icon.createWithResource(this.a, (int) R.drawable.icon_shortcuts_jddou)).setIntent(new Intent("android.intent.action.VIEW", Uri.parse(e2))).build());
                    }
                }
            }
            CommonBase.putStringToPreference("quick_action_shortcut", sb.toString());
            this.b.setDynamicShortcuts(this.f11462c);
        } catch (Exception unused) {
        }
    }

    @RequiresApi(api = 25)
    public void b() {
        this.b.removeAllDynamicShortcuts();
        String stringFromPreference = CommonBase.getStringFromPreference("quick_action_shortcut", "");
        if (Log.D) {
            Log.d("ShortcutsConfig", "removeAllShortcuts()-\u5c55\u793a\u8fc7\u7684\u5feb\u6377\u65b9\u5f0f= " + stringFromPreference);
        }
        if (TextUtils.isEmpty(stringFromPreference)) {
            return;
        }
        List asList = Arrays.asList(stringFromPreference.split(DYConstants.DY_REGEX_COMMA));
        if (Log.D) {
            Log.d("ShortcutsConfig", "removeAllShortcuts()-\u5c55\u793a\u8fc7\u7684\u5feb\u6377\u65b9\u5f0f= " + asList);
        }
        List<ShortcutInfo> pinnedShortcuts = this.b.getPinnedShortcuts();
        ArrayList arrayList = new ArrayList();
        if (asList == null || pinnedShortcuts == null || pinnedShortcuts.size() <= 0) {
            return;
        }
        for (ShortcutInfo shortcutInfo : pinnedShortcuts) {
            if (asList.contains(shortcutInfo.getId())) {
                arrayList.add(shortcutInfo.getId());
            }
        }
        this.b.disableShortcuts(arrayList);
    }

    @RequiresApi(api = 25)
    public void c(ArrayList<b> arrayList) {
        this.b.removeAllDynamicShortcuts();
        ArrayList arrayList2 = new ArrayList();
        Iterator<b> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().d());
        }
        if (Log.D) {
            Log.d("ShortcutsConfig", "\u8981\u5c55\u793a\u7684\u5feb\u6377\u65b9\u5f0f= " + arrayList2.toString());
        }
        ArrayList arrayList3 = new ArrayList();
        String stringFromPreference = CommonBase.getStringFromPreference("quick_action_shortcut", "");
        if (!TextUtils.isEmpty(stringFromPreference)) {
            List<String> asList = Arrays.asList(stringFromPreference.split(DYConstants.DY_REGEX_COMMA));
            if (!asList.isEmpty()) {
                for (String str : asList) {
                    if (!arrayList2.contains(str)) {
                        arrayList3.add(str);
                    }
                }
            }
            if (Log.D) {
                Log.d("ShortcutsConfig", "\u5c55\u793a\u8fc7\u7684\u5feb\u6377\u65b9\u5f0f= " + asList + " diffIds=" + arrayList3.toString());
            }
        }
        List<ShortcutInfo> pinnedShortcuts = this.b.getPinnedShortcuts();
        ArrayList arrayList4 = new ArrayList();
        if (pinnedShortcuts == null || pinnedShortcuts.size() <= 0) {
            return;
        }
        for (ShortcutInfo shortcutInfo : pinnedShortcuts) {
            if (arrayList3.contains(shortcutInfo.getId())) {
                arrayList4.add(shortcutInfo.getId());
            }
        }
        this.b.disableShortcuts(arrayList4);
    }
}
