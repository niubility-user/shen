package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.views.view.ReactViewGroup;

/* loaded from: classes13.dex */
public class JDCustomCardbannerItemViewManager extends ViewGroupManager<FrameLayout> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RTCJDReactCardItemView";
    }

    /* loaded from: classes13.dex */
    public static class CardItemView3 extends FrameLayout {
        private int leftRightSpace;
        private int marginLeftRight;
        private ReactViewGroup parentView;
        private Spring springX;

        public CardItemView3(Context context) {
            super(context);
            this.marginLeftRight = 60;
            this.leftRightSpace = 40;
            this.marginLeftRight = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card_left_right_padding);
            this.leftRightSpace = context.getResources().getDimensionPixelOffset(R.dimen.jdreact_banner_card__left_right_space);
            initSpring();
        }

        private void initSpring() {
            Spring springConfig = SpringSystem.create().createSpring().setSpringConfig(new SpringConfig(250.0d, 250.0d));
            this.springX = springConfig;
            springConfig.addListener(new SimpleSpringListener() { // from class: com.jingdong.jdreact.plugin.banner.JDCustomCardbannerItemViewManager.CardItemView3.1
                @Override // com.jingdong.jdreact.plugin.banner.SimpleSpringListener, com.jingdong.jdreact.plugin.banner.SpringListener
                public void onSpringUpdate(Spring spring) {
                    CardItemView3.this.setScreenX((int) spring.getCurrentValue());
                    if (CardItemView3.this.parentView != null) {
                        if (CardItemView3.this.parentView instanceof CardSlidePanelStyle3) {
                            ((CardSlidePanelStyle3) CardItemView3.this.parentView).onViewPosChangedRight(CardItemView3.this, 0);
                        } else if (CardItemView3.this.parentView instanceof CardSlidePanelStyle4) {
                            ((CardSlidePanelStyle4) CardItemView3.this.parentView).onViewPosChangedRight(CardItemView3.this, 0);
                        }
                    }
                }
            });
        }

        private void setCurrentSpringPos(int i2, int i3) {
            this.springX.setCurrentValue(i2);
        }

        @Override // android.view.ViewGroup
        public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
            super.addView(view, i2, layoutParams);
        }

        public void animTo(int i2, int i3) {
            setCurrentSpringPos(getLeft(), getTop());
            this.springX.setEndValue(i2);
        }

        public void meansureall() {
            View childAt = getChildAt(0);
            if (childAt != null) {
                childAt.layout(0, 0, getWidth(), getHeight());
            }
        }

        public void onStartDragging() {
            this.springX.setAtRest();
        }

        public void setParentView(ReactViewGroup reactViewGroup) {
            this.parentView = reactViewGroup;
        }

        public int setScreenX(int i2) {
            int left = i2 - getLeft();
            offsetLeftAndRight(left);
            return left;
        }

        public void setScreenY(int i2) {
            offsetTopAndBottom(i2 - getTop());
        }

        public void meansureall(int i2) {
            measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
            View childAt = getChildAt(0);
            if (childAt != null) {
                childAt.layout(i2, 0, getWidth() - i2, getHeight());
            }
        }

        public CardItemView3(ThemedReactContext themedReactContext) {
            super(themedReactContext);
            this.marginLeftRight = 60;
            this.leftRightSpace = 40;
            initSpring();
        }

        public void meansureall(int i2, int i3) {
            measure(View.MeasureSpec.makeMeasureSpec(getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getHeight(), 1073741824));
            View childAt = getChildAt(0);
            if (childAt != null) {
                childAt.layout(i2, 0, getWidth() - i3, getHeight());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public FrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new CardItemView3(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(FrameLayout frameLayout) {
        super.onDropViewInstance((JDCustomCardbannerItemViewManager) frameLayout);
    }
}
