package com.rrtutors.swiperefreshlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.rrtutors.swiperefreshlayout.apis.ApiCall;
import com.rrtutors.swiperefreshlayout.apis.NewsResult;
import com.rrtutors.swiperefreshlayout.apis.Repository;

public class RecyclerActivity extends AppCompatActivity {

    SwipeRefreshLayout swiperefresh;
    RecyclerView recyclerview;
    NewsItemAdapter newsItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        swiperefresh=findViewById(R.id.swiperefresh);
        recyclerview=findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        newsItemAdapter=new NewsItemAdapter();
        recyclerview.setAdapter(newsItemAdapter);


        swiperefresh.setColorSchemeColors(new int[]{R.color.green,R.color.purple});
        swiperefresh.setDistanceToTriggerSync(50);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(true);
                callRequest();
            }
        });

        callRequest();

    }
    private void callRequest()
    {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ApiCall call= Repository.getRetrofit().create(ApiCall.class);
                Call<NewsResult> result= call.fetchNews();
                result.enqueue(new Callback<NewsResult>() {
                    @Override
                    public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                        swiperefresh.setRefreshing(false);
                        Log.v("Response","Response "+response);
                        if(response.isSuccessful())
                        {
                            if(response.body().getStatus().equals("ok"))
                            {
                                newsItemAdapter.addNews(response.body().getSources());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResult> call, Throwable t) {
                        swiperefresh.setRefreshing(false);
                        Log.v("Response","Response Error  "+t.getMessage());
                    }
                });
            }
        });
    }
}
