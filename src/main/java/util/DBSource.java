package util;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class DBSource {

    private static final DataSource DATA_SOURCE;

    static {
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDatabaseName("mnk");
        source.setServerName("localhost");
        source.setUser("zskuil");
        source.setPortNumber(5432);
        source.setPassword("acef13da09");
        DATA_SOURCE = source;
    }



    public static DataSource getDataSource() {
        return DATA_SOURCE;
    }
}
