package devopsca1;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    private final PetitionService service;

    public PetitionController(PetitionService service) {
        this.service = service;
    }

    // List all petitions
    @GetMapping
    public List<Petition> list() {
        return service.getAll();
    }

    // Get petition by ID
    @GetMapping("/{id}")
    public Petition get(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    // Sign a petition
    @PostMapping("/{id}/sign")
    public Petition sign(@PathVariable Long id) {
        Petition p = service.getById(id).orElse(null);
        if (p != null) p.sign();
        return p;
    }

    // Create a new petition
    @PostMapping("/create")
    public Petition create(@RequestParam String title, @RequestParam String description) {
        Petition p = new Petition(System.currentTimeMillis(), title, description);
        service.add(p);
        return p;
    }
}
