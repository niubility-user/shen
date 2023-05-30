package com.jd.viewkit.templates.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitConstant;
import com.jd.viewkit.helper.JDViewKitLayoutInterface;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.container.JDViewKitDefaultBannerView;
import com.jd.viewkit.templates.container.JDViewKitFullHorBannerView;
import com.jd.viewkit.templates.container.JDViewKitHorizontalBannerView;
import com.jd.viewkit.templates.container.JDViewKitMultiTabView;
import com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewKitBannerView;
import com.jd.viewkit.templates.container.jdviewkitdynamicbanner.gradient.JDViewKitGradientDynamicBanner;
import com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner;
import com.jd.viewkit.templates.container.jdviewkitflatviewviewv2.JDViewKitFlatViewViewV2;
import com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitScorllView;
import com.jd.viewkit.templates.container.jdviewkitswipecard.JDViewKitSwipeCardView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.templates.view.jdviewkitrichtextview.JDViewKitRichTextView;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitAbsoluteLayout extends JDViewKitBaseLayout implements View.OnClickListener, JDViewKitLayoutInterface {
    private static final String TAG = "JDViewKitAbsoluteLayout";
    protected JDViewKitDataSourceModel dataSourceModel;
    private boolean isOnlyUseAbsolute;
    private int itemSpace;
    protected Context mContext;
    private int maxHeigh;
    private int maxWidth;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;

    public JDViewKitAbsoluteLayout(@NonNull Context context) {
        super(context);
        this.isOnlyUseAbsolute = true;
        this.mContext = context;
    }

    private void layoutAbsolute(int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt == this.bgImageView) {
                childAt.layout(0, 0, i4 - i2, i5 - i3);
            } else if (JDViewKitBaseLayout.viewIsFilter(childAt)) {
                childAt.layout(i2, i3, i2, i3);
            } else {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int i7 = marginLayoutParams.topMargin;
                int i8 = marginLayoutParams.leftMargin;
                int i9 = marginLayoutParams.rightMargin;
                int i10 = marginLayoutParams.bottomMargin;
                childAt.layout(i8, i7, measuredWidth + i8, measuredHeight + i7);
            }
        }
    }

    private void layoutHorizontal(int i2, int i3, int i4, int i5) {
        int i6;
        int childCount = getChildCount();
        String layout_subAxis = getLayout_subAxis();
        int i7 = i4 - i2;
        int i8 = this.paddingLeft;
        int i9 = i5 - i3;
        int i10 = (i9 - this.paddingTop) - this.paddingBottom;
        int i11 = 0;
        int i12 = 0;
        while (i12 < childCount) {
            View childAt = getChildAt(i12);
            if (childAt == this.bgImageView) {
                childAt.layout(i11, i11, i7, i9);
            } else if (JDViewKitBaseLayout.viewIsFilter(childAt)) {
                childAt.layout(i2, i3, i2, i3);
            } else {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                String layout_alignSelf = childAt instanceof JDViewKitLayoutInterface ? ((JDViewKitLayoutInterface) childAt).getLayout_alignSelf() : layout_subAxis;
                int i13 = this.paddingTop;
                if (layout_alignSelf.equals(JDViewKitConstant.LAYOUT_ALIGN_CENTER)) {
                    i6 = (i10 - measuredHeight) / 2;
                } else {
                    if (layout_alignSelf.equals(JDViewKitConstant.LAYOUT_ALIGN_END)) {
                        i6 = i10 - measuredHeight;
                    }
                    childAt.layout(i8, i13, i8 + measuredWidth, measuredHeight + i13);
                    i8 += measuredWidth + this.itemSpace;
                }
                i13 += i6;
                childAt.layout(i8, i13, i8 + measuredWidth, measuredHeight + i13);
                i8 += measuredWidth + this.itemSpace;
            }
            i12++;
            i11 = 0;
        }
    }

    private void layoutVertical(int i2, int i3, int i4, int i5) {
        int i6;
        String layout_subAxis = getLayout_subAxis();
        int i7 = i4 - i2;
        int i8 = (i7 - this.paddingLeft) - this.paddingRight;
        int childCount = getChildCount();
        int i9 = this.paddingTop;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt == this.bgImageView) {
                childAt.layout(0, 0, i7, i5 - i3);
            } else if (JDViewKitBaseLayout.viewIsFilter(childAt)) {
                childAt.layout(i2, i3, i2, i3);
            } else {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                String layout_alignSelf = childAt instanceof JDViewKitLayoutInterface ? ((JDViewKitLayoutInterface) childAt).getLayout_alignSelf() : layout_subAxis;
                int i11 = this.paddingLeft;
                if (layout_alignSelf.equals(JDViewKitConstant.LAYOUT_ALIGN_CENTER)) {
                    i6 = (i8 - measuredWidth) / 2;
                } else {
                    if (layout_alignSelf.equals(JDViewKitConstant.LAYOUT_ALIGN_END)) {
                        i6 = i8 - measuredWidth;
                    }
                    childAt.layout(i11, i9, measuredWidth + i11, i9 + measuredHeight);
                    i9 += measuredHeight + this.itemSpace;
                }
                i11 += i6;
                childAt.layout(i11, i9, measuredWidth + i11, i9 + measuredHeight);
                i9 += measuredHeight + this.itemSpace;
            }
        }
    }

    private int[] measureAbsolute() {
        return new int[]{this.maxWidth, this.maxHeigh};
    }

    private int[] measureHorizontal() {
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt != this.bgImageView && !JDViewKitBaseLayout.viewIsFilter(childAt)) {
                i2 += childAt.getMeasuredWidth() + this.itemSpace;
            }
        }
        if (i2 > 0) {
            i2 -= this.itemSpace;
        }
        return new int[]{Math.min(i2 + this.paddingLeft + this.paddingRight, this.maxWidth), this.maxHeigh};
    }

    private int[] measureVertical() {
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt != this.bgImageView && !JDViewKitBaseLayout.viewIsFilter(childAt)) {
                i2 += childAt.getMeasuredHeight() + this.itemSpace;
            }
        }
        if (i2 > 0) {
            i2 -= this.itemSpace;
        }
        return new int[]{this.maxWidth, Math.min(i2 + this.paddingTop + this.paddingBottom, this.maxHeigh)};
    }

    public JDViewKitDataSourceModel getDataSourceModel() {
        return this.dataSourceModel;
    }

    public JDViewKitVirtualView getJDViewKitVirtualView() {
        return this.virtualView;
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_alignSelf() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return t.getLayout_alignSelf();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_direction() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_DIRECTION_HORIZONTAL;
        }
        return t.getLayout_direction();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public int getLayout_itemSpace() {
        T t = this.virtualView;
        if (t == 0) {
            return 0;
        }
        return t.getLayout_itemSpace();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_mainAxis() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return t.getLayout_mainAxis();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_subAxis() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return t.getLayout_subAxis();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_type() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LYAOUT_TYPE_ABSOLUTE;
        }
        return t.getLayout_type();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public boolean getOnlyUseAbsolute() {
        return this.isOnlyUseAbsolute;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JDViewKitEventHelper.click(getJDViewKitVirtualView(), this.dataSourceModel, this, getChannelModel());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (!this.isOnlyUseAbsolute && this.virtualView != 0 && getDataSourceModel() != null) {
            if (getLayout_type().equals(JDViewKitConstant.LYAOUT_TYPE_LINEAR)) {
                if (getLayout_direction().equals(JDViewKitConstant.LAYOUT_DIRECTION_VERTICAL)) {
                    layoutVertical(i2, i3, i4, i5);
                    return;
                } else {
                    layoutHorizontal(i2, i3, i4, i5);
                    return;
                }
            }
            layoutAbsolute(i2, i3, i4, i5);
            return;
        }
        layoutAbsolute(i2, i3, i4, i5);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int[] measureHorizontal;
        super.onMeasure(i2, i3);
        measureChildren(i2, i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (!this.isOnlyUseAbsolute && this.virtualView != 0 && getDataSourceModel() != null) {
            if (getLayout_type().equals(JDViewKitConstant.LYAOUT_TYPE_LINEAR)) {
                if (getLayout_direction().equals(JDViewKitConstant.LAYOUT_DIRECTION_VERTICAL)) {
                    measureHorizontal = measureVertical();
                } else {
                    measureHorizontal = measureHorizontal();
                }
                if (measureHorizontal != null && measureHorizontal.length == 2) {
                    size = Math.min(measureHorizontal[0], this.maxWidth);
                    size2 = Math.min(measureHorizontal[1], this.maxHeigh);
                }
                setMeasuredDimension(size, size2);
            }
            int[] measureAbsolute = measureAbsolute();
            i4 = measureAbsolute[0];
            i5 = measureAbsolute[1];
        } else {
            int[] measureAbsolute2 = measureAbsolute();
            i4 = measureAbsolute2[0];
            i5 = measureAbsolute2[1];
        }
        int i6 = i4;
        size2 = i5;
        size = i6;
        setMeasuredDimension(size, size2);
    }

    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        Map mapByValueRe;
        if (jDViewKitDataSourceModel == null) {
            return;
        }
        this.dataSourceModel = jDViewKitDataSourceModel;
        setDataSourceMap(jDViewKitDataSourceModel.getDataMap(), z);
        if (getChildCount() == 0) {
            return;
        }
        if (StringTool.isNotEmpty(this.virtualView.getValueRefer()) && (mapByValueRe = ExpressionParserTool.getMapByValueRe(this.virtualView.getValueRefer(), this.dataSourceModel.getDataMap())) != null) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel2 = new JDViewKitDataSourceModel(mapByValueRe);
            jDViewKitDataSourceModel2.setTimeStame(jDViewKitDataSourceModel.timeStame);
            jDViewKitDataSourceModel2.setViewListener(jDViewKitDataSourceModel.getViewListener());
            jDViewKitDataSourceModel2.setFloorAcrossListener(jDViewKitDataSourceModel.getFloorAcrossListener());
            jDViewKitDataSourceModel = jDViewKitDataSourceModel2;
        }
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof JDViewKitAbsoluteLayout) {
                JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = (JDViewKitAbsoluteLayout) childAt;
                jDViewKitAbsoluteLayout.setOnlyUseAbsolute(this.isOnlyUseAbsolute);
                jDViewKitAbsoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, z);
                jDViewKitAbsoluteLayout.setDataSourceMap(jDViewKitDataSourceModel.getDataMap(), z);
            } else if (childAt instanceof JDViewKitImageView) {
                ((JDViewKitImageView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitTextView) {
                ((JDViewKitTextView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitRichTextView) {
                ((JDViewKitRichTextView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitFreeCouponView) {
                ((JDViewKitFreeCouponView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitCouponView) {
                ((JDViewKitCouponView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitFlatViewViewV2) {
                ((JDViewKitFlatViewViewV2) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitBannerView) {
                ((JDViewKitBannerView) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitDefaultBannerView) {
                ((JDViewKitDefaultBannerView) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitReversalDynamicBanner) {
                ((JDViewKitReversalDynamicBanner) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitGradientDynamicBanner) {
                ((JDViewKitGradientDynamicBanner) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitScorllView) {
                ((JDViewKitScorllView) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitMultiTabView) {
                ((JDViewKitMultiTabView) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitProgressView) {
                ((JDViewKitProgressView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitSearchView) {
                ((JDViewKitSearchView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitMultistateView) {
                JDViewKitMultistateView jDViewKitMultistateView = (JDViewKitMultistateView) childAt;
                jDViewKitMultistateView.setDataSourceModel(jDViewKitDataSourceModel, z);
                jDViewKitMultistateView.setDataSourceMap(jDViewKitDataSourceModel.getDataMap(), z);
            } else if (childAt instanceof JDViewKitFullHorBannerView) {
                ((JDViewKitFullHorBannerView) childAt).setDataSourceSByVirtualView(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitHorizontalBannerView) {
                ((JDViewKitHorizontalBannerView) childAt).setDataSourceSByVirtualView(this.dataSourceModel, z);
            } else if (childAt instanceof JDViewKitSwipeCardView) {
                ((JDViewKitSwipeCardView) childAt).setDataSourceSByVirtualView(this.dataSourceModel, z);
            } else if (childAt instanceof JDViewKitCountdownView) {
                ((JDViewKitCountdownView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            } else if (childAt instanceof JDViewKitVideoView) {
                ((JDViewKitVideoView) childAt).setDataSourceModel(jDViewKitDataSourceModel, z);
            }
        }
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public void setOnlyUseAbsolute(boolean z) {
        this.isOnlyUseAbsolute = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualView jDViewKitVirtualView) {
        if (jDViewKitVirtualView == 0) {
            return;
        }
        this.virtualView = jDViewKitVirtualView;
        this.maxWidth = GlobalManage.getInstance().getRealPx(getVirtualView().getWidth(), getChannelModel());
        this.maxHeigh = GlobalManage.getInstance().getRealPx(getVirtualView().getHeigh(), getChannelModel());
        this.itemSpace = GlobalManage.getInstance().getRealPx(getVirtualView().getLayout_itemSpace(), getChannelModel());
        this.paddingTop = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingTop(), getChannelModel());
        this.paddingRight = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingRight(), getChannelModel());
        this.paddingBottom = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingBottom(), getChannelModel());
        this.paddingLeft = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingLeft(), getChannelModel());
        super.setVirtualView(jDViewKitVirtualView);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getHeigh(), getChannelModel()));
        layoutParams.topMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getOrgY(), getChannelModel());
        layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getOrgX(), getChannelModel());
        setLayoutParams(layoutParams);
        if (jDViewKitVirtualView.getChildren() != null && jDViewKitVirtualView.getChildren().size() > 0) {
            for (int i2 = 0; i2 < jDViewKitVirtualView.getChildren().size(); i2++) {
                View viewByVirtualView = JDViewKitViewFactory.getViewByVirtualView(this.mContext, jDViewKitVirtualView.getChildren().get(i2), getChannelModel());
                if (viewByVirtualView != null) {
                    addView(viewByVirtualView);
                }
            }
        }
        if (jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick) != null) {
            setOnClickListener(this);
        }
        if (!StringTool.isEmpty(jDViewKitVirtualView.getHidden())) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public JDViewKitAbsoluteLayout(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }

    public JDViewKitAbsoluteLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isOnlyUseAbsolute = true;
    }

    public JDViewKitAbsoluteLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.isOnlyUseAbsolute = true;
    }
}
