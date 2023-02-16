package com.example.book.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
	
	private Integer id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String body;

}
