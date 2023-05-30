package com.jingdong.app.mall.home.widget.recommend;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.jingdong.app.mall.home.o.a.f;

/* loaded from: classes4.dex */
public class HomeRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
    private final AccessibilityDelegateCompat a;

    /* loaded from: classes4.dex */
    private static class a extends RecyclerViewAccessibilityDelegate.ItemDelegate {
        a(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
            super(recyclerViewAccessibilityDelegate);
        }

        private boolean isErrorView(View view) {
            return view == null || view.getLayoutParams() == null || !(view.getLayoutParams() instanceof RecyclerView.LayoutParams);
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate.ItemDelegate, androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            try {
                if (isErrorView(view)) {
                    return;
                }
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            } catch (Exception e2) {
                f.s0(this, e2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate.ItemDelegate, androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            try {
                if (isErrorView(view)) {
                    return false;
                }
                return super.performAccessibilityAction(view, i2, bundle);
            } catch (Exception e2) {
                f.s0(this, e2);
                return false;
            }
        }
    }

    public HomeRecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        super(recyclerView);
        this.a = new a(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
    public AccessibilityDelegateCompat getItemDelegate() {
        return this.a;
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        try {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        try {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }
}
