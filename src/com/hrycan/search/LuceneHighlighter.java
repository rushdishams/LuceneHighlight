package com.hrycan.search;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
 
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.NullFragmenter;
import org.apache.lucene.search.highlight.QueryScorer;
//import org.apache.lucene.util.Version;
 
public class LuceneHighlighter
{
    public static String highlight(String pText, String pQuery) throws Exception
    {	
    	Analyzer analyzer = new StandardAnalyzer();
    	QueryParser parser = new QueryParser("contents", analyzer);
	
        Highlighter highlighter = new Highlighter(new QueryScorer(parser.parse(pQuery)));
        highlighter.setTextFragmenter(new NullFragmenter());
 
        String text = highlighter.getBestFragment(analyzer, "", pText);
 
        if (text != null)
        {
            return text;
        }
        return pText;    
    }
 
    public static void main(String[] pArgs)
    {
   
 
        InputStream inputStream = null; 
        try
        {
        	//URL url = new URI("http://www.sustainalytics.com").toURL();
            //inputStream = url.openStream();
            //String data = IOUtils.toString(inputStream);
            //System.out.println(highlight(data, "sustainalytics"));
        	File file = new File ("01-mod.txt");
        	String text = FileUtils.readFileToString(file);
        	
            System.out.println(highlight(text, "\"sustainable management\""));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(inputStream);
        }
    }
}