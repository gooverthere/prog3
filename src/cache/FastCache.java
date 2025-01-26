package cache;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class FastCache implements Cache {
    private List<String> list;
    private List<List<String>> length_sorted = new ArrayList<>();

    public FastCache(List<String> dictionary) throws Exception {
        list = dictionary;

        length_sorted.add(null);
        length_sorted.add(list.stream().filter(w -> w.length() == 1).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 2).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 3).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 4).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 5).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 6).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 7).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 8).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 9).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() == 10).collect(Collectors.toList()));
        length_sorted.add(list.stream().filter(w -> w.length() >= 11).collect(Collectors.toList()));
    }

    public List<String> get_length_sorted(int length) {
        if (length > get_cache_length()) {
            length = get_cache_length() - 1;
        }
        return length_sorted.get(length);
    }

    public boolean contains(String s) {
        int cache_index = s.length();
        if (cache_index >= get_cache_length()) {
            cache_index = get_cache_length() - 1;
        }

        List<String> temp = length_sorted.get(cache_index);
        if (temp != null) {
            return temp.contains(s);
        } else {
            return false;
        }
    }

    public int get_cache_length() {
        return length_sorted.size();
    }

    public List<String> get_list() {
        return list;
    };
}
