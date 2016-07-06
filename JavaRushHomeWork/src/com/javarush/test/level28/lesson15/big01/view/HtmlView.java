package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View
{
    private Controller controller;

    private final String filePath = "./src/" + (this.getClass().getPackage().toString().substring(8)).replace(".", "/") + "/vacancies.html";

    protected Document getDocument() throws IOException
    {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    public void userCitySelectEmulationMethod() throws IOException
    {
        controller.onCitySelect("Odessa");
    }

    @Override
    public void update(List<Vacancy> vacancies) throws IOException
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException
    {
        Document mainDoc = null;

        try
        {
            mainDoc = getDocument();
            Element firstElement = mainDoc.getElementsByClass("template").first();
            Element elementCopy = firstElement.clone();
            elementCopy.removeClass("template").removeAttr("style");

            mainDoc.select("tr[class=vacancy]").remove();


            for (Vacancy vacancy : vacancies)
            {
                elementCopy.select("[class=city]").html(vacancy.getCity());
                elementCopy.select("[class=companyName]").html(vacancy.getCompanyName());
                elementCopy.select("[class=salary]").html(vacancy.getSalary());
                elementCopy.select("a").html(vacancy.getTitle()).attr("href", vacancy.getUrl());
                mainDoc.select("tr[class=vacancy template]").before(elementCopy.outerHtml());
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
            return "Some exception occurred";
        }

        return mainDoc.html();
    }

    private void updateFile(String string) throws IOException
    {
        FileOutputStream writer = new FileOutputStream(filePath);
        writer.write(string.getBytes("UTF-8"));
        writer.close();
    }
}
