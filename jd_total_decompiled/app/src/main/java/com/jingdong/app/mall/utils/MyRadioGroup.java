package com.jingdong.app.mall.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

/* loaded from: classes4.dex */
public class MyRadioGroup extends LinearLayout {
    private int mCheckedId;
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private c mOnCheckedChangeListener;
    private d mPassThroughListener;
    private boolean mProtectFromCheckedChange;

    /* loaded from: classes4.dex */
    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.view.ViewGroup.LayoutParams
        protected void setBaseAttributes(TypedArray typedArray, int i2, int i3) {
            if (typedArray.hasValue(i2)) {
                ((LinearLayout.LayoutParams) this).width = typedArray.getLayoutDimension(i2, "layout_width");
            } else {
                ((LinearLayout.LayoutParams) this).width = -2;
            }
            if (typedArray.hasValue(i3)) {
                ((LinearLayout.LayoutParams) this).height = typedArray.getLayoutDimension(i3, "layout_height");
            } else {
                ((LinearLayout.LayoutParams) this).height = -2;
            }
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class b implements CompoundButton.OnCheckedChangeListener {
        private b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (MyRadioGroup.this.mProtectFromCheckedChange) {
                return;
            }
            MyRadioGroup.this.mProtectFromCheckedChange = true;
            if (MyRadioGroup.this.mCheckedId != -1) {
                MyRadioGroup myRadioGroup = MyRadioGroup.this;
                myRadioGroup.setCheckedStateForView(myRadioGroup.mCheckedId, false);
            }
            MyRadioGroup.this.mProtectFromCheckedChange = false;
            MyRadioGroup.this.setCheckedId(compoundButton.getId());
        }
    }

    /* loaded from: classes4.dex */
    public interface c {
        void a(MyRadioGroup myRadioGroup, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class d implements ViewGroup.OnHierarchyChangeListener {

        /* renamed from: g  reason: collision with root package name */
        private ViewGroup.OnHierarchyChangeListener f11776g;

        private d() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            MyRadioGroup myRadioGroup = MyRadioGroup.this;
            if (view == myRadioGroup && (view2 instanceof RadioButton)) {
                if (view2.getId() == -1) {
                    view2.setId(view2.hashCode());
                }
                ((RadioButton) view2).setOnCheckedChangeListener(MyRadioGroup.this.mChildOnCheckedChangeListener);
            } else if (view == myRadioGroup && (view2 instanceof ViewGroup)) {
                RadioButton findRadioButton = myRadioGroup.findRadioButton((ViewGroup) view2);
                if (findRadioButton.getId() == -1) {
                    findRadioButton.setId(findRadioButton.hashCode());
                }
                findRadioButton.setOnCheckedChangeListener(MyRadioGroup.this.mChildOnCheckedChangeListener);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f11776g;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            MyRadioGroup myRadioGroup = MyRadioGroup.this;
            if (view == myRadioGroup && (view2 instanceof RadioButton)) {
                ((RadioButton) view2).setOnCheckedChangeListener(null);
            } else if (view == myRadioGroup && (view2 instanceof ViewGroup)) {
                myRadioGroup.findRadioButton((ViewGroup) view2).setOnCheckedChangeListener(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f11776g;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public MyRadioGroup(Context context) {
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
        this.mCheckedId = i2;
        c cVar = this.mOnCheckedChangeListener;
        if (cVar != null) {
            cVar.a(this, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedStateForView(int i2, boolean z) {
        View findViewById = findViewById(i2);
        if (findViewById == null || !(findViewById instanceof RadioButton)) {
            return;
        }
        ((RadioButton) findViewById).setChecked(z);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) view;
            if (radioButton.isChecked()) {
                this.mProtectFromCheckedChange = true;
                int i3 = this.mCheckedId;
                if (i3 != -1) {
                    setCheckedStateForView(i3, false);
                }
                this.mProtectFromCheckedChange = false;
                setCheckedId(radioButton.getId());
            }
        } else if (view instanceof ViewGroup) {
            RadioButton findRadioButton = findRadioButton((ViewGroup) view);
            if (findRadioButton.isChecked()) {
                this.mProtectFromCheckedChange = true;
                int i4 = this.mCheckedId;
                if (i4 != -1) {
                    setCheckedStateForView(i4, false);
                }
                this.mProtectFromCheckedChange = false;
                setCheckedId(findRadioButton.getId());
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

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void clearCheck() {
        check(-1);
    }

    public RadioButton findRadioButton(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        RadioButton radioButton = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            if (viewGroup.getChildAt(i2) instanceof RadioButton) {
                radioButton = (RadioButton) viewGroup.getChildAt(i2);
            } else if (viewGroup.getChildAt(i2) instanceof ViewGroup) {
                findRadioButton((ViewGroup) viewGroup.getChildAt(i2));
            }
        }
        return radioButton;
    }

    public int getCheckedRadioButtonId() {
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
        this.mPassThroughListener.f11776g = onHierarchyChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LinearLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public MyRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCheckedId = -1;
        this.mProtectFromCheckedChange = false;
        init();
    }
}
