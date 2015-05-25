package com.sustainalytics.indexing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
 
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.NullFragmenter;
import org.apache.lucene.search.highlight.QueryScorer;
 
public class LuceneHighlighter{
    public static String highlight(String pText, String pQuery) throws Exception{	
    	
    	Analyzer analyzer = new StandardAnalyzer();
    	QueryParser parser = new QueryParser("contents", analyzer);
	
        Highlighter highlighter = new Highlighter(new QueryScorer(parser.parse(pQuery)));
        highlighter.setTextFragmenter(new NullFragmenter());
 
        String text = highlighter.getBestFragment(analyzer, "", pText);
 
        if (text != null){
            return text;
        }
        return pText;    
    }
 
    public static void main(String[] args){

        	File file = new File ("01-mod.txt");
        	String text = null;

        	try {
				text = FileUtils.readFileToString(file);
			} catch (IOException e) {
				System.out.println("Error reading file");
			}
        	
            try {
				System.out.println(highlight(text, "\"sustainable management\""));
			} catch (Exception e) {
				System.out.println("Error highlighting text");
			}
       
    }// end main
    
}//end class