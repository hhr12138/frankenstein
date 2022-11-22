package common.choice.frankenstein.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private Long id;
    private Long styleId;
    private List<Long> componentIds;
}
