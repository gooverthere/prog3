package cache;

import java.util.List;

public interface Cache {
    public List<String> get_list();
    public List<String> get_length_sorted(int length);
    public boolean contains(String s);
    public int get_cache_length();
}
