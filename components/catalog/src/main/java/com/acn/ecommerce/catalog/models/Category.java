package com.acn.ecommerce.catalog.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<CatalogItem> items;

}
