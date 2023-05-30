package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.view.JDViewFlipper;
import com.jingdong.app.mall.home.floor.view.view.SearchWordEntity;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class HomeTitleViewFlipper extends JDViewFlipper {
    private static final Handler E = new Handler(Looper.getMainLooper());
    private Animation A;
    private Animation B;
    private com.jingdong.app.mall.home.o.a.b C;
    private final Runnable D;

    /* renamed from: g */
    private int f10069g;

    /* renamed from: h */
    private ArrayList<SearchWordEntity> f10070h;

    /* renamed from: i */
    private ArrayList<SearchWordEntity> f10071i;

    /* renamed from: j */
    private ArrayList<SearchWordEntity> f10072j;

    /* renamed from: k */
    private ArrayList<SearchWordEntity> f10073k;

    /* renamed from: l */
    private SearchWordEntity f10074l;

    /* renamed from: m */
    private long f10075m;

    /* renamed from: n */
    private final AtomicBoolean f10076n;
    private long o;
    private long p;
    private int q;
    private boolean r;
    private int s;
    private int t;
    private final AtomicBoolean u;
    private final AtomicBoolean v;
    private boolean w;
    long x;
    private Animation y;
    private Animation z;

    /* loaded from: classes4.dex */
    public static class SimpleViewHolder extends LinearLayout {
        private final c clickListener;
        private final Context context;
        private final int initTextSize;
        private TextView textView;
        public SearchWordEntity wordEntity;

        /* loaded from: classes4.dex */
        public class a implements View.OnClickListener {
            a() {
                SimpleViewHolder.this = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SimpleViewHolder.this.clickListener != null) {
                    SimpleViewHolder.this.clickListener.onClick(SimpleViewHolder.this.wordEntity);
                }
            }
        }

        public SimpleViewHolder(Context context, int i2, c cVar) {
            super(context);
            this.context = context;
            this.initTextSize = i2;
            this.clickListener = cVar;
            initView();
        }

        private void initView() {
            setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            setOrientation(0);
            setGravity(16);
            TextView textView = new TextView(this.context);
            this.textView = textView;
            textView.setFocusable(false);
            this.textView.setGravity(16);
            this.textView.setBackgroundColor(0);
            this.textView.setTextSize(0, d.d(this.initTextSize));
            this.textView.setSingleLine(true);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setOnClickListener(new a());
            addView(this.textView, new LinearLayout.LayoutParams(-1, -1));
        }

        public void bindData(SearchWordEntity searchWordEntity) {
            String charSequence;
            this.wordEntity = searchWordEntity;
            if (searchWordEntity != null && searchWordEntity.isValid()) {
                charSequence = searchWordEntity.showWord;
                searchWordEntity.isShowed = true;
                searchWordEntity.carouseTimes++;
            } else {
                String charSequence2 = this.context.getResources().getText(R.string.homeActivity_autoComplete).toString();
                charSequence = this.context.getResources().getText(R.string.homeActivity_autoComplete_privacy).toString();
                if (f.k0()) {
                    charSequence = charSequence2;
                }
            }
            OKLog.d("HomeTitleViewFlipper", "setShowWord: " + charSequence);
            this.textView.setText(charSequence);
            this.textView.setContentDescription("\u641c\u7d22\u6846" + charSequence);
        }

        public SearchWordEntity getWordEntity() {
            return this.wordEntity;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ j.b f10078g;

        a(j.b bVar) {
            HomeTitleViewFlipper.this = r1;
            this.f10078g = bVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (JDHomeFragment.Q0()) {
                HomeTitleViewFlipper.this.s(this.f10078g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
            HomeTitleViewFlipper.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            HomeTitleViewFlipper.this.h();
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        void onClick(SearchWordEntity searchWordEntity);
    }

    public HomeTitleViewFlipper(Context context, c cVar) {
        super(context);
        this.f10076n = new AtomicBoolean(false);
        this.o = 1L;
        this.p = 0L;
        this.q = 0;
        this.s = 0;
        this.t = 0;
        this.u = new AtomicBoolean(false);
        this.v = new AtomicBoolean(false);
        this.w = true;
        this.x = 0L;
        this.D = new b();
        int l2 = l();
        int min = Math.min(com.jingdong.app.mall.home.state.old.a.f() ? l2 : e.b().c(l2), 36);
        addView(new SimpleViewHolder(context, min, cVar));
        addView(new SimpleViewHolder(context, min, cVar));
        n().bindData(null);
        OKLog.d("HomeTitleViewFlipper", "construct");
    }

    private void A() {
        ArrayList<SearchWordEntity> j2 = j();
        if (j2.size() > 0) {
            JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
            try {
                Iterator<SearchWordEntity> it = j2.iterator();
                while (it.hasNext()) {
                    SearchWordEntity next = it.next();
                    com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c("");
                    c2.put("word", next.showWord);
                    c2.put("sid", next.carouseTimes);
                    d.put(c2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            com.jingdong.app.mall.home.r.c.a.z("Home_SearchExpo", "", d.toString(), RecommendMtaUtils.Home_PageId);
            z(this.f10072j);
        }
    }

    private boolean f() {
        if (this.v.get() && this.u.get()) {
            return this.r;
        }
        return true;
    }

    private void g() {
        E.removeCallbacks(this.D);
        this.f10076n.set(false);
    }

    public void h() {
        if (this.v.get() && this.u.get() && this.r) {
            this.A = k(this.A, R.anim.home_title_search_box_alpha_in);
            this.B = k(this.B, R.anim.home_title_search_box_alpha_out);
            setInAnimation(this.A);
            setOutAnimation(this.B);
        } else {
            this.y = k(this.y, R.anim.home_title_search_box_anim_in);
            this.z = k(this.z, R.anim.home_title_search_box_anim_out);
            setInAnimation(this.y);
            setOutAnimation(this.z);
        }
        n().bindData(o());
        OKLog.d("HomeTitleViewFlipper", "interval: " + (System.currentTimeMillis() - this.x));
        showNext();
        q();
    }

    private boolean i(ArrayList<SearchWordEntity> arrayList, ArrayList<SearchWordEntity> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return (arrayList2 == null || arrayList2.isEmpty()) ? false : true;
        } else if (arrayList2 == null || arrayList2.isEmpty() || arrayList.size() != arrayList2.size()) {
            return true;
        } else {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                SearchWordEntity searchWordEntity = arrayList.get(i2);
                SearchWordEntity searchWordEntity2 = arrayList2.get(i2);
                if (searchWordEntity == null || searchWordEntity2 == null || !TextUtils.equals(searchWordEntity.showWord, searchWordEntity2.showWord) || !TextUtils.equals(searchWordEntity.realWord, searchWordEntity2.realWord) || !TextUtils.equals(searchWordEntity.sourceValue, searchWordEntity2.sourceValue)) {
                    return true;
                }
            }
            return false;
        }
    }

    private Animation k(Animation animation, int i2) {
        return animation != null ? animation : AnimationUtils.loadAnimation(getContext(), i2);
    }

    private SimpleViewHolder n() {
        int i2 = this.t;
        this.t = i2 + 1;
        View childAt = getChildAt(i2);
        f.n(childAt);
        SimpleViewHolder simpleViewHolder = (SimpleViewHolder) childAt;
        this.t %= 2;
        return simpleViewHolder;
    }

    private SearchWordEntity o() {
        if (this.s == 0) {
            long j2 = this.p;
            if (j2 >= this.o) {
                this.f10073k = this.f10071i;
            } else {
                this.f10073k = this.f10072j;
                this.p = j2 + 1;
            }
        }
        ArrayList<SearchWordEntity> arrayList = this.f10073k;
        if (arrayList != null && !arrayList.isEmpty()) {
            ArrayList<SearchWordEntity> arrayList2 = this.f10073k;
            int i2 = this.s;
            this.s = i2 + 1;
            SearchWordEntity searchWordEntity = arrayList2.get(i2);
            if (this.s == this.f10073k.size()) {
                this.s %= this.f10073k.size();
            }
            w(searchWordEntity);
            this.f10074l = searchWordEntity;
            return searchWordEntity;
        }
        this.s = 0;
        this.f10074l = null;
        return null;
    }

    private void p(ArrayList<SearchWordEntity> arrayList, ArrayList<SearchWordEntity> arrayList2) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<SearchWordEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            SearchWordEntity next = it.next();
            if (next != null && next.isShowed) {
                if (arrayList2.indexOf(next) > 0) {
                    return;
                }
                arrayList2.add(next);
            }
        }
    }

    private void q() {
        ArrayList<SearchWordEntity> arrayList;
        if (this.q >= 2 && (arrayList = this.f10073k) != null && arrayList.size() > 1 && f() && this.w) {
            g();
            this.f10076n.set(true);
            this.x = System.currentTimeMillis();
            E.postDelayed(this.D, this.q * 1000);
            return;
        }
        this.f10076n.set(false);
    }

    private ArrayList<SearchWordEntity> r(ArrayList<SearchWordEntity> arrayList, ArrayList<SearchWordEntity> arrayList2) {
        ArrayList<SearchWordEntity> arrayList3 = new ArrayList<>();
        if (arrayList != null && !arrayList.isEmpty()) {
            arrayList3.addAll(arrayList);
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            arrayList3.addAll(arrayList2);
        }
        return arrayList3;
    }

    public void s(j.b bVar) {
        OKLog.d("HomeTitleViewFlipper", "setData");
        boolean z = true;
        if (bVar == null || (TextUtils.isEmpty(bVar.a) && TextUtils.isEmpty(bVar.b))) {
            OKLog.d("HomeTitleViewFlipper", "data is null");
            this.f10070h = null;
            this.f10071i = null;
            this.f10072j = null;
            this.f10073k = null;
            this.o = 1L;
            this.q = 0;
            this.r = false;
            x();
            return;
        }
        ArrayList<SearchWordEntity> arrayList = TextUtils.isEmpty(bVar.a) ? null : (ArrayList) JDJSON.parseArray(bVar.a, SearchWordEntity.class);
        ArrayList<SearchWordEntity> arrayList2 = TextUtils.isEmpty(bVar.b) ? null : (ArrayList) JDJSON.parseArray(bVar.b, SearchWordEntity.class);
        if (!i(this.f10070h, arrayList) && !i(this.f10071i, arrayList2)) {
            z = false;
        }
        this.q = bVar.f10308c;
        this.r = bVar.d;
        if (z) {
            OKLog.d("HomeTitleViewFlipper", "data change");
            this.f10070h = arrayList;
            this.f10071i = arrayList2;
            this.f10072j = r(arrayList, arrayList2);
            this.f10073k = null;
            x();
            return;
        }
        if (this.s == 0) {
            if (this.p >= this.o) {
                this.f10073k = this.f10071i;
            } else {
                this.f10073k = this.f10072j;
            }
        }
        q();
    }

    private void w(SearchWordEntity searchWordEntity) {
        if (searchWordEntity == null || TextUtils.isEmpty(searchWordEntity.showWord)) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b b2 = com.jingdong.app.mall.home.r.c.b.b();
        b2.a("clientbegin", "" + System.currentTimeMillis());
        b2.a("word", searchWordEntity.showWord);
        com.jingdong.app.mall.home.floor.common.h.a.f("Home_CCSearchExpo", b2.toString());
    }

    private void x() {
        OKLog.d("HomeTitleViewFlipper", "refresh");
        g();
        y();
        View currentView = getCurrentView();
        f.n(currentView);
        SimpleViewHolder simpleViewHolder = (SimpleViewHolder) currentView;
        if (simpleViewHolder == null) {
            return;
        }
        simpleViewHolder.bindData(o());
        q();
    }

    private void y() {
        this.p = 0L;
        this.s = 0;
    }

    private void z(ArrayList<SearchWordEntity> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<SearchWordEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            SearchWordEntity next = it.next();
            next.isShowed = false;
            next.carouseTimes = 0;
        }
    }

    public void B(j.b bVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f10075m;
        com.jingdong.app.mall.home.o.a.b bVar2 = this.C;
        if (bVar2 != null) {
            E.removeCallbacks(bVar2);
        }
        if (elapsedRealtime > 10000) {
            s(bVar);
            return;
        }
        a aVar = new a(bVar);
        this.C = aVar;
        E.postDelayed(aVar, 1000L);
    }

    public void C() {
        this.v.set(true);
        this.u.set(true);
        g();
    }

    public void D(int i2) {
        this.f10069g = i2;
    }

    public void E(@ColorInt int i2, boolean z) {
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            f.n(childAt);
            SimpleViewHolder simpleViewHolder = (SimpleViewHolder) childAt;
            if (simpleViewHolder != null) {
                simpleViewHolder.textView.setTextColor(i2);
                simpleViewHolder.textView.getPaint().setFakeBoldText(z);
                simpleViewHolder.textView.setText(simpleViewHolder.textView.getText());
            }
        }
    }

    public void F(long j2) {
        if (this.o != j2) {
            this.o = j2;
            x();
        }
    }

    public void G(int i2) {
        int d = d.d(Math.min(i2, 36));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            f.n(childAt);
            SimpleViewHolder simpleViewHolder = (SimpleViewHolder) childAt;
            if (simpleViewHolder != null) {
                simpleViewHolder.textView.setTextSize(0, d);
            }
        }
    }

    public void H(boolean z) {
        this.u.set(z);
        if (f()) {
            if (this.f10076n.get()) {
                return;
            }
            q();
            return;
        }
        g();
    }

    public void c() {
        if (this.f10076n.get()) {
            return;
        }
        q();
    }

    public void d() {
        this.v.set(false);
        this.u.set(false);
    }

    public void e() {
        A();
    }

    public ArrayList<SearchWordEntity> j() {
        ArrayList<SearchWordEntity> arrayList = new ArrayList<>();
        p(this.f10072j, arrayList);
        return arrayList;
    }

    public int l() {
        int i2 = this.f10069g;
        return i2 != 0 ? i2 : com.jingdong.app.mall.home.state.old.a.f() ? 36 : 28;
    }

    public SearchWordEntity m() {
        this.f10075m = SystemClock.elapsedRealtime();
        View currentView = getCurrentView();
        if (currentView instanceof SimpleViewHolder) {
            this.f10074l = ((SimpleViewHolder) currentView).getWordEntity();
        }
        if (k.w()) {
            Context context = getContext();
            SearchWordEntity searchWordEntity = this.f10074l;
            ToastUtils.showToastInCenter(context, searchWordEntity != null ? searchWordEntity.showWord : DYConstants.DY_NULL_STR);
        }
        return this.f10074l;
    }

    public void t() {
        this.w = false;
        g();
        View currentView = getCurrentView();
        f.n(currentView);
        SimpleViewHolder simpleViewHolder = (SimpleViewHolder) currentView;
        if (simpleViewHolder == null) {
            return;
        }
        j.f(simpleViewHolder.wordEntity, true);
        A();
    }

    public void u(boolean z) {
        ArrayList<SearchWordEntity> arrayList;
        OKLog.d("HomeTitleViewFlipper", "onResume");
        this.w = true;
        if (z) {
            OKLog.d("HomeTitleViewFlipper", "onResume,needChangeText");
        }
        g();
        if (z) {
            if ((this.q < 2 || !f()) && (arrayList = this.f10073k) != null && arrayList.size() > 1) {
                View currentView = getCurrentView();
                f.n(currentView);
                SimpleViewHolder simpleViewHolder = (SimpleViewHolder) currentView;
                if (simpleViewHolder == null) {
                    return;
                }
                simpleViewHolder.bindData(o());
            }
        }
        q();
    }

    public void v() {
        int l2 = l();
        if (!com.jingdong.app.mall.home.state.old.a.f()) {
            l2 = e.b().c(l2);
        }
        G(l2);
    }
}
