package com.quanlythuphi.constants;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static final String ALL = "Tất cả";
    public static final String PHI_BAT_BUOC = "Phí bắt buộc";
    public static final String PHI_TU_NGUYEN = "Phí tự nguyện";
    public static final String DA_NOP = "Đã nộp";
    public static final String CHUA_NOP = "Chưa nộp";
    public static final String HO_NGHEO = "Hộ nghèo";
    public static final String HO_CAN_NGHEO = "Hộ cận nghèo";
    public static final String HO_BINH_THUONG = "Hộ bình thường";
    public static final String GIOI_TINH_NAM = "Nam";
    public static final String GIOI_TINH_NU = "Nữ";
    public static final String GIOI_TINH_KHAC = "Khác";
    public static final String KET_HON = "Đã kết hôn";
    public static final String DOC_THAN = "Độc thân";
    public static final String LY_HON = "Ly hôn";
    public static final String THUONG_TRU = "Thường trú";
    public static final String TAM_TRU = "Tạm trú";

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

    public static Boolean mapLoaiPhi(String s) {
        if (s == null)
            return null;
        if (s.equals(DA_NOP))
            return true;
        if (s.equals(CHUA_NOP))
            return false;
        return null;
    }

    public static String doiSoThanhTien(Long i) {
        if (i == null)
            return null;
        Locale locale = new Locale("vi", "VN");
        NumberFormat format =  NumberFormat.getCurrencyInstance(locale);

        return format.format(i);
    }

    public static String mapLoaiPhi(Boolean i) {
        if (i == null)
            return null;
        if (i.equals(Boolean.TRUE))
            return DA_NOP;
        if (i.equals(Boolean.FALSE))
            return CHUA_NOP;
        return null;
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

    public static String mapDateToString(Date date) {
        if (date == null)
            return null;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
