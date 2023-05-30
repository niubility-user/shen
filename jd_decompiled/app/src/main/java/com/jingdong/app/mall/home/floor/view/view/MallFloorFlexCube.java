package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.flexcube.help.IExpoInterface;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IClickEvent;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.h.a;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.widget.CountDownLayout;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.r.f.a.l;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class MallFloorFlexCube extends BaseMallFloor<l> {
    private final f flexSize;
    private View flexView;
    private CountDownLayout mCountDownLayout;
    private final BabelScope mScope;

    public MallFloorFlexCube(Context context) {
        super(context);
        BabelScope babelScope = new BabelScope();
        this.mScope = babelScope;
        this.flexSize = new f(-1, -1);
        babelScope.register(IClickEvent.class, new IClickEvent() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorFlexCube.1
            @Override // com.jd.lib.flexcube.iwidget.ui.IClickEvent
            public void onClick(View view, ClickEvent clickEvent) {
                JDJSONObject jDJSONObject;
                JumpEntity jumpEntity = null;
                try {
                    jDJSONObject = JDJSON.parseObject(clickEvent.customParams);
                    try {
                        jumpEntity = (JumpEntity) JDJSON.parseObject(jDJSONObject.optString("jump"), JumpEntity.class);
                    } catch (Exception e2) {
                        e = e2;
                        com.jingdong.app.mall.home.o.a.f.s0(this, e);
                        MallFloorFlexCube.this.execJump(jumpEntity, jDJSONObject);
                    }
                } catch (Exception e3) {
                    e = e3;
                    jDJSONObject = null;
                }
                MallFloorFlexCube.this.execJump(jumpEntity, jDJSONObject);
            }
        });
        babelScope.register(IExpoInterface.class, new IExpoInterface() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorFlexCube.2
            @Override // com.jd.lib.flexcube.help.IExpoInterface
            public void sendExposureData(JSONObject jSONObject) {
                MallFloorFlexCube.this.addExpoObj(jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addExpoObj(JSONObject jSONObject) {
        if (jSONObject == null || ((l) this.mPresenter).j()) {
            return;
        }
        String optString = jSONObject.optString("eventId");
        ((l) this.mPresenter).b0(optString);
        if (!TextUtils.isEmpty(optString) && !optString.startsWith("Home_FloorIDExpo")) {
            ((l) this.mPresenter).P(jSONObject);
        } else {
            ((l) this.mPresenter).Q(jSONObject);
        }
    }

    private void checkExpo() {
        View view = this.flexView;
        if (view instanceof MsgInterface) {
            ((MsgInterface) view).pushAction(MsgActionInterface.class, new MsgActionInterface<JSONObject>() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorFlexCube.3
                @Override // com.jd.lib.flexcube.help.MsgActionInterface
                public void handleAction(List<JSONObject> list) {
                    if (list == null || list.size() <= 0) {
                        return;
                    }
                    Iterator<JSONObject> it = list.iterator();
                    while (it.hasNext()) {
                        MallFloorFlexCube.this.addExpoObj(it.next());
                    }
                }
            });
        }
    }

    private void checkTimeLayout() {
        if (!((l) this.mPresenter).X()) {
            c.k(true, this.mCountDownLayout);
            return;
        }
        if (this.mCountDownLayout == null) {
            CountDownLayout countDownLayout = new CountDownLayout(this.mContext);
            this.mCountDownLayout = countDownLayout;
            m.a(this, countDownLayout);
        }
        this.mCountDownLayout.g(((l) this.mPresenter).V());
        this.mCountDownLayout.h(((l) this.mPresenter).W(), ((l) this.mPresenter).U());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execJump(JumpEntity jumpEntity, JDJSONObject jDJSONObject) {
        if (jumpEntity == null) {
            return;
        }
        a.c(this, jumpEntity);
        com.jingdong.app.mall.home.floor.common.i.l.e(this.mContext, jumpEntity);
        String optString = jDJSONObject.optString("eventId");
        if (!TextUtils.isEmpty(optString) && !TextUtils.equals(optString, com.jingdong.app.mall.home.floor.common.i.l.b)) {
            com.jingdong.app.mall.home.r.c.a.t(optString, "", jumpEntity.getSrvJson(), RecommendMtaUtils.Home_PageId);
        } else {
            com.jingdong.app.mall.home.floor.common.i.l.onClickJsonEvent(this.mContext, jumpEntity, "", jumpEntity.getSrv(), jumpEntity.getSrvJson());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        ((l) this.mPresenter).a0();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeRefresh() {
        super.onHomeRefresh();
        ((l) this.mPresenter).a0();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeResume(MallFloorEvent mallFloorEvent) {
        super.onHomeResume(mallFloorEvent);
        ((l) this.mPresenter).Y(isFloorDisplay());
        checkExpo();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
        checkExpo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        if (this.flexView == null) {
            View S = ((l) this.mPresenter).S();
            this.flexView = S;
            RelativeLayout.LayoutParams u = this.flexSize.u(S);
            u.addRule(14);
            this.flexView.setLayoutParams(u);
            m.b(this, this.flexView, 0);
        }
        View view = this.flexView;
        if (view instanceof IFloorView) {
            ((IFloorView) view).update(this.mScope, (BabelScope) ((l) this.mPresenter).R(), 0);
        }
        ((l) this.mPresenter).Y(true);
        checkTimeLayout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public l createPresenter() {
        return new l();
    }
}
