package com.jingdong.sdk.platform.business.viewholder;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.adapter.PartsRecyclerAdapterB;
import com.jingdong.sdk.platform.business.entity.PartsEntity;
import com.jingdong.sdk.platform.business.entity.PartsItemEntity;
import com.jingdong.sdk.platform.business.utils.BusinessTools;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import com.jingdong.sdk.platform.business.views.PullToSeekMoreHorizontalRecyclerView;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.mta.MtaParams;
import com.jingdong.sdk.platform.utils.PlatformEventUtils;
import com.jingdong.sdk.platform.utils.PlatformTools;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class CommonPartsViewHolderB extends BaseViewHolder {
    private PartsRecyclerAdapterB adapter;
    private View floorLayout;
    private boolean isTenthRevision;
    private Context mContext;
    private RecyclerView.ItemDecoration mDecoration;
    private View mRedView;
    private int mSpaceMarginLeft;
    private int mSpaceRight;
    private PullToSeekMoreHorizontalRecyclerView seekMoreView;
    private int space;
    private SimpleDraweeView tailIcon;
    private TextView text1;
    private TextView text2;
    private LinearLayout titleLayout;

    public CommonPartsViewHolderB(Context context, BaseData baseData, String str, ViewGroup viewGroup) {
        super(context, baseData, str, viewGroup);
        this.mContext = context;
    }

    private void bindData2View(final PartsEntity partsEntity) {
        if (partsEntity != null && partsEntity.isEffective()) {
            this.isTenthRevision = partsEntity.tenthRevision;
            boolean z = partsEntity.darkModel;
            String str = z ? "#1d1b1b" : partsEntity.bgc;
            int i2 = z ? R.color.pd_color_1d1b1b : R.color.platform_color_white;
            if (getBaseEntity() instanceof BaseTemplateEntity) {
                ViewCompat.setBackground(this.mRootView, BusinessTools.getFloorBgDrawable((BaseTemplateEntity) getBaseEntity(), str, ContextCompat.getColor(this.mContext, i2), partsEntity.darkModel, this.isTenthRevision));
            } else {
                this.mRootView.setBackgroundColor(PlatformTools.getColorValue(str, i2));
            }
            if (this.isTenthRevision) {
                this.mRedView.setBackgroundResource(0);
                this.mRedView.setVisibility(8);
            } else {
                this.mRedView.setBackgroundResource(partsEntity.darkModel ? R.drawable.platform_left_red_line_dark : R.drawable.lib_pd_red_linebar);
                this.mRedView.setVisibility(0);
            }
            handleIcon(partsEntity);
            handleText(partsEntity);
            this.seekMoreView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
            RecyclerView refreshableView = this.seekMoreView.getRefreshableView();
            if (refreshableView.getItemDecorationCount() == 0) {
                refreshableView.addItemDecoration(this.mDecoration);
            }
            PartsRecyclerAdapterB partsRecyclerAdapterB = new PartsRecyclerAdapterB(this.mContext, partsEntity, this.isTenthRevision, new PartsRecyclerAdapterB.OnItemClickListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB.1
                @Override // com.jingdong.sdk.platform.business.adapter.PartsRecyclerAdapterB.OnItemClickListener
                public void onItemClick(PartsItemEntity partsItemEntity) {
                    JumpHelper.jump(CommonPartsViewHolderB.this.mContext, partsItemEntity.imgJumpUrl, partsItemEntity.imgJumpType);
                    PlatformEventUtils.sendMtaEvent(CommonPartsViewHolderB.this.getBaseData().mManageKey, new MtaParams(partsEntity.buried + "_bpAccessoryFloorItem", CommonPartsViewHolderB.this.getBaseEntity().getMtaParam()));
                }
            });
            this.adapter = partsRecyclerAdapterB;
            refreshableView.setAdapter(partsRecyclerAdapterB);
            this.titleLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Context context = CommonPartsViewHolderB.this.mContext;
                    PartsEntity partsEntity2 = partsEntity;
                    JumpHelper.jump(context, partsEntity2.jumpUrl, partsEntity2.jumpType);
                    PlatformEventUtils.sendMtaEvent(CommonPartsViewHolderB.this.getBaseData().mManageKey, new MtaParams(partsEntity.buried + "_bpAccessoryFloor", CommonPartsViewHolderB.this.getBaseEntity().getMtaParam()));
                }
            });
            this.seekMoreView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB.3
                @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
                public void onRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                    if (((BaseViewHolder) CommonPartsViewHolderB.this).mIsDestroy) {
                        return;
                    }
                    CommonPartsViewHolderB.this.titleLayout.performClick();
                    CommonPartsViewHolderB.this.seekMoreView.onRefreshComplete();
                }
            });
            LinearLayout.LayoutParams layoutParams = this.seekMoreView.getLayoutParams() instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) this.seekMoreView.getLayoutParams() : null;
            if (this.isTenthRevision) {
                this.titleLayout.setPadding(DPIUtil.dip2px(10.0f), DPIUtil.dip2px(16.0f), 0, DPIUtil.dip2px(16.0f));
                if (layoutParams != null) {
                    layoutParams.height = DPIUtil.dip2px(110.0f);
                }
            } else {
                this.titleLayout.setPadding(0, DPIUtil.dip2px(24.0f), 0, DPIUtil.dip2px(20.0f));
                if (layoutParams != null) {
                    layoutParams.height = DPIUtil.dip2px(100.0f);
                }
            }
            if (layoutParams != null) {
                this.seekMoreView.setLayoutParams(layoutParams);
            }
            runOnAttachToWindow(new Runnable() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB.4
                @Override // java.lang.Runnable
                public void run() {
                    PartsEntity partsEntity2;
                    String str2;
                    if (((BaseViewHolder) CommonPartsViewHolderB.this).mIsDestroy || (partsEntity2 = partsEntity) == null || (str2 = partsEntity2.type) == null || !str2.equals("1")) {
                        return;
                    }
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("type", (Object) partsEntity.type);
                    PlatformEventUtils.sendMtaEvent(CommonPartsViewHolderB.this.getBaseData().mManageKey, new MtaParamWithJsonParam(partsEntity.buried + "_GeneralFloorExpo", CommonPartsViewHolderB.this.getBaseEntity().getMtaParam(), jDJSONObject.toString()));
                }
            });
            return;
        }
        hideViewHolder();
    }

    private void handleIcon(PartsEntity partsEntity) {
        LinearLayout.LayoutParams layoutParams;
        if (!TextUtils.isEmpty(partsEntity.tailIcon)) {
            this.tailIcon.setVisibility(0);
            int i2 = partsEntity.tailIconH;
            if (i2 <= 0) {
                i2 = 20;
            }
            partsEntity.tailIconH = i2;
            if (this.tailIcon.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                layoutParams = (LinearLayout.LayoutParams) this.tailIcon.getLayoutParams();
                layoutParams.height = DPIUtil.dip2px(partsEntity.tailIconH / 2);
                if (this.isTenthRevision) {
                    layoutParams.setMargins(DPIUtil.dip2px(5.0f), 0, DPIUtil.dip2px(10.0f), 0);
                } else {
                    layoutParams.setMargins(DPIUtil.dip2px(5.0f), 0, DPIUtil.dip2px(21.0f), 0);
                }
            } else {
                layoutParams = null;
            }
            if (layoutParams != null) {
                this.tailIcon.setLayoutParams(layoutParams);
            }
            JDImageUtils.displayImage(partsEntity.tailIcon, this.tailIcon, (JDDisplayImageOptions) null, new JDSimpleImageLoadingListener() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB.6
                @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    if (((BaseViewHolder) CommonPartsViewHolderB.this).mIsDestroy) {
                        return;
                    }
                    CommonPartsViewHolderB.this.tailIcon.setVisibility(8);
                }
            });
            setImageDark(this.tailIcon, partsEntity.darkModel, ContextCompat.getColor(this.mContext, R.color.pd_color_ececec));
            return;
        }
        this.tailIcon.setVisibility(8);
    }

    private void handleText(PartsEntity partsEntity) {
        this.text1.setMaxWidth(DPIUtil.getWidth(this.mContext) / 2);
        this.text1.setText(partsEntity.text1);
        RelativeLayout.LayoutParams layoutParams = this.text1.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) this.text1.getLayoutParams() : null;
        if (layoutParams != null) {
            layoutParams.setMargins(0, 0, 0, 0);
        }
        this.text1.setTextColor(partsEntity.darkModel ? ContextCompat.getColor(this.mContext, R.color.pd_color_ececec) : PlatformTools.getColorValue(partsEntity.text1C, getResources().getColor(R.color.platform_color_848484)));
        this.text1.setTextSize(1, PlatformTools.getTextSizeValue(partsEntity.text1S, 13));
        BusinessTools.setTextBold(this.text1, partsEntity.text1B, false);
        if (layoutParams != null) {
            this.text1.setLayoutParams(layoutParams);
        }
    }

    private void setImageDark(ImageView imageView, boolean z, @ColorInt int i2) {
        GenericDraweeHierarchy hierarchy;
        if (!(imageView instanceof SimpleDraweeView) || (hierarchy = ((SimpleDraweeView) imageView).getHierarchy()) == null) {
            return;
        }
        hierarchy.setActualImageColorFilter(new PorterDuffColorFilter(i2, z ? PorterDuff.Mode.SRC_IN : PorterDuff.Mode.DST));
    }

    private void setSpace(boolean z) {
        this.space = this.mContext.getResources().getDimensionPixelSize(R.dimen.platform_space_width_6);
        this.mSpaceRight = this.mContext.getResources().getDimensionPixelSize(R.dimen.platform_space_width_8);
        this.mSpaceMarginLeft = this.mContext.getResources().getDimensionPixelSize(R.dimen.platform_space_width_18);
        if (z) {
            this.mSpaceMarginLeft = this.mContext.getResources().getDimensionPixelSize(R.dimen.platform_space_width_10);
        }
        if (this.mDecoration == null) {
            this.mDecoration = new RecyclerView.ItemDecoration() { // from class: com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB.5
                @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                    int itemCount = recyclerView.getAdapter().getItemCount();
                    int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
                    if (childLayoutPosition == 0) {
                        rect.left = CommonPartsViewHolderB.this.mSpaceMarginLeft;
                    } else if (childLayoutPosition == itemCount - 1) {
                        rect.left = CommonPartsViewHolderB.this.space;
                        rect.right = CommonPartsViewHolderB.this.mSpaceRight;
                    } else {
                        rect.left = CommonPartsViewHolderB.this.space;
                    }
                }
            };
        }
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class getViewModelClass() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void initView() {
        this.floorLayout = findViewById(R.id.platform_parts);
        this.mRedView = findViewById(R.id.platform_red_bar);
        this.titleLayout = (LinearLayout) findViewById(R.id.platform_parts_title);
        this.text1 = (TextView) findViewById(R.id.platform_parts_name);
        this.text2 = (TextView) findViewById(R.id.platform_parts_more);
        this.tailIcon = (SimpleDraweeView) findViewById(R.id.platform_parts_tailicon);
        this.seekMoreView = (PullToSeekMoreHorizontalRecyclerView) findViewById(R.id.platform_parts_seekmore_view);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
        this.mLayoutId = R.layout.lib_platform_floor_parts_b;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
        this.adapter = null;
        this.floorLayout = null;
        this.titleLayout = null;
        this.text1 = null;
        this.text2 = null;
        this.tailIcon = null;
        this.seekMoreView = null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseEntity baseEntity) {
        Object obj = baseEntity.mData;
        if (obj != null) {
            PartsEntity partsEntity = (PartsEntity) JDJSON.parseObject(obj.toString(), PartsEntity.class);
            setSpace(partsEntity.tenthRevision);
            bindData2View(partsEntity);
        }
    }
}
