package com.jingdong.sdk.aac.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.jingdong.sdk.aac.model.BaseViewModel;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class MyFactory extends ViewModelProvider.NewInstanceFactory {
    private String managerKey;

    public MyFactory(String str) {
        this.managerKey = str;
    }

    @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> cls) {
        if (BaseViewModel.class.isAssignableFrom(cls)) {
            try {
                Constructor<T> constructor = cls.getConstructor(String.class);
                constructor.setAccessible(true);
                return constructor.newInstance(this.managerKey);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + cls, e2);
            } catch (InstantiationException e3) {
                throw new RuntimeException("Cannot create an instance of " + cls, e3);
            } catch (NoSuchMethodException e4) {
                throw new RuntimeException("Cannot create an instance of " + cls, e4);
            } catch (InvocationTargetException e5) {
                throw new RuntimeException("Cannot create an instance of " + cls, e5);
            }
        }
        return (T) super.create(cls);
    }
}
