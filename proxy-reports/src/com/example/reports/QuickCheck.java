package com.example.reports;

public class QuickCheck {

    public static void main(String[] args) {
        User student = new User("Riya", "STUDENT");
        User admin = new User("Kshitij", "ADMIN");

        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");

        System.out.println("=== QuickCheck ===");
        System.out.println("1. Testing access control (STUDENT -> FACULTY):");
        facultyReport.display(student);
        System.out.println();
        
        System.out.println("2. Testing lazy loading (ADMIN -> ADMIN):");
        adminReport.display(admin);
        System.out.println();
        
        System.out.println("3. Testing caching (ADMIN -> ADMIN again):");
        adminReport.display(admin);
    }
}
