package webdriver.elements;

import org.openqa.selenium.By;

/**
 * Created by User on 06.12.2015.
 */
public class CheckBox extends BaseElement {

    public CheckBox(By locator) {
        super(locator);
    }

    public CheckBox(By locator, String name) {
        super(locator, name);
    }

    public CheckBox(String stringLocator, String nameOfElement) {
        super(stringLocator, nameOfElement);
    }

    protected String getElementType() {
        return getLoc("loc.checkBox");
    }

    public void selectCheckBox() {
        Boolean isCheked = this.getElement().isSelected();
        if (!isCheked) {
            this.getElement().click();
        }
    }

    public void unselectCheckBox() {
        if (this.getElement().isSelected()) {
            this.getElement().click();
        }
    }

    public Boolean isSelectedCheckBox() {
        return this.getElement().isSelected();
    }
}
