package com.desilva;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.fail;


/**
 * Created by stevedesilva
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class,Application.class})
public class ApplicationTest {

    @Test
    public void main_WhenInvalidInput_ShouldExitApplication() {

        PowerMockito.mockStatic(System.class);
        try {
            String[] fakePathArgs = new String[]{"/fakePath/AddressBook"};
            Application.main(fakePathArgs);

            // verify exit was called
            PowerMockito.verifyStatic();
            System.exit(0);

        } catch (Exception e) {
            fail(" Exception then running method");
        }
    }


}
