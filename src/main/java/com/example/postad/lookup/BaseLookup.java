package com.example.postad.lookup;

import com.example.postad.model.BaseIdModel;
import com.example.postad.utilities.DbConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseLookup extends BaseIdModel {
    private static final long serialVersionUID = 1L;

    // to map the id s of required code information for integration with external systems.
    @Column(length = DbConstants.textShortSize)
    private String integrationCode;

}

