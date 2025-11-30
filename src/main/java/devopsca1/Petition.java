package devopsca1;

import java.util.ArrayList;
import java.util.List;

public class Petition {

    private Long id;
    private String title;
    private String description;

    // Store signatures as simple objects (name + email)
    private List<Signature> signatures = new ArrayList<>();

    public Petition() {}

    public Petition(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List<Signature> getSignatures() { return signatures; }

    public void addSignature(String name, String email) {
        signatures.add(new Signature(name, email));
    }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }

    // signature class to create petition signatures
    public static class Signature {
        private String name;
        private String email;

        public Signature() {}

        public Signature(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
