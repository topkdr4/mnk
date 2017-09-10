package services;
import analytics.Analytics;
import beans.Country;
import beans.Index;
import beans.ResultValue;
import beans.Value;
import exceptions.ServiceException;
import org.apache.log4j.xml.SAXErrorHandler;
import util.DBSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public final class ValueService {

    private ValueService() {

    }


    public static void add(Country country, Value value) {
        throw new UnsupportedOperationException();
    }


    public static List<Value> getValues(Country country, Index index) throws ServiceException, SQLException {
        DataSource source = DBSource.getDataSource();
        final String sql = "{call get_values(?, ?)}";
        try (Connection connection = source.getConnection()) {
            List<Value> result = new ArrayList<>();

            CallableStatement statement = connection.prepareCall(sql);
            statement.registerOutParameter(1, Types.OTHER);

            statement.setInt(1, country.getId());
            statement.setInt(2, index.getUid());

            ResultSet set = (ResultSet) statement.executeQuery().getObject(1);
            while (set.next()) {
                Value value = new Value(set.getDouble(1), set.getDouble(2));
                result.add(value);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static List<ResultValue> getResultValues(Country country, Index index) throws ServiceException {
        try {
            List<Value> values = ValueService.getValues(country, index);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;//Analytics.minSquare(values);
    }


    public static void remove(Value value) {
        throw new UnsupportedOperationException();
    }

}
