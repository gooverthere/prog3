package test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/// REMEMBER TO USE '-enableassertions' !!!!!!!

public class TestsRunner {
    private static String config_test_method_prefix = "test_";

    private static List<Method> findTests(Class<DividerTests> tests) {
        List<Method> results = new ArrayList<>();

        System.err.printf("Searching for tests...\nFound: ");
        for (Method m : tests.getMethods()) {
            if (m.getName().contains(config_test_method_prefix)) {
                results.add(m);
                System.out.printf(" %s |", m.getName());
            }
        }
        return results;
    }

    private static boolean runTest(Method test) throws Exception {
        try {
            test.invoke(null);
        } catch (Exception e) {
            if (e.getCause() instanceof AssertionError) {
                System.out.println("\033[0;31mFAIL\033[0m");
            } else {
                System.out.println("\033[0;31mERROR\033[0m");
            }
            e.getCause().printStackTrace();
            return false;
        }
        System.out.println("\033[0;32mPASS\033[0m");
        return true;
    }

    private static void performTestSuite(@SuppressWarnings("rawtypes") Class test_class) throws Exception {
        int fail_counter = 0;
        int test_counter = 0;
        ArrayList<String> fails = new ArrayList<>();
        System.out.printf("\nRunnig: %s\n", test_class.getName());
        @SuppressWarnings("unchecked")
        List<Method> tests = findTests(test_class);

        System.out.printf("\n\n");

        for (Method t : tests) {
            System.out.printf("Running %s\n", t.getName());
            test_counter += 1;
            boolean result = runTest(t);
            if (!result) {
                fail_counter += 1;
                fails.add(t.getName());
            }
        }

        System.out.printf("\n\nRan %d tests in suite, %d passed, %d failed\n",
                test_counter,
                test_counter - fail_counter,
                fail_counter);

        if (fail_counter > 0) {
            System.out.println("Failing tests:");
            for (String t : fails) {
                System.out.printf("- %s\n", t);
            }
            System.out.println();
        }

        return;

    }

    public static void main(String[] args) throws Exception {
        performTestSuite(DividerTests.class);
        performTestSuite(CacheTests.class);
        performTestSuite(CheckerTests.class);
    };
}
