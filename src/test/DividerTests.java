package test;

import div.Divider;

public class DividerTests {

    public static void test_one_letter_in_the_middle() throws Exception {
        Divider divader = new Divider("aba");
        assert "aa".equals(divader.divide("b"));
    }

    public static void test_at_the_end() throws Exception {
        Divider divader = new Divider("kociarze");
        assert "koci".equals(divader.divide("arze"));
    }

    public static void test_at_the_start() throws Exception {
        Divider divader = new Divider("kociarze");
        assert "arze".equals(divader.divide("koci"));
    }

    public static void test_divident_equals_divisor() throws Exception {
        Divider divader = new Divider("kot");
        assert "".equals(divader.divide("kot"));
    }

    public static void test_constructor_empty() throws Exception {
        Divider divader = new Divider("");
        assert "".equals(divader.divide("kot"));
    }

    public static void test_check_empty() throws Exception {
        Divider divader = new Divider("kot");
        assert "kot".equals(divader.divide(""));
    }

    public static void test_word_not_found() throws Exception {
        Divider divader = new Divider("aaa");
        assert "".equals(divader.divide("bbb"));
    }

    public static void test_mixed_letters() throws Exception {
        Divider divide = new Divider("abababa");
        assert "aaaa".equals(divide.divide("bbb"));
    }

    public static void test_req_1() throws Exception {
        Divider divide = new Divider("atletka");
        assert "teka".equals(divide.divide("alt"));
    }

    public static void test_req_2() throws Exception {
        Divider divide = new Divider("koszt");
        assert "".equals(divide.divide("sto"));
    }

    public static void test_req_3() throws Exception {
        Divider divide = new Divider("laleczka");
        assert "alcza".equals(divide.divide("lek"));
    }

}
