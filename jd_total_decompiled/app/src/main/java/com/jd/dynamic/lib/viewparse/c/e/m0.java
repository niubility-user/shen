package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Typeface;
import android.text.SpannedString;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.TextViewCompat;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.lib.utils.DPIUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class m0 extends p0<AppCompatTextView> {
    private String f(String str) {
        long j2;
        try {
            j2 = Long.parseLong(str);
        } catch (Exception unused) {
            j2 = 0;
        }
        return com.jd.dynamic.lib.utils.m.i(j2);
    }

    /* renamed from: g */
    public void j(AppCompatTextView appCompatTextView, List<String> list, float f2, String str) {
        if (appCompatTextView.getText() instanceof SpannedString) {
            return;
        }
        if (com.jd.dynamic.lib.utils.m.I(list)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                DynamicTemplateEngine dynamicTemplateEngine = this.a;
                com.jd.dynamic.lib.utils.s.b(it.next(), dynamicTemplateEngine.currentData, dynamicTemplateEngine, appCompatTextView);
            }
        }
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(appCompatTextView, (int) n0.f(str, 1.0f), (int) f2, 1, 2);
    }

    private void i(HashMap<String, String> hashMap, final AppCompatTextView appCompatTextView, final List<String> list) {
        int i2 = R.id.dynamic_text_size_origin;
        final float floatValue = appCompatTextView.getTag(i2) != null ? ((Float) appCompatTextView.getTag(i2)).floatValue() : DPIUtil.px2dip(appCompatTextView.getTextSize());
        final String str = hashMap.get(DYConstants.DY_AUTO_MIN_SIZE);
        if (appCompatTextView.getLayoutParams() == null || appCompatTextView.getLayoutParams().width == -2 || appCompatTextView.getLayoutParams().height == -2) {
            appCompatTextView.post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.c.e.x
                {
                    m0.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    m0.this.j(appCompatTextView, list, floatValue, str);
                }
            });
        } else {
            j(appCompatTextView, list, floatValue, str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:388:0x0175, code lost:
        if (com.jd.dynamic.DYConstants.DY_JD_LIGHT.equals(r5) != false) goto L377;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x0262, code lost:
        if (r13.getTag(com.jd.dynamic.R.id.dynamic_use_auto_size) != null) goto L436;
     */
    /* JADX WARN: Removed duplicated region for block: B:449:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x0273  */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    @RequiresApi(api = 16)
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(HashMap<String, String> hashMap, AppCompatTextView appCompatTextView) {
        int i2;
        Typeface a;
        TextUtils.TruncateAt truncateAt;
        List<String> list = null;
        if (hashMap.containsKey(DYConstants.DY_TEXT_ELLIPSIZE) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_TEXT_ELLIPSIZE))) {
            int i3 = R.id.dynamic_text_view_char;
            appCompatTextView.setTag(i3, null);
            String str = hashMap.get(DYConstants.DY_TEXT_ELLIPSIZE);
            if ("none".equals(str)) {
                truncateAt = TextUtils.TruncateAt.MARQUEE;
            } else if ("start".equals(str)) {
                truncateAt = TextUtils.TruncateAt.START;
            } else if (DYConstants.DY_MIDDLE.equals(str)) {
                truncateAt = TextUtils.TruncateAt.MIDDLE;
            } else if ("end".equals(str)) {
                truncateAt = TextUtils.TruncateAt.END;
            } else if (DYConstants.DY_CHAR.equals(str)) {
                appCompatTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                appCompatTextView.setTag(i3, com.jd.dynamic.b.c.a.b);
            }
            appCompatTextView.setEllipsize(truncateAt);
        }
        boolean z = false;
        TextViewCompat.setAutoSizeTextTypeWithDefaults(appCompatTextView, 0);
        int i4 = R.id.dynamic_text_size_origin;
        if (appCompatTextView.getTag(i4) != null) {
            appCompatTextView.setTextSize(2, ((Float) appCompatTextView.getTag(i4)).floatValue());
        }
        String str2 = hashMap.get(DYConstants.DY_ATTRIBUTED_TEXT);
        boolean z2 = !TextUtils.isEmpty(str2);
        if (hashMap.containsKey("text")) {
            boolean containsKey = hashMap.containsKey(DYConstants.DY_COUNTDOWN);
            String str3 = hashMap.get("text");
            if (containsKey) {
                str3 = f(str3);
            } else if (!z2) {
                com.jd.dynamic.lib.utils.m.v(str3, appCompatTextView, null);
            }
            appCompatTextView.setText(str3);
        }
        if (!hashMap.containsKey(DYConstants.DY_TEXT_MAXLINES) || TextUtils.isEmpty(hashMap.get(DYConstants.DY_TEXT_MAXLINES))) {
            int maxLines = appCompatTextView.getMaxLines();
            if (com.jd.dynamic.b.a.b.o().b()) {
                if (maxLines != 1) {
                    appCompatTextView.setSingleLine(false);
                }
                appCompatTextView.setSingleLine();
            }
            appCompatTextView.setMaxLines(maxLines);
        } else {
            int i5 = Integer.MAX_VALUE;
            try {
                int parseInt = Integer.parseInt(hashMap.get(DYConstants.DY_TEXT_MAXLINES));
                if (parseInt > 0) {
                    i5 = parseInt;
                }
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidTextParse parse maxLines catch error", b(), d(), e2);
            }
            if (com.jd.dynamic.b.a.b.o().b()) {
                if (i5 != 1) {
                    appCompatTextView.setSingleLine(false);
                }
                appCompatTextView.setSingleLine();
            }
            appCompatTextView.setMaxLines(i5);
        }
        if (hashMap.containsKey(DYConstants.DY_TEXT_STYLE) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_TEXT_STYLE))) {
            String str4 = hashMap.get(DYConstants.DY_TEXT_STYLE);
            if ("normal".equals(str4)) {
                a = Typeface.DEFAULT;
            } else if ("bold".equals(str4)) {
                a = Typeface.DEFAULT_BOLD;
            } else {
                String str5 = DYConstants.DY_JD_ZH_BOLD;
                if (!DYConstants.DY_JD_ZH_BOLD.equals(str4)) {
                    str5 = DYConstants.DY_JD_ZH_NORMAL;
                    if (!DYConstants.DY_JD_ZH_NORMAL.equals(str4)) {
                        str5 = DYConstants.DY_JD_NORMAL;
                        if (!DYConstants.DY_JD_NORMAL.equals(str4)) {
                            str5 = DYConstants.DY_JD_BOLD;
                            if (!DYConstants.DY_JD_BOLD.equals(str4)) {
                                str5 = DYConstants.DY_JD_LIGHT;
                            }
                        }
                    }
                }
                a = com.jd.dynamic.lib.utils.i.a(appCompatTextView.getContext(), str5);
            }
            appCompatTextView.setTypeface(a);
        }
        if (com.jd.dynamic.b.a.b.o().w("textViewGravity")) {
            if (hashMap.containsKey(DYConstants.DY_GRAVITY) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_GRAVITY))) {
                String str6 = hashMap.get(DYConstants.DY_GRAVITY);
                if (!DYConstants.DY_CENTER.equals(str6)) {
                    if ("left".equals(str6)) {
                        i2 = 19;
                    } else if ("right".equals(str6)) {
                        i2 = 21;
                    }
                    appCompatTextView.setGravity(i2);
                }
                appCompatTextView.setGravity(17);
            }
        } else if (hashMap.containsKey(DYConstants.DY_GRAVITY) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_GRAVITY))) {
            String str7 = hashMap.get(DYConstants.DY_GRAVITY);
            if (!DYConstants.DY_CENTER.equals(str7)) {
                if ("left".equals(str7)) {
                    i2 = GravityCompat.START;
                } else if ("right".equals(str7)) {
                    i2 = GravityCompat.END;
                } else if (DYConstants.DY_CENTER_VERTICAL.equals(str7)) {
                    i2 = 16;
                } else if (DYConstants.DY_CENTER_HORIZONTAL.equals(str7)) {
                    appCompatTextView.setGravity(1);
                }
                appCompatTextView.setGravity(i2);
            }
            appCompatTextView.setGravity(17);
        }
        if (hashMap.containsKey("includeFontPadding")) {
            String str8 = hashMap.get("includeFontPadding");
            if ("1".equals(str8)) {
                appCompatTextView.setIncludeFontPadding(true);
            } else if ("0".equals(str8)) {
                appCompatTextView.setIncludeFontPadding(false);
            }
        }
        String str9 = hashMap.get(DYConstants.DY_AUTO_SIZE);
        if (!TextUtils.equals("1", str9)) {
            if (!TextUtils.isEmpty(str9) && !TextUtils.equals("1", str9)) {
                appCompatTextView.setTag(R.id.dynamic_use_auto_size, null);
            }
            if (z2 && str2 != null) {
                list = com.jd.dynamic.lib.utils.s.i(str2);
            }
            if (!z) {
                i(hashMap, appCompatTextView, list);
                return;
            } else if (com.jd.dynamic.lib.utils.m.I(list)) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    DynamicTemplateEngine dynamicTemplateEngine = this.a;
                    com.jd.dynamic.lib.utils.s.b(it.next(), dynamicTemplateEngine.currentData, dynamicTemplateEngine, appCompatTextView);
                }
                return;
            } else {
                return;
            }
        }
        appCompatTextView.setTag(R.id.dynamic_use_auto_size, com.jd.dynamic.b.c.a.b);
        z = true;
        if (z2) {
            list = com.jd.dynamic.lib.utils.s.i(str2);
        }
        if (!z) {
        }
    }
}
