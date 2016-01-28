package github.com.javaminusminus.simplebdd;

class Report {

    private String ok = "✓";
    private String err = "✖";
    private String colorNormal = "\033[0m";
    private String colorTitled = "\033[37m";
    private String colorReport = "\033[90m";
    private String colorPassed = "\033[92m";
    private String colorFailed = "\033[31m";

    Report(String name, Result[] results) {
        int total = results.length;
        int failures = 0;
        System.out.println("\n" + this.colorReport + name + "\n");
        for (int i = 0; i < total; i++) {
            Result r = results[i];
            if (r.failed) {
                failures++;
                String msg = this.colorFailed;
                msg += this.err + " should " + r.should + ", expected [" + r.expected + "], got [" + r.got + "]";
                msg += this.colorReport;
                System.out.println("\t" + msg);
            } else {
                System.out.println("\t" + this.colorPassed + this.ok + this.colorReport + " should " + r.should);
            }
        }
        String status;
        if (failures == 0) {
            status = this.colorPassed + this.ok + this.colorReport;
        } else {
            status = this.colorFailed + this.err + this.colorReport;
        }
        System.out.print("\n" + status + " " + Integer.toString(total) + " tests complete");
        if (failures > 0) {
            System.out.print(", " + Integer.toString(failures) + " failures");
        }
        System.out.println(this.colorNormal + "\n");
    }
}
