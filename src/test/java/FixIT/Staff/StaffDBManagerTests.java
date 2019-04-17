package FixIT.Staff;

import org.junit.Before;
import org.junit.Test;

public class StaffDBManagerTests {

    @Before
    public void setup() {
        StaffDBManager.getInstance().dropUserTable();
        StaffDBManager.getInstance().createStaffTable();
    }

    @Test
    public void test() {

    }
}
