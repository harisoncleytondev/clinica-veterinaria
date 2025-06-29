package br.com.harisondev.petclinic;

import br.com.harisondev.petclinic.input.ConsoleInput;
import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;
import br.com.harisondev.petclinic.user.storage.UserStorage;

public class Main {

	public static void main(String[] args) {
		UserStorage.createAnimal("Totó",                   // name
			    "123",            // cpf
			    "Alergia alimentar",      // disease
			    "Cachorro",               // species
			    0.45,                     // height (m)
			    4);
		
		UserStorage.createAnimal("Miau",                   // name
			    "123",            // cpf 
			    "Perna quebrada",         // disease
			    "Gato",                   // species
			    0.30,                     // height (m)
			    2);     
		
		UserStorage.createUser("joao", "123", UserTypes.CLIENT);
		UserStorage.createUser("dr joao", "234", UserTypes.VETERINARY);
		
		ConsoleInput console = new ConsoleInput();
		console.start();
	}

}
