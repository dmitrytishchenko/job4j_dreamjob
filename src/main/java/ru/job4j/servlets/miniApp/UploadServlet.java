package ru.job4j.servlets.miniApp;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.repository.Dispatcher;

import javax.servlet.RequestDispatcher;
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
    private static final Logger LOG = LogManager.getLogger(UploadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();

        LOG.trace("Создан список имен файлов");
        for (File name : new File("images").listFiles()) {
            images.add(name.getName());
            LOG.trace("Добавлено новое имя файла - " + name.getName());
        }
        req.setAttribute("images", images);
        LOG.trace("Установлен аттрибут images");
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        LOG.trace("Создана фабрика для определения файла или поля");
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        LOG.trace("Определена папка для хранения файлов");
        factory.setRepository(repository);
        LOG.trace("Папка для хранения добавлена в фабрику");
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            LOG.trace("Получаем список всех данных в запросе");
            File folder = new File("images");
            LOG.trace("Создана папка файлов");
            if (!folder.exists()) {
                folder.mkdir();
                LOG.trace("Создание папки images");
            }
            LOG.trace("Проверка всех данных в запросе на тип (файл, поле)");
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + item.getName());
                    LOG.trace("Если не поле, значит файл. Загрузка нового файла.");
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