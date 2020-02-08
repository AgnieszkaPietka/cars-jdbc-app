package agnieszka.homework;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test implements Dao {
	 static List<Car> cars = new ArrayList<>();

	    static {
	        Car honda = new Car("1234", "Honda", "Civic", LocalDate.of(2012, 12, 12), "Niebieski");
	        honda.setId(1);
	        cars.add(honda);
	       
	    }


	    @Override
	    public List<Car> getCars() {
	        return cars;
	    }

	    @Override
	    public List<Car> searchCar(Map<String, String> param) {
	        List<Car> temp1Cars = new ArrayList<>();
	        List<Car> temp2Cars = new ArrayList<>();
	        List<Car> temp3Cars = new ArrayList<>();
	        List<Car> temp4Cars = new ArrayList<>();
	        List<Car> temp5Cars = new ArrayList<>();
	        if (param.get("brand") != null) {
	            for (Car car : cars) {
	                if (car.getBrand().equals(param.get("brand")))
	                    temp1Cars.add(car);
	            }
	        } else temp1Cars.addAll(cars);
	        if (param.get("model") != null) {
	            for (Car car : temp1Cars) {
	                if (car.getModel().equals(param.get("model")))
	                    temp2Cars.add(car);
	            }
	        } else temp2Cars.addAll(temp1Cars);
	        if (param.get("productionDate") != null) {
	            for (Car car : temp2Cars) {
	                if (car.getProductionDate().toString().equals(param.get("production_date")))
	                    temp3Cars.add(car);
	            }
	        } else temp3Cars.addAll(temp2Cars);
	        if (param.get("vin") != null) {
	            for (Car car : temp3Cars) {
	                if (car.getVin().equals(param.get("vin")))
	                    temp4Cars.add(car);
	            }
	        } else temp4Cars.addAll(temp3Cars);
	        if (param.get("color") != null) {
	            for (Car car : temp4Cars) {
	                if (car.getColor().equals(param.get("color")))
	                    temp5Cars.add(car);
	            }
	        } else temp5Cars.addAll(temp4Cars);
	        return temp5Cars;
	    }

	    @Override
	    public Car getCarById(int id) {
	        List<Car> oneCar = cars.stream()
	                .filter(car -> car.getId() == id)
	                .collect(Collectors.toList());
	        if (oneCar.size() == 0) oneCar.add(new Car());
	        return oneCar.get(0);
	    }

	    @Override
	    public boolean deleteCarById(int id) {
	        cars = cars.stream()
	                .filter(brandCar -> brandCar.getId() != id)
	                .collect(Collectors.toList());
	        return true;
	    }

	    @Override
	    public boolean addCar(Car car) {
	        car.setId(cars.get(cars.size()-1).getId() + 1);
	        cars.add(car);
	        return true;
	    }

	    @Override
	    public boolean updateCar(Car car) {
	        int i = 0;
	        for (Car element : cars) {
	            if (element.getId() != car.getId()) {
	                i++;
	            } else {
	                cars.set(i, car);
	                break;
	            }
	        }
	        return true;
	    }

	    @Override
	    public void operationsDB(DBOperations... options) {

	    }
	

}
