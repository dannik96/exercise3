package cz.cvut.fel.omo.cv3.common;

public class TextUtils {

    public static String byteArrayToHex(byte[] hash) {
        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
