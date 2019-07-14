import javax.persistence.*;

@Entity
@Table(name="Address")
public class AddressDataSet {
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
