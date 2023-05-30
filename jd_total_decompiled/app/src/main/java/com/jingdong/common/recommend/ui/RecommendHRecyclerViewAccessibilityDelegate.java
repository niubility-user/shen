package com.jingdong.common.recommend.ui;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

/* loaded from: classes6.dex */
public class RecommendHRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
    private final AccessibilityDelegateCompat homeItemDelegate;

    /* loaded from: classes6.dex */
    private static class HomeItemDelegate extends RecyclerViewAccessibilityDelegate.ItemDelegate {
        HomeItemDelegate(RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate) {
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
            } catch (Exception unused) {
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate.ItemDelegate, androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            try {
                if (isErrorView(view)) {
                    return false;
                }
                return super.performAccessibilityAction(view, i2, bundle);
            } catch (Exception unused) {
                return false;
            }
        }
    }

    public RecommendHRecyclerViewAccessibilityDelegate(RecyclerView recyclerView) {
        super(recyclerView);
        this.homeItemDelegate = new HomeItemDelegate(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
    public AccessibilityDelegateCompat getItemDelegate() {
        return this.homeItemDelegate;
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        try {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate, androidx.core.view.AccessibilityDelegateCompat
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        try {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        } catch (Exception unused) {
        }
    }
}
