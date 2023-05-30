package com.jingdong.common.listui;

/* loaded from: classes5.dex */
public class WrapBundle {
    public String event_id;
    public String event_param;
    public boolean isLastPosition;
    private ItemHook itemHook;
    public String pageName;
    public String page_param;

    /* loaded from: classes5.dex */
    public interface ItemHook {
        boolean itemHook();
    }

    public static WrapBundle build() {
        return new WrapBundle();
    }

    public String getEventId() {
        String str = this.event_id;
        return str == null ? "" : str;
    }

    public String getPageName() {
        String str = this.pageName;
        return str == null ? "" : str;
    }

    public boolean itemClickHook() {
        ItemHook itemHook = this.itemHook;
        if (itemHook == null) {
            return false;
        }
        return itemHook.itemHook();
    }

    public WrapBundle setEvent_id(String str) {
        this.event_id = str;
        return this;
    }

    public WrapBundle setEvent_param(String str) {
        this.event_param = str;
        return this;
    }

    public WrapBundle setItemHook(ItemHook itemHook) {
        this.itemHook = itemHook;
        return this;
    }

    public WrapBundle setLastPosition(boolean z) {
        this.isLastPosition = z;
        return this;
    }

    public WrapBundle setPageName(String str) {
        this.pageName = str;
        return this;
    }

    public WrapBundle setPage_param(String str) {
        this.page_param = str;
        return this;
    }
}
