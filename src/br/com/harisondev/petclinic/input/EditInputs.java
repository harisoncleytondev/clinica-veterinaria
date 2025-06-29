package br.com.harisondev.petclinic.input;

import java.util.ArrayList;

import br.com.harisondev.petclinic.consultation.Consultation;
import br.com.harisondev.petclinic.consultation.storage.ConsultationStorage;
import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;
import br.com.harisondev.petclinic.user.storage.UserStorage;
import br.com.harisondev.petclinic.user.types.UserAnimalType;

public class EditInputs {

	private ConsoleInput console;

	public EditInputs(ConsoleInput console) {
		this.console = console;
	}

	// Editar user comum | cliente/veterinario
	public void changeUser() {

		String cpf, newCpf, newName;

		cpf = console.messageFormat(false, "Editando uma conta", "Informe o CPF da conta que deseja editar");

		// pega o usuario pelo cpf independente de ser veterinario ou cliente
		User oldUser = null;
		for (User user : UserStorage.users) {
			if (user.getCpf().equals(cpf) && user.getType() != UserTypes.ANIMAL) {
				oldUser = user;
				return;
			}
		}

		if (oldUser == null) {
			System.out.println("---------------------------------------");
			System.out.println("Conta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		newCpf = console.messageFormat(true, "Editando uma conta", "Novo CPF (Pressione [Enter] para não alterar)");
		if (newCpf.equals("")) {
			newCpf = oldUser.getCpf();
		}
		newName = console.messageFormat(true, "Editando uma conta", "Novo nome (Pressione [Enter] para não alterar)");
		if (newName.equals("")) {
			newName = oldUser.getName();
		}

		System.out.println("---------------------------------------");
		System.out.println("Novos dados:");
		System.out.println();
		System.out.println("Nome: " + newName);
		System.out.println("CPF: " + cpf);
		System.out.println();
		System.out.println("Cancelar [0]");
		System.out.println("Salvar [1]");

		String input = console.scanner.nextLine();

		if (input.equals("0")) {
			console.start();
			return;
		} else {
			System.out.println("Salvando...");
			ArrayList<Consultation> consults = ConsultationStorage.getConsultationsFromCpf(cpf);
			ArrayList<UserAnimalType> animals = UserStorage.getAnimalsFromCpf(cpf);

			for (Consultation consult : consults) {
				if (oldUser.getType().equals(UserTypes.CLIENT)) {
					consult.setCpfClient(newCpf);
				} else {
					consult.setCpfVeterinary(newCpf);
				}
			}

			for (User user : animals) {
				user.setCpf(newCpf);
			}

			oldUser.setCpf(newCpf);
			oldUser.setName(newName);
			System.out.println("Salvo com sucesso.");
			console.start();
			return;
		}

	}

	// Editar pet
	public void changePet() {
		String cpf, oldName, name, disease, species, height, age;

		cpf = console.messageFormat(false, "Editando um PET", "Informe o CPF do dono do PET que deseja editar");
		User client = UserStorage.getUserFromCpf(cpf, UserTypes.CLIENT);

		if (client == null) {
			System.out.println("---------------------------------------");
			System.out.println("Conta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		oldName = console.messageFormat(false, "Editando um PET", "Informe o nome do PET que deseja editar");
		UserAnimalType animal = UserStorage.getAnimalFromName(cpf, oldName);

		if (animal == null) {
			System.out.println("---------------------------------------");
			System.out.println("PET não encontrado no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		name = console.messageFormat(true, "Editando um PET", "Novo nome (Pressione [Enter] para não alterar)");
		if (name.equals("")) {
			name = animal.getName();
		}

		disease = console.messageFormat(true, "Editando um PET", "Nova doença (Pressione [Enter] para não alterar)");
		if (disease.equals("")) {
			disease = animal.getDisease();
		}

		species = console.messageFormat(true, "Editando um PET", "Nova espécie (Pressione [Enter] para não alterar)");
		if (species.equals("")) {
			species = animal.getSpecies();
		}

		height = console.messageFormat(true, "Editando um PET",
				"Novo tamanho [Em metros] (Pressione [Enter] para não alterar)");
		if (height.equals("")) {
			height = String.valueOf(animal.getHeight());
		}

		age = console.messageFormat(true, "Editando um PET", "Nova idade (Pressione [Enter] para não alterar)");
		if (age.equals("")) {
			age = String.valueOf(animal.getAge());
		}

		System.out.println("---------------------------------------");
		System.out.println("Novos dados:");
		System.out.println();
		System.out.println("Nome: " + name);
		System.out.println("Doença: " + disease);
		System.out.println();
		System.out.println("Tamanho: " + height + "m");
		System.out.println("Idade: " + age);
		System.out.println();
		System.out.println("Cancelar [0]");
		System.out.println("Salvar [1]");

		String input = console.scanner.nextLine();

		if (input.equals("0")) {
			console.start();
			return;
		} else {
			System.out.println("Salvando...");

			animal.setName(name);
			animal.setDisease(disease);
			animal.setAge(Integer.parseInt(age));
			animal.setHeight(Double.parseDouble(height));

			System.out.println("Salvo com sucesso.");
			console.start();
		}

	}

	// Editar consulta
	public void changeConsult() {
		String id, cpfClient, cpfVeterinary, namePet, diagnostic;

		id = console.messageFormat(false, "Editando uma consulta",
				"Digite o ID da consulta (caso não saiba, use a busca para encontrá-lo).");

		Consultation consult = ConsultationStorage.getConsultationFromId(id);

		if (consult == null) {
			System.out.println("---------------------------------------");
			System.out.println("Consulta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		cpfClient = console.messageFormat(true, "Editando uma consulta",
				"Novo CPF do cliente (Pressione [Enter] para não alterar");
		if (cpfClient.equals("")) {
			cpfClient = consult.getCpfClient();
		}
		User client = UserStorage.getUserFromCpf(cpfClient, UserTypes.CLIENT);
		if (client == null) {
			System.out.println("---------------------------------------");
			System.out.println("Conta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		cpfVeterinary = console.messageFormat(true, "Editando uma consulta",
				"Novo CPF do veterinário (Pressione [Enter] para não alterar");
		if (cpfVeterinary.equals("")) {
			cpfVeterinary = consult.getCpfVeterinary();
		}
		User veterinary = UserStorage.getUserFromCpf(cpfClient, UserTypes.VETERINARY);
		if (veterinary == null) {
			System.out.println("---------------------------------------");
			System.out.println("Conta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		namePet = console.messageFormat(true, "Editando uma consulta",
				"Novo nome para o PET (Pressione [Enter] para não alterar");
		if (namePet.equals("")) {
			namePet = consult.getNamePet();
		}

		diagnostic = console.messageFormat(true, "Editando uma consulta",
				"Novo diagnóstico (Pressione [Enter] para não alterar");
		if (diagnostic.equals("")) {
			diagnostic = consult.getDiagnostic();
		}

		System.out.println("---------------------------------------");
		System.out.println("Novos dados:");
		System.out.println();
		System.out.println("Cliente: " + client.getName() + " (" + client.getCpf() + ")");
		System.out.println("Veterinário: " + veterinary.getName() + " (" + veterinary.getCpf() + ")");
		System.out.println();
		System.out.println("PET: " + namePet);
		System.out.println("Diagnóstico.: " + diagnostic);
		System.out.println();
		System.out.println("Cancelar [0]");
		System.out.println("Salvar [1]");

		String input = console.scanner.nextLine();

		if (input.equals("0")) {
			console.start();
			return;
		} else {
			System.out.println("Salvando...");

			consult.setNamePet(namePet);
			consult.setDiagnostic(diagnostic);
			consult.setCpfClient(cpfClient);
			consult.setCpfVeterinary(cpfVeterinary);

			System.out.println("Salvo com sucesso.");
			console.start();
		}

	}

}
