package com.jdjr.risk.identity.verify.api;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import com.jdjr.risk.identity.verify.IdentityVerifySession;

/* loaded from: classes18.dex */
public class IdentityVerifyConfigLoader extends AsyncTaskLoader {
    private IdentityVerifySession a;

    public IdentityVerifyConfigLoader(@NonNull Context context, @NonNull IdentityVerifySession identityVerifySession) {
        super(context);
        this.a = identityVerifySession;
        forceLoad();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public Object loadInBackground() {
        try {
            return IdentityVerifyApi.a(getContext(), this.a);
        } catch (Exception unused) {
            return new Bundle();
        }
    }
}
