package es.ulpgc.eite.da.lhdez.cleancodetemplate.second;

import java.lang.ref.WeakReference;

/**
 * Created by Luis on March, 2021
 */
public interface SecondContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void onDataUpdated(SecondViewModel viewModel);

    void navigateToNextScreen();

    void finishScreen();
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void onResume();

    void onStart();

    void onRestart();

    void onBackPressed();

    void onPause();

    void onDestroy();

    void onButtonClicked();
  }

  interface Model {
    String getStoredData();

    void onDataFromNextScreen(String data);

    void onRestartScreen(String data);

    void onDataFromPreviousScreen(String data);
  }

}
