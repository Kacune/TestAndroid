package app.kacunagi.youruchet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyElem {
    private String name;
    private double curs;

    @Override
    public String toString() {
        return "Наименование: " +name+" Курс: "+curs;
    }

    public CurrencyElem(String name, double curs) {
        this.name = name;
        this.curs = curs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurs() {
        return curs;
    }

    public void setCurs(double curs) {
        this.curs = curs;
    }

    public static List<CurrencyElem> getCurses() throws IOException, ParseException {
        List<CurrencyElem> currencies = new ArrayList<>();
        Document doc;
        doc = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp").get();
        Elements listCurrency = doc.select("Valute");
        for (Element element : listCurrency) {
            CurrencyElem currencyElem = new CurrencyElem(element.select("CharCode").text(), Double.valueOf(element.select("Value").text().replaceAll(",", ".")));
            currencies.add(currencyElem);
        }
        return currencies;
    }
}
