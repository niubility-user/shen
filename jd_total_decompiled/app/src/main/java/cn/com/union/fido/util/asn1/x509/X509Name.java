package cn.com.union.fido.util.asn1.x509;

import cn.com.union.fido.util.asn1.ASN1Encodable;
import cn.com.union.fido.util.asn1.ASN1EncodableVector;
import cn.com.union.fido.util.asn1.ASN1Object;
import cn.com.union.fido.util.asn1.ASN1Sequence;
import cn.com.union.fido.util.asn1.ASN1Set;
import cn.com.union.fido.util.asn1.ASN1TaggedObject;
import cn.com.union.fido.util.asn1.DEREncodable;
import cn.com.union.fido.util.asn1.DERObject;
import cn.com.union.fido.util.asn1.DERObjectIdentifier;
import cn.com.union.fido.util.asn1.DERSequence;
import cn.com.union.fido.util.asn1.DERSet;
import cn.com.union.fido.util.asn1.DERString;
import cn.com.union.fido.util.asn1.DERUniversalString;
import cn.com.union.fido.util.asn1.pkcs.PKCSObjectIdentifiers;
import cn.com.union.fido.util.asn1.util.Strings;
import cn.com.union.fido.util.asn1.util.encoders.Hex;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.app.mall.e;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.tencent.mapsdk.internal.la;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: classes.dex */
public class X509Name extends ASN1Encodable {
    public static final DERObjectIdentifier BUSINESS_CATEGORY;
    public static final DERObjectIdentifier C;
    public static final DERObjectIdentifier CN;
    public static final DERObjectIdentifier COUNTRY_OF_CITIZENSHIP;
    public static final DERObjectIdentifier COUNTRY_OF_RESIDENCE;
    public static final DERObjectIdentifier DATE_OF_BIRTH;
    public static final DERObjectIdentifier DC;
    public static final DERObjectIdentifier DMD_NAME;
    public static final DERObjectIdentifier DN_QUALIFIER;
    public static final Hashtable DefaultLookUp;
    public static boolean DefaultReverse;
    public static final Hashtable DefaultSymbols;
    public static final DERObjectIdentifier E;
    public static final DERObjectIdentifier EmailAddress;
    private static final Boolean FALSE;
    public static final DERObjectIdentifier GENDER;
    public static final DERObjectIdentifier GENERATION;
    public static final DERObjectIdentifier GIVENNAME;
    public static final DERObjectIdentifier INITIALS;
    public static final DERObjectIdentifier L;
    public static final DERObjectIdentifier NAME;
    public static final DERObjectIdentifier NAME_AT_BIRTH;
    public static final DERObjectIdentifier O;
    public static final Hashtable OIDLookUp;
    public static final DERObjectIdentifier OU;
    public static final DERObjectIdentifier PLACE_OF_BIRTH;
    public static final DERObjectIdentifier POSTAL_ADDRESS;
    public static final DERObjectIdentifier POSTAL_CODE;
    public static final DERObjectIdentifier PSEUDONYM;
    public static final Hashtable RFC1779Symbols;
    public static final Hashtable RFC2253Symbols;
    public static final DERObjectIdentifier SERIALNUMBER;
    public static final DERObjectIdentifier SN;
    public static final DERObjectIdentifier ST;
    public static final DERObjectIdentifier STREET;
    public static final DERObjectIdentifier SURNAME;
    public static final Hashtable SymbolLookUp;
    public static final DERObjectIdentifier T;
    public static final DERObjectIdentifier TELEPHONE_NUMBER;
    private static final Boolean TRUE;
    public static final DERObjectIdentifier UID;
    public static final DERObjectIdentifier UNIQUE_IDENTIFIER;
    public static final DERObjectIdentifier UnstructuredAddress;
    public static final DERObjectIdentifier UnstructuredName;
    private Vector added;
    private X509NameEntryConverter converter;
    private int hashCodeValue;
    private boolean isHashCodeCalculated;
    private Vector ordering;
    private ASN1Sequence seq;
    private Vector values;

