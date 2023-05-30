package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.LinearLayout;

/* loaded from: classes4.dex */
public abstract class LinearLayoutTab extends LinearLayout implements Checkable {
    private boolean mBroadcasting;
    private boolean mChecked;
    private b mOnCheckedChangeWidgetListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* renamed from: g  reason: collision with root package name */
        boolean f11977g;

        /* loaded from: classes4.dex */
        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b  reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public String toString() {
            return "CompoundButton.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + this.f11977g + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Boolean.valueOf(this.f11977g));
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f11977g = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* loaded from: classes4.dex */
    public interface b {
        void a(LinearLayoutTab linearLayoutTab, boolean z);
    }

    public LinearLayoutTab(Context context) {
        super(context);
        setClickable(true);
    }

    private void refreshState() {
        if (this.mChecked) {
            checked();
        } else {
            unChecked();
        }
    }

    protected abstract void checked();

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutTab.class.getName());
        accessibilityEvent.setChecked(this.mChecked);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutTab.class.getName());
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(this.mChecked);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.f11977g);
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f11977g = isChecked();
        return savedState;
    }

    @Override // android.view.View
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            refreshState();
            if (this.mBroadcasting) {
                return;
            }
            this.mBroadcasting = true;
            b bVar = this.mOnCheckedChangeWidgetListener;
            if (bVar != null) {
                bVar.a(this, this.mChecked);
            }
            this.mBroadcasting = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnCheckedChangeWidgetListener(b bVar) {
        this.mOnCheckedChangeWidgetListener = bVar;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        boolean z = this.mChecked;
        if (z) {
            return;
        }
        setChecked(!z);
    }

    protected abstract void unChecked();

    public LinearLayoutTab(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setClickable(true);
    }
}
