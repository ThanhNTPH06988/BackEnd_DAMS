package com.tamdt.pmbackend.common;

import java.util.HashMap;
import java.util.Map;

public interface AppConstant {

    String PASSWORD_DEFAULT = "1qaz@123";
    String[] ARR_PASS = {"A","B","C","D","D"};
    Long ZERO = 0L;

    Map<Integer,String> headerExcelStaff = new HashMap<Integer, String>(){{
        put(1,"STT");
        put(2,"Mã NV");
        put(3,"Tên NV");
        put(4,"Giới tính");
        put(5,"Ngày sinh");
        put(6,"Số CMND");
        put(7,"Email");
        put(8,"Điện thoại");
        put(9,"Địa chỉ");
        put(10,"Chức vụ");
    }};

    interface DELETED{
        String YES = "Y";
        String NO = "N";
    }

    interface CONFIRM{
        Long YES = 1L;
        Long NO = 0L;
    }

    interface IS_PROCESSS{
        Long UN_DISTRIBUTE = 0L;
        Long DISTRIBUTE = 1L;
    }


    interface MENU{
        Long LEVEL1 = 1L;
        Long LEVEL2 = 2L;
        Long SUBMENU = 1L;
        Long NO_SUBMENU = 0L;
    }

    interface STATUS{
        Long ACTIVE = 1L;
        Long INACTIVE = 0L;
    }

    interface DATE_FORMAT{
        String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
        String DD_MM_YYYY = "dd/MM/yyyy"; //07/06/2013
        String DD_MMM_YYYY = "dd-MMM-yyyy"; //7-Jun-2013
        String E_MMM_DD_YYYY = "E, MMM dd yyyy"; //Fri, June 7 2013
        String EEEE_MMM_DD_YYYY_FULL_TIME = "EEEE, MMM dd, yyyy HH:mm:ss a"; //Friday, Jun 7, 2013 12:10:56 PM
        String YYYY_MM_DD_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ"; //2014-10-05T15:23:01Z
    }

    interface STAFF_POSITION {
        Long NO_STAFF = 0L;
        Long MANAGER = 1L;
        Long WAREHOUSE_STAFF = 2L;
        Long CASHIER = 3L;
        Long WAREHOUSE = 4L;
    }

    interface IS_PROCESS {
        Long NO_ACTIVE = 0L;
        Long ACTIVE = 1L;
    }

    interface STAFF_ID {
        Long NO_ID = 0L;
    }

    interface IS_RUN {
        Long NO_RUN = 0L;
        Long RUN = 1L;
    }
    interface ADDR_RANK {
        Long LEVEL1 = 1L;
        Long LEVEL2 = 2L;
        Long LEVEL3 = 3L;
    }
    interface ACTIVE {
        Long ACTIVE = 1L;
        Long NO_ACTIVE = 0L;
    }
    interface PASS_CHANGE {
        String NO = "N";
        String YES = "Y";
    }
    interface CASHIER_STATUS_CONFIRM{
        Long CONFIRM = 1L;
        Long CANCEL = 2L;
    }

    interface DRIVER_CONFIRM{
        Long CONFIRM = 1L;
        Long CANCEL = 2L;
    }

}
