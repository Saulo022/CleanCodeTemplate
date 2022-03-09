package es.ulpgc.eite.da.lhdez.cleancodetemplate.first;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.AppMediator;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.FirstToSecondState;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.SecondToFirstState;

/**
 * Created by Luis on March, 2021
 */
public class FirstPresenter implements FirstContract.Presenter {

  public static String TAG = FirstPresenter.class.getSimpleName();

  private WeakReference<FirstContract.View> view;
  private FirstState state;
  private FirstContract.Model model;
  private AppMediator mediator;

  public FirstPresenter(AppMediator mediator) {
    this.mediator = mediator;
    state = mediator.getFirstState();
  }

  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state 
    state = new FirstState();

    // call the model and update the state
    state.data = model.getStoredData();

    /*
    // use passed state if is necessary
    PreviousToFirstState savedState = getStateFromPreviousScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromPreviousScreen(savedState.data);

      // update the state if is necessary
      state.data = savedState.data;
    }
    */

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

    // use passed state if is necessary
    SecondToFirstState savedState = getStateFromNextScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.onDataFromNextScreen(savedState.data);

      // update the state if is necessary
      state.data = savedState.data;
    }

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
    FirstToSecondState newState = new FirstToSecondState();
    state.data = model.getStoredData();
    newState.data = state.data;
    passStateToNextScreen(newState);
    view.get().navigateToNextScreen();
  }

  private SecondToFirstState getStateFromNextScreen() {
    return mediator.getNextFirstScreenState();
  }

  private void passStateToNextScreen(FirstToSecondState state) {
    mediator.setNextFirstScreenState(state);
  }

  /*
  private void passStateToPreviousScreen(FirstToPreviousState state) {
    mediator.setPreviousFirstScreenState(state);
  }

  private PreviousToFirstState getStateFromPreviousScreen() {
    return mediator.getPreviousFirstScreenState();
  }
  */

  @Override
  public void injectView(WeakReference<FirstContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(FirstContract.Model model) {
    this.model = model;
  }

}
