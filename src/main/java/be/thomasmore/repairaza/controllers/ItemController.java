package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Item;
import be.thomasmore.repairaza.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping({"/itemDetails/{id}","/itemDetails"})
    public String itemDetails(Model model, @PathVariable(required = false) Integer id){
        if (id == null) return "itemDetails";

        Optional<Item> itemFromDb = itemRepository.findById(id);
        if (itemFromDb.isPresent()){
            model.addAttribute("item",itemFromDb.get());
            long nrOfItems= itemRepository.count();
            model.addAttribute("prevId",  id > 1 ? id-1 : nrOfItems);
            model.addAttribute("nextId", id < nrOfItems ? id + 1 : 1);
        }
        return "itemDetails";
    }


    @GetMapping({"itemchoiche"})
    public String itemchoiche(){

        return "itemchoiche";
    }

    @GetMapping({"/itemlist"})
        public String itemlist(Model model){
        final Iterable<Item> allItems =itemRepository.findAll();
        model.addAttribute("items",allItems);
        return "itemlist";

    }

    @GetMapping({"itemlist/{filter}"})
    public String itemlistWatch(Model model,@PathVariable(required = false) String filter){
        if (filter == null) return "itemlist";
        Iterable<Item> items;
        switch (filter) {
            case "watch":
                items = itemRepository.findBySoortObjectEquals("watch");
                break;
            case "car":
                items = itemRepository.findBySoortObjectEquals("car");
                break;
            case "furniture":
                items = itemRepository.findBySoortObjectEquals("furniture");
                break;
            case "instruments":
                items = itemRepository.findBySoortObjectEquals("instruments");
                break;
            default:
                items = itemRepository.findAll();
                filter = null;
                break;
        }
        model.addAttribute("items", items);
        return "itemlist";
    }
}
