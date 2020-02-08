package agnieszka.homework;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Dao {


    List<Car> getCars() throws SQLException;
    
    List<Car> searchCar(Map<String, String> param) throws SQLException;

    Car getCarById(int id) throws SQLException;

    boolean deleteCarById(int id) throws SQLException;

    boolean addCar(Car car) throws SQLException;

    boolean updateCar(Car car) throws SQLException;

    void operationsDB(DBOperations ... options) throws SQLException;

}
