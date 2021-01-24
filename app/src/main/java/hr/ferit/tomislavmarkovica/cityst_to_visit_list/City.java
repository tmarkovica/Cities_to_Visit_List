package hr.ferit.tomislavmarkovica.cityst_to_visit_list;

import org.json.JSONException;
import org.json.JSONObject;

public class City {
    private int id = 0;
    private String wikiDataId = "";
    private String type = "";
    private String city = "";
    private String name = "";
    private String country = "";
    private String countryCode = "";
    private String region = "";
    private String regionCode = "";
    private int elevationMeters = 0;
    private double latitude = 0;
    private double longitude = 0;
    private int population = 0;
    private String timezone = "";
    private boolean deleted;

    public City(String name) { this.name = name; }

    public City(int id,
            String wikiDataId,
            String type,
            String city,
            String name,
            String country,
            String countryCode,
            String region,
            String regionCode,
            double latitude,
            double longitude) {
        this.id = id;
        this.wikiDataId = wikiDataId;
        this.type = type;
        this.city = city;
        this.name = name;
        this.country = country;
        this.countryCode = countryCode;
        this.region = region;
        this.regionCode = regionCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int get_id() { return this.id; }
    public void set_id(int id) { this.id = id; }

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
        return type + "\n" +
                "-------------" + "\n" +
                id + "\n" +
                "name: " + name + "\n" +
                "country: " + country + "\n" +
                "countryCode: " + countryCode + "\n" +
                "region: " + region + "\n" +
                "regionCode: " + regionCode + "\n" +
                "latitude: " + latitude + "\n" +
                "longitude: " + longitude  +"\n";
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", id);
            obj.put("wikiDataId", wikiDataId);
            obj.put("type", type);
            obj.put("city", city);
            obj.put("name", name);
            obj.put("country", country);
            obj.put("countryCode", countryCode);
            obj.put("region", region);
            obj.put("regionCode", regionCode);
            obj.put("latitude", latitude);
            obj.put("longitude", longitude);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
