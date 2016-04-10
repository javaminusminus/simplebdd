# Test
#### Package: github.com.jminusminus.simplebdd
[![Build Status](https://travis-ci.org/jminusminus/simplebdd.svg?branch=master)](https://travis-ci.org/jminusminus/simplebdd)
## Stability: 1 - Stable
Super simple behavior-driven development style test writer for [Jmm](https://github.com/jminusminus/jmm).

## Install
```
jmm get github.com/jminusminus/simplebdd
```
## github.com.jminusminus.simplebdd.Test
The default test runner for Jmm.

## Usage

Create a test file.

```java
// My_test.java
package path.to.package;

import github.com.jminusminus.simplebdd.Test;

public class My_test extends Test {
    public static void main(String[] args) {
         My_test test = new My_test();
         test.run();
    }

    public void testThatThisIsTrue() {
         this.should("return that boolean matches boolean");
         this.assertEqual(true, true);
    }
}
```

Run the jmm test command.

    jmm test ./My_test.java

Read the results.

    github.com.jminusminus.jmmexample.Helloworld_test

         ✓ should return the congratulations text

    ✓ 1 tests complete

## Extend Test

To create a test extend the Test class and create an instance of it in the main method.

```java
public class My_test extends Test {
    public static void main(String[] args) {
         My_test test = new My_test();
         test.run();
    }
}
```

## test_xxx()

Any method that starts with "test" will be executed by SimpleBDD.

```java
public void test_name() {
    // Test code
}
```

## before()

Method called before any test is executed.

```java
public void before() {
    // Prep-code
}
```

## beforeEach()

Method called before each test is executed.

```java
public void beforeEach() {
    // Prep-code
}
```

## after()

Method called after all tests have executed.

```java
public void after() {
    // Cleanup code
}
```

## afterEach()

Method called after each test has executed.

```java
public void afterEach() {
    // Cleanup code
}
```

```
import github.com.jminusminus.simplebdd.Test;
```
## void run()
Runs the test by calling each method that begins with "test" outputting the results to `System.out`. The command will exit with the number of failed tests.

```java
public static void main(String[] args) {
    My_test test = new My_test();
    test.run();
}
```

## void should(String should)
Adds a description of what the preceding code "should" do.

```java
public void test_boolean_is_boolean() {
    this.should("return that boolean matches boolean");
}
```

## boolean assertEqual(Object a, Object b)
Used to test if two types are the same. Supported types are Short, Int, Long, Float, Double, Boolean, Char and String.

```java
public void testName() {
    this.assertEqual(true, true);
}
```

## boolean assertNotEqual(Object a, Object b)
Used to test if two types are NOT the same. Supported types are Short, Int, Long, Float, Double, Boolean, Char and String.

```java
public void testName() {
    this.assertNotEqual(false, true);
}
```

