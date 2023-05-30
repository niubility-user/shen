package com.jd.viewkit.helper;

import com.jd.viewkit.JDViewKit;
import com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.templates.container.JDViewKitMultiTabView;
import com.jd.viewkit.templates.container.JDViewKitTopNavView;
import com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdviewkitvirtualanchornavview.JDViewKitVirtualAnchorIndex;
import com.jd.viewkit.templates.model.jdviewkitvirtualanchornavview.JDViewKitVirtualAnchorNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualtopnavview.JDViewKitVirtualTopNavJumpParams;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.DateTool;
import com.jd.viewkit.tool.JSONTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitFloorModel {
    private List<JDViewKitVirtualAnchorIndex> anchorIndexList;
    private Map<String, Object> dataSourceMap;
    private List<JDViewKitDataSourceModel> dataSourceModelList;
    private JDViewKitDataSourcesManager dataSourcesManager;
    protected Map<String, JDViewKitVirtualView> dslMap;
    private JDViewKitFloorAcrossListener floorAcrossListener;
    private String floorId;
    private boolean isForceUpdate;
    private JDViewKitVirtualView rootVirtualView;
    private List<JDViewKitVirtualTopNavJumpParams> topNavJumpParamsList;
    private JDViewKitViewListener viewListener;
    private JDViewKitVirtualViewManager virtualViewManager;
    private Long timeStame = DateTool.getTime();
    private int p_firstPos = -1;
    private Map<Integer, List<JDViewKitFloorModel>> subListMap = null;
    public int floorModelState = 0;
    public int refreshType = 0;

    public JDViewKitFloorModel(String str, Map<String, Object> map, List<JDViewKitDataSourceModel> list, JDViewKitVirtualView jDViewKitVirtualView) {
        this.floorId = str;
        this.dataSourceMap = map;
        this.dataSourceModelList = list;
        this.rootVirtualView = jDViewKitVirtualView;
    }

    public static JDViewKitFloorModel getFollrFloorModel(String str, Map<String, Object> map, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, JDViewKitDataSourcesManager jDViewKitDataSourcesManager, JDViewKitVirtualViewManager jDViewKitVirtualViewManager, Long l2) {
        JDViewKitFloorModel jDViewKitFloorModel;
        JDViewKitVirtualView dslRoot2Virtaul = jDViewKitVirtualViewManager.dslRoot2Virtaul(str, jDViewKitVirtualServiceModel);
        if (dslRoot2Virtaul == null || dslRoot2Virtaul.getType() == null) {
            return null;
        }
        List<JDViewKitDataSourceModel> dslData2Model = jDViewKitDataSourcesManager.dslData2Model(map, dslRoot2Virtaul, l2);
        String floorId = JDViewKit.getFloorId(dslRoot2Virtaul, dslData2Model);
        jDViewKitVirtualViewManager.saveRootVirtaul(floorId, dslRoot2Virtaul);
        if (dslRoot2Virtaul.getType().equals(JDViewKitVirtualView.viewTypeBottomNav)) {
            jDViewKitFloorModel = new JDViewKitBottomNavFloorModel(floorId, map, dslData2Model, dslRoot2Virtaul);
        } else if (dslRoot2Virtaul.getType().equals(JDViewKitVirtualView.viewTypeTopNav)) {
            jDViewKitFloorModel = new JDViewKitTopNavFloorModel(floorId, map, dslData2Model, dslRoot2Virtaul);
        } else {
            jDViewKitFloorModel = new JDViewKitFloorModel(floorId, map, dslData2Model, dslRoot2Virtaul);
        }
        jDViewKitFloorModel.setDslMap(jDViewKitVirtualViewManager.getDslsMap()).setVirtualViewManager(jDViewKitVirtualViewManager).setDataSourcesManager(jDViewKitDataSourcesManager);
        jDViewKitFloorModel.timeStame = l2;
        return jDViewKitFloorModel;
    }

    private List<JDViewKitFloorModel> getSubFloorList() {
        JDViewKitVirtualView jDViewKitVirtualView = this.rootVirtualView;
        if (jDViewKitVirtualView == null || jDViewKitVirtualView.getType() == null) {
            return null;
        }
        if (this.rootVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultiTab) || this.rootVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultiPlusTab)) {
            this.refreshType = 2;
            int selectSubFloorList = JDViewKitMultiTabView.getSelectSubFloorList(this.dataSourceModelList, this.dslMap);
            if (selectSubFloorList != -1) {
                if (this.subListMap == null) {
                    this.subListMap = new HashMap();
                }
                List<JDViewKitFloorModel> list = this.subListMap.get(Integer.valueOf(selectSubFloorList)) != null ? this.subListMap.get(Integer.valueOf(selectSubFloorList)) : null;
                if (list == null || (list != null && list.size() == 1 && list.get(0).floorModelState == 1)) {
                    List<Object> subFloorList = JDViewKitMultiTabView.getSubFloorList(this, this.dslMap);
                    if (subFloorList == null || subFloorList.size() == 0) {
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < subFloorList.size(); i2++) {
                        Map map = (Map) subFloorList.get(i2);
                        JDViewKitFloorModel follrFloorModel = getFollrFloorModel(JSONTool.getJsonString((Map) map.get("dslRoot")), (Map) map.get("dslData"), this.rootVirtualView.getServiceModel(), this.dataSourcesManager, this.virtualViewManager, this.timeStame);
                        follrFloorModel.setViewListener(this.viewListener);
                        follrFloorModel.setFloorAcrossListener(this.floorAcrossListener);
                        if (map.get("needRefresh") != null && map.get("needRefresh").toString().equals("Y")) {
                            follrFloorModel.floorModelState = 1;
                            follrFloorModel.refreshType = 1;
                        }
                        arrayList.add(follrFloorModel);
                    }
                    this.subListMap.put(Integer.valueOf(selectSubFloorList), arrayList);
                    return arrayList;
                }
                return list;
            }
            return null;
        }
        return null;
    }

    public JDViewKitFloorModel get(int i2) {
        int i3;
        List<JDViewKitFloorModel> subFloorList = getSubFloorList();
        if (subFloorList == null || i2 <= 0 || subFloorList.size() <= i2 - 1) {
            return this;
        }
        JDViewKitFloorModel jDViewKitFloorModel = subFloorList.get(i3);
        jDViewKitFloorModel.setViewListener(this.viewListener);
        jDViewKitFloorModel.setFloorAcrossListener(this.floorAcrossListener);
        return jDViewKitFloorModel;
    }

    public List<JDViewKitVirtualAnchorIndex> getAnchorIndex() {
        JDViewKitVirtualView jDViewKitVirtualView = this.rootVirtualView;
        if (jDViewKitVirtualView == null || jDViewKitVirtualView.getType() == null || !this.rootVirtualView.getType().equals(JDViewKitVirtualView.viewTypeAnchorNav)) {
            return null;
        }
        List<JDViewKitVirtualAnchorIndex> list = this.anchorIndexList;
        if (list != null) {
            return list;
        }
        List<JDViewKitVirtualAnchorIndex> achorIndex = JDViewKitVirtualAnchorNavView.getAchorIndex(this.dataSourceModelList);
        this.anchorIndexList = achorIndex;
        return achorIndex;
    }

    public int getCount() {
        List<JDViewKitFloorModel> subFloorList = getSubFloorList();
        if (subFloorList != null) {
            return subFloorList.size() + 1;
        }
        return 1;
    }

    public Map<String, Object> getDataSourceMap() {
        return this.dataSourceMap;
    }

    public List<JDViewKitDataSourceModel> getDataSourceModelList() {
        return this.dataSourceModelList;
    }

    public int getFirstPos() {
        return this.p_firstPos;
    }

    public JDViewKitFloorAcrossListener getFloorAcrossListener() {
        return this.floorAcrossListener;
    }

    public String getFloorId() {
        return this.floorId;
    }

    public int getHeigh() {
        JDViewKitVirtualView jDViewKitVirtualView = this.rootVirtualView;
        if (jDViewKitVirtualView != null) {
            return jDViewKitVirtualView.getHeigh();
        }
        return 0;
    }

    public JDViewKitVirtualView getRootVirtualView() {
        return this.rootVirtualView;
    }

    public List<JDViewKitVirtualTopNavJumpParams> getTopNavJumpParamsList() {
        JDViewKitVirtualView jDViewKitVirtualView = this.rootVirtualView;
        if (jDViewKitVirtualView == null || jDViewKitVirtualView.getType() == null || !this.rootVirtualView.getType().equals(JDViewKitVirtualView.viewTypeTopNav)) {
            return null;
        }
        List<JDViewKitVirtualTopNavJumpParams> list = this.topNavJumpParamsList;
        if (list != null) {
            return list;
        }
        List<JDViewKitVirtualTopNavJumpParams> topNavJumpParams = JDViewKitTopNavView.getTopNavJumpParams(this.dataSourceModelList, this.dslMap);
        this.topNavJumpParamsList = topNavJumpParams;
        return topNavJumpParams;
    }

    public String getType() {
        JDViewKitVirtualView jDViewKitVirtualView = this.rootVirtualView;
        if (jDViewKitVirtualView != null) {
            return jDViewKitVirtualView.getType();
        }
        return null;
    }

    public JDViewKitViewListener getViewListener() {
        return this.viewListener;
    }

    public boolean isForceUpdate() {
        return this.isForceUpdate;
    }

    public JDViewKitFloorModel setDataSourcesManager(JDViewKitDataSourcesManager jDViewKitDataSourcesManager) {
        this.dataSourcesManager = jDViewKitDataSourcesManager;
        return this;
    }

    public JDViewKitFloorModel setDslMap(Map<String, JDViewKitVirtualView> map) {
        this.dslMap = map;
        return this;
    }

    public void setFirstPos(int i2) {
        this.p_firstPos = i2;
    }

    public void setFloorAcrossListener(JDViewKitFloorAcrossListener jDViewKitFloorAcrossListener) {
        this.floorAcrossListener = jDViewKitFloorAcrossListener;
        if (getDataSourceModelList() == null || getDataSourceModelList().size() <= 0) {
            return;
        }
        Iterator<JDViewKitDataSourceModel> it = getDataSourceModelList().iterator();
        while (it.hasNext()) {
            it.next().setFloorAcrossListener(jDViewKitFloorAcrossListener);
        }
    }

    public void setForceUpdate(boolean z) {
        this.isForceUpdate = z;
    }

    public void setViewListener(JDViewKitViewListener jDViewKitViewListener) {
        this.viewListener = jDViewKitViewListener;
        if (getDataSourceModelList() == null || getDataSourceModelList().size() <= 0) {
            return;
        }
        Iterator<JDViewKitDataSourceModel> it = getDataSourceModelList().iterator();
        while (it.hasNext()) {
            it.next().setViewListener(jDViewKitViewListener);
        }
    }

    public JDViewKitFloorModel setVirtualViewManager(JDViewKitVirtualViewManager jDViewKitVirtualViewManager) {
        this.virtualViewManager = jDViewKitVirtualViewManager;
        return this;
    }
}
