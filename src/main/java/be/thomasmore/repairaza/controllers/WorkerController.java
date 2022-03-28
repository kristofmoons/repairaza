package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Restaureur;
import be.thomasmore.repairaza.model.Taxateur;
import be.thomasmore.repairaza.repositories.RestaureurRepository;
import be.thomasmore.repairaza.repositories.TaxateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class WorkerController {
    @Autowired
    private TaxateurRepository taxateurRepository;
    @Autowired
    private RestaureurRepository restaureurRepository;

    @GetMapping({"/workerlist"})
    public String workerlist(Model model){
        final Iterable<Taxateur> allTaxateurs =taxateurRepository.findAll();
        final Iterable<Restaureur> allRestaureurs =restaureurRepository.findAll();

        model.addAttribute("taxateurs",allTaxateurs);
        model.addAttribute("restaureurs",allRestaureurs);
        return "workerlist";

    }

    @GetMapping({"/taxateurdetails/{id}","/taxateurdetails"})
    public String taxateurdetails(Model model, @PathVariable(required = false) Integer id){
        if (id == null) return "taxateurdetails";

        Optional<Taxateur> taxateurFromDb = taxateurRepository.findById(id);
        if (taxateurFromDb.isPresent()){
            model.addAttribute("taxateur",taxateurFromDb.get());
        }
        return "taxateurdetails";
    }
    @GetMapping({"/restaureurdetails/{id}","/restaureurdetails"})
    public String restaureurdetails(Model model, @PathVariable(required = false) Integer id){
        if (id == null) return "restaureurdetails";

        Optional<Restaureur> restaureurFromDb = restaureurRepository.findById(id);
        if (restaureurFromDb.isPresent()){
            model.addAttribute("restaureur",restaureurFromDb.get());
        }
        return "restaureurdetails";
    }
}
