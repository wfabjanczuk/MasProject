package udemy.exampleEntity;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Item{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	/*
	@ElementCollection
	@CollectionTable(name = "item_image", joinColumns=@JoinColumn(name = "item_id"))
	@Column(name = "filename")
	private Collection<String> images = new ArrayList<String>();
	*/
	
	@ElementCollection
	@Column(name = "filename")
	private Collection<String> images = new ArrayList<String>();
	
	public Item() {}
	public Item(String name) {
		this.name = name;
	}
	
	public Collection<String> getImages() {
		return images;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", images=" + images + "]";
	}
	
}
