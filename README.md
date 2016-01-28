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

### testXXX()

```java
public void testName() {
    this.should("return that boolean matches boolean");
    this.assertEqual(true, true);
}
```

### before()

### beforeEach()

### after()

### afterEach()
