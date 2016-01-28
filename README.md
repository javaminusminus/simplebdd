# Simple Behavior Driven Development

Super simple behavior-driven development style test writer for [Java--](https://github.com/javaminusminus/jmm).

## Usage

```java
public class My_test extends Test {

    public static void main(String[] args) {
        My_test test = new My_test();
        test.run();
        test.report();
    }

    public void testThatThisIsTrue() {
        this.should("return that boolean matches boolean");
        this.assertEqual(true, true);
    }
}
```

### Extend Test

```java
public class My_test extends Test {

    public static void main(String[] args) {
        My_test test = new My_test();
        test.run();
        test.report();
    }
}
```

### testXXX()

```java
public void testName() {
    this.should("return that boolean matches boolean");
    this.assertEqual(true, true);
}
```

#### should()

```java
public void testName() {
    this.should("return that boolean matches boolean");
    this.assertEqual(true, true);
}
```

#### assertEqual()

```java
this.assertEqual(true, true);
```

#### assertNotEqual()

```java
this.assertNotEqual(true, true);
```

### before()

```java
public void before() {
    // Prep-code
}
```

### beforeEach()

```java
public void beforeEach() {
    // Prep-code
}
```

### after()

```java
public void after() {
    // Cleanup code
}
```

### afterEach()

```java
public void afterEach() {
    // Cleanup code
}
```
