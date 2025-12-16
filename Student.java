public class Student {
    private int id;
    private String name;
    private String dept;
    private String sem;
    private String status;

    public Student(int id, String name, String dept, String sem, String status) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.sem = sem;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDept() { return dept; }
    public String getSem() { return sem; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}