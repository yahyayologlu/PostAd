package com.example.postad.lookup.model;

import com.example.postad.lookup.GenericLookup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;


@Getter
@Setter
@Entity
@DiscriminatorValue(GenericLookup.CONTENT_TYPE)
public class ContentType extends GenericLookup {

    private static final long serialVersionUID = 1L;

    @OneToMany(targetEntity = SubContentType.class)
    @JoinColumn(nullable = true, name = "up_content_id")
    private List<SubContentType> subContentTypes;

    private String apiName;
}
