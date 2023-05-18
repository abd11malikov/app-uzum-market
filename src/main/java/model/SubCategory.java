package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategory {
    private int id;
    private String name;
    private int categoryId;
}
