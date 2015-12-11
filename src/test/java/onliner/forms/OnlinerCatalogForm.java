package onliner.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class OnlinerCatalogForm extends BaseForm {
    private Button btnTv = new Button(By.cssSelector("a[href = 'http://catalog.onliner.by/tv']"), "Catalog link");

    public OnlinerCatalogForm() {
        super(By.className("catalog-navigation-classifier "), "catalog onliner By");
    }

    public OnlinerTvCatalogForm navigateCatalogTv() {
        btnTv.click();
        browser.waitForPageToLoad();
        return new OnlinerTvCatalogForm();
    }

}