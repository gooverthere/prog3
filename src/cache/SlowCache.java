package cache;

import java.util.List;

public class SlowCache implements Cache{
    private List<String> list;

    public SlowCache(List<String> dictionary) throws Exception {
        list = dictionary;
    }

    public List<String> get_length_sorted(int length) {
        return list;
    }

    public boolean contains(String s) {
        return list.contains(s);
    }

    public int get_cache_length() {
        return 1;
    }

    public List<String> get_list(){
        return list;
    };
}
