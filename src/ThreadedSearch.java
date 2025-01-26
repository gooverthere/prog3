import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import cache.UltraCache;
import cache.Cache;
import checker.Checker;

class ThreadedChecker extends Thread {
    private List<String> dictionary;
    private List<String> word_list;
    private String start_char;
    private long size;

    public ThreadedChecker(List<String> dictionary, String start_char) {
        this.dictionary = dictionary;
        this.start_char = start_char;
        this.word_list = (dictionary.stream().filter(w -> String.valueOf(w.charAt(0)).equals(start_char))
                .collect(Collectors.toList()));

        this.size = word_list.size();
    }

    public void run() {
        try {
            System.out.printf("Thread: %s started\n", start_char);
            FileWriter myWriter = new FileWriter("./results/" + start_char + ".txt");
            Cache c = new UltraCache(dictionary, 2);
            System.out.printf("Thread: %s cache created\n", start_char);

            long j = 0;
            long i = 0;

            for (String base_word : word_list) {
                StringBuilder presented_results = new StringBuilder();
                presented_results.append(String.format("word = %s\n", base_word));
                String a = Checker.check(base_word, c);
                presented_results.append(a);
                presented_results.append("\n");
                myWriter.append(presented_results.toString());

                i++;
                j++;
                if (j >= 100) {
                    System.out.printf("Thread: %s is %6.3f %% finished\n", start_char, (float) i / size * 100);
                    j = 0;
                }
            }

            myWriter.close();
            System.out.printf("Thread: %s finished\n", start_char);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception is caught");
            System.out.printf("Thread: %s crashed\n", start_char);
        }
    }
}

public class ThreadedSearch {
    public static void main(String[] args) throws Exception {
        List<ThreadedChecker> thread_list = new ArrayList<ThreadedChecker>();
        String dictionary_path = "slownik.txt";
        List<String> dictionary = Files.lines(Paths.get(dictionary_path), StandardCharsets.UTF_16LE)
                .collect(Collectors.toList());

        List<String> letter_list = Arrays.asList("a", "ą", "b", "c", "ć", "d", "e",
                "ę", "f", "g", "h", "i", "j", "k", "l", "ł", "m", "n", "ń", "o", "ó",
                "p", "q", "r", "s", "ś", "t", "u", "v", "w", "x", "y", "z", "ź", "ż");

        for (String letter : letter_list) {

            thread_list.add(new ThreadedChecker(dictionary, letter));
        }

        for (ThreadedChecker thread : thread_list) {
            thread.start();
        }

    }
}