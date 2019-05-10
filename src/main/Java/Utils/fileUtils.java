package Utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class fileUtils {
    static Logger logger=Logger.getLogger(fileUtils.class);

    public static void saveStringToFile(String thesavething,String filename,boolean isappend){
        try {
            File writeName = new File(filename);
            try (FileWriter writer = new FileWriter(writeName,isappend);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(thesavething);
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            logger.warn("保存string失败："+thesavething);
            e.printStackTrace();
        }
    }

    public static String ReadFileToString(File file){
        try {
            StringBuilder result=new StringBuilder();
            List<String> T=FileUtils.readLines(file);
            for(String t:T){
                result.append(t);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
