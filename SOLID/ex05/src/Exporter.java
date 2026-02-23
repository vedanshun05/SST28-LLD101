public abstract class Exporter {
    /**
     * Returns true if this exporter can handle the given request.
     * If this returns true, export() must not throw validation exceptions.
     */
    public abstract boolean supports(ExportRequest req);

    /**
     * Exports the request.
     * Precondition: supports(req) must be true.
     * Postcondition: Returns a valid ExportResult (never null).
     * Throws IllegalArgumentException if preconditions are not met.
     */
    public abstract ExportResult export(ExportRequest req);
}
