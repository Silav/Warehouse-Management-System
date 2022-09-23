package Automated_JUnit_Tests;

import Controller.Admin_Management;
import Controller.User_Management;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_Case_2 {

    @Test
    public void testSetup() {
        String str = "I am done with Junit setup";
        assertEquals("I am done with Junit setup", str);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
        System.out.println("Fetching all users from Database");
        User_Management.get_All_Users_from_File();
    }

    @Test
    public void test_Admin_Login() throws Exception {

        System.out.println("Test Case Admin login");
        assertEquals(true, Admin_Management.admin_Login());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");
    }

}