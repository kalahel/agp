package com.ucp;

import com.ucp.configuration.ConfigurationEntry;
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

/**
 * Retrieves the results from a Lucene query, stores and iterates on them.
 */
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

    /**
     * Initializes the iterator and loads a result from a document list.
     * Each french word comes directly from D. VODISLAV's course and has to be in french.
     *
     * @param searchWords Keywords, separated by spaces
     * @throws IOException    From file opening
     * @throws ParseException From QueryParser
     */
    public void init(String searchWords) throws IOException, ParseException {
        Analyzer analyseur = new StandardAnalyzer();

        Path indexPath = FileSystems.getDefault().getPath(ConfigurationEntry.RELATIVE_INDEX_PATH);

        Directory index = FSDirectory.open(indexPath);

        this.directoryReader = DirectoryReader.open(index);
        this.searcher = new IndexSearcher(this.directoryReader);

        QueryParser queryParser = new QueryParser("contenu", analyseur);
        Query req = queryParser.parse(searchWords);

        this.documentResult = searcher.search(req, MAX_RESULTS);
        this.numberOfResult = documentResult.scoreDocs.length;
    }

    /**
     * Closes the DirectoryReader when arriving at the last result.
     *
     * @return Next document in list
     * @throws IOException
     */
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

    /**
     * @return True if the DocumentList has at least one more result, False if it doesn't.
     */
    public boolean hasNext() {
        return this.currentIndex < this.numberOfResult;
    }
}
