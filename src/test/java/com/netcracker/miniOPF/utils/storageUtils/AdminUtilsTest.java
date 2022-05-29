package com.netcracker.miniOPF.utils.storageUtils;

import com.netcracker.miniOPF.model.admin.Admin;
import com.netcracker.miniOPF.model.admin.AdminImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
class AdminUtilsTest
{
    private List<Admin> admins;
    private List<Admin> adminsReversed;
    private AdminUtils adminUtils;

    @BeforeEach
    public void setUp()
    {
        adminUtils = new AdminUtils();

        admins = new ArrayList<>(Arrays.asList(
                AdminImpl.builder().id(1).login("admin1").name("testName1").password("testPassword1").build()
                , AdminImpl.builder().id(2).login("admin2").name("testName2").password("testPassword2").build()
                , AdminImpl.builder().id(3).login("admin3").name("testName3").password("testPassword3").build()
                , AdminImpl.builder().id(4).login("admin4").name("testName4").password("testPassword4").build()
                , AdminImpl.builder().id(5).login("admin5").name("testName5").password("testPassword5").build()));

        adminsReversed = new ArrayList<>(admins);
        Collections.reverse(adminsReversed);
    }

    @AfterEach
    public void clearAll()
    {
        admins.clear();
        adminsReversed.clear();
    }

    @Test
    public void sortAdminsByLogin()
    {
        Assertions.assertEquals(admins, adminUtils.sortAdminsByLogin(adminsReversed, false));
        Assertions.assertEquals(adminsReversed, adminUtils.sortAdminsByLogin(admins, true));
    }

    @Test
    public void sortAdminsByLoginWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByLogin(null, false));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByLogin(new ArrayList<>(), false));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Sorting empty list", nullException.getMessage());
    }

    @Test
    public void sortAdminsByPassword()
    {
        Assertions.assertEquals(admins, adminUtils.sortAdminsByPassword(adminsReversed, false));
        Assertions.assertEquals(adminsReversed, adminUtils.sortAdminsByPassword(admins, true));
    }

    @Test
    public void sortAdminsByPasswordWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByPassword(null, false));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByPassword(new ArrayList<>(), false));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Sorting empty list", nullException.getMessage());
    }

    @Test
    public void sortAdminsByID()
    {
        Assertions.assertEquals(admins, adminUtils.sortAdminsByID(adminsReversed, false));
        Assertions.assertEquals(adminsReversed, adminUtils.sortAdminsByID(admins, true));
    }

    @Test
    public void sortAdminsByIDWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByID(null, false));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByID(new ArrayList<>(), false));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Sorting empty list", nullException.getMessage());
    }

    @Test
    public void sortAdminsByName()
    {
        Assertions.assertEquals(admins, adminUtils.sortAdminsByName(adminsReversed, false));
        Assertions.assertEquals(adminsReversed, adminUtils.sortAdminsByName(admins, true));
    }

    @Test
    public void sortAdminsByNameWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByName(null, false));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.sortAdminsByName(new ArrayList<>(), false));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Sorting empty list", nullException.getMessage());
    }

    @Test
    public void searchAdminByLogin()
    {
        Assertions.assertEquals(admins.get(1), adminUtils.searchAdminByLogin(admins, "admin2"));
        Assertions.assertNull(adminUtils.searchAdminByLogin(admins, "admin"));
    }

    @Test
    public void searchAdminsByLoginWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminByLogin(null, "admin"));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminByLogin(new ArrayList<>(), "admin"));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Searching in empty list", nullException.getMessage());
    }

    @Test
    public void searchAdminsByPassword()
    {
        Assertions.assertEquals(new ArrayList<>(List.of(admins.get(1))),
                                adminUtils.searchAdminsByPassword(admins, "testPassword2"));
        Assertions.assertEquals(new ArrayList<Admin>(), adminUtils.searchAdminsByPassword(admins, "testPassword"));
    }

    @Test
    public void searchAdminsByPasswordWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminsByPassword(null, "testPassword"));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminsByPassword(new ArrayList<>(), "testPassword"));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Searching in empty list", nullException.getMessage());
    }

    @Test
    public void searchAdminByID()
    {
        Assertions.assertEquals(admins.get(0), adminUtils.searchAdminByID(admins, 1));
        Assertions.assertNull(adminUtils.searchAdminByID(admins, 6));
    }

    @Test
    public void searchAdminsByIDWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminByID(null, 1));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminByID(new ArrayList<>(), 1));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Searching in empty list", nullException.getMessage());
    }

    @Test
    public void searchAdminsByName()
    {
        Assertions.assertEquals(new ArrayList<>(List.of(admins.get(2))),
                                adminUtils.searchAdminsByName(admins, "testName3"));
        Assertions.assertEquals(new ArrayList<Admin>(), adminUtils.searchAdminsByName(admins, "testName"));
    }

    @Test
    public void searchAdminsByNameWithException()
    {
        Exception nullException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminsByName(null, "testName"));

        Exception emptyException = Assertions.assertThrows(NullPointerException.class, () ->
                adminUtils.searchAdminsByName(new ArrayList<>(), "testName"));

        Assertions.assertEquals(nullException.getMessage(), emptyException.getMessage());

        Assertions.assertEquals("Searching in empty list", nullException.getMessage());
    }
}