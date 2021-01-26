package com.appcognito.retrofit2mvvm_covid19.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appcognito.retrofit2mvvm_covid19.R;
import com.appcognito.retrofit2mvvm_covid19.api.ApiListener;
import com.appcognito.retrofit2mvvm_covid19.api.Covid19;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFragment extends Fragment {

    private MainViewModel viewModel;
    private com.appcognito.retrofit2mvvm_covid19.databinding.MainFragmentBinding binding;

    private boolean loading = false;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int serverPage = 0;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        binding.recycleSidoInfo.setHasFixedSize(true);
        binding.recycleSidoInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycleSidoInfo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount = llm.getChildCount();
                    totalItemCount = llm.getItemCount();
                    pastVisiblesItems = llm.findFirstVisibleItemPosition();
                    int pos = llm.findFirstCompletelyVisibleItemPosition();
                    Log.d("ScrollDown", ""+pos+","+totalItemCount+":"+visibleItemCount+","+pastVisiblesItems);

                    if (!loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = true;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            serverPage++;
                            getCorvidSidoInfo();
                        }
                    }

                } else {
                    LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    visibleItemCount = llm.getChildCount();
                    totalItemCount = llm.getItemCount();
                    pastVisiblesItems = llm.findFirstVisibleItemPosition();
                    int pos = llm.findFirstCompletelyVisibleItemPosition();
                    Log.d("ScrollUp", ""+pos+","+totalItemCount+":"+visibleItemCount+","+pastVisiblesItems);
                    if (pos <= 0) {
                        binding.refreshLayout.setEnabled(true);
                    } else {
                        binding.refreshLayout.setEnabled(false);
                        binding.refreshLayout.setRefreshing(false);
                    }
                }

                binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        serverPage = 0;
                        getCorvidSidoInfo();
                    }
                });
            }
        });

        getCorvidSidoInfo();
        return binding.getRoot();
    }

    private AdapterSidoInfo adapter;
    private void loadCorvidSidoInfo() {

        adapter = new AdapterSidoInfo(viewModel.sidoItems.getValue(), getActivity());
        binding.recycleSidoInfo.setAdapter(adapter);

    }

    private void getCorvidSidoInfo() {
        Covid19 covid19 = new Covid19();
        String dt = new SimpleDateFormat("yyyyMMdd").format(new Date());
        covid19.getSidoInfo(dt, dt, viewModel, new ApiListener() {
            @Override
            public void onSuccess() {
                loadCorvidSidoInfo();
                if (serverPage == 0)
                    binding.refreshLayout.setRefreshing(false);
            }

            @Override
            public void onFail(String errMsg) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}