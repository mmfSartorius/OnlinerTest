package onliner.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.CheckBox;
import webdriver.elements.DropDownList;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.ArrayList;
import java.util.List;

public class OnlinerTvCatalogForm extends BaseForm {
    private CheckBox ckbMark;
    private Label lblMarks = new Label(By.xpath("//span[@data-bind = 'text: facet.dictionary.count' and text()=\"24\"]"), "label all marks");
    private TextBox txtPriceTo = new TextBox(By.xpath("//input[@class = 'schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']")
            , "textbox price to");
    private String ddlDiagonal = "//span[text() = \"Диагональ\"]/../following::div[1]//select[contains(@data-bind, \"facet.value.%s\")]";
    private DropDownList ddlDiagonalFrom = new DropDownList(By.xpath(String.format(ddlDiagonal, "from"))
            , "button diagonal from");
    private DropDownList ddlDiagonalTo = new DropDownList(By.xpath(String.format(ddlDiagonal, "to"))
            , "button diagonal to");
    private TextBox txtReleaseDateFrom = new TextBox(By.xpath("//input[@placeholder = '2011']")
            , "textbox redease date from");
    private List<Label> lblItemsList = new ArrayList<Label>();
    private String tagItem = "//span[@class = 'schema-tags__text' and contains(text(), \"%s\")] ";

    public OnlinerTvCatalogForm() {
        super(By.xpath("//h1[@class='schema-header__title' and text()=\"Телевизоры\"]"), "Catalog Tv Onliner by");
    }

    public void assertLabel() {
        assert (lblMarks.isPresent());
    }

    /**
     * Нажатие чекбокса марки
     *
     * @param mark - марка
     */
    public void selektMark(String mark) {
        lblMarks.click();
        String xpathOfMark = String.format("//input[@class='i-checkbox__real' and @value='%s']/..//span[@class = 'i-checkbox__faux']", mark.toLowerCase());
        ckbMark = new CheckBox(By.xpath(xpathOfMark), String.format("%s checkBox", mark));
        ckbMark.selectCheckBox();
        waitTagItem(mark);
    }

    /**
     * Ввод максимальной цены
     *
     * @param price - цена
     */
    public void inputPriceTo(String price) {
        txtPriceTo.setText(price);
    }

    /**
     * Выбор минимальной диагонали
     *
     * @param item - диагональ
     */
    public void selectDiagonalFrom(String item) {
        ddlDiagonalFrom.selectItemOfDropDownList(String.format("%s", item));
        waitTagItem(item);
    }

    /**
     * Выбор максимальной диагонали
     *
     * @param item - диагональ
     */
    public void selectDiagonalTo(String item) {
        ddlDiagonalTo.selectItemOfDropDownList(String.format("%s", item));
        waitTagItem(item);
    }

    /**
     * Ввод самой ранней даты изготовления
     *
     * @param date - дата
     */
    public void inputReleaseDateFrom(String date) {
        txtReleaseDateFrom.setText(date);
        waitTagItem(date);
    }

    /**
     * Ожидание принятия параметров сортировки
     *
     * @param title - название параметра
     */
    private void waitTagItem(String title) {
        Label lblTag = new Label(By.xpath(String.format(tagItem, title)));
        lblTag.waitForIsElementPresent();
    }

    /**
     * Проверка соответствия параметров
     *
     * @param mark         - марка
     * @param date         - дата изготовления
     * @param price        - цена
     * @param diagonalFrom - минимальная диагональ
     * @param diagonalTo   - максимальная диагональ
     */
    public void checkCompliance(String mark, String date, String price, String diagonalFrom, String diagonalTo) {
        for (int i = 1; i <= 30; i++) {
            Label temp = new Label(By.xpath(String.format("//div[@class = 'schema-product__group'][%s]//div[@class = 'schema-product__image']", i))
                    , "Item " + Integer.toString(i));
            if (temp.isPresent()) {
                lblItemsList.add(temp);
            }
            logger.info("Size of List " + Integer.toString(lblItemsList.size()));
        }

        for (Label temp : lblItemsList) {
            temp.click();
            browser.waitForPageToLoad();
            OnlinerTvForm onlinerTvForm = new OnlinerTvForm();
            onlinerTvForm.checkMark(mark);
            onlinerTvForm.checkPrice(price);
            onlinerTvForm.checkDate(date);
            onlinerTvForm.checkDiagonal(diagonalTo, diagonalFrom);
            browser.getDriver().navigate().back();
            browser.waitForPageToLoad();
        }
    }
}