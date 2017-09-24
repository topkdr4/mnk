package services;
import analytics.Analytics;
import beans.Country;
import beans.Index;
import beans.ResultValue;
import beans.Value;
import exceptions.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.DBSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public final class ValueService {
    
    private static final Logger log = LogManager.getLogger(ValueService.class);
    

    private ValueService() {

    }


    public static void add(Country country, Index index, Value value) throws ServiceException {
        DataSource source = DBSource.getDataSource();
        final String sql = "{call add_values(?, ?, ?, ?)}";
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            
            int pos = 1;
            statement.setInt(pos++, country.getId());
            statement.setInt(pos++, index.getUid());
            statement.setDouble(pos++, value.getValueX());
            statement.setDouble(pos++, value.getValueY());
            
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }


    public static List<Value> getValues(Country country, Index index) throws ServiceException {
        DataSource source = DBSource.getDataSource();
        final String sql = "{? = call get_values(?, ?)}";
        try (Connection connection = source.getConnection()) {
            List<Value> result = new ArrayList<>();
            connection.setAutoCommit(false);
            CallableStatement statement = connection.prepareCall(sql);

            statement.registerOutParameter(1, Types.OTHER);
            statement.setInt(2, country.getId());
            statement.setInt(3, index.getUid());

            
            log.info(sql);
            statement.execute();

            ResultSet set = (ResultSet) statement.getObject(1);
            while (set.next()) {
                Value value = new Value(set.getDouble(1), set.getDouble(2));
                value.setUid(set.getInt(3));
                result.add(value);
            }

            set.close();
            return result;
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }


    public static List<ResultValue> getResultValues(Country country, Index index) throws ServiceException {
        List<Value> values = ValueService.getValues(country, index);
        return Analytics.minSquare(values);
    }
    
    
    public static void updateValue(Value value) throws ServiceException {
        DataSource source = DBSource.getDataSource();
        final String sql = "{call update_values(?, ?, ?)}";
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            
            int index = 1;
            statement.setInt(index++, value.getUid());
            statement.setDouble(index++, value.getValueX());
            statement.setDouble(index++, value.getValueY());
            
            statement.execute();
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }


    public static void remove(Value value) throws ServiceException {
        DataSource source = DBSource.getDataSource();
        final String sql = "{call remove_values(?)}";
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, value.getUid());
            statement.execute();
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }

}
