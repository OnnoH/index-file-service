package com.semantica.yada.indexfileservice;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class IndexFile {

    private static final Tika tika = new Tika();


    public static void indexFile(String uuid) throws Exception {
        try {
            URL url = new URL("http://localhost:8181/api/filedownloadservice/v1/files/" + uuid);
            Document document = new Document();
            document.add(new TextField("id", uuid, Field.Store.YES));
            String fullText = tika.parseToString(url);
            document.add(new TextField("fulltext", fullText, Field.Store.YES));
            indexDocument(document);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
        }
    }

    private static void indexDocument(Document docToAdd) throws Exception
    {
        String indexDirectory = "/tmp/index-dir";
        IndexWriter writer = null;
        try
        {
            Directory indexWriteToDir =
                    FSDirectory.open(Paths.get(indexDirectory));

            writer = new IndexWriter(indexWriteToDir, new IndexWriterConfig());
            writer.addDocument(docToAdd);
            writer.flush();
            writer.commit();
        }
        finally
        {
            if (writer != null)
            {
                writer.close();
            }
        }
    }

}
