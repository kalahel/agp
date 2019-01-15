package com.ucp;

import java.io.*;
import java.nio.file.*;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;

public class LuceneLauncher {
    public final static String TEXT_PATH = "C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\texts";
    public final static String INDEX_PATH = "C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\index";

    public static void main(String[] args) {
        int MAX_RESULTS = 100; //nombre max de réponses retournées
        try {
            LuceneLauncher.indexDocuments();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1. Specifier l'analyseur pour le texte.
        //    Le même analyseur est utilisé pour l'indexation et la recherche
        Analyzer analyseur = new StandardAnalyzer();

        // 2. Creation de l'index
//	    Directory index = new RAMDirectory();  //création index en mémoire
        Path indexpath = FileSystems.getDefault().getPath("C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\index"); //localisation index
        try {
            Directory index = FSDirectory.open(indexpath);  //création index sur disque

            // 4. Interroger l'index
            DirectoryReader ireader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(ireader); //l'objet qui fait la recherche dans l'index
            String reqstr = "ouvrier zola femme mine";

            //Parsing de la requete en un objet Query
            //  "contenu" est le champ interrogé par defaut si aucun champ n'est precisé
            QueryParser qp = new QueryParser("contenu", analyseur);
            Query req = qp.parse(reqstr);

            TopDocs resultats = searcher.search(req, MAX_RESULTS); //recherche

            // 6. Affichage resultats
            System.out.println(resultats.totalHits + " documents correspondent");
            for (int i = 0; i < resultats.scoreDocs.length; i++) {
                int docId = resultats.scoreDocs[i].doc;
                Document d = searcher.doc(docId);
                System.out.println(d.get("nom") + ": score " + resultats.scoreDocs[i].score);
            }

            // fermeture seulement quand il n'y a plus besoin d'acceder aux resultats
            ireader.close();
        } catch (Exception e) {
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
