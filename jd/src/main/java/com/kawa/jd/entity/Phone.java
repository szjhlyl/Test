package com.kawa.jd.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jd_item")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//

	private Long spu;//
	private Long sku;//
	private String title;//
	private Long price;//
	private String url;//
	private String pic;//
	private java.util.Date created;//
	private java.util.Date updated;//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSpu() {
		return spu;
	}

	public void setSpu(Long spu) {
		this.spu = spu;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public java.util.Date getCreated() {
		return created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	public java.util.Date getUpdated() {
		return updated;
	}

	public void setUpdated(java.util.Date updated) {
		this.updated = updated;
	}

}
