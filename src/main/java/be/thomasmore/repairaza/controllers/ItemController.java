package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.Item;
import be.thomasmore.repairaza.model.Liefhebber;
import be.thomasmore.repairaza.repositories.ItemRepository;
import be.thomasmore.repairaza.repositories.LiefhebberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private LiefhebberRepository liefhebberRepository;

    private Logger logger =  LoggerFactory.getLogger(ItemController.class);

    private Item findItemById(Collection<Item> items, int itemId) {
        for (Item i : items) {
            if (i.getId() == itemId) return i;
        }
        return null;
    }

    @GetMapping({"/itemDetails/{id}","/itemDetails"})
    public String itemDetails(Model model,
                              Principal principal,
                              @PathVariable(required = false) Integer id){
        if (id == null) return "itemDetails";

        Optional<Item> itemFromDb = itemRepository.findById(id);
        if (itemFromDb.isPresent()){
            Item foundItemForLiefhebber = null;
            if (principal != null) {
                Optional<Liefhebber> optionalLiefhebber = liefhebberRepository.findByUserUsername(principal.getName());
                if (optionalLiefhebber.isPresent()) {
                    Liefhebber liefhebber = optionalLiefhebber.get();
                    foundItemForLiefhebber = findItemById(liefhebber.getItems(), id);
                }
            }

            model.addAttribute("item",itemFromDb.get());
            long nrOfItems= itemRepository.count();
            model.addAttribute("prevId",  id > 1 ? id-1 : nrOfItems);
            model.addAttribute("nextId", id < nrOfItems ? id + 1 : 1);
            model.addAttribute("vindLeuk", foundItemForLiefhebber != null);

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
        boolean showFilter = false;
        model.addAttribute("showFilters",showFilter);
        model.addAttribute("items",allItems);
        return "itemlist";

    }
    private Boolean StringToBoolean(String filterString) {
        return (filterString == null || filterString.equals("all")) ? null : filterString.equals("yes");
    }
    @GetMapping({"/itemlist/filter"})
    public String itemlistWithFilter(Model model,
                                     @RequestParam(required = false) String name,
                                     @RequestParam(required = false) String soort,
                                     @RequestParam(required = false) String intstock,
                                     @RequestParam(required = false) Double minPrice,
                                     @RequestParam(required = false) Double maxPrice ){

        logger.info(String.format("itemListWithFilter ----------minPrice=%d, maxPrice=%d, name=%s, soort=%s, intstock=%s",
                minPrice,maxPrice,name, soort,intstock));
        logger.info(soort);

        List<Item> items = itemRepository.findByFilter(minPrice, maxPrice,
                StringToBoolean(intstock),
               name==null|| name.isBlank()? null: name,
                soort==null|| soort.isBlank()?null: soort);

        model.addAttribute("showFilters", true);
        model.addAttribute("items", items);
        model.addAttribute("nrOfItems", items.size());
        model.addAttribute("name", name);
        model.addAttribute("soort", soort);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("intstock", intstock);

        return "itemlist";
    }

    @PostMapping({"/itemleuk", "/itemleuk/{id}"})
    public String itemLeukToggle(Model model,
                                   Principal principal,
                                   @PathVariable(required = false) Integer id) {
        if (id == null) return "redirect:/itemlist";
        if (principal == null) return "redirect:/itemDetails/" + id;

        Optional<Liefhebber> optionalLiefhebber = liefhebberRepository.findByUserUsername(principal.getName());
        Optional<Item> optionalItem = itemRepository.findById(id);

        if (optionalItem.isPresent() && optionalLiefhebber.isPresent()) {
            Liefhebber liefhebber = optionalLiefhebber.get();
            Item foundItemforLiefhebber = findItemById(liefhebber.getItems(), id);
            if (foundItemforLiefhebber == null)
                liefhebber.getItems().add(optionalItem.get());
            else
                liefhebber.getItems().remove(foundItemforLiefhebber);
            liefhebberRepository.save(liefhebber);
        }
        return "redirect:/itemDetails/" + id;
    }

}
