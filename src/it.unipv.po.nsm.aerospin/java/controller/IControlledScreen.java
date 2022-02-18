package controller;

import view.ScreensController;

public interface IControlledScreen {

    //Permette di recuperare lo screen Parent
    public void setScreenParent(ScreensController screenPage);
}