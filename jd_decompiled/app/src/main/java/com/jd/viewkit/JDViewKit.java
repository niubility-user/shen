package com.jd.viewkit;

import android.content.Context;
import com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager;
import com.jd.viewkit.dataSources.manager.impl.JDViewKitDataSourcesManagerImpl;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitFloorModel;
import com.jd.viewkit.helper.JDViewKitPageRefreshModel;
import com.jd.viewkit.helper.JDViewKitToastListener;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.manager.JDViewKitViewManager;
import com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager;
import com.jd.viewkit.templates.manager.impl.JDViewKitViewManagerImpl;
import com.jd.viewkit.templates.manager.impl.JDViewKitVirtualViewManagerJsonImpl;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.thirdinterface.JDViewKitEventService;
import com.jd.viewkit.thirdinterface.JDViewKitImageService;
import com.jd.viewkit.thirdinterface.JDViewKitMtaService;
import com.jd.viewkit.thirdinterface.JDViewKitVideoService;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitParamsModel;
import com.jd.viewkit.tool.DateTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/* loaded from: classes18.dex */
public class JDViewKit {
    private JDViewKitDataSourcesManager dataSourcesManager;
    private JDViewKitEventService eventService;
    private JDViewKitImageService imageService;
    private JDViewKitMtaService mtaService;
    private JDViewKitRefreshListener refreshListener;
    private JDViewKitVirtualServiceModel serviceModel;
    private JDViewKitVideoService videoService;
    private JDViewKitViewManager viewManager;
    private JDViewKitVirtualViewManager virtualViewManager;

    /* loaded from: classes18.dex */
    public interface JDViewKitRefreshListener {
        void pageRefresh();

        void pageRefresh(JDViewKitPageRefreshModel jDViewKitPageRefreshModel, JDViewKitEventCallBack jDViewKitEventCallBack);
    }

    public JDViewKit(int i2, JDViewKitEventService jDViewKitEventService, JDViewKitImageService jDViewKitImageService, JDViewKitMtaService jDViewKitMtaService, JDViewKitParamsModel jDViewKitParamsModel) {
        JDViewKitDataSourcesManagerImpl jDViewKitDataSourcesManagerImpl = new JDViewKitDataSourcesManagerImpl();
        this.dataSourcesManager = jDViewKitDataSourcesManagerImpl;
        jDViewKitDataSourcesManagerImpl.setParamsModel(jDViewKitParamsModel);
        this.virtualViewManager = new JDViewKitVirtualViewManagerJsonImpl();
        this.viewManager = new JDViewKitViewManagerImpl();
        setScreenWidth(i2);
        this.eventService = jDViewKitEventService;
        this.imageService = jDViewKitImageService;
        this.mtaService = jDViewKitMtaService;
        this.serviceModel = new JDViewKitVirtualServiceModel(jDViewKitEventService, jDViewKitImageService, jDViewKitMtaService, this.dataSourcesManager, this.virtualViewManager, this.viewManager);
    }

    public static String getFloorId(JDViewKitVirtualView jDViewKitVirtualView, List<JDViewKitDataSourceModel> list) {
        if (jDViewKitVirtualView == null) {
            return "vk";
        }
        String str = "vk" + jDViewKitVirtualView.getDslId();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (list != null) {
            for (JDViewKitDataSourceModel jDViewKitDataSourceModel : list) {
                if (!StringTool.isEmpty(jDViewKitDataSourceModel.getDslId())) {
                    linkedHashSet.add(jDViewKitDataSourceModel.getDslId());
                }
            }
        }
        Iterator it = linkedHashSet.iterator();
        StringBuffer stringBuffer = new StringBuffer(str);
        if (StringTool.isNotEmpty(jDViewKitVirtualView.getValueRefer())) {
            stringBuffer.append(jDViewKitVirtualView.getValueRefer());
        }
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return StringTool.encrypt(stringBuffer.toString());
    }

    public static String getVersion() {
        return "1.0.0";
    }

    public JDViewKitDataSourcesManager getDataSourcesManager() {
        return this.dataSourcesManager;
    }

    public JDViewKitEventService getEventService() {
        return this.eventService;
    }

    public JDViewKitFloorModel getFollrModelByDsl(String str, String str2) {
        return JDViewKitFloorModel.getFollrFloorModel(str, this.dataSourcesManager.dslData2Map(str2), this.serviceModel, this.dataSourcesManager, this.virtualViewManager, DateTool.getTime());
    }

    public JDViewKitImageService getImageService() {
        return this.imageService;
    }

    public JDViewKitMtaService getMtaService() {
        return this.mtaService;
    }

    public JDViewKitBaseLayout getRootLayoutByFloorId(Context context, String str) {
        JDViewKitVirtualView rootVirtaul = this.virtualViewManager.getRootVirtaul(str);
        JDViewKitBaseLayout rootViewByVirtualView = JDViewKitViewFactory.getRootViewByVirtualView(context, rootVirtaul, new JDViewKitChannelModel());
        if (rootViewByVirtualView.getChannelModel() != null) {
            rootViewByVirtualView.getChannelModel().setRootView(rootViewByVirtualView);
        }
        rootViewByVirtualView.setDslsMap(this.virtualViewManager.getDslsMap());
        rootViewByVirtualView.setVirtualView(rootVirtaul);
        return rootViewByVirtualView;
    }

    public int getScreenWidth() {
        return GlobalManage.getInstance().getScreenWidth();
    }

    public JDViewKitVideoService getVideoService() {
        return this.videoService;
    }

    public JDViewKitViewManager getViewManager() {
        return this.viewManager;
    }

    public JDViewKitVirtualViewManager getVirtualViewManager() {
        return this.virtualViewManager;
    }

    public void setDataSourcesManager(JDViewKitDataSourcesManager jDViewKitDataSourcesManager) {
        this.dataSourcesManager = jDViewKitDataSourcesManager;
    }

    public void setDslMapByStr(String str) {
        this.virtualViewManager.dslMap2Virtual(str, this.serviceModel);
    }

    public void setRefreshListener(JDViewKitRefreshListener jDViewKitRefreshListener) {
        this.refreshListener = jDViewKitRefreshListener;
        JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel = this.serviceModel;
        if (jDViewKitVirtualServiceModel != null) {
            jDViewKitVirtualServiceModel.setRefreshListener(jDViewKitRefreshListener);
        }
    }

    public void setScreenWidth(int i2) {
        GlobalManage.getInstance().setScreenWidth(i2);
    }

    public void setToastListener(JDViewKitToastListener jDViewKitToastListener) {
        JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel = this.serviceModel;
        if (jDViewKitVirtualServiceModel != null) {
            jDViewKitVirtualServiceModel.setToastListener(jDViewKitToastListener);
        }
    }

    public void setVideoService(JDViewKitVideoService jDViewKitVideoService) {
        this.videoService = jDViewKitVideoService;
        GlobalManage.getInstance().setVideoService(jDViewKitVideoService);
    }

    public void setViewManager(JDViewKitViewManager jDViewKitViewManager) {
        this.viewManager = jDViewKitViewManager;
    }

    public void setVirtualViewManager(JDViewKitVirtualViewManager jDViewKitVirtualViewManager) {
        this.virtualViewManager = jDViewKitVirtualViewManager;
    }
}
