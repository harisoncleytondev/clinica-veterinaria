package br.com.harisondev.petclinic.consultation.storage;

import br.com.harisondev.petclinic.consultation.Consultation;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsultationStorage {
    
    public static ArrayList<Consultation> consultations = new ArrayList<>();
    
    public static void loadConsultation(Consultation consult) {
        if (!consultations.contains(consult)) {
            consultations.add(consult);
        }
    }

    public static Consultation createConsultation(String cpfClient, String cpfVeterinary, String namePet, String diagnostic) {
        LocalDate date = LocalDate.now();
        
        int id = consultations.size() + 1;
        
        Consultation consult = new Consultation(String.valueOf(id), cpfClient, cpfVeterinary, namePet, diagnostic, date);
        loadConsultation(consult);
        return consult;
    }
    
    public static ArrayList<Consultation> getConsultationsFromCpf(String cpf) {
        ArrayList<Consultation> filter = new ArrayList<>();
        
        for (Consultation consult : consultations) {
            if (consult != null && consult.getCpfClient().equals(cpf) || consult.getCpfVeterinary().equals(cpf)) {
                filter.add(consult);
            }
        }
        return filter;
    }
    
    public static Consultation getConsultationFromId(String id) {
        for (Consultation consult : consultations) {
            if (consult != null && consult.getId().equals(id)) {
                return consult;
            }
        }
        return null;
    }
    
    public static Boolean deleteConsultation(String id) {
    	Consultation consult = getConsultationFromId(id);
    	if (consult == null) {
    		return false;
    	}
    	
    	consultations.remove(consultations.indexOf(consult));
    	return true;
    }
    
}
