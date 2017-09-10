package services;
import beans.Country;
import exceptions.ServiceException;
import exceptions.SystemError;
import org.apache.log4j.Logger;
import util.DBSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public final class CountryService {

    private static final Logger log = Logger.getLogger(CountryService.class);

    private CountryService() {
        
    }
    
    
    public static List<Country> getCountries() throws ServiceException {
        List<Country> result = new ArrayList<>();
        DataSource source = DBSource.getDataSource();

        try (Connection connection = source.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM t_country");

            while (set.next()) {
                Country country = new Country();
                country.setName(set.getString("name"));
                country.setUrl(set.getString("url"));
                country.setId(Integer.valueOf(set.getString("uid")));

                result.add(country);
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }

        return result;
    }
    
    
    public static void register(Country country) throws ServiceException {
        final String method = "{call add_country(?, ?)}";
        DataSource source = DBSource.getDataSource();
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(method);

            statement.setString(1, country.getName());
            statement.setString(2, country.getUrl());

            log.info(method);

            ResultSet set = statement.executeQuery();
            if (set.next()) {
                int uid = Integer.valueOf(set.getString(1));
                country.setId(uid);
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }
    
    
    public static void remove(Country country) throws ServiceException {
        final String method = "{call remove_country(?)}";
        DataSource source = DBSource.getDataSource();
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(method);
            statement.setInt(1, country.getId());
            statement.executeQuery();
            log.info(method);
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }
    
}
