package com.vivo.identifier;

import android.content.Context;
import java.util.List;

/* loaded from: classes11.dex */
public class IdentifierManager {
    public static List deleteOAIDBLACK(Context context, List<String> list) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.deleteOAIDBLACK(list);
    }

    public static String getAAID(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getAAID();
    }

    public static String getAAIDNoDelay(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getAAIDNoDelay();
    }

    public static String getGUID(Context context) {
        IdentifierIdClient instanceCore = IdentifierIdClient.getInstanceCore(context);
        if (instanceCore == null) {
            return null;
        }
        return instanceCore.getGUID();
    }

    public static String getGUIDNoDelay(Context context) {
        IdentifierIdClient instanceCore = IdentifierIdClient.getInstanceCore(context);
        if (instanceCore == null) {
            return null;
        }
        return instanceCore.getGUIDNoDelay();
    }

    public static String getOAID(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getOAID();
    }

    public static String getOAIDNoDelay(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getOAIDNoDelay();
    }

    public static String getOAIDStatus(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getOAIDSTATUS();
    }

    public static String getUDID(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getUDID();
    }

    public static String getVAID(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getVAID();
    }

    public static String getVAIDNoDelay(Context context) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return null;
        }
        return identifierIdClient.getVAIDNoDelay();
    }

    public static boolean insertOAIDBLACK(Context context, List<String> list) {
        IdentifierIdClient identifierIdClient = IdentifierIdClient.getInstance(context);
        if (identifierIdClient == null) {
            return false;
        }
        return identifierIdClient.insertOAIDBLACK(list);
    }

    public static boolean isSupported(Context context) {
        if (IdentifierIdClient.getInstance(context) == null) {
            return false;
        }
        return IdentifierIdClient.isSupported();
    }
}
