package com.xiaomi.push;

import java.net.UnknownHostException;

/* loaded from: classes11.dex */
final class x4 {

    /* loaded from: classes11.dex */
    static class a {
        r4 a;
        String b;

        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a a(Exception exc) {
        b(exc);
        boolean z = exc instanceof a6;
        Exception exc2 = exc;
        if (z) {
            a6 a6Var = (a6) exc;
            exc2 = exc;
            if (a6Var.a() != null) {
                exc2 = a6Var.a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        String str = exc2.getClass().getSimpleName() + ":" + message;
        int a2 = q5.a(exc2);
        if (a2 != 0) {
            aVar.a = r4.a(r4.GSLB_REQUEST_SUCCESS.a() + a2);
        }
        if (aVar.a == null) {
            aVar.a = r4.GSLB_TCP_ERR_OTHER;
        }
        if (aVar.a == r4.GSLB_TCP_ERR_OTHER) {
            aVar.b = str;
        }
        return aVar;
    }

    private static void b(Exception exc) {
        exc.getClass();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a c(Exception exc) {
        r4 r4Var;
        r4 r4Var2;
        Throwable cause;
        b(exc);
        boolean z = exc instanceof a6;
        Exception exc2 = exc;
        if (z) {
            a6 a6Var = (a6) exc;
            exc2 = exc;
            if (a6Var.a() != null) {
                exc2 = a6Var.a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        int a2 = q5.a(exc2);
        String str = exc2.getClass().getSimpleName() + ":" + message;
        if (a2 != 0) {
            r4 a3 = r4.a(r4.CONN_SUCCESS.a() + a2);
            aVar.a = a3;
            if (a3 == r4.CONN_BOSH_ERR && (cause = exc2.getCause()) != null && (cause instanceof UnknownHostException)) {
                r4Var = r4.CONN_BOSH_UNKNOWNHOST;
            }
            r4Var2 = aVar.a;
            if (r4Var2 != r4.CONN_TCP_ERR_OTHER || r4Var2 == r4.CONN_XMPP_ERR || r4Var2 == r4.CONN_BOSH_ERR) {
                aVar.b = str;
            }
            return aVar;
        }
        r4Var = r4.CONN_XMPP_ERR;
        aVar.a = r4Var;
        r4Var2 = aVar.a;
        if (r4Var2 != r4.CONN_TCP_ERR_OTHER) {
        }
        aVar.b = str;
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a d(Exception exc) {
        r4 r4Var;
        r4 r4Var2;
        b(exc);
        boolean z = exc instanceof a6;
        Exception exc2 = exc;
        if (z) {
            a6 a6Var = (a6) exc;
            exc2 = exc;
            if (a6Var.a() != null) {
                exc2 = a6Var.a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        if (exc2.getCause() != null) {
            message = exc2.getCause().getMessage();
        }
        int a2 = q5.a(exc2);
        String str = exc2.getClass().getSimpleName() + ":" + message;
        if (a2 == 105) {
            r4Var = r4.BIND_TCP_READ_TIMEOUT;
        } else if (a2 == 199) {
            r4Var = r4.BIND_TCP_ERR;
        } else if (a2 == 499) {
            aVar.a = r4.BIND_BOSH_ERR;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                r4Var = r4.BIND_BOSH_ITEM_NOT_FOUND;
            }
            r4Var2 = aVar.a;
            if (r4Var2 != r4.BIND_TCP_ERR || r4Var2 == r4.BIND_XMPP_ERR || r4Var2 == r4.BIND_BOSH_ERR) {
                aVar.b = str;
            }
            return aVar;
        } else {
            r4Var = a2 != 109 ? a2 != 110 ? r4.BIND_XMPP_ERR : r4.BIND_TCP_BROKEN_PIPE : r4.BIND_TCP_CONNRESET;
        }
        aVar.a = r4Var;
        r4Var2 = aVar.a;
        if (r4Var2 != r4.BIND_TCP_ERR) {
        }
        aVar.b = str;
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static a e(Exception exc) {
        r4 r4Var;
        r4 r4Var2;
        b(exc);
        boolean z = exc instanceof a6;
        Exception exc2 = exc;
        if (z) {
            a6 a6Var = (a6) exc;
            exc2 = exc;
            if (a6Var.a() != null) {
                exc2 = a6Var.a();
            }
        }
        a aVar = new a();
        String message = exc2.getMessage();
        int a2 = q5.a(exc2);
        String str = exc2.getClass().getSimpleName() + ":" + message;
        if (a2 == 105) {
            r4Var = r4.CHANNEL_TCP_READTIMEOUT;
        } else if (a2 == 199) {
            r4Var = r4.CHANNEL_TCP_ERR;
        } else if (a2 == 499) {
            aVar.a = r4.CHANNEL_BOSH_EXCEPTION;
            if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                r4Var = r4.CHANNEL_BOSH_ITEMNOTFIND;
            }
            r4Var2 = aVar.a;
            if (r4Var2 != r4.CHANNEL_TCP_ERR || r4Var2 == r4.CHANNEL_XMPPEXCEPTION || r4Var2 == r4.CHANNEL_BOSH_EXCEPTION) {
                aVar.b = str;
            }
            return aVar;
        } else {
            r4Var = a2 != 109 ? a2 != 110 ? r4.CHANNEL_XMPPEXCEPTION : r4.CHANNEL_TCP_BROKEN_PIPE : r4.CHANNEL_TCP_CONNRESET;
        }
        aVar.a = r4Var;
        r4Var2 = aVar.a;
        if (r4Var2 != r4.CHANNEL_TCP_ERR) {
        }
        aVar.b = str;
        return aVar;
    }
}
