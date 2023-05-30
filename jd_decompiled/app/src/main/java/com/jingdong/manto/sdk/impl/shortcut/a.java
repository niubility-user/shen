package com.jingdong.manto.sdk.impl.shortcut;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.api.IShortcutManager;
import com.jingdong.manto.utils.MantoCryptoUtils;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class a implements IShortcutManager {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.sdk.impl.shortcut.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class DialogInterfaceOnClickListenerC0673a implements DialogInterface.OnClickListener {
        final /* synthetic */ Activity a;

        DialogInterfaceOnClickListenerC0673a(a aVar, Activity activity) {
            this.a = activity;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                Intent intent = new Intent();
                intent.addFlags(268435456);
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", this.a.getPackageName(), null));
                this.a.startActivity(intent);
            } catch (Exception unused) {
                Toast.makeText(this.a, "\u8bbe\u7f6e\u9875\u6253\u5f00\u5931\u8d25", 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements DialogInterface.OnClickListener {
        b(a aVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.cancel();
        }
    }

    /* loaded from: classes16.dex */
    class c implements IShortcutManager {
        c(a aVar) {
        }

        private Intent a(Context context, IShortcutManager.a aVar) {
            Bitmap c2;
            String b = a.b(aVar);
            if (b == null || (c2 = a.c(context, aVar.b, aVar.d)) == null) {
                return null;
            }
            Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent.putExtra("android.intent.extra.shortcut.NAME", aVar.a);
            intent.putExtra("duplicate", false);
            intent.putExtra("android.intent.extra.shortcut.ICON", c2);
            Intent intent2 = new Intent("com.jingdong.action.MANTO_SHORTCUT");
            intent2.putExtra("extra_info", b);
            intent2.setPackage(context.getPackageName());
            intent2.addFlags(1073741824);
            intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
            return intent;
        }

        @Override // com.jingdong.manto.sdk.api.IShortcutManager
        public boolean sendToDesktop(Activity activity, IShortcutManager.a aVar) {
            Intent a = a(activity, aVar);
            if (a == null) {
                return false;
            }
            activity.sendBroadcast(a);
            Toast makeText = Toast.makeText(activity, "\u5df2\u6dfb\u52a0", 1);
            makeText.setGravity(17, 0, 0);
            makeText.show();
            return true;
        }
    }

    /* loaded from: classes16.dex */
    class d implements IShortcutManager {
        d(a aVar) {
        }

        @TargetApi(25)
        private boolean a(ShortcutInfo shortcutInfo, ShortcutManager shortcutManager) {
            for (ShortcutInfo shortcutInfo2 : shortcutManager.getPinnedShortcuts()) {
                if (shortcutInfo2.getId().equals(shortcutInfo.getId())) {
                    return true;
                }
                if (shortcutInfo2.getShortLabel() != null && shortcutInfo2.getShortLabel().equals(shortcutInfo.getShortLabel())) {
                    return true;
                }
                if (shortcutInfo2.getLongLabel() != null && shortcutInfo2.getLongLabel().equals(shortcutInfo.getLongLabel())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.jingdong.manto.sdk.api.IShortcutManager
        @TargetApi(26)
        public boolean sendToDesktop(Activity activity, IShortcutManager.a aVar) {
            Bitmap c2;
            ShortcutManager shortcutManager = (ShortcutManager) activity.getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                String b = a.b(aVar);
                if (MantoStringUtils.isEmpty(b) || (c2 = a.c(activity, aVar.b, aVar.d)) == null) {
                    return false;
                }
                Intent intent = new Intent("com.jingdong.action.MANTO_SHORTCUT");
                intent.putExtra("extra_info", b);
                intent.setPackage(activity.getPackageName());
                intent.addFlags(1073741824);
                ShortcutInfo.Builder builder = new ShortcutInfo.Builder(activity, b);
                builder.setActivity(new ComponentName(activity.getPackageName(), "com.jingdong.manto.ui.MantoShortcutEntry"));
                builder.setShortLabel(aVar.a).setLongLabel(aVar.a).setIntent(intent);
                builder.setIcon(Icon.createWithBitmap(c2));
                ShortcutInfo build = builder.build();
                if (!a(build, shortcutManager)) {
                    boolean requestPinShortcut = shortcutManager.requestPinShortcut(build, PendingIntent.getBroadcast(activity, 0, new Intent(activity, ShortcutCallbackReceiver.class), Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728).getIntentSender());
                    if (requestPinShortcut) {
                        Toast makeText = Toast.makeText(activity, "\u5df2\u53d1\u9001\u5230\u684c\u9762\uff0c\u82e5\u672a\u6dfb\u52a0\u6210\u529f\uff0c\u8bf7\u67e5\u770b\u7cfb\u7edf\u6743\u9650", 1);
                        makeText.setGravity(17, 0, 0);
                        makeText.show();
                    }
                    return requestPinShortcut;
                }
                ArrayList arrayList = new ArrayList(1);
                arrayList.add(build);
                shortcutManager.updateShortcuts(arrayList);
                Toast makeText2 = Toast.makeText(activity, "\u5df2\u6dfb\u52a0", 1);
                makeText2.setGravity(17, 0, 0);
                makeText2.show();
                return true;
            }
            return false;
        }
    }

    private void a(Activity activity, String str) {
        if (MantoStringUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.manto.widget.dialog.a.a(activity, "\u63d0\u793a", str, "\u53bb\u8bbe\u7f6e", "\u53d6\u6d88", new DialogInterfaceOnClickListenerC0673a(this, activity), new b(this), null, null, null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(IShortcutManager.a aVar) {
        StringBuilder sb;
        String str;
        if (MantoStringUtils.isEmpty(aVar.f14183c) || MantoStringUtils.isEmpty(aVar.a)) {
            return null;
        }
        String str2 = aVar.f14183c + "#@#" + aVar.a + "#@#" + aVar.d;
        if (TextUtils.isEmpty(aVar.f14184e)) {
            sb = new StringBuilder();
            sb.append(str2);
            sb.append("#@#");
            str = "";
        } else {
            sb = new StringBuilder();
            sb.append(str2);
            sb.append("#@#");
            str = aVar.f14184e;
        }
        sb.append(str);
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(aVar.f14185f)) {
            sb2 = sb2 + "#@#" + aVar.f14185f;
        }
        return MantoCryptoUtils.encrypt(sb2);
    }

    private static void b(Context context, Bitmap bitmap, String str) {
        float density = MantoDensityUtils.getDensity(context);
        float f2 = density * 2.0f;
        Paint paint = new Paint();
        paint.setTextSize(density * 6.0f);
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        float measureText = paint.measureText(str);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f3 = fontMetrics.bottom;
        float f4 = f3 - fontMetrics.top;
        paint.setColor(context.getResources().getColor(R.color.manto_half_transparent));
        Canvas canvas = new Canvas(bitmap);
        RectF rectF = new RectF(bitmap.getWidth() - ((int) (measureText + ((density * 4.0f) * 2.0f))), bitmap.getHeight() - ((int) ((f2 * 2.0f) + f4)), bitmap.getWidth(), bitmap.getHeight());
        canvas.drawRoundRect(rectF, 6.0f, 6.0f, paint);
        paint.setColor(-1);
        canvas.drawText(str, rectF.centerX(), rectF.centerY() + ((f4 / 2.0f) - f3), paint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap c(Context context, Bitmap bitmap, String str) {
        Bitmap bitmap2 = null;
        if (context != null && bitmap != null && !bitmap.isRecycled()) {
            int density = (int) (MantoDensityUtils.getDensity(context) * 48.0f);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, density, density, false);
            if (createScaledBitmap != null) {
                bitmap2 = createScaledBitmap.copy(Bitmap.Config.ARGB_8888, true);
                createScaledBitmap.recycle();
            }
            if (TextUtils.equals("2", str)) {
                b(context, bitmap2, context.getResources().getString(R.string.manto_debug_type_dev));
            }
        }
        return bitmap2;
    }

    @Override // com.jingdong.manto.sdk.api.IShortcutManager
    public boolean sendToDesktop(Activity activity, IShortcutManager.a aVar) {
        boolean sendToDesktop = Build.VERSION.SDK_INT >= 26 ? new d(this).sendToDesktop(activity, aVar) : new c(this).sendToDesktop(activity, aVar);
        if (!sendToDesktop) {
            a(activity, activity.getResources().getString(R.string.manto_short_sent_tips));
        }
        return sendToDesktop;
    }
}
