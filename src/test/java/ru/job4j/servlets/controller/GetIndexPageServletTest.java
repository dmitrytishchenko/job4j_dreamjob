package ru.job4j.servlets.controller;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


public class GetIndexPageServletTest {
private final static String path = "WEB-INF/index.jsp";
@Test
    public void test() throws ServletException, IOException {
    final GetIndexPageServlet servlet = new GetIndexPageServlet();
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
    servlet.doGet(request, response);
    verify(request, times(1)).getRequestDispatcher(path);
    verify(request, never()).getSession();
    verify(dispatcher).forward(request, response);
}
}