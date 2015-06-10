package com.xjp.androidmddemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xjp.androidmddemo.R;
import com.xjp.androidmddemo.activity.adapter.RecyclerAdapter;
import com.xjp.androidmddemo.activity.model.ModelBean;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    protected Toolbar toolbar;
    protected TextView title;
    protected Spinner spinner;
    protected Button add;

    private RecyclerView recylerView;
    private List<ModelBean> beanList;
    private RecyclerAdapter adapter;
    private String des[] = {"云层里的阳光", "好美的海滩", "好美的海滩", "夕阳西下的美景", "夕阳西下的美景"
            , "夕阳西下的美景", "夕阳西下的美景", "夕阳西下的美景", "好美的海滩"};

    private int resId[] = {R.drawable.img1, R.drawable.img2, R.drawable.img2, R.drawable.img3,
            R.drawable.img4, R.drawable.img5, R.drawable.img3, R.drawable.img1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initSpinner();
    }


    private void initSpinner() {
        String categorys[] = this.getResources().getStringArray(R.array.categorys);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorys);

        // 为adapter设置下拉菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner设置adapter
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    private void initViews() {

        toolbar = findView(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
            title = findView(R.id.toolbar_title);
            spinner = findView(R.id.toolbar_category);
            add = findView(R.id.button_add);
            if (null != title) {
                title.setText(getTitle());
            }
        }

        add.setOnClickListener(this);

        recylerView = findView(R.id.recyclerview);
        //设置布局显示方式
        recylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, true));
        //设置添加删除item时候的动画
        recylerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ModelBean bean = new ModelBean();
            bean.setResId(resId[i]);
            bean.setTitle(des[i]);
            beanList.add(bean);
        }
        adapter = new RecyclerAdapter(this, beanList);
        recylerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object object) {
                Toast.makeText(MainActivity.this, ((ModelBean) object).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                recylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, true));
                break;
            case 1:
                recylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, true));
                break;
            case 2:
                recylerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case 3:
                recylerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayout.HORIZONTAL, true));
                break;
            case 4:
                recylerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        ModelBean bean = new ModelBean();
        bean.setTitle("这是新添加的");
        bean.setResId(R.drawable.img5);
        beanList.add(0, bean);
        adapter.notifyDataSetChanged();//更新全部数据
        adapter.notifyItemInserted(0);//在
        adapter.notifyItemRemoved(0);
        adapter.notifyItemChanged(0);
        adapter.notifyItemMoved(0,1);
        adapter.notifyItemRangeChanged(0,2);
        adapter.notifyItemRangeInserted(0,2);
        adapter.notifyItemRangeRemoved(0,2);

    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
