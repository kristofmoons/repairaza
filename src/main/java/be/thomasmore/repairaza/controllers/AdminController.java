package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Item;
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
    public Item findItem(@PathVariable Integer id) {
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
        return "admin/itemedit";
    }

    @PostMapping("/itemedit/{id}")
    public String itemEditPost(Model model,
                               @PathVariable int id,
                               @ModelAttribute("item") Item item,
                               @RequestParam String itemName,
                               @RequestParam String itemDetails,
                               @RequestParam double itemPrice,
                               @RequestParam int taxateurId) {


        if (item.getTaxateurs().getId() != taxateurId) {
            item.setTaxateurs(new Taxateur(taxateurId));
        }
        item.setItemName(itemName);
        item.setItemDetails(itemDetails);
        item.setPrice(itemPrice);
        itemRepository.save(item);
        model.addAttribute("item", item);


        return "redirect:/admin/itemedit/" + id;

    }


}