    static {
        DERObjectIdentifier dERObjectIdentifier = new DERObjectIdentifier("2.5.4.6");
        C = dERObjectIdentifier;
        DERObjectIdentifier dERObjectIdentifier2 = new DERObjectIdentifier("2.5.4.10");
        O = dERObjectIdentifier2;
        DERObjectIdentifier dERObjectIdentifier3 = new DERObjectIdentifier("2.5.4.11");
        OU = dERObjectIdentifier3;
        DERObjectIdentifier dERObjectIdentifier4 = new DERObjectIdentifier("2.5.4.12");
        T = dERObjectIdentifier4;
        DERObjectIdentifier dERObjectIdentifier5 = new DERObjectIdentifier("2.5.4.3");
        CN = dERObjectIdentifier5;
        DERObjectIdentifier dERObjectIdentifier6 = new DERObjectIdentifier("2.5.4.5");
        SN = dERObjectIdentifier6;
        DERObjectIdentifier dERObjectIdentifier7 = new DERObjectIdentifier("2.5.4.9");
        STREET = dERObjectIdentifier7;
        SERIALNUMBER = dERObjectIdentifier6;
        DERObjectIdentifier dERObjectIdentifier8 = new DERObjectIdentifier("2.5.4.7");
        L = dERObjectIdentifier8;
        DERObjectIdentifier dERObjectIdentifier9 = new DERObjectIdentifier("2.5.4.8");
        ST = dERObjectIdentifier9;
        DERObjectIdentifier dERObjectIdentifier10 = new DERObjectIdentifier("2.5.4.4");
        SURNAME = dERObjectIdentifier10;
        DERObjectIdentifier dERObjectIdentifier11 = new DERObjectIdentifier("2.5.4.42");
        GIVENNAME = dERObjectIdentifier11;
        DERObjectIdentifier dERObjectIdentifier12 = new DERObjectIdentifier("2.5.4.43");
        INITIALS = dERObjectIdentifier12;
        DERObjectIdentifier dERObjectIdentifier13 = new DERObjectIdentifier("2.5.4.44");
        GENERATION = dERObjectIdentifier13;
        DERObjectIdentifier dERObjectIdentifier14 = new DERObjectIdentifier("2.5.4.45");
        UNIQUE_IDENTIFIER = dERObjectIdentifier14;
        DERObjectIdentifier dERObjectIdentifier15 = new DERObjectIdentifier("2.5.4.15");
        BUSINESS_CATEGORY = dERObjectIdentifier15;
        DERObjectIdentifier dERObjectIdentifier16 = new DERObjectIdentifier("2.5.4.17");
        POSTAL_CODE = dERObjectIdentifier16;
        DERObjectIdentifier dERObjectIdentifier17 = new DERObjectIdentifier("2.5.4.46");
        DN_QUALIFIER = dERObjectIdentifier17;
        DERObjectIdentifier dERObjectIdentifier18 = new DERObjectIdentifier("2.5.4.65");
        PSEUDONYM = dERObjectIdentifier18;
        DERObjectIdentifier dERObjectIdentifier19 = new DERObjectIdentifier("1.3.6.1.5.5.7.9.1");
        DATE_OF_BIRTH = dERObjectIdentifier19;
        DERObjectIdentifier dERObjectIdentifier20 = new DERObjectIdentifier("1.3.6.1.5.5.7.9.2");
        PLACE_OF_BIRTH = dERObjectIdentifier20;
        DERObjectIdentifier dERObjectIdentifier21 = new DERObjectIdentifier("1.3.6.1.5.5.7.9.3");
        GENDER = dERObjectIdentifier21;
        DERObjectIdentifier dERObjectIdentifier22 = new DERObjectIdentifier("1.3.6.1.5.5.7.9.4");
        COUNTRY_OF_CITIZENSHIP = dERObjectIdentifier22;
        DERObjectIdentifier dERObjectIdentifier23 = new DERObjectIdentifier("1.3.6.1.5.5.7.9.5");
        COUNTRY_OF_RESIDENCE = dERObjectIdentifier23;
        DERObjectIdentifier dERObjectIdentifier24 = new DERObjectIdentifier("1.3.36.8.3.14");
        NAME_AT_BIRTH = dERObjectIdentifier24;
        DERObjectIdentifier dERObjectIdentifier25 = new DERObjectIdentifier("2.5.4.16");
        POSTAL_ADDRESS = dERObjectIdentifier25;
        DMD_NAME = new DERObjectIdentifier("2.5.4.54");
        DERObjectIdentifier dERObjectIdentifier26 = X509ObjectIdentifiers.id_at_telephoneNumber;
        TELEPHONE_NUMBER = dERObjectIdentifier26;
        DERObjectIdentifier dERObjectIdentifier27 = X509ObjectIdentifiers.id_at_name;
        NAME = dERObjectIdentifier27;
        DERObjectIdentifier dERObjectIdentifier28 = PKCSObjectIdentifiers.pkcs_9_at_emailAddress;
        EmailAddress = dERObjectIdentifier28;
        DERObjectIdentifier dERObjectIdentifier29 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredName;
        UnstructuredName = dERObjectIdentifier29;
        DERObjectIdentifier dERObjectIdentifier30 = PKCSObjectIdentifiers.pkcs_9_at_unstructuredAddress;
        UnstructuredAddress = dERObjectIdentifier30;
        E = dERObjectIdentifier28;
        DERObjectIdentifier dERObjectIdentifier31 = new DERObjectIdentifier("0.9.2342.19200300.100.1.25");
        DC = dERObjectIdentifier31;
        DERObjectIdentifier dERObjectIdentifier32 = new DERObjectIdentifier("0.9.2342.19200300.100.1.1");
        UID = dERObjectIdentifier32;
        DefaultReverse = false;
        Hashtable hashtable = new Hashtable();
        DefaultSymbols = hashtable;
        Hashtable hashtable2 = new Hashtable();
        RFC2253Symbols = hashtable2;
        Hashtable hashtable3 = new Hashtable();
        RFC1779Symbols = hashtable3;
        Hashtable hashtable4 = new Hashtable();
        DefaultLookUp = hashtable4;
        OIDLookUp = hashtable;
        SymbolLookUp = hashtable4;
        TRUE = Boolean.TRUE;
        FALSE = Boolean.FALSE;
        hashtable.put(dERObjectIdentifier, "C");
        hashtable.put(dERObjectIdentifier2, IShareAdapter.SHARE_ACTION_OPEN);
        hashtable.put(dERObjectIdentifier4, "T");
        hashtable.put(dERObjectIdentifier3, "OU");
        hashtable.put(dERObjectIdentifier5, "CN");
        hashtable.put(dERObjectIdentifier8, "L");
        hashtable.put(dERObjectIdentifier9, "ST");
        hashtable.put(dERObjectIdentifier6, "SERIALNUMBER");
        hashtable.put(dERObjectIdentifier28, "E");
        hashtable.put(dERObjectIdentifier31, la.p);
        hashtable.put(dERObjectIdentifier32, "UID");
        hashtable.put(dERObjectIdentifier7, "STREET");
        hashtable.put(dERObjectIdentifier10, "SURNAME");
        hashtable.put(dERObjectIdentifier11, "GIVENNAME");
        hashtable.put(dERObjectIdentifier12, "INITIALS");
        hashtable.put(dERObjectIdentifier13, "GENERATION");
        hashtable.put(dERObjectIdentifier30, "unstructuredAddress");
        hashtable.put(dERObjectIdentifier29, "unstructuredName");
        hashtable.put(dERObjectIdentifier14, "UniqueIdentifier");
        hashtable.put(dERObjectIdentifier17, "DN");
        hashtable.put(dERObjectIdentifier18, "Pseudonym");
        hashtable.put(dERObjectIdentifier25, "PostalAddress");
        hashtable.put(dERObjectIdentifier24, "NameAtBirth");
        hashtable.put(dERObjectIdentifier22, "CountryOfCitizenship");
        hashtable.put(dERObjectIdentifier23, "CountryOfResidence");
        hashtable.put(dERObjectIdentifier21, "Gender");
        hashtable.put(dERObjectIdentifier20, "PlaceOfBirth");
        hashtable.put(dERObjectIdentifier19, "DateOfBirth");
        hashtable.put(dERObjectIdentifier16, "PostalCode");
        hashtable.put(dERObjectIdentifier15, "BusinessCategory");
        hashtable.put(dERObjectIdentifier26, "TelephoneNumber");
        hashtable.put(dERObjectIdentifier27, "Name");
        hashtable2.put(dERObjectIdentifier, "C");
        hashtable2.put(dERObjectIdentifier2, IShareAdapter.SHARE_ACTION_OPEN);
        hashtable2.put(dERObjectIdentifier3, "OU");
        hashtable2.put(dERObjectIdentifier5, "CN");
        hashtable2.put(dERObjectIdentifier8, "L");
        hashtable2.put(dERObjectIdentifier9, "ST");
        hashtable2.put(dERObjectIdentifier7, "STREET");
        hashtable2.put(dERObjectIdentifier31, la.p);
        hashtable2.put(dERObjectIdentifier32, "UID");
        hashtable3.put(dERObjectIdentifier, "C");
        hashtable3.put(dERObjectIdentifier2, IShareAdapter.SHARE_ACTION_OPEN);
        hashtable3.put(dERObjectIdentifier3, "OU");
        hashtable3.put(dERObjectIdentifier5, "CN");
        hashtable3.put(dERObjectIdentifier8, "L");
        hashtable3.put(dERObjectIdentifier9, "ST");
        hashtable3.put(dERObjectIdentifier7, "STREET");
        hashtable4.put("c", dERObjectIdentifier);
        hashtable4.put("o", dERObjectIdentifier2);
        hashtable4.put("t", dERObjectIdentifier4);
        hashtable4.put("ou", dERObjectIdentifier3);
        hashtable4.put(AdvanceSetting.CLEAR_NOTIFICATION, dERObjectIdentifier5);
        hashtable4.put(NotifyType.LIGHTS, dERObjectIdentifier8);
        hashtable4.put("st", dERObjectIdentifier9);
        hashtable4.put("sn", dERObjectIdentifier6);
        hashtable4.put("serialnumber", dERObjectIdentifier6);
        hashtable4.put("street", dERObjectIdentifier7);
        hashtable4.put("emailaddress", dERObjectIdentifier28);
        hashtable4.put("dc", dERObjectIdentifier31);
        hashtable4.put(e.a, dERObjectIdentifier28);
        hashtable4.put("uid", dERObjectIdentifier32);
        hashtable4.put("surname", dERObjectIdentifier10);
        hashtable4.put("givenname", dERObjectIdentifier11);
        hashtable4.put("initials", dERObjectIdentifier12);
        hashtable4.put("generation", dERObjectIdentifier13);
        hashtable4.put("unstructuredaddress", dERObjectIdentifier30);
        hashtable4.put("unstructuredname", dERObjectIdentifier29);
        hashtable4.put("uniqueidentifier", dERObjectIdentifier14);
        hashtable4.put("dn", dERObjectIdentifier17);
        hashtable4.put("pseudonym", dERObjectIdentifier18);
        hashtable4.put("postaladdress", dERObjectIdentifier25);
        hashtable4.put("nameofbirth", dERObjectIdentifier24);
        hashtable4.put("countryofcitizenship", dERObjectIdentifier22);
        hashtable4.put("countryofresidence", dERObjectIdentifier23);
        hashtable4.put("gender", dERObjectIdentifier21);
        hashtable4.put("placeofbirth", dERObjectIdentifier20);
        hashtable4.put("dateofbirth", dERObjectIdentifier19);
        hashtable4.put("postalcode", dERObjectIdentifier16);
        hashtable4.put("businesscategory", dERObjectIdentifier15);
        hashtable4.put("telephonenumber", dERObjectIdentifier26);
        hashtable4.put("name", dERObjectIdentifier27);
    }

