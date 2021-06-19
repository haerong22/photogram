package com.photogram.web.utils;

public class Script {

    public static String back(String messsage) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('"+messsage+"');");
        sb.append("history.back();");
        sb.append("</script>");

        return sb.toString();
    }

}
