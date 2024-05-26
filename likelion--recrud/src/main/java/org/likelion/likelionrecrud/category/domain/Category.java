package org.likelion.likelionrecrud.category.domain;

import java.util.ArrayList;
import java.util.List;

import org.likelion.likelionrecrud.category.api.dto.request.UpdateCategoryRequestDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private Category parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Category> children = new ArrayList<>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<CategoryItem> categoryItems = new ArrayList<>();

	@Builder
	private Category(Category parent, String name) {
		this.name = name;
		this.parent = parent;
	}

	public void updateCategory(String name, Category parent){
		this.name = name;
		this.parent = parent;
		if (!this.categoryItems.isEmpty()){
			this.categoryItems.forEach(
				(categoryItem) -> categoryItem.updateCategoryAndItem(this)
			);
		}
	}
}
