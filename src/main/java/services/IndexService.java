package services;
import beans.Index;
import exceptions.ServiceException;
import org.apache.log4j.Logger;
import util.DBSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public final class IndexService {

    private static final Logger log = Logger.getLogger(IndexService.class);

    private IndexService() {

    }


    public static List<Index> getIndexes() throws ServiceException {
        DataSource source = DBSource.getDataSource();
        List<Index> result = new ArrayList<>();
        try (Connection connection = source.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM t_index");
            while (set.next()) {
                String uid  = set.getString("uid");
                String name = set.getString("name");
                Index index = new Index(Integer.valueOf(uid), name);

                result.add(index);
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }

        return result;
    }


    public static void register(String futureName) throws ServiceException{
        DataSource source = DBSource.getDataSource();
        final String sql = "{call add_index(?)}";
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setString(1, futureName);
            statement.executeQuery();
            log.info(sql);
        } catch (SQLException e) {
            log.warn(e);
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }


    public static void remove(Index index) throws ServiceException {
        final String method = "{call remove_index(?)}";
        DataSource source = DBSource.getDataSource();
        try (Connection connection = source.getConnection()) {
            CallableStatement statement = connection.prepareCall(method);
            statement.setInt(1, index.getUid());
            statement.executeQuery();
            log.info(method);
        } catch (SQLException e) {
            throw new ServiceException(e.getErrorCode(), e.getMessage());
        }
    }

}
