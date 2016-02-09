//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.simplebdd;

public class Test {

    protected java.lang.reflect.Method before;
    protected java.lang.reflect.Method beforeEach;
    protected java.lang.reflect.Method after;
    protected java.lang.reflect.Method afterEach;
    protected java.lang.reflect.Method[] tests = new java.lang.reflect.Method[0];
    protected Result[] results = new Result[0];

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

    public void should(String should) {
        this.resultStart();
        this.results[this.results.length-1].should = should;
    }

    public boolean assertEqual(Object a, Object b) {
        if (a.equals(b)) {
            return true;
        }
        this.resultFailure(a, b);
        return false;
    }

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
