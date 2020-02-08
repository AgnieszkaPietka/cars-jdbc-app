package agnieszka.homework;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBDaoImplementation extends SupportDatabase implements Dao {

	    @Override
	    public List<Car> getCars() throws SQLException {
	        operationsDB(DBOperations.INIT_CONNECTION);
	        final String SQL_SELECT = "SELECT * FROM cars";
	        List<Car> cars = new ArrayList<>();
	        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {

	            while (resultSet.next()) {
	                Car car = new Car();
	                resultSetCar(resultSet, car);
	                cars.add(car);
	            }
	        }
	        operationsDB(DBOperations.CLOSE_CONNECTION);
	        return cars;
	    }


	    @Override
	    public List<Car> searchCar(Map<String, String> param) throws SQLException {
	        operationsDB(DBOperations.INIT_CONNECTION);
	        String SQL_SELECT = "SELECT * FROM cars";
	        if (param.get("brand") != null) {
	            SQL_SELECT += " WHERE brand = '" + param.get("brand") + "'";
	        }
	        if (param.get("model") != null) {
	            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
	            else SQL_SELECT += " WHERE ";
	            SQL_SELECT += " model = '" + param.get("model") + "'";
	        }
	        if (param.get("productionDate") != null) {
	            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
	            else SQL_SELECT += " WHERE ";
	            SQL_SELECT += "production_date = '" + param.get("production_date") + "'";
	        }
	        if (param.get("vin") != null) {
	            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
	            else SQL_SELECT += " WHERE ";
	            SQL_SELECT += "vin = '" + param.get("vin") + "'";
	        }
	        if (param.get("color") != null) {
	            if (SQL_SELECT.contains("WHERE")) SQL_SELECT += " AND ";
	            else SQL_SELECT += " WHERE ";
	            SQL_SELECT += "color = '" + param.get("color") + "'";
	        }
	        List<Car> cars = new ArrayList<>();
	        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
	            while (resultSet.next()) {
	                Car car = new Car();
	                resultSetCar(resultSet, car);
	                cars.add(car);
	            }
	        }
	        operationsDB(DBOperations.CLOSE_CONNECTION);
	        return cars;
	    }

	    @Override
	    public Car getCarById(int id) throws SQLException {
	        operationsDB(DBOperations.INIT_CONNECTION);
	        System.out.println("\n\nODCZYT DANYCH Samochodu " + id);
	        final String SQL_SELECT = "SELECT * FROM cars Where id=" + id;
	        Car car = new Car();
	        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT)) {
//	            columnsFromTableDB(resultSet);
	            if (resultSet.next()) {
	                resultSetCar(resultSet, car);
	            }
	        }
	        operationsDB(DBOperations.CLOSE_CONNECTION);
	        return car;
	    }

	    @Override
	    public boolean deleteCarById(int id) throws SQLException {
	        operationsDB(DBOperations.INIT_CONNECTION);
	        System.out.println("\n\nUsuwanie samochodu z bazy " + id);
	        final String SQL_SELECT = "DELETE FROM cars Where id=" + id;
	        statement.executeUpdate(SQL_SELECT);
	        operationsDB(DBOperations.CLOSE_CONNECTION);
	        return true;
	    }

	    @Override
	    public boolean addCar(Car car) throws SQLException {
	        operationsDB(DBOperations.INIT_CONNECTION);
	        System.out.println("\n\nWstawianie danych...");
	        String insert = String.format(
	                "INSERT INTO cars(vin, brand, model, production_date,color) VALUES ('%s','%s','%s','%s', '%s')",
	                car.getVin(),
	                car.getBrand(),
	                car.getModel(),
	                car.getProductionDate().toString(),
	                car.getColor());
	        try {
	            statement.executeUpdate(insert);
	            return true;
	        } catch (SQLException e) {
	            System.out.println("Problem z zapisem danych");
	            e.printStackTrace();
	            return false;
	        } finally {
	            operationsDB(DBOperations.CLOSE_CONNECTION);
	        }
	    }

	    @Override
	    public boolean updateCar(Car car) throws SQLException {
	        operationsDB(DBOperations.INIT_CONNECTION);
	        System.out.println("\n\nZmiana danych...");
	        String updateSQL = String.format(
	                "UPDATE cars SET vin='%s', brand='%s', model='%s', production_date='%s',color='%s' WHERE id='%s'",
	                car.getVin(),
	                car.getBrand(),
	                car.getModel(),
	                car.getProductionDate().toString(),
	                car.getColor(),
	                car.getId());
	        try {
	            statement.executeUpdate(updateSQL);
	            return true;
	        } catch (SQLException e) {
	            System.out.println("Problem z zapisem danych do tablicy!!!");
	            e.printStackTrace();
	            return false;
	        } finally {
	            operationsDB(DBOperations.CLOSE_CONNECTION);
	        }
	    }

	    private void resultSetCar(ResultSet resultSet, Car car) throws SQLException {
	        car.setId(resultSet.getInt(1));
	        car.setVin(resultSet.getString(2));
	        car.setBrand(resultSet.getString(3));
	        car.setModel(resultSet.getString(4));
	        String date = resultSet.getString(5);
	        car.setProductionDate(LocalDate.of(Integer.parseInt(date.substring(0, 4)),
	                Integer.parseInt(date.substring(5, 7)),
	                Integer.parseInt(date.substring(8, 10))));
	        car.setColor(resultSet.getString(6));
	    }

}
