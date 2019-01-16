package com.ucp;

import com.ucp.configuration.ConfigurationEntry;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class LuceneLauncher {


    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try {
            LuceneLauncher.indexDocuments();
        } catch (IOException e) {
            e.printStackTrace();
        }

        LuceneIterator luceneIterator = new LuceneIterator();
        try {
            luceneIterator.init("volcano");
            while (luceneIterator.hasNext())
                System.out.println(luceneIterator.next().toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public static void indexDocuments() throws IOException {

        Analyzer analyseur = new StandardAnalyzer();

        Path indexpath = FileSystems.getDefault().getPath(ConfigurationEntry.RELATIVE_INDEX_PATH);
        Directory index = FSDirectory.open(indexpath);

        IndexWriterConfig config = new IndexWriterConfig(analyseur);
        IndexWriter indexWriter = new IndexWriter(index, config);

        File dir = new File(ConfigurationEntry.RELATIVE_TEXT_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {

                Document doc = new Document();
                doc.add(new Field("nom", child.getName(), TextField.TYPE_STORED));
                doc.add(new Field("contenu", new FileReader(child), TextField.TYPE_NOT_STORED));
                indexWriter.addDocument(doc);

            }
        } else {
            System.err.println("_indexDocuments_ : Else hit");
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
        indexWriter.close(); //on ferme le index writer après l'indexation de tous les documents

    }
}
