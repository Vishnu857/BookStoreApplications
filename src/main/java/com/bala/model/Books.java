package com.bala.model;
import com.mongodb.lang.NonNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("Books")
public class Books {
    @Id
    private String id;
    @NotBlank
    private String bookName;
    @NotBlank
    private String authorName;
    @NotBlank
    private String publications;
    @NotBlank
    private String publishedDate;
    @NotBlank
    private String genre;

    public Books() {

    }
}
