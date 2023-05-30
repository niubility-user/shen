package com.jd.lib.productdetail.mainimage.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.GoToBigEntnty;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.callback.OnPdImageListener;
import com.jd.lib.productdetail.mainimage.comment.PdMCommentActivity;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.sdk.platform.floor.isv.ICommonBasicAbility;
import java.util.HashMap;

/* loaded from: classes15.dex */
public class PdMainImageView extends FrameLayout {
    private LifecycleOwner lifecycleOwner;
    private UnBottomDialog mLayerDialog;
    private PdMainImagePresenter mainImagePresenter;
    private PdMainImageViewPage mainImageView;
    public OnPdImageListener onPdImageListener;

    public PdMainImageView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoCommentActivity() {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter == null || pdMainImagePresenter.mainImageData.getValue() == null) {
            return;
        }
        Intent intent = new Intent(getContext(), PdMCommentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("skuId", this.mainImagePresenter.getMainImageParams().skuId);
        if (this.mainImagePresenter.mainImageData.getValue().extMap != null) {
            bundle.putString("isShadowSku", this.mainImagePresenter.mainImageData.getValue().extMap.isShadowSku);
        }
        bundle.putString("categroy", this.mainImagePresenter.getCategoryId(0) + ";" + this.mainImagePresenter.getCategoryId(1) + ";" + this.mainImagePresenter.getCategoryId(2));
        if (this.mainImagePresenter.mainImageData.getValue().extMap != null) {
            bundle.putString("isXnzt", this.mainImagePresenter.mainImageData.getValue().extMap.isXnzt);
        }
        intent.putExtras(bundle);
        PdMainImagePresenter pdMainImagePresenter2 = this.mainImagePresenter;
        if (pdMainImagePresenter2 != null) {
            pdMainImagePresenter2.jumpToPage.setValue(Boolean.TRUE);
        }
        getContext().startActivity(intent);
    }

