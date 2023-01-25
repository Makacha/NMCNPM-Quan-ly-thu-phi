package com.quanlythuphi.constants;

public class Constants {
    public static final String ALL = "Tất cả";
    public static final String PHI_BAT_BUOC = "Phí bắt buộc";
    public static final String PHI_TU_NGUYEN = "Phí tự nguyện";
    public static final String HO_NGHEO = "Hộ nghèo";
    public static final String HO_CAN_NGHEO = "Hộ cận nghèo";
    public static final String HO_BINH_THUONG = "Hộ bình thường";

    public static boolean validateNumber(String s) {
        return s.matches("^[0-9]+$");
    }

    public static Integer mapCheDo(String s) {
        if (s == null)
            return null;
        if (s.equals(HO_NGHEO))
            return 2;
        if (s.equals(HO_CAN_NGHEO))
            return 1;
        return 0;
    }

    public static String mapCheDo(Integer i) {
        if (i == null)
            return null;
        if (i == 2)
            return HO_NGHEO;
        if (i == 1)
            return HO_CAN_NGHEO;
        return HO_BINH_THUONG;
    }
}
