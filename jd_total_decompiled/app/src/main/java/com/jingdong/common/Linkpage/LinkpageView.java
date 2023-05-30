package com.jingdong.common.Linkpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.R;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class LinkpageView extends FrameLayout {
    private LinkpageAdapter adapter;
    private LinearLayout container;
    private Context context;
    private int currentIndex;
    private ImageView imageView;
    private ILinkpageListener listener;
    private int pageCount;
    private int pageNumber;
    private ImageView passImg;
    private ImageView[] points;
    private List<Integer> posterList;
    private ViewPager viewPager;

    /* loaded from: classes5.dex */
    private class pointListener implements View.OnClickListener {
        private pointListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LinkpageView.this.setCurView(((Integer) view.getTag()).intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class viewpagerListener implements ViewPager.OnPageChangeListener {
        int pageState;

        private viewpagerListener() {
            this.pageState = 100;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            this.pageState = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            if (!LinkpageView.this.posterList.isEmpty() && i2 == LinkpageView.this.posterList.size() - 1 && this.pageState == 1) {
                this.pageState = 100;
                LinkpageView.this.listener.onFinish();
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            LinkpageView.this.pageNumber = i2;
            LinkpageView.this.sendCommonData(i2);
            if (LinkpageView.this.posterList.isEmpty() || i2 != LinkpageView.this.posterList.size() - 1) {
                LinkpageView.this.container.setVisibility(0);
            } else {
                LinkpageView.this.container.setVisibility(4);
            }
        }
    }

    public LinkpageView(Context context, ILinkpageListener iLinkpageListener) {
        this(context, null, iLinkpageListener);
    }

    private void initPoint(int i2) {
        this.pageCount = i2;
        this.container.removeAllViews();
    }

    private void initView() {
        ImageView imageView = (ImageView) findViewById(R.id.img_pass);
        this.passImg = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.Linkpage.LinkpageView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LinkpageView.this.listener != null) {
                    LinkpageView.this.listener.onPassClickListener(LinkpageView.this.pageNumber);
                }
            }
        });
        this.container = (LinearLayout) findViewById(R.id.sh);
        ViewPager viewPager = (ViewPager) findViewById(R.id.z8);
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new viewpagerListener());
        LinkpageAdapter linkpageAdapter = new LinkpageAdapter(this.context, this.posterList, this.listener);
        this.adapter = linkpageAdapter;
        this.viewPager.setAdapter(linkpageAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCommonData(int i2) {
        if (i2 == 0) {
            sendCommonData("NewFunctionNavigation_VirtualVisitFirstPageExpo", "NewFunctionNavigation_FirstPage");
        } else if (i2 == 1) {
            sendCommonData("NewFunctionNavigation_VirtualVisitSecondPageExpo", "NewFunctionNavigation_SecondPage");
        } else if (i2 == 2) {
            sendCommonData("NewFunctionNavigation_VirtualVisitThirdPageExpo", "NewFunctionNavigation_ThirdPage");
        } else if (i2 == 3) {
            sendCommonData("NewFunctionNavigation_VirtualVisitForthPageExpo", "NewFunctionNavigation_ForthPage");
        } else if (i2 != 4) {
        } else {
            sendCommonData("NewFunctionNavigation_VirtualVisitLastPageExpo", "NewFunctionNavigation_LastPage");
        }
    }

    private void setCurDot(int i2) {
        if (i2 < 0 || i2 > this.pageCount - 1 || this.currentIndex == i2) {
            return;
        }
        this.points[i2].setEnabled(true);
        this.points[this.currentIndex].setEnabled(false);
        this.currentIndex = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurView(int i2) {
        if (i2 < 0 || i2 >= this.pageCount) {
            return;
        }
        this.viewPager.setCurrentItem(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.adapter = null;
        ViewPager viewPager = this.viewPager;
        if (viewPager != null) {
            viewPager.setAdapter(null);
            this.viewPager.removeAllViews();
        }
        this.viewPager = null;
        super.onDetachedFromWindow();
    }

    public void refreshData(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        initPoint(list.size());
        this.posterList.clear();
        this.posterList.addAll(list);
        this.adapter.notifyDataSetChanged();
        if (this.posterList.isEmpty()) {
            return;
        }
        sendCommonData("NewFunctionNavigation_VirtualVisitFirstPageExpo", "NewFunctionNavigation_FirstPage");
    }

    public LinkpageView(Context context, AttributeSet attributeSet, ILinkpageListener iLinkpageListener) {
        super(context, attributeSet);
        this.currentIndex = 0;
        this.pageCount = 1;
        this.pageNumber = 0;
        this.posterList = new ArrayList();
        this.context = context;
        this.listener = iLinkpageListener;
        LayoutInflater.from(context).inflate(R.layout.view_linkpage, (ViewGroup) this, true);
        initView();
    }

    private void sendCommonData(String str, String str2) {
        Context context = this.context;
        JDMtaUtils.sendCommonData(context, str, "", "", context, "", "", "", str2);
    }
}
