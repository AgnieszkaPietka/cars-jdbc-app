package agnieszka.homework;

import java.time.LocalDate;


public class Car {
	private int id;
	private String vin;
	private String brand;
	private String model;
	private LocalDate productionDate;
	private String color;
	
		
	public Car() {
	}
	
	public Car(String vin, String brand, String model, LocalDate productionDate, String color) {
	
	this.vin = vin;
	this.brand = brand;
	this.model = model;
	this.productionDate = productionDate;
	this.color = color;
	}
	
	public int getId() {
		return id;	
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public LocalDate getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "\nCar [id=" + id + ", vin=" + vin + ", brand=" + brand + ", model=" + model
		        + ", productionDate=" + productionDate + ", color=" + color + "]";
	}


}


