package marketapp.model;

public class Product {

	private int id;
	private String name;
	private float price;
	private String image;	

	public Product() {
		super();
		this.id = 0;
		this.name = "";		
		this.price = 0;
		this.image = "https://picsum.photos/100";
	}

	public Product(String name) {
		// Taking the values from the above constructor
		this();
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + "]";
	}
}
