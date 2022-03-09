package es.ulpgc.eite.da.lhdez.cleancodetemplate.second;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.AppMediator;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.FirstToSecondState;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.SecondToFirstState;

/**
 * Created by Luis on March, 2021
 */
public class SecondPresenter implements SecondContract.Presenter {

  public static String TAG = SecondPresenter.class.getSimpleName();

  private WeakReference<SecondContract.View> view;
  private SecondState state;
  private SecondContract.Model model;
  private AppMediator mediator;

  public SecondPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getSecondState();
  }

  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state 
    state = new SecondState();

    // call the model and update the state
    state.data = model.getStoredData();

    // use passed state if is necessary
    FirstToSecondState savedState = getStateFromPreviousScreen();

    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromPreviousScreen(savedState.data);

      // update the state if is necessary
      state.data = savedState.data;
    }
  }

  @Override
  public void onRestart() {
    // Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.data);
  }

  @Override
  public void onResume() {
    // Log.e(TAG, "onResume()");

    /*
    // use passed state if is necessary
    NextToSecondState savedState = getStateFromNextScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromNextScreen(savedState.data);

      // update the state if is necessary
      state.data = savedState.data;
    }
    */

    // call the model and update the state
    //state.data = model.getStoredData();

    // update the view
    view.get().onDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    // Log.e(TAG, "onBackPressed()");
  }

  @Override
  public void onPause() {
    // Log.e(TAG, "onPause()");
  }

  @Override
  public void onDestroy() {
    // Log.e(TAG, "onDestroy()");
  }

  @Override
  public void onButtonClicked() {
    SecondToFirstState savedState = new SecondToFirstState();
    //savedState.data = state.data;
    savedState.data = model.getStoredData();
    passStateToPreviousScreen(savedState);

    view.get().finishScreen();
  }

  /*
  private NextToSecondState getStateFromNextScreen() {
    return mediator.getNextSecondScreenState();
  }

  private void passStateToNextScreen(SecondToNextState state) {
    mediator.setNextSecondScreenState(state);
  }
  */

  private void passStateToPreviousScreen(SecondToFirstState state) {
    mediator.setPreviousSecondScreenState(state);
  }

  private FirstToSecondState getStateFromPreviousScreen() {
    return mediator.getPreviousSecondScreenState();
  }

  @Override
  public void injectView(WeakReference<SecondContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(SecondContract.Model model) {
    this.model = model;
  }

}
