package br.com.harisondev.petclinic.user;

import br.com.harisondev.petclinic.user.storage.UserStorage;

public class User {

	private String name, cpf;
	private UserTypes type;

	public User(String name, String cpf, UserTypes type) {
		this.setName(name);
		this.setCpf(cpf);
		this.setType(type);
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserTypes getType() {
		return type;
	}
	public void setType(UserTypes type) {
		this.type = type;
	}

	public static User getUser(String cpf, UserTypes types) {
		return UserStorage.getUserFromCpf(cpf, types);
	}
	
}
