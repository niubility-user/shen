package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class d0 extends p0<EditText> {
    private String f(String str) {
        long j2;
        try {
            j2 = Long.parseLong(str);
        } catch (Exception unused) {
            j2 = 0;
        }
        return com.jd.dynamic.lib.utils.m.i(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(List list, EditText editText) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DynamicTemplateEngine dynamicTemplateEngine = this.a;
            com.jd.dynamic.lib.utils.s.b((String) it.next(), dynamicTemplateEngine.currentData, dynamicTemplateEngine, editText);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0118, code lost:
        if (com.jd.dynamic.DYConstants.DY_JD_LIGHT.equals(r0) != false) goto L55;
     */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    @RequiresApi(api = 16)
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(HashMap<String, String> hashMap, final EditText editText) {
        String str;
        int b;
        Typeface a;
        TextUtils.TruncateAt truncateAt;
        if (hashMap.containsKey("text")) {
            boolean containsKey = hashMap.containsKey(DYConstants.DY_COUNTDOWN);
            String str2 = hashMap.get("text");
            editText.setText(containsKey ? f(str2) : str2);
        }
        int i2 = Integer.MAX_VALUE;
        if (hashMap.containsKey(DYConstants.DY_TEXT_MAXLINES) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_TEXT_MAXLINES))) {
            try {
                int parseInt = Integer.parseInt(hashMap.get(DYConstants.DY_TEXT_MAXLINES));
                if (parseInt > 0) {
                    i2 = parseInt;
                }
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidTextParse parse maxLines catch error", b(), d(), e2);
            }
            editText.setMaxLines(i2);
        } else if (editText.getMaxLines() == 1 || editText.getMaxLines() == Integer.MAX_VALUE) {
            editText.setMaxLines(1);
        }
        if (hashMap.containsKey(DYConstants.DY_TEXT_ELLIPSIZE) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_TEXT_ELLIPSIZE))) {
            String str3 = hashMap.get(DYConstants.DY_TEXT_ELLIPSIZE);
            if ("none".equals(str3)) {
                truncateAt = TextUtils.TruncateAt.MARQUEE;
            } else if ("start".equals(str3)) {
                truncateAt = TextUtils.TruncateAt.START;
            } else if (DYConstants.DY_MIDDLE.equals(str3)) {
                truncateAt = TextUtils.TruncateAt.MIDDLE;
            } else if ("end".equals(str3)) {
                truncateAt = TextUtils.TruncateAt.END;
            }
            editText.setEllipsize(truncateAt);
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
                        str5 = DYConstants.DY_JD_BOLD;
                        if (!DYConstants.DY_JD_BOLD.equals(str4)) {
                            str5 = DYConstants.DY_JD_NORMAL;
                            if (!DYConstants.DY_JD_NORMAL.equals(str4)) {
                                str5 = DYConstants.DY_JD_LIGHT;
                            }
                        }
                    }
                }
                a = com.jd.dynamic.lib.utils.i.a(editText.getContext(), str5);
            }
            editText.setTypeface(a);
        }
        if (hashMap.containsKey(DYConstants.DY_GRAVITY)) {
            String str6 = hashMap.get(DYConstants.DY_GRAVITY);
            if (!TextUtils.isEmpty(str6)) {
                String[] split = str6.split("-");
                if (split.length > 1) {
                    b = com.jd.dynamic.lib.utils.m.b(split[1], false) | com.jd.dynamic.lib.utils.m.b(split[0], true);
                } else if (split.length > 0) {
                    b = com.jd.dynamic.lib.utils.m.b(split[0], true) | 16;
                }
                editText.setGravity(b);
            }
        }
        if (!hashMap.containsKey(DYConstants.DY_ATTRIBUTED_TEXT) || TextUtils.isEmpty(hashMap.get(DYConstants.DY_ATTRIBUTED_TEXT)) || (str = hashMap.get(DYConstants.DY_ATTRIBUTED_TEXT)) == null) {
            return;
        }
        final List<String> i3 = com.jd.dynamic.lib.utils.s.i(str);
        editText.post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.c.e.d
            @Override // java.lang.Runnable
            public final void run() {
                d0.this.h(i3, editText);
            }
        });
    }
}
