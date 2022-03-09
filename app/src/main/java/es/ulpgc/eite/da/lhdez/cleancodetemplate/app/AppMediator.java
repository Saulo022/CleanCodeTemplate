package es.ulpgc.eite.da.lhdez.cleancodetemplate.app;

import es.ulpgc.eite.da.lhdez.cleancodetemplate.first.FirstState;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.second.SecondState;

/**
 * Created by Luis on March, 2021
 */
@SuppressWarnings("unused")
public class AppMediator {

  private static AppMediator INSTANCE;

  private FirstState mFirstState;
  private SecondState mSecondState;

  private FirstToSecondState firstToSecondState;
  private SecondToFirstState secondToFirstState;

  private AppMediator() {

  }

  public static AppMediator getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new AppMediator();
    }

    return INSTANCE;
  }

  // to reset the state when testing
  public static void resetInstance() {
    INSTANCE = null;
  }

  public FirstState getFirstState() {
    return mFirstState;
  }

  public SecondState getSecondState() {
    return mSecondState;
  }

  public SecondToFirstState getNextFirstScreenState() {
    SecondToFirstState state = secondToFirstState;
    secondToFirstState=null;
    return state;
  }

  public void setNextFirstScreenState(FirstToSecondState state) {
    firstToSecondState = state;
  }

  public void setPreviousSecondScreenState(SecondToFirstState state) {
    secondToFirstState=state;
  }

  public FirstToSecondState getPreviousSecondScreenState() {
    FirstToSecondState state = firstToSecondState;
    firstToSecondState=null;
    return state;
  }

  /*
  public void setPreviousFirstScreenState(FirstToPreviousState state) {

  }

  public PreviousToFirstState getPreviousFirstScreenState() {
    return null;
  }
  */

}
