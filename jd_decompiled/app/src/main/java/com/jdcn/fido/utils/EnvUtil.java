package com.jdcn.fido.utils;

import android.content.Context;
import com.jdcn.fido.http.HttpUrlUtil;

/* loaded from: classes18.dex */
public class EnvUtil {
    private static final String Releasecert = "MIIESTCCAzGgAwIBAgIUb9ahAbcp4PbmKCuGRs+GaBrPMBgwDQYJKoZIhvcNAQELBQAwXjEYMBYGA1UEAwwPV2FuZ1lpbiBVc2VyIENBMR8wHQYDVQQLDBZXYW5nWWluIFNlY3VyaXR5Q2VudGVyMRQwEgYDVQQKDAtXYW5nWWluLmNvbTELMAkGA1UEBhMCQ04wHhcNMTgwODI5MTAyOTE2WhcNMTkwODI5MTAyOTE2WjCBlDF0MHIGA1UEAwxr5Lqs5Lic6YeR6J6NLeaKgOacr+eglOWPkemDqC3kuKrkurrkuJrliqHnu7zlkIjnoJTlj5Hpg6gt6aOO5o6n56CU5Y+R6YOoLeaZuuiDveivhuWIq+WunumqjOWupChBS1MwMDAwMEFLUykxDzANBgNVBAsMBmpyIHRvcDELMAkGA1UECgwCamQwggEgMA0GCSqGSIb3DQEBAQUAA4IBDQAwggEIAoIBAQC40b+9fdJRXY+AOdC5I3mfwZVFWMzpc+8CSBseuMdKEX57stGoKAVilElvUVCM4amrBqb90/18Ji9fQ+Ra/hiOxjsaDkhrMkSwi1b+VT4Zg3orn/Gpt9/A7UpfRCZlhKVTI370k6vfTZgKtXOtowDtksPLhYffu/vJbCuSN2gMq0WmZ55WWXWE6QRB/0r9nOtBjjs6Ebsj3M99TUbZtgt6MKsOmsK9bfyYiNhZdq2L7F77JcbM7ZRil//xI4ET5ks1hYzrt4rXrg26ATLZhkjSmsDTuuMfk1QkqIRLlQdIDuaWpU6rTg8u8lUDsTSd2gsk71EAaeP2dfWaL60++ZDHAgEDo4HJMIHGMAkGA1UdEwQCMAAwCwYDVR0PBAQDAgbAMGwGA1UdHwRlMGMwYaBfoF2GW2h0dHA6Ly90b3BjYS5kLmNoaW5hYmFuay5jb20uY24vcHVibGljL2l0cnVzY3JsP0NBPTFFRTQ1QjcxNkQwOUE0OTI4MkIxMzQ2QTJDQzNDNjI3MzExMzgwRUIwHwYDVR0jBBgwFoAUCKxvAe67vsOUVzpp1dx/r34ctOAwHQYDVR0OBBYEFOxwX51lfkiPGzdSHJp/aoWEy7yGMA0GCSqGSIb3DQEBCwUAA4IBAQAQFz4OkKRmF1eahWwFes7ZMLmYuc+wc1Jfa166Ylefjb79zu3p+P+Acb07hhbKioHIdsw6IszzYqMntmP9OfCAkXhxEmAeZNAgsHdw5aIoD4Uzg0pD7oVKjCaStFsadaPUa3vVJR/grKFAQRPunsGC8pLb8X2WjBOeYLZNgAwUhrtJZzjeog+zYvQRo55Ed/kXVHrdgSVA9vCmhKwnmRhe6kzJj7GUikqm4GdQhjJIfkV/0eULsrLEhM+dHn4qKDdZzNBIa/AEQDpC9pmD8ZnIzxAAdeuPOhOuv/DyCvQwIv4KymYASHIl4ouMOYV8hPgau2W5H4bUyPKbz4HiM/Gf";
    private static final String cert = "MIIEUjCCAzqgAwIBAgIUC+joirETCy0/UmfYc7lPSSs3zjkwDQYJKoZIhvcNAQELBQAwbzEfMB0GA1UEAwwWSlIuSkQuY29tIFRlc3QgVXNlciBDQTEkMCIGA1UECwwbV2FuZ1lpbiBTZWN1cml0eUNlbnRlciBUZXN0MRkwFwYDVQQKDBBXYW5nWWluLmNvbSBUZXN0MQswCQYDVQQGEwJDTjAeFw0xODA4MzAwNzM0NTlaFw0xOTA4MzAwNzM0NTlaMIGUMXQwcgYDVQQDDGvkuqzkuJzph5Hono0t5oqA5pyv56CU5Y+R6YOoLeS4quS6uuS4muWKoee7vOWQiOeglOWPkemDqC3po47mjqfnoJTlj5Hpg6gt5pm66IO96K+G5Yir5a6e6aqM5a6kKEFLUzAwMDAwQUtTKTEPMA0GA1UECwwGanIgdG9wMQswCQYDVQQKDAJqZDCCASAwDQYJKoZIhvcNAQEBBQADggENADCCAQgCggEBAPVsdj3gCCOzN8j9Fd52GPlMsFcowuiJGyCp36hx3QbAYfva0ONhjzVRK82H6d/Pj+4LvQ437tixsLrJ3vxdC3+ka34QzWlMdRc0fRFF/jGBBT2bQTm2u1NIXBILo9FoTpg0bmmnHXB0xba2Lih0Ch9T8IdGNMvgmCibKAmI36pXyn9V00da/e84TKtrtou2kwaE1ycaX62hCuEK+E9j69vCebfeM4RYhXCurSAN380abtVFTrHIDKbM+xiZa2FilAtlK9H1AwmEl1DZKmtx5ZRm+kb4LFak9EpJEacz3zC5ospIyFihMhcdPyB6eA0JB2tD4styMQdTGTeLbcHiZ3ECAQOjgcEwgb4wCQYDVR0TBAIwADALBgNVHQ8EBAMCBsAwZAYDVR0fBF0wWzBZoFegVYZTaHR0cDovLzE3Mi4yNS40Ny4zOjgwODAvcHVibGljL2l0cnVzY3JsP0NBPTM0MDg3NUIwNzM3OUI3QTRDRDkxMUZDMEQ5NUY1NDNGRDU0MDY4MTcwHwYDVR0jBBgwFoAUkxJl9Pcmp07U2cN3U+4ZOaS4CNcwHQYDVR0OBBYEFAkpimDvC/g0hcPsnv0Y3cCqj7c6MA0GCSqGSIb3DQEBCwUAA4IBAQBKH7QGwtBTQjCB3q1IA+CAGWUs9oBJysGNTDB+Ebl2nkpy2XvUwn2enDg9BhGnW8inCcfjgl5E00IJeXFtMReB6/oZeCfWg5GzfEjSgvE2VbDUtkD6Uz+gXAi+Sij59NyEGzKuqmxhBApKI/RmVuPC2aV1cSEEGc0Ho+iBuN61RSeLkZ/PmfmCoyt/BOYKnDJfmChc2Vstj9Oot+pojYzcFjGvFEeO9WmUAysCpY+qNUCOiq7i66bW7CnqoqIgeuzmetag4PqH4PSWrmfh3k8dTShd9werZjlPWpmIijyNNa+h33st7J+7aBJxUbgDEw4gtJHlzLx2imBvV6ZPFN5u";
    private static volatile int serverType;

    public static void getEnv(Context context) {
        Class<?> loadClass;
        try {
            if (serverType == -1 || (loadClass = context.getClassLoader().loadClass("com.jingdong.jdsdk.config.HostConfig")) == null) {
                return;
            }
            Object invoke = loadClass.getMethod("getJDPayDevelopType", new Class[0]).invoke(null, new Object[0]);
            if (invoke != null) {
                serverType = ((Integer) invoke).intValue();
            }
            if (serverType == 2) {
                HttpUrlUtil.setHostUrl("http://10.222.242.120:8080");
            } else {
                HttpUrlUtil.setHostUrl(HttpUrlUtil.URL_HOST_RELEASE);
            }
        } catch (Throwable unused) {
            serverType = -1;
            HttpUrlUtil.setHostUrl(HttpUrlUtil.URL_HOST_RELEASE);
        }
    }

    public static int getServerType() {
        return serverType;
    }
}
