package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes4.dex */
public class JsonViewLayout extends ScrollView {
    public static float s = 18.0f;

    /* renamed from: g */
    private int f11056g;

    /* renamed from: h */
    private int f11057h;

    /* renamed from: i */
    private int f11058i;

    /* renamed from: j */
    private int f11059j;

    /* renamed from: k */
    private int f11060k;

    /* renamed from: l */
    private int f11061l;

    /* renamed from: m */
    private Context f11062m;

    /* renamed from: n */
    private JSONObject f11063n;
    private JSONArray o;
    private LinearLayout p;
    int q;
    float r;

    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g */
        private Object f11064g;

        /* renamed from: h */
        private JsonView f11065h;

        /* renamed from: i */
        private int f11066i;

        /* renamed from: j */
        private boolean f11067j = true;

        /* renamed from: k */
        private boolean f11068k;

        public a(Object obj, JsonView jsonView, int i2) {
            JsonViewLayout.this = r1;
            this.f11064g = obj;
            this.f11065h = jsonView;
            this.f11066i = i2;
            this.f11068k = obj instanceof JSONArray;
        }

        public Object a() {
            return this.f11064g;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Object opt;
            Object tag = view.getTag();
            if (tag instanceof Boolean) {
                this.f11067j = ((Boolean) tag).booleanValue();
                view.setTag(null);
            }
            if (this.f11065h.getChildCount() == 1) {
                JSONArray names = this.f11068k ? (JSONArray) this.f11064g : ((JSONObject) this.f11064g).names();
                if (names != null) {
                    this.f11067j = false;
                    if (!this.f11068k && names.length() == 1 && "nameValuePairs".equals(names.opt(0).toString()) && (opt = ((JSONObject) this.f11064g).opt("nameValuePairs")) != null) {
                        this.f11064g = opt;
                        boolean z = opt instanceof JSONArray;
                        this.f11068k = z;
                        names = z ? (JSONArray) opt : ((JSONObject) opt).names();
                    }
                    for (int i2 = 0; names != null && i2 < names.length(); i2++) {
                        JsonView jsonView = new JsonView(this.f11065h.getContext());
                        Object opt2 = names.opt(i2);
                        if (!this.f11068k) {
                            String str = (String) opt2;
                            JsonViewLayout.this.h(str, ((JSONObject) this.f11064g).opt(str), jsonView, this.f11066i);
                        } else {
                            String valueOf = String.valueOf(i2);
                            if (opt2 instanceof JSONObject) {
                                JSONObject jSONObject = (JSONObject) opt2;
                                String optString = jSONObject.optString("type");
                                String optString2 = jSONObject.optString("floorId");
                                if (!TextUtils.isEmpty(optString)) {
                                    valueOf = valueOf.concat(" : ").concat(optString);
                                }
                                if (!TextUtils.isEmpty(optString2)) {
                                    valueOf = valueOf.concat(CartConstant.KEY_YB_INFO_LINK).concat(optString2);
                                }
                            }
                            JsonViewLayout.this.h(valueOf, opt2, jsonView, this.f11066i);
                        }
                        this.f11065h.addViewNoInvalidate(jsonView);
                    }
                } else {
                    this.f11067j = !this.f11067j;
                }
                this.f11065h.showIcon(this.f11067j);
                this.f11065h.requestLayout();
                this.f11065h.invalidate();
                return;
            }
            boolean z2 = !this.f11067j;
            this.f11067j = z2;
            this.f11065h.showIcon(z2);
            for (int i3 = 1; i3 < this.f11065h.getChildCount(); i3++) {
                this.f11065h.getChildAt(i3).setVisibility(!this.f11067j ? 0 : 8);
            }
        }
    }

    public JsonViewLayout(Context context) {
        super(context);
        this.f11056g = -10377423;
        this.f11057h = -13421773;
        this.f11058i = -1151165;
        this.f11059j = -3976202;
        this.f11060k = -1;
        this.f11061l = -12543801;
        i(context);
    }

    private boolean c() {
        return (this.f11063n == null && this.o == null) ? false : true;
    }

    private void d(ViewGroup viewGroup, boolean z) {
        boolean z2 = viewGroup instanceof JsonView;
        if (z2) {
            k((JsonView) viewGroup, z);
        }
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (z2) {
                k((JsonView) viewGroup, z);
            }
            if (childAt instanceof ViewGroup) {
                d((ViewGroup) childAt, z);
            }
        }
    }

    private void e() {
        JsonView jsonView = new JsonView(this.f11062m);
        jsonView.showIcon(true);
        jsonView.hideValue();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "JSON");
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f11057h), 0, spannableStringBuilder.length(), 33);
        jsonView.showKey(spannableStringBuilder);
        Object obj = this.f11063n;
        if (obj == null) {
            obj = this.o;
        }
        jsonView.setIconClickListener(new a(obj, jsonView, 0));
        this.p.addView(jsonView);
    }

    private String g(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("      ");
        }
        return sb.toString();
    }

    public void h(String str, Object obj, JsonView jsonView, int i2) {
        int i3;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
        jsonView.hideIcon();
        if (obj instanceof JSONObject) {
            jsonView.showIcon(true);
            int i4 = i2 + 1;
            jsonView.setIconClickListener(new a(obj, jsonView, i4));
            spannableStringBuilder.append((CharSequence) str);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f11057h), 0, spannableStringBuilder.length(), 33);
            jsonView.setCommand(g(i4));
        } else {
            spannableStringBuilder.append((CharSequence) "\"").append((CharSequence) str).append((CharSequence) "\"").append((CharSequence) ":");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f11056g), 0, spannableStringBuilder.length(), 33);
            if (obj instanceof JSONArray) {
                jsonView.showIcon(true);
                jsonView.setIconClickListener(new a(obj, jsonView, i2 + 1));
                jsonView.setCommand(g(i2));
                spannableStringBuilder2.append((CharSequence) ("  " + ((JSONArray) obj).length() + "  "));
                spannableStringBuilder2.setSpan(new ForegroundColorSpan(this.f11060k), 0, spannableStringBuilder2.length(), 33);
                jsonView.showValue(spannableStringBuilder2);
                jsonView.showArrayLength(-2894893);
            } else {
                jsonView.hideIcon();
                spannableStringBuilder2.append((CharSequence) obj.toString());
                if (obj instanceof String) {
                    i3 = this.f11058i;
                } else if (obj instanceof Boolean) {
                    i3 = this.f11061l;
                } else {
                    i3 = obj instanceof Number ? this.f11059j : -4408744;
                }
                spannableStringBuilder2.setSpan(new ForegroundColorSpan(i3), 0, spannableStringBuilder2.length(), 33);
                jsonView.showValue(spannableStringBuilder2);
                jsonView.setCommand(g(i2 + 1));
            }
        }
        jsonView.showKey(spannableStringBuilder);
    }

    private void i(Context context) {
        this.f11062m = context;
        this.p = new LinearLayout(this.f11062m);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.p.setLayoutParams(layoutParams);
        this.p.setOrientation(1);
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this.f11062m);
        horizontalScrollView.setLayoutParams(layoutParams);
        horizontalScrollView.setPadding(24, h.A + 12, 0, 48);
        horizontalScrollView.addView(this.p);
        addView(horizontalScrollView);
    }

    private void j(View view, float f2) {
        if (view instanceof JsonView) {
            JsonView jsonView = (JsonView) view;
            jsonView.setTextSize(f2);
            int childCount = jsonView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                j(jsonView.getChildAt(i2), f2);
            }
        }
    }

    private void k(JsonView jsonView, boolean z) {
        if (jsonView != null) {
            if (z) {
                jsonView.expand();
            } else {
                jsonView.collapse();
            }
        }
    }

    private float n(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private void p(float f2) {
        l(s * ((f2 / 100.0f) + 1.0f));
    }

    public void b(String str) {
        if (!c()) {
            Object obj = null;
            try {
                obj = new JSONTokener(str).nextValue();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (obj != null && (obj instanceof JSONObject)) {
                this.f11063n = (JSONObject) obj;
            } else if (obj != null && (obj instanceof JSONArray)) {
                this.o = (JSONArray) obj;
            } else {
                throw new IllegalArgumentException("jsonStr is illegal.");
            }
            e();
            return;
        }
        throw new IllegalArgumentException("JsonViweLayout can not bind again.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & motionEvent.getActionMasked();
        boolean z = false;
        if (action == 0) {
            this.q = 1;
        } else if (action == 1) {
            this.q = 0;
        } else if (action != 2) {
            if (action == 5) {
                this.r = n(motionEvent);
                this.q++;
            } else if (action == 6) {
                this.q--;
                z = true;
            }
        } else if (this.q >= 2) {
            float n2 = n(motionEvent);
            if (Math.abs(n2 - this.r) > 3.0f) {
                p(n2 - this.r);
                this.r = n2;
            }
        }
        return z ? z : super.dispatchTouchEvent(motionEvent);
    }

    public void f() {
        LinearLayout linearLayout = this.p;
        if (linearLayout != null) {
            d(linearLayout, false);
        }
    }

    public void l(float f2) {
        if (f2 < 16.0f) {
            f2 = 16.0f;
        } else if (f2 > 32.0f) {
            f2 = 32.0f;
        }
        if (s != f2) {
            s = f2;
            o(f2);
        }
    }

    public void m(int i2) {
        this.f11059j = i2;
    }

    public void o(float f2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            j(this.p.getChildAt(i2), f2);
        }
    }

    public JsonViewLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11056g = -10377423;
        this.f11057h = -13421773;
        this.f11058i = -1151165;
        this.f11059j = -3976202;
        this.f11060k = -1;
        this.f11061l = -12543801;
        i(context);
    }

    public JsonViewLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11056g = -10377423;
        this.f11057h = -13421773;
        this.f11058i = -1151165;
        this.f11059j = -3976202;
        this.f11060k = -1;
        this.f11061l = -12543801;
        i(context);
    }
}
