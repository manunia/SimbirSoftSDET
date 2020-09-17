package UI_testing.pages;

import UI_testing.config.SeleniumHandler;

public abstract class AbstractPage {
    public SeleniumHandler handler;

    public AbstractPage(SeleniumHandler handler) {
        this.handler = handler;
    }
}
