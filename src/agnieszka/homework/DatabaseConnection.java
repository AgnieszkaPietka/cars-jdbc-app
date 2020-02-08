package agnieszka.homework;

public class DatabaseConnection {
	static final private String HOST_MY = "localhost";
    static final private int PORT = 3306;
    static final private String DB_NAME = "cars";
    static final private String USER_NAME = "Agnieszka";
    static final private String PASSWORD = "*******";
    static final private String PARAM_STRING = "useSSL=false&serverTimezone=UTC";

    static int getPORT() {
        return PORT;
    }

    static String getHOST_MY() {
        return HOST_MY;
    }

    static String getDB_NAME() {
        return DB_NAME;
    }

    static String getUSER_NAME() {
        return USER_NAME;
    }

    static String getPASSWORD() {
        return PASSWORD;
    }

    static String getPARAM_STRING() {
        return PARAM_STRING;
    }
}


