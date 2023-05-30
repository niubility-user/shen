package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.utils.PDManager;
import de.greenrobot.event.EventBus;

/* loaded from: classes15.dex */
public abstract class PdBaseConstraintLayout extends ConstraintLayout implements PDManager.PDListener {
    protected static final String CLASS_NAME = "com.jd.lib.productdetail.ProductDetailActivity";
    protected boolean isDestory;
    protected Context mContext;
    protected PDManager mDetailManager;
    protected String mManagerKey;
    protected ProductDetailEntity mProduct;

    public PdBaseConstraintLayout(Context context) {
        super(context);
        this.isDestory = false;
        this.mContext = context;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public String getActionName() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public Object getData(String str) {
        return null;
    }

    public EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    public void initParamData(ProductDetailEntity productDetailEntity) {
        this.mProduct = productDetailEntity;
        String str = productDetailEntity.mManageKey;
        this.mManagerKey = str;
        this.mDetailManager = PDManager.getInstances(str);
        if (TextUtils.isEmpty(getActionName())) {
            return;
        }
        this.mDetailManager.registerListener(this);
    }

    protected abstract void initView();

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public boolean isValid() {
        return true;
    }

    public void onDestoryView() {
        this.isDestory = true;
    }

    @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
    public void onEvent(String str, Object obj) {
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    public PdBaseConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isDestory = false;
        this.mContext = context;
    }
}
