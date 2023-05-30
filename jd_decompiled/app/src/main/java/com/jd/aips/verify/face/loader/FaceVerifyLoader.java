package com.jd.aips.verify.face.loader;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import com.jd.aips.verify.face.FaceVerifySession;
import com.jd.aips.verify.face.api.FaceVerifyApi;

/* loaded from: classes12.dex */
public class FaceVerifyLoader extends AsyncTaskLoader {
    private FaceVerifySession session;

    public FaceVerifyLoader(Context context, FaceVerifySession faceVerifySession) {
        super(context);
        this.session = faceVerifySession;
        forceLoad();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    @Nullable
    public Bundle loadInBackground() {
        try {
            return FaceVerifyApi.requestVerify(getContext(), this.session);
        } catch (Exception unused) {
            return new Bundle();
        }
    }
}
