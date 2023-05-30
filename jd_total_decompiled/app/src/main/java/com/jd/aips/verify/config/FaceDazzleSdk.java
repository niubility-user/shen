package com.jd.aips.verify.config;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes12.dex */
public class FaceDazzleSdk implements Serializable {
    static final long serialVersionUID = 793970249995663395L;
    public Config config;
    public String version;

    /* loaded from: classes12.dex */
    public class Config implements Serializable {
        static final long serialVersionUID = 5325585254968600779L;
        public List<DazzleRule> face_dazzle_rules;
        public String facedazzle_rule_optional;
        public int facedazzle_time = 500;
        public int facedazzle_times = 4;

        /* loaded from: classes12.dex */
        public class DazzleRule implements Serializable {
            public List<String> face_dazzle_colours;

            public DazzleRule() {
            }
        }

        public Config() {
        }
    }
}
