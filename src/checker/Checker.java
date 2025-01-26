package checker;

import java.util.List;

import cache.Cache;
import div.Divider;

public class Checker {
    public static String check(String base_word, Cache c) {
        boolean word_not_found = true;
        StringBuilder result_string = new StringBuilder();

        for (int i = 0; (i < base_word.length() && i < c.get_cache_length()); i++) {
            List<String> list = c.get_length_sorted(i);
            Divider divide_checker = new Divider(base_word);
            if (list != null) {
                for (String word : list) {
                    String result = divide_checker.divide(word);

                    if (result != "" && c.contains(result)) {
                        result_string.append(String.format("%s/%s = %s\n", base_word, word, result));
                        word_not_found = false;
                    }
                }
            }
        }

        if (word_not_found) {
            result_string.append(String.format("NO MATCHES\n"));
        }

        return result_string.toString();
    }
}
