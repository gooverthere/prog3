package cache;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;

public class UltraCache implements Cache {
    private List<String> list;
    private HashMap<String, List<List<String>>> alphapetical = new HashMap<>();
    private List<List<String>> length_sorted = new ArrayList<>();

    private int max_cache_entry_length = 50;
    private int cache_key_size = 2;

    public UltraCache(List<String> dictionary) throws Exception {
        list = dictionary;

        create_alphabetical_tree();
        create_length_buckets();
    }

    public UltraCache(List<String> dictionary, int key_size) throws Exception {
        cache_key_size = key_size;
        list = dictionary;

        create_alphabetical_tree();
        create_length_buckets();
    }

    private String get_key(String word){
        String key;

        if (word.length() >= cache_key_size) {
            key = String.valueOf(word.substring(0, cache_key_size));
        } else {
            key = String.valueOf(word.charAt(0));
        }

        return key;
    }

    private void create_alphabetical_tree() {
        for (String word : list) {
            String key = get_key(word);

            List<List<String>> string_sorted_temp = alphapetical.get(key);

            if (string_sorted_temp == null) {
                string_sorted_temp = new ArrayList<>();
                for (int i = 0; i < max_cache_entry_length; i++) {
                    string_sorted_temp.add(new ArrayList<>());
                }
            }

            List<String> string_and_length_sorted_temp = string_sorted_temp.get(word.length());

            string_and_length_sorted_temp.add(word);
            string_sorted_temp.set(word.length(), string_and_length_sorted_temp);
            alphapetical.put(key, string_sorted_temp);
        }
    }

    private void create_length_buckets() {
        for (int i = 0; i < max_cache_entry_length; i++) {
            length_sorted.add(null);
        }

        for (List<List<String>> char_cache : alphapetical.values()) {
            for (List<String> char_length_cache : char_cache) {
                if (!char_length_cache.isEmpty()) {
                    List<String> char_length_cache_temp;
                    
                    int number_of_letters = char_length_cache.get(0).length();
                    
                    if (length_sorted.get(number_of_letters) != null) {
                        char_length_cache_temp = Stream
                                .concat(char_length_cache.stream(), length_sorted.get(number_of_letters).stream())
                                .toList();
                    } else {
                        char_length_cache_temp = char_length_cache;
                    }
                    
                    length_sorted.set(number_of_letters, char_length_cache_temp);
                }
            }
        }
    }

    public List<String> get_length_sorted(int length) {
        return length_sorted.get(length);
    }

    public boolean contains(String s) {
        String key = get_key(s);

        if (alphapetical.keySet().contains(key)) {
            if (alphapetical.get(key) != null
                    && alphapetical.get(key).get(s.length()) != null
                    && alphapetical.keySet().contains(key)) {
                return alphapetical.get(key).get(s.length()).contains(s);
            }
        }
        return false;
    }

    public int get_cache_length() {
        return length_sorted.size();
    }

    public List<String> get_list() {
        return list;
    };

}
