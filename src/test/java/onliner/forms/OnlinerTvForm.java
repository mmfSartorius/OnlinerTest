package onliner.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Label;


public class OnlinerTvForm extends BaseForm {
    Label lblMark = new Label(By.cssSelector("h2[class = 'catalog-masthead__title']"));
    Label lblPrice = new Label(By.xpath("//a//small[contains(text() , 'руб')]/.."));
    Label lblDate = new Label(By.xpath("//span[contains(text() , 'г.')]"));
    Label lblDiagonal = new Label(By.xpath("//td[contains(text(), 'Диагональ')]/../td[2]/span"));

    public OnlinerTvForm() {
        super(By.id("device-header-image"), "Tv");
    }

    /**
     * Проверка соответствия марки
     *
     * @param mark - марка
     */
    public void checkMark(String mark) {
        Boolean check = lblMark.getElement().getText().contains(mark);
        logger.info("CheckMark " + check.toString());
        Assert.assertTrue(check);
    }

    /**
     * Проверка соответствия цены
     *
     * @param price - цена
     */
    public void checkPrice(String price) {
        String[] variationsInPrices = lblPrice.getElement().getText().split("–");
        String stringToCompare = variationsInPrices[0].replace(" ", "").replace("руб.", "");
        Boolean check = Integer.valueOf(stringToCompare) <= Integer.valueOf(price);
        logger.info("CheckPrice " + check.toString());
        Assert.assertTrue(Integer.valueOf(stringToCompare) <= Integer.valueOf(price));
    }

    /**
     * Проверка соответствия даты
     *
     * @param date - дата изготовления
     */
    public void checkDate(String date) {
        String checkDate = lblDate.getElement().getText();
        String stringToCompare = checkDate.replace(" ", "").replace("г.", "");
        Boolean check = Integer.valueOf(stringToCompare) >= Integer.valueOf(date);
        logger.info("CheckDate " + check.toString());
        Assert.assertTrue(check);
    }

    /**
     * Проверка соответствия Диагонали
     *
     * @param diagonalTo   - максимальная диагональ
     * @param diagonalFrom - минимальная диагональ
     */
    public void checkDiagonal(String diagonalTo, String diagonalFrom) {
        String checkDiagonal = lblDiagonal.getElement().getText();
        String stringToCompare = checkDiagonal.replace("\"", "");
        Boolean check = Integer.valueOf(diagonalFrom) <= Integer.valueOf(stringToCompare)
                && Integer.valueOf(stringToCompare) <= Integer.valueOf(diagonalTo);
        logger.info("CheckDiagonal " + check.toString());
        Assert.assertTrue(check);
    }
}
