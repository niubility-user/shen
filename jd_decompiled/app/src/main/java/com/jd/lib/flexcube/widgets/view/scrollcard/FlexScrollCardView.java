package com.jd.lib.flexcube.widgets.view.scrollcard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.flexcube.help.IExpoInterface;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.ScrollCardEntity;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import com.jd.lib.flexcube.widgets.entity.text.ScrollCardConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class FlexScrollCardView extends HorizontalScrollView implements IWidget<ScrollCardEntity>, MsgInterface {

    /* renamed from: g  reason: collision with root package name */
    private ScrollCardConfig f4520g;

    /* renamed from: h  reason: collision with root package name */
    private float f4521h;

    /* renamed from: i  reason: collision with root package name */
    private e f4522i;

    /* renamed from: j  reason: collision with root package name */
    private ScrollCardEntity f4523j;

    /* renamed from: k  reason: collision with root package name */
    private BabelScope f4524k;

    /* renamed from: l  reason: collision with root package name */
    private LinearLayout f4525l;

    /* renamed from: m  reason: collision with root package name */
    private int f4526m;

    /* renamed from: n  reason: collision with root package name */
    private int f4527n;
    private int o;
    private List<Rect> p;
    private List<CardView> q;
    private int r;
    private Rect s;
    private Handler t;
    private Map u;
    private boolean v;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FlexScrollCardView.this.p == null) {
                FlexScrollCardView.this.p = new ArrayList();
            }
            FlexScrollCardView.this.p.clear();
            for (int i2 = 0; i2 < FlexScrollCardView.this.f4525l.getChildCount(); i2++) {
                CardView cardView = (CardView) FlexScrollCardView.this.f4525l.getChildAt(i2);
                FlexScrollCardView.this.p.add(new Rect(cardView.getLeft(), 0, cardView.getLeft() + cardView.getWidth(), 0));
            }
            FlexScrollCardView.this.scrollTo((FlexScrollCardView.this.u == null || FlexScrollCardView.this.u.get("mScrollX") == null) ? 0 : ((Integer) FlexScrollCardView.this.u.get("mScrollX")).intValue(), 0);
            FlexScrollCardView.this.v = true;
            if (FlexScrollCardView.this.u == null || FlexScrollCardView.this.u.get("hasFirstExpo") != null) {
                return;
            }
            FlexScrollCardView.this.n(0, 0);
            FlexScrollCardView.this.u.put("hasFirstExpo", "1");
        }
    }

    /* loaded from: classes15.dex */
    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1) {
                return;
            }
            FlexScrollCardView flexScrollCardView = FlexScrollCardView.this;
            flexScrollCardView.n(flexScrollCardView.o, FlexScrollCardView.this.f4527n);
        }
    }

    public FlexScrollCardView(Context context) {
        super(context);
        this.f4522i = new e(this);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
    }

    private void a(Map map, String str) {
        if (c.c(str)) {
            return;
        }
        if (str.startsWith("$")) {
            str = str.substring(1);
        }
        if (map.get(str) != null) {
            map.put(str, map.get(str).toString());
        }
    }

    private List<JSONObject> j(int i2, int i3) {
        List<CardView> list = this.q;
        if (list != null && !list.isEmpty()) {
            List<Integer> k2 = k(i2, i3);
            ArrayList arrayList = new ArrayList();
            if (k2 != null && !k2.isEmpty()) {
                Iterator<Integer> it = k2.iterator();
                while (it.hasNext()) {
                    arrayList.add(this.q.get(it.next().intValue()).a());
                }
            }
            return arrayList;
        }
        return new ArrayList();
    }

    private List<Integer> k(int i2, int i3) {
        int i4;
        if (this.p == null) {
            return null;
        }
        int layoutParamsWidth = ((i3 + getLayoutParamsWidth()) - getPaddingLeft()) - getPaddingRight();
        ArrayList arrayList = new ArrayList();
        for (int i5 = 0; i5 < this.p.size(); i5++) {
            Rect rect = this.p.get(i5);
            int i6 = rect.left;
            if ((i6 >= i2 && i6 <= layoutParamsWidth) || ((i4 = rect.right) >= i2 && i4 <= layoutParamsWidth)) {
                arrayList.add(Integer.valueOf(i5));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(int i2, int i3) {
        List<Integer> k2 = k(i2, i3);
        BabelScope babelScope = this.f4524k;
        IExpoInterface iExpoInterface = babelScope != null ? (IExpoInterface) babelScope.getService(IExpoInterface.class) : null;
        if (k2 == null || k2.isEmpty() || iExpoInterface == null) {
            return;
        }
        Iterator<Integer> it = k2.iterator();
        while (it.hasNext()) {
            JSONObject a2 = this.q.get(it.next().intValue()).a();
            if (a2 != null) {
                iExpoInterface.sendExposureData(a2);
            }
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        e eVar = this.f4522i;
        if (eVar != null) {
            eVar.i(new CfInfo(0.0f, 0.0f, 0.0f, 0.0f), 0.0f);
        }
        getLayoutParams().width = 0;
        setBackgroundColor(0);
        this.u = null;
        this.t = null;
        List<CardView> list = this.q;
        if (list != null && !list.isEmpty()) {
            Iterator<CardView> it = this.q.iterator();
            while (it.hasNext()) {
                it.next().clear();
            }
            this.q.clear();
        }
        LinearLayout linearLayout = this.f4525l;
        if (linearLayout != null && linearLayout.getChildCount() > 0) {
            this.f4525l.removeAllViews();
        }
        removeAllViews();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (this.t == null) {
                this.t = new b(Looper.getMainLooper());
            }
            this.f4527n = 0;
            this.o = Integer.MAX_VALUE;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void draw(Canvas canvas) {
        this.f4522i.b(canvas);
        super.draw(canvas);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        ScrollCardConfig scrollCardConfig = this.f4520g;
        if (scrollCardConfig == null) {
            return 0;
        }
        return scrollCardConfig.getH(this.f4521h);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        ScrollCardConfig scrollCardConfig = this.f4520g;
        if (scrollCardConfig == null) {
            return 0;
        }
        return scrollCardConfig.getW(this.f4521h);
    }

    public void l(List<HashMap> list, IWidgetCommunication iWidgetCommunication) {
        List<CardView> list2 = this.q;
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            a(list.get(i2), this.f4523j.dataPath.cardExpo);
            a(list.get(i2), this.f4523j.dataPath.cardEvent);
            this.q.get(i2).updateContent(list.get(i2), iWidgetCommunication);
        }
        post(new a());
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull ScrollCardEntity scrollCardEntity, float f2) {
        if (scrollCardEntity != null && scrollCardEntity.config != null && scrollCardEntity.dataPath != null) {
            scrollCardEntity.parseElementList();
            List<BaseWidgetEntity> list = scrollCardEntity.subElementList;
            if (list != null && !list.isEmpty()) {
                removeAllViews();
                this.f4523j = scrollCardEntity;
                this.f4520g = scrollCardEntity.config;
                this.f4521h = f2;
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(scrollCardEntity.bgColor, 0));
                this.f4522i.i(this.f4520g.cfInfo, f2);
                LinearLayout linearLayout = new LinearLayout(getContext());
                this.f4525l = linearLayout;
                linearLayout.setOrientation(0);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                PaddingInfo paddingInfo = this.f4520g.paddingInfo;
                if (paddingInfo != null) {
                    Rect padding = paddingInfo.getPadding(f2, true);
                    this.s = padding;
                    layoutParams.topMargin = padding.top;
                    layoutParams.bottomMargin = padding.bottom;
                } else {
                    this.s = new Rect();
                }
                addView(this.f4525l, layoutParams);
                this.r = (int) (this.f4520g.cardConfig.cardPadding * this.f4521h);
                return;
            }
            clear();
            return;
        }
        clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4522i.h(canvas);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        if (i2 < 0) {
            i2 = 0;
        }
        this.f4526m = i2;
        Map map = this.u;
        if (map != null) {
            map.put("mScrollX", Integer.valueOf(i2));
        }
        this.f4527n = Math.max(this.f4526m, this.f4527n);
        this.o = Math.min(this.f4526m, this.o);
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            if (this.v) {
                this.t.sendEmptyMessageDelayed(1, 200L);
            }
        }
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
        if (obj == null) {
            return;
        }
        try {
            int i2 = this.f4526m;
            cls.cast(obj).handleAction(j(i2, i2));
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ScrollCardEntity scrollCardEntity;
        List<BaseWidgetEntity> list;
        if (map != null && (scrollCardEntity = this.f4523j) != null && scrollCardEntity.dataPath != null && (list = scrollCardEntity.subElementList) != null && !list.isEmpty()) {
            List<HashMap> f2 = com.jd.lib.flexcube.widgets.b.b.f(map, this.f4523j.dataPath.scrollSubElement, HashMap.class);
            if (f2 != null && !f2.isEmpty()) {
                this.v = false;
                this.u = map;
                this.f4524k = iWidgetCommunication != null ? iWidgetCommunication.getBabelScope() : null;
                this.f4525l.removeAllViews();
                List<CardView> list2 = this.q;
                if (list2 == null) {
                    this.q = new ArrayList();
                } else {
                    list2.clear();
                }
                int i2 = 0;
                while (i2 < f2.size()) {
                    CardView cardView = new CardView(getContext(), this.f4521h, i2);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                    layoutParams.leftMargin = i2 == 0 ? this.s.left : 0;
                    layoutParams.rightMargin = i2 == f2.size() + (-1) ? this.s.right : this.r;
                    this.f4525l.addView(cardView, layoutParams);
                    cardView.updateStyle(this.f4523j, this.f4521h);
                    this.q.add(cardView);
                    i2++;
                }
                l(f2, iWidgetCommunication);
                return;
            }
            clear();
            return;
        }
        clear();
    }
}
