package social.network.ms_geo.storage.postgres;

public class QueriesConstant {
    public static String SAVE_CITY = "INSERT INTO location.cities (city_id, is_deleted, city_title, country_id, country_title) VALUES ";
    public static String SAVE_COUNTRY = "INSERT INTO location.countries (country_id, is_deleted, country_title) VALUES ";
    public static String GET_ALL_CITIES = "SELECT * FROM location.cities WHERE country_id = ?;";
    public static String GET_ALL_COUNTRIES = "SELECT * FROM location.countries;";
}