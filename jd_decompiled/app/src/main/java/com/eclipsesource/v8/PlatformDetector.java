package com.eclipsesource.v8;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/* loaded from: classes.dex */
public class PlatformDetector {

    /* loaded from: classes.dex */
    public static class Arch {
        public static String getName() {
            String property = System.getProperty("os.arch");
            String normalizeArch = PlatformDetector.normalizeArch(property);
            if (normalizeArch.equals("unknown")) {
                throw new UnsatisfiedLinkError("Unsupported arch: " + property);
            }
            return normalizeArch;
        }
    }

    /* loaded from: classes.dex */
    public static class OS {
        public static String getLibFileExtension() {
            if (isWindows()) {
                return "dll";
            }
            if (isMac()) {
                return "dylib";
            }
            if (isLinux() || isAndroid() || isNativeClient()) {
                return "so";
            }
            throw new UnsatisfiedLinkError("Unsupported platform library-extension for: " + getName());
        }

        public static String getName() {
            String property = System.getProperty("os.name");
            String normalizeOs = PlatformDetector.normalizeOs(property);
            String property2 = System.getProperty("java.specification.vendor");
            if (PlatformDetector.normalize(property2).contains("android") || normalizeOs.contains("android")) {
                return "android";
            }
            if (normalizeOs.equals("unknown")) {
                throw new UnsatisfiedLinkError("Unsupported platform/vendor: " + property + " / " + property2);
            }
            return normalizeOs;
        }

        public static boolean isAndroid() {
            return getName().equals("android");
        }

        public static boolean isLinux() {
            return getName().equals(Platform.LINUX);
        }

        public static boolean isMac() {
            return getName().equals(Platform.MACOSX);
        }

        public static boolean isNativeClient() {
            return getName().equals(Platform.NATIVE_CLIENT);
        }

        public static boolean isWindows() {
            return getName().equals(Platform.WINDOWS);
        }
    }

    /* loaded from: classes.dex */
    public static class Vendor {
        private static final String LINUX_ID_PREFIX = "ID=";
        private static final String[] LINUX_OS_RELEASE_FILES = {"/etc/os-release", "/usr/lib/os-release"};
        private static final String REDHAT_RELEASE_FILE = "/etc/redhat-release";

        private static void closeQuietly(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
        }

        private static String getLinuxOsReleaseId() {
            for (String str : LINUX_OS_RELEASE_FILES) {
                File file = new File(str);
                if (file.exists()) {
                    return parseLinuxOsReleaseFile(file);
                }
            }
            File file2 = new File(REDHAT_RELEASE_FILE);
            if (file2.exists()) {
                return parseLinuxRedhatReleaseFile(file2);
            }
            throw new UnsatisfiedLinkError("Unsupported linux vendor: " + getName());
        }

        public static String getName() {
            if (OS.isWindows()) {
                return "microsoft";
            }
            if (OS.isMac()) {
                return "apple";
            }
            if (OS.isLinux()) {
                return getLinuxOsReleaseId();
            }
            if (OS.isAndroid()) {
                return "google";
            }
            throw new UnsatisfiedLinkError("Unsupported vendor: " + getName());
        }

        /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
            r0 = com.eclipsesource.v8.PlatformDetector.normalizeOsReleaseValue(r4.substring(3));
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.lang.String parseLinuxOsReleaseFile(java.io.File r4) {
            /*
                r0 = 0
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L36
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L36
                java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L36
                r3.<init>(r4)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L36
                java.lang.String r4 = "utf-8"
                r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L36
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L36
            L13:
                java.lang.String r4 = r1.readLine()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L37
                if (r4 == 0) goto L2a
                java.lang.String r2 = "ID="
                boolean r2 = r4.startsWith(r2)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L37
                if (r2 == 0) goto L13
                r2 = 3
                java.lang.String r4 = r4.substring(r2)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L37
                java.lang.String r0 = com.eclipsesource.v8.PlatformDetector.access$300(r4)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L37
            L2a:
                closeQuietly(r1)
                return r0
            L2e:
                r4 = move-exception
                r0 = r1
                goto L32
            L31:
                r4 = move-exception
            L32:
                closeQuietly(r0)
                throw r4
            L36:
                r1 = r0
            L37:
                closeQuietly(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.eclipsesource.v8.PlatformDetector.Vendor.parseLinuxOsReleaseFile(java.io.File):java.lang.String");
        }

        private static String parseLinuxRedhatReleaseFile(File file) {
            BufferedReader bufferedReader;
            String str = "centos";
            BufferedReader bufferedReader2 = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (IOException unused) {
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String lowerCase = readLine.toLowerCase(Locale.US);
                    if (!lowerCase.contains("centos")) {
                        if (lowerCase.contains("fedora")) {
                            str = "fedora";
                        } else if (!lowerCase.contains("red hat enterprise linux")) {
                            closeQuietly(bufferedReader);
                            return null;
                        } else {
                            str = "rhel";
                        }
                    }
                    closeQuietly(bufferedReader);
                    return str;
                }
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                closeQuietly(bufferedReader2);
                throw th;
            }
            closeQuietly(bufferedReader);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalize(String str) {
        return str == null ? "" : str.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeArch(String str) {
        String normalize = normalize(str);
        return normalize.matches("^(x8664|amd64|ia32e|em64t|x64)$") ? "x86_64" : normalize.matches("^(x8632|x86|i[3-6]86|ia32|x32)$") ? "x86_32" : normalize.matches("^(ia64|itanium64)$") ? "itanium_64" : normalize.matches("^(sparc|sparc32)$") ? "sparc_32" : normalize.matches("^(sparcv9|sparc64)$") ? "sparc_64" : (normalize.matches("^(arm|arm32)$") || normalize.startsWith("armv7")) ? "arm_32" : ("aarch64".equals(normalize) || normalize.startsWith("armv8")) ? "aarch_64" : normalize.matches("^(ppc|ppc32)$") ? "ppc_32" : "ppc64".equals(normalize) ? "ppc_64" : "ppc64le".equals(normalize) ? "ppcle_64" : "s390".equals(normalize) ? "s390_32" : "s390x".equals(normalize) ? "s390_64" : "unknown";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeOs(String str) {
        String normalize = normalize(str);
        return normalize.startsWith("aix") ? "aix" : normalize.startsWith("hpux") ? "hpux" : (!normalize.startsWith("os400") || (normalize.length() > 5 && Character.isDigit(normalize.charAt(5)))) ? normalize.startsWith("android") ? "android" : normalize.startsWith(Platform.LINUX) ? Platform.LINUX : normalize.startsWith(Platform.NATIVE_CLIENT) ? Platform.NATIVE_CLIENT : (normalize.startsWith(Platform.MACOSX) || normalize.startsWith("osx")) ? Platform.MACOSX : normalize.startsWith("freebsd") ? "freebsd" : normalize.startsWith("openbsd") ? "openbsd" : normalize.startsWith("netbsd") ? "netbsd" : (normalize.startsWith("solaris") || normalize.startsWith("sunos")) ? "sunos" : normalize.startsWith(Platform.WINDOWS) ? Platform.WINDOWS : "unknown" : "os400";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeOsReleaseValue(String str) {
        return str.trim().replace("\"", "");
    }
}
