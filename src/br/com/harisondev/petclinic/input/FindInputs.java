package br.com.harisondev.petclinic.input;

import br.com.harisondev.petclinic.consultation.Consultation;
import br.com.harisondev.petclinic.consultation.storage.ConsultationStorage;
import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;
import br.com.harisondev.petclinic.user.storage.UserStorage;
import br.com.harisondev.petclinic.user.types.UserAnimalType;
import java.util.ArrayList;

public class FindInputs {

	private ConsoleInput console;

	public FindInputs(ConsoleInput console) {
		this.console = console;
	}

	//Pegar todos os pets
	public void findAllPets() {
		ArrayList<User> list = UserStorage.users;
		ArrayList<UserAnimalType> filter = new ArrayList<>();

		for (User user : list) {
			if (user != null && user.getType().equals(UserTypes.ANIMAL)) {
				filter.add((UserAnimalType) user);
			}
		}
		int index = 0;
		for (UserAnimalType animal : filter) {
			index++;

			User user = UserStorage.getUserFromCpf(animal.getCpf(), UserTypes.CLIENT);

			System.out.println("---------------------------------------");
			System.out.println("PET (" + index + "): ");
			System.out.println();
			System.out.println("Responsável: " + user.getName());
			System.out.println("CPF: " + user.getCpf());
			System.out.println();
			System.out.println("Nome: " + animal.getName());
			System.out.println("Sintomas: " + animal.getDisease());
			System.out.println("Espécie: " + animal.getSpecies());
			System.out.println("Tamanho: " + animal.getHeight() + "m");
			System.out.println("Idade: " + animal.getAge());
			System.out.println();
		}

		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
		return;
	}

	//Procurar todos os pets de um cliente pelo cpf
	public void findPets() {
		String cpf;
		cpf = console.messageFormat(false, "Buscando cliente", "Informe o CPF do cliente.");

		ArrayList<UserAnimalType> list = UserStorage.getAnimalsFromCpf(cpf);
		if (list == null) {
			System.out.println("---------------------------------------");
			System.out.println("Não foram encontrados pets para essa conta.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			index++;
			System.out.println("---------------------------------------");
			System.out.println("PET (" + index + "): ");
			System.out.println("Nome: " + list.get(i).getName());
			System.out.println("Sintomas: " + list.get(i).getDisease());
			System.out.println("Espécie: " + list.get(i).getSpecies());
			System.out.println("Tamanho: " + list.get(i).getHeight() + "m");
			System.out.println("Idade: " + list.get(i).getAge());
			System.out.println();
		}

		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
		return;
	}

	// Procurar cliente pelo cpf
	public void findClient() {
		String cpf;

		cpf = console.messageFormat(false, "Buscando cliente", "Informe o CPF do cliente.");
		User user = UserStorage.getUserFromCpf(cpf, UserTypes.CLIENT);

		if (user == null) {
			System.out.println("---------------------------------------");
			System.out.println("Cliente não encontrado no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		System.out.println("---------------------------------------");
		System.out.println("Cliente:");
		System.out.println("Nome: " + user.getName());
		System.out.println("CPF: " + user.getCpf());
		System.out.println("PETs cadastrados: " + UserStorage.getAnimalsFromCpf(user.getCpf()).size());
		System.out.println();
		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
		return;

	}

	//Pegar todas as consultas
	public void findConsultationsAll() {
		System.out.println("---------------------------------------");
		System.out.println("Todas as consultas registradas:");
		System.out.println();
		int index = 0;

		ArrayList<Consultation> list = ConsultationStorage.consultations;

		if (list.size() <= 0) {
			System.out.println("---------------------------------------");
			System.out.println("Nenhuma consulta encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		User client, veterinary;

		for (int i = 0; i < list.size(); i++) {
			client = UserStorage.getUserFromCpf(list.get(i).getCpfClient(), UserTypes.CLIENT);
			veterinary = UserStorage.getUserFromCpf(list.get(i).getCpfVeterinary(), UserTypes.VETERINARY);

			if (client == null || veterinary == null)
				return;

			index++;
			System.out.println("Consulta: (" + index + ")");
			System.out.println();
			System.out.println("Id: " + list.get(i).getId());
			System.out.println("Cliente: " + client.getName());
			System.out.println("Veterinário: " + veterinary.getName());
			System.out.println();
			System.out.println("Diagnóstico: " + list.get(i).getDiagnostic());
			System.out.println("Data: " + list.get(i).getDate());
			System.out.println();
		}

		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
	}

	//Pegar consulta pelo CPF
	public void findConsultations() {
		String cpf;
		cpf = console.messageFormat(false, "Pesquisando por consultas", "Qual o CPF que deseja pesquisar?");

		System.out.println("---------------------------------------");

		ArrayList<Consultation> list = ConsultationStorage.getConsultationsFromCpf(cpf);

		if (list.size() <= 0) {
			System.out.println("---------------------------------------");
			System.out.println("Nenhuma consulta encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		int index = 0;

		User client, veterinary;

		for (int i = 0; i < list.size(); i++) {
			index++;

			client = UserStorage.getUserFromCpf(list.get(i).getCpfClient(), UserTypes.CLIENT);
			veterinary = UserStorage.getUserFromCpf(list.get(i).getCpfVeterinary(), UserTypes.VETERINARY);

			System.out.println("Consulta: (" + index + ")");
			System.out.println();
			System.out.println("Id: " + list.get(i).getId());
			System.out.println("Cliente: " + client.getName());
			System.out.println("Veterinário: " + veterinary.getName());
			System.out.println();
			System.out.println("Diagnóstico: " + list.get(i).getDiagnostic());
			System.out.println("Data: " + list.get(i).getDate());
			System.out.println();
		}

		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
	}

	//Pegar todos os clientes
	public void findAllClients() {
		
		ArrayList<User> users = UserStorage.users;
		ArrayList<User> filter = new ArrayList<>();
		
		for (User user : users) {
			if (user != null && user.getType().equals(UserTypes.CLIENT)) {
				filter.add(user);
			}
		}
		
		int index = 0;
		for (User user : filter) {
			index++;
			System.out.println("---------------------------------------");
			System.out.println("Cliente (" + index + "): ");
			System.out.println();
			System.out.println("Nome: " + user.getName());
			System.out.println("CPF: " + user.getCpf());
			System.out.println();			
		}
		
		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
		return;
	}
	
	//Pegar todos os veterinarios
	public void findAllVeterinarians() {
		
		ArrayList<User> users = UserStorage.users;
		ArrayList<User> filter = new ArrayList<>();
		
		for (User user : users) {
			if (user != null && user.getType().equals(UserTypes.VETERINARY)) {
				filter.add(user);
			}
		}
		
		int index = 0;
		for (User user : filter) {
			index++;
			System.out.println("---------------------------------------");
			System.out.println("Veterinário (" + index + "): ");
			System.out.println();
			System.out.println("Nome: " + user.getName());
			System.out.println("CPF: " + user.getCpf());
			System.out.println();			
		}
		
		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
		return;
	}
}