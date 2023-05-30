package com.jingdong.jdreact.plugin.banner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.jingdong.jdreact.plugin.banner.ui.CardSwitchListener;
import java.util.List;

@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})
/* loaded from: classes13.dex */
public class CardFragment extends FrameLayout {
    private List<CardDataItem> dataList;
    private Context mContext;
    private int type;

    public CardFragment(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.type = 1;
    }

    private void initView(Context context) {
        int i2 = this.type;
        if (i2 == 1) {
            FrameLayout.inflate(context, R.layout.jdreact_card_layout, this);
        } else if (i2 == 2) {
            FrameLayout.inflate(context, R.layout.jdreact_card_layout_2, this);
        } else if (i2 == 3) {
            FrameLayout.inflate(context, R.layout.jdreact_card_layout_3, this);
        }
    }

    public void clearCardSlide() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.image_slide_panel);
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                viewGroup.getChildAt(i2).setBackground(null);
            }
            viewGroup.removeAllViews();
        }
    }

    public void enableflip(boolean z) {
        CycleViewPager cycleViewPager;
        int i2 = this.type;
        if (i2 == 1) {
            CardSlidePanel cardSlidePanel = (CardSlidePanel) findViewById(R.id.image_slide_panel);
            if (cardSlidePanel != null) {
                cardSlidePanel.enableflip(z);
            }
        } else if (i2 != 3 || (cycleViewPager = (CycleViewPager) findViewById(R.id.image_slide_panel)) == null) {
        } else {
            cycleViewPager.enableflip(z);
        }
    }

    public void setCardSwitchListener(CardSwitchListener cardSwitchListener) {
        CycleViewPager cycleViewPager;
        int i2 = this.type;
        if (i2 == 1) {
            CardSlidePanel cardSlidePanel = (CardSlidePanel) findViewById(R.id.image_slide_panel);
            if (cardSlidePanel != null) {
                cardSlidePanel.setCardSwitchListener(cardSwitchListener);
            }
        } else if (i2 == 2) {
            CardSlidePanelStyle2 cardSlidePanelStyle2 = (CardSlidePanelStyle2) findViewById(R.id.image_slide_panel);
            if (cardSlidePanelStyle2 != null) {
                cardSlidePanelStyle2.setCardSwitchListener(cardSwitchListener);
            }
        } else if (i2 != 3 || (cycleViewPager = (CycleViewPager) findViewById(R.id.image_slide_panel)) == null) {
        } else {
            cycleViewPager.setCardSwitchListener(cardSwitchListener);
        }
    }

    public void setDataList(List<CardDataItem> list, int i2, int i3) {
        if (list == null || list.size() < 1) {
            return;
        }
        this.dataList = list;
        int i4 = this.type;
        if (i4 == 1) {
            CardSlidePanel cardSlidePanel = (CardSlidePanel) findViewById(R.id.image_slide_panel);
            cardSlidePanel.fillData(this.dataList, i2, i3);
            if (cardSlidePanel.hasInit()) {
                cardSlidePanel.reLayout();
            }
        } else if (i4 == 2) {
            CardSlidePanelStyle2 cardSlidePanelStyle2 = (CardSlidePanelStyle2) findViewById(R.id.image_slide_panel);
            cardSlidePanelStyle2.fillData(this.dataList, i3);
            if (cardSlidePanelStyle2.hasInit()) {
                cardSlidePanelStyle2.reLayout();
            }
        } else if (i4 == 3) {
            CycleViewPager cycleViewPager = (CycleViewPager) findViewById(R.id.image_slide_panel);
            cycleViewPager.fillData(this.dataList, i3);
            if (cycleViewPager.hasInit()) {
                cycleViewPager.reLayout();
            }
        }
    }

    public void setScaleXY(boolean z) {
        CardSlidePanelStyle2 cardSlidePanelStyle2;
        if (this.type != 2 || (cardSlidePanelStyle2 = (CardSlidePanelStyle2) findViewById(R.id.image_slide_panel)) == null) {
            return;
        }
        cardSlidePanelStyle2.setScaleXY(z);
    }

    public void setStyle(int i2) {
        this.type = i2;
        initView(this.mContext);
    }

    public void setTimeInteval(int i2) {
        int i3 = this.type;
        if (i3 == 1) {
            ((CardSlidePanel) findViewById(R.id.image_slide_panel)).setTimeInteval(i2);
        } else if (i3 == 2) {
            ((CardSlidePanelStyle2) findViewById(R.id.image_slide_panel)).setTimeInteval(i2);
        } else if (i3 == 3) {
            ((CycleViewPager) findViewById(R.id.image_slide_panel)).setTimeInteval(i2);
        }
    }

    public CardFragment(Context context) {
        super(context);
        this.type = 1;
        this.mContext = context;
    }

    public CardFragment(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.type = 1;
        this.mContext = context;
    }
}
