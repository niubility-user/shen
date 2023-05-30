package com.jd.lib.flexcube.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.b;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.LabelGroupEntity;
import com.jd.lib.flexcube.widgets.entity.TextEntity;
import com.jd.lib.flexcube.widgets.entity.text.LabelGroupConfig;
import com.jd.lib.flexcube.widgets.entity.text.LabelGroupDataPath;
import com.jd.lib.flexcube.widgets.entity.text.TextConfig;
import com.jd.lib.flexcube.widgets.entity.text.TextDataPath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexLabelGroup extends ViewGroup implements IWidget<LabelGroupEntity> {

    /* renamed from: g  reason: collision with root package name */
    private LabelGroupConfig f4460g;

    /* renamed from: h  reason: collision with root package name */
    private LabelGroupDataPath f4461h;

    /* renamed from: i  reason: collision with root package name */
    private final Context f4462i;

    /* renamed from: j  reason: collision with root package name */
    private float f4463j;

    /* renamed from: k  reason: collision with root package name */
    private int f4464k;

    /* renamed from: l  reason: collision with root package name */
    private TextConfig f4465l;

    /* renamed from: m  reason: collision with root package name */
    int f4466m;

    /* renamed from: n  reason: collision with root package name */
    int f4467n;
    private List<View> o;
    private List<List<View>> p;
    private List<Integer> q;

    public FlexLabelGroup(Context context) {
        super(context);
        getClass().getSimpleName();
        this.f4464k = 0;
        this.f4462i = context;
    }

    private void a() {
        this.p = new ArrayList();
        this.o = new ArrayList();
        this.q = new ArrayList();
    }

    private void b(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.f4461h.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = b.a(map, this.f4461h.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.f4461h.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull LabelGroupEntity labelGroupEntity, float f2) {
        LabelGroupDataPath labelGroupDataPath;
        LabelGroupConfig labelGroupConfig;
        if (labelGroupEntity != null && (labelGroupDataPath = labelGroupEntity.dataPath) != null && labelGroupDataPath.labels != null && (labelGroupConfig = labelGroupEntity.config) != null) {
            this.f4463j = f2;
            this.f4460g = labelGroupConfig;
            this.f4461h = labelGroupDataPath;
            TextConfig textConfig = new TextConfig();
            this.f4465l = textConfig;
            textConfig.autoFitType = "1";
            textConfig.maxRowNum = "1";
            LabelGroupConfig labelGroupConfig2 = labelGroupEntity.config;
            textConfig.color = labelGroupConfig2.color;
            textConfig.bgColor = labelGroupConfig2.bgColor;
            textConfig.fontInfo = labelGroupConfig2.fontInfo;
            textConfig.frameInfo = labelGroupConfig2.frameInfo;
            textConfig.paddingInfo = labelGroupConfig2.paddingInfo;
            textConfig.cfInfo = labelGroupConfig2.cfInfo;
            textConfig.w = Math.min(labelGroupConfig2.maxWidth, labelGroupConfig2.w);
            LabelGroupConfig labelGroupConfig3 = labelGroupEntity.config;
            this.f4467n = (int) (labelGroupConfig3.padding * f2);
            this.f4464k = (int) (labelGroupConfig3.w * f2);
            try {
                this.f4466m = Integer.parseInt(labelGroupConfig3.maxRowNum);
                return;
            } catch (Exception unused) {
                this.f4466m = Integer.MAX_VALUE;
                return;
            }
        }
        getLayoutParams().width = 0;
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        removeAllViews();
        List<List<View>> list = this.p;
        if (list != null || this.o != null || this.q != null) {
            list.clear();
            this.o.clear();
            this.q.clear();
        }
        setClickable(false);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        return -2;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        int i2 = this.f4464k;
        if (i2 > 0) {
            return i2;
        }
        return -2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int size = this.p.size();
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            List<View> list = this.p.get(i7);
            int intValue = this.q.get(i7).intValue();
            int size2 = list.size();
            int i8 = 0;
            for (int i9 = 0; i9 < size2; i9++) {
                View view = list.get(i9);
                view.layout(i8, i6, view.getMeasuredWidth() + i8, view.getMeasuredHeight() + i6);
                i8 += view.getMeasuredWidth() + this.f4467n;
            }
            i6 += intValue + this.f4467n;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        a();
        int childCount = getChildCount();
        int i4 = childCount - 1;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i5 >= childCount) {
                break;
            }
            View childAt = getChildAt(i5);
            measureChild(childAt, i2, i3);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i6 + measuredWidth > this.f4464k) {
                this.p.add(this.o);
                this.o = new ArrayList();
                i7 = Math.max(i7, i6 - this.f4467n);
                this.q.add(Integer.valueOf(i8));
                if (this.p.size() >= this.f4466m) {
                    i9 += i8;
                    break;
                }
                i9 = i9 + i8 + this.f4467n;
                i6 = 0;
                i8 = 0;
            }
            this.o.add(childAt);
            i6 += measuredWidth + this.f4467n;
            i8 = Math.max(i8, measuredHeight);
            if (i5 == i4) {
                i9 += i8;
                i7 = Math.max(i7, i6 - this.f4467n);
                this.q.add(Integer.valueOf(i8));
                this.p.add(this.o);
            }
            i5++;
        }
        setMeasuredDimension(this.f4464k, i9);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        LabelGroupDataPath labelGroupDataPath = this.f4461h;
        if (labelGroupDataPath != null && c.d(labelGroupDataPath.labels)) {
            List f2 = b.f(map, this.f4461h.labels, String.class);
            if (f2 != null && f2.size() != 0) {
                clear();
                int size = f2.size();
                int i2 = this.f4460g.maxLabel;
                if (size > i2) {
                    f2 = f2.subList(0, i2);
                }
                Iterator it = f2.iterator();
                while (it.hasNext()) {
                    TextDataPath textDataPath = new TextDataPath();
                    textDataPath.text = (String) it.next();
                    textDataPath.clickEvent = null;
                    TextEntity textEntity = new TextEntity();
                    textEntity.config = this.f4465l;
                    textEntity.dataPath = textDataPath;
                    FlexTextView flexTextView = new FlexTextView(this.f4462i);
                    flexTextView.updateStyle(textEntity, this.f4463j);
                    addView(flexTextView, new LinearLayout.LayoutParams(flexTextView.getLayoutParamsWidth(), flexTextView.getLayoutParamsHeight()));
                    flexTextView.updateContent(null, iWidgetCommunication);
                }
                if (TextUtils.isEmpty(this.f4461h.clickEvent)) {
                    setClickable(false);
                    return;
                }
                setClickable(true);
                b(map, iWidgetCommunication);
                return;
            }
            getLayoutParams().width = 0;
            clear();
            return;
        }
        getLayoutParams().width = 0;
        clear();
    }
}
