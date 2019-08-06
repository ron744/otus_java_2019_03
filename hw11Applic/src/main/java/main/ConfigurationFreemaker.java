package main;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ConfigurationFreemaker {

    private String templateDir = "hw11Applic/web/WEB-INF/templates";

    public void Configurate(Map root, String templateName, HttpServletResponse response) throws IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);

        try {
            cfg.setDirectoryForTemplateLoading(new File(templateDir));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        Template template = null;
        try {
            template = cfg.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = response.getWriter();
        try {
            template.process(root, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
