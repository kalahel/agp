package com.ucp;

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
    // TODO CHANGE WITH CORRECT FILES
    // TODO MAKE IT RELATIVE
    //public final static String TEXT_PATH = "C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\texts";
    public final static String TEXT_PATH = "C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\descriptions";
    public final static String INDEX_PATH = "C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\index";

    public static void main(String[] args) {
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

        // 1. Specifier l'analyseur pour le texte.
        //    Le même analyseur est utilisé pour l'indexation et la recherche
        Analyzer analyseur = new StandardAnalyzer();

        // 2. Creation de l'index
//	    Directory index = new RAMDirectory();  //création index en mémoire
        Path indexpath = FileSystems.getDefault().getPath(LuceneLauncher.INDEX_PATH); //localisation index
        Directory index = FSDirectory.open(indexpath);  //création index sur disque

        IndexWriterConfig config = new IndexWriterConfig(analyseur);
        IndexWriter indexWriter = new IndexWriter(index, config);

        File dir = new File(LuceneLauncher.TEXT_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // 3. Indexation des documents
                //    Ici on indexe seulement un fichier
                Document doc = new Document();
                doc.add(new Field("nom", child.getName(), TextField.TYPE_STORED));
                doc.add(new Field("contenu", new FileReader(child), TextField.TYPE_NOT_STORED));
                indexWriter.addDocument(doc);
                //indexer les autres documents de la même façon

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
