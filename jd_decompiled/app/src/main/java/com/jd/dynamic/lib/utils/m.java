package com.jd.dynamic.lib.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IImageLoader;
import com.jd.dynamic.base.interfaces.IUniConfig;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.b.a.a.n1;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jd.dynamic.lib.views.CollectionView;
import com.jd.dynamic.lib.views.listeners.IMeasureListener;
import com.jd.dynamic.yoga.android.YogaLayout;
import com.jd.lib.un.utils.UnTimeUtils;
import com.jingdong.common.ad.AdUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/* loaded from: classes13.dex */
public class m {
    private static final List<String> a;
    private static Map<String, Integer> b;

    /* loaded from: classes13.dex */
    public static class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ CollectionView f2273g;

        a(CollectionView collectionView) {
            this.f2273g = collectionView;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((YogaLayout) this.f2273g.getParent()).invalidate(this.f2273g);
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements IImageLoader.ImageRequestListener<Bitmap> {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ IMeasureListener b;

        b(JSONObject jSONObject, IMeasureListener iMeasureListener) {
            this.a = jSONObject;
            this.b = iMeasureListener;
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        /* renamed from: a */
        public void onSuccess(Bitmap bitmap) {
            if (bitmap != null) {
                try {
                    int px2dip = DPIUtil.px2dip(bitmap.getWidth());
                    int px2dip2 = DPIUtil.px2dip(bitmap.getHeight());
                    this.a.put("width", px2dip);
                    this.a.put("height", px2dip2);
                } catch (Exception unused) {
                }
                this.b.onMeasureInfo(this.a);
            }
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onCancel() {
            this.b.onMeasureInfo(this.a);
        }

        @Override // com.jd.dynamic.base.interfaces.IImageLoader.ImageRequestListener
        public void onFailure(Throwable th) {
            this.b.onMeasureInfo(this.a);
        }
    }

    /* loaded from: classes13.dex */
    public static class c {
        public ViewPager2 a;
        public ViewPager b;

        /* renamed from: c */
        public int f2274c;

        public c(ViewPager2 viewPager2, ViewPager viewPager) {
            this.f2274c = -1;
            this.b = viewPager;
            this.a = viewPager2;
            if (viewPager2 != null) {
                this.f2274c = 1;
            }
            if (viewPager != null) {
                this.f2274c = 0;
            }
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add("marginLeft");
        arrayList.add("marginRight");
        arrayList.add("marginTop");
        arrayList.add("marginBottom");
        arrayList.add("margin");
        arrayList.add("marginStart");
        arrayList.add("marginEnd");
        arrayList.add("marginHorizontal");
        arrayList.add("marginVertical");
        arrayList.add(DYConstants.DY_MARGIN_ALL);
        arrayList.add("paddingLeft");
        arrayList.add("paddingRight");
        arrayList.add("paddingBottom");
        arrayList.add("paddingTop");
        arrayList.add("paddingStart");
        arrayList.add("paddingEnd");
        arrayList.add("paddingHorizontal");
        arrayList.add("paddingVertical");
        arrayList.add(DYConstants.DY_PADDING_ALL);
        arrayList.add("padding");
        arrayList.add("height");
        arrayList.add("width");
        arrayList.add(ViewProps.MIN_WIDTH);
        arrayList.add(ViewProps.MIN_HEIGHT);
        arrayList.add("maxWidth");
        arrayList.add("maxHeight");
        arrayList.add(ViewProps.FLEX_BASIS);
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put("UnknownHostException", Integer.valueOf((int) R2.attr.layout_constraintCircleAngle));
        b.put(AdUtils.TIMEOUT_EXCEPTION, Integer.valueOf((int) R2.attr.layout_constraintCircleRadius));
        b.put("ConnectException", Integer.valueOf((int) R2.attr.layout_constraintDimensionRatio));
        b.put("IOException", Integer.valueOf((int) R2.attr.layout_constraintEnd_toEndOf));
        b.put("IllegalStateException", Integer.valueOf((int) R2.attr.layout_constraintEnd_toStartOf));
        b.put("SocketException", Integer.valueOf((int) R2.attr.layout_constraintGuide_begin));
        b.put("StreamResetException", Integer.valueOf((int) R2.attr.layout_constraintGuide_end));
        b.put("JDFileDownloadError", Integer.valueOf((int) R2.attr.layout_constraintHeight_default));
    }

    public static void A(HashMap<String, String> hashMap, View view) {
        if (J(hashMap)) {
            String str = hashMap.get("onBind");
            int i2 = R.id.dynamic_lifecycle_on_bind;
            if (view.getTag(i2) == null && !TextUtils.isEmpty(str)) {
                view.setTag(i2, str);
            }
            String str2 = hashMap.get("onLoad");
            int i3 = R.id.dynamic_lifecycle_on_load;
            if (view.getTag(i3) == null && !TextUtils.isEmpty(str2)) {
                view.setTag(i3, str2);
            }
            String str3 = hashMap.get("onRefresh");
            int i4 = R.id.dynamic_lifecycle_on_refresh;
            if (view.getTag(i4) != null || TextUtils.isEmpty(str3)) {
                return;
            }
            view.setTag(i4, str3);
        }
    }

    public static /* synthetic */ void B(HashMap hashMap, Map.Entry entry) {
        hashMap.remove(entry.getKey());
    }

    public static void C(JSONObject jSONObject, int i2, View view, @NonNull final DynamicTemplateEngine dynamicTemplateEngine, boolean z) {
        View d;
        int i3;
        if (jSONObject != null && !z) {
            dynamicTemplateEngine.currentData = jSONObject;
        }
        boolean z2 = false;
        t.g("refreshView", "layoutId = " + i2);
        if (dynamicTemplateEngine.getRootContainer() != null && dynamicTemplateEngine.getUnbindMap() != null) {
            boolean z3 = false;
            for (Map.Entry<ViewNode, HashMap<String, String>> entry : dynamicTemplateEngine.getUnbindMap().entrySet()) {
                HashMap<String, String> value = entry.getValue();
                final ViewNode key = entry.getKey();
                boolean z4 = key == null || key.getAttributes() == null || (i3 = key.viewId) == 0 || i3 != i2;
                if (!z) {
                    z4 = z4 || key.unNeedInitBind;
                }
                if (!z4) {
                    try {
                        final View d2 = d(dynamicTemplateEngine, i2, view);
                        for (Map.Entry<String, String> entry2 : value.entrySet()) {
                            dynamicTemplateEngine.newBindDataWithEL(key, jSONObject, entry2.getValue(), entry2.getKey());
                        }
                        if (d2 != null) {
                            try {
                                boolean y = com.jd.dynamic.b.a.b.o().y();
                                if (d2.getTag(R.id.dynamic_view_need_rebind) != null && y) {
                                    key.getELAttributes().remove("visibility");
                                }
                                if (dynamicTemplateEngine.getActivity() != null) {
                                    dynamicTemplateEngine.getActivity().runOnUiThread(new Runnable() { // from class: com.jd.dynamic.lib.utils.c
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            m.t(dynamicTemplateEngine, key, d2);
                                        }
                                    });
                                }
                                z3 = true;
                            } catch (Exception e2) {
                                e = e2;
                                z3 = true;
                                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine newRefreshView2 error", j(dynamicTemplateEngine), O(dynamicTemplateEngine), e);
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                }
            }
            z2 = z3;
        }
        if (z2 || (d = d(dynamicTemplateEngine, i2, view)) == null || !(d.getParent() instanceof YogaLayout)) {
            return;
        }
        ((YogaLayout) d.getParent()).invalidate(d);
    }

    public static boolean D() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public static boolean E(SparseArrayCompat sparseArrayCompat) {
        return sparseArrayCompat != null && sparseArrayCompat.size() > 0;
    }

    public static boolean F(ResultEntity resultEntity) {
        return resultEntity != null && G(resultEntity.viewNode);
    }

    public static boolean G(ViewNode viewNode) {
        return (viewNode == null || TextUtils.isEmpty(viewNode.getViewName()) || !viewNode.parseSuccess) ? false : true;
    }

    public static boolean H(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean I(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean J(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean K(JSONArray jSONArray) {
        return jSONArray != null && jSONArray.length() > 0;
    }

    public static <T> boolean L(T... tArr) {
        return tArr != null && tArr.length > 0;
    }

    public static View M(View view) {
        if (view.getParent() instanceof View) {
            View view2 = (View) view.getParent();
            return !(view2 instanceof com.jd.dynamic.lib.viewparse.b.carouselView.f) ? ((view2 instanceof CollectionView) && ((CollectionView) view2).getScrollDirection() == 1) ? view2 : M(view2) : view2;
        }
        return null;
    }

    public static File N(File file) {
        t.e("DY_LOG rename template origin is : " + file);
        if (file != null && file.exists() && file.isFile()) {
            File file2 = new File(file.getParentFile(), file.getName().replaceFirst(DYConstants.TEMP_NAME_PREFIX, ""));
            boolean renameTo = file.renameTo(file2);
            t.e("DY_LOG rename is success : " + renameTo + " origin : " + file.getName() + " path: " + file.getAbsolutePath());
            if (renameTo) {
                t.e("DY_LOG target is : " + file2.getName() + " path: " + file2.getAbsolutePath());
                return file2;
            }
        }
        return file;
    }

    public static String O(DynamicTemplateEngine dynamicTemplateEngine) {
        if (dynamicTemplateEngine != null) {
            String systemCode = dynamicTemplateEngine.getSystemCode();
            return (!TextUtils.isEmpty(systemCode) || dynamicTemplateEngine.getTemplate() == null) ? systemCode : dynamicTemplateEngine.getTemplate().systemCode;
        }
        return null;
    }

    public static ArrayList<String> P(JSONArray jSONArray) {
        if (K(jSONArray)) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.optString(i2));
            }
            return arrayList;
        }
        return null;
    }

    public static HashMap<String, String> Q(HashMap<String, String> hashMap) {
        final HashMap<String, String> hashMap2 = new HashMap<>(hashMap);
        Observable.from(hashMap.entrySet()).filter(new Func1() { // from class: com.jd.dynamic.lib.utils.b
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                Boolean g2;
                g2 = m.g((Map.Entry) obj);
                return g2;
            }
        }).forEach(new Action1() { // from class: com.jd.dynamic.lib.utils.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m.B(hashMap2, (Map.Entry) obj);
            }
        });
        return hashMap2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0044 -> B:45:0x0047). Please submit an issue!!! */
    public static void R(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        String str2 = File.separator;
        try {
            if (str.contains(str2)) {
                int lastIndexOf = str.lastIndexOf(str2);
                if (lastIndexOf >= str.length() - 1) {
                    return;
                }
                File file2 = new File(str.substring(0, lastIndexOf));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                file.createNewFile();
            } else {
                file.createNewFile();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static /* synthetic */ void S(Map.Entry entry) {
        String str = (String) entry.getValue();
        if (a.contains((String) entry.getKey()) && str.startsWith("S")) {
            entry.setValue(str.replace("S", ""));
        }
    }

    public static boolean T(long j2) {
        if (com.jd.dynamic.b.a.b.o().m()) {
            r1 = j2 == -1;
            if (r1) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_JS_ADDRESS, "jsContext is Illegal", null, null, new RuntimeException());
            }
        }
        return r1;
    }

    public static int U(String str) {
        if (TextUtils.isEmpty(str)) {
            return R2.attr.layout_constraintGuide_percent;
        }
        for (String str2 : b.keySet()) {
            if (str.contains(str2)) {
                return b.get(str2).intValue();
            }
        }
        return R2.attr.layout_constraintGuide_percent;
    }

    public static View V(View view) {
        if (view.getParent() instanceof View) {
            View view2 = (View) view.getParent();
            return ((view2 instanceof CollectionView) && ((CollectionView) view2).getScrollDirection() == 0) ? view2 : V(view2);
        }
        return null;
    }

    public static boolean W(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "data".equals(str) || "height".equals(str) || "width".equals(str) || str.startsWith("margin") || "tag".equals(str);
    }

    public static float a(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return f2;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    public static int b(String str, boolean z) {
        if (str == null) {
            return z ? 3 : 16;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1383228885:
                if (str.equals("bottom")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals(DYConstants.DY_CENTER)) {
                    c2 = 1;
                    break;
                }
                break;
            case 115029:
                if (str.equals("top")) {
                    c2 = 2;
                    break;
                }
                break;
            case 3317767:
                if (str.equals("left")) {
                    c2 = 3;
                    break;
                }
                break;
            case 108511772:
                if (str.equals("right")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return 80;
            case 1:
                return z ? 1 : 16;
            case 2:
                return 48;
            case 3:
                return 3;
            case 4:
                return 5;
            default:
                return z ? 3 : 16;
        }
    }

    public static View c(View view) {
        if (view == null) {
            return null;
        }
        int i2 = R.id.dynamic_item_type;
        if (view.getTag(i2) != null) {
            return view;
        }
        if (view.getParent() instanceof View) {
            View view2 = (View) view.getParent();
            return view2.getTag(i2) != null ? view2 : c(view2);
        }
        return null;
    }

    public static View d(DynamicTemplateEngine dynamicTemplateEngine, int i2, View view) {
        if (dynamicTemplateEngine == null) {
            return null;
        }
        return (view == null || view.getId() != i2) ? (view == null || view.findViewById(i2) == null) ? e(dynamicTemplateEngine, i2, view, c(view)) : view.findViewById(i2) : view;
    }

    public static View e(DynamicTemplateEngine dynamicTemplateEngine, int i2, View view, View view2) {
        if (dynamicTemplateEngine == null) {
            return null;
        }
        if (view == null || view.getId() != i2) {
            if (view2 == null || view2.findViewById(i2) == null) {
                if (dynamicTemplateEngine.getRootContainer() == null) {
                    return null;
                }
                return dynamicTemplateEngine.getRootContainer().findViewById(i2);
            }
            return view2.findViewById(i2);
        }
        return view;
    }

    public static c f(com.jd.dynamic.lib.viewparse.b.carouselView.f fVar) {
        return new c(fVar.w(), fVar.y());
    }

    public static /* synthetic */ Boolean g(Map.Entry entry) {
        return Boolean.valueOf(DynamicUtils.isEL((String) entry.getValue()) || DynamicUtils.isKnownSymbol((String) entry.getValue()));
    }

    public static Object h(Class cls, String str) {
        if (cls != null) {
            if (cls.isPrimitive() || cls == String.class) {
                if (cls == String.class) {
                    return str;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(Integer.TYPE.getName(), Integer.class);
                hashMap.put(Double.TYPE.getName(), Double.class);
                hashMap.put(Float.TYPE.getName(), Float.class);
                hashMap.put(Long.TYPE.getName(), Long.class);
                hashMap.put(Short.TYPE.getName(), Short.class);
                hashMap.put(Byte.TYPE.getName(), Byte.class);
                hashMap.put(Boolean.TYPE.getName(), Boolean.class);
                hashMap.put(Character.TYPE.getName(), Character.class);
                Class cls2 = (Class) hashMap.get(cls.getName());
                if (cls2 == null) {
                    try {
                        cls.getConstructor(String.class).newInstance(str);
                    } catch (Exception unused) {
                        return null;
                    }
                }
                return cls2.getConstructor(String.class).newInstance(str);
            }
            return null;
        }
        return null;
    }

    @SuppressLint({"DefaultLocale"})
    public static String i(long j2) {
        if (j2 <= 0) {
            return "00:00:00";
        }
        long j3 = (long) UnTimeUtils.HOUR;
        long j4 = j2 / j3;
        long j5 = j2 - (j3 * j4);
        long j6 = 60000;
        long j7 = j5 / j6;
        String[] strArr = {String.format("%1$02d", Long.valueOf(j4)), String.format("%1$02d", Long.valueOf(j7)), String.format("%1$02d", Long.valueOf((j5 - (j6 * j7)) / 1000))};
        return strArr[0] + ":" + strArr[1] + ":" + strArr[2];
    }

    public static String j(DynamicTemplateEngine dynamicTemplateEngine) {
        if (dynamicTemplateEngine != null) {
            String bizField = dynamicTemplateEngine.getBizField();
            if (dynamicTemplateEngine.getTemplate() != null) {
                return dynamicTemplateEngine.getTemplate().bizField + "-" + dynamicTemplateEngine.getTemplate().version;
            }
            return bizField;
        }
        return null;
    }

    public static String k(Template template) {
        return DYConstants.TEMP_NAME_PREFIX + p.b(template.getDownloadUrl(), template.getDownloadFileName());
    }

    @Nullable
    public static Map<String, Object> l(@NonNull DynamicTemplateEngine dynamicTemplateEngine, @NonNull String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            ArrayList<String> arrayList = new ArrayList();
            for (String str3 : split) {
                if (!TextUtils.isEmpty(str3)) {
                    arrayList.add(str3);
                }
            }
            try {
                if (I(arrayList)) {
                    View d = !TextUtils.isEmpty(str2) ? d(dynamicTemplateEngine, com.jd.dynamic.lib.dynamic.parser.h.a(dynamicTemplateEngine, str2), dynamicTemplateEngine.getRootContainer()) : null;
                    if (d == null) {
                        d = dynamicTemplateEngine.getRootContainer();
                    }
                    HashMap hashMap = new HashMap();
                    for (String str4 : arrayList) {
                        if (!TextUtils.isEmpty(dynamicTemplateEngine.getCachePool().getParam(str4))) {
                            hashMap.put(str4, s.b(str4, dynamicTemplateEngine.currentData, dynamicTemplateEngine, d));
                        }
                    }
                    return hashMap;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static JSONObject m(ResultEntity resultEntity, JSONObject jSONObject) {
        return q(resultEntity == null ? "" : resultEntity.zipVersion, jSONObject);
    }

    public static JSONObject n(Template template, JSONObject jSONObject) {
        return q(template == null ? "" : template.pckVersion, jSONObject);
    }

    public static JSONObject o(String str, IMeasureListener iMeasureListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", 0);
            jSONObject.put("height", 0);
        } catch (Exception unused) {
        }
        if (DynamicSdk.getEngine().getImageLoader() == null) {
            return jSONObject;
        }
        File cacheImageFile = DynamicSdk.getEngine().getImageLoader().getCacheImageFile(str);
        if (cacheImageFile != null && cacheImageFile.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(cacheImageFile.getAbsolutePath(), options);
            int px2dip = DPIUtil.px2dip(options.outWidth);
            int px2dip2 = DPIUtil.px2dip(options.outHeight);
            try {
                jSONObject.put("width", px2dip);
                jSONObject.put("height", px2dip2);
            } catch (Exception unused2) {
            }
            if (iMeasureListener == null) {
                return jSONObject;
            }
            iMeasureListener.onMeasureInfo(jSONObject);
        } else if (iMeasureListener != null) {
            x(str, iMeasureListener, jSONObject);
        }
        return jSONObject;
    }

    public static JSONObject p(String str, String str2) {
        Bitmap bitmap;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", 0);
            jSONObject.put("height", 0);
        } catch (Exception unused) {
        }
        IUniConfig uniConfig = DynamicSdk.getEngine().getUniConfig();
        if (uniConfig == null) {
            return jSONObject;
        }
        if (TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str2) || (bitmap = uniConfig.getBitmap(str2)) == null) {
                return jSONObject;
            }
            int px2dip = DPIUtil.px2dip(bitmap.getWidth());
            int px2dip2 = DPIUtil.px2dip(bitmap.getHeight());
            jSONObject.put("width", px2dip);
            jSONObject.put("height", px2dip2);
            return jSONObject;
        }
        TextView textViewOrNull = uniConfig.getTextViewOrNull(str2, str);
        if (textViewOrNull == null) {
            return jSONObject;
        }
        textViewOrNull.measure(0, 0);
        int px2dip3 = DPIUtil.px2dip(textViewOrNull.getMeasuredWidth());
        int px2dip4 = DPIUtil.px2dip(textViewOrNull.getMeasuredHeight());
        jSONObject.put("width", px2dip3);
        jSONObject.put("height", px2dip4);
        return jSONObject;
    }

    public static JSONObject q(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (str == null) {
            str = "";
        }
        try {
            jSONObject.put("pckVersion", str);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void r(View view, int i2) {
        if (view.getParent() instanceof YogaLayout) {
            ((YogaLayout) view.getParent()).setChildVisibility(view, i2);
        } else {
            view.setVisibility(i2);
        }
        Object tag = view.getTag(R.id.dynamic_collection_view);
        if (tag instanceof CollectionView) {
            CollectionView collectionView = (CollectionView) tag;
            if (collectionView.getRecyclerView() != null) {
                view.post(new a(collectionView));
            }
        }
    }

    public static void s(View view, DynamicTemplateEngine dynamicTemplateEngine) {
        if (view == null || dynamicTemplateEngine == null || !dynamicTemplateEngine.isAttached) {
            return;
        }
        try {
            int i2 = R.id.dynamic_lifecycle_on_bind;
            if (view.getTag(i2) instanceof String) {
                String str = (String) view.getTag(i2);
                if (!TextUtils.isEmpty(str)) {
                    Iterator<String> it = s.i(str).iterator();
                    while (it.hasNext()) {
                        s.b(it.next(), view, dynamicTemplateEngine, view);
                    }
                }
            }
            int i3 = R.id.dynamic_lifecycle_on_refresh;
            if (view.getTag(i3) instanceof String) {
                String str2 = (String) view.getTag(i3);
                if (!TextUtils.isEmpty(str2)) {
                    Iterator<String> it2 = s.i(str2).iterator();
                    while (it2.hasNext()) {
                        s.b(it2.next(), view, dynamicTemplateEngine, view);
                    }
                }
            }
            if (view.getTag(R.id.dynamic_lifecycle_on_load_exec) == null) {
                int i4 = R.id.dynamic_lifecycle_on_load;
                if (view.getTag(i4) instanceof String) {
                    String str3 = (String) view.getTag(i4);
                    if (!TextUtils.isEmpty(str3)) {
                        Iterator<String> it3 = s.i(str3).iterator();
                        while (it3.hasNext()) {
                            s.b(it3.next(), view, dynamicTemplateEngine, view);
                        }
                    }
                    view.setTag(R.id.dynamic_lifecycle_on_load_exec, com.jd.dynamic.b.c.a.b);
                }
            }
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "DynamicTemplateEngine handleLifecycleEvents error", j(dynamicTemplateEngine), O(dynamicTemplateEngine), e2);
        }
    }

    public static /* synthetic */ void t(DynamicTemplateEngine dynamicTemplateEngine, ViewNode viewNode, View view) {
        com.jd.dynamic.lib.viewparse.c.v vVar = new com.jd.dynamic.lib.viewparse.c.v();
        vVar.b(dynamicTemplateEngine);
        vVar.a(viewNode.getELAttributes(), view);
    }

    public static void u(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                u(file2);
            }
        }
        file.delete();
    }

    public static void v(CharSequence charSequence, TextView textView, List<n1.c> list) {
        if (TextUtils.isEmpty(charSequence) || textView.getTag(R.id.dynamic_text_view_char) == null) {
            textView.setText(charSequence);
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            spannableStringBuilder.append(charSequence.charAt(i2)).append((CharSequence) DYConstants.DY_CHAR_SING);
        }
        if (list != null && list.size() > 0) {
            for (n1.c cVar : list) {
                spannableStringBuilder.setSpan(cVar.a, cVar.b * 2, cVar.f2178c * 2, 17);
            }
        }
        textView.setText(spannableStringBuilder);
    }

    public static void w(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (D()) {
            runnable.run();
        } else if (BaseFrameLayout.getMyHandler() != null) {
            BaseFrameLayout.getMyHandler().post(runnable);
        }
    }

    private static void x(String str, @NonNull IMeasureListener iMeasureListener, JSONObject jSONObject) {
        DynamicSdk.getEngine().getImageLoader().loadImage(str, new b(jSONObject, iMeasureListener));
    }

    public static /* synthetic */ void y(Throwable th) {
    }

    public static void z(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        Observable.from(hashMap.entrySet()).forEach(new Action1() { // from class: com.jd.dynamic.lib.utils.e
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m.S((Map.Entry) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.utils.d
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                m.y((Throwable) obj);
            }
        });
    }
}
