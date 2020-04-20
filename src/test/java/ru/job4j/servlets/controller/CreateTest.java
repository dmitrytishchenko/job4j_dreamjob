//package ru.job4j.servlets.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PowerMockIgnore;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import ru.job4j.servlets.model.Role;
//import ru.job4j.servlets.model.User;
//import ru.job4j.servlets.repository.MemoryStore;
//import ru.job4j.servlets.repository.Store;
//import ru.job4j.servlets.repository.ValidateService;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Date;
//
//import static org.junit.Assert.assertFalse;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(ValidateService.class)
////@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "javax.management.*"})
////@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", "com.amazonaws.http.conn.ssl.*", "javax.net.ssl.*"})
////@PowerMockIgnore({"javax.management.*", "javax.crypto.*"})
//public class CreateTest {
//    private Store store;
//    private @org.mockito.Mock
//    HttpSession session;
//    private @org.mockito.Mock
//    HttpServletRequest req;
//    private @Mock
//    HttpServletResponse resp;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        this.store = MemoryStore.getInstance();
//        this.store.add(new User(1, "Petr", "Arsentev",
//                "A", "B@e.n", new Role("ADMIN"),
//                new Date(), "1"));
//        this.req = PowerMockito.mock(HttpServletRequest.class);
//        this.resp = PowerMockito.mock(HttpServletResponse.class);
//        when(this.req.getSession(false)).thenReturn(this.session);
//    }
//
//    @Test
//    public void whenDeleteUserThenHasNoInStoreIt() throws ServletException, IOException {
//        when(this.req.getParameter("id")).thenReturn("1");
//        final User user = this.store.findById(1);
//        System.out.println("STORE BEFORE: " + this.store.findAll().toString());
//        when(this.session.getAttribute("user")).thenReturn(user);
//        final Delete servlet = new Delete();
//        servlet.doPost(this.req, this.resp);
//        final boolean isUserById = this.store.findAll().stream()
//                .mapToInt(User::getId)
//                .anyMatch(Integer.valueOf(1)::equals);
//        System.out.println("STORE AFTER: " + this.store.findAll());
//        assertFalse(isUserById);
//    }
//}
package ru.job4j.servlets.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.repository.Validate;
import ru.job4j.servlets.repository.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class CreateTest{
    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        Validate validate = ValidateStub.getInstance();
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("Petr Arsentev");
        new Create().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getName(), is("Petr Arsentev"));
    }
}