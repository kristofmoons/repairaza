package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Item;
import be.thomasmore.repairaza.model.Restaureur;
import be.thomasmore.repairaza.model.Taxateur;
import be.thomasmore.repairaza.repositories.ItemRepository;
import be.thomasmore.repairaza.repositories.RestaureurRepository;
import be.thomasmore.repairaza.repositories.TaxateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TaxateurRepository taxateurRepository;
    @Autowired
    private RestaureurRepository restaureurRepository;


    @ModelAttribute("item")
    public Item findItem(@PathVariable(required = false) Integer id) {
        if (id == null) return new Item();

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent())
            return optionalItem.get();

        return null;
    }

    @GetMapping("/itemedit/{id}")
    public String itemEdit(Model model,
                           @PathVariable Integer id) {
        if (id == null) return "admin/itemEdit";

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            model.addAttribute("item", optionalItem.get());
        }
        model.addAttribute("taxateurs", taxateurRepository.findAll());
        model.addAttribute("restaureurs",restaureurRepository.findAll());

        return "admin/itemedit";
    }

    @PostMapping("/itemedit/{id}")
    public String itemEditPost(Model model,
                               @PathVariable int id,
                               @ModelAttribute("item") Item item,
                               @RequestParam String itemName,
                               @RequestParam String itemDetails,
                               @RequestParam double itemPrice,
                               @RequestParam int taxateurId
//                               @RequestParam int restaureurId
                               ) {


        if (item.getTaxateurs().getId() != taxateurId) {
            item.setTaxateurs(new Taxateur(taxateurId));
        }
//        if (item.getRestaureurs().hashCode() != restaureurId) {
//            item.setRestaureurs((Collection<Restaureur>) new Restaureur(restaureurId)))
//        }
        item.setItemName(itemName);
        item.setItemDetails(itemDetails);
        item.setPrice(itemPrice);
        itemRepository.save(item);
        model.addAttribute("item", item);


        return "redirect:/admin/itemedit/" + id;

    }
    @GetMapping("/itemnew")
    public String itemNew(Model model) {
        logger.info("itemNew ");
        model.addAttribute("item", new Item());
        model.addAttribute("taxateurs", taxateurRepository.findAll());
        model.addAttribute("restaureurs",restaureurRepository.findAll());
        return "admin/itemnew";
    }

    @PostMapping("/itemnew")
    public String itemNewPost(Model model,
                               @ModelAttribute("item") Item item,
//                              @RequestParam int restaureurId
                              @RequestParam int taxateurId
    ) {
        logger.info("itemNewPost -- new name=" + item.getItemName());
//        item.setRestaureurs((Collection<Restaureur>) new Restaureur(restaureurId));
        item.setTaxateurs(new Taxateur(taxateurId));
        item.setInStock(true);
        Item newItem = itemRepository.save(item);
        return "redirect:/itemDetails/" + newItem.getId();
    }

}