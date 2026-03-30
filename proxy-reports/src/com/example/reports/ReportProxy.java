package com.example.reports;

/**
 * Proxy that adds access control and lazy loading to RealReport.
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    
    // Lazy reference
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1. Pre-processing: Access Check
        if (!accessControl.canAccess(user, classification)) {
            System.err.println("DENIED: User " + user.getName() 
                    + " (" + user.getRole() + ") cannot access " + classification + " report.");
            return;
        }

        // 2. Lazy loading: only load RealReport once per proxy
        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }

        // 3. Delegation
        realReport.display(user);
    }
}
