package agnieszka.homework;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import javax.swing.DefaultListModel;

public class CarManagement {

	    public static final String ZERO_DATE_PROD = "0001-01-01";
	    private static List<Car> cars = new ArrayList<>();
	    private static Dao dao;
	    
	    
	    public static void main(String[] args) {
	    //	dao = DaoProvider.getDao(Sources.TEST);
	    	dao = DaoProvider.getDao(Sources.DB);
	        try {
				cars = dao.getCars();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        String numberOption;

	        do {
	            numberOption = getOption("Wybierz opcjê: \n"
	                    + "[1] - Dodaj samochód \n"
	                    + "[2] - Edytuj samochód \n"
	                    + "[3] - Usuñ samochód \n"
	                    + "[4] - Lista samochodów \n"
	                    + "[5] - Wyszukiwanie samochodu \n"
	                    + "[6] - WyjdŸ \n");

	            switch (numberOption) {
	                case "1":
					try {
						if (dao.addCar(getNewCar())) System.out.println("Zapisano");
	                    else System.out.println("B³¹d zapisu");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                    break;
	                case "2":
					int numberCar;
					try {
						numberCar = readNumberCar();
	                    Car car = getNewCar();
	                    car.setId(numberCar);
						methodsDao("updateCar", car);
					} catch (SQLException e) {
						e.printStackTrace();
					}
	                    break;
	                case "3":
					try {
						deleteCar();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	                    break;
	                case "4":
					try {
						showCars();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	                    break;
	                case "5":
	                    Map<String, String> param = createParamMap();
					try {
						showFoundCars(param);
					} catch (SQLException e) {
						e.printStackTrace();
					}
	                    break;
	            }
	        } while (!numberOption.equalsIgnoreCase("6"));
	    }

	    public static void methodsDao(String method, Object c) throws SQLException {
	        switch (method) {
	            case "deleteCarById":
	                dao.deleteCarById((Integer) c);
	                break;
	            case "updateCar":
	                dao.updateCar((Car) c);
	                break;
	            case "addCar":
	                dao.addCar((Car) c);
	                break;
	        }
	        cars = dao.getCars();
	        
	    } 


	    private static int readNumberCar() throws SQLException {
	        showCars();
	        System.out.print("Podaj ID samochodu: ");
	        Scanner choiceOption = new Scanner(System.in);
	        return Integer.valueOf(choiceOption.next());
	    }


	    public static void deleteCar() throws SQLException {
	        showCars();
	        System.out.print("Który samochód usun¹æ (podaj ID: ");
	        Scanner choiceOption = new Scanner(System.in);
	        String numberOption = choiceOption.next();
	        dao.deleteCarById(Integer.valueOf(numberOption));
	    }

	    private static void showCars() throws SQLException {
	        System.out.println("--------------- Lista samochodów ---------------");
	        System.out.printf("%3s  %10s  %10s %10s %12s %10s \n", "id", "VIN", "Marka", "Model", "DataProdukcji", "Kolor");
	        System.out.println("-----------------------------------------");
	        cars = dao.getCars();
	        for (Car car : cars) {
	            System.out.printf("%3s  %10s %10s %10s %12s %10s\n", car.getId(), car.getVin(), car.getBrand(), car.getModel(),
	                    car.getProductionDate(), car.getColor());
	        }
	        System.out.println("-----------------------------------------");
	    }

	    public static void showFoundCars(Map<String, String> param) throws SQLException {
	        System.out.println("----------------------- WYNIKI WYSZUKIWANIA --------------------");
	        System.out.printf("%3s  %10s  %10s %10s %12s %11s \n", "ID", "VIN", "Marka", "Model",  "DataProd.", "Kolor");
	        System.out.println("----------------------------------------------------------------");
	        cars = dao.searchCar(param);
	        for (Car car : cars) {
	            System.out.printf("%3s  %10s  %10s %10s %12s %11s \n", car.getId(),
	                    car.getBrand(), car.getModel(), car.getVin(), car.getProductionDate(), car.getColor());
	        }
	        System.out.println("----------------------------------------------------------------");
	       
	    }

	    private static Map<String, String> createParamMap() {
	        Map<String, String> param = new HashMap<>();
	        System.out.println("Podaj szukane dane:");
	        Car searchedCar = getNewCar();
	        if (searchedCar.getBrand().length() > 0) param.put("brand", searchedCar.getBrand());
	        if (searchedCar.getModel().length() > 0) param.put("model", searchedCar.getModel());
	        if (searchedCar.getVin().length() > 0) param.put("vin", searchedCar.getVin());
	        if (!(searchedCar.getProductionDate().toString().equals(ZERO_DATE_PROD)))
	            param.put("production_date", searchedCar.getProductionDate().toString());
	        if (searchedCar.getColor().length() > 0) param.put("color", searchedCar.getColor());
	        return param;
	    }


	    private static String getOption(String s) {
	        String numberOption;
	        System.out.print(s);
	        Scanner choiceOption = new Scanner(System.in);
	        numberOption = choiceOption.next();
	        return numberOption;
	    }

	    private static Car getNewCar() {
	        Scanner readData = new Scanner(System.in);
	        String vin = getVin(readData);
	        System.out.print("Podaj markê: ");
	        String brand = readData.nextLine().toUpperCase();
	        System.out.print("Podaj model: ");
	        String model = changeUpperFirstChar(readData.nextLine());
	        LocalDate productionDate = getProductionDate(readData);
	        System.out.print("Podaj kolor: ");
	        String color = changeUpperFirstChar(readData.nextLine());
	        return new Car(vin, brand.toUpperCase(), model, productionDate, color);
	    }

	    private static LocalDate getProductionDate(Scanner readData) {
	        String prodDate;
	        do {
	            System.out.print("Podaj datê produkcji (yyyy-mm-dd): ");
	            prodDate = readData.nextLine();
	            if (prodDate.isEmpty()) {
	                prodDate = ZERO_DATE_PROD;
	            }
	        }
	        while (prodDate.length() != 10);
	        return stringToDate(prodDate);
	    }

	    public static LocalDate stringToDate(String prodDate) {
	        return LocalDate.of(Integer.parseInt(prodDate.substring(0, 4)),
	                Integer.parseInt(prodDate.substring(5, 7)),
	                Integer.parseInt(prodDate.substring(8, 10)));
	    }

	    private static String getVin(Scanner readData) {
	        String vin;
	        do {
	            System.out.print("Podaj VIN (max 4 znaki): ");
	            vin = readData.nextLine();
	        }
	        while (vin.length() > 4);
	        return vin;
	    }

	    public static String changeUpperFirstChar(String text) {
	        char[] modelNew = text.toCharArray();
	        if (!text.isEmpty()) {
	            modelNew[0] = Character.toUpperCase(modelNew[0]);
	        }
	        return new String(modelNew);
	    }
	}


