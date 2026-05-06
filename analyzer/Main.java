package analyzer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main{

  public static void main(String[] args){

    if(args.length == 0 ){

      System.out.println("Usage: java analyzer.Main <path-to-text-file>");
      System.out.println("Example: java analyzer.Main sample.txt ");
      System.exit(1);
    }

    String filePath = args[0];

    try{
      
      TextAnalyzer analyzer = new TextAnalyzer(filePath);
      AnalysisResult result = analyzer.analyze();
      printReport( filePath, result);

    }catch (IOException e ){

      System.err.println("[!] Could not read file :" + filePath);
      System.err.println("     Reason: " + e.getMessage());
      System.exit(1);
    }
  }

private static void printReport(String filePath,AnalysisResult result){

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        Text File Analysis Report     ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  File     : " + filePath);
        System.out.println("  Lines    : " + result.getLineCount());
        System.out.println("  Words    : " + result.getWordCount());
        System.out.println("  Characters: " + result.getCharCount());
        System.out.println("  Unique words: " + result.getUniqueWordCount());

        System.out.println("\n  --- Top 10 most frequent words ---");

        int rank = 1 ; 
        for( Map.Entry<String, Integer> entry : result.getTopWords()){
          System.out.printf("    %2d. %-20s %d times%n",
                    rank++, entry.getKey(), entry.getValue());

        }

        System.out.println("\n" + "=".repeat(42));
}

}
