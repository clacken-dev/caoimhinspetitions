package devopsca1;

public class Petition {

    private Long id;
    private String title;
    private String description;
    private int signatures = 0;

    public Petition() {}

    public Petition(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getSignatures() { return signatures; }

    public void sign() { this.signatures++; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
}
