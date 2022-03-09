package es.ulpgc.eite.da.lhdez.cleancodetemplate.second;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.lhdez.cleancodetemplate.R;

/**
 * Created by Luis on March, 2021
 */
public class SecondActivity
    extends AppCompatActivity implements SecondContract.View {

  public static String TAG = SecondActivity.class.getSimpleName();

  private SecondContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    getSupportActionBar().setTitle(R.string.second_screen_title);

    findViewById(R.id.secondButton)
        .setOnClickListener(view -> presenter.onButtonClicked());

    // do the setup
    SecondScreen.configure(this);

    if (savedInstanceState == null) {
      presenter.onStart();

    } else {
      presenter.onRestart();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    // load the data
    presenter.onResume();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

    presenter.onBackPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();

    presenter.onPause();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    presenter.onDestroy();
  }

  @Override
  public void onDataUpdated(SecondViewModel viewModel) {
    //Log.e(TAG, "onDataUpdated()");

    // deal with the data
    ((TextView) findViewById(R.id.secondMessage)).setText(viewModel.data);
  }

  @Override
  public void finishScreen() {
    finish();
  }

  @Override
  public void navigateToNextScreen() {
    Intent intent = new Intent(this, SecondActivity.class);
    startActivity(intent);
  }

  @Override
  public void injectPresenter(SecondContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
