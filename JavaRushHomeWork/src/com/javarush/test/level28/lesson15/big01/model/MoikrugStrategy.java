package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MoikrugStrategy implements Strategy
{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
    private static final String referrer = "http://javarush.ru/";

    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> allVacancies = new ArrayList<>();

        try
        {
            int page = 0;

            while (true)
            {
                Document doc = getDocument(searchString, page);

                Elements alltheElements = doc.getElementsByClass("job");
                if (alltheElements.size() == 0) break;

                for (Element elem : alltheElements)
                {
                    Vacancy vacancy = new Vacancy();

                    vacancy.setTitle(elem.select("[class=title]").text());
                    vacancy.setCompanyName(elem.getElementsByClass("company_name").first().getElementsByTag("a").text());
                    vacancy.setCity(elem.select("[class=location]").text());
                    vacancy.setUrl("https://moikrug.ru" + elem.select("[class=title]").select("a").attr("href"));
                    vacancy.setSiteName("https://moikrug.ru/");
                    vacancy.setSalary(elem.select("[class=count]").text());
                    allVacancies.add(vacancy);
                }
                page++;
            }
        }
        catch (Exception ignored)
        {
        }

        return allVacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException
    {
        Document document = null;
        try
        {
            document = Jsoup.connect(String.format(URL_FORMAT, page, searchString)).userAgent(userAgent).referrer(referrer).get();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return document;
    }
}
