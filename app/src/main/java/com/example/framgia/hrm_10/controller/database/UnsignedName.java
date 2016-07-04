package com.example.framgia.hrm_10.controller.database;

/**
 * Created by framgia on 04/07/2016.
 */
public class UnsignedName {
    public static final String SPECIAL_CHARACTERS_TEMP = "àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰỳỲỷỶỹỸỵỴýÝ :+\\<>\"*,!?%$=@#~[]`|^";
    public static final String REPLACEMENTS_TEMP = "aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeEiIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOuUuUuUuUuUuUuUuUuUuUuUyYyYyYyYyY ___\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";
    private static final int INDEX_NULL = 0;
    public static char[] REPLACEMENTS = REPLACEMENTS_TEMP.toCharArray();

    public static char removeAccent(char ch) {
        int index = SPECIAL_CHARACTERS_TEMP.indexOf(ch);
        if (index >= INDEX_NULL) {
            ch = REPLACEMENTS[index];
        }
        return ch;
    }

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }
}
