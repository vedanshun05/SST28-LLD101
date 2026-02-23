import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public boolean supports(ExportRequest req) {
        return req != null && (req.body == null || req.body.length() <= 20);
    }

    @Override
    public ExportResult export(ExportRequest req) {
        if (!supports(req)) {
            throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
