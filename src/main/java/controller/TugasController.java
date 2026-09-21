package controller;

import model.Storage;
import model.TugasIndividu;
import model.TugasKelompok;

import java.util.ArrayList;

public class TugasController {
    private final ArrayList<Storage> listTugasKu = new ArrayList<>();

    public TugasController() {
        listTugasKu.add(new TugasIndividu("T01", "PBO", "Laporan Mini Project 2", "2026-09-20 23:59", "Spada"));
        listTugasKu.add(new TugasKelompok("T02", "Basis Data", "Desain ERD Sistem", "2026-09-25 20:00", 4));
    }

    public ArrayList<Storage> getAllTugas() {
        return listTugasKu;
    }

    public boolean tambahTugas(Storage tugas) {
        if (cariTugas(tugas.getKodeTugas()) != null) {
            return false;
        }
        listTugasKu.add(tugas);
        return true;
    }

    public Storage cariTugas(String kodeTugas) {
        for (Storage t : listTugasKu) {
            if (t.getKodeTugas().equalsIgnoreCase(kodeTugas)) {
                return t;
            }
        }
        return null;
    }

    public boolean hapusTugas(String kodeTugas) {
        Storage t = cariTugas(kodeTugas);
        if (t != null) {
            listTugasKu.remove(t);
            return true;
        }
        return false;
    }
}