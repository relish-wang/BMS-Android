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
import zjicm.xmt130706121.bms.entity.Book;
import zjicm.xmt130706121.bms.request.DeleteBookRequest;
import zjicm.xmt130706121.bms.request.ViewBooksRequest;
import zjicm.xmt130706121.bms.ui.activity.BookAty;

/**
 * Created by Relish on 2016/7/7.
 */
public class BooksFgm extends zjicm.xmt130706121.bms.base.BaseFgm {

    @Bind(R.id.srl)
    SwipeRefreshLayout srl;

    @Bind(R.id.lvBooks)
    ListView lvBooks;
    BookAdapter adapter;
    ArrayList<Book> books;

    @Override
    protected int layoutResId() {
        return R.layout.fgm_books;
    }

    @SuppressWarnings("all")
    @Override
    protected void initView() {
        books = new ArrayList<>();
        adapter = new BookAdapter();
        lvBooks.setAdapter(adapter);
        srl.setOnRefreshListener(() -> requestData());
        requestData();
    }

    public void requestData() {
        ViewBooksRequest request = new ViewBooksRequest();
        request.setOnResponseListener(new BaseRequest.OnResponseListener<ArrayList<Book>>() {
            @Override
            public void onSuccess(ArrayList<Book> bookList) {
                books = new ArrayList<>();
                books.addAll(bookList);
                adapter.notifyDataSetChanged();
                lvBooks.invalidate();
                if (srl.isRefreshing()) {
                    srl.setRefreshing(false);
                }
            }

            @Override
            public void onFail(String message) {
                showLoading(false);
                showMessage("暂无数据");
                if (srl.isRefreshing()) {
                    srl.setRefreshing(false);
                }
            }
        });
        request.request();
    }

    @SuppressWarnings("unused")
    @OnItemClick(R.id.lvBooks)
    public void checkBookInfo(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("book", books.get(position));
        bundle.putInt("position", position);
        goActivityForResult(BookAty.class, 0x22, bundle);
    }

    @SuppressWarnings("unused")
    @OnItemLongClick(R.id.lvBooks)
    public boolean deleteBook(int position) {
        Book b = books.get(position);
        int bookId = b.getId();
        new AlertDialog.Builder(getActivity())
                .setTitle("删除[" + b.getName() + "]")
                .setMessage("是否删除此书籍")
                .setPositiveButton("删除", (DialogInterface dialog, int which) -> {
                    DeleteBookRequest request = new DeleteBookRequest(bookId);
                    request.setOnResponseListener(new BaseRequest.OnResponseListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            if (srl.isRefreshing()) {
                                srl.setRefreshing(false);
                            }
                            requestData();
                            showMessage("[" + b.getName() + "]已被删除。");
                        }

                        @Override
                        public void onFail(String message) {
                            if (srl.isRefreshing()) {
                                srl.setRefreshing(false);
                            }
                            showMessage(message);

                        }
                    });
                    request.request();
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
        return true;
    }

    public void updateBook(int position, Book b) {
        books.get(position).setName(b.getName());
        books.get(position).setInventory(b.getInventory());
        adapter.notifyDataSetChanged();
        lvBooks.invalidate();
    }

    class BookAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return books.size();
        }

        @Override
        public Object getItem(int position) {
            return books.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.rv_item_book, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Book b = books.get(position);
            holder.tvBookName.setText(b.getName());
            holder.tvInventory.setText(String.valueOf(b.getInventory()));
            return convertView;
        }

        class ViewHolder extends BaseHolder {

            @Bind(R.id.tvName)
            TextView tvBookName;
            @Bind(R.id.tvInventory)
            TextView tvInventory;


            public ViewHolder(View view) {
                super(view);
            }
        }
    }

}
