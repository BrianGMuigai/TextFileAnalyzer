package analyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalyzer {

  private final String filePath;

  public TextAnalyzer(String filePath){

    this.filePath = filePath;

  }

  public AnalysisResult analyze() throws IOException{

    int lineCount = 0;
    int wordCount = 0;
    int charCount = 0;

    List<String> allWords = new ArrayList<>();

    try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){

      String line;

      while((line = reader.readLine()) != null){

        lineCount++;
        charCount+= line.length();

       List<String> wordsOnLine = tokenize(line);
       wordCount +=wordsOnLine.size();

       allWords.addAll(wordsOnLine);
      }
    }

    Map<String, Integer> freqMap = buildFrequencyMap(allWords);
    List<Map.Entry<String, Integer>> topWords = topNWords(freqMap, 10);
    int uniqueCount = freqMap.size();

    return new AnalysisResult(lineCount, wordCount, charCount, uniqueCount, freqMap,topWords);

  }

  private List<String> tokenize(String line){
  
    List<String> words = new ArrayList<>();

    if(line.trim().isEmpty()){
      
      return words;

    }

    String[] tokens = line.split("\\s+");

    for(String token : tokens ){
      String cleaned = cleanWord(token);

      if(!cleaned.isEmpty()){
        words.add(cleaned);
      }
    }

    return words;
  }

  private String cleanWord(String word){

    return word.replaceAll("[^a-zA-Z0-9]", "" ).toLowerCase();

  }

  private Map<String, Integer> buildFrequencyMap(List<String> words){
    Map<String, Integer> freqMap = new HashMap<>();

    for (String word : words ){

      freqMap.put(word, freqMap.getOrDefault(word, 0) + 1 );

    }

    return freqMap;
  }

  public List<Map.Entry<String, Integer>> topNWords(Map<String, Integer> freqMap, int n){

    List<Map.Entry<String, Integer>> entries = new ArrayList<>(freqMap.entrySet());

    entries.sort((a, b) -> b.getValue() - a.getValue());

    return entries.subList(0, Math.min(n, entries.size()));

  }

  public List<Map.Entry<String, Integer>> topNWords(Map<String, Integer> freqMap){
    return topNWords(freqMap, 5);
  }
}
