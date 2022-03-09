package es.ulpgc.eite.da.lhdez.cleancodetemplate.second;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.lhdez.cleancodetemplate.R;
import es.ulpgc.eite.da.lhdez.cleancodetemplate.app.AppMediator;

/**
 * Created by Luis on March, 2021
 */
public class SecondScreen {

  public static void configure(SecondContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    String data = context.get().getString(R.string.second_message_text);

    AppMediator mediator = AppMediator.getInstance();

    SecondContract.Presenter presenter = new SecondPresenter(mediator);
    SecondContract.Model model = new SecondModel(data);
    presenter.injectModel(model);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
