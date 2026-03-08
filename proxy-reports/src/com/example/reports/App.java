package com.example.reports;

/**
 * CampusVault Demo: demonstrates access control and lazy loading via Proxy.
 */
public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // We now use ReportProxy instead of ReportFile directly
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        System.out.println("--- Scenario 1: Student opens PUBLIC report ---");
        viewer.open(publicReport, student);
        System.out.println();

        System.out.println("--- Scenario 2: Student tries to open FACULTY report (should be DENIED) ---");
        viewer.open(facultyReport, student);
        System.out.println();

        System.out.println("--- Scenario 3: Faculty opens FACULTY report ---");
        viewer.open(facultyReport, faculty);
        System.out.println();

        System.out.println("--- Scenario 4: Admin opens ADMIN report ---");
        viewer.open(adminReport, admin);
        System.out.println();

        System.out.println("--- Scenario 5: Admin opens ADMIN report AGAIN (should be FAST, no [disk] log) ---");
        viewer.open(adminReport, admin);
    }
}
