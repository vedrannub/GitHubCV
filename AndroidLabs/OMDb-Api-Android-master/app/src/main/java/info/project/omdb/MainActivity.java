package info.project.omdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import info.project.omdb.Utils.CommonUtils;
import info.project.omdb.omdbApiRetrofitService.RetrofitLoader;
import info.project.omdb.omdbApiRetrofitService.searchService;

/**
 * Created by lorrayne on 09/11/2016.
 */

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<searchService.ResultWithDetail> {


    private static final int LOADER_ID = 1;
    private static final String LOG_TAG = "MainActivity";
    private Button mSearchButton;
    private EditText mSearchEditText;
    private RecyclerView mMovieListRecyclerView;
    private MovieRecyclerViewAdapter mMovieAdapter;
    private String mMovieTitle;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Alerta sobre o método de entrada do título
        Toast t = Toast.makeText(this, "Título do filme de acordo com o país de origem!", Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();

        mSearchEditText = (EditText) findViewById(R.id.search_edittext);
        // Define ação para pressionar o botão de pesquisa no teclado
        mSearchEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
                    startSearch();
                    handled = true;
                }
                return handled;
            }
        });
        mSearchButton = (Button) findViewById(R.id.search_button);
        mMovieListRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch();
            }
        });
        mMovieAdapter = new MovieRecyclerViewAdapter(null);
        mMovieListRecyclerView.setAdapter(mMovieAdapter);
        // Número de colunas e a orientação
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(getResources().getInteger(R.integer.grid_column_count), StaggeredGridLayoutManager.VERTICAL);
        mMovieListRecyclerView.setItemAnimator(null);
        mMovieListRecyclerView.setLayoutManager(gridLayoutManager);
        getSupportLoaderManager().enableDebugLogging(true);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_spinner);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("mMovieTitle", mMovieTitle);
        outState.putInt("progress_visibility", mProgressBar.getVisibility());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Sempre chama a superclasse para restaurar a hierarquia de exibição
        super.onRestoreInstanceState(savedInstanceState);
        int progress_visibility = savedInstanceState.getInt("progress_visibility");
        // Se a barra de progresso estava visível antes da mudança de orientação
        if (progress_visibility == View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        mMovieTitle = savedInstanceState.getString("mMovieTitle");
        if (mMovieTitle != null) {
            Bundle args = new Bundle();
            args.putString("movieTitle", mMovieTitle);
            getSupportLoaderManager().initLoader(LOADER_ID, args, this);
        }
    }

    @Override
    public Loader<searchService.ResultWithDetail> onCreateLoader(int id, Bundle args) {
        return new RetrofitLoader(MainActivity.this, args.getString("movieTitle"));
    }

    @Override
    public void onLoadFinished(Loader<searchService.ResultWithDetail> loader, searchService.ResultWithDetail resultWithDetail) {
        mProgressBar.setVisibility(View.GONE);
        mMovieListRecyclerView.setVisibility(View.VISIBLE);
        if (resultWithDetail.getResponse().equals("True")) {
            mMovieAdapter.swapData(resultWithDetail.getMovieDetailList());
        } else {
            Snackbar.make(mMovieListRecyclerView,

                    "O título do filme digitado não foi encontrado", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<searchService.ResultWithDetail> loader) {
        mMovieAdapter.swapData(null);
    }

    private void startSearch() {
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            CommonUtils.hideSoftKeyboard(MainActivity.this);
            String movieTitle = mSearchEditText.getText().toString().trim();
            if (!movieTitle.isEmpty()) {
                Bundle args = new Bundle();
                args.putString("movieTitle", movieTitle);
                getSupportLoaderManager().restartLoader(LOADER_ID, args, this);
                mMovieTitle = movieTitle;
                mProgressBar.setVisibility(View.VISIBLE);
                mMovieListRecyclerView.setVisibility(View.GONE);
            } else
                Snackbar.make(mMovieListRecyclerView,
                        "Por favor digite o título do filme",
                        Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(mMovieListRecyclerView,
                    "Rede indisponível! Conecte-se à Internet",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public class MovieRecyclerViewAdapter
            extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

        private List<searchService.Detail> mValues;

        public MovieRecyclerViewAdapter(List<searchService.Detail> items) {
            mValues = items;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_movie, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final searchService.Detail detail = mValues.get(position);
            final String title = detail.Title;
            final String imdbId = detail.imdbID;
            final String director = detail.Director;
            final String year = detail.Year;
            holder.mDirectorView.setText(director);
            holder.mTitleView.setText(title);
            holder.mYearView.setText(year);

            final String imageUrl;
            if (!detail.Poster.equals("N/A")) {
                imageUrl = detail.Poster;
            } else {
                // Imagem padrão se não houver poster disponível
                imageUrl = getResources().getString(R.string.default_poster);
            }
            holder.mThumbImageView.layout(0, 0, 0, 0); // Invalidar a largura para que o deslize não use essa dimensão
            Glide.with(MainActivity.this).load(imageUrl).into(holder.mThumbImageView);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    // Passa o objeto de dados no pacote e preenche a atividade de detalhes.
                    intent.putExtra(DetailActivity.MOVIE_DETAIL, detail);
                    intent.putExtra(DetailActivity.IMAGE_URL, imageUrl);

                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(MainActivity.this,
                                    holder.mThumbImageView, "poster");
                    startActivity(intent, options.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            if (mValues == null) {
                return 0;
            }
            return mValues.size();
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            super.onViewRecycled(holder);
            Glide.clear(holder.mThumbImageView);
        }

        public void swapData(List<searchService.Detail> items) {
            if (items != null) {
                mValues = items;
                notifyDataSetChanged();

            } else {
                mValues = null;
            }
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mTitleView;
            public final TextView mYearView;
            public final TextView mDirectorView;
            public final ImageView mThumbImageView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mTitleView = (TextView) view.findViewById(R.id.movie_title);
                mYearView = (TextView) view.findViewById(R.id.movie_year);
                mThumbImageView = (ImageView) view.findViewById(R.id.thumbnail);
                mDirectorView = (TextView) view.findViewById(R.id.movie_director);
            }

        }
    }

}
