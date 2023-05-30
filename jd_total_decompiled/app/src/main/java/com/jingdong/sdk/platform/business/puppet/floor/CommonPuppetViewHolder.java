package com.jingdong.sdk.platform.business.puppet.floor;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager;
import com.jingdong.sdk.lib.puppetlayout.ylayout.android.YogaLayout;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.business.puppet.JDPuppetHandler;

/* loaded from: classes10.dex */
public class CommonPuppetViewHolder extends BaseViewHolder {
    public CommonPuppetViewHolder(Context context, BaseData baseData, String str, ViewGroup viewGroup) {
        super(context, baseData, str, viewGroup);
    }

    private ViewGroup createTemplateView() {
        String str;
        int lastIndexOf;
        PuppetManager puppetManager = PuppetManager.getInstance();
        String str2 = null;
        if (this.mId.startsWith("bpConfig_")) {
            String str3 = this.mId;
            String substring = str3.substring(9, str3.length());
            if (!TextUtils.isEmpty(substring) && (lastIndexOf = substring.lastIndexOf(CartConstant.KEY_YB_INFO_LINK)) != -1 && lastIndexOf < substring.length() - 1) {
                str2 = substring.substring(0, lastIndexOf);
                str = substring.substring(lastIndexOf + 1, substring.length());
                if (TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                    return puppetManager.createTemplateView(this.mContext, str2, str);
                }
                return new YogaLayout(getContext());
            }
        }
        str = null;
        if (TextUtils.isEmpty(str2)) {
        }
        return new YogaLayout(getContext());
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
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected ViewGroup onCreatedView(ViewGroup viewGroup) {
        ViewGroup createTemplateView = createTemplateView();
        if (createTemplateView != null) {
            PuppetManager.getInstance().bindViewAction(createTemplateView, new JDPuppetHandler(getContext()));
        }
        return createTemplateView;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseEntity baseEntity) {
        Object obj = baseEntity.mData;
        if (obj != null && this.mRootView != null) {
            if (obj instanceof String) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("data", (Object) JDJSON.parseObject((String) baseEntity.mData));
                PuppetManager.getInstance().bindViewData(this.mRootView, jDJSONObject);
            } else if (obj instanceof JDJSONObject) {
                JDJSONObject jDJSONObject2 = new JDJSONObject();
                jDJSONObject2.put("data", baseEntity.mData);
                PuppetManager.getInstance().bindViewData(this.mRootView, jDJSONObject2);
            }
        }
        runOnAttachToWindow(new Runnable() { // from class: com.jingdong.sdk.platform.business.puppet.floor.CommonPuppetViewHolder.1
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseViewHolder) CommonPuppetViewHolder.this).mIsDestroy || ((BaseViewHolder) CommonPuppetViewHolder.this).mRootView == null) {
                    return;
                }
                PuppetManager.getInstance().expo(((BaseViewHolder) CommonPuppetViewHolder.this).mRootView);
            }
        });
    }
}
