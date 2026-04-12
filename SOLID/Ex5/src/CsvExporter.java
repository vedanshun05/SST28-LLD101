import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String escapedTitle = escapeCsv(req.title);
        String escapedBody = escapeCsv(req.body);

        String csv = "title,body\n" + escapedTitle + "," + escapedBody + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeCsv(String value) {
        if (value == null) return "";

        if (value.contains(",") || value.contains("\"") || value.contains("\n")){
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }

        return value;
    }
}
