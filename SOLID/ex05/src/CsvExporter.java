import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public boolean supports(ExportRequest req) {
        return req != null;
    }

    @Override
    public ExportResult export(ExportRequest req) {
        if (!supports(req))
            throw new IllegalArgumentException("Invalid request");

        // We're keeping the exact (smelly) implementation to satisfy the exercise's
        // strict byte-count output requirement, but now it's guarded by the base
        // contract
        String body = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
