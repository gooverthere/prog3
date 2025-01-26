package test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cache.Cache;
import cache.UltraCache;
import cache.FastCache;

@SuppressWarnings("unused")
public class PerformaceTests {

    public static void main(String[] args) throws Exception {

        String dictionary_path = "slownik.txt";
        int word_length = 11;

        List<String> dictionary = Files.lines(Paths.get(dictionary_path), StandardCharsets.UTF_16LE)
                .collect(Collectors.toList());
        Cache c = new UltraCache(dictionary,2);

        dictionary = dictionary.stream().filter(w -> w.length() == word_length).collect(Collectors.toList());


        Integer cum_duration = 0;
        Map<String, Integer> timings = new HashMap<String, Integer>();
        Integer count = 250;

        FileWriter myWriter = new FileWriter("dictionary_path.txt");

        for (long i = 0; i <= count; i++) {
            StringBuilder presented_results = new StringBuilder();
            int u = (int) (Math.random() * (dictionary.size()));
            String base_word = dictionary.get(u);
            
            System.out.printf("Test progress: %3.4f%%\n", (float) i / count * 100);
            presented_results.append(String.format("Working with word: %s\n", base_word));
            System.out.printf(presented_results.toString());
            
            long startTime = System.nanoTime();

            String a = checker.Checker.check(base_word, c);

            long endTime = System.nanoTime();

            System.out.printf(a);
            presented_results.append(a);
            
            myWriter.append(presented_results.toString());
            
            Integer run_duration = (int) (endTime - startTime) / 1000000;
            cum_duration += run_duration;
            timings.put(base_word, run_duration);
            String timing_summary = String.format("Search duration: %d ms\n\n", run_duration);
            System.out.printf(timing_summary);
            myWriter.append(timing_summary);
            presented_results.append("\n\n");
        }
        // System.err.println(timings); // Can be used for stats

        String timing_summary = String.format("Average operation duration: %d ms\n", cum_duration / count);
        System.out.printf(timing_summary);
        myWriter.append(timing_summary);
        
        myWriter.close();
    };
}
