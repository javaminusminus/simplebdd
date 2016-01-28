# SimpleBDD

Super simple behavior-driven development style test writer for [Java--](https://github.com/javaminusminus/jmm).

## Usage

```java
// My_test.java
package path.to.package;

import github.com.javaminusminus.simplebdd.Test;

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

    jmm test ./My_test.java

### Extend Test

To create a test extend the Test class and create an instance of it in the main method.

```java
public class My_test extends Test {
    public static void main(String[] args) {
        My_test test = new My_test();
        test.run();
    }
}
```

### testXXX()

Any method that starts with "test" will be executed by SimpleBDD.

```java
public void testName() {
    // Test code
}
```

#### run()

Runs the test by calling each method that begins with "test" outputting the results to `System.out`. The program will exit with the number of failed tests.

```java
public static void main(String[] args) {
    My_test test = new My_test();
    test.run();
}
```

#### should()

Adds a description of what the preceding code "should" do.

```java
public void testName() {
    this.should("return that boolean matches boolean");
}
```

#### assertEqual()

Used to test if two types are the same. Supported types are Short, Int, Long, Float, Double, Boolean, Char and String.

```java
public void testName() {
    this.assertEqual(true, true);
}
```

#### assertNotEqual()

Used to test if two types are NOT the same. Supported types are Short, Int, Long, Float, Double, Boolean, Char and String.

```java
public void testName() {
    this.assertNotEqual(false, true);
}
```

### before()

Method called before any test is executed.

```java
public void before() {
    // Prep-code
}
```

### beforeEach()

Method called before each test is executed.

```java
public void beforeEach() {
    // Prep-code
}
```

### after()

Method called after all tests have executed.

```java
public void after() {
    // Cleanup code
}
```

### afterEach()

Method called after each test has executed.

```java
public void afterEach() {
    // Cleanup code
}
```
