package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import com.jingdong.app.mall.bundle.PageComponents.list.footer.FooterEntity;
import com.jingdong.app.mall.bundle.PageComponents.list.footer.States;
import com.jingdong.app.mall.bundle.PageComponents.list.net.BaseGeneralData;
import com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.BaseOriginalEntity;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.DataChangeInfo;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.List;

/* loaded from: classes19.dex */
public class BottomIncreaseDataProcessing<OriginalEntity extends BaseOriginalEntity, GeneralData extends BaseGeneralData> extends IDataProcessing<OriginalEntity, GeneralData> {
    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void addFooter(GeneralData generaldata, List list) {
        List<IFloorEntity> list2 = generaldata.generalList;
        FooterEntity footerEntity = generaldata.footerEntity;
        footerEntity.currentState = this.changeInfo.isEnd ? States.PAGING_END : States.PAGING_LOADING;
        list2.remove(footerEntity);
        list2.add(footerEntity);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void addLocalList(GeneralData generaldata, List list) {
        generaldata.generalList.addAll(list);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void before(HttpSetting httpSetting, BaseGeneralData baseGeneralData) {
        httpSetting.putJsonParam("page", Integer.valueOf(baseGeneralData.page));
        httpSetting.setEffect(0);
    }

    protected void checkEnd(List list) {
        this.changeInfo.isEnd = list.size() == 0;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public boolean judgeListExp(GeneralData generaldata, List list) {
        if (list == null) {
            onFail(generaldata, States.PAGING_ERROR);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void onFail(GeneralData generaldata, States states) {
        if (states == null) {
            states = States.PAGING_ERROR;
        }
        generaldata.footerEntity.currentState = states;
        DataChangeInfo dataChangeInfo = this.changeInfo;
        int size = generaldata.generalList.size();
        dataChangeInfo.changedSize = size;
        dataChangeInfo.beforeSize = size;
        this.iFinalData.onFail(this.changeInfo);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing
    public void onSuccess(OriginalEntity originalentity, GeneralData generaldata) {
        List list = originalentity.getList();
        if (judgeListExp(generaldata, list)) {
            return;
        }
        List<IFloorEntity> list2 = generaldata.generalList;
        this.changeInfo.beforeSize = list2.size();
        addLocalList(generaldata, list);
        checkEnd(list);
        DataChangeInfo dataChangeInfo = this.changeInfo;
        if (dataChangeInfo.isEnd) {
            dataChangeInfo.removeOddIndex = removeOddEntity(generaldata);
        }
        addFooter(generaldata, list);
        generaldata.page++;
        this.changeInfo.changedSize = list2.size();
        this.iFinalData.onSuccess(this.changeInfo);
    }
}
