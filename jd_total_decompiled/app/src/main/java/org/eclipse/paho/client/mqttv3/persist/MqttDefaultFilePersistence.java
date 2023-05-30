package org.eclipse.paho.client.mqttv3.persist;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;

/* loaded from: classes11.dex */
public class MqttDefaultFilePersistence implements MqttClientPersistence {
    private static final FilenameFilter FILE_FILTER = new FilenameFilter() { // from class: org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence.1
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(MqttDefaultFilePersistence.MESSAGE_FILE_EXTENSION);
        }
    };
    private static final String LOCK_FILENAME = ".lck";
    private static final String MESSAGE_BACKUP_FILE_EXTENSION = ".bup";
    private static final String MESSAGE_FILE_EXTENSION = ".msg";
    private File clientDir;
    private File dataDir;
    private FileLock fileLock;

    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }

    private void checkIsOpen() throws MqttPersistenceException {
        if (this.clientDir == null) {
            throw new MqttPersistenceException();
        }
    }

    private File[] getFiles() throws MqttPersistenceException {
        checkIsOpen();
        File[] listFiles = this.clientDir.listFiles(FILE_FILTER);
        if (listFiles != null) {
            return listFiles;
        }
        throw new MqttPersistenceException();
    }

    private boolean isSafeChar(char c2) {
        return Character.isJavaIdentifierPart(c2) || c2 == '-';
    }

    private void restoreBackups(File file) throws MqttPersistenceException {
        File[] listFiles = file.listFiles(new FileFilter() { // from class: org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence.2
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.getName().endsWith(MqttDefaultFilePersistence.MESSAGE_BACKUP_FILE_EXTENSION);
            }
        });
        if (listFiles != null) {
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                File file2 = new File(file, listFiles[i2].getName().substring(0, listFiles[i2].getName().length() - 4));
                if (!listFiles[i2].renameTo(file2)) {
                    file2.delete();
                    listFiles[i2].renameTo(file2);
                }
            }
            return;
        }
        throw new MqttPersistenceException();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() throws MqttPersistenceException {
        checkIsOpen();
        for (File file : getFiles()) {
            file.delete();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void close() throws MqttPersistenceException {
        synchronized (this) {
            FileLock fileLock = this.fileLock;
            if (fileLock != null) {
                fileLock.release();
            }
            if (getFiles().length == 0) {
                this.clientDir.delete();
            }
            this.clientDir = null;
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) throws MqttPersistenceException {
        checkIsOpen();
        File file = this.clientDir;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(MESSAGE_FILE_EXTENSION);
        return new File(file, stringBuffer.toString()).exists();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) throws MqttPersistenceException {
        checkIsOpen();
        try {
            File file = this.clientDir;
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
            stringBuffer.append(MESSAGE_FILE_EXTENSION);
            FileInputStream fileInputStream = new FileInputStream(new File(file, stringBuffer.toString()));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i2 = 0; i2 < available; i2 += fileInputStream.read(bArr, i2, available - i2)) {
            }
            fileInputStream.close();
            return new MqttPersistentData(str, bArr, 0, available, null, 0, 0);
        } catch (IOException e2) {
            throw new MqttPersistenceException(e2);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration keys() throws MqttPersistenceException {
        checkIsOpen();
        File[] files = getFiles();
        Vector vector = new Vector(files.length);
        for (File file : files) {
            vector.addElement(file.getName().substring(0, r4.length() - 4));
        }
        return vector.elements();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) throws MqttPersistenceException {
        if (this.dataDir.exists() && !this.dataDir.isDirectory()) {
            throw new MqttPersistenceException();
        }
        if (!this.dataDir.exists() && !this.dataDir.mkdirs()) {
            throw new MqttPersistenceException();
        }
        if (this.dataDir.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (isSafeChar(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            stringBuffer.append("-");
            for (int i3 = 0; i3 < str2.length(); i3++) {
                char charAt2 = str2.charAt(i3);
                if (isSafeChar(charAt2)) {
                    stringBuffer.append(charAt2);
                }
            }
            synchronized (this) {
                if (this.clientDir == null) {
                    File file = new File(this.dataDir, stringBuffer.toString());
                    this.clientDir = file;
                    if (!file.exists()) {
                        this.clientDir.mkdir();
                    }
                }
                try {
                    this.fileLock = new FileLock(this.clientDir, LOCK_FILENAME);
                } catch (Exception unused) {
                }
                restoreBackups(this.clientDir);
            }
            return;
        }
        throw new MqttPersistenceException();
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        checkIsOpen();
        File file = this.clientDir;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(MESSAGE_FILE_EXTENSION);
        File file2 = new File(file, stringBuffer.toString());
        File file3 = this.clientDir;
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str));
        stringBuffer2.append(MESSAGE_FILE_EXTENSION);
        stringBuffer2.append(MESSAGE_BACKUP_FILE_EXTENSION);
        File file4 = new File(file3, stringBuffer2.toString());
        if (file2.exists() && !file2.renameTo(file4)) {
            file4.delete();
            file2.renameTo(file4);
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
                if (mqttPersistable.getPayloadBytes() != null) {
                    fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                if (file4.exists()) {
                    file4.delete();
                }
            } catch (IOException e2) {
                throw new MqttPersistenceException(e2);
            }
        } finally {
            if (file4.exists() && !file4.renameTo(file2)) {
                file2.delete();
                file4.renameTo(file2);
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) throws MqttPersistenceException {
        checkIsOpen();
        File file = this.clientDir;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(MESSAGE_FILE_EXTENSION);
        File file2 = new File(file, stringBuffer.toString());
        if (file2.exists()) {
            file2.delete();
        }
    }

    public MqttDefaultFilePersistence(String str) {
        this.clientDir = null;
        this.fileLock = null;
        this.dataDir = new File(str);
    }
}
