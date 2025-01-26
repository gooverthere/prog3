package test;

import java.util.Arrays;
import java.util.List;

import cache.Cache;
import cache.SlowCache;
import checker.Checker;

public class CheckerTests {

    public static void test_checker() throws Exception {
        String prefix = "emulsja";
        String sufix = "krowy";
        String word = prefix + sufix;
        List<String> dict_mock = Arrays.asList(
                prefix, sufix, word);

        Cache c = new SlowCache(dict_mock);

        String expected_output = String.format("%s/%s = %s\n", word, prefix, sufix);
        expected_output += String.format("%s/%s = %s\n", word, sufix, prefix);
        String checker_output = Checker.check(word, c);

        assert (checker_output.equals(expected_output));
    }
}
