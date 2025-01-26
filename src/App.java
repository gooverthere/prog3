import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cache.Cache;
import cache.FastCache;
import cache.SlowCache;
import cache.UltraCache;

import checker.Checker;

@SuppressWarnings("unused")
public class App {
    public static void main(String[] args) throws Exception {
        String dictionary_path = "slownik.txt";

        Stream<String> stream = Files.lines(Paths.get(dictionary_path), StandardCharsets.UTF_16LE);
        List<String> dictionary = stream.collect(Collectors.toList());
        stream.close();

        Cache c = new UltraCache(dictionary);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.printf("Enter word to check (q! to quit): ");
        String base_word = reader.readLine();

        while (!base_word.equals("q!")) {
            long startTime = System.nanoTime();

            if (c.contains(base_word)) {
                System.out.println(Checker.check(base_word, c));
            } else {
                System.out.println("Word does not exists in dictiary");
            }

            long endTime = System.nanoTime();
            Integer run_duration = (int) (endTime - startTime) / 1000000;
            String timing_summary = String.format("Search duration: %d ms\n\n", run_duration);
            System.out.printf(timing_summary);

            System.out.printf("Enter word to check: ");
            base_word = reader.readLine();
        }
    }
}
