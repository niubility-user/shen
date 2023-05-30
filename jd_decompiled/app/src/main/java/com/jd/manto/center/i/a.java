package com.jd.manto.center.i;

import android.text.TextUtils;
import com.jd.manto.center.MantoDiscoveryFragment;
import com.jd.manto.center.e.b;
import com.jd.manto.center.model.entity.MantoDiscoveryBean;
import com.jd.manto.center.model.entity.MantoDiscoveryEntity;
import com.jd.manto.center.model.state.ActivityDataState;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.cleanmvp.presenter.BasePresenter;
import com.jingdong.common.frame.IMyActivity;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a extends BasePresenter<com.jd.manto.center.j.a> {
    private MantoDiscoveryFragment b;
    private ActivityDataState d;
    ArrayList<MantoDiscoveryBean> a = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private b f6545c = new b(new HttpGroupUtil());

    public a(MantoDiscoveryFragment mantoDiscoveryFragment) {
        this.b = mantoDiscoveryFragment;
        ActivityDataState activityDataState = new ActivityDataState();
        this.d = activityDataState;
        this.f6545c.e(activityDataState);
    }

    private void c(MantoDiscoveryEntity mantoDiscoveryEntity) {
        if (mantoDiscoveryEntity != null) {
            if (com.jd.manto.center.k.b.c(mantoDiscoveryEntity.banner)) {
                MantoDiscoveryBean mantoDiscoveryBean = new MantoDiscoveryBean();
                mantoDiscoveryBean.type = 0;
                mantoDiscoveryBean.banner = mantoDiscoveryEntity.banner;
                this.a.add(mantoDiscoveryBean);
            }
            if (!TextUtils.isEmpty(mantoDiscoveryEntity.recommendText)) {
                MantoDiscoveryBean mantoDiscoveryBean2 = new MantoDiscoveryBean();
                mantoDiscoveryBean2.type = 2;
                mantoDiscoveryBean2.recommendText = mantoDiscoveryEntity.recommendText;
                this.a.add(mantoDiscoveryBean2);
            }
            if (com.jd.manto.center.k.b.c(mantoDiscoveryEntity.recommend)) {
                Iterator<MantoDiscoveryBean> it = mantoDiscoveryEntity.recommend.iterator();
                while (it.hasNext()) {
                    it.next().type = 1;
                }
                this.a.addAll(mantoDiscoveryEntity.recommend);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public com.jd.manto.center.j.a createNullObject() {
        return null;
    }

    public void b() {
        if (this.f6545c == null || this.b.getActivity() == null || !(this.b.getActivity() instanceof IMyActivity)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        this.f6545c.d((IMyActivity) this.b.getActivity(), jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public void onAttach(com.jd.manto.center.j.a aVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public void onDetach(com.jd.manto.center.j.a aVar) {
        b bVar = this.f6545c;
        if (bVar != null) {
            bVar.a();
        }
        ActivityDataState activityDataState = this.d;
        if (activityDataState != null) {
            activityDataState.clearState(-1);
        }
        if (this.f6545c != null) {
            this.f6545c = null;
        }
    }

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public void onEvent(BaseEvent baseEvent) {
    }

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    public void onEventMainThread(BaseEvent baseEvent) {
        if (getUI() == null) {
            return;
        }
        String type = baseEvent.getType();
        type.hashCode();
        if (!type.equals("MANTO_DISCOVERY_SUCCESS")) {
            if (type.equals("MANTO_DISCOVERY_FAILURE")) {
                getUI().i();
                return;
            }
            return;
        }
        this.a.clear();
        c(this.d.getMantoDiscoveryEntity());
        if (com.jd.manto.center.k.b.c(this.a)) {
            getUI().p(this.a);
        } else {
            getUI().showEmptyView();
        }
    }

    @Override // com.jingdong.cleanmvp.presenter.BasePresenter
    protected void onSuspend() {
    }
}
