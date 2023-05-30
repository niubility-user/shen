package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes11.dex */
public class p {
    private static Object a = new Object();
    private static Map<String, Queue<String>> b = new HashMap();

    public static boolean a(XMPushService xMPushService, String str, String str2) {
        synchronized (a) {
            SharedPreferences sharedPreferences = xMPushService.getSharedPreferences("push_message_ids", 0);
            Queue<String> queue = b.get(str);
            if (queue == null) {
                String[] split = sharedPreferences.getString(str, "").split(DYConstants.DY_REGEX_COMMA);
                LinkedList linkedList = new LinkedList();
                for (String str3 : split) {
                    linkedList.add(str3);
                }
                b.put(str, linkedList);
                queue = linkedList;
            }
            if (queue.contains(str2)) {
                return true;
            }
            queue.add(str2);
            if (queue.size() > 25) {
                queue.poll();
            }
            String d = com.xiaomi.push.p0.d(queue, DYConstants.DY_REGEX_COMMA);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, d);
            edit.commit();
            return false;
        }
    }
}
