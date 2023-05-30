package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.lib.puppetlayout.view.ui.TextWidget;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ColorUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager;
import com.jingdong.sdk.lib.puppetlayout.ylayout.UnUtils;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.lib.puppetlayout.ylayout.util.Range;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes8.dex */
public class n extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private TextWidget f15254k;

    /* renamed from: l  reason: collision with root package name */
    private float f15255l = 0.0f;

    /* renamed from: m  reason: collision with root package name */
    private String f15256m = null;

    /* renamed from: n  reason: collision with root package name */
    private JDJSONArray f15257n = null;
    private ArrayList<Range<Integer>> o = null;
    public String p = null;

    private void v() {
        ArrayList<Range<Integer>> arrayList = this.o;
        if (arrayList != null) {
            arrayList.clear();
            this.o = null;
        }
        this.p = null;
    }

    private int w(String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        HashMap hashMap = new HashMap();
        for (String str4 : str.split(DYConstants.DY_REGEX_COMMA, 2)) {
            if (!TextUtils.isEmpty(str4)) {
                String[] split = str4.split(":");
                if (split.length == 2 && split[0] != null && split[1] != null) {
                    hashMap.put(split[0], split[1]);
                }
            }
        }
        if (TextUtils.isEmpty((CharSequence) hashMap.get("android"))) {
            return -1;
        }
        String[] split2 = ((String) hashMap.get("android")).split("/");
        String packageName = this.f15254k.getContext().getPackageName();
        if (split2.length >= 2) {
            str2 = split2[1];
            str3 = split2[0];
        } else {
            str2 = split2.length == 1 ? split2[0] : "";
            str3 = packageName;
        }
        return this.f15254k.getContext().getResources().getIdentifier(str2, "string", str3);
    }

    private void x() {
        SpannableString spannableString = new SpannableString(this.f15256m);
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            Range<Integer> range = this.o.get(i2);
            JDJSONObject optJSONObject = this.f15257n.optJSONObject(i2);
            if (!TextUtils.isEmpty(optJSONObject.optString("rlSize"))) {
                try {
                    float floatValue = Float.valueOf(optJSONObject.optString("rlSize")).floatValue();
                    if (floatValue > 0.0f) {
                        spannableString.setSpan(new RelativeSizeSpan(floatValue), range.getLower().intValue(), range.getUpper().intValue(), 17);
                    }
                } catch (Exception e2) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e2.printStackTrace();
                    }
                }
            }
            if (!TextUtils.isEmpty(optJSONObject.optString("strike"))) {
                try {
                    spannableString.setSpan(new StrikethroughSpan(), range.getLower().intValue(), range.getUpper().intValue(), 17);
                } catch (Exception e3) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e3.printStackTrace();
                    }
                }
            }
        }
        this.f15254k.setText(spannableString);
    }

    private HashMap<String, String> y(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split(DYConstants.DY_REGEX_COMMA)) {
            if (!TextUtils.isEmpty(str2)) {
                if (str2.startsWith("url:")) {
                    hashMap.put("url", str2.substring(4, str2.length()));
                } else {
                    String[] split = str2.split(":");
                    if (split.length == 2 && split[0] != null && split[1] != null) {
                        hashMap.put(split[0], split[1]);
                    }
                }
            }
        }
        return hashMap;
    }

    private void z(String str) {
        JDJSONArray jDJSONArray = this.f15257n;
        if (jDJSONArray != null) {
            jDJSONArray.clear();
            this.f15257n = null;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.f15257n = JDJSON.parseArray(str);
        } catch (Exception e2) {
            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                e2.printStackTrace();
            }
        }
    }

    public void A(TextWidget textWidget) {
        this.f15254k = textWidget;
        this.a = textWidget;
    }

    public boolean B(String str, LinkedHashMap<String, String> linkedHashMap) {
        String[] split;
        v();
        if (this.f15257n == null) {
            return false;
        }
        if (linkedHashMap != null && linkedHashMap.size() > 0 && !TextUtils.isEmpty(str) && (split = Pattern.compile("\\{(.*?)\\}").split(str, -1)) != null && split.length > 0) {
            this.o = new ArrayList<>();
            try {
                int i2 = 0;
                int i3 = 0;
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    this.o.add(Range.create(Integer.valueOf(split[i3].length() + i2), Integer.valueOf(split[i3].length() + i2 + entry.getValue().length())));
                    i2 += split[i3].length() + entry.getValue().length();
                    i3++;
                }
            } catch (Exception e2) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e2.printStackTrace();
                }
                v();
            }
        }
        if (this.o != null) {
            this.p = str;
            return true;
        }
        return false;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        TextWidget textWidget = new TextWidget(context);
        this.f15254k = textWidget;
        this.a = textWidget;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public boolean q(String str, String str2, String str3) {
        if (super.q(str, str2, str3)) {
            return true;
        }
        if ("text".equals(str)) {
            this.f15256m = str2;
            if (str2 == null) {
                this.f15254k.setTextSize(1, 0.0f);
                this.f15254k.f(null);
            } else {
                if (str2.startsWith("@local/")) {
                    try {
                        int w = w(str2.substring(7, str2.length()));
                        if (w != -1 && w != 0) {
                            this.f15254k.setText(w);
                        }
                    } catch (Exception e2) {
                        if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                            e2.printStackTrace();
                        }
                    }
                } else if (u()) {
                    x();
                } else {
                    this.f15254k.f(this.f15256m);
                }
                float f2 = this.f15255l;
                if (f2 != 0.0f) {
                    this.f15254k.setTextSize(1, f2);
                }
            }
            try {
                if (this.f15254k.getParent() != null && (this.f15254k.getParent() instanceof YogaLayout)) {
                    ((YogaLayout) this.f15254k.getParent()).invalidate(this.f15254k);
                }
            } catch (Exception e3) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e3.printStackTrace();
                }
            }
        } else if (ViewProps.FONT_SIZE.equals(str)) {
            try {
                this.f15255l = 0.0f;
                if (str2.endsWith("dp")) {
                    if (str2.length() > 2) {
                        float floatValue = Float.valueOf(str2.substring(0, str2.length() - 2)).floatValue();
                        this.f15255l = floatValue;
                        this.f15254k.setTextSize(1, floatValue);
                    }
                } else {
                    float floatValue2 = Float.valueOf(str2).floatValue();
                    this.f15255l = floatValue2;
                    this.f15254k.setTextSize(1, floatValue2);
                }
            } catch (Exception e4) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e4.printStackTrace();
                }
            }
        } else if ("color".equals(str)) {
            this.f15254k.a(str2);
        } else if (DeeplinkProductDetailHelper.LAYER_STYLE.equals(str)) {
            this.f15254k.e(str2);
        } else if ("jdZH".equals(str)) {
            this.f15254k.d(str2);
        } else if ("maxlines".equals(str)) {
            try {
                this.f15254k.setMaxLines(Integer.parseInt(str2));
            } catch (NumberFormatException e5) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e5.printStackTrace();
                }
            }
        } else if (DYConstants.DY_TEXT_ELLIPSIZE.equals(str)) {
            this.f15254k.b(str2);
        } else if ("includeFontPadding".equals(str)) {
            this.f15254k.c(str2);
        } else if ("font".equals(str)) {
            if ("bold".equals(str2)) {
                this.f15254k.setTypeface(null, 1);
            } else if (FontsUtil.KEY_MULTI_REGULAR.equals(str2)) {
                this.f15254k.setTypeface(null, 0);
            } else if ("JDBBold".equals(str2)) {
                com.jingdong.sdk.lib.puppetlayout.e.a.a(this.f15254k, 4097);
            } else if ("JDBRegular".equals(str2)) {
                com.jingdong.sdk.lib.puppetlayout.e.a.a(this.f15254k, 4099);
            } else if ("JDBLight".equals(str2)) {
                com.jingdong.sdk.lib.puppetlayout.e.a.a(this.f15254k, 4098);
            }
        } else if ("linesNum".equals(str)) {
            if (!"0".equals(str2)) {
                try {
                    this.f15254k.setMaxLines(Integer.parseInt(str2));
                } catch (NumberFormatException e6) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e6.printStackTrace();
                    }
                }
            }
        } else if ("lineBreakMode".equals(str)) {
            if ("truncatingHead".equals(str2)) {
                this.f15254k.setEllipsize(TextUtils.TruncateAt.START);
            } else if ("truncatingTail".equals(str2)) {
                this.f15254k.setEllipsize(TextUtils.TruncateAt.END);
            } else if ("truncatingMiddle".equals(str2)) {
                this.f15254k.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            }
        } else if (ViewProps.TEXT_ALIGN.equals(str)) {
            this.f15254k.setGravity(com.jingdong.sdk.lib.puppetlayout.h.c.a.b(str2).a);
        } else if ("shadow".equals(str)) {
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    String[] split = str2.split(DYConstants.DY_REGEX_COMMA);
                    if (split.length == 4) {
                        int parseColor = Color.parseColor(ColorUtils.translate2ArgbColor(str2));
                        this.f15254k.setShadowLayer(Float.valueOf(split[1]).floatValue(), Float.valueOf(split[2]).floatValue(), Float.valueOf(split[3]).floatValue(), parseColor);
                    }
                }
            } catch (Exception e7) {
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    e7.printStackTrace();
                }
            }
        } else if ("unconfig".equals(str)) {
            HashMap<String, String> y = y(str2);
            if (y == null) {
                return false;
            }
            String str4 = y.get("type");
            if (!TextUtils.isEmpty(str4)) {
                if ("unify".equals(str4)) {
                    String str5 = y.get("iconId");
                    String str6 = y.get("text");
                    if (!TextUtils.isEmpty(str5)) {
                        try {
                            if (PuppetManager.getInstance().isNotDemo()) {
                                UnIconConfigHelper.setTextViewProperties(str5, this.f15254k);
                            }
                        } catch (Exception e8) {
                            if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                                e8.printStackTrace();
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(str6)) {
                        this.f15254k.setIncludeFontPadding(false);
                        this.f15254k.f(str6);
                    }
                } else if ("unspan".equals(str4)) {
                    UnUtils.setSpanText(y.get("iconIds"), y.get("text"), this.f15254k);
                }
                return true;
            }
            if (!TextUtils.isEmpty(y.get("unify"))) {
                try {
                    UnIconConfigHelper.setTextViewProperties(y.get("unify"), this.f15254k);
                } catch (Exception e9) {
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        e9.printStackTrace();
                    }
                }
            }
            if (DYConstants.DY_TRUE.equals(y.get("includeFontPadding"))) {
                this.f15254k.setIncludeFontPadding(true);
            } else if (DYConstants.DY_FALSE.equals(y.get("includeFontPadding"))) {
                this.f15254k.setIncludeFontPadding(false);
            }
            if (!TextUtils.isEmpty(y.get("text"))) {
                this.f15254k.f(y.get("text"));
            }
        } else if ("spanList".equals(str)) {
            z(str2);
        }
        return true;
    }

    public boolean u() {
        ArrayList<Range<Integer>> arrayList;
        JDJSONArray jDJSONArray = this.f15257n;
        return jDJSONArray != null && jDJSONArray.size() > 0 && (arrayList = this.o) != null && arrayList.size() > 0 && this.f15257n.size() == this.o.size() && this.p != null;
    }
}
