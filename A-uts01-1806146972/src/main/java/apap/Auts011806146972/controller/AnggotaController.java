package apap.Auts011806146972.controller;

import apap.Auts011806146972.model.AnggotaModel;
import apap.Auts011806146972.service.AnggotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnggotaController {
    @Autowired
    private AnggotaService anggotaService;

    @GetMapping("/")
    private String home(Model model){
        List<AnggotaModel> anggotalist = anggotaService.getListAnggota();
        model.addAttribute("anggotalist", anggotalist);
        return "home";
    }

    @GetMapping("/anggota/{nia}")
    public String viewAnggota(
            @PathVariable String nia,
            Model model
    ) {
        String msg = "";
        boolean error = false;
        AnggotaModel anggotaModel = anggotaService.getAnggotabyNia(nia);
        model.addAttribute("anggota", anggotaModel);
        model.addAttribute("msg", msg);
        model.addAttribute("error", error);
        return "view-anggota";
    }

    @GetMapping("/anggota/update/{nia}")
    public String changeAnggotaFormPage(
            @PathVariable String nia,
            Model model
    ){
        AnggotaModel anggotaModel = anggotaService.getAnggotabyNia(nia);
        model.addAttribute("anggota", anggotaModel);
        return "form-update-anggota";
    }

    @PostMapping("/anggota/update")
    public String changeAnggotaFormSubmit(
            @ModelAttribute AnggotaModel anggotaModel,
            Model model
    ){
        String nia = anggotaService.updateAnggota(anggotaModel);

        String msg;
        boolean error;
        if (nia == null) {
            System.out.println("masuk controller if");
            msg = "GAGAL! NIA mu sudah ada di Anggota lain";
            error = true;
        }
        else {
            msg = "Berhasil di update";
            error = false;
            AnggotaModel anggotaModel1 = anggotaService.getAnggotabyNia(nia);
            model.addAttribute("anggota", anggotaModel1);
        }
        model.addAttribute("msg", msg);
        model.addAttribute("error", error);
        return "view-anggota";
    }

    @GetMapping("/anggota/add")
    public String tambahAnggotaFormPage(
            Model model
    ){
        AnggotaModel anggotaModel = new AnggotaModel();
        model.addAttribute("anggota", anggotaModel);
        return "form-add-anggota";
    }

    @PostMapping(value="/anggota/add", params = "simpan")
    public String tambahAnggotaFormSubmit(
            @ModelAttribute AnggotaModel anggotaModel,
            Model model
    ){
        String msg = "";
        boolean error;
        boolean adagak = anggotaService.addAnggota(anggotaModel);
        if (adagak != true){
            msg = "GAGAL! NIA mu sudah ada di Anggota lain";
            error = true;
        }
        else {
            msg = "Berhasil tertambah";
            System.out.println("masuk else");

            model.addAttribute("anggota", anggotaService.getAnggotabyNia(anggotaModel.getNia()));
            error = false;
        }
        model.addAttribute("error", error);
        model.addAttribute("msg", msg);
        return "view-anggota";
    }
}
