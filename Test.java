//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// [![Build Status](https://travis-ci.org/jminusminus/simplebdd.svg?branch=master)](https://travis-ci.org/jminusminus/simplebdd)
// ## Stability: 1 - Stable
// Super simple behavior-driven development style test writer for [Jmm](https://github.com/jminusminus/jmm).
package github.com.jminusminus.simplebdd;

// The default test runner for Jmm.
//
// ## Usage
//
// Create a test file.
//
// ```java
// // My_test.java
// package path.to.package;
//
// import github.com.jminusminus.simplebdd.Test;
//
// public class My_test extends Test {
//     public static void main(String[] args) {
//         My_test test = new My_test();
//         test.run();
//     }
//
//     public void testThatThisIsTrue() {
//         this.should("return that boolean matches boolean");
//         this.assertEqual(true, true);
//     }
// }
// ```
//
// Run the jmm test command.
//
//     jmm test ./My_test.java
//
// Read the results.
//
//     github.com.jminusminus.jmmexample.Helloworld_test
//
//         ✓ should return the congratulations text
//
//     ✓ 1 tests complete
//
// ## Extend Test
//
// To create a test extend the Test class and create an instance of it in the main method.
//
// ```java
// public class My_test extends Test {
//     public static void main(String[] args) {
//         My_test test = new My_test();
//         test.run();
//     }
// }
// ```
//
// ## test_xxx()
//
// Any method that starts with "test" will be executed by SimpleBDD.
//
// ```java
// public void test_name() {
//     // Test code
// }
// ```
//
// ## before()
//
// Method called before any test is executed.
//
// ```java
// public void before() {
//     // Prep-code
// }
// ```
//
// ## beforeEach()
// 
// Method called before each test is executed.
//
// ```java
// public void beforeEach() {
//     // Prep-code
// }
// ```
//
// ## after()
//
// Method called after all tests have executed.
//
// ```java
// public void after() {
//     // Cleanup code
// }
// ```
//
// ## afterEach()
//
// Method called after each test has executed.
//
// ```java
// public void afterEach() {
//     // Cleanup code
// }
// ```
public class Test {

    protected java.lang.reflect.Method before;
    protected java.lang.reflect.Method beforeEach;
    protected java.lang.reflect.Method after;
    protected java.lang.reflect.Method afterEach;
    protected java.lang.reflect.Method[] tests = new java.lang.reflect.Method[0];
    protected Result[] results = new Result[0];

    // Runs the test by calling each method that begins with "test" outputting the results to `System.out`. The command will exit with the number of failed tests.
    //
    // ```java
    // public static void main(String[] args) {
    //     My_test test = new My_test();
    //     test.run();
    // }
    // ```
    public void run() {
        java.lang.reflect.Method[] methods = this.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            java.lang.reflect.Method method = methods[i];
            if (method.getName().startsWith("test")) {
                this.addTest(method);
            } else if (method.getName().equals("before")) {
                this.before = method;
            } else if (method.getName().equals("beforeEach")) {
                this.beforeEach = method;
            } else if (method.getName().equals("after")) {
                this.after = method;
            } else if (method.getName().equals("afterEach")) {
                this.afterEach = method;
            }
        }
        this.executeTests();
        this.report();
    }

    // Adds a description of what the preceding code "should" do.
    //
    // ```java
    // public void test_boolean_is_boolean() {
    //     this.should("return that boolean matches boolean");
    // }
    // ```
    public void should(String should) {
        this.resultStart();
        this.results[this.results.length-1].should = should;
    }

    // Used to test if two types are the same. Supported types are Short, Int, Long, Float, Double, Boolean, Char and String.
    //
    // ```java
    // public void testName() {
    //     this.assertEqual(true, true);
    // }
    // ```
    public boolean assertEqual(Object a, Object b) {
        if (a.equals(b)) {
            return true;
        }
        this.resultFailure(a, b);
        return false;
    }

    // Used to test if two types are NOT the same. Supported types are Short, Int, Long, Float, Double, Boolean, Char and String.
    //
    // ```java
    // public void testName() {
    //     this.assertNotEqual(false, true);
    // }
    // ```
    public boolean assertNotEqual(Object a, Object b) {
        if (!a.equals(b)) {
            return true;
        }
        this.resultFailure(a, b);
        return false;
    }

    protected void report() {
        new Report(this.getClass().getName(), this.results);
        int failures = 0;
        for (int i = 0; i < this.results.length; i++) {
            if (this.results[i].failed) {
                failures++;
            }
        }
        System.exit(failures);
    }

    protected void addTest(java.lang.reflect.Method v) {
        int len = this.tests.length;
        java.lang.reflect.Method[] a = new java.lang.reflect.Method[len + 1];
        a[len] = v;
        for (int i = 0; i < len; i++ ) {
            a[i] = this.tests[i];
        }
        this.tests = a;
    }

    protected void resultStart() {
        int len = this.results.length;
        Result[] a = new Result[len + 1];
        a[len] = new Result();
        for (int i = 0; i < len; i++ ) {
            a[i] = this.results[i];
        }
        this.results = a;
    }

    protected void resultFailure() {
        this.results[this.results.length-1].failed = true;
    }

    protected void resultFailure(Object a, Object b) {
        this.results[this.results.length-1].expected = a;
        this.results[this.results.length-1].got = b;
        this.results[this.results.length-1].failed = true;
    }

    protected void executeTests() {
        this.executeBefore();
        for (int i = 0; i < this.tests.length; i++) {
            this.executeTest(this.tests[i]);
        }
        this.executeAfter();
    }

    protected void executeTest(java.lang.reflect.Method method) {
        this.executeBeforeEach();
        try {
            method.invoke(this);
        } catch (IllegalAccessException e) {
            this.resultFailure();
            System.out.println(e.getCause());
        } catch (java.lang.reflect.InvocationTargetException e) {
            this.resultFailure();
            System.out.println(e.getCause());
            e.printStackTrace(System.out);
        }
        this.executeAfterEach();
    }

    protected void executeBefore() {
        if (this.before == null) {
            return;
        }
        try {
            this.before.invoke(this);
        } catch (IllegalAccessException e) {
            System.out.println(e.getCause());
        } catch (java.lang.reflect.InvocationTargetException e) {
            System.out.println(e.getCause());
            e.printStackTrace(System.out);
        }
    }

    protected void executeAfter() {
        if (this.after == null) {
            return;
        }
        try {
            this.after.invoke(this);
        } catch (IllegalAccessException e) {
            System.out.println(e.getCause());
        } catch (java.lang.reflect.InvocationTargetException e) {
            System.out.println(e.getCause());
            e.printStackTrace(System.out);
        }
    }

    protected void executeBeforeEach() {
        if (this.beforeEach == null) {
            return;
        }
        try {
            this.beforeEach.invoke(this);
        } catch (IllegalAccessException e) {
            System.out.println(e.getCause());
        } catch (java.lang.reflect.InvocationTargetException e) {
            System.out.println(e.getCause());
            e.printStackTrace(System.out);
        }
    }

    protected void executeAfterEach() {
        if (this.afterEach == null) {
            return;
        }
        try {
            this.afterEach.invoke(this);
        } catch (IllegalAccessException e) {
            System.out.println(e.getCause());
        } catch (java.lang.reflect.InvocationTargetException e) {
            System.out.println(e.getCause());
            e.printStackTrace(System.out);
        }
    }
}
