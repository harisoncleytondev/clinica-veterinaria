package br.com.harisondev.petclinic.user.types;

import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;

public class UserAnimalType extends User {
	
	//Disease = doença.
	private String disease, species;
	private double height;
	private int age;

	public UserAnimalType(String name, String cpf, String disease, String species, double height, int age) {
		super(name, cpf, UserTypes.ANIMAL); 
		this.setDisease(disease);
		this.setSpecies(species);
		this.setHeight(height);
		this.setAge(age);
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
