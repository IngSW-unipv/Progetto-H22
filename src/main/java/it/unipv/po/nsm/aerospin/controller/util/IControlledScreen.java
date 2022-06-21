package controller.util;

import view.ScreenContainer;

/**
 * Interfaccia che permette di mantenere la gerarchia tra Screen
 *
 * @author GruppoNoSuchMethod
 */
public interface IControlledScreen {

    void setScreenParent(ScreenContainer screenPage);
}
