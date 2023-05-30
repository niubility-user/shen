package com.vivo.identifier;

import android.database.ContentObserver;

/* loaded from: classes11.dex */
public class IdentifierIdObserver extends ContentObserver {
    private static final String TAG = "VMS_SDK_Observer";
    private String mAppId;
    private IdentifierIdClient mIdentifierIdClient;
    private int mType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IdentifierIdObserver(IdentifierIdClient identifierIdClient, int i2, String str) {
        super(null);
        this.mIdentifierIdClient = identifierIdClient;
        this.mType = i2;
        this.mAppId = str;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        IdentifierIdClient identifierIdClient = this.mIdentifierIdClient;
        if (identifierIdClient != null) {
            identifierIdClient.sendMessageToDataBase(this.mType, this.mAppId);
        }
    }
}
