package com.jingdong.jdreact.plugin.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

@SuppressLint({"NewApi"})
/* loaded from: classes13.dex */
public class CardItemView extends FrameLayout {
    private static final String TAG = "CardItemView";
    public SimpleDraweeView imageView;
    private View parentView;
    private Spring springX;
    int type;

    public CardItemView(Context context) {
        super(context);
        this.type = 0;
        FrameLayout.inflate(context, R.layout.jdreact_card_item_2, this);
        this.imageView = (SimpleDraweeView) findViewById(R.id.card_image_view);
        initSpring();
    }

    private void initSpring() {
        setLocalImage(this.imageView);
        Spring springConfig = SpringSystem.create().createSpring().setSpringConfig(new SpringConfig(250.0d, 250.0d));
        this.springX = springConfig;
        springConfig.addListener(new SimpleSpringListener() { // from class: com.jingdong.jdreact.plugin.banner.CardItemView.1
            {
                CardItemView.this = this;
            }

            @Override // com.jingdong.jdreact.plugin.banner.SimpleSpringListener, com.jingdong.jdreact.plugin.banner.SpringListener
            public void onSpringUpdate(Spring spring) {
                int screenX = CardItemView.this.setScreenX((int) spring.getCurrentValue());
                if (CardItemView.this.parentView != null) {
                    if (CardItemView.this.parentView instanceof CardSlidePanel) {
                        ((CardSlidePanel) CardItemView.this.parentView).onViewPosChangedRight(CardItemView.this, 0);
                    } else if (CardItemView.this.parentView instanceof CardSlidePanelStyle2) {
                        ((CardSlidePanelStyle2) CardItemView.this.parentView).onViewPosChangedRight(CardItemView.this, screenX);
                    }
                }
            }
        });
    }

    private void setCurrentSpringPos(int i2, int i3) {
        this.springX.setCurrentValue(i2);
    }

    public void animTo(int i2, int i3) {
        setCurrentSpringPos(getLeft(), getTop());
        this.springX.setEndValue(i2);
    }

    public void fillData(CardDataItem cardDataItem) {
        try {
            this.imageView.setImageURI(cardDataItem.imagePath);
        } catch (ArrayIndexOutOfBoundsException unused) {
        }
    }

    public void onStartDragging() {
        this.springX.setAtRest();
    }

    public void setLocalImage(SimpleDraweeView simpleDraweeView) {
        simpleDraweeView.setHierarchy(GenericDraweeHierarchyBuilder.newInstance(getResources()).setFadeDuration(300).setPlaceholderImage(R.drawable.jdreact_home_icon_default).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY).build());
    }

    public void setParentView(View view) {
        this.parentView = view;
    }

    public int setScreenX(int i2) {
        int left = i2 - getLeft();
        offsetLeftAndRight(left);
        return left;
    }

    public void setScreenY(int i2) {
        offsetTopAndBottom(i2 - getTop());
    }

    public CardItemView(Context context, int i2) {
        super(context);
        this.type = 0;
        if (i2 == 3) {
            FrameLayout.inflate(context, R.layout.jdreact_card_item_3, this);
        } else {
            FrameLayout.inflate(context, R.layout.jdreact_card_item, this);
        }
        this.imageView = (SimpleDraweeView) findViewById(R.id.card_image_view);
        this.type = i2;
        initSpring();
    }

    public CardItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.type = 0;
        FrameLayout.inflate(context, R.layout.jdreact_card_item, this);
        this.imageView = (SimpleDraweeView) findViewById(R.id.card_image_view);
        initSpring();
    }
}
