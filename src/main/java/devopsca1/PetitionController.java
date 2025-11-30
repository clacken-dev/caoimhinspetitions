package devopsca1;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    private final List<Petition> petitions = new ArrayList<>();

    public PetitionController() {
        // Preload sample petitions
        petitions.add(new Petition(1L, "Bring back plastic straws", "Paper straws are no good."));
        petitions.add(new Petition(2L, "Save the turtles", "Save turtles by reducing ocean contamination"));
        petitions.add(new Petition(3L, "Reduce cycle lanes so traffic can flow", "Open up more car lanes so that traffic can flow freely"));
        petitions.add(new Petition(4L, "Community Library", "Open a new library in town"));
    }

    // List all petitions
    @GetMapping
    public List<Petition> list() {
        return petitions;
    }

    // Get petition by ID
    @GetMapping("/{id}")
    public Petition get(@PathVariable Long id) {
        return petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create a new petition
    @PostMapping("/create")
    public Petition create(@RequestBody Petition petition) {
        if (petition.getId() == null) {
            petition.setId(System.currentTimeMillis()); // simple unique ID
        }
        petitions.add(petition);
        return petition;
    }

    // Sign petition with name and email
    @PostMapping("/{id}/sign")
    public Petition sign(@PathVariable Long id, @RequestBody Petition.Signature signature) {
        Optional<Petition> optionalPetition = petitions.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (optionalPetition.isPresent()) {
            Petition petition = optionalPetition.get();
            petition.addSignature(signature.getName(), signature.getEmail());
            return petition;
        }
        return null;
    }

    // Search petitions by title
    @GetMapping("/search")
    public List<Petition> search(@RequestParam String query) {
        return petitions.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
