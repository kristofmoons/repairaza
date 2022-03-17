package be.thomasmore.repairaza.controllers;

import be.thomasmore.repairaza.model.worker;
import be.thomasmore.repairaza.repositories.ItemRepository;
import be.thomasmore.repairaza.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
