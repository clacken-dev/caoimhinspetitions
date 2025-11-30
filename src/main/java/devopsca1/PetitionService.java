package devopsca1;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetitionService {
    private final List<Petition> petitions = new ArrayList<>();

    public PetitionService() {
        petitions.add(new Petition(1L, "Save the Park", "Stop the new construction."));
        petitions.add(new Petition(2L, "Community Library", "Open a new library in town."));
    }

    public List<Petition> getAll() {
        return petitions;
    }

    public Optional<Petition> getById(Long id) {
        return petitions.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Petition add(Petition petition) {
        petitions.add(petition);
        return petition;
    }
}
