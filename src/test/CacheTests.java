package test;

import java.util.Arrays;
import java.util.List;

import cache.FastCache;
import cache.SlowCache;
import cache.UltraCache;

public class CacheTests {

    public static void test_sorting_by_length_UC() throws Exception {
        List<String> dict_mock = Arrays.asList(
                "123", "12345", "1234", "1234567",
                "12", "123456", "123456789101112", "1",
                "12345678", "123456789", "1234567890");
        UltraCache c = new UltraCache(dict_mock);

        assert c.get_length_sorted(1).contains("1");
        assert c.get_length_sorted(2).contains("12");
        assert c.get_length_sorted(3).contains("123");
        assert c.get_length_sorted(4).contains("1234");
        assert c.get_length_sorted(5).contains("12345");
        assert c.get_length_sorted(6).contains("123456");
        assert c.get_length_sorted(7).contains("1234567");
        assert c.get_length_sorted(8).contains("12345678");
        assert c.get_length_sorted(9).contains("123456789");
        assert c.get_length_sorted(10).contains("1234567890");
        assert c.get_length_sorted(15).contains("123456789101112");
    }

    public static void test_contains_check_UC() throws Exception {
        List<String> dict_mock = Arrays.asList("1", "12", "123456789", "123456789101112");
        UltraCache c = new UltraCache(dict_mock);

        assert c.contains("1");
        assert c.contains("12");
        assert c.contains("123456789");
        assert c.contains("123456789101112");
        assert !c.contains("ab");
    }

    public static void test_not_found_UC() throws Exception {
        List<String> dict_mock = Arrays.asList(
                "123");
        UltraCache c = new UltraCache(dict_mock);

        assert !c.contains("1");
    }

    public static void test_sorting_by_length_FC() throws Exception {
        List<String> dict_mock = Arrays.asList(
                "123", "12345", "1234", "1234567", "12345678900",
                "12", "123456", "123456789101112", "1",
                "12345678", "123456789", "1234567890");
        FastCache c = new FastCache(dict_mock);

        assert c.get_length_sorted(1).contains("1");
        assert c.get_length_sorted(2).contains("12");
        assert c.get_length_sorted(3).contains("123");
        assert c.get_length_sorted(4).contains("1234");
        assert c.get_length_sorted(5).contains("12345");
        assert c.get_length_sorted(6).contains("123456");
        assert c.get_length_sorted(7).contains("1234567");
        assert c.get_length_sorted(8).contains("12345678");
        assert c.get_length_sorted(9).contains("123456789");
        assert c.get_length_sorted(10).contains("1234567890");
        assert c.get_length_sorted(11).contains("12345678900");
        assert c.get_length_sorted(15).contains("123456789101112");
    }

    public static void test_not_found_FC() throws Exception {
        List<String> dict_mock = Arrays.asList(
                "123");
        FastCache c = new FastCache(dict_mock);

        assert !c.contains("1");
    }


    public static void test_contains_check_FC() throws Exception {
        List<String> dict_mock = Arrays.asList("1", "12", "123456789", "123456789101112", "123456789041","1234567890411");
        FastCache c = new FastCache(dict_mock);

        assert c.contains("1");
        assert c.contains("12");
        assert c.contains("123456789");
        assert c.contains("123456789041");
        assert c.contains("1234567890411");
        assert c.contains("123456789101112");
        assert !c.contains("ab");
    }

    public static void test_sorting_by_length_SC() throws Exception {
        List<String> dict_mock = Arrays.asList(
                "123", "12345", "1234", "1234567", "12345678900",
                "12", "123456", "123456789101112", "1",
                "12345678", "123456789", "1234567890");
        SlowCache c = new SlowCache(dict_mock);

        assert c.get_length_sorted(1).contains("1");
        assert c.get_length_sorted(2).contains("12");
        assert c.get_length_sorted(3).contains("123");
        assert c.get_length_sorted(4).contains("1234");
        assert c.get_length_sorted(5).contains("12345");
        assert c.get_length_sorted(6).contains("123456");
        assert c.get_length_sorted(7).contains("1234567");
        assert c.get_length_sorted(8).contains("12345678");
        assert c.get_length_sorted(9).contains("123456789");
        assert c.get_length_sorted(10).contains("1234567890");
        assert c.get_length_sorted(11).contains("12345678900");
        assert c.get_length_sorted(15).contains("123456789101112");
    }

    public static void test_contains_check_SC() throws Exception {
        List<String> dict_mock = Arrays.asList("1", "12", "123456789", "123456789101112");
        SlowCache c = new SlowCache(dict_mock);

        assert c.contains("1");
        assert c.contains("12");
        assert c.contains("123456789");
        assert c.contains("123456789101112");
        assert !c.contains("ab");
    }
}
