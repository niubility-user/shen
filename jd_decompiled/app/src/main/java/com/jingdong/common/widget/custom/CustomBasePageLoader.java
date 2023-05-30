package com.jingdong.common.widget.custom;

import com.jingdong.common.widget.custom.CustomListFooterView;

/* loaded from: classes12.dex */
public abstract class CustomBasePageLoader {
    public CustomListFooterView footerView;
    public int page = 1;
    public String offSet = "-1";

    public CustomBasePageLoader() {
    }

    public void checkTooLess(int i2) {
        if (2 != this.page || i2 <= 0 || i2 >= 4) {
            return;
        }
        showNextPage();
    }

    public abstract void onRefresh();

    public void onScroll() {
    }

    public abstract void showNextPage();

    public abstract void showPageOne();

    public CustomBasePageLoader(CustomListFooterView customListFooterView) {
        this.footerView = customListFooterView;
        customListFooterView.setRetryListener(new CustomListFooterView.RetryListener() { // from class: com.jingdong.common.widget.custom.CustomBasePageLoader.1
            @Override // com.jingdong.common.widget.custom.CustomListFooterView.RetryListener
            public void emptyRetry() {
                CustomBasePageLoader.this.showPageOne();
            }

            @Override // com.jingdong.common.widget.custom.CustomListFooterView.RetryListener
            public void retry() {
                CustomBasePageLoader.this.showNextPage();
            }
        });
    }
}
