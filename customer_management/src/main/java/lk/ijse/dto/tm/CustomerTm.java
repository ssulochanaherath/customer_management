package lk.ijse.dto.tm;

public class CustomerTm {
    private String id;
    private String name;
    private String address;
    private int tele;

    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String address, int tele) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tele = tele;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTele() {
        return tele;
    }

    public void setTele(int tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tele=" + tele +
                '}';
    }
}
