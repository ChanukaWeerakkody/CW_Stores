package lk.ijse.thogakade.dto;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private String id;
    private String name;
    private String city;
    private String contact;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, String city, String contact) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.contact = contact;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", contact=" + contact +
                '}';
    }
}
