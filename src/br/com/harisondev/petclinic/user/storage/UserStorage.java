package br.com.harisondev.petclinic.user.storage;

import java.util.ArrayList;

import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;
import br.com.harisondev.petclinic.user.types.UserAnimalType;

public class UserStorage {

	public static ArrayList<User> users = new ArrayList<>();

	public static void loadUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
	}

	public static User getUserFromCpf(String cpf, UserTypes types) {
		for (User user : users) {
			if (user.getCpf().equals(cpf) && user.getType() == types) {
				return user;
			}
		}

		return null;
	}
	
	public static ArrayList<UserAnimalType> getAnimalsFromCpf(String cpf) {
		ArrayList<UserAnimalType> filter = new ArrayList<>();
		
		for (User user : users) {
			if (user != null && user.getType() == UserTypes.ANIMAL && user.getCpf().equals(cpf)) {
				//O UserAnimalType da extends no user, sendo assim é um User. (UserAnimalType) apenas pro java identificar
				UserAnimalType animal = (UserAnimalType) user;
				filter.add(animal);
			}
		}
		return filter;
	}
	
	public static UserAnimalType getAnimalFromName(String cpf, String name) {
		for (User user : users) {
			if (user != null && user.getType() == UserTypes.ANIMAL && user.getCpf().equals(cpf)) {
				if (user.getName().equals(name)) {
					return (UserAnimalType) user;
				}
			}
		}
		
		return null;
	}

	public static User createUser(String name, String cpf, UserTypes types) {
		if (getUserFromCpf(cpf, types) != null && types != UserTypes.ANIMAL) {
			return null;
		}

		User user = new User(name, cpf, types);
		loadUser(user);

		return user;
	}
	
	public static UserAnimalType createAnimal(String name, String cpf, String disease, String species, double height, int age) {
		UserAnimalType animal = new UserAnimalType(name, cpf, disease, species, height, age);
		loadUser(animal);
		return animal;
	}
	
	public static Boolean deleteUser(String cpf) {
    	User user = null;
    	
    	for (User userr : users) {
    		if (!userr.getType().equals(UserTypes.ANIMAL) & userr.getCpf().equals(cpf)) {
    			user = userr;
    		}
    	}
    	
    	if (user == null) {
    		return false;
    	}
    	
    	users.remove(users.indexOf(user));
    	return true;
    }

	public static void deleteAnimal(String cpf, String name) {
		for (User user : users) {
			if (user != null && user.getType() == UserTypes.ANIMAL && user.getCpf().equals(cpf)) {
				if (user.getName().equals(name)) {
					users.remove(users.indexOf(user));
				}
			}
		}
		
	}

}
