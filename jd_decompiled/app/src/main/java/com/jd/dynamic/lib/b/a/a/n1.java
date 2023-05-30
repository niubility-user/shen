package com.jd.dynamic.lib.b.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.CachePool;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IUniConfig;
import com.jd.dynamic.entity.AttrMethod;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.RichTextEntity;
import com.jd.dynamic.lib.b.a.a.n1;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.views.CornerSimpleDraweeView;
import com.jd.dynamic.lib.views.RichTextViewContainer;
import com.jd.dynamic.lib.views.listeners.IMeasureListener;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class n1 extends e1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements IMeasureListener {
        final /* synthetic */ com.jd.dynamic.a.f a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f2176c;

        a(com.jd.dynamic.a.f fVar, String str, String str2) {
            this.a = fVar;
            this.b = str;
            this.f2176c = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(String str) {
            n1 n1Var = n1.this;
            com.jd.dynamic.lib.utils.s.b(str, n1Var.mTargetView, ((CommFunction) n1Var).mEngine, n1.this.mTargetView);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(Throwable th) {
        }

        @Override // com.jd.dynamic.lib.views.listeners.IMeasureListener
        public void onMeasureInfo(JSONObject jSONObject) {
            com.jd.dynamic.a.f fVar = this.a;
            if (fVar != null) {
                fVar.a(jSONObject);
                return;
            }
            if (com.jd.dynamic.b.a.b.o().e()) {
                n1.this.addObjCache(this.b, jSONObject);
            } else {
                n1.this.addCache(this.b, jSONObject.toString());
            }
            if (TextUtils.isEmpty(this.f2176c)) {
                return;
            }
            Observable.from(com.jd.dynamic.lib.utils.s.i(this.f2176c)).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.a1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    n1.a.this.a((String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.b1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    n1.a.b((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class b extends ImageSpan {

        /* renamed from: g  reason: collision with root package name */
        private boolean f2177g;

        public b(Context context, Bitmap bitmap, int i2) {
            super(context, bitmap, i2);
            this.f2177g = true;
        }

        public void a(boolean z) {
            this.f2177g = z;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
            int i7;
            int i8;
            Drawable drawable = getDrawable();
            canvas.save();
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            if (this.f2177g) {
                int i9 = fontMetricsInt.ascent;
                i7 = fontMetricsInt.top + i5;
                int i10 = fontMetricsInt.descent;
                i8 = fontMetricsInt.bottom;
            } else {
                i7 = fontMetricsInt.ascent + i5;
                i8 = fontMetricsInt.descent;
            }
            int i11 = (i5 + i8) - i7;
            int i12 = drawable.getBounds().bottom - drawable.getBounds().top;
            int i13 = ((ImageSpan) this).mVerticalAlignment;
            canvas.translate(f2, i13 != 1 ? i13 != 3 ? i13 != 4 ? (i7 + i11) - i12 : (((i6 - i4) - i12) / 2) + i4 : i7 + ((i11 - i12) / 2) : ((i7 + i11) - i12) - r9);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    /* loaded from: classes13.dex */
    public class c {
        public Object a;
        public int b;

        /* renamed from: c  reason: collision with root package name */
        public int f2178c;

        public c(n1 n1Var, Object obj, int i2, int i3) {
            this.a = obj;
            this.b = i2;
            this.f2178c = i3;
        }
    }

    @SuppressLint({"DefaultLocale"})
    private Object c(JSONObject jSONObject) {
        TextUtils.TruncateAt truncateAt;
        boolean optBoolean = jSONObject.optBoolean(DYConstants.DY_IS_JS);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("width", 0);
            jSONObject2.put("height", 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.mEngine == null) {
            return jSONObject2;
        }
        TextView textView = new TextView(this.mEngine.getActivity());
        JSONObject optJSONObject = jSONObject.optJSONObject("value");
        String optString = jSONObject.optString("mode");
        if (optJSONObject != null) {
            String optString2 = optJSONObject.optString("text");
            if (TextUtils.isEmpty(optString2)) {
                return jSONObject2;
            }
            JSONArray optJSONArray = optJSONObject.optJSONArray("attributes");
            String optString3 = optJSONObject.optString("maxWidth");
            if (!TextUtils.isEmpty(optString3)) {
                textView.setMaxWidth(DPIUtil.dip2px(com.jd.dynamic.lib.utils.m.a(optString3, 2.14748365E9f)));
            }
            String optString4 = optJSONObject.optString("maxHeight");
            if (!TextUtils.isEmpty(optString4)) {
                textView.setMaxHeight(DPIUtil.dip2px(com.jd.dynamic.lib.utils.m.a(optString4, 2.14748365E9f)));
            }
            if (!TextUtils.isEmpty(optJSONObject.optString("lineSpace"))) {
                textView.setLineSpacing(DPIUtil.dip2px(Float.parseFloat(r3)), 1.0f);
            }
            String optString5 = optJSONObject.optString(DYConstants.DY_TEXT_MAXLINES);
            if (!TextUtils.isEmpty(optString5)) {
                textView.setMaxLines((int) com.jd.dynamic.lib.utils.m.a(optString5, 2.14748365E9f));
            }
            String optString6 = optJSONObject.optString(DYConstants.DY_TEXT_ELLIPSIZE);
            if (!TextUtils.isEmpty(optString6)) {
                char c2 = '\uffff';
                switch (optString6.hashCode()) {
                    case -1074341483:
                        if (optString6.equals(DYConstants.DY_MIDDLE)) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case 100571:
                        if (optString6.equals("end")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 3387192:
                        if (optString6.equals("none")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 109757538:
                        if (optString6.equals("start")) {
                            c2 = 1;
                            break;
                        }
                        break;
                }
                if (c2 == 0) {
                    truncateAt = TextUtils.TruncateAt.MARQUEE;
                } else if (c2 == 1) {
                    truncateAt = TextUtils.TruncateAt.START;
                } else if (c2 == 2) {
                    truncateAt = TextUtils.TruncateAt.MIDDLE;
                } else if (c2 == 3) {
                    truncateAt = TextUtils.TruncateAt.END;
                }
                textView.setEllipsize(truncateAt);
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(optString2);
            spannableStringBuilder.clearSpans();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    j(textView, spannableStringBuilder, optJSONArray.optJSONObject(i2), false, null);
                }
            }
            textView.setText(spannableStringBuilder);
            textView.measure(0, 0);
            if (optBoolean) {
                jSONObject2.put("width", DPIUtil.px2dipF(textView.getMeasuredWidth()));
                jSONObject2.put("height", DPIUtil.px2dipF(textView.getMeasuredHeight()));
                return jSONObject2;
            } else if ("width".equals(optString)) {
                return String.valueOf(DPIUtil.px2dipF(textView.getMeasuredWidth()));
            } else {
                if ("height".equals(optString)) {
                    return String.valueOf(DPIUtil.px2dipF(textView.getMeasuredHeight()));
                }
                jSONObject2.put("width", DPIUtil.px2dipF(textView.getMeasuredWidth()));
                jSONObject2.put("height", DPIUtil.px2dipF(textView.getMeasuredHeight()));
                return jSONObject2;
            }
        }
        try {
            jSONObject2.put("width", 0);
            jSONObject2.put("height", 0);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return jSONObject2;
    }

    private JSONObject d(JSONObject jSONObject, com.jd.dynamic.a.f fVar) {
        DynamicTemplateEngine dynamicTemplateEngine;
        ResultEntity resultEntity;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("width", 0);
            jSONObject2.put("height", 0);
        } catch (Exception unused) {
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("value");
        if (optJSONObject == null) {
            return jSONObject2;
        }
        String optString = optJSONObject.optString("src");
        String optString2 = optJSONObject.optString(DYConstants.RES_CODE);
        String optString3 = optJSONObject.optString(DYConstants.SHOW_NAME);
        String optString4 = optJSONObject.optString("cacheKey");
        String optString5 = optJSONObject.optString("callback");
        boolean z = (TextUtils.isEmpty(optString5) && fVar == null) ? false : true;
        if (!TextUtils.isEmpty(optString)) {
            if (optString.startsWith(DYConstants.DY_ASSETS) && (dynamicTemplateEngine = this.mEngine) != null && (resultEntity = dynamicTemplateEngine.entity) != null && !TextUtils.isEmpty(resultEntity.zipDir)) {
                optString = DYConstants.DY_FILE_PATH_START + this.mEngine.entity.zipDir + File.separator + optString;
            }
            if (optString.startsWith("http") || optString.startsWith(DYConstants.DY_FILE_PATH_START)) {
                if (!z) {
                    return com.jd.dynamic.lib.utils.m.o(optString, null);
                }
                com.jd.dynamic.lib.utils.m.o(optString, new a(fVar, optString4, optString5));
            }
        } else if (!TextUtils.isEmpty(optString3) || !TextUtils.isEmpty(optString2)) {
            return com.jd.dynamic.lib.utils.m.p(optString3, optString2);
        }
        return jSONObject2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void p(View view) {
        if (view.getParent() instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view.getParent();
            if (recyclerView.getAdapter() != null) {
                recyclerView.getAdapter().notifyItemChanged(recyclerView.getChildAdapterPosition(view));
                if ((recyclerView.getParent() instanceof View) && (recyclerView.getParent().getParent() instanceof YogaLayout)) {
                    ((YogaLayout) recyclerView.getParent().getParent()).invalidate((View) recyclerView.getParent());
                }
            }
        }
    }

    private void f(View view, String str, String str2) {
        AttrMethod setterMethod;
        if (view == null || (setterMethod = CachePool.getSetterMethod(str)) == null) {
            return;
        }
        try {
            Method method = view.getClass().getMethod(setterMethod.methodName, setterMethod.parameterTypes);
            if (method != null) {
                Class<?>[] clsArr = setterMethod.parameterTypes;
                method.invoke(view, clsArr != null ? com.jd.dynamic.lib.utils.m.h(clsArr[0], str2) : null);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void g(final View view, JSONObject jSONObject) {
        if (view == null) {
            view = this.mTargetView;
        }
        if (view != null) {
            final String optString = jSONObject.optString("value");
            String optString2 = jSONObject.optString("key");
            optString2.hashCode();
            char c2 = '\uffff';
            switch (optString2.hashCode()) {
                case -1957724523:
                    if (optString2.equals(DYConstants.DY_ATTRIBUTED_TEXT)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1551543255:
                    if (optString2.equals("richText")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1063571914:
                    if (optString2.equals(DYConstants.DY_TEXT_COLOR)) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -905800158:
                    if (optString2.equals(DYConstants.DY_SET_SRC)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 92909918:
                    if (optString2.equals("alpha")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 515545814:
                    if (optString2.equals("setHintText")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 1747544847:
                    if (optString2.equals("richTextMaxLine")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1941332754:
                    if (optString2.equals("visibility")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 1984984239:
                    if (optString2.equals("setText")) {
                        c2 = '\b';
                        break;
                    }
                    break;
            }
            try {
                switch (c2) {
                    case 0:
                        if (view instanceof TextView) {
                            k((TextView) view, jSONObject);
                        }
                        if (!(view.getParent() instanceof YogaLayout)) {
                            return;
                        }
                        ((YogaLayout) view.getParent()).invalidate(view);
                        return;
                    case 1:
                        if (TextUtils.isEmpty(optString) || !(view instanceof RichTextViewContainer)) {
                            return;
                        }
                        ((RichTextViewContainer) view).setCloseSufix(optString);
                        return;
                    case 2:
                        if (TextUtils.isEmpty(optString) || !(view instanceof TextView)) {
                            return;
                        }
                        ((TextView) view).setTextColor(Color.parseColor(optString));
                        return;
                    case 3:
                        if (TextUtils.isEmpty(optString) || !(view instanceof CornerSimpleDraweeView)) {
                            return;
                        }
                        HashMap<String, String> hashMap = new HashMap<>(1);
                        hashMap.put("src", optString);
                        com.jd.dynamic.lib.viewparse.c.e.j0 j0Var = new com.jd.dynamic.lib.viewparse.c.e.j0();
                        j0Var.e(this.mEngine);
                        j0Var.a(hashMap, (CornerSimpleDraweeView) view);
                        return;
                    case 4:
                        float parseFloat = Float.parseFloat(optString);
                        if (parseFloat > 1.0f) {
                            parseFloat = 1.0f;
                        }
                        if (parseFloat < 0.0f) {
                            parseFloat = 0.0f;
                        }
                        view.setAlpha(parseFloat);
                        return;
                    case 5:
                        if (view instanceof TextView) {
                            ((TextView) view).setHint(optString);
                        }
                        if (!(view.getParent() instanceof YogaLayout)) {
                            return;
                        }
                        ((YogaLayout) view.getParent()).invalidate(view);
                        return;
                    case 6:
                        if (TextUtils.isEmpty(optString) || !(view instanceof RichTextViewContainer)) {
                            return;
                        }
                        ((RichTextViewContainer) view).setRichMaxlines(Integer.parseInt(optString));
                        return;
                    case 7:
                        if (TextUtils.isEmpty(optString)) {
                            return;
                        }
                        Runnable runnable = new Runnable() { // from class: com.jd.dynamic.lib.b.a.a.c1
                            @Override // java.lang.Runnable
                            public final void run() {
                                n1.this.n(optString, view);
                            }
                        };
                        if (com.jd.dynamic.lib.utils.m.D()) {
                            runnable.run();
                            return;
                        } else {
                            AndroidSchedulers.mainThread().createWorker().schedule(new com.jd.dynamic.lib.b.a.a.a(runnable));
                            return;
                        }
                    case '\b':
                        if (view instanceof TextView) {
                            com.jd.dynamic.lib.utils.m.v(optString, (TextView) view, null);
                            if ((view instanceof EditText) && !TextUtils.isEmpty(optString)) {
                                ((EditText) view).setSelection(optString.length());
                            }
                        }
                        if (!(view.getParent() instanceof YogaLayout)) {
                            return;
                        }
                        ((YogaLayout) view.getParent()).invalidate(view);
                        return;
                    default:
                        f(view, optString2, optString);
                        if (!(view.getParent() instanceof YogaLayout)) {
                            return;
                        }
                        ((YogaLayout) view.getParent()).invalidate(view);
                        return;
                }
            } catch (Exception unused) {
            }
        }
    }

    private void h(TextView textView, SpannableStringBuilder spannableStringBuilder, RichTextEntity richTextEntity, int i2, int i3, List<c> list) {
        IUniConfig uniConfig;
        if (TextUtils.isEmpty(richTextEntity.iconCode) || (uniConfig = DynamicSdk.getEngine().getUniConfig()) == null) {
            return;
        }
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(richTextEntity.iconText)) {
            bitmap = uniConfig.getBitmap(richTextEntity.iconCode);
        } else {
            TextView textViewOrNull = uniConfig.getTextViewOrNull(richTextEntity.iconCode, richTextEntity.iconText);
            if (textViewOrNull != null) {
                textViewOrNull.setTextSize(com.jd.dynamic.lib.utils.m.a(richTextEntity.fontSize, 14.0f));
                textViewOrNull.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                textViewOrNull.layout(0, 0, textViewOrNull.getMeasuredWidth(), textViewOrNull.getMeasuredHeight());
                textViewOrNull.buildDrawingCache();
                bitmap = Bitmap.createBitmap(textViewOrNull.getDrawingCache());
            }
        }
        if (bitmap != null) {
            if (!spannableStringBuilder.toString().startsWith("1 ")) {
                spannableStringBuilder.insert(i2, "1 ");
            }
            if (!TextUtils.isEmpty(richTextEntity.iconSpace)) {
                spannableStringBuilder.insert(i3, richTextEntity.iconSpace);
            }
            b bVar = new b(textView.getContext(), bitmap, 4);
            bVar.a(false);
            if (list != null) {
                list.add(new c(this, bVar, i2, i3));
            } else {
                spannableStringBuilder.setSpan(bVar, i2, i3, 17);
            }
        }
    }

    private void i(TextView textView, SpannableStringBuilder spannableStringBuilder, RichTextEntity richTextEntity, int i2, int i3, boolean z, List<c> list) {
        IUniConfig uniConfig;
        Object styleSpan;
        c cVar;
        float f2;
        c cVar2;
        if (!TextUtils.isEmpty(richTextEntity.fontSize)) {
            try {
                f2 = Float.parseFloat(richTextEntity.fontSize);
            } catch (Exception unused) {
                f2 = -1.0f;
            }
            if (f2 != -1.0f) {
                if (z) {
                    float textSize = textView.getTextSize();
                    int i4 = R.id.dynamic_text_size_origin;
                    if (textView.getTag(i4) != null) {
                        textSize = ((Float) textView.getTag(i4)).floatValue();
                    }
                    float f3 = f2 / textSize;
                    com.jd.dynamic.lib.utils.t.e("ViewFunction", "handleAttributes", "RelativeSizeSpan zoomRatio = " + f3);
                    Object relativeSizeSpan = new RelativeSizeSpan(f3);
                    if (list != null) {
                        cVar2 = new c(this, relativeSizeSpan, i2, i3);
                        list.add(cVar2);
                    } else {
                        spannableStringBuilder.setSpan(relativeSizeSpan, i2, i3, 33);
                    }
                } else {
                    Object absoluteSizeSpan = new AbsoluteSizeSpan((int) f2, true);
                    if (list != null) {
                        cVar2 = new c(this, absoluteSizeSpan, i2, i3);
                        list.add(cVar2);
                    } else {
                        spannableStringBuilder.setSpan(absoluteSizeSpan, i2, i3, 18);
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(richTextEntity.fontName)) {
            if (TextUtils.equals(richTextEntity.fontName, "SYSTEM")) {
                styleSpan = new StyleSpan(0);
                if (list != null) {
                    cVar = new c(this, styleSpan, i2, i3);
                    list.add(cVar);
                }
                spannableStringBuilder.setSpan(styleSpan, i2, i3, 18);
            } else if (TextUtils.equals(richTextEntity.fontName, "normal")) {
                styleSpan = new StyleSpan(0);
                if (list != null) {
                    cVar = new c(this, styleSpan, i2, i3);
                    list.add(cVar);
                }
                spannableStringBuilder.setSpan(styleSpan, i2, i3, 18);
            } else if (TextUtils.equals(richTextEntity.fontName, "bold")) {
                styleSpan = new StyleSpan(1);
                if (list != null) {
                    cVar = new c(this, styleSpan, i2, i3);
                    list.add(cVar);
                }
                spannableStringBuilder.setSpan(styleSpan, i2, i3, 18);
            } else {
                Typeface a2 = com.jd.dynamic.lib.utils.i.a(this.mEngine.getActivity(), richTextEntity.fontName);
                if (a2 != null) {
                    com.jd.dynamic.lib.utils.i iVar = new com.jd.dynamic.lib.utils.i(a2);
                    if (list != null) {
                        list.add(new c(this, iVar, i2, i3));
                    } else {
                        spannableStringBuilder.setSpan(new com.jd.dynamic.lib.utils.i(a2), i2, i3, 18);
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(richTextEntity.textColor)) {
            float f4 = Float.NaN;
            try {
                f4 = Color.parseColor(richTextEntity.textColor);
            } catch (Exception unused2) {
            }
            if (!Float.isNaN(f4)) {
                Object foregroundColorSpan = new ForegroundColorSpan((int) f4);
                if (list != null) {
                    list.add(new c(this, foregroundColorSpan, i2, i3));
                } else {
                    spannableStringBuilder.setSpan(foregroundColorSpan, i2, i3, 18);
                }
            }
        }
        if (TextUtils.equals(richTextEntity.strikethrough, "1")) {
            Object strikethroughSpan = new StrikethroughSpan();
            if (list != null) {
                list.add(new c(this, strikethroughSpan, i2, i3));
            } else {
                spannableStringBuilder.setSpan(strikethroughSpan, i2, i3, 18);
            }
        }
        if (TextUtils.isEmpty(richTextEntity.iconCode) || (uniConfig = DynamicSdk.getEngine().getUniConfig()) == null) {
            return;
        }
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(richTextEntity.iconText)) {
            bitmap = uniConfig.getBitmap(richTextEntity.iconCode);
        } else {
            TextView textViewOrNull = uniConfig.getTextViewOrNull(richTextEntity.iconCode, richTextEntity.iconText);
            if (textViewOrNull != null) {
                textViewOrNull.setTextSize(com.jd.dynamic.lib.utils.m.a(richTextEntity.fontSize, 14.0f));
                textViewOrNull.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                textViewOrNull.layout(0, 0, textViewOrNull.getMeasuredWidth(), textViewOrNull.getMeasuredHeight());
                textViewOrNull.buildDrawingCache();
                bitmap = Bitmap.createBitmap(textViewOrNull.getDrawingCache());
            }
        }
        if (bitmap != null) {
            if (!spannableStringBuilder.toString().startsWith("1 ")) {
                spannableStringBuilder.insert(0, "1 ");
            }
            b bVar = new b(textView.getContext(), bitmap, 4);
            bVar.a(false);
            if (list != null) {
                list.add(new c(this, bVar, 0, 1));
            } else {
                spannableStringBuilder.setSpan(bVar, 0, 1, 17);
            }
        }
    }

    private void j(TextView textView, SpannableStringBuilder spannableStringBuilder, JSONObject jSONObject, boolean z, List<c> list) {
        RichTextEntity richTextEntity;
        int i2;
        int i3;
        if (jSONObject == null || (richTextEntity = (RichTextEntity) new Gson().fromJson(jSONObject.toString(), RichTextEntity.class)) == null) {
            return;
        }
        int i4 = -1;
        if (richTextEntity.iconPosition != -1 && !TextUtils.isEmpty(richTextEntity.iconCode)) {
            int i5 = richTextEntity.iconPosition;
            h(textView, spannableStringBuilder, richTextEntity, i5, i5 + 1, list);
            return;
        }
        if (!TextUtils.isEmpty(richTextEntity.rangeLoc) || !TextUtils.isEmpty(richTextEntity.rangeLen)) {
            try {
                i2 = Integer.parseInt(richTextEntity.rangeLoc);
            } catch (Exception unused) {
                i2 = -1;
            }
            try {
                i4 = Integer.parseInt(richTextEntity.rangeLen);
            } catch (Exception unused2) {
            }
            if (i2 < 0 || i4 <= 0 || (i3 = i2 + i4) > spannableStringBuilder.length()) {
                return;
            }
        } else if (!TextUtils.isEmpty(richTextEntity.regularExp)) {
            Matcher matcher = Pattern.compile(richTextEntity.regularExp).matcher(spannableStringBuilder);
            while (matcher.find()) {
                i(textView, spannableStringBuilder, richTextEntity, matcher.start(), matcher.end(), z, list);
            }
            return;
        } else {
            i2 = 0;
            i3 = spannableStringBuilder.length();
        }
        i(textView, spannableStringBuilder, richTextEntity, i2, i3, z, list);
    }

    private void k(TextView textView, JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        ArrayList arrayList;
        n1 n1Var;
        TextView textView2;
        SpannableStringBuilder spannableStringBuilder;
        ArrayList arrayList2;
        n1 n1Var2;
        TextView textView3;
        SpannableStringBuilder spannableStringBuilder2;
        ArrayList arrayList3 = new ArrayList();
        if (TextUtils.isEmpty(textView.getText()) || jSONObject == null || (optJSONObject = jSONObject.optJSONObject("value")) == null || (optJSONArray = optJSONObject.optJSONArray("attributes")) == null || optJSONArray.length() < 1) {
            return;
        }
        if (com.jd.dynamic.b.a.b.o().s("attr_text_line_space")) {
            try {
                if (optJSONObject.has("lineSpace")) {
                    textView.setLineSpacing(DPIUtil.dip2px(Float.parseFloat(optJSONObject.optString("lineSpace"))), 1.0f);
                }
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "attributes parse lineSpace catch error", com.jd.dynamic.lib.utils.m.j(this.mEngine), com.jd.dynamic.lib.utils.m.O(this.mEngine), e2);
            }
        }
        CharSequence text = textView.getText();
        Object tag = textView.getTag(R.id.dynamic_use_auto_size);
        SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(text);
        spannableStringBuilder3.clearSpans();
        int i2 = 0;
        if (tag != null) {
            while (i2 < optJSONArray.length()) {
                Object tag2 = textView.getTag(R.id.dynamic_text_view_char);
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                if (tag2 != null) {
                    n1Var2 = this;
                    textView3 = textView;
                    spannableStringBuilder2 = spannableStringBuilder3;
                    arrayList2 = arrayList3;
                } else {
                    arrayList2 = null;
                    n1Var2 = this;
                    textView3 = textView;
                    spannableStringBuilder2 = spannableStringBuilder3;
                }
                n1Var2.j(textView3, spannableStringBuilder2, optJSONObject2, true, arrayList2);
                i2++;
            }
        } else {
            while (i2 < optJSONArray.length()) {
                Object tag3 = textView.getTag(R.id.dynamic_text_view_char);
                JSONObject optJSONObject3 = optJSONArray.optJSONObject(i2);
                if (tag3 != null) {
                    n1Var = this;
                    textView2 = textView;
                    spannableStringBuilder = spannableStringBuilder3;
                    arrayList = arrayList3;
                } else {
                    arrayList = null;
                    n1Var = this;
                    textView2 = textView;
                    spannableStringBuilder = spannableStringBuilder3;
                }
                n1Var.j(textView2, spannableStringBuilder, optJSONObject3, false, arrayList);
                i2++;
            }
        }
        com.jd.dynamic.lib.utils.m.v(spannableStringBuilder3, textView, arrayList3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(String str, View view) {
        str.hashCode();
        int i2 = 4;
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    c2 = 0;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    c2 = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c2 = 2;
                    break;
                }
                break;
            case 47602:
                if (str.equals("0.0")) {
                    c2 = 3;
                    break;
                }
                break;
            case 48563:
                if (str.equals("1.0")) {
                    c2 = 4;
                    break;
                }
                break;
            case 49524:
                if (str.equals("2.0")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 3:
                break;
            case 1:
            case 4:
            default:
                i2 = 0;
                break;
            case 2:
            case 5:
                i2 = 8;
                break;
        }
        if (com.jd.dynamic.b.a.b.o().y() && i2 == 0) {
            int i3 = R.id.dynamic_view_need_rebind;
            if (view.getTag(i3) != null) {
                Object tag = view.getTag(R.id.dynamic_item_data);
                if (tag instanceof JSONObject) {
                    this.mEngine.newRefreshItemView((JSONObject) tag, view.getId(), view);
                    com.jd.dynamic.lib.utils.t.e("ViewRefresh", "reBind item", Integer.valueOf(view.getId()));
                } else {
                    DynamicTemplateEngine dynamicTemplateEngine = this.mEngine;
                    dynamicTemplateEngine.newRefreshView(dynamicTemplateEngine.currentData, view.getId(), view);
                    com.jd.dynamic.lib.utils.t.e("ViewRefresh", "reBind View", Integer.valueOf(view.getId()));
                }
                view.setTag(i3, null);
            }
        }
        com.jd.dynamic.lib.utils.m.r(view, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
        if ((r3 instanceof org.json.JSONObject) != false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b A[Catch: Exception -> 0x006e, TryCatch #0 {Exception -> 0x006e, blocks: (B:18:0x0047, B:20:0x004b, B:21:0x004e, B:23:0x0059, B:24:0x005d), top: B:29:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0059 A[Catch: Exception -> 0x006e, TryCatch #0 {Exception -> 0x006e, blocks: (B:18:0x0047, B:20:0x004b, B:21:0x004e, B:23:0x0059, B:24:0x005d), top: B:29:0x0047 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d A[Catch: Exception -> 0x006e, TRY_LEAVE, TryCatch #0 {Exception -> 0x006e, blocks: (B:18:0x0047, B:20:0x004b, B:21:0x004e, B:23:0x0059, B:24:0x005d), top: B:29:0x0047 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void o(org.json.JSONObject r3, final int r4) {
        /*
            r2 = this;
            java.lang.String r0 = "fromKey"
            java.lang.String r3 = r3.optString(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            r1 = 0
            if (r0 != 0) goto L46
            com.jd.dynamic.b.a.b r0 = com.jd.dynamic.b.a.b.o()
            boolean r0 = r0.e()
            if (r0 == 0) goto L22
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r2.mEngine
            com.jd.dynamic.base.CachePool r0 = r0.getCachePool()
            java.lang.Object r3 = r0.getDataObj(r3)
            goto L2c
        L22:
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r2.mEngine
            com.jd.dynamic.base.CachePool r0 = r0.getCachePool()
            java.lang.Object r3 = r0.getData(r3)
        L2c:
            boolean r0 = r3 instanceof java.lang.String
            if (r0 == 0) goto L41
            org.json.JSONTokener r0 = new org.json.JSONTokener     // Catch: org.json.JSONException -> L3c
            java.lang.String r3 = (java.lang.String) r3     // Catch: org.json.JSONException -> L3c
            r0.<init>(r3)     // Catch: org.json.JSONException -> L3c
            java.lang.Object r3 = r0.nextValue()     // Catch: org.json.JSONException -> L3c
            goto L47
        L3c:
            r3 = move-exception
            r3.printStackTrace()
            goto L46
        L41:
            boolean r0 = r3 instanceof org.json.JSONObject
            if (r0 == 0) goto L46
            goto L47
        L46:
            r3 = r1
        L47:
            boolean r0 = r3 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> L6e
            if (r0 == 0) goto L4e
            r1 = r3
            org.json.JSONObject r1 = (org.json.JSONObject) r1     // Catch: java.lang.Exception -> L6e
        L4e:
            com.jd.dynamic.lib.b.a.a.z0 r3 = new com.jd.dynamic.lib.b.a.a.z0     // Catch: java.lang.Exception -> L6e
            r3.<init>()     // Catch: java.lang.Exception -> L6e
            boolean r4 = com.jd.dynamic.lib.utils.m.D()     // Catch: java.lang.Exception -> L6e
            if (r4 == 0) goto L5d
            r3.run()     // Catch: java.lang.Exception -> L6e
            goto L72
        L5d:
            rx.Scheduler r4 = rx.android.schedulers.AndroidSchedulers.mainThread()     // Catch: java.lang.Exception -> L6e
            rx.Scheduler$Worker r4 = r4.createWorker()     // Catch: java.lang.Exception -> L6e
            com.jd.dynamic.lib.b.a.a.a r0 = new com.jd.dynamic.lib.b.a.a.a     // Catch: java.lang.Exception -> L6e
            r0.<init>(r3)     // Catch: java.lang.Exception -> L6e
            r4.schedule(r0)     // Catch: java.lang.Exception -> L6e
            goto L72
        L6e:
            r3 = move-exception
            r3.printStackTrace()
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.b.a.a.n1.o(org.json.JSONObject, int):void");
    }

    private void q(JSONObject jSONObject) {
        final View c2;
        View view = this.mTargetView;
        if (view == null || (c2 = com.jd.dynamic.lib.utils.m.c(view)) == null) {
            return;
        }
        View pendingView = getPendingView(com.jd.dynamic.lib.dynamic.parser.h.a(this.mEngine, String.valueOf(jSONObject.optInt("layoutId"))), c2);
        if (pendingView != null) {
            g(pendingView, jSONObject);
            if (c2.getParent() instanceof RecyclerView) {
                p(c2);
            } else if (c2.getParent() == null) {
                c2.post(new Runnable() { // from class: com.jd.dynamic.lib.b.a.a.d1
                    @Override // java.lang.Runnable
                    public final void run() {
                        n1.this.p(c2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(JSONObject jSONObject, int i2) {
        this.mEngine.newRefreshView(jSONObject, i2, this.mTargetView);
    }

    private Object s(JSONObject jSONObject) {
        View pendingView = getPendingView(v(jSONObject));
        Object obj = null;
        if (pendingView != null) {
            String optString = jSONObject.optString("key");
            AttrMethod method = CachePool.getMethod(optString);
            if (method != null) {
                try {
                    Method method2 = pendingView.getClass().getMethod(method.methodName, method.parameterTypes);
                    if (method2 != null) {
                        obj = method2.invoke(pendingView, method.args);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (DYConstants.DY_TEXT_COLOR.equals(optString) && (obj instanceof Integer)) {
                obj = String.format("#%08X", Integer.valueOf(((Integer) obj).intValue() & ViewCompat.MEASURED_SIZE_MASK));
            }
            if ("text".equals(optString) && pendingView.getTag(R.id.dynamic_text_view_char) != null && (obj instanceof CharSequence)) {
                try {
                    obj = ((CharSequence) obj).toString().replaceAll(DYConstants.DY_CHAR_SING, "");
                } catch (Exception unused) {
                }
            }
        }
        return (obj == null || ((obj instanceof CharSequence) && TextUtils.isEmpty((CharSequence) obj))) ? jSONObject.opt("defaultValue") : obj;
    }

    private void t(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("layoutId");
        if (optJSONArray == null) {
            o(jSONObject, v(jSONObject));
            return;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            int optInt = optJSONArray.optInt(i2, -1);
            if (-1 != optInt) {
                o(jSONObject, optInt);
            } else {
                o(jSONObject, com.jd.dynamic.lib.dynamic.parser.h.a(this.mEngine, optJSONArray.optString(i2)));
            }
        }
    }

    private void u(JSONObject jSONObject) {
        g(getPendingView(v(jSONObject)), jSONObject);
    }

    private int v(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("viewId", -1);
        if (-1 == optInt) {
            return com.jd.dynamic.lib.dynamic.parser.h.a(this.mEngine, String.valueOf(jSONObject.optInt("layoutId")));
        }
        return optInt;
    }

    public Object b(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject, com.jd.dynamic.a.f fVar) {
        Object remove = jSONObject.remove("fun");
        String str = remove instanceof String ? (String) remove : null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        if (str.equals(DYConstants.SIZE_FOR_IMAGE)) {
            return d(jSONObject, fVar);
        }
        return null;
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine == null) {
            return null;
        }
        Object remove = jSONObject.remove("fun");
        String str = remove instanceof String ? (String) remove : "";
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -2088324902:
                if (str.equals("reBindData")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1899989709:
                if (str.equals(DYConstants.SIZE_FOR_IMAGE)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1347771489:
                if (str.equals("changeItemViewAttributes")) {
                    c2 = 2;
                    break;
                }
                break;
            case -199516587:
                if (str.equals(DYConstants.SIZE_FOR_TEXT)) {
                    c2 = 3;
                    break;
                }
                break;
            case 3480550:
                if (str.equals("getAttribute")) {
                    c2 = 4;
                    break;
                }
                break;
            case 865451303:
                if (str.equals("changeAttributes")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                t(jSONObject);
                break;
            case 1:
                return d(jSONObject, null);
            case 2:
                q(jSONObject);
                break;
            case 3:
                return c(jSONObject);
            case 4:
                return s(jSONObject);
            case 5:
                u(jSONObject);
                break;
            default:
                return null;
        }
        return null;
    }
}
