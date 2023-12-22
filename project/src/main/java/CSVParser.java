import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVParser {
    public static void parse(Connection connection) throws FileNotFoundException, SQLException {
        var pattern = Pattern.compile("(,)?((\"[^\"]*(\"{2})*[^\"]*\")*[^,]*)");
        var csv = new File("Country.csv");
        var parser = new Scanner(csv);
        parser.nextLine();

        while (parser.hasNextLine()) {
            var text = parser.nextLine();
            var matcher = pattern.matcher(text);
            var strings = new String[5];
            var i = 0;
            while (matcher.find() && i < 5) {
                var s = matcher.start();
                var e = matcher.end();
                var str = text.substring(s, e);
                strings[i] = str.replace("\"", "").replace(",", "");
                i++;
            }
            var country = Country.getCountry(strings);

            //испольняется один раз, чтобы заполнить таблицу
            if (Program.CREATE_DB)
                Database.insertCountry(connection, country);

        }

        parser.close();
    }
}
