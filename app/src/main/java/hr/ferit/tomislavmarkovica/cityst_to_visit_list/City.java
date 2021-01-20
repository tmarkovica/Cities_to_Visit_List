package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

public class City {
    private String id;
    private String wikiDataId;
    private String type;
    private String city;
    private String name;
    private String country;
    private String countryCode;
    private String region;
    private String regionCode;
    private int elevationMeters;
    private double latitude;
    private double longitude;
    private int population;
    private String timezone;
    private boolean deleted;

    public City(String id,
            String wikiDataId,
            String type,
            String city,
            String name,
            String country,
            String countryCode,
            String region,
            String regionCode,
            int elevationMeters,
            double latitude,
            double longitude,
            int population,
            String timezone,
            boolean deleted) {
        this.id = id;
        this.wikiDataId = wikiDataId;
        this.type = type;
        this.city = city;
        this.name = name;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionCode = regionCode;
        this.elevationMeters =;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
        this.timezone = timezone;
        this.deleted = deleted;
    }

    public String get_id() { return this.id; }
    public void set_id(String id) { this.id = id; }

    public String get_type() { return this.type; }
    public void set_type(String type) { this.type = type; }

    public String get_city() { return this.city; }
    public void set_city(String city) { this.city = city; }

    public String get_name() { return this.name; }
    public void set_name(String name) { this.name = name; }

    public String get_wikiDataId() { return wikiDataId; }
    public void set_wikiDataId(String wikiDataId) { this.wikiDataId = wikiDataId; }

    public String get_country() { return country; }
    public void set_country(String country) { this.country = country; }

    public String get_countryCode() { return this.countryCode; }
    public void set_countryCode(String countryCode) { this.countryCode = countryCode; }

    public String get_region() { return this.region; }
    public void set_region(String region) { this.region = region; }

    public String get_regionCode() { return this.regionCode; }
    public void set_regionCode(String regionCode) { this.regionCode = regionCode; }

    public int get_elevationMeters() { return this.elevationMeters; }
    public void set_elevationMeters(int elevationMeters) { this.elevationMeters = elevationMeters; }

    public double get_latitude() { return this.latitude; }
    public void set_latitude(double latitude) { this.latitude = latitude; }

    public double get_longitude() { return this.longitude; }
    public void longitude(double longitude) { this.longitude = longitude; }

    public int get_population() { return this.population; }
    public void set_population(int population) { this.population = population; }

    public String get_timezone() { return this.timezone; }
    public void set_timezone(String timezone) { this.timezone = timezone; }

    public boolean get_deleted() { return this.deleted; }
    public void set_deleted(boolean deleted) { this.deleted = deleted; }

    @Override
    public String toString() {
        return "City {" + "\n" +
                "id='" + id + '\'' +
                ", wikiDataId='" + wikiDataId + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", elevationMeters=" + elevationMeters +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", population=" + population +
                ", timezone='" + timezone + '\'' +
                ", deleted=" + deleted +
                '}' + "\n" + "\n";
    }
}
