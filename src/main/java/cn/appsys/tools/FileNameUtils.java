package cn.appsys.tools;

import java.util.Date;

public class FileNameUtils {
    public static String getNewFileName(String oldFileName) {
        Date date = new Date();
        String newFileName = date.getTime()+"_APPLOGO."+getFileSuffix(oldFileName);
        return newFileName;
    }


    public static String getFileSuffix(String oldFileName) {
        String suffix = oldFileName.substring(oldFileName.lastIndexOf(".")+1);
        return suffix;
    }
}
