package br.com.harisondev.petclinic.input;

import br.com.harisondev.petclinic.consultation.storage.ConsultationStorage;
import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;
import br.com.harisondev.petclinic.user.storage.UserStorage;
import br.com.harisondev.petclinic.user.types.UserAnimalType;

public class DeleteInputs {

	private ConsoleInput console;

	public DeleteInputs(ConsoleInput console) {
		this.console = console;
	}

	// Deleta uma consulta
	public void deleteConsult() {
		String id;

		id = console.messageFormat(false, true, "Deletando uma consulta",
				"Digite o ID da consulta (caso não saiba, use a busca para encontrá-lo).");

		Boolean consult = ConsultationStorage.deleteConsultation(id);

		if (!consult || consult == null) {
			System.out.println("---------------------------------------");
			System.out.println("Consulta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		System.out.println("---------------------------------------");
		System.out.println("Consulta deletada com sucesso.");
		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
		return;
	}

	// Deleta um user | cliente ou veterinario
	public void deleteUser() {
		String cpf;
		// Caso client seja true o tipo da conta é cliente se não veterinario

		cpf = console.messageFormat(false, false, "Deletando uma conta", "Digite o CPF da conta.");

		Boolean user = UserStorage.deleteUser(cpf);

		if (user == null || !user) {
			System.out.println("---------------------------------------");
			System.out.println("Conta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}

		System.out.println("---------------------------------------");
		System.out.println("Conta deletada com sucesso.");
		System.out.println("Pressione [Enter] para continuar.");
		console.scanner.nextLine();
		console.start();
	}

	// Deleta um pet
	public void deletePet() {
		String cpf, name;
		
		cpf = console.messageFormat(false, false, "Deletando um PET", "Digite o CPF da conta.");
		User user = UserStorage.getUserFromCpf(cpf, UserTypes.CLIENT);
		
		if (user == null) {
			System.out.println("---------------------------------------");
			System.out.println("Conta não encontrada no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}
		
		name = console.messageFormat(false, false, "Deletando um PET", "Digite nome nome do PET.");
		UserAnimalType animal = UserStorage.getAnimalFromName(cpf, name);
		
		if (animal == null) {
			System.out.println("---------------------------------------");
			System.out.println("PET não encontrado no banco de dados.");
			System.out.println("Pressione [Enter] para continuar.");
			console.scanner.nextLine();
			console.start();
			return;
		}
		
		UserStorage.deleteAnimal(cpf, name);
	}

}
