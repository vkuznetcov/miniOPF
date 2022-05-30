package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.area.Area;
import com.netcracker.miniOPF.model.area.AreaImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class AreaUtilsTest
{
    private AreaUtils areaUtils;
    private List<Area> areas;
    private List<Area> areasReversed;

    @BeforeEach
    void setUp()
    {
        areaUtils = new AreaUtils();

        areas = Arrays.asList(
                AreaImpl.builder().id(1).name("testName1").description("testDescription1").build()
                , AreaImpl.builder().id(2).name("testName2").description("testDescription2").build()
                , AreaImpl.builder().id(3).name("testName3").description("testDescription3").build()
                , AreaImpl.builder().id(4).name("testName4").description("testDescription4").build());
        areasReversed = new ArrayList<>(areas);
        Collections.reverse(areasReversed);
    }

    @Test
    void sortAreasByID()
    {
        assertEquals(areas, areaUtils.sortAreasByID(areasReversed, false));
        assertEquals(areasReversed, areaUtils.sortAreasByID(areas, true));
    }

    @Test
    void sortAreasByIDWithException()
    {
        Exception nullException = assertThrows(NullPointerException.class,
                                               () -> areaUtils.sortAreasByID(null, false));
        Exception emptyException = assertThrows(NullPointerException.class,
                                                () -> areaUtils.sortAreasByID(new ArrayList<>(), false));

        assertEquals("List is null", nullException.getMessage());
        assertEquals("List is empty", emptyException.getMessage());
    }

    @Test
    void sortAreasByName()
    {
        assertEquals(areas, areaUtils.sortAreasByName(areasReversed, false));
        assertEquals(areasReversed, areaUtils.sortAreasByName(areas, true));
    }

    @Test
    void sortAreasByNameWithException()
    {
        Exception nullException = assertThrows(NullPointerException.class,
                                               () -> areaUtils.sortAreasByName(null, false));
        Exception emptyException = assertThrows(NullPointerException.class,
                                                () -> areaUtils.sortAreasByName(new ArrayList<>(), false));

        assertEquals("List is null", nullException.getMessage());
        assertEquals("List is empty", emptyException.getMessage());
    }

    @Test
    void sortAreasByDescription()
    {
        assertEquals(areas, areaUtils.sortAreasByDescription(areasReversed, false));
        assertEquals(areasReversed, areaUtils.sortAreasByDescription(areas, true));
    }

    @Test
    void sortAreasByDescriptionWithException()
    {
        Exception nullException = assertThrows(NullPointerException.class,
                                               () -> areaUtils.sortAreasByDescription(null, false));
        Exception emptyException = assertThrows(NullPointerException.class,
                                                () -> areaUtils.sortAreasByDescription(new ArrayList<>(), false));

        assertEquals("List is null", nullException.getMessage());
        assertEquals("List is empty", emptyException.getMessage());
    }

    @Test
    void searchAreaByID()
    {
        assertEquals(areas.get(0), areaUtils.searchAreaByID(areas, 1));
        assertNull(areaUtils.searchAreaByID(areas, 6));
    }

    @Test
    void searchAreaByIDWithException()
    {
        Exception nullException = assertThrows(NullPointerException.class,
                                               () -> areaUtils.searchAreaByID(null, 1));
        Exception emptyException = assertThrows(NullPointerException.class,
                                                () -> areaUtils.searchAreaByID(new ArrayList<>(), 1));

        assertEquals("List is null", nullException.getMessage());
        assertEquals("List is empty", emptyException.getMessage());
    }

    @Test
    void searchAreaByName()
    {
        assertEquals(areas.get(0), areaUtils.searchAreaByName(areas, "testName1"));
        assertNull(areaUtils.searchAreaByName(areas, "testName"));
    }

    @Test
    void searchAreaByNameWithException()
    {
        Exception nullException = assertThrows(NullPointerException.class,
                                               () -> areaUtils.searchAreaByName(null, "testName1"));
        Exception emptyException = assertThrows(NullPointerException.class,
                                                () -> areaUtils.searchAreaByName(new ArrayList<>(), "testName1"));

        assertEquals("List is null", nullException.getMessage());
        assertEquals("List is empty", emptyException.getMessage());
    }

    @Test
    void searchAreasByDescription()
    {
        assertEquals(new ArrayList<>(List.of(areas.get(0))), areaUtils.searchAreasByDescription(areas, "testDescription1"));
        assertEquals(new ArrayList<Area>(), areaUtils.searchAreasByDescription(areas, "testDescription"));
    }

    @Test
    void searchAreasByDescriptionWithException()
    {
        Exception nullException = assertThrows(NullPointerException.class,
                                               () -> areaUtils.searchAreasByDescription(null, "testDescription1"));
        Exception emptyException = assertThrows(NullPointerException.class,
                                                () -> areaUtils.searchAreasByDescription(new ArrayList<>(), "testDescription1"));

        assertEquals("List is null", nullException.getMessage());
        assertEquals("List is empty", emptyException.getMessage());
    }
}