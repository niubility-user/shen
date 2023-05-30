package com.huawei.hms.common.internal;

import java.util.ArrayList;
import java.util.ListIterator;

/* loaded from: classes12.dex */
public class BindResolveClients {
    private static final Object b = new Object();
    private ArrayList<ResolveClientBean> a;

    /* loaded from: classes12.dex */
    private static class b {
        private static final BindResolveClients a = new BindResolveClients();
    }

    public static BindResolveClients getInstance() {
        return b.a;
    }

    public boolean isClientRegistered(ResolveClientBean resolveClientBean) {
        boolean contains;
        synchronized (b) {
            contains = this.a.contains(resolveClientBean);
        }
        return contains;
    }

    public void notifyClientReconnect() {
        synchronized (b) {
            ListIterator<ResolveClientBean> listIterator = this.a.listIterator();
            while (listIterator.hasNext()) {
                listIterator.next().clientReconnect();
            }
            this.a.clear();
        }
    }

    public void register(ResolveClientBean resolveClientBean) {
        if (resolveClientBean == null) {
            return;
        }
        synchronized (b) {
            if (!this.a.contains(resolveClientBean)) {
                this.a.add(resolveClientBean);
            }
        }
    }

    public void unRegister(ResolveClientBean resolveClientBean) {
        if (resolveClientBean == null) {
            return;
        }
        synchronized (b) {
            if (this.a.contains(resolveClientBean)) {
                ListIterator<ResolveClientBean> listIterator = this.a.listIterator();
                while (true) {
                    if (!listIterator.hasNext()) {
                        break;
                    } else if (resolveClientBean.equals(listIterator.next())) {
                        listIterator.remove();
                        break;
                    }
                }
            }
        }
    }

    public void unRegisterAll() {
        synchronized (b) {
            this.a.clear();
        }
    }

    private BindResolveClients() {
        this.a = new ArrayList<>();
    }
}
