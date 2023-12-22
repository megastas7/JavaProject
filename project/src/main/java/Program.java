import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.System.out;

public class Program {

    public static final boolean CREATE_DB = false;

    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");

        var connection = DriverManager.getConnection("jdbc:sqlite:country.db");
        //запускается один раз, чтобы создать базу данных
        if (CREATE_DB) {
            var createDbStat = connection.createStatement();
            createDbStat.execute(
                    "CREATE TABLE 'Country' ('CountryOrArea' varchar(100),'Subregion' varchar(100),'Region' varchar(100),'InternetUsers' real,'Population' real);");
            createDbStat.close();
        }

        CSVParser.parse(connection);
        answers(connection);
        connection.close();
    }

    private static void answers(Connection connection) throws SQLException {
        var data = connection.createStatement().executeQuery("SELECT Subregion, SUM(InternetUsers)/sum(Population) AS total FROM Country GROUP BY Subregion ORDER BY  total;");
        out.println("\nДанные для графика Excel");
        while (data.next())
            out.println(data.getString("Subregion") + ";=" + data.getString("total").replace(".", ","));

        out.println("\nСтраны процент зарегистрированных в интернете пользователей которых находится в промежутке от 75% до 85%");
        var countries = connection.createStatement().executeQuery("SELECT CountryOrArea,InternetUsers/Population*100 AS total FROM country WHERE total BETWEEN 75 AND 85 ORDER BY total;");
        while (countries.next())
            out.println(countries.getString("CountryOrArea"));

        out.println("\nСтрана в Восточной Европе с наименьшим кол-вом зарегистрированных в ин-ете пользователей");
        out.println(connection.createStatement().executeQuery("SELECT CountryOrArea FROM Country WHERE Subregion = \"Eastern Europe\" ORDER BY InternetUsers;").getString("CountryOrArea"));
    }
}
