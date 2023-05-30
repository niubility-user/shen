package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import com.jingdong.app.mall.bundle.PageComponents.list.footer.FooterEntity;
import com.jingdong.app.mall.bundle.PageComponents.list.footer.States;
import com.jingdong.app.mall.bundle.PageComponents.list.net.BaseGeneralData;
import com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.BaseOriginalEntity;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.List;

/* loaded from: classes19.dex */
public class FirstDataProcessing<OriginalEntity extends BaseOriginalEntity, GeneralData extends BaseGeneralData> extends IDataProcessing<OriginalEntity, GeneralData> {
    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void addFooter(GeneralData generaldata, List list) {
        List<IFloorEntity> list2 = generaldata.generalList;
        FooterEntity footerEntity = generaldata.footerEntity;
        footerEntity.currentState = States.NOTHING;
        list2.remove(footerEntity);
        list2.add(footerEntity);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void addLocalList(GeneralData generaldata, List list) {
        generaldata.generalList.addAll(list);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void before(HttpSetting httpSetting, BaseGeneralData baseGeneralData) {
        httpSetting.putJsonParam("page", 1);
        httpSetting.setEffect(1);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public boolean judgeListExp(GeneralData generaldata, List list) {
        if (list == null) {
            onFail(generaldata, States.ERROR);
            return true;
        } else if (list.size() == 0) {
            onFail(generaldata, States.NODATA);
            return true;
        } else {
            return false;
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void onFail(GeneralData generaldata, States states) {
        if (states == null) {
            states = States.ERROR;
        }
        addFooter(generaldata, null);
        generaldata.footerEntity.currentState = states;
        this.iFinalData.onFail(this.changeInfo);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void onSuccess(OriginalEntity originalentity, GeneralData generaldata) {
        List list = originalentity.getList();
        if (judgeListExp(generaldata, list)) {
            return;
        }
        generaldata.generalList.clear();
        addLocalList(generaldata, list);
        addFooter(generaldata, list);
        generaldata.page = 2;
        this.iFinalData.onSuccess(this.changeInfo);
    }
}
