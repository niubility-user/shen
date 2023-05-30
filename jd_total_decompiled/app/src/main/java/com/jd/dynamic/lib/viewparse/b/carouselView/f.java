package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.s;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jd.dynamic.lib.viewparse.b.carouselView.d;
import com.jd.dynamic.lib.views.ItemView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class f extends FrameLayout {
    private String A;
    private String B;
    private String C;
    private String D;
    private final d.a E;

    /* renamed from: g  reason: collision with root package name */
    private ViewPager f2334g;

    /* renamed from: h  reason: collision with root package name */
    private ViewPager2 f2335h;

    /* renamed from: i  reason: collision with root package name */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> f2336i;

    /* renamed from: j  reason: collision with root package name */
    private PagerAdapter f2337j;

    /* renamed from: k  reason: collision with root package name */
    private final List<ItemView> f2338k;

    /* renamed from: l  reason: collision with root package name */
    private JSONArray f2339l;

    /* renamed from: m  reason: collision with root package name */
    private ViewNode f2340m;

    /* renamed from: n  reason: collision with root package name */
    private d f2341n;
    private e o;
    private Context p;
    private int q;
    private m r;
    private final Map<String, i> s;
    private Map<String, Integer> t;
    private AtomicInteger u;
    private DynamicTemplateEngine v;
    private int w;
    private String x;
    private boolean y;
    private String z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements PullToRefreshBase.OnRefreshListener<com.jd.dynamic.lib.viewparse.b.carouselView.d> {
        a() {
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
        public void onRefresh(PullToRefreshBase<com.jd.dynamic.lib.viewparse.b.carouselView.d> pullToRefreshBase) {
            pullToRefreshBase.onRefreshComplete();
            if (TextUtils.isEmpty(f.this.v())) {
                return;
            }
            List<String> i2 = s.i(f.this.v());
            if (i2.isEmpty()) {
                return;
            }
            Iterator<String> it = i2.iterator();
            while (it.hasNext()) {
                f fVar = f.this;
                s.b(it.next(), fVar, fVar.v, f.this);
            }
        }
    }

    /* loaded from: classes13.dex */
    class b implements d.a {
        b() {
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a
        @Nullable
        public String a() {
            return null;
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a
        public void a(String str, boolean z) {
            DYConstants.DYLog("\u8fd9\u91ccfinish,inner");
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a
        public void a(boolean z) {
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a
        @Nullable
        public View b() {
            if (f.this.o != null) {
                return f.this.o.getView();
            }
            return null;
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a
        public void b(boolean z) {
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a
        public boolean c() {
            return false;
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
        }

        @Override // com.jd.dynamic.lib.viewparse.b.a.d.a, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final ViewPager2 f2343g;

        /* renamed from: h  reason: collision with root package name */
        private int f2344h;

        /* renamed from: i  reason: collision with root package name */
        private final int f2345i;

        /* renamed from: j  reason: collision with root package name */
        private final d f2346j;

        /* renamed from: k  reason: collision with root package name */
        private final int f2347k;

        c(ViewPager2 viewPager2, int i2, int i3, d dVar, int i4) {
            this.f2343g = viewPager2;
            this.f2344h = i2;
            this.f2345i = i3;
            this.f2346j = dVar;
            this.f2347k = i4;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewPager2 viewPager2;
            if (BaseFrameLayout.getMyHandler() == null || (viewPager2 = this.f2343g) == null) {
                return;
            }
            d dVar = d.TYPE_IDLE;
            d dVar2 = this.f2346j;
            if (dVar == dVar2 || d.TYPE_LOAD_MORE == dVar2) {
                return;
            }
            if (d.TYPE_NORMAL != dVar2 || this.f2344h < this.f2345i) {
                viewPager2.setCurrentItem(this.f2344h);
                this.f2344h++;
                BaseFrameLayout.getMyHandler().postDelayed(this, this.f2347k);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public enum d {
        TYPE_CYCLE,
        TYPE_NORMAL,
        TYPE_LOAD_MORE,
        TYPE_IDLE
    }

    public f(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2338k = new ArrayList();
        this.f2341n = d.TYPE_IDLE;
        this.q = 0;
        this.s = new HashMap();
        this.t = new HashMap();
        this.u = new AtomicInteger(100);
        this.w = 2000;
        this.x = "0";
        this.y = false;
        this.E = new b();
        e(context);
    }

    public f(Context context, ViewNode viewNode) {
        this(context, null, 0);
        this.f2340m = viewNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A() {
        this.f2334g.getAdapter().notifyDataSetChanged();
        t();
    }

    private void e(Context context) {
        this.p = context;
    }

    private void g(d dVar) {
        if (dVar == null || dVar == this.f2341n) {
            return;
        }
        this.f2341n = dVar;
        int i2 = this.q;
        if (i2 <= 0 || i2 >= u()) {
            this.q = 0;
        }
        setTag(R.id.carousel_current_page, Integer.valueOf(this.q));
    }

    private void l() {
        ViewPager2 viewPager2;
        int i2;
        if (this.f2335h == null) {
            return;
        }
        if ("0".equals(this.x)) {
            viewPager2 = this.f2335h;
            i2 = 0;
        } else {
            viewPager2 = this.f2335h;
            i2 = 1;
        }
        viewPager2.setOrientation(i2);
    }

    private void m(ItemView itemView) {
        ViewNode viewNode;
        HashMap<String, String> attributes;
        i n2;
        if (itemView == null || (viewNode = itemView.mine) == null || (attributes = viewNode.getAttributes()) == null) {
            return;
        }
        String str = attributes.get("triggerAction");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = attributes.get(DYConstants.DY_IDENTIFIER);
        if (TextUtils.isEmpty(str2) || this.s.containsKey(str2) || (n2 = n(str)) == null) {
            return;
        }
        this.s.put(str2, n2);
    }

    private i n(String str) {
        try {
            int indexOf = str.indexOf(":");
            if (indexOf <= 0) {
                return null;
            }
            i iVar = new i();
            iVar.b = Float.parseFloat(str.substring(0, indexOf));
            iVar.a = str.substring(indexOf + 1, str.length());
            return iVar;
        } catch (Exception unused) {
            return null;
        }
    }

    private void p() {
        if (this.p == null) {
            return;
        }
        this.f2335h = new ViewPager2(this.p);
        l();
        NormalPageAdapter2 normalPageAdapter2 = new NormalPageAdapter2(this, true);
        this.f2336i = normalPageAdapter2;
        this.f2335h.setAdapter(normalPageAdapter2);
        this.f2335h.setCurrentItem(this.q);
        if (getChildCount() > 0) {
            removeAllViews();
        }
        super.addView(this.f2335h, new FrameLayout.LayoutParams(-1, -1));
        this.f2335h.registerOnPageChangeCallback(new com.jd.dynamic.lib.viewparse.b.carouselView.c(this, this.v));
        if (this.y) {
            s();
        }
    }

    private void q() {
        if (this.p == null) {
            return;
        }
        this.f2335h = new ViewPager2(this.p);
        l();
        NormalPageAdapter2 normalPageAdapter2 = new NormalPageAdapter2(this, false);
        this.f2336i = normalPageAdapter2;
        this.f2335h.setAdapter(normalPageAdapter2);
        this.f2335h.setCurrentItem(this.q);
        if (getChildCount() > 0) {
            removeAllViews();
        }
        super.addView(this.f2335h, new FrameLayout.LayoutParams(-1, -1));
        this.f2335h.registerOnPageChangeCallback(new com.jd.dynamic.lib.viewparse.b.carouselView.c(this, this.v));
    }

    private void r() {
        if (!(this.p instanceof FragmentActivity)) {
            DYConstants.DYLog("carousel view context not activity!!!!!");
            return;
        }
        m mVar = new m(this.p);
        this.r = mVar;
        mVar.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        this.r.setOnRefreshListener(new a());
        this.r.a(this.z);
        this.r.b(this.A);
        this.r.c(this.B);
        com.jd.dynamic.lib.viewparse.b.carouselView.d refreshableView = this.r.getRefreshableView();
        this.f2334g = refreshableView;
        refreshableView.v(this.E);
        this.f2334g.addOnPageChangeListener(new com.jd.dynamic.lib.viewparse.b.carouselView.c(this, this.v));
        h hVar = new h((FragmentActivity) this.p, this.f2334g, this);
        this.f2337j = hVar;
        hVar.b(this.q);
        ((h) this.f2337j).c(new TextView(this.p));
        this.f2334g.setAdapter(this.f2337j);
        this.f2334g.setCurrentItem(this.q);
        if (getChildCount() > 0) {
            removeAllViews();
        }
        super.addView(this.r, new FrameLayout.LayoutParams(-1, -1));
    }

    private void s() {
        if (this.f2335h != null && u() > 0) {
            Handler myHandler = BaseFrameLayout.getMyHandler();
            ViewPager2 viewPager2 = this.f2335h;
            int currentItem = viewPager2.getCurrentItem();
            int u = u();
            d dVar = this.f2341n;
            int i2 = this.w;
            myHandler.postDelayed(new c(viewPager2, currentItem, u, dVar, i2), i2);
        }
    }

    private void t() {
        int i2 = this.q;
        if (i2 <= 0 || i2 >= u()) {
            return;
        }
        setTag(R.id.carousel_current_page, Integer.valueOf(this.q));
        ViewPager2 viewPager2 = this.f2335h;
        if (viewPager2 != null) {
            viewPager2.setCurrentItem(this.q);
        }
        PagerAdapter pagerAdapter = this.f2337j;
        if (pagerAdapter instanceof h) {
            ((h) pagerAdapter).b(this.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String v() {
        return this.C;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z() {
        this.f2335h.getAdapter().notifyDataSetChanged();
        t();
    }

    public void D(int i2) {
    }

    public void E(boolean z) {
        this.y = z;
        if (z) {
            s();
        }
    }

    public void F(String str) {
        try {
            if (DynamicUtils.isElOrKnownSymbol(str)) {
                this.f2339l = null;
            } else {
                this.f2339l = new JSONArray(str);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void G(int i2) {
        if (i2 <= 0) {
            return;
        }
        this.w = i2;
    }

    public void H(String str) {
        d dVar;
        if (str == null) {
            return;
        }
        str.hashCode();
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
        }
        switch (c2) {
            case 0:
                dVar = d.TYPE_NORMAL;
                break;
            case 1:
                dVar = d.TYPE_CYCLE;
                break;
            case 2:
                dVar = d.TYPE_LOAD_MORE;
                break;
            default:
                return;
        }
        g(dVar);
    }

    public void I(int i2) {
        this.q = i2;
    }

    public void J(String str) {
        this.C = str;
    }

    public void K(String str) {
        this.B = str;
        m mVar = this.r;
        if (mVar != null) {
            mVar.c(str);
        }
    }

    public void L(String str) {
        this.z = str;
        m mVar = this.r;
        if (mVar != null) {
            mVar.a(str);
        }
    }

    public void M(String str) {
        this.A = str;
        m mVar = this.r;
        if (mVar != null) {
            mVar.b(str);
        }
    }

    public void N(String str) {
        this.D = str;
    }

    public void O(String str) {
        this.x = str;
        l();
    }

    public i a(String str) {
        return this.s.get(str);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    public String b(int i2) {
        ViewNode viewNode;
        int andDecrement;
        JSONArray jSONArray = this.f2339l;
        if (jSONArray == null) {
            return String.valueOf(1000);
        }
        try {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null && (viewNode = this.f2340m) != null) {
                String str = viewNode.getAttributes().get(DYConstants.DY_ITEM_IDENTIFIER);
                if (TextUtils.isEmpty(str)) {
                    str = DYConstants.DY_IDENTIFIER;
                }
                String optString = optJSONObject.optString(str);
                if (TextUtils.isEmpty(optString)) {
                    return String.valueOf(1000);
                }
                Integer num = this.t.get(optString);
                if (num != null) {
                    return String.valueOf(num);
                }
                try {
                    andDecrement = Integer.parseInt(optString);
                    this.t.put(optString, Integer.valueOf(andDecrement));
                } catch (Exception unused) {
                    andDecrement = this.u.getAndDecrement();
                    this.t.put(optString, Integer.valueOf(andDecrement));
                }
                return String.valueOf(andDecrement);
            }
            return String.valueOf(1000);
        } catch (Exception unused2) {
            return String.valueOf(1000);
        }
    }

    public void d() {
        ViewPager viewPager = this.f2334g;
        if (viewPager == null && this.f2335h == null) {
            if (d.TYPE_CYCLE == this.f2341n) {
                p();
            }
            if (d.TYPE_NORMAL == this.f2341n) {
                q();
            }
            if (d.TYPE_LOAD_MORE == this.f2341n) {
                r();
                return;
            }
            return;
        }
        if (viewPager != null && viewPager.getAdapter() != null) {
            if (this.f2334g.getWindowToken() != null) {
                this.f2334g.getAdapter().notifyDataSetChanged();
                t();
            } else {
                post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.b.a.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        f.this.A();
                    }
                });
            }
        }
        ViewPager2 viewPager2 = this.f2335h;
        if (viewPager2 == null || viewPager2.getAdapter() == null) {
            return;
        }
        if (this.f2335h.getWindowToken() == null) {
            post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.b.a.b
                @Override // java.lang.Runnable
                public final void run() {
                    f.this.z();
                }
            });
            return;
        }
        this.f2335h.getAdapter().notifyDataSetChanged();
        t();
    }

    public void f(DynamicTemplateEngine dynamicTemplateEngine) {
        this.v = dynamicTemplateEngine;
    }

    public void h(ItemView itemView) {
        this.f2338k.add(itemView);
        m(itemView);
    }

    public ItemView j(String str) {
        List<ItemView> list = this.f2338k;
        ItemView itemView = null;
        if (list == null || list.size() == 0) {
            return null;
        }
        for (ItemView itemView2 : this.f2338k) {
            ViewNode viewNode = itemView2.mine;
            if (viewNode != null && viewNode.getAttributes() != null && itemView2.mine.getAttributes().size() > 0 && !TextUtils.isEmpty(itemView2.mine.getAttributes().get(DYConstants.DY_IDENTIFIER))) {
                Integer num = this.t.get(itemView2.mine.getAttributes().get(DYConstants.DY_IDENTIFIER));
                if (num == null || !TextUtils.equals(str, String.valueOf(num))) {
                }
            }
            itemView = itemView2;
        }
        return itemView == null ? this.f2338k.get(0) : itemView;
    }

    public JSONObject k(int i2) {
        JSONArray jSONArray = this.f2339l;
        if (jSONArray != null && i2 < jSONArray.length()) {
            try {
                return this.f2339l.optJSONObject(i2);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public int u() {
        JSONArray jSONArray = this.f2339l;
        if (jSONArray == null || jSONArray.length() == 0) {
            return 0;
        }
        return this.f2339l.length();
    }

    public ViewPager2 w() {
        return this.f2335h;
    }

    public String x() {
        return this.D;
    }

    public ViewPager y() {
        return this.f2334g;
    }
}
