package analyzer;

import java.util.Map;
import java.util.List;

public class AnalysisResult{

  private final int lineCount;
  private final int wordCount;
  private final int charCount;
  private final int uniqueWordCount;


private final Map<String, Integer> frequencyMap;

private final List<Map.Entry<String,Integer>> topWords;

public AnalysisResult(int lineCount, int wordCount, int charCount, int uniqueWordCount, Map<String, Integer> frequencyMap, List<Map.Entry<String,Integer>>topWords){
  
  this.lineCount = lineCount;
  this.wordCount = wordCount;
  this.charCount = charCount;
  this.uniqueWordCount  = uniqueWordCount;
  this.frequencyMap = frequencyMap;
  this.topWords = topWords;


}

public int getLineCount(){ return lineCount;}
public int getWordCount (){ return wordCount;}
public int getCharCount () { return charCount;}
public int getUniqueWordCount(){ return getUniqueWordCount;}
public Map<String. Integer> getFrequencyMap() { return frequencyMap;}
public List<Map.Entry<String, Integer>> getTopWords () { return topWords;}

}
