package com.jd.dynamic.lib.views;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IDarkChangeListener;
import com.jd.dynamic.base.interfaces.IRecycleListener;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.TagViewAdapter;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class TagView extends ViewGroup implements IDarkChangeListener, IRecycleListener {

    /* renamed from: g */
    private JSONArray f2513g;

    /* renamed from: h */
    private final List<ItemView> f2514h;

    /* renamed from: i */
    private int f2515i;

    /* renamed from: j */
    private int f2516j;

    /* renamed from: k */
    private int f2517k;

    /* renamed from: l */
    private boolean f2518l;

    /* renamed from: m */
    private int f2519m;

    /* renamed from: n */
    private int f2520n;
    private int o;
    private int p;
    private TagViewAdapter q;
    private DataChangeObserver r;
    private int s;
    private DynamicTemplateEngine t;
    private String u;
    private boolean v;
    private SparseArrayCompat<ArrayList<TagViewAdapter.ItemViewHolder>> w;

    /* loaded from: classes13.dex */
    public class DataChangeObserver extends DataSetObserver {
        DataChangeObserver() {
            TagView.this = r1;
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TagView.this.c();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public TagView(@NonNull Context context) {
        this(context, null);
    }

    public TagView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2514h = new ArrayList();
        this.f2520n = Integer.MAX_VALUE;
        this.v = false;
        this.w = new SparseArrayCompat<>();
    }

    private int a(int i2) {
        int size = View.MeasureSpec.getSize(i2);
        return View.MeasureSpec.getMode(i2) == 1073741824 ? size : size;
    }

    private int b(int i2, int i3) {
        return View.MeasureSpec.getMode(i2) == 1073741824 ? View.MeasureSpec.getSize(i2) : i3;
    }

    public void c() {
        TagViewAdapter tagViewAdapter = this.q;
        if (tagViewAdapter == null || tagViewAdapter.getCount() == 0) {
            return;
        }
        removeAllViews();
        this.w.clear();
        this.p = 0;
        for (int i2 = 0; i2 < this.q.getCount(); i2++) {
            RecyclerView.ViewHolder viewHolder = this.q.getViewHolder(null, null, i2);
            if (viewHolder instanceof TagViewAdapter.ItemViewHolder) {
                viewHolder.itemView.setTag(R.id.dynamic_tag_view_item, com.jd.dynamic.b.c.a.b);
                this.q.bindViewHolder(i2, viewHolder);
                View view = viewHolder.itemView;
                view.setTag(R.id.dynamic_item_view_visibility, Integer.valueOf(view.getVisibility()));
                addView(viewHolder.itemView);
                TagViewAdapter.ItemViewHolder itemViewHolder = (TagViewAdapter.ItemViewHolder) viewHolder;
                int itemType = itemViewHolder.getItemType();
                ArrayList<TagViewAdapter.ItemViewHolder> arrayList = this.w.get(itemType);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.w.put(itemType, arrayList);
                }
                arrayList.add(itemViewHolder);
            }
        }
    }

    public /* synthetic */ void e(String str) {
        com.jd.dynamic.lib.utils.s.b(str, this, this.t, this);
    }

    public static /* synthetic */ void f(Throwable th) {
    }

    private void g() {
        post(new Runnable() { // from class: com.jd.dynamic.lib.views.s
            {
                TagView.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                TagView.this.h();
            }
        });
    }

    public /* synthetic */ void h() {
        if (getParent() instanceof YogaLayout) {
            ((YogaLayout) getParent()).invalidate(this);
        }
    }

    public void addItemView(ItemView itemView) {
        this.f2514h.add(itemView);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    public void attachEngine(DynamicTemplateEngine dynamicTemplateEngine) {
        this.t = dynamicTemplateEngine;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.LayoutParams(getContext(), attributeSet);
    }

    public TagViewAdapter getAdapter() {
        return this.q;
    }

    public int getCurrentShowChildCounts() {
        return this.p;
    }

    public int getCurrentShowHistoryNum() {
        return this.s;
    }

    public JSONArray getData() {
        return this.f2513g;
    }

    public DynamicTemplateEngine getEngine() {
        return this.t;
    }

    public ItemView getItemViewFromId(int i2, Map<String, Integer> map) {
        Integer num;
        List<ItemView> list = this.f2514h;
        if (list == null || list.size() == 0) {
            return null;
        }
        for (ItemView itemView : this.f2514h) {
            ViewNode viewNode = itemView.mine;
            if (viewNode != null && viewNode.getAttributes() != null && itemView.mine.getAttributes().size() > 0 && (num = map.get(itemView.mine.getAttributes().get(DYConstants.DY_IDENTIFIER))) != null && num.intValue() == i2) {
                return itemView;
            }
        }
        return this.f2514h.get(0);
    }

    public int getShowingCount() {
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3).getVisibility() == 0) {
                i2++;
            }
        }
        return i2;
    }

    @Override // com.jd.dynamic.base.interfaces.IDarkChangeListener
    public void onDarkChange(boolean z) {
        setAdapter();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int i11 = this.f2519m + paddingLeft;
        int i12 = 0;
        for (int i13 = 0; i13 < getChildCount(); i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                DynamicTemplateEngine dynamicTemplateEngine = this.t;
                if (dynamicTemplateEngine != null && dynamicTemplateEngine.isAttached && this.q != null && TagViewAdapter.getViewHolderByView(childAt) != null) {
                    TagViewAdapter.ItemViewHolder viewHolderByView = TagViewAdapter.getViewHolderByView(childAt);
                    viewHolderByView.dispatchTagViewEvent(this.q.getItem(viewHolderByView.getIndex()));
                }
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i7 = marginLayoutParams.topMargin;
                    i8 = marginLayoutParams.leftMargin;
                    i9 = marginLayoutParams.rightMargin;
                    i6 = marginLayoutParams.bottomMargin;
                } else {
                    i6 = 0;
                    i7 = 0;
                    i8 = 0;
                    i9 = 0;
                }
                int measuredWidth = childAt.getMeasuredWidth() + i8 + i9;
                int measuredHeight = childAt.getMeasuredHeight() + i7 + i6;
                if (i11 + measuredWidth + paddingRight > i10) {
                    paddingTop += this.f2516j + i12;
                    i11 = paddingLeft;
                    i12 = measuredHeight;
                }
                i12 = Math.max(measuredHeight, i12);
                childAt.layout(i8 + i11, i7 + paddingTop, (i11 + measuredWidth) - i9, (measuredHeight + paddingTop) - i6);
                i11 += measuredWidth + this.f2515i;
            }
        }
        if (this.v && getVisibility() == 0 && !TextUtils.isEmpty(this.u)) {
            Observable.from(com.jd.dynamic.lib.utils.s.i(this.u)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.t
                {
                    TagView.this = this;
                }

                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    TagView.this.e((String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.views.r
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    TagView.f((Throwable) obj);
                }
            });
            this.v = false;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int a = a(i2);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i10 = -1;
        int i11 = paddingLeft;
        int i12 = paddingTop;
        int i13 = 0;
        int i14 = -1;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        while (i13 < getChildCount()) {
            View childAt = getChildAt(i13);
            if (i14 != i10) {
                childAt.setVisibility(8);
                i4 = paddingLeft;
                i5 = paddingTop;
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                int i18 = i14;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(a + 1, View.MeasureSpec.getMode(i2));
                int i19 = paddingLeft + paddingRight;
                i4 = paddingLeft;
                int i20 = layoutParams.width;
                if (i20 <= 0) {
                    i20 = -2;
                }
                int childMeasureSpec = ViewGroup.getChildMeasureSpec(makeMeasureSpec, i19, i20);
                int i21 = paddingTop + paddingBottom;
                int i22 = layoutParams.height;
                i5 = paddingTop;
                if (i22 <= 0) {
                    i22 = -2;
                }
                childAt.measure(childMeasureSpec, ViewGroup.getChildMeasureSpec(i3, i21, i22));
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    i6 = marginLayoutParams.topMargin;
                    i8 = marginLayoutParams.leftMargin;
                    i9 = marginLayoutParams.rightMargin;
                    i7 = marginLayoutParams.bottomMargin;
                } else {
                    i6 = 0;
                    i7 = 0;
                    i8 = 0;
                    i9 = 0;
                }
                int measuredHeight = childAt.getMeasuredHeight();
                int measuredWidth = childAt.getMeasuredWidth() + i8 + i9;
                int i23 = measuredHeight + i6 + i7;
                if (i13 != 0 || i11 + measuredWidth + paddingRight <= a) {
                    this.p = i13 + 1;
                    if (i11 + measuredWidth + paddingRight > a) {
                        i17++;
                        if (i17 > this.f2520n - 1) {
                            this.p = i13;
                            childAt.setVisibility(8);
                            i14 = i13;
                        } else {
                            int i24 = this.f2517k;
                            if (i24 <= 0 || i17 != i24) {
                                i12 += this.f2516j + i15;
                                i15 = i23;
                                i11 = i4;
                            } else {
                                this.p = i13;
                                i14 = i18;
                            }
                        }
                    }
                    i15 = Math.max(i23, i15);
                    i11 += measuredWidth + this.f2515i;
                    i16 = Math.max(i11, i16);
                    i14 = i18;
                } else {
                    childAt.setVisibility(8);
                    i14 = 0;
                    i15 = 0;
                }
            }
            i13++;
            paddingLeft = i4;
            paddingTop = i5;
            i10 = -1;
        }
        if (this.f2517k == 1 && this.f2518l) {
            this.f2519m = a - i11;
        }
        int i25 = 0 + i12 + i15 + paddingBottom;
        if (i16 > 0) {
            i16 -= this.f2515i;
        }
        setMeasuredDimension(b(i2, i16), i25);
        com.jd.dynamic.lib.utils.t.e(DYConstants.DYN_TAG_VIEW, "onMeasure", "height = " + i25);
    }

    @Override // com.jd.dynamic.base.interfaces.IRecycleListener
    public void onViewRecycled() {
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setAdapter() {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.TagView.setAdapter():void");
    }

    public void setData(String str) {
        com.jd.dynamic.lib.utils.t.e("data=", str);
        try {
            if (!TextUtils.isEmpty(str) && !DynamicUtils.isElOrKnownSymbol(str)) {
                this.f2513g = new JSONArray(str);
            }
            this.f2513g = null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f2513g = null;
        }
    }

    public void setLimitColumnSize(int i2) {
        this.f2520n = i2;
        if (this.o != i2) {
            this.w.clear();
        }
        this.o = this.f2520n;
    }

    public void setLineSpacing(int i2) {
        this.f2516j = i2;
    }

    public void setOnLayoutEvent(String str) {
        this.u = str;
    }

    public void setTagSpacing(int i2) {
        this.f2515i = i2;
    }
}
