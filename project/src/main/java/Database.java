import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    public static void insertCountry(Connection connection, Country country) throws SQLException {
        var s = connection.prepareStatement("INSERT INTO 'Country' ('CountryOrArea','Subregion','Region','InternetUsers','Population') VALUES (?,?,?,?,?);");
        s.setString(1, country.getCountryOrArea());
        s.setString(2, country.getSubregion());
        s.setString(3, country.getRegion());
        s.setLong(4, country.getInternetUsers());
        s.setLong(5, country.getPopulation());
        s.executeUpdate();
        s.close();
    }

}
