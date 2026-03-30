package com.example.reports;

/**
 * Report viewer that depends on the Report interface instead of a concrete class.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
