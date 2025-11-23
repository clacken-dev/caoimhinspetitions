package devopsca1;

public class Petition {
    private Long id;
    private String title;
    private String description;
    private int signatures;

    public Petition(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.signatures = 0;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getSignatures() { return signatures; }
    public void sign() { signatures++; }
}
