package apap.Auts011806146972.service;

import apap.Auts011806146972.model.AnggotaModel;
import apap.Auts011806146972.repository.AnggotaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnggotaServiceImpl implements AnggotaService{
    @Autowired
    AnggotaDb anggotaDb;

    @Override
    public List<AnggotaModel> getListAnggota() {
        return anggotaDb.findAll();
    }

    @Override
    public AnggotaModel getAnggotabyNia(String nia) {
        return anggotaDb.findByNia(nia).get();
    }

    @Override
    public String updateAnggota(AnggotaModel anggotaModel) {
        AnggotaModel targetAnggota = anggotaDb.findById(anggotaModel.getId()).get();
//        Optional<AnggotaModel> targetAnggota = anggotaDb.findByNia(anggotaModel.getNia());
        String nia = null;
        try{
            Optional<AnggotaModel> ceknia = anggotaDb.findByNia(anggotaModel.getNia());
            if(ceknia.isPresent()){
                System.out.println(ceknia.get().getId());
                System.out.println(targetAnggota.getId());
                if(!ceknia.get().getId().equals(targetAnggota.getId())){
                    System.out.println("masukkk");
                    return null;
                }
            }

                targetAnggota.setAlamat(anggotaModel.getAlamat());
                targetAnggota.setJenis_kelamin(anggotaModel.getJenis_kelamin());
                targetAnggota.setNama(anggotaModel.getNama());
                targetAnggota.setNo_hp(anggotaModel.getNo_hp());
                targetAnggota.setTanggal_lahir(anggotaModel.getTanggal_lahir());
                targetAnggota.setPekerjaan(anggotaModel.getPekerjaan());
                targetAnggota.setStatus(anggotaModel.getStatus());
                targetAnggota.setNia(anggotaModel.getNia());
                nia = targetAnggota.getNia();
//                System.out.println("masuk else service");
//                System.out.println(nia);
//            System.out.println(nia);
            return nia;
        } catch (NullPointerException nullException){
            return null;
        }
    }

    @Override
    public boolean addAnggota(AnggotaModel anggotaModel) {
            Optional<AnggotaModel> penerbanganModel1 = anggotaDb.findByNia(anggotaModel.getNia());
            if(!penerbanganModel1.isPresent()) {
                anggotaDb.save(anggotaModel);
                return true;
            }
            return false;
    }
}
