import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "addresses")
//(name="addresses")
public class AddressDataSet implements Serializable {
    private String street;

    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "users")
    private User user;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    AddressDataSet(){}
}