    public X509Name(ASN1Sequence aSN1Sequence) {
        Vector vector;
        String str;
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.seq = aSN1Sequence;
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Set aSN1Set = ASN1Set.getInstance(objects.nextElement());
            int i2 = 0;
            while (i2 < aSN1Set.size()) {
                ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Set.getObjectAt(i2));
                if (aSN1Sequence2.size() != 2) {
                    throw new IllegalArgumentException("badly sized pair");
                }
                this.ordering.addElement(DERObjectIdentifier.getInstance(aSN1Sequence2.getObjectAt(0)));
                DEREncodable objectAt = aSN1Sequence2.getObjectAt(1);
                if (!(objectAt instanceof DERString) || (objectAt instanceof DERUniversalString)) {
                    vector = this.values;
                    str = "#" + bytesToString(Hex.encode(objectAt.getDERObject().getDEREncoded()));
                } else {
                    str = ((DERString) objectAt).getString();
                    if (str.length() <= 0 || str.charAt(0) != '#') {
                        vector = this.values;
                    } else {
                        vector = this.values;
                        str = "\\".concat(String.valueOf(str));
                    }
                }
                vector.addElement(str);
                this.added.addElement(i2 != 0 ? TRUE : FALSE);
                i2++;
            }
        }
    }

    public X509Name(String str) {
        this(DefaultReverse, DefaultLookUp, str);
    }

    public X509Name(String str, X509NameEntryConverter x509NameEntryConverter) {
        this(DefaultReverse, DefaultLookUp, str, x509NameEntryConverter);
    }

    public X509Name(Hashtable hashtable) {
        this((Vector) null, hashtable);
    }

    public X509Name(Vector vector, Hashtable hashtable) {
        this(vector, hashtable, new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Hashtable hashtable, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        if (vector != null) {
            for (int i2 = 0; i2 != vector.size(); i2++) {
                this.ordering.addElement(vector.elementAt(i2));
                this.added.addElement(FALSE);
            }
        } else {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                this.ordering.addElement(keys.nextElement());
                this.added.addElement(FALSE);
            }
        }
        for (int i3 = 0; i3 != this.ordering.size(); i3++) {
            DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) this.ordering.elementAt(i3);
            if (hashtable.get(dERObjectIdentifier) == null) {
                throw new IllegalArgumentException("No attribute for object id - " + dERObjectIdentifier.getId() + " - passed to distinguished name");
            }
            this.values.addElement(hashtable.get(dERObjectIdentifier));
        }
    }

    public X509Name(Vector vector, Vector vector2) {
        this(vector, vector2, new X509DefaultEntryConverter());
    }

    public X509Name(Vector vector, Vector vector2, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        if (vector.size() != vector2.size()) {
            throw new IllegalArgumentException("oids vector must be same length as values.");
        }
        for (int i2 = 0; i2 < vector.size(); i2++) {
            this.ordering.addElement(vector.elementAt(i2));
            this.values.addElement(vector2.elementAt(i2));
            this.added.addElement(FALSE);
        }
    }

    public X509Name(boolean z, String str) {
        this(z, DefaultLookUp, str);
    }

    public X509Name(boolean z, String str, X509NameEntryConverter x509NameEntryConverter) {
        this(z, DefaultLookUp, str, x509NameEntryConverter);
    }

    public X509Name(boolean z, Hashtable hashtable, String str) {
        this(z, hashtable, str, new X509DefaultEntryConverter());
    }

    public X509Name(boolean z, Hashtable hashtable, String str, X509NameEntryConverter x509NameEntryConverter) {
        this.converter = null;
        this.ordering = new Vector();
        this.values = new Vector();
        this.added = new Vector();
        this.converter = x509NameEntryConverter;
        X509NameTokenizer x509NameTokenizer = new X509NameTokenizer(str);
        while (x509NameTokenizer.hasMoreTokens()) {
            String nextToken = x509NameTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf == -1) {
                throw new IllegalArgumentException("badly formated directory string");
            }
            String substring = nextToken.substring(0, indexOf);
            String substring2 = nextToken.substring(indexOf + 1);
            DERObjectIdentifier decodeOID = decodeOID(substring, hashtable);
            if (substring2.indexOf(43) > 0) {
                X509NameTokenizer x509NameTokenizer2 = new X509NameTokenizer(substring2, '+');
                String nextToken2 = x509NameTokenizer2.nextToken();
                this.ordering.addElement(decodeOID);
                this.values.addElement(nextToken2);
                Vector vector = this.added;
                Boolean bool = FALSE;
                while (true) {
                    vector.addElement(bool);
                    if (x509NameTokenizer2.hasMoreTokens()) {
                        String nextToken3 = x509NameTokenizer2.nextToken();
                        int indexOf2 = nextToken3.indexOf(61);
                        String substring3 = nextToken3.substring(0, indexOf2);
                        String substring4 = nextToken3.substring(indexOf2 + 1);
                        this.ordering.addElement(decodeOID(substring3, hashtable));
                        this.values.addElement(substring4);
                        vector = this.added;
                        bool = TRUE;
                    }
                }
            } else {
                this.ordering.addElement(decodeOID);
                this.values.addElement(substring2);
                this.added.addElement(FALSE);
            }
        }
        if (z) {
            Vector vector2 = new Vector();
            Vector vector3 = new Vector();
            Vector vector4 = new Vector();
            int i2 = 1;
            for (int i3 = 0; i3 < this.ordering.size(); i3++) {
                if (((Boolean) this.added.elementAt(i3)).booleanValue()) {
                    vector2.insertElementAt(this.ordering.elementAt(i3), i2);
                    vector3.insertElementAt(this.values.elementAt(i3), i2);
                    vector4.insertElementAt(this.added.elementAt(i3), i2);
                    i2++;
                } else {
                    vector2.insertElementAt(this.ordering.elementAt(i3), 0);
                    vector3.insertElementAt(this.values.elementAt(i3), 0);
                    vector4.insertElementAt(this.added.elementAt(i3), 0);
                    i2 = 1;
                }
            }
            this.ordering = vector2;
            this.values = vector3;
            this.added = vector4;
        }
    }

    private void appendValue(StringBuffer stringBuffer, Hashtable hashtable, DERObjectIdentifier dERObjectIdentifier, String str) {
        String str2 = (String) hashtable.get(dERObjectIdentifier);
        if (str2 == null) {
            str2 = dERObjectIdentifier.getId();
        }
        stringBuffer.append(str2);
        stringBuffer.append('=');
        int length = stringBuffer.length();
        stringBuffer.append(str);
        int length2 = stringBuffer.length();
        if (str.length() >= 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
            length += 2;
        }
        while (length != length2) {
            if (stringBuffer.charAt(length) == ',' || stringBuffer.charAt(length) == '\"' || stringBuffer.charAt(length) == '\\' || stringBuffer.charAt(length) == '+' || stringBuffer.charAt(length) == '=' || stringBuffer.charAt(length) == '<' || stringBuffer.charAt(length) == '>' || stringBuffer.charAt(length) == ';') {
                stringBuffer.insert(length, "\\");
                length++;
                length2++;
            }
            length++;
        }
    }

    private String bytesToString(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return new String(cArr);
    }

    private String canonicalize(String str) {
        String lowerCase = Strings.toLowerCase(str.trim());
        if (lowerCase.length() <= 0 || lowerCase.charAt(0) != '#') {
            return lowerCase;
        }
        ASN1Object decodeObject = decodeObject(lowerCase);
        return decodeObject instanceof DERString ? Strings.toLowerCase(((DERString) decodeObject).getString().trim()) : lowerCase;
    }

    private DERObjectIdentifier decodeOID(String str, Hashtable hashtable) {
        if (Strings.toUpperCase(str).startsWith("OID.")) {
            return new DERObjectIdentifier(str.substring(4));
        }
        if (str.charAt(0) < '0' || str.charAt(0) > '9') {
            DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) hashtable.get(Strings.toLowerCase(str));
            if (dERObjectIdentifier != null) {
                return dERObjectIdentifier;
            }
            throw new IllegalArgumentException("Unknown object id - " + str + " - passed to distinguished name");
        }
        return new DERObjectIdentifier(str);
    }

    private ASN1Object decodeObject(String str) {
        try {
            return ASN1Object.fromByteArray(Hex.decode(str.substring(1)));
        } catch (IOException e2) {
            throw new IllegalStateException("unknown encoding in name: ".concat(String.valueOf(e2)));
        }
    }

    private boolean equivalentStrings(String str, String str2) {
        String canonicalize = canonicalize(str);
        String canonicalize2 = canonicalize(str2);
        return canonicalize.equals(canonicalize2) || stripInternalSpaces(canonicalize).equals(stripInternalSpaces(canonicalize2));
    }

    public static X509Name getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public static X509Name getInstance(Object obj) {
        if (obj == null || (obj instanceof X509Name)) {
            return (X509Name) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new X509Name((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    private String stripInternalSpaces(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (str.length() != 0) {
            char charAt = str.charAt(0);
            stringBuffer.append(charAt);
            int i2 = 1;
            while (i2 < str.length()) {
                char charAt2 = str.charAt(i2);
                if (charAt != ' ' || charAt2 != ' ') {
                    stringBuffer.append(charAt2);
                }
                i2++;
                charAt = charAt2;
            }
        }
        return stringBuffer.toString();
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public boolean equals(Object obj) {
        int i2;
        int i3;
        boolean z;
        if (obj == this) {
            return true;
        }
        if ((obj instanceof X509Name) || (obj instanceof ASN1Sequence)) {
            if (getDERObject().equals(((DEREncodable) obj).getDERObject())) {
                return true;
            }
            try {
                X509Name x509Name = getInstance(obj);
                int size = this.ordering.size();
                if (size != x509Name.ordering.size()) {
                    return false;
                }
                boolean[] zArr = new boolean[size];
                int i4 = -1;
                if (this.ordering.elementAt(0).equals(x509Name.ordering.elementAt(0))) {
                    i4 = size;
                    i2 = 0;
                    i3 = 1;
                } else {
                    i2 = size - 1;
                    i3 = -1;
                }
                while (i2 != i4) {
                    DERObjectIdentifier dERObjectIdentifier = (DERObjectIdentifier) this.ordering.elementAt(i2);
                    String str = (String) this.values.elementAt(i2);
                    int i5 = 0;
                    while (true) {
                        if (i5 >= size) {
                            z = false;
                            break;
                        } else if (!zArr[i5] && dERObjectIdentifier.equals((DERObjectIdentifier) x509Name.ordering.elementAt(i5)) && equivalentStrings(str, (String) x509Name.values.elementAt(i5))) {
                            zArr[i5] = true;
                            z = true;
                            break;
                        } else {
                            i5++;
                        }
                    }
                    if (!z) {
                        return false;
                    }
                    i2 += i3;
                }
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return false;
    }

    public boolean equals(Object obj, boolean z) {
        if (z) {
            if (obj == this) {
                return true;
            }
            if ((obj instanceof X509Name) || (obj instanceof ASN1Sequence)) {
                if (getDERObject().equals(((DEREncodable) obj).getDERObject())) {
                    return true;
                }
                try {
                    X509Name x509Name = getInstance(obj);
                    int size = this.ordering.size();
                    if (size != x509Name.ordering.size()) {
                        return false;
                    }
                    for (int i2 = 0; i2 < size; i2++) {
                        if (!((DERObjectIdentifier) this.ordering.elementAt(i2)).equals((DERObjectIdentifier) x509Name.ordering.elementAt(i2)) || !equivalentStrings((String) this.values.elementAt(i2), (String) x509Name.values.elementAt(i2))) {
                            return false;
                        }
                    }
                    return true;
                } catch (IllegalArgumentException unused) {
                    return false;
                }
            }
            return false;
        }
        return equals(obj);
    }

    public Vector getOIDs() {
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.ordering.size(); i2++) {
            vector.addElement(this.ordering.elementAt(i2));
        }
        return vector;
    }

    public Vector getValues() {
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.values.size(); i2++) {
            vector.addElement(this.values.elementAt(i2));
        }
        return vector;
    }

    public Vector getValues(DERObjectIdentifier dERObjectIdentifier) {
        Vector vector = new Vector();
        for (int i2 = 0; i2 != this.values.size(); i2++) {
            if (this.ordering.elementAt(i2).equals(dERObjectIdentifier)) {
                String str = (String) this.values.elementAt(i2);
                if (str.length() > 2 && str.charAt(0) == '\\' && str.charAt(1) == '#') {
                    str = str.substring(1);
                }
                vector.addElement(str);
            }
        }
        return vector;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public int hashCode() {
        if (this.isHashCodeCalculated) {
            return this.hashCodeValue;
        }
        this.isHashCodeCalculated = true;
        for (int i2 = 0; i2 != this.ordering.size(); i2++) {
            String stripInternalSpaces = stripInternalSpaces(canonicalize((String) this.values.elementAt(i2)));
            this.hashCodeValue = stripInternalSpaces.hashCode() ^ this.hashCodeValue;
        }
        return this.hashCodeValue;
    }

    @Override // cn.com.union.fido.util.asn1.ASN1Encodable
    public DERObject toASN1Object() {
        DERSequence dERSequence;
        if (this.seq == null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            DERObjectIdentifier dERObjectIdentifier = null;
            int i2 = 0;
            while (i2 != this.ordering.size()) {
                ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
                DERObjectIdentifier dERObjectIdentifier2 = (DERObjectIdentifier) this.ordering.elementAt(i2);
                aSN1EncodableVector3.add(dERObjectIdentifier2);
                aSN1EncodableVector3.add(this.converter.getConvertedValue(dERObjectIdentifier2, (String) this.values.elementAt(i2)));
                if (dERObjectIdentifier == null || ((Boolean) this.added.elementAt(i2)).booleanValue()) {
                    dERSequence = new DERSequence(aSN1EncodableVector3);
                } else {
                    aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
                    aSN1EncodableVector2 = new ASN1EncodableVector();
                    dERSequence = new DERSequence(aSN1EncodableVector3);
                }
                aSN1EncodableVector2.add(dERSequence);
                i2++;
                dERObjectIdentifier = dERObjectIdentifier2;
            }
            aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
            this.seq = new DERSequence(aSN1EncodableVector);
        }
        return this.seq;
    }

    public String toString() {
        return toString(DefaultReverse, DefaultSymbols);
    }

    public String toString(boolean z, Hashtable hashtable) {
        StringBuffer stringBuffer = new StringBuffer();
        Vector vector = new Vector();
        StringBuffer stringBuffer2 = null;
        for (int i2 = 0; i2 < this.ordering.size(); i2++) {
            if (((Boolean) this.added.elementAt(i2)).booleanValue()) {
                stringBuffer2.append('+');
                appendValue(stringBuffer2, hashtable, (DERObjectIdentifier) this.ordering.elementAt(i2), (String) this.values.elementAt(i2));
            } else {
                stringBuffer2 = new StringBuffer();
                appendValue(stringBuffer2, hashtable, (DERObjectIdentifier) this.ordering.elementAt(i2), (String) this.values.elementAt(i2));
                vector.addElement(stringBuffer2);
            }
        }
        boolean z2 = true;
        if (z) {
            for (int size = vector.size() - 1; size >= 0; size--) {
                if (z2) {
                    z2 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(size).toString());
            }
        } else {
            for (int i3 = 0; i3 < vector.size(); i3++) {
                if (z2) {
                    z2 = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append(vector.elementAt(i3).toString());
            }
        }
        return stringBuffer.toString();
    }
}
