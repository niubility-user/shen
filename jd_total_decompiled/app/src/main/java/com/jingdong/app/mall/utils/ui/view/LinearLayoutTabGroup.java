package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jingdong.app.mall.utils.ui.view.LinearLayoutTab;

/* loaded from: classes4.dex */
public class LinearLayoutTabGroup extends LinearLayout {
    private int mCheckedId;
    private LinearLayoutTab.b mChildOnCheckedChangeListener;
    private c mOnCheckedChangeListener;
    private d mPassThroughListener;
    private boolean mProtectFromCheckedChange;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class b implements LinearLayoutTab.b {
        private b() {
        }

        @Override // com.jingdong.app.mall.utils.ui.view.LinearLayoutTab.b
        public void a(LinearLayoutTab linearLayoutTab, boolean z) {
            if (LinearLayoutTabGroup.this.mProtectFromCheckedChange) {
                return;
            }
            int id = linearLayoutTab.getId();
            LinearLayoutTabGroup.this.mProtectFromCheckedChange = true;
            if (LinearLayoutTabGroup.this.mCheckedId != -1 && LinearLayoutTabGroup.this.mCheckedId != id && z) {
                LinearLayoutTabGroup linearLayoutTabGroup = LinearLayoutTabGroup.this;
                linearLayoutTabGroup.setCheckedStateForView(linearLayoutTabGroup.mCheckedId, false);
            }
            LinearLayoutTabGroup.this.mProtectFromCheckedChange = false;
            if (LinearLayoutTabGroup.this.mCheckedId == id || !z) {
                return;
            }
            LinearLayoutTabGroup.this.setCheckedId(id);
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        void a(LinearLayoutTabGroup linearLayoutTabGroup, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class d implements ViewGroup.OnHierarchyChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private ViewGroup.OnHierarchyChangeListener f11978g;

        private d() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view == LinearLayoutTabGroup.this && (view2 instanceof LinearLayoutTab)) {
                if (view2.getId() == -1) {
                    view2.setId(view2.hashCode());
                }
                ((LinearLayoutTab) view2).setOnCheckedChangeWidgetListener(LinearLayoutTabGroup.this.mChildOnCheckedChangeListener);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f11978g;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == LinearLayoutTabGroup.this && (view2 instanceof LinearLayoutTab)) {
                ((LinearLayoutTab) view2).setOnCheckedChangeWidgetListener(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f11978g;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public LinearLayoutTabGroup(Context context) {
        super(context);
        this.mCheckedId = -1;
        this.mProtectFromCheckedChange = false;
        setOrientation(1);
        init();
    }

    private void init() {
        this.mChildOnCheckedChangeListener = new b();
        d dVar = new d();
        this.mPassThroughListener = dVar;
        super.setOnHierarchyChangeListener(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i2) {
        if (this.mCheckedId == i2) {
            return;
        }
        this.mCheckedId = i2;
        c cVar = this.mOnCheckedChangeListener;
        if (cVar != null) {
            cVar.a(this, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedStateForView(int i2, boolean z) {
        View findViewById = findViewById(i2);
        if (findViewById == null || !(findViewById instanceof LinearLayoutTab)) {
            return;
        }
        ((LinearLayoutTab) findViewById).setChecked(z);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof LinearLayoutTab) {
            LinearLayoutTab linearLayoutTab = (LinearLayoutTab) view;
            if (linearLayoutTab.isChecked()) {
                this.mProtectFromCheckedChange = true;
                int i3 = this.mCheckedId;
                if (i3 != -1) {
                    setCheckedStateForView(i3, false);
                }
                this.mProtectFromCheckedChange = false;
                setCheckedId(linearLayoutTab.getId());
            }
        }
        super.addView(view, i2, layoutParams);
    }

    public void check(int i2) {
        if (i2 == -1 || i2 != this.mCheckedId) {
            int i3 = this.mCheckedId;
            if (i3 != -1) {
                setCheckedStateForView(i3, false);
            }
            if (i2 != -1) {
                setCheckedStateForView(i2, true);
            }
            setCheckedId(i2);
        }
    }

    public void clearCheck() {
        check(-1);
    }

    public int getCheckedLinearLayoutTabId() {
        return this.mCheckedId;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.mCheckedId;
        if (i2 != -1) {
            this.mProtectFromCheckedChange = true;
            setCheckedStateForView(i2, true);
            this.mProtectFromCheckedChange = false;
            setCheckedId(this.mCheckedId);
        }
    }

    public void setOnCheckedChangeListener(c cVar) {
        this.mOnCheckedChangeListener = cVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mPassThroughListener.f11978g = onHierarchyChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LinearLayout.LayoutParams(-2, -2);
    }

    public LinearLayoutTabGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCheckedId = -1;
        this.mProtectFromCheckedChange = false;
        init();
    }
}
