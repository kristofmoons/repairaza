package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Item;
import be.thomasmore.repairaza.repositories.ItemRepository;
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
    @PostMapping("/itemedit/{id}")
    public String itemEditPost(Model model,
                                @PathVariable int id,
                                @RequestParam String itemName) {
        logger.info("itemEditPost " + id + " -- new name=" + itemName);
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
           Item item = optionalItem.get();
            item.setItemName(itemName);
            itemRepository.save(item);
            model.addAttribute("item", item);
        }
        return "admin/itemedit";
    }


}