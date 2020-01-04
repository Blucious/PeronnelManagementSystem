package PMS.entity;

public class Privilege {

    private String name;

    public Privilege(){
    }

    public Privilege(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "name='" + name + '\'' +
                '}';
    }
}
