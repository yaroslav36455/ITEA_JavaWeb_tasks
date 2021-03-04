package ua.itea.model.products;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@NamedQuery(name = "GetAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "GetAllMEDIA", query = "SELECT p FROM Product p WHERE p.category = ua.itea.model.products.Category.MEDIA")
@NamedQuery(name = "GetAllOFFICE", query = "SELECT p FROM Product p WHERE p.category = ua.itea.model.products.Category.OFFICE")
@NamedQuery(name = "GetAllCAD", query = "SELECT p FROM Product p WHERE p.category = ua.itea.model.products.Category.CAD")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "category")
	@Enumerated(value = EnumType.ORDINAL)
	private Category category;
	
	@Column(name = "picture")
	private String picture; 
	
	
	public Product() {
		/* empty */
	}
	
	public Product(int id) {
		this.id = id;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(category, description, id, name, picture, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		return category == other.category && Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(picture, other.picture) && price == other.price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", category=" + category + ", picture=" + picture + "]";
	}
}
