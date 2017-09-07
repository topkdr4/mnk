package services;
import beans.Country;
import exceptions.ServiceException;
import exceptions.SystemError;

import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public final class CountryService {

    private CountryService() {
        
    }
    
    
    public static List<Country> getCountries() throws ServiceException {
        throw new SystemError(1, "Unsupported operation");
    }
    
    
    public static Country register(Country country) throws SystemError {
        throw new SystemError(1, "Unsupported operation");
    }
    
    
    public static void remove(Country country) throws SystemError {
        throw new SystemError(1, "Unsupported operation");
    }
    
}
