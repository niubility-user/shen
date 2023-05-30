package com.jingdong.jdexreport.c;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes12.dex */
public class b extends c {
    public b(int i2, int i3, int i4, String str, String str2, boolean z) {
        super(i2, i3, i4, str, str2, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0165 A[Catch: AssertionError -> 0x01c5, Exception -> 0x01cb, TRY_ENTER, TRY_LEAVE, TryCatch #35 {AssertionError -> 0x01c5, Exception -> 0x01cb, blocks: (B:122:0x0165, B:137:0x0182, B:171:0x01c1), top: B:245:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0182 A[Catch: AssertionError -> 0x01c5, Exception -> 0x01cb, TRY_ENTER, TRY_LEAVE, TryCatch #35 {AssertionError -> 0x01c5, Exception -> 0x01cb, blocks: (B:122:0x0165, B:137:0x0182, B:171:0x01c1), top: B:245:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01c1 A[Catch: AssertionError -> 0x01c5, Exception -> 0x01cb, TRY_ENTER, TRY_LEAVE, TryCatch #35 {AssertionError -> 0x01c5, Exception -> 0x01cb, blocks: (B:122:0x0165, B:137:0x0182, B:171:0x01c1), top: B:245:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x01d7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x015e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x017b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x01b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x01d0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:230:0x01ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:234:0x01de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int f() {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        SocketException e2;
        ConnectException e3;
        Throwable th2;
        SocketException socketException;
        ConnectException connectException;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        InputStream inputStream2 = null;
        r2 = null;
        r2 = null;
        inputStream2 = null;
        inputStream2 = null;
        inputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        int i6 = 1;
        try {
            try {
                try {
                    httpURLConnection = a();
                    try {
                        try {
                            httpURLConnection.setConnectTimeout(this.f12576c);
                            httpURLConnection.setDoOutput(true);
                            if (this.f12581i) {
                                httpURLConnection.setDoInput(true);
                            }
                            if (httpURLConnection instanceof HttpsURLConnection) {
                                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new a());
                            }
                            httpURLConnection.setUseCaches(false);
                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setInstanceFollowRedirects(true);
                            httpURLConnection.setReadTimeout(this.b);
                            HashMap<String, String> b = b();
                            if (b != null) {
                                for (Map.Entry<String, String> entry : b.entrySet()) {
                                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                                }
                            }
                            new Date().getTime();
                            try {
                                httpURLConnection.connect();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            } catch (AssertionError e5) {
                                e5.printStackTrace();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (AssertionError e6) {
                                        e6.printStackTrace();
                                    } catch (Exception unused) {
                                    }
                                }
                                return -1;
                            }
                            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                            byte[] bytes = e().getBytes();
                            if (bytes.length != 0) {
                                dataOutputStream.write(bytes);
                            }
                            dataOutputStream.flush();
                            dataOutputStream.close();
                            int responseCode = httpURLConnection.getResponseCode();
                            if (responseCode == 304) {
                                httpURLConnection.disconnect();
                                new Date().getTime();
                                if (httpURLConnection != null) {
                                    try {
                                        httpURLConnection.disconnect();
                                    } catch (AssertionError e7) {
                                        e7.printStackTrace();
                                    } catch (Exception unused2) {
                                    }
                                }
                                return responseCode;
                            } else if (responseCode == 200) {
                                try {
                                    inputStream = httpURLConnection.getInputStream();
                                    try {
                                        byte[] bArr = new byte[1024];
                                        byteArrayOutputStream = new ByteArrayOutputStream(1024);
                                        while (true) {
                                            try {
                                                int read = inputStream.read(bArr);
                                                if (read == -1) {
                                                    break;
                                                }
                                                byteArrayOutputStream.write(bArr, 0, read);
                                            } catch (ConnectException e8) {
                                                e3 = e8;
                                                inputStream2 = inputStream;
                                                e3.printStackTrace();
                                                if (inputStream2 != null) {
                                                }
                                                if (byteArrayOutputStream != null) {
                                                }
                                                if (httpURLConnection != null) {
                                                }
                                                return i6;
                                            } catch (SocketException e9) {
                                                e2 = e9;
                                                inputStream2 = inputStream;
                                                e2.printStackTrace();
                                                if (inputStream2 != null) {
                                                }
                                                if (byteArrayOutputStream != null) {
                                                }
                                                if (httpURLConnection != null) {
                                                }
                                                return i6;
                                            } catch (SocketTimeoutException e10) {
                                                e = e10;
                                                byteArrayOutputStream2 = byteArrayOutputStream;
                                                try {
                                                    e.printStackTrace();
                                                    if (inputStream != null) {
                                                        try {
                                                            inputStream.close();
                                                        } catch (Exception unused3) {
                                                        }
                                                    }
                                                    if (byteArrayOutputStream2 != null) {
                                                        try {
                                                            byteArrayOutputStream2.close();
                                                        } catch (Exception unused4) {
                                                        }
                                                    }
                                                    if (httpURLConnection != null) {
                                                        try {
                                                            httpURLConnection.disconnect();
                                                        } catch (AssertionError e11) {
                                                            e = e11;
                                                            i5 = 1;
                                                            e.printStackTrace();
                                                            return i5;
                                                        } catch (Exception unused5) {
                                                        }
                                                    }
                                                    return 1;
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    byteArrayOutputStream = byteArrayOutputStream2;
                                                    inputStream2 = inputStream;
                                                    if (inputStream2 != null) {
                                                        try {
                                                            inputStream2.close();
                                                        } catch (Exception unused6) {
                                                        }
                                                    }
                                                    if (byteArrayOutputStream != null) {
                                                        try {
                                                            byteArrayOutputStream.close();
                                                        } catch (Exception unused7) {
                                                        }
                                                    }
                                                    if (httpURLConnection != null) {
                                                        try {
                                                            httpURLConnection.disconnect();
                                                        } catch (AssertionError e12) {
                                                            e12.printStackTrace();
                                                        } catch (Exception unused8) {
                                                        }
                                                    }
                                                    throw th;
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                inputStream2 = inputStream;
                                                th.printStackTrace();
                                                if (inputStream2 != null) {
                                                }
                                                if (byteArrayOutputStream != null) {
                                                }
                                                if (httpURLConnection != null) {
                                                }
                                                return i6;
                                            }
                                        }
                                        new Date().getTime();
                                        a(httpURLConnection, byteArrayOutputStream);
                                        if (inputStream != null) {
                                            try {
                                                inputStream.close();
                                            } catch (Exception unused9) {
                                            }
                                        }
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused10) {
                                        }
                                        if (httpURLConnection != null) {
                                            try {
                                                httpURLConnection.disconnect();
                                                return 0;
                                            } catch (AssertionError e13) {
                                                e = e13;
                                                e.printStackTrace();
                                                return i5;
                                            } catch (Exception unused11) {
                                                return 0;
                                            }
                                        }
                                        return 0;
                                    } catch (ConnectException e14) {
                                        connectException = e14;
                                        i4 = 1;
                                        i6 = i4;
                                        e3 = connectException;
                                        byteArrayOutputStream = null;
                                        inputStream2 = inputStream;
                                        e3.printStackTrace();
                                        if (inputStream2 != null) {
                                        }
                                        if (byteArrayOutputStream != null) {
                                        }
                                        if (httpURLConnection != null) {
                                        }
                                        return i6;
                                    } catch (SocketException e15) {
                                        socketException = e15;
                                        i3 = 1;
                                        i6 = i3;
                                        e2 = socketException;
                                        byteArrayOutputStream = null;
                                        inputStream2 = inputStream;
                                        e2.printStackTrace();
                                        if (inputStream2 != null) {
                                        }
                                        if (byteArrayOutputStream != null) {
                                        }
                                        if (httpURLConnection != null) {
                                        }
                                        return i6;
                                    } catch (SocketTimeoutException e16) {
                                        e = e16;
                                    } catch (Throwable th6) {
                                        th2 = th6;
                                        i2 = 1;
                                        i6 = i2;
                                        th = th2;
                                        byteArrayOutputStream = null;
                                        inputStream2 = inputStream;
                                        th.printStackTrace();
                                        if (inputStream2 != null) {
                                        }
                                        if (byteArrayOutputStream != null) {
                                        }
                                        if (httpURLConnection != null) {
                                        }
                                        return i6;
                                    }
                                } catch (ConnectException e17) {
                                    connectException = e17;
                                    inputStream = null;
                                } catch (SocketException e18) {
                                    socketException = e18;
                                    inputStream = null;
                                } catch (Throwable th7) {
                                    th2 = th7;
                                    inputStream = null;
                                }
                            } else {
                                try {
                                    throw new SocketException("http status is not 200 or 304");
                                } catch (ConnectException e19) {
                                    connectException = e19;
                                    i4 = responseCode;
                                    inputStream = null;
                                    i6 = i4;
                                    e3 = connectException;
                                    byteArrayOutputStream = null;
                                    inputStream2 = inputStream;
                                    e3.printStackTrace();
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (Exception unused12) {
                                        }
                                    }
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused13) {
                                        }
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return i6;
                                } catch (SocketException e20) {
                                    socketException = e20;
                                    i3 = responseCode;
                                    inputStream = null;
                                    i6 = i3;
                                    e2 = socketException;
                                    byteArrayOutputStream = null;
                                    inputStream2 = inputStream;
                                    e2.printStackTrace();
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (Exception unused14) {
                                        }
                                    }
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused15) {
                                        }
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return i6;
                                } catch (Throwable th8) {
                                    th2 = th8;
                                    i2 = responseCode;
                                    inputStream = null;
                                    i6 = i2;
                                    th = th2;
                                    byteArrayOutputStream = null;
                                    inputStream2 = inputStream;
                                    th.printStackTrace();
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (Exception unused16) {
                                        }
                                    }
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception unused17) {
                                        }
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return i6;
                                }
                            }
                        } catch (ConnectException e21) {
                            e = e21;
                            e3 = e;
                            byteArrayOutputStream = null;
                            i6 = -1;
                            e3.printStackTrace();
                            if (inputStream2 != null) {
                            }
                            if (byteArrayOutputStream != null) {
                            }
                            if (httpURLConnection != null) {
                            }
                            return i6;
                        } catch (SocketException e22) {
                            e = e22;
                            e2 = e;
                            byteArrayOutputStream = null;
                            i6 = -1;
                            e2.printStackTrace();
                            if (inputStream2 != null) {
                            }
                            if (byteArrayOutputStream != null) {
                            }
                            if (httpURLConnection != null) {
                            }
                            return i6;
                        } catch (Throwable th9) {
                            th = th9;
                            th = th;
                            byteArrayOutputStream = null;
                            i6 = -1;
                            th.printStackTrace();
                            if (inputStream2 != null) {
                            }
                            if (byteArrayOutputStream != null) {
                            }
                            if (httpURLConnection != null) {
                            }
                            return i6;
                        }
                    } catch (SocketTimeoutException e23) {
                        e = e23;
                        inputStream = null;
                    }
                } catch (ConnectException e24) {
                    e = e24;
                    httpURLConnection = null;
                } catch (SocketException e25) {
                    e = e25;
                    httpURLConnection = null;
                } catch (SocketTimeoutException e26) {
                    e = e26;
                    httpURLConnection = null;
                    inputStream = null;
                } catch (Throwable th10) {
                    th = th10;
                    httpURLConnection = null;
                }
            } catch (Throwable th11) {
                th = th11;
                if (inputStream2 != null) {
                }
                if (byteArrayOutputStream != null) {
                }
                if (httpURLConnection != null) {
                }
                throw th;
            }
        } catch (AssertionError e27) {
            e = e27;
            i5 = i6;
        } catch (Exception unused18) {
        }
    }
}
