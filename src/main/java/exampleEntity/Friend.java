package exampleEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Friend{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;

	@ElementCollection
	@CollectionTable(name = "friend_nickname", joinColumns=@JoinColumn(name = "friend_id"))
	@Column(name = "nickname")
	private Collection<String> nicknames = new ArrayList<String>();

	//collection of embeddable Address objects [with default address specific column names being overridden using @AttributeOverride]
	/*
	@AttributeOverrides( {
		@AttributeOverride(name="street", column=@Column(name="address_street")),
		@AttributeOverride(name="city", column=@Column(name="address_city")),
		@AttributeOverride(name="zipcode", column=@Column(name="address_zipcode"))
	} )
	private Collection<Address> addresses = new ArrayList<Address>();	
	public Collection<Address> getAddresses() {
		return addresses;
	}
	*/
	
	public Friend() {}
	public Friend(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Collection<String> getNicknames() {
		return nicknames;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", name=" + name + ", email=" + email + ", nicknames=" + nicknames + "]";
	}
	
}
