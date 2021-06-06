package com.example.postad.lookup;

import com.example.postad.utilities.DbConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "generic_lookup")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "class_type", discriminatorType = DiscriminatorType.STRING)
public class GenericLookup extends BaseLookup {
    private static final long serialVersionUID = 1L;

    public static final String COMPANY_REGISTRY_TYPE = "company_registry_type";
    public static final String EXPERT_COST_TYPE = "expert_cost_type";
    public static final String CONTENT_TYPE = "content_type";
    public static final String COLOR_TYPE = "color_type";
    public static final String SUB_CONTENT = "sub_content";
    public static final String COST_UNIT = "cost_unit";

    @Column(length = DbConstants.textTallSize, nullable = false)
    private String name;

    private Integer value;

}
