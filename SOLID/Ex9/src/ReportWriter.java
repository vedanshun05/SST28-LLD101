public class ReportWriter implements IReportWriter {
    public String write(Submission s, int plag, int code) {
        return "report-" + s.roll + ".txt";
    }
}
