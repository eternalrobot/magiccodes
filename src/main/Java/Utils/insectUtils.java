package Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class insectUtils {
    private static Document getDocumentByUrl(String url) throws IOException {
        Document document = Jsoup.connect(url).data("query", "Java")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/73.0.3683.103 Safari/537.36").get();
        return document;
    }

    public static void getBaiduContent() {
        List<String> theurls=new ArrayList<String>();
        List<String> thecontents=new ArrayList<String>();
        List<String> thetitle=new ArrayList<String>();
        try {
            Queue<String> urls = new ArrayDeque<String>();
            String beginurl = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E7%83" +
                    "%AD%E6%90%9C&oq=%25E8%25A7%2586%25E8%25A7%2589%25E4%25B8%25AD%25E5%259B%25BD%25E5%2586%258" +
                    "D%25E6%25AC%25A1%25E8%2587%25B4%25E6%25AD%2589&rsv_pq=ebbfec440000f7c6&rsv_t=f5d9foCQorBDy7" +
                    "ZHJt5ggv%2FWJaFeB39nxJqulUp" +
                    "sNyqupZ5zER56FVrdjlY&rqlang=cn&rsv_enter=1&rsv_sug3=7&rsv_sug1=5&rsv_sug7=100&rsv_sug2=0&inputT=1644&rsv_sug4=1643";
            ((ArrayDeque<String>) urls).push(beginurl);
            Document begindocument=getDocumentByUrl(((ArrayDeque<String>) urls).pop());
            Elements beginelements=begindocument.select(
                    "#con-ar > div:nth-child(2) > div > div > table > tbody");
            for(Element T :beginelements){
                Elements t=T.select("tr>td>span>a");
                for(Element s:t){
                    ((ArrayDeque<String>) urls).push("https://www.baidu.com"+s.attr("href"));
                }
            }

            for (int i = 0; i < 25; i++) {
                System.out.println(i);
                Document document=getDocumentByUrl(((ArrayDeque<String>) urls).pop());
                Elements result = document.select(".result");
                for (Element t : result) {
                    Elements url = t.select("h3>a");
                    Document resultdocument=getDocumentByUrl(url.get(0).attr("href"));
                    theurls.add(resultdocument.baseUri());
                    thecontents.add(t.text() + resultdocument.text());
                    thetitle.add(url.get(0).text());
                }
                Elements nextUrls = document.select("#rs > table > tbody > tr:nth-child(2) > th:nth-child(3)>a");
                ((ArrayDeque<String>) urls).push("https://www.baidu.com" + nextUrls.get(0).attr("href"));
            }
            System.out.println("over");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("开始建立索引");
        try {
            luceneUtils.EstablishLuceneForWebUrlAndContent(
                    "D:\\ideaWorkSpace\\daimafenxiang\\src\\main\\Resource\\insectweb\\lucene1",
                    theurls,thecontents,thetitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
