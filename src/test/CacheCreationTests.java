package test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import cache.Cache;
import cache.UltraCache;
import cache.FastCache;
import cache.SlowCache;

public class CacheCreationTests {

    public static void main(String[] args) throws Exception {
        String dictionary_path = "slownik.txt";
        List<String> dictionary = Files.lines(Paths.get(dictionary_path), StandardCharsets.UTF_16LE)
                .collect(Collectors.toList());

        long startTime = System.nanoTime();

        UltraCache c1 = new UltraCache(dictionary,2);

        long endTime = System.nanoTime();

        float run_duration = (float) (endTime - startTime) / 1000000;
        System.out.printf("%s[2] creation time %.3f ms\n", c1.getClass().getName(), run_duration);

         startTime = System.nanoTime();

        UltraCache c4 = new UltraCache(dictionary,1);

         endTime = System.nanoTime();

         run_duration = (float) (endTime - startTime) / 1000000;
        System.out.println();
        System.out.printf("%s[1] creation time %.3f ms\n", c4.getClass().getName(), run_duration);

        startTime = System.nanoTime();

        Cache c2 = new FastCache(dictionary);

        endTime = System.nanoTime();

        run_duration = (float) (endTime - startTime) / 1000000;
        System.out.println();
        System.out.printf("%s creation time %.3f ms\n", c2.getClass().getName(), run_duration);

        startTime = System.nanoTime();

        Cache c3 = new SlowCache(dictionary);

        endTime = System.nanoTime();

        float run_duration_short = (float) (endTime - startTime) / 1000000;
        System.out.println();
        System.out.printf("%s creation time %.3f ms\n", c3.getClass().getName(), run_duration_short);
    };
}
