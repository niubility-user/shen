package com.jd.viewkit.templates.model.model;

import com.jd.viewkit.JDViewKit;
import com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager;
import com.jd.viewkit.helper.JDViewKitToastListener;
import com.jd.viewkit.templates.manager.JDViewKitViewManager;
import com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager;
import com.jd.viewkit.thirdinterface.JDViewKitEventService;
import com.jd.viewkit.thirdinterface.JDViewKitImageService;
import com.jd.viewkit.thirdinterface.JDViewKitMtaService;

/* loaded from: classes18.dex */
public class JDViewKitVirtualServiceModel {
    private JDViewKitDataSourcesManager dataSourcesManager;
    private JDViewKitEventService eventService;
    private JDViewKitImageService imageService;
    private JDViewKitMtaService mtaService;
    private JDViewKit.JDViewKitRefreshListener refreshListener;
    private JDViewKitToastListener toastListener;
    private JDViewKitViewManager viewManager;
    private JDViewKitVirtualViewManager virtualViewManager;

    public JDViewKitVirtualServiceModel(JDViewKitEventService jDViewKitEventService, JDViewKitImageService jDViewKitImageService, JDViewKitMtaService jDViewKitMtaService, JDViewKitDataSourcesManager jDViewKitDataSourcesManager, JDViewKitVirtualViewManager jDViewKitVirtualViewManager, JDViewKitViewManager jDViewKitViewManager) {
        this.eventService = jDViewKitEventService;
        this.imageService = jDViewKitImageService;
        this.mtaService = jDViewKitMtaService;
        this.dataSourcesManager = jDViewKitDataSourcesManager;
        this.virtualViewManager = jDViewKitVirtualViewManager;
        this.viewManager = jDViewKitViewManager;
    }

    public JDViewKitDataSourcesManager getDataSourcesManager() {
        return this.dataSourcesManager;
    }

    public JDViewKitEventService getEventService() {
        return this.eventService;
    }

    public JDViewKitImageService getImageService() {
        return this.imageService;
    }

    public JDViewKitMtaService getMtaService() {
        return this.mtaService;
    }

    public JDViewKit.JDViewKitRefreshListener getRefreshListener() {
        return this.refreshListener;
    }

    public JDViewKitToastListener getToastListener() {
        return this.toastListener;
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

    public void setEventService(JDViewKitEventService jDViewKitEventService) {
        this.eventService = jDViewKitEventService;
    }

    public void setImageService(JDViewKitImageService jDViewKitImageService) {
        this.imageService = jDViewKitImageService;
    }

    public void setMtaService(JDViewKitMtaService jDViewKitMtaService) {
        this.mtaService = jDViewKitMtaService;
    }

    public void setRefreshListener(JDViewKit.JDViewKitRefreshListener jDViewKitRefreshListener) {
        this.refreshListener = jDViewKitRefreshListener;
    }

    public void setToastListener(JDViewKitToastListener jDViewKitToastListener) {
        this.toastListener = jDViewKitToastListener;
    }

    public void setViewManager(JDViewKitViewManager jDViewKitViewManager) {
        this.viewManager = jDViewKitViewManager;
    }

    public void setVirtualViewManager(JDViewKitVirtualViewManager jDViewKitVirtualViewManager) {
        this.virtualViewManager = jDViewKitVirtualViewManager;
    }

    public JDViewKitVirtualServiceModel(JDViewKitEventService jDViewKitEventService, JDViewKitImageService jDViewKitImageService, JDViewKitMtaService jDViewKitMtaService, JDViewKit.JDViewKitRefreshListener jDViewKitRefreshListener, JDViewKitDataSourcesManager jDViewKitDataSourcesManager, JDViewKitVirtualViewManager jDViewKitVirtualViewManager, JDViewKitViewManager jDViewKitViewManager) {
        this.eventService = jDViewKitEventService;
        this.imageService = jDViewKitImageService;
        this.mtaService = jDViewKitMtaService;
        this.refreshListener = jDViewKitRefreshListener;
        this.dataSourcesManager = jDViewKitDataSourcesManager;
        this.virtualViewManager = jDViewKitVirtualViewManager;
        this.viewManager = jDViewKitViewManager;
    }
}
