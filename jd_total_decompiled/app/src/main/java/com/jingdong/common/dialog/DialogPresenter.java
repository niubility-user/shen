package com.jingdong.common.dialog;

import android.text.TextUtils;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.common.cart.CartBaseUtil;

/* loaded from: classes5.dex */
public class DialogPresenter extends BasePresenter<IDialogView> {
    private final IDialogView dialogView;

    public DialogPresenter(IDialogView iDialogView) {
        this.dialogView = iDialogView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public IDialogView createNullObject() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public void onAttach(IDialogView iDialogView) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public void onDetach(IDialogView iDialogView) {
    }

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public void onEvent(BaseEvent baseEvent) {
    }

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals(CartBaseUtil.EVENT_TYPE_DLG_CLOSE)) {
            String message = baseEvent.getMessage();
            if (TextUtils.isEmpty(message)) {
                return;
            }
            this.dialogView.dismissDialog(message);
        } else if (type.equals(CartBaseUtil.EVENT_TYPE_DLG_TITLE)) {
            String message2 = baseEvent.getMessage();
            if (TextUtils.isEmpty(message2)) {
                return;
            }
            this.dialogView.setDialogTitle(message2);
        }
    }

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    protected void onSuspend() {
    }
}
