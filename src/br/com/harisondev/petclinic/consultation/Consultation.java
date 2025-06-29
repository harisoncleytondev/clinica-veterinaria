package br.com.harisondev.petclinic.consultation;

import java.time.LocalDate;

public class Consultation {
	
    private String id, cpfClient, cpfVeterinary, namePet, diagnostic;
    private LocalDate date;

    public Consultation(String id, String cpfClient, String cpfVeterinary, String namePet, String diagnostic, LocalDate date) {
        this.id = id;
        this.cpfClient = cpfClient;
        this.cpfVeterinary = cpfVeterinary;
        this.namePet = namePet;
        this.diagnostic = diagnostic;
        this.date = date;
    }
    
    public String getId() {
        return id;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public String getCpfVeterinary() {
        return cpfVeterinary;
    }

    public String getNamePet() {
        return namePet;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }

    public void setCpfVeterinary(String cpfVeterinary) {
        this.cpfVeterinary = cpfVeterinary;
    }

    public void setNamePet(String namePet) {
        this.namePet = namePet;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
