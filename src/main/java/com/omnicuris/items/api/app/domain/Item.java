package com.omnicuris.items.api.app.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @NotNull
    @NotEmpty
    @NotBlank
    private String itemId;
    private String itemName;


}
