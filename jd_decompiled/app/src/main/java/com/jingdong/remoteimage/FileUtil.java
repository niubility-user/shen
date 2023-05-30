package com.jingdong.remoteimage;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

/* loaded from: classes7.dex */
public class FileUtil {
    public static String formatDir(String str) {
        String str2 = File.separator;
        if (str.endsWith(str2)) {
            return str;
        }
        return str + str2;
    }

    public static String formatPath(String str, String str2) {
        String str3 = File.separator;
        if (str2.startsWith(str3)) {
            str2 = str2.substring(1);
        }
        if (!str.endsWith(str3)) {
            str = str + str3;
        }
        return str + str2;
    }

    public static String getAppFilesPath(Context context) {
        return context.getFilesDir().getPath();
    }

    public static String getAssetPath(String str) {
        return "file:///android_asset" + str;
    }

    public static boolean getDirectoryPath(String str) {
        return getDirectoryPath(str, true);
    }

    public static String getFromAssets(String str, Context context) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(str), "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            bufferedReader2.close();
                            return sb.toString();
                        }
                    } catch (Error e2) {
                        e = e2;
                        throw new Exception(e.getMessage());
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Error e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static boolean isFileExist(String str) {
        File file = new File(str);
        return file.exists() && !file.isDirectory();
    }

    public static String readFile(String str) {
        Throwable th;
        FileInputStream fileInputStream;
        try {
            try {
                fileInputStream = new FileInputStream(new File(str));
            } catch (RuntimeException unused) {
                fileInputStream = null;
            } catch (Exception unused2) {
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String str2 = new String(bArr, "utf-8");
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return str2;
            } catch (RuntimeException unused3) {
                if (fileInputStream != null) {
                    fileInputStream.close();
                    return null;
                }
                return null;
            } catch (Exception unused4) {
                if (fileInputStream != null) {
                    fileInputStream.close();
                    return null;
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static File[] readFilesFromDir(String str) {
        return readFilesFromDir(new File(str));
    }

    public static String removeAllFilesFromDir(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(str);
            if (file.exists()) {
                File[] readFilesFromDir = readFilesFromDir(file);
                int i2 = 0;
                while (i2 < readFilesFromDir.length) {
                    File file2 = readFilesFromDir[i2];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(file2.getName());
                    sb2.append(i2 == readFilesFromDir.length + (-1) ? "" : "###");
                    sb.append(sb2.toString());
                    if (file2.isDirectory()) {
                        removeOneDir(file2.getPath());
                    } else {
                        file2.delete();
                    }
                    i2++;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    public static boolean removeOneDir(String str) {
        String formatDir = formatDir(str);
        try {
            removeAllFilesFromDir(formatDir);
            new File(formatDir).delete();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String removeOneFile(String str) {
        try {
            new File(str).delete();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return e2.getMessage();
        }
    }

    public static <U> void writeObjectToFile(String str, String str2, U u) {
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                try {
                    File file = new File(str);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File(str + str2)));
                    try {
                        objectOutputStream2.writeObject(u);
                        objectOutputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream = objectOutputStream2;
                        e.printStackTrace();
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream = objectOutputStream2;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public static <U> void writeObjectToSdcard(String str, String str2, U u) {
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                try {
                    if (Environment.getExternalStorageState().equals("mounted")) {
                        File file = new File(str);
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File(str + str2)));
                        try {
                            objectOutputStream2.writeObject(u);
                            objectOutputStream = objectOutputStream2;
                        } catch (Exception e2) {
                            e = e2;
                            objectOutputStream = objectOutputStream2;
                            e.printStackTrace();
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public static boolean getDirectoryPath(String str, boolean z) {
        File file = new File(str);
        if (!file.exists()) {
            if (z) {
                file.mkdirs();
            }
            if (!file.isDirectory()) {
                return false;
            }
        }
        return true;
    }

    public static File[] readFilesFromDir(File file) {
        return file.listFiles();
    }

    public static String[] formatPath(String str) {
        if (str == null) {
            return null;
        }
        String[] split = str.split(File.separatorChar == '\\' ? "\\\\" : File.separator);
        return new String[]{str.replace(split[split.length - 1], ""), split[split.length - 1]};
    }

    public static <U> String writeObjectToFile(String str, U u) {
        ObjectOutputStream objectOutputStream = null;
        try {
            try {
                try {
                    ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File(str)));
                    try {
                        objectOutputStream2.writeObject(u);
                        objectOutputStream2.close();
                        return null;
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream = objectOutputStream2;
                        e.printStackTrace();
                        String message = e.getMessage();
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                        return message;
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream = objectOutputStream2;
                        if (objectOutputStream != null) {
                            try {
                                objectOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                e3.getMessage();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
            return e5.getMessage();
        }
    }
}