    private void initJdBottomDialog() {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            if (pdMainImagePresenter.mLayerDialog == null) {
                pdMainImagePresenter.mLayerDialog = new UnBottomDialog(getContext());
            }
            this.mainImagePresenter.mLayerDialog.setButtonColor(0);
            this.mainImagePresenter.mLayerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageView.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
            this.mainImagePresenter.mLayerDialog.mTitleContentLayout.setBackgroundResource(R.color.lib_pd_image_white);
            this.mainImagePresenter.mLayerDialog.setTitleTextColor(getContext().getResources().getColor(R.color.lib_pd_image_common_black));
            PdMainImagePresenter pdMainImagePresenter2 = this.mainImagePresenter;
            pdMainImagePresenter2.mLayerDialog.setDarkMode(pdMainImagePresenter2.getMainImageParams().isDark);
        }
    }

    private void initView(Context context) {
        PdMainImagePresenter pdMainImagePresenter;
        PdMainImageViewPage pdMainImageViewPage = (PdMainImageViewPage) LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_root, this).findViewById(R.id.lib_pd_view_newmainimage);
        this.mainImageView = pdMainImageViewPage;
        this.mainImagePresenter = pdMainImageViewPage.getMainImagePresenter();
        initJdBottomDialog();
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(((BaseActivity) context).getWindow().getDecorView());
        this.lifecycleOwner = lifecycleOwner;
        if (lifecycleOwner == null || (pdMainImagePresenter = this.mainImagePresenter) == null) {
            return;
        }
        pdMainImagePresenter.viewCallBackMutableLiveData.observe(lifecycleOwner, new Observer<PdMImageEventEntity>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(PdMImageEventEntity pdMImageEventEntity) {
                OnPdImageListener onPdImageListener;
                if (pdMImageEventEntity != null) {
                    PdImageEventCode pdImageEventCode = pdMImageEventEntity.pdImageEventCodeCode;
                    if (pdImageEventCode == PdImageEventCode.JUMPTODETAIL) {
                        OnPdImageListener onPdImageListener2 = PdMainImageView.this.onPdImageListener;
                        if (onPdImageListener2 != null) {
                            onPdImageListener2.onJumpToDetail();
                        }
                    } else if (pdImageEventCode == PdImageEventCode.OPEN_COMMENTPAGE) {
                        if (PdMainImageView.this.mainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                            PdMainImageView.this.gotoCommentActivity();
                            return;
                        }
                        OnPdImageListener onPdImageListener3 = PdMainImageView.this.onPdImageListener;
                        if (onPdImageListener3 != null) {
                            onPdImageListener3.onOpenCommentPage((Bundle) pdMImageEventEntity.mObject);
                        }
                    } else if (pdImageEventCode == PdImageEventCode.FRESH_CART_COUNT) {
                        OnPdImageListener onPdImageListener4 = PdMainImageView.this.onPdImageListener;
                        if (onPdImageListener4 != null) {
                            onPdImageListener4.addCart(pdMImageEventEntity.mObject);
                        }
                    } else if (pdImageEventCode != PdImageEventCode.INTERCEPT_KEYBACK || (onPdImageListener = PdMainImageView.this.onPdImageListener) == null) {
                    } else {
                        Object obj = pdMImageEventEntity.mObject;
                        if (obj instanceof Boolean) {
                            onPdImageListener.isInterceptKeyBack(((Boolean) obj).booleanValue());
                        }
                    }
                }
            }
        });
    }

    public void eventReceive(PdImageEventCode pdImageEventCode, Object obj) {
        PdMImageEventEntity pdMImageEventEntity = new PdMImageEventEntity(pdImageEventCode, obj);
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.viewReceiveLiveData.setValue(pdMImageEventEntity);
            if (TextUtils.equals(DYConstants.DY_TRUE, JDMobileConfig.getInstance().getConfig("JDProductdetail", "isUseChangeScreenCode", "enable", DYConstants.DY_TRUE)) && pdImageEventCode == PdImageEventCode.EVENT_CHANGE_SCREEN) {
                PdMainImagePresenter pdMainImagePresenter2 = this.mainImagePresenter;
                PdImageFromType pdImageFromType = pdMainImagePresenter2.imageFromType;
                if (pdImageFromType == PdImageFromType.PRODUCTDETAIL) {
                    pdMainImagePresenter2.appImageWidth = PDUtils.getAppWidth((Activity) getContext());
                } else if (pdImageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                    pdMainImagePresenter2.appImageWidth = PDUtils.getAppWidth((Activity) getContext()) - PDUtils.dip2px((Activity) getContext(), 20.0f);
                }
                PdMainImageViewPage pdMainImageViewPage = this.mainImageView;
                if (pdMainImageViewPage != null) {
                    pdMainImageViewPage.reSetImageLayout();
                }
            }
        }
    }

    public PdMainImagePresenter getMainImagePresenter() {
        return this.mainImagePresenter;
    }

    public ImageView getMiniView() {
        return this.mainImageView.getMiniView();
    }

    public void goToBig(int i2) {
        GoToBigEntnty goToBigEntnty = new GoToBigEntnty();
        goToBigEntnty.position = i2;
        goToBigEntnty.fromBigImage = false;
        goToBigEntnty.autoPlay = false;
        goToBigEntnty.pureMode = true;
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.toBigEntntyMutableLiveData.setValue(goToBigEntnty);
        }
    }

    public void handlerEvent(PDViewEvent pDViewEvent) {
        this.mainImagePresenter.pDViewEvent.setValue(pDViewEvent);
    }

    public void onPendingMtaInfo(String str, String str2, HashMap<String, String> hashMap, String str3) {
        PdMainImageViewPage pdMainImageViewPage = this.mainImageView;
        if (pdMainImageViewPage != null) {
            pdMainImageViewPage.onPendingMtaInfo(str, str2, hashMap, str3);
        }
    }

    public void setComeType(PdImageFromType pdImageFromType) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.imageFromType = pdImageFromType;
            if (pdImageFromType == PdImageFromType.PRODUCTDETAIL) {
                pdMainImagePresenter.appImageWidth = PDUtils.getAppWidth((Activity) getContext());
            } else if (pdImageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                pdMainImagePresenter.appImageWidth = PDUtils.getAppWidth((Activity) getContext()) - PDUtils.dip2px((Activity) getContext(), 20.0f);
            }
            this.mainImageView.requestLayoutByFrom();
        }
    }

    public void setISVConfig(String str, ICommonBasicAbility iCommonBasicAbility) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.getMainImageParams().moduleName = str;
            this.mainImagePresenter.iCommonBasicAbility = iCommonBasicAbility;
        }
    }

    public void setMainData(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mainImageData.setValue(wareBusinessUnitMainImageEntity);
        }
    }

    public void setMainParams(PdMainImageParams pdMainImageParams) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mainImageParams = pdMainImageParams;
        }
    }

    public void setOnPdImageListener(OnPdImageListener onPdImageListener) {
        this.onPdImageListener = onPdImageListener;
    }

    public void setPdCommentInfo(PdCommentInfo pdCommentInfo) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.pdCommentInfo.postValue(pdCommentInfo);
        }
    }

    public void setPdMainImageCommonEntity(Bundle bundle) {
        if (this.mainImagePresenter != null) {
            this.mainImagePresenter.pdCommentInfo.postValue((PdCommentInfo) bundle.getParcelable("pdCommentInfo"));
        }
    }

    public void setmTopCoverViewId(int i2) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mTopCoverViewId.setValue(Integer.valueOf(i2));
        }
    }

    public PdMainImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }
}
