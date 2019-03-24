package com.graphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Book {

	@Id
	private String id;
	private String title;
    private String publisher;
    private String[] authors;
    private String publishedDate;

}
