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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    private Logger logger =  LoggerFactory.getLogger(ItemController.class);

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
}
