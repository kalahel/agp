package com.ucp;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class LuceneIterator {
    private final static int MAX_RESULTS = 100;
    private int currentIndex;
    private int numberOfResult;
    private TopDocs documentResult;
    private IndexSearcher searcher;
    private DirectoryReader directoryReader;

    public LuceneIterator() {
        this.currentIndex = 0;
        this.numberOfResult = 0;
        this.documentResult = null;
        this.searcher = null;
        this.directoryReader = null;

    }


    public void init(String searchWords) throws IOException, ParseException {

        // 1. Specifier l'analyseur pour le texte.
        //    Le même analyseur est utilisé pour l'indexation et la recherche
        Analyzer analyseur = new StandardAnalyzer();

        // 2. Creation de l'index
//	    Directory index = new RAMDirectory();  //création index en mémoire
        Path indexpath = FileSystems.getDefault().getPath("C:\\Fac\\M1\\COO\\agp\\src\\main\\resources\\index"); //localisation index

        Directory index = FSDirectory.open(indexpath);  //création index sur disque

        // 4. Interroger l'index
        this.directoryReader = DirectoryReader.open(index);
        this.searcher = new IndexSearcher(this.directoryReader); //l'objet qui fait la recherche dans l'index

        //Parsing de la requete en un objet Query
        //  "contenu" est le champ interrogé par defaut si aucun champ n'est precisé
        QueryParser queryParser = new QueryParser("contenu", analyseur);
        Query req = queryParser.parse(searchWords);

        this.documentResult = searcher.search(req, MAX_RESULTS);
        this.numberOfResult = documentResult.scoreDocs.length;

    }

    public DocumentResult next() throws IOException {
        int docId = this.documentResult.scoreDocs[currentIndex].doc;

        DocumentResult documentResult = new DocumentResult(searcher.doc(docId), this.documentResult.scoreDocs[currentIndex].score);

        if (this.currentIndex == this.numberOfResult - 1) {
            // Iterator hit the last document
            this.directoryReader.close();
        } else if (this.currentIndex >= this.numberOfResult) {
            return null;
        }
        this.currentIndex++;
        return documentResult;
    }

    public boolean hasNext() {
        return this.currentIndex < this.numberOfResult;
    }
}
