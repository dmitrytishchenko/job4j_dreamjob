package ru.job4j.servlets.exampleTestMockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ StaticService.class })
public class UserStaticServiceTest {
    private static final Object OBJECT_PARAM = new Object();
    private static final String RETURN_STRING = "result";

    private final UserStaticService useStaticService = new UserStaticService();


    public UserStaticServiceTest() {
        PowerMockito.mockStatic(StaticService.class);

        PowerMockito.when(StaticService.doStaticWithParams(OBJECT_PARAM)).thenReturn(RETURN_STRING);
    }

    @Test
    public void useStaticTest() {
        String result = useStaticService.useStatic(OBJECT_PARAM);

        PowerMockito.verifyStatic(StaticService.class);
        StaticService.doStatic();

        PowerMockito.verifyStatic(StaticService.class);
        StaticService.doStaticWithParams(OBJECT_PARAM);

        assertEquals(RETURN_STRING, result);
    }
}