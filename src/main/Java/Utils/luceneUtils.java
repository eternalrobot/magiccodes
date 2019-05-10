package Utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.python.antlr.ast.Str;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.*;

public class luceneUtils {
    static FieldType onlyStoredType = new FieldType();
    static FieldType indexedTermVectorsStoredType = new FieldType();
    static FieldType indexedAllStoredType = new FieldType();
    static {
        onlyStoredType.setTokenized(false);
        onlyStoredType.setIndexOptions(IndexOptions.NONE);
        onlyStoredType.setStored(true);
        onlyStoredType.freeze();
        indexedTermVectorsStoredType.setStored(true);
        indexedTermVectorsStoredType.setTokenized(true);
        indexedTermVectorsStoredType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        indexedTermVectorsStoredType.setStoreTermVectors(true);
        indexedTermVectorsStoredType.setStoreTermVectorPositions(true);
        indexedTermVectorsStoredType.setStoreTermVectorOffsets(true);
        indexedTermVectorsStoredType.freeze();
        indexedAllStoredType.setStored(true);
        indexedAllStoredType.setTokenized(true);
        indexedAllStoredType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        indexedAllStoredType.freeze();
    }

    public static void EstablishLuceneForWebUrlAndContent(String lucenefilepath, List<String> urls, List<String> contents, List<String> titles) throws IOException {
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        Directory directory = FSDirectory
                .open((new File(lucenefilepath)).toPath());
        IndexWriter writer = new IndexWriter(directory, config);
        for (int i = 0; i < urls.size(); i++) {
            Document doc = new Document();

            doc.add(new Field("url", urls.get(i), onlyStoredType));

            doc.add(new Field("content", contents.get(i), indexedTermVectorsStoredType));

            doc.add(new Field("title", titles.get(i), indexedAllStoredType));

            writer.addDocument(doc);
            writer.flush();
        }
        writer.commit();
        writer.close();
        directory.close();
    }

    public static Map<String,String> searchForWeb(String indexDir, String par, String aim) throws Exception {
        Map<String,String> result=new HashMap<String, String>();
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        //建立查询解析器
        /*
         * 第一个参数是要查询的字段；
         * 第二个参数是分析器Analyzer
         * */
        QueryParser parser = new QueryParser(aim, analyzer);
        //根据传进来的par查找
        Query query = parser.parse(par);
        //计算索引开始时间
        long start = System.currentTimeMillis();
        //开始查询
        /*
         * 第一个参数是通过传过来的参数来查找得到的query；
         * 第二个参数是要出查询的行数
         * */
        TopDocs topDocs = searcher.search(query, 10);
        //索引结束时间
        long end = System.currentTimeMillis();
        System.out.println("匹配" + par + ",总共花费了" + (end - start) + "毫秒,共查到" + topDocs.totalHits + "条记录。");
        //高亮显示start
        //算分
        QueryScorer scorer = new QueryScorer(query);
        //显示得分高的片段
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        //设置标签内部关键字的颜色
        //第一个参数：标签的前半部分；第二个参数：标签的后半部分。
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        //第一个参数是对查到的结果进行实例化；第二个是片段得分（显示得分高的片段，即摘要）
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        //设置片段
        highlighter.setTextFragmenter(fragmenter);
        //高亮显示end
        //遍历topDocs
        /*
         * ScoreDoc:是代表一个结果的相关度得分与文档编号等信息的对象。
         * scoreDocs:代表文件的数组
         * @throws Exception
         * */
        int i=0;
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            //获取文档
            Document document = searcher.doc(scoreDoc.doc);
            //输出全路径
            result.put("url"+i,document.get("url"));
            result.put("title"+i,document.get("title"));
            result.put("content"+i,document.get("content"));
            i++;
            String desc = document.get(aim);
            if (desc != null) {
                //把全部得分高的摘要给显示出来
                //第一个参数是对哪个参数进行设置；第二个是以流的方式读入
                TokenStream tokenStream = analyzer.tokenStream(aim, new StringReader(desc));
                //获取最高的片段
                result.put("hightlight"+i,highlighter.getBestFragment(tokenStream, desc));
            }
        }
        reader.close();
        return result;
    }
//
//    public static void EstablishLuceneForFile(File file,String lucenefilepath) throws IOException {
//        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
//        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
//        Directory directory = FSDirectory
//                .open((new File(lucenefilepath)).toPath());
//        IndexWriter writer = new IndexWriter(directory, config);
//        Document doc = new Document();
//        doc.add(new StringField("size",String.valueOf(file.length()),Field.Store.YES));
//        doc.add(new TextField("content",Objects.requireNonNull(fileUtils.ReadFileToString(file)),Field.Store.YES));
//        doc.add(new StringField("name",file.getName(),Field.Store.YES));
//        writer.addDocument(doc);
//        writer.commit();
//        writer.close();
//        directory.close();
//    }
}
