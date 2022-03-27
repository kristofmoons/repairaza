package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Item;
import be.thomasmore.repairaza.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/itemedit/{id}")
    public String itemEdit(Model model,
                            @PathVariable Integer id) {
        if (id == null) return "admin/itemEdit";

        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            model.addAttribute("item", optionalItem.get());
        }
        return "admin/itemedit";
    }

}