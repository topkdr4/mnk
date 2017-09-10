import beans.Country;
import beans.Index;
import exceptions.ServiceException;
import services.CountryService;
import services.ValueService;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class Main {

    public static void main(String[] args) {
        try {
            Country country = new Country();
            country.setId(0);

            Index index = new Index(1, "");

            ValueService.getResultValues(country, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
