package es.ulpgc.eite.da.lhdez.cleancodetemplate.second;

/**
 * Created by Luis on March, 2021
 */
public class SecondModel implements SecondContract.Model {

  public static String TAG = SecondModel.class.getSimpleName();

  private String data;

  public SecondModel(String data) {
    this.data = data;
  }

  @Override
  public String getStoredData() {
    // Log.e(TAG, "getStoredData()");
    return data;
  }

  @Override
  public void onRestartScreen(String data) {
    // Log.e(TAG, "onRestartScreen()");
  }

  @Override
  public void onDataFromNextScreen(String data) {
    // Log.e(TAG, "onDataFromNextScreen()");
  }

  @Override
  public void onDataFromPreviousScreen(String data) {
    // Log.e(TAG, "onDataFromPreviousScreen()");
  }
}