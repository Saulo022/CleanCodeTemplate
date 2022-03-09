package es.ulpgc.eite.da.lhdez.cleancodetemplate.first;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.eite.da.lhdez.cleancodetemplate.R;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.second.SecondActivity;

/**
 * Created by Luis on March, 2021
 */
public class FirstActivity
    extends AppCompatActivity implements FirstContract.View {


  public static String TAG = FirstActivity.class.getSimpleName();

  private FirstContract.Presenter presenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
    getSupportActionBar().setTitle(R.string.first_screen_title);

    findViewById(R.id.firstButton)
        .setOnClickListener(view -> presenter.onButtonClicked());

    // do the setup
    FirstScreen.configure(this);

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
  public void onDataUpdated(FirstViewModel viewModel) {
    //Log.e(TAG, "onDataUpdated()");

    // deal with the data
    ((TextView) findViewById(R.id.firstMessage)).setText(viewModel.data);
  }


  @Override
  public void navigateToNextScreen() {
    Intent intent = new Intent(this, SecondActivity.class);
    startActivity(intent);
  }

  @Override
  public void injectPresenter(FirstContract.Presenter presenter) {
    this.presenter = presenter;
  }
}
