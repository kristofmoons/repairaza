package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.worker;
import be.thomasmore.repairaza.repositories.ItemRepository;
import be.thomasmore.repairaza.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class WorkerController {
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping({"/workerlist"})
    public String workerlist(Model model){
        final Iterable<worker> allWorkers =workerRepository.findAll();

        model.addAttribute("workers",allWorkers);
        return "workerlist";

    }

    @GetMapping({"/workerdetails/{id}","/workerdetails"})
    public String workerdetails(Model model, @PathVariable(required = false) Integer id){
        if (id == null) return "workerdetails";

        Optional<worker> workerFromDb = workerRepository.findById(id);
        if (workerFromDb.isPresent()){
            model.addAttribute("worker",workerFromDb.get());
        }
        int nrOfWorkers = (int) workerRepository.count();
        model.addAttribute("prevId",  id > 1 ? id-1 : nrOfWorkers);
        model.addAttribute("nextId", id < nrOfWorkers ? id + 1 : 1);
        return "workerdetails";
    }
}
