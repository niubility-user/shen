package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager;

/* loaded from: classes19.dex */
public class Footer extends BaseFooter {
    public Footer(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(IListManager iListManager, View view) {
        if (iListManager != null) {
            iListManager.actionDistanceBottom();
        }
    }

    public void bind(FooterEntity footerEntity) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            States states = footerEntity.currentState;
            layoutParams.height = states == States.ERROR || states == States.NODATA ? -1 : -2;
        }
        States states2 = footerEntity.currentState;
        changeState(states2);
        State state = this.stateMap.get(states2);
        if (state != null) {
            state.bind(footerEntity);
        }
    }

    public Footer(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.stateMap.put(States.PAGING_LOADING, new PagingLoadingState(this));
        this.stateMap.put(States.PAGING_ERROR, new PagingErrorState(this));
        this.stateMap.put(States.PAGING_END, new PagingEndState(this));
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.footer.BaseFooter
    public Footer setClick(final IListManager iListManager) {
        super.setClick(iListManager);
        this.stateMap.get(States.PAGING_ERROR).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.footer.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Footer.a(IListManager.this, view);
            }
        });
        return this;
    }
}
