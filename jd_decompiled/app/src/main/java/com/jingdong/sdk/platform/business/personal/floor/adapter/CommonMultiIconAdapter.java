package com.jingdong.sdk.platform.business.personal.floor.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.platform.business.personal.entity.CommonMultiIconEntity;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import com.jingdong.sdk.platform.mta.MtaParams;
import com.jingdong.sdk.platform.utils.PlatformEventUtils;
import com.jingdong.sdk.platform.widget.BaseViewHolder;
import com.jingdong.sdk.platform.widget.IconMoreItem;
import com.jingdong.sdk.platform.widget.SimpleAdapter;
import java.lang.ref.WeakReference;

/* loaded from: classes10.dex */
public class CommonMultiIconAdapter extends SimpleAdapter<CommonMultiIconEntity.IconElement, MultiIconViewHolder> implements BaseViewHolder.ViewHolderClickListener {
    private final BaseActivity activity;
    private final LayoutInflater layoutInflater;
    private final String manageKey;
    private SparseArray<WeakReference<MultiIconViewHolder>> viewHolderSparseArray;

    /* loaded from: classes10.dex */
    public static class MultiIconViewHolder extends BaseViewHolder {
        @BindView(R2.id.icon_more_item)
        IconMoreItem moreItem;

        public MultiIconViewHolder(View view, BaseViewHolder.ViewHolderClickListener viewHolderClickListener) {
            super(view, viewHolderClickListener);
        }

        public void bindData(CommonMultiIconEntity.IconElement iconElement) {
            IconMoreItem iconMoreItem = this.moreItem;
            if (iconMoreItem == null || iconElement == null) {
                return;
            }
            iconMoreItem.bindData(iconElement);
        }
    }

    /* loaded from: classes10.dex */
    public class MultiIconViewHolder_ViewBinding implements Unbinder {
        private MultiIconViewHolder target;

        @UiThread
        public MultiIconViewHolder_ViewBinding(MultiIconViewHolder multiIconViewHolder, View view) {
            this.target = multiIconViewHolder;
            multiIconViewHolder.moreItem = (IconMoreItem) Utils.findRequiredViewAsType(view, R.id.icon_more_item, "field 'moreItem'", IconMoreItem.class);
        }

        @Override // butterknife.Unbinder
        @CallSuper
        public void unbind() {
            MultiIconViewHolder multiIconViewHolder = this.target;
            if (multiIconViewHolder != null) {
                this.target = null;
                multiIconViewHolder.moreItem = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CommonMultiIconAdapter(BaseActivity baseActivity, RecyclerView recyclerView, LayoutInflater layoutInflater, String str, SparseArray<WeakReference<MultiIconViewHolder>> sparseArray) {
        super(recyclerView);
        this.layoutInflater = layoutInflater;
        this.activity = baseActivity;
        this.manageKey = str;
        this.viewHolderSparseArray = sparseArray;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2 + hashCode();
    }

    @Override // com.jingdong.sdk.platform.widget.BaseViewHolder.ViewHolderClickListener
    public void onClick(View view, int i2) {
        final CommonMultiIconEntity.IconElement iconElement = models().get(i2);
        if (iconElement != null) {
            if (iconElement.needLogin == 1) {
                LoginUserHelper.getInstance().executeLoginRunnable(this.activity, new Runnable() { // from class: com.jingdong.sdk.platform.business.personal.floor.adapter.CommonMultiIconAdapter.1
                    {
                        CommonMultiIconAdapter.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        BaseActivity baseActivity = CommonMultiIconAdapter.this.activity;
                        CommonMultiIconEntity.IconElement iconElement2 = iconElement;
                        JumpHelper.jump(baseActivity, iconElement2.mUrl, iconElement2.jumpType);
                    }
                }, "", false);
            } else {
                JumpHelper.jump(this.activity, iconElement.mUrl, iconElement.jumpType);
            }
            CommonMultiIconEntity.IconElement.MaiDian maiDian = iconElement.maiDian;
            if (maiDian != null) {
                PlatformEventUtils.sendMtaEvent(this.manageKey, new MtaParams(maiDian.eventId, maiDian.eventParam, null, "1"));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MultiIconViewHolder multiIconViewHolder, int i2) {
        if (multiIconViewHolder != null) {
            multiIconViewHolder.bindData(models().get(i2));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MultiIconViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        SparseArray<WeakReference<MultiIconViewHolder>> sparseArray = this.viewHolderSparseArray;
        if (sparseArray != null) {
            WeakReference<MultiIconViewHolder> weakReference = sparseArray.get(i2);
            r2 = weakReference != null ? weakReference.get() : null;
            if (OKLog.D) {
                OKLog.d("DaCuFloor", "getViewHolder form Cache" + r2 + "viewType " + i2);
            }
        }
        if (r2 == null) {
            r2 = new MultiIconViewHolder(this.layoutInflater.inflate(R.layout.layout_common_multi_icon_item, viewGroup, false), this);
            SparseArray<WeakReference<MultiIconViewHolder>> sparseArray2 = this.viewHolderSparseArray;
            if (sparseArray2 != null) {
                sparseArray2.put(i2, new WeakReference<>(r2));
                if (OKLog.D) {
                    OKLog.d("DaCuFloor", "putViewHolder to Cache" + r2 + "  " + i2);
                }
            }
        }
        return r2;
    }
}
