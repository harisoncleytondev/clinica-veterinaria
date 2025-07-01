package br.com.harisondev.petclinic.input;

import br.com.harisondev.petclinic.consultation.storage.ConsultationStorage;
import br.com.harisondev.petclinic.user.User;
import br.com.harisondev.petclinic.user.UserTypes;
import br.com.harisondev.petclinic.user.storage.UserStorage;
import br.com.harisondev.petclinic.user.types.UserAnimalType;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterInputs {

	private ConsoleInput console;
	
	public RegisterInputs(ConsoleInput console) {
		this.console = console;
	}
	
	//Cadastrando um veterinario
    public void registerVeterinary() {
        String name, cpf;

        name = console.messageFormat(false, false, "Cadastrando um Veterinário", "Qual é o nome do(a) doutor(a)?");

        cpf = console.messageFormat(false, false, "Cadastrando um Veterinário", "Qual é o CPF do(a) doutor(a)?");

        if (UserStorage.getUserFromCpf(cpf, UserTypes.CLIENT) != null || UserStorage.getUserFromCpf(cpf, UserTypes.VETERINARY) != null) {
        	System.out.println("---------------------------------------");
        	System.out.println("Já existe uma conta com esse CPF.");
            System.out.println("Pressione [Enter] para continuar.");
            console.scanner.nextLine();
            console.start();
        	return;
        }
        
        System.out.println("---------------------------------------");
        System.out.println("Veterinário:");
        System.out.println("Nome: " + name);
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
            UserStorage.createUser(name, cpf, UserTypes.VETERINARY);

            System.out.println("Salvo com sucesso.");
            return;
        }
    }

    //Cadastrando um animal
    public void registerAnimal() {
        //Responsavel
        String nameOwner, cpf;
        //Pet
        String namePet, disease, species, height, age;

        // Definindo nome do responsavel.
        nameOwner = console.messageFormat(false, false, "Cadastrando um Paciente", "Qual é o nome do(a) responsável pelo PET?");

        // Definindo cpf do responsavel
        cpf = console.messageFormat(false, false, "Cadastrando um Paciente", "Qual é o CPF do(a) responsável pelo PET?");

        if (UserStorage.getUserFromCpf(cpf, UserTypes.CLIENT) != null || UserStorage.getUserFromCpf(cpf, UserTypes.VETERINARY) != null) {
        	System.out.println("---------------------------------------");
        	System.out.println("Já existe uma conta com esse CPF.");
            System.out.println("Pressione [Enter] para continuar.");
            console.scanner.nextLine();
            console.start();
        	return;
        }
        
        // Definindo nome do PET
        namePet = console.messageFormat(false, false, "Cadastrando um Paciente", "Qual é o nome do PET?");

        // Definindo Doença
        disease = console.messageFormat(false, false, "Cadastrando um Paciente", "Quais sintomas o PET está sentindo?");

        // Definindo Especie
        species = console.messageFormat(false, false, "Cadastrando um Paciente", "Por favor, informe a espécie do PET.");

        // Definindo Especie
        height = console.messageFormat(false, true, "Cadastrando um Paciente", "Qual é o tamanho do PET (em metros)?");

        // Definindo Idade
        age = console.messageFormat(false, true, "Cadastrando um Paciente", "Qual a idade do PET?");

        System.out.println("---------------------------------------");
        System.out.println("Responsável:");
        System.out.println("Nome: " + nameOwner);
        System.out.println("CPF: " + cpf);
        System.out.println();
        System.out.println("PET: ");
        System.out.println("Nome: " + namePet);
        System.out.println("Sintomas: " + disease);
        System.out.println("Espécie: " + species);
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
            if (UserStorage.getUserFromCpf(cpf, UserTypes.CLIENT) == null) {
                UserStorage.createUser(nameOwner, cpf, UserTypes.CLIENT);
            }

            UserStorage.createAnimal(namePet, cpf, disease, species, Double.parseDouble(height), (int) Double.parseDouble(age));
            System.out.println("Salvo com sucesso.");
            return;
        }
    }

    //Cadastrando uma consulta
    public void registerConsultation() {

        String cpfClient, cpfVeterinary, namePet, diagnostic;

        // Definindo CPF do cliente
        cpfClient = console.messageFormat(false, false, "Criando uma consulta", "Para prosseguir, qual o CPF do cliente?");

        // Definindo CPF do veterinário
        cpfVeterinary = console.messageFormat(false, false, "Criando uma consulta", "Para prosseguir, qual o CPF do veterinário?");

        User client = UserStorage.getUserFromCpf(cpfClient, UserTypes.CLIENT);
        User veterinary = UserStorage.getUserFromCpf(cpfVeterinary, UserTypes.VETERINARY);

        if (client == null || veterinary == null) {
            System.out.println("Algum dos CPFs informados não foram encontrados no banco de dados.");
            System.out.println("Pressione [Enter] para continuar.");
            console.scanner.nextLine();
            console.start();
            return;
        }
        
        // Definindo nome do do pet
        UserAnimalType animal = null;

        while (animal == null) {
            namePet = console.messageFormat(false, false, "Criando uma consulta", "Para prosseguir, qual o nome do pet?");

            for (User user : UserStorage.users) {
                if (user.getName().equals(namePet)) {
                    animal = (UserAnimalType) user;
                }
            }

            if (animal == null) {
                System.out.println("O pet com o nome '" + namePet + "' não foi encontrado.");
                System.out.println("Pressione [Enter] para continuar.");
                console.scanner.nextLine();
                console.start();
                return;
            }
        }

        diagnostic = console.messageFormat(false, false, "Criando uma consulta", "Para prosseguir, qual o diagnostico do pet?");

        System.out.println("---------------------------------------");
        System.out.println("Consulta:");
        System.out.println("Cliente: " + client.getName());
        System.out.println("Veterinário: " + veterinary.getName());
        System.out.println();
        System.out.println("PET: ");
        System.out.println("Nome: " + animal.getName());
        System.out.println("Espécie: " + animal.getSpecies());
        System.out.println();
        System.out.println("Diagnóstico: " + diagnostic);
        System.out.println(LocalDate.now());
        System.out.println();
        System.out.println("Cancelar [0]");
        System.out.println("Salvar [1]");

        String input = console.scanner.nextLine();

        if (input.equals("0")) {
            console.start();
            return;
        } else {
            System.out.println("Salvando...");
            ConsultationStorage.createConsultation(cpfClient, cpfVeterinary, animal.getName(), diagnostic);

            System.out.println("Salvo com sucesso.");
            return;
        }
    }
}
