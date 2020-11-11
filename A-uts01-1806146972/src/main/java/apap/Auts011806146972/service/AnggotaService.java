package apap.Auts011806146972.service;

import apap.Auts011806146972.model.AnggotaModel;

import java.util.List;

public interface AnggotaService {
    List<AnggotaModel> getListAnggota();
    AnggotaModel getAnggotabyNia(String nia);
    String updateAnggota(AnggotaModel anggotaModel);
    boolean addAnggota(AnggotaModel anggotaModel);
}
