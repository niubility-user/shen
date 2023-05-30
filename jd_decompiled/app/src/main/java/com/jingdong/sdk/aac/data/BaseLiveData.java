package com.jingdong.sdk.aac.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/* loaded from: classes7.dex */
public class BaseLiveData<T> extends MutableLiveData<T> {
    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // androidx.lifecycle.LiveData
    @Nullable
    public T getValue() {
        return (T) super.getValue();
    }

    @Override // androidx.lifecycle.LiveData
    public boolean hasActiveObservers() {
        return super.hasActiveObservers();
    }

    @Override // androidx.lifecycle.LiveData
    public boolean hasObservers() {
        return super.hasObservers();
    }

    @Override // androidx.lifecycle.LiveData
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull Observer<? super T> observer) {
        super.observe(lifecycleOwner, observer);
    }

    @Override // androidx.lifecycle.LiveData
    public void observeForever(@NonNull Observer<? super T> observer) {
        super.observeForever(observer);
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void postValue(T t) {
        if (t != null && (t instanceof IValue)) {
            ((IValue) t).setLiveData(this);
        }
        super.postValue(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void removeObserver(@NonNull Observer<? super T> observer) {
        super.removeObserver(observer);
    }

    @Override // androidx.lifecycle.LiveData
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner) {
        super.removeObservers(lifecycleOwner);
    }

    @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
    public void setValue(T t) {
        if (t != null && (t instanceof IValue)) {
            ((IValue) t).setLiveData(this);
        }
        super.setValue(t);
    }
}
