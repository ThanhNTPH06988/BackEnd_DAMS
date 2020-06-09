package com.tamdt.pmbackend.common.helper;

public class ExceptionCode {

    public static final String NOT_FOUND = "not.found";

    public static final String UNDIFINED_ERROR = "Có lỗi mới phát sinh. Xin vui lòng thông báo với quản trị viên. Xin cảm ơn!";

    public static String buildMessage(Exception e) {
        StringBuilder causeBuilder = new StringBuilder(e.toString());

        String cause = causeBuilder.toString();
        StringBuilder message = new StringBuilder();

        if (cause.contains(NOT_FOUND)) {
            message.append(NOT_FOUND);
        } else
            message.append(UNDIFINED_ERROR);

        return message.toString();
    }

}
