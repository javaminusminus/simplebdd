package github.com.javaminusminus.simplebdd;

public class Test_test extends Test {

    public static void main(String[] args) {
        Test_test test = new Test_test();
        test.run();
    }

    public void before() {
        // System.out.println("Before");
    }

    public void beforeEach() {
        // System.out.println("BeforeEach");
    }

    public void after() {
        // System.out.println("After");
    }

    public void afterEach() {
        // System.out.println("AfterEach");
    }

    public void testShort() {
        this.should("return that short matches short");
        this.assertEqual((short)1, (short)1);
        this.should("return that short does not match short");
        this.assertNotEqual((short)1, (short)2);
    }

    public void testInt() {
        this.should("return that int matches int");
        this.assertEqual(1, 1);
        this.should("return that int does not match int");
        this.assertNotEqual(1, 2);
    }

    public void testLong() {
        this.should("return that long matches long");
        this.assertEqual(999999999L, 999999999L);
        this.should("return that long does not match long");
        this.assertNotEqual(999999999L, 222222222L);
    }

    public void testFloat() {
        this.should("return that float matches float");
        this.assertEqual(0.11, 0.11);
        this.should("return that float does not match float");
        this.assertNotEqual(0.11, 0.12);
    }

    public void testDouble() {
        this.should("return that double matches double");
        this.assertEqual(0.999999999, 0.999999999);
        this.should("return that double does not match double");
        this.assertNotEqual(0.999999999, 0.999999992);
    }

    public void testBoolean() {
        this.should("return that boolean matches boolean");
        this.assertEqual(true, true);
        this.should("return that boolean does not match boolean");
        this.assertNotEqual(true, false);
    }

    public void testChar() {
        this.should("return that char matches char");
        this.assertEqual((char)13, (char)13);
        this.should("return that char does not match char");
        this.assertNotEqual((char)13, (char)12);
    }

    public void testString() {
        this.should("return that string matches string");
        this.assertEqual("foo", "foo");
        this.should("return that string does not match string");
        this.assertNotEqual("foo", "bar");
    }
}
