package udemy.exampleEntity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@Column(nullable=false)
	private String name;
	
	@Embedded
	private Address address;

	/*
	@Embedded
	@AttributeOverrides( {
		@AttributeOverride(name="street", column=@Column(name="home_street")),
		@AttributeOverride(name="city", column=@Column(name="home_city")),
		@AttributeOverride(name="zipcode", column=@Column(name="home_zipcode"))
	} )
	private Address homeAddress;

	@Embedded
	@AttributeOverrides( {
		@AttributeOverride(name="street", column=@Column(name="billing_street")),
		@AttributeOverride(name="city", column=@Column(name="billing_city")),
		@AttributeOverride(name="zipcode", column=@Column(name="billing_zipcode"))
	} )
	private Address billingAddress;
	*/
	public Person() {}		
	public Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}	
	/*
	public Person(String name, Address homeAddress, Address billingAddress) {
		this.name = name;
		this.homeAddress = homeAddress;
		this.billingAddress = billingAddress;
	}
	*/
}