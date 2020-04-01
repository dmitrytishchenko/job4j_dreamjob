package ru.job4j.servlets.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersController.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        LOG.trace("Создание списка всех файлов");
        for (File name : new File("images").listFiles()) {
            images.add(name.getName());
            LOG.trace("Добавление в список файла - " + name.getName());
        }
        req.setAttribute("images", images);
        LOG.trace("Установлен атрибут images= " + images.size());
        req.getRequestDispatcher(String.format("%s/", req.getContextPath())).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        LOG.trace("Создание фабрики, для определения полей и файлов");
        try {
            List<FileItem> items = upload.parseRequest(req);
            LOG.trace("Получаем список всех данных в запросе");
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            LOG.trace("Создаем папку хранения файлов");
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    LOG.trace("Проверяем, файл или поле, если файл, то сразу пишем в поток");
                    File file = new File(folder + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
