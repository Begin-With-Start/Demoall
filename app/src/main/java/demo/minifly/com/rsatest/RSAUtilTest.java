package demo.minifly.com.rsatest;


import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RSAUtilTest {

    private static String priKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM5Mn+YAcCiJxTnN+t0c7CW253fV6K5boVLXWG+hp44+0kO1YczM8A05JuThSWqiN6KxsJjDpwsPX1PHc018o63Xea0G27IM2c/uwJw+0sEHxDxxGB/oApiDhY3ZY28q6KNFs2suUuFvbd+ThaBU2DTF2ZmY77AUwltTTH4kJqeRAgMBAAECgYAxrQfP1YksachhG/Vtavp+NmXUWGD4Iz5ceVhlQRLc0MnG3hVXwH2JYZq5AHqyP6+P8KOqlWpS4ne878kW/o1W7ufFq+RHdjzONWAXhwczTO/Hlm4W1t23XHNGgjZNbOySNekHHhdlCDpWji9jv1RAYTug4lxD7LHRPkJ1Vw5EAQJBAPv3ord3L7m22UFc+UTRpg2LVOwGQ0ZCvueg94P3lVYxsKaEko3lGOEplHTnrHL/3tP+DbJu264QFZ2eMKQhMVECQQDRmeCXrcc+aqyKJmqN69ljtoDMZ0AS209tSvs+OhJDgt9whkhUZ3BIEWiN2jf4fbU5o9aN7bDwwAeT+e+2ToJBAkEAzamKij/hl6+001JFMDQDWujQbzqJbO8MDdpoTbInlcxHSRPK38EQtrCOuYFkVBWf1t92ZJ9x9jiwvA+j2kQxkQJBAIQ/yiUKIegxGJLGcH8BrBWtRpEyKIx9Uh1MrT/zFikAv4jZQXP4HDFswoKWiLBCaQFobez5Z6ZQE7f7gJOP78ECQD65UdIPI/CnBEj/wrsPCfnr0rJ6pp+SeGVqbaoumPSUVERjjMCmOfvNNK5JqoXum/rZTDxSA7LFsSbG29EfRC8=";
    private static String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOTJ/mAHAoicU5zfrdHOwltud31eiuW6FS11hvoaeOPtJDtWHMzPANOSbk4UlqojeisbCYw6cLD19Tx3NNfKOt13mtBtuyDNnP7sCcPtLBB8Q8cRgf6AKYg4WN2WNvKuijRbNrLlLhb23fk4WgVNg0xdmZmO+wFMJbU0x+JCankQIDAQAB";

    public static String sign(String inputStr) throws Exception {
        byte[] encodedData = RSAUtil.encryptByPublicKey(toBytes(inputStr), pubKey);
        String sign = encodeBase64(encodedData);
        return sign;
    }

    public static String resign(String inputStr) throws Exception {
        byte[] decodeBase64 = decodeBase64(inputStr);
        byte[] decodedData = RSAUtil.decryptByPrivateKey(decodeBase64, priKey);

        String outputStr = toString(decodedData);

        return outputStr;
    }

    public static void testRsa() throws Exception {
        System.err.println("公钥加密—�?�私钥解�?");
        String inputStr = "123456";

        String encryptStr = sign(inputStr);
        System.err.println("加密�?: " + inputStr + "\n\r" + "BASE64加密�?: " + encryptStr);

        String decryptStr = resign(encryptStr);
        System.err.println("解密�?: " + decryptStr);

//        String sign = "NO/bzeCSoUE4VtSVeVf3O7x6PkrbctwIfM8Y5REF17aEnSksh/W/28KKjm/ETFZuAcIZmnPGwX2DXt+yorKpveb5+SWW67kdWJOcRjCtV3MUUu/zqfm91J17kyOK0KsNaKwcB2vKewAVYaHgHpKdjqhWKUepb067Cge49B6PVT8=";
        String sign = "Iljf/y7axMv/3QsvNLNgMi7Cgp1sGdgL22FP5ZNfRDDGxgu9PdprcCOTcUDSFn6luwVa54HKFdPmfW2X45lH2BlZc+8giCwiF7GBzZMTQNoGRxYyW88DSgZNhNLmw2XH2EOwVz8UTil9eII4YQ3pQuxRWvt2IVCjiIN4vFf9Zuk=";

        resign(sign);
    }

    public static void testResign() throws Exception {
//        String sign = "NO/bzeCSoUE4VtSVeVf3O7x6PkrbctwIfM8Y5REF17aEnSksh/W/28KKjm/ETFZuAcIZmnPGwX2DXt+yorKpveb5+SWW67kdWJOcRjCtV3MUUu/zqfm91J17kyOK0KsNaKwcB2vKewAVYaHgHpKdjqhWKUepb067Cge49B6PVT8=";
        String sign = "Iljf/y7axMv/3QsvNLNgMi7Cgp1sGdgL22FP5ZNfRDDGxgu9PdprcCOTcUDSFn6luwVa54HKFdPmfW2X45lH2BlZc+8giCwiF7GBzZMTQNoGRxYyW88DSgZNhNLmw2XH2EOwVz8UTil9eII4YQ3pQuxRWvt2IVCjiIN4vFf9Zuk=";

        String resign = resign(sign);

        System.err.println("sign: " + sign);
        System.err.println("解密�?: " + resign);
    }

    public static void main(String[] args) throws Exception{
    	testRsa();
    	
        testResign();
    }


    public static String toString(byte[] bytes) {
        return toString(bytes, 0, bytes.length, "utf-8");
    }

    public static String toString(byte[] bytes, String charset) {
        return toString(bytes, 0, bytes.length, charset);
    }

    public static String toString(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    public static String toString(byte[] data, int offset, int length, String charset) {
        if (data == null) {
            return null;
        }
        try {
            return new String(data, offset, length, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static byte[] toBytes(String data) {
        return toBytes(data, "utf-8");
    }

    public static byte[] toBytes(String data, String charset) {
        if (data == null) {
            return null;
        }
        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String encodeBase64(byte[] bytes) throws IOException {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static byte[] decodeBase64(String base64String) {
        return Base64.decode(base64String, Base64.NO_WRAP);
    }
}