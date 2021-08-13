package info.project.omdb.omdbApiRetrofitService;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

/**
 * Created by lorrayne on 09/11/2016.
 */
public class RetrofitLoader extends AsyncTaskLoader<searchService.ResultWithDetail> {

    private static final String LOG_TAG = "RetrofitLoader";

    private final String mTitle;

    private searchService.ResultWithDetail mData;

    public RetrofitLoader(Context context, String title) {
        super(context);
        mTitle = title;
    }

    @Override
    public searchService.ResultWithDetail loadInBackground() {
        // obtém resultados da chamada da API
        try {
            searchService.Result result = searchService.performSearch(mTitle);
            searchService.ResultWithDetail resultWithDetail = new searchService.ResultWithDetail(result);
            if (result.Search != null) {
                for (searchService.Movie movie : result.Search) {
                    resultWithDetail.addToList(searchService.getDetail(movie.imdbID));
                }
            }
            return resultWithDetail;
        } catch (final IOException e) {
            Log.e(LOG_TAG, "Error from api access", e);
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {

            deliverResult(mData); // loaderManager chama onLoadFinished
        } else {
            forceLoad(); // chamada de loadInBackground
        }
    }


    @Override
    protected void onReset() {
        Log.d(LOG_TAG, "onReset");
        super.onReset();
        mData = null;
    }

    @Override
    public void deliverResult(searchService.ResultWithDetail data) {
        if (isReset()) {
            // O carregador foi redefinido; Ignora o resultado e invalida os dados.
            return;
        }

        //Mantém uma referência aos dados antigos para que ele não receba o lixo coletado.
        searchService.ResultWithDetail oldData = mData;
        mData = data;

        if (isStarted()) {
            //Se o Loader estiver em um estado iniciado, entregue os resultados
            //O método da super classe realiza essa tarefa
            super.deliverResult(data);
        }

    }
}
