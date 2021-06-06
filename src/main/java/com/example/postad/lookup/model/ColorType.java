package com.example.postad.lookup.model;

import com.example.postad.lookup.GenericLookup;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@DiscriminatorValue(GenericLookup.COLOR_TYPE)
public class ColorType extends GenericLookup {

    private static final long serialVersionUID = 1L;

    private String apiName;
}
