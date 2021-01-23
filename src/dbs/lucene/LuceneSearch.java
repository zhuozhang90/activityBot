package dbs.lucene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
public class LuceneSearch {
    public static String getTotalSteps(String date) throws IOException, ParseException, IOException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 1. create the index
        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);

        // add all docs to indexwriter
        List<String> allLines = Files.readAllLines(Paths.get("C:\\Users\\zzhang\\Projects\\BU_MET\\eclipse-workspace\\cs622project\\activityplain.txt"));
        for (String line : allLines) {
            String[] fields = line.split(",");
            String stepCount = fields[0].split(":")[1];
            String stepDelta = fields[1].split(":")[1];
            String time = fields[2].split(":")[1].split(" ")[0];
            String sensor = fields[3].split(":")[1];
//            System.out.println(time + " - " + stepCount);
            addActivityDoc(w, sensor, time, stepCount, stepDelta);
        }
        w.close();

        // 2. query

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = new QueryParser("time", analyzer).parse("\"" + date + "\"");

        // 3. search
        int hitsPerPage = 100;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        // 4. display results
//        System.out.println("Found " + hits.length + " hits.");
        int totalSteps = 0;
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            int step = Integer.parseInt(d.get("step_count"));
            if (step > totalSteps) totalSteps = step;
//            System.out.println((i + 1) + ". " + d.get("step_count") + "\t" + d.get("time"));
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close();
        return "You had " + totalSteps + " steps on " + date;
    }

    public static String getRunningData(String date) throws IOException, ParseException, IOException {
        // 0. Specify the analyzer for tokenizing text.
        //    The same analyzer should be used for indexing and searching
        StandardAnalyzer analyzer = new StandardAnalyzer();

        // 1. create the index
        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter w = new IndexWriter(index, config);

        // add all docs to indexwriter
        List<String> allLines = Files.readAllLines(Paths.get("C:\\Users\\zzhang\\Projects\\BU_MET\\eclipse-workspace\\cs622project\\activfitplain.txt"));
        for (String line : allLines) {
            String[] fields = line.split(",");
            String startTime = fields[1].split(":")[1].split(" ")[0];
            String endTime = fields[3].split(":")[1].split(" ")[0];
            String activity = fields[2].split(":")[1].replaceAll("^\"|\"$", "");
            String sensor = fields[4].split(":")[1];
//            System.out.println(startTime + " - " + activity);
            addActiFitDoc(w, sensor, startTime, endTime, activity);
        }
        w.close();

        // 2. query

        // the "title" arg specifies the default field to use
        // when no field is explicitly specified in the query.
        Query q = new QueryParser("start_time", analyzer).parse("\"" + date + "\"");

        // 3. search
        int hitsPerPage = 100;
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;

        // 4. display results
//        System.out.println("Found " + hits.length + " hits.");

        int runTimes = 0;
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            if (d.get("activity").contains("running")) runTimes++;
//            System.out.println((i + 1) + ". " + d.get("start_time") + "\t" + d.get("activity"));
        }

        // reader can only be closed when there
        // is no need to access the documents any more.
        reader.close();

        return (runTimes == 0) ? "You didn't run that day" :  "You ran on " + date;
    }

    private static void addActiFitDoc(IndexWriter w, String sensor, String startTime,
                                       String endTime, String activity) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("sensor_name", sensor, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new TextField("start_time", startTime, Field.Store.YES));
        doc.add(new StringField("end_time", endTime, Field.Store.YES));
        doc.add(new TextField("activity", activity, Field.Store.YES));
        w.addDocument(doc);
    }

    private static void addActivityDoc(IndexWriter w, String sensor, String time,
                                       String stepCount, String stepDelta) throws IOException {
        Document doc = new Document();
        doc.add(new StringField("sensor_name", sensor, Field.Store.YES));

        // use a string field for isbn because we don't want it tokenized
        doc.add(new TextField("time", time, Field.Store.YES));
        doc.add(new StringField("step_count", stepCount, Field.Store.YES));
        doc.add(new StringField("step_delta", stepDelta, Field.Store.YES));        w.addDocument(doc);
    }

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(getRunningData("2016-06-08"));
        System.out.println(getTotalSteps("2017-06-08"));
    }
}
