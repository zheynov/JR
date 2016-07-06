package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * Этот класс будет реализовывать конкретную стратегию работы с сайтом ХэдХантер (http://hh.ua/ и http://hh.ru/).
 */


public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private static final String userAgent = "Mozilla/5.0";
    private static final String referrer = "http://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2";

    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> allVacancies = new ArrayList<>();

        try
        {
            int page = 0;

            while (true)
            {
                Document doc = getDocument(searchString, page);

                Elements alltheElements = doc.select("[data-qa=vacancy-serp__vacancy]");
                if (alltheElements.size() == 0) break;

                for (Element elem : alltheElements)
                {
                    Vacancy vacancy = new Vacancy();

                    vacancy.setTitle(elem.select("[data-qa=vacancy-serp__vacancy-title]").first().text());
                    vacancy.setCompanyName(elem.select("[data-qa=vacancy-serp__vacancy-employer]").text());
                    vacancy.setCity(elem.select("[data-qa=vacancy-serp__vacancy-address]").text());
                    vacancy.setUrl(elem.select("[data-qa=vacancy-serp__vacancy-title]").attr("href"));
                    vacancy.setSiteName("http://hh.ru/");
                    vacancy.setSalary(elem.select("[data-qa=vacancy-serp__vacancy-compensation]").text());

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
            document = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent(userAgent).referrer(referrer).get();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return document;
    }
}
