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
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
public class UsersServletTest {
    @Test
    public void whenGetAllUsers() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        User user = new User("Dmitriy", "tdmitriu", "1", "tdmitriu@mail.ru", new Role("user"), new Date(), "photo");
        validate.add(user);
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn(String.valueOf(user.getId()));
        new UsersServlet().doPost(req, resp);
        assertThat(validate.findAll().size(), is(0));
    }
}