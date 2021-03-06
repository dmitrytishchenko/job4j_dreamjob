package ru.job4j.servlets.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Validate;
import ru.job4j.servlets.repository.ValidateService;
import ru.job4j.servlets.repository.ValidateStub;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
public class DeleteTest {
    @Test
    public void whenAddUserThenDelete() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        User user = new User("Dimitry", "tdmitriu", "1", "tdmitriu@mail.ru", "USA", "NY", new Date(), "photo", new Role("user"));
        validate.add(user);
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        new Delete().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }
}