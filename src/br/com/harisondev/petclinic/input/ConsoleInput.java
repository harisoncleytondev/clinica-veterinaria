package br.com.harisondev.petclinic.input;

import java.util.Scanner;

public class ConsoleInput {

	public Scanner scanner = new Scanner(System.in);
	private FindInputs find;
	private RegisterInputs register;
	private EditInputs edit;
	private DeleteInputs delete;

	public ConsoleInput() {
		find = new FindInputs(this);
		register = new RegisterInputs(this);
		edit = new EditInputs(this);
		delete = new DeleteInputs(this);
	}

	public void start() {

		String option;

		while (true) {
			System.out.println("---------------------------------------");
			System.out.println("KiWi Pet - Clinica Veterinária");
			System.out.println();
			System.out.println("Cadastro de Paciente [0]");
			System.out.println("Cadastro de Veterinários [1]");
			System.out.println();
			System.out.println("Gerenciar informações salvas [2]");
			System.out.println("Buscar informações [3]");
			System.out.println();
			System.out.println("Criar uma consulta [4]");
			System.out.println("Consultar relatórios [5]");
			System.out.println();
			System.out.println("Sair [9]");
			System.out.println();
			System.out.println("Digite a opção que deseja.");

			option = scanner.nextLine();

			switch (option) {
			// cadastrar paciente
			case "0":
				register.registerAnimal();
				break;
			case "1":
				// cadastrar veterinario
				register.registerVeterinary();
				break;
			case "2":
				// gerenciar conta
				account();
				break;
			case "3":
				// buscar info
				findInfo();
				break;
			case "4":
				// registrar consulta
				register.registerConsultation();
				break;
			case "5":
				findAllInfos();
				// find.findConsultationsAll();
				break;
			case "9":
				// fechar
				scanner.close();
				break;
			default:
				System.out.println("---------------------------------------");
				System.out.println("Opção não encontrada.");
				System.out.println("Pressione [Enter] para continuar.");
				scanner.nextLine();
				break;
			}
		}
	}

	// Relatorios
	private void findAllInfos() {
		String option;

		while (true) {
			System.out.println("---------------------------------------");
			System.out.println("KiWi Pet - Relatórios");
			System.out.println();
			System.out.println("Ver todas as consultas [0]");
			System.out.println("Ver todos os PETs [1]");
			System.out.println("Ver todos os clientes [2]");
			System.out.println("Ver todos os veterinários [3]");
			System.out.println();
			System.out.println("Voltar [9]");
			System.out.println();
			System.out.println("Digite a opção que deseja.");

			option = scanner.nextLine();

			switch (option) {
			case "0":
				find.findConsultationsAll();
				break;
			case "1":
				find.findAllPets();
				break;
			case "2":
				find.findAllClients();
				break;
			case "3":
				find.findAllVeterinarians();
				break;
			case "9":
				start();
				break;
			default:
				System.out.println("---------------------------------------");
				System.out.println("Opção não encontrada.");
				System.out.println("Pressione [Enter] para continuar.");
				scanner.nextLine();
				break;
			}
		}

	}

	// Edição de contas
	private void account() {
		String option;

		while (true) {
			System.out.println("---------------------------------------");
			System.out.println("KiWi Pet - Gerenciando contas");
			System.out.println();
			System.out.println("Editar informações de uma conta [0]");
			System.out.println("Deletar uma conta [1]");
			System.out.println();
			System.out.println("Editar informações de um PET [2]");
			System.out.println("Deletar um PET [3]");
			System.out.println();
			System.out.println("Editar informações de uma consulta [4]");
			System.out.println("Deletar uma consulta [5]");
			System.out.println();
			System.out.println("Voltar [9]");
			System.out.println();
			System.out.println("Digite a opção que deseja.");

			option = scanner.nextLine();

			switch (option) {
			case "0":
				edit.changeUser();
				break;
			case "1":
				delete.deleteUser();
				break;
			case "2":
				edit.changePet();
				break;
			case "3":
				delete.deletePet();
				break;
			case "4":
				edit.changeConsult();
				break;
			case "5":
				delete.deleteConsult();
				break;
			case "9":
				start();
				break;
			default:
				System.out.println("---------------------------------------");
				System.out.println("Opção não encontrada.");
				System.out.println("Pressione [Enter] para continuar.");
				scanner.nextLine();
				break;
			}
		}
	}

	// Buscar informações
	private void findInfo() {
		String option;

		while (true) {
			System.out.println("---------------------------------------");
			System.out.println("KiWi Pet - Buscar informações");
			System.out.println();
			System.out.println("Buscar um cliente [0]");
			System.out.println("Buscar PETs de um cliente [1]");
			System.out.println();
			System.out.println("Buscar consultas por CPF do cliente [2]");
			System.out.println("Buscar consultas por CPF do veterinário [3]");
			System.out.println();
			System.out.println("Voltar [9]");
			System.out.println();
			System.out.println("Digite a opção que deseja.");

			option = scanner.nextLine();

			switch (option) {
			case "0":
				find.findClient();
				break;
			case "1":
				find.findPets();
				break;
			case "2":
				find.findConsultations();
				break;
			case "3":
				find.findConsultations();
				break;
			case "9":
				start();
				break;
			default:
				System.out.println("---------------------------------------");
				System.out.println("Opção não encontrada.");
				System.out.println("Pressione [Enter] para continuar.");
				scanner.nextLine();
				break;
			}
		}
	}

	public String messageFormat(Boolean empty, boolean number, String header, String content) {
		String input = "";

		while (true) {
			System.out.println("---------------------------------------");
			System.out.println("KiWi Pet - " + header + ".");
			System.out.println();
			System.out.println(content);
			System.out.println();
			System.out.println("Cancelar [0]");

			if (number) {
				try {
					String value = scanner.nextLine();
					double num = Double.parseDouble(value);
					input = String.valueOf(num);
					return input;
				} catch (Exception e) {
					System.out.println("---------------------------------------");
					System.out.println("Você precisa digitar um número.");
					System.out.println("Pressione [Enter] para continuar.");
					scanner.nextLine();
				}
				
			} else {
				input = scanner.nextLine();

				if (input.equals("0")) {
					start();
					return null;
				} else if (!empty && input.equals("")) {
					System.out.println("---------------------------------------");
					System.out.println("Você precisa digitar algo.");
					System.out.println("Pressione [Enter] para continuar.");
					scanner.nextLine();
				} else {
					return input;
				}
			}

		}
	}

}
