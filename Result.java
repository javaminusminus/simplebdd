//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.simplebdd;

class Result {
    public String should;
    public Object expected;
    public Object got;
    public boolean failed = false;
}
