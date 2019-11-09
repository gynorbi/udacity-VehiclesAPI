package com.udacity.boogle.maps;

public class LatLon {
    private Double lat;
    private Double lon;

    public LatLon(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    // Overriding equals() to compare two LatLon objects
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of LatLon or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof LatLon)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        LatLon l = (LatLon) o;

        // Compare the data members and return accordingly
        return Double.compare(this.lat, l.lat) == 0
                && Double.compare(this.lon, l.lon) == 0;
    }

    // Overriding hashCode() to compare two LatLon objects
    @Override
    public int hashCode(){
        return this.lat.hashCode()+this.lon.hashCode();
    }
}
