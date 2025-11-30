package devopsca1;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    private final List<Petition> petitions = new ArrayList<>();

    public PetitionController() {
        petitions.add(new Petition(1L, "Save the Park", "Stop construction"));
        petitions.add(new Petition(2L, "Open a Library", "We need a community library"));
    }

    @GetMapping
    public List<Petition> getAll() {
        return petitions;
    }

    @GetMapping("/{id}")
    public Petition getById(@PathVariable Long id) {
        return petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/create")
    public Petition create(@RequestBody Petition petition) {
        petition.setId(System.currentTimeMillis());
        petitions.add(petition);
        return petition;
    }

    @PostMapping("/{id}/sign")
    public Petition sign(@PathVariable Long id) {
        Petition p = getById(id);
        if (p != null) p.sign();
        return p;
    }

    @GetMapping("/search")
    public List<Petition> search(@RequestParam String term) {
        return petitions.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(term.toLowerCase()))
                .toList();
    }
}
