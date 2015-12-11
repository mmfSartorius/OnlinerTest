package onliner.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class OnlinerMainForm extends BaseForm {

    private Button lblCatalog = new Button(By.linkText("Каталог и цены"));

    public OnlinerMainForm() {
        super(By.className("catalog-bar__list"), "Onliner by");
    }

    public void assertLabel() {
        assert (lblCatalog.isPresent());
    }

    public OnlinerCatalogForm navigateToCatalog() {
        lblCatalog.click();
        browser.waitForPageToLoad();
        return new OnlinerCatalogForm();
    }
}
