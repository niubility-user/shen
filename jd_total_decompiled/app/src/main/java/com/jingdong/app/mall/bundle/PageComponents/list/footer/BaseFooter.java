package com.jingdong.app.mall.bundle.PageComponents.list.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes19.dex */
public class BaseFooter extends LinearLayout {
    Map<States, State> stateMap;

    public BaseFooter(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(IListManager iListManager, View view) {
        if (iListManager != null) {
            iListManager.firstFetch();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void b(IListManager iListManager, View view) {
        if (iListManager != null) {
            iListManager.firstFetch();
        }
    }

    public void changeState(States states) {
        State state = this.stateMap.get(states);
        if (state != null) {
            removeAllViews();
            addView(state.getView());
        }
    }

    public BaseFooter setClick(final IListManager iListManager) {
        this.stateMap.get(States.NODATA).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.footer.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseFooter.a(IListManager.this, view);
            }
        });
        this.stateMap.get(States.ERROR).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.footer.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseFooter.b(IListManager.this, view);
            }
        });
        return this;
    }

    public BaseFooter setCustomState(States states, State state) {
        this.stateMap.put(states, state);
        return this;
    }

    public BaseFooter(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        HashMap hashMap = new HashMap();
        this.stateMap = hashMap;
        hashMap.put(States.NODATA, new NoDataState(this));
        this.stateMap.put(States.ERROR, new ErrorState(this));
        Map<States, State> map = this.stateMap;
        States states = States.NOTHING;
        map.put(states, new NothingState(this));
        changeState(states);
    }
}
