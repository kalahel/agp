package com.ucp;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.lucene.document.Document;


@Data
@AllArgsConstructor
public class DocumentResult {
    private Document document;
    private float score;
}
