package pl.martyna.catering.app.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DietInput {
    private String name;

    private String description;

    private int price;
}
