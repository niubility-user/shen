package com.jdpay.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import com.jdpay.lib.converter.Converter;
import com.jdpay.lib.util.Utils;
import com.jdpay.net.http.HttpProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes18.dex */
public class ServiceFactory {
    private Provider proxyProvider;
    private Provider rawProvider;
    private final ProviderInvocationHandler providerInvocationhandler = new ProviderInvocationHandler();
    private final SparseArrayCompat<Class<? extends Converter>> requestContervers = new SparseArrayCompat<>(1);
    private final SparseArrayCompat<Class<? extends Converter>> responseContervers = new SparseArrayCompat<>(1);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public final class ProviderInvocationHandler implements InvocationHandler {
        private ProviderInvocationHandler() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            return method.invoke(ServiceFactory.this.rawProvider, objArr);
        }
    }

    /* loaded from: classes18.dex */
    private static final class ServiceInvocationHandler implements InvocationHandler {
        final Class<? extends RequestAdapter> clzAdapter;
        final ServiceFactory factory;

        public ServiceInvocationHandler(@NonNull Class<? extends RequestAdapter> cls, @NonNull ServiceFactory serviceFactory) {
            this.clzAdapter = cls;
            this.factory = serviceFactory;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            return this.clzAdapter.newInstance().build(method, objArr, this.factory);
        }
    }

    public ServiceFactory(@NonNull Provider provider) {
        setProvider(provider);
    }

    public static <T> T create(@NonNull Class<T> cls, @NonNull Class<? extends RequestAdapter> cls2, @NonNull ServiceFactory serviceFactory) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ServiceInvocationHandler(cls2, serviceFactory));
    }

    public synchronized ServiceFactory addRequestConverter(int i2, Class<? extends Converter> cls) {
        synchronized (this.requestContervers) {
            this.requestContervers.put(i2, cls);
        }
        return this;
    }

    public synchronized ServiceFactory addResponseConverter(int i2, Class<? extends Converter> cls) {
        synchronized (this.responseContervers) {
            this.responseContervers.put(i2, cls);
        }
        return this;
    }

    public synchronized ServiceFactory clearRequestConverter() {
        synchronized (this.requestContervers) {
            this.requestContervers.clear();
        }
        return this;
    }

    public synchronized ServiceFactory clearResponseConverter() {
        synchronized (this.responseContervers) {
            this.responseContervers.clear();
        }
        return this;
    }

    public synchronized Provider getProvider() {
        return this.proxyProvider;
    }

    public Class<? extends Converter> getRequestConverter(int i2) {
        Class<? extends Converter> cls;
        synchronized (this.requestContervers) {
            cls = this.requestContervers.get(i2);
        }
        return cls;
    }

    public Class<? extends Converter> getResponseConverter(int i2) {
        Class<? extends Converter> cls;
        synchronized (this.responseContervers) {
            cls = this.responseContervers.get(i2);
        }
        return cls;
    }

    public synchronized ServiceFactory removeRequestConverter(int i2) {
        synchronized (this.requestContervers) {
            this.requestContervers.remove(i2);
        }
        return this;
    }

    public synchronized ServiceFactory removeResponseConverter(int i2) {
        synchronized (this.responseContervers) {
            this.responseContervers.remove(i2);
        }
        return this;
    }

    public synchronized void setProvider(@Nullable Provider provider) {
        if (Utils.isSameClass(this.rawProvider, provider)) {
            return;
        }
        this.rawProvider = provider;
        if (provider != null) {
            this.proxyProvider = (Provider) Proxy.newProxyInstance(provider.getClass().getClassLoader(), new Class[]{HttpProvider.class}, this.providerInvocationhandler);
        } else {
            this.proxyProvider = null;
        }
    }
}
