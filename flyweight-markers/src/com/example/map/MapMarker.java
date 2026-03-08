package com.example.map;

/**
 * Map marker that holds extrinsic state (lat, lng, label) 
 * and a reference to shared intrinsic state (MarkerStyle).
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;

    // Shared intrinsic state (Flyweight)
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getLabel() { return label; }
    public MarkerStyle getStyle() { return style; }
}
