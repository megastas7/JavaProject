public class Country {
    private String CountryOrArea;
    private String Subregion;
    private String Region;
    private long InternetUsers;
    private long Population;

    public Country() {
    }

    public static Country getCountry(String[] strings) {
        var country = new Country();
        country.setCountryOrArea(strings[0]);
        country.setSubregion(strings[1]);
        country.setRegion(strings[2]);
        country.setInternetUsers(strings[3]);
        country.setPopulation(strings[4]);
        return country;
    }

    public String getCountryOrArea() {
        return CountryOrArea;
    }

    public void setCountryOrArea(String countryOrArea) {
        CountryOrArea = countryOrArea;
    }

    public String getSubregion() {
        return Subregion;
    }

    public void setSubregion(String subregion) {
        Subregion = subregion;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public long getInternetUsers() {
        return InternetUsers;
    }

    public void setInternetUsers(long internetUsers) {
        InternetUsers = internetUsers;
    }

    public long getPopulation() {
        return Population;
    }

    public void setPopulation(long population) {
        Population = population;
    }

    public void setInternetUsers(String internetUsers) {
        long x;
        try{
            x = Long.parseLong(internetUsers);
        }catch (NumberFormatException exception){
            x = 0;
        }
        InternetUsers = x;
    }

    public void setPopulation(String population) {
        long x;
        try{
            x = Long.parseLong(population);
        }catch (NumberFormatException exception){
            x = 0;
        }
        Population = x;
    }


}
