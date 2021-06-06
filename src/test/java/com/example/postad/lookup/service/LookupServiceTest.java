package com.example.postad.lookup.service;

import com.example.postad.exceptionhandle.BaseException;
import com.example.postad.lookup.GenericLookup;
import com.example.postad.lookup.LookupRepo;
import com.example.postad.lookup.LookupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LookupServiceTest {

    private LookupService lookupService;

    @Mock
    private LookupRepo repo;

    @Before
    public void init() { lookupService = new LookupService(repo); }

    @Test
    public void findOne() {
        //given
        final String lookupId = "9aa524ab-ebf9-4a38-815f-163d7ebf";

        when(repo.findById(lookupId)).thenReturn(Optional.of(new GenericLookup()));

        //when
        GenericLookup lookup = lookupService.findOne(lookupId);

        //then
        assertThat(lookup).isNotNull();
    }

    @Test
    public void findOneNotFound() {
        //given
        final String lookupId = "9aa524ab-ebf9-4a38-815f-163d7ebf";

        when(repo.findById(lookupId)).thenReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> lookupService.findOne(lookupId));

        //then
        assertThat(throwable).isInstanceOf(BaseException.class).hasMessage("Lookup not found with given id: " + lookupId);
    }

    @Test
    public void findByIntegrationCodeAndNameContains() {
        //given
        final String code = "12";
        final String name = "iphone";

        when(repo.findByIntegrationCodeAndNameContains(code, name)).thenReturn(new ArrayList<>());

        //when
        List<GenericLookup> generic = lookupService.findByIntegrationCodeAndNameContains(code,name);

        //then
        assertThat(generic).isNotNull();
    }

    @Test
    public void findByNameIsContaining() {
        //given
        final String name = "iphone";

        when(repo.findByNameIsContaining(name)).thenReturn(new ArrayList<>());

        //when
        List<GenericLookup> generic = lookupService.findByNameIsContaining(name);

        //then
        assertThat(generic).isNotNull();
    }

    @Test
    public void findByIntegrationCode() {
        //given
        final String code = "9aa524ab";

        when(repo.findByIntegrationCode(code)).thenReturn(new ArrayList<>());

        //when
        List<GenericLookup> generic = lookupService.findByIntegrationCode(code);

        //then
        assertThat(generic).isNotNull();
    }

    @Test
    public void findByClassType() {

        when(repo.findByClassType(GenericLookup.COLOR_TYPE)).thenReturn(new ArrayList<>());
        when(repo.findByClassType(GenericLookup.CONTENT_TYPE)).thenReturn(new ArrayList<>());
        when(repo.findByClassType(GenericLookup.COST_UNIT)).thenReturn(new ArrayList<>());
        when(repo.findByClassType(GenericLookup.EXPERT_COST_TYPE)).thenReturn(new ArrayList<>());
        when(repo.findByClassType(GenericLookup.COMPANY_REGISTRY_TYPE)).thenReturn(new ArrayList<>());
        when(repo.findByClassType(GenericLookup.SUB_CONTENT)).thenReturn(new ArrayList<>());

        //when
        List<GenericLookup> colorType = lookupService.findByClassType(GenericLookup.COLOR_TYPE);
        List<GenericLookup> contentType = lookupService.findByClassType(GenericLookup.CONTENT_TYPE);
        List<GenericLookup> costType = lookupService.findByClassType(GenericLookup.COST_UNIT);
        List<GenericLookup> expertCostType = lookupService.findByClassType(GenericLookup.EXPERT_COST_TYPE);
        List<GenericLookup> companyType = lookupService.findByClassType(GenericLookup.COMPANY_REGISTRY_TYPE);
        List<GenericLookup> subContentType = lookupService.findByClassType(GenericLookup.SUB_CONTENT);

        //then
        assertThat(colorType).isNotNull();
        assertThat(contentType).isNotNull();
        assertThat(costType).isNotNull();
        assertThat(expertCostType).isNotNull();
        assertThat(companyType).isNotNull();
        assertThat(subContentType).isNotNull();
    }
}