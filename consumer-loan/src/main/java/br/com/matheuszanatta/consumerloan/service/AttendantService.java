package br.com.matheuszanatta.consumerloan.service;

import domain.Attendant;
import dto.Theme;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendantService {

    List<Attendant> attendants = new ArrayList<>();

    public AttendantService() {
        attendants.add(new Attendant("João", Theme.LOAN_CONTRACTING, 0));
        attendants.add(new Attendant("Jana", Theme.LOAN_CONTRACTING, 0));
        attendants.add(new Attendant("Joaquim", Theme.LOAN_CONTRACTING, 0));
    }

    public Attendant getAvailableAttendant(Theme theme) {
        return attendants.stream()
                .filter(attendant -> attendant.getTeam().equals(theme))
                .filter(attendant -> attendant.getCurrentRequisitions() < 3)
                .findFirst()
                .orElse(null);
    }

    public void relieveAttendants() {
        attendants.forEach(attendant -> attendant.setCurrentRequisitions(0));
    }
}
