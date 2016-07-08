package zjicm.xmt130706121.bms.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import zjicm.xmt130706121.bms.R;
import zjicm.xmt130706121.bms.base.BaseHolder;
import zjicm.xmt130706121.bms.base.BaseRequest;
import zjicm.xmt130706121.bms.entity.Record4Admin;
import zjicm.xmt130706121.bms.request.ReturnBookRequest;
import zjicm.xmt130706121.bms.request.ViewBorrowRequest;
import zjicm.xmt130706121.bms.ui.activity.BorrowAty;

/**
 * Created by Relish on 2016/7/7.
 */
public class BorrowsFgm extends zjicm.xmt130706121.bms.base.BaseFgm {


    @Bind(R.id.srl)
    SwipeRefreshLayout srl;

    @Bind(R.id.lvBorrows)
    ListView lvBorrows;
    RecordAdapter adapter;
    ArrayList<Record4Admin> records;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_borrows;
    }

    @SuppressWarnings("all")
    @Override
    protected void initView() {
        records = new ArrayList<>();
        adapter = new RecordAdapter();
        lvBorrows.setAdapter(adapter);
        requestData();
        srl.setOnRefreshListener(() -> requestData());
    }

    public void requestData() {
        ViewBorrowRequest request = new ViewBorrowRequest();
        request.setOnResponseListener(new BaseRequest.OnResponseListener<ArrayList<Record4Admin>>() {
            @Override
            public void onSuccess(ArrayList<Record4Admin> record4Admins) {
                showLoading(false);if (srl.isRefreshing()) {
                    srl.setRefreshing(false);
                }
                records = new ArrayList<>();
                records.addAll(record4Admins);
                adapter.notifyDataSetChanged();
                lvBorrows.invalidate();
            }

            @Override
            public void onFail(String message) {
                showLoading(false);if (srl.isRefreshing()) {
                    srl.setRefreshing(false);
                }
                showMessage("暂无数据");
            }
        });
        request.request();
        showLoading(true);
    }

    @SuppressWarnings("unused")
    @OnItemClick(R.id.lvBorrows)
    public void checkBookInfo(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("record", records.get(position));
        goActivityForResult(BorrowAty.class, 0x33, bundle);
    }

    @SuppressWarnings("unused")
    @OnItemLongClick(R.id.lvBorrows)
    public boolean deleteBook(int position) {
        Record4Admin r = records.get(position);
        int recordId = r.getId();
        new AlertDialog.Builder(getActivity())
                .setTitle("还书")
                .setMessage("确认已还书")
                .setPositiveButton("确认", (DialogInterface dialog, int which) -> {
                    ReturnBookRequest request = new ReturnBookRequest(recordId);
                    request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            if (srl.isRefreshing()) {
                                srl.setRefreshing(false);
                            }
                            requestData();
                            showMessage("还书完成");
                        }

                        @Override
                        public void onFail(String message) {
                            showMessage(message);
                            if (srl.isRefreshing()) {
                                srl.setRefreshing(false);
                            }
                        }
                    });
                    request.request();
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
        return true;
    }

    public void updateRecord(int p) {
        records.remove(p);
        adapter.notifyDataSetChanged();
        lvBorrows.invalidate();
    }

    class RecordAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return records.size();
        }

        @Override
        public Object getItem(int position) {
            return records.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.rv_item_record, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Record4Admin r = records.get(position);
            holder.tvBookName.setText(r.getBook().getName());
            holder.tvInventory.setText(String.valueOf(r.getBook().getInventory()));
            holder.tvUsername.setText(r.getUser().getName());
            holder.tvBorrowTime.setText(r.getBorrowTime());
            return convertView;
        }

        class ViewHolder extends BaseHolder {

            @Bind(R.id.tvBookName)
            TextView tvBookName;
            @Bind(R.id.tvInventory)
            TextView tvInventory;
            @Bind(R.id.tvUsername)
            TextView tvUsername;
            @Bind(R.id.tvBorrowTime)
            TextView tvBorrowTime;


            public ViewHolder(View view) {
                super(view);
            }
        }
    }

}
