package org.likelion.likelionrecrud.category.domain;

import org.likelion.likelionrecrud.item.domain.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryItem {
	@Id
	@Column(name = "category_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@Builder
	private CategoryItem(Category category, Item item) {
		this.category = category;
		this.item = item;
	}

	public void updateCategoryAndItem(Category category,Item item){ //item의 카테고리에 대한 내용들도 수정
		this.category = category;
		item.updateCategoryInformation(category);
	}
	// public void updateCategory(Category category){
	// 	this.category = category;
	// }
}
