package org.rodriguez.uiautomation.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {
    private static Logger log = LogManager.getLogger(FileUtil.class);
    public static String usrTstRsrcDir = System.getProperty("user.dir")+"/src/test/resources/";
    public static String usrTstLogDir = System.getProperty("user.dir")+"/src/test/logs";


}
