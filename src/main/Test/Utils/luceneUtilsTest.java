package Utils;

import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.*;

public class luceneUtilsTest {

    @Test
    public void search() {
        try {
            Map<String,String> result=luceneUtils.searchForWeb(
                    "D:\\ideaWorkSpace\\daimafenxiang\\src\\main\\Resource\\insectweb\\lucene1",
                    "洪水","content");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}