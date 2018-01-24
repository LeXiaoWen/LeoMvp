package com.leo.leomvp.test.qmui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.leo.leomvp.R;
import com.leo.mvp.util.toast.ToastUtils;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QMUIActivity extends AppCompatActivity {

    @BindView(R.id.topbar)
    QMUITopBar mTopbar;
    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.emptyView)
    QMUIEmptyView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qmui);
        ButterKnife.bind(this);
        initList();
        mTopbar.setTitle("Dialog Activity");
        mTopbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("点击了返回！");
            }
        });

    }

    private void initList() {
        String[] listItems = new String[]{
                "消息类型对话框（蓝色按钮）",
                "消息类型对话框（红色按钮）",
                "菜单类型对话框",
                "带 checkBox 的消息确认框",
                "单选菜单类型对话框",
                "多选菜单类型对话框",
                "多选菜单类型对话框（item 数量很多）",
                "带输入框的对话框",
                "高度适应键盘升降的对话框",
                "Loading"
        };

        final ArrayList<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);

        mListview.setAdapter(new ArrayAdapter<>(this, R.layout.simple_list_item, data));

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showMessagePositiveDialog();
                        break;
                    case 1:
                        showMessageNegativeDialog();
                        break;
                    case 2:
                        showMenuDialog();
                        break;
                    case 3:
                        showConfirmMessageDialog();
                        break;
                    case 4:
                        showSingleChoiceDialog();
                        break;
                    case 5:
                        showMultiChoiceDialog();
                        break;
                    case 6:
                        showNumberousMultiChoiceDialog();
                        break;
                    case 7:
                        showEditTextDialog();
                        break;
                    case 8:
                        showAutoDialog();
                        break;
                    case 9:
                        showLoading();
                        break;

                }
            }
        });

    }

    private void showLoading() {
        final QMUITipDialog dialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("正在加载")
                .create();
        dialog.show();
        mListview.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },1500);
    }

    private void showAutoDialog() {
    }

    private void showEditTextDialog() {

    }

    private void showNumberousMultiChoiceDialog() {
        final String[] items = new String[]{
                "选项1", "选项2", "选项3", "选项4", "选项5", "选项6",
                "选项7", "选项8", "选项9", "选项10", "选项11", "选项12",
                "选项13", "选项14", "选项15", "选项16", "选项17", "选项18"
        };
        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(this)
                .setCheckedItems(new int[]{1, 3})
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("提交", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                String result = "你选择了 ";
                for (int i = 0; i < builder.getCheckedItemIndexes().length; i++) {
                    result += "" + builder.getCheckedItemIndexes()[i] + "; ";
                }
//                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                ToastUtils.showShortToast(result);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showMultiChoiceDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3", "选项4", "选项5", "选项6"};
        final QMUIDialog.MultiCheckableDialogBuilder builder = new QMUIDialog.MultiCheckableDialogBuilder(this)
                .setCheckedItems(new int[]{1, 3})
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("提交", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                String result = "你选择了 ";
                for (int i = 0; i < builder.getCheckedItemIndexes().length; i++) {
                    result += "" + builder.getCheckedItemIndexes()[i] + "; ";
                }
                ToastUtils.showShortToast(result);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showSingleChoiceDialog() {
        final String[] items = new String[]{"选项1", "选项2", "选项3"};
        final int checkedIndex = 0;
        new QMUIDialog.CheckableDialogBuilder(this)
                .setCheckedIndex(checkedIndex)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShortToast("你选择了 " + items[which]);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showConfirmMessageDialog() {
        QMUIDialog.CheckBoxMessageDialogBuilder builder = new QMUIDialog.CheckBoxMessageDialogBuilder(this)
                .setTitle("退出后是否删除账号信息")
                .setMessage("删除账号信息");
        builder.setChecked(true);
        builder.addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        builder.addAction("退出", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        });
        boolean checked = builder.isChecked();
        builder.show();
    }

    private void showMenuDialog() {
        final String[] items = new String[]{
                "选项1", "选项2", "选项3", "选项4", "选项5", "选项6", "选项7", "选项8", "选项9"
                , "选项10", "选项11", "选项12"
        };
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShortToast(items[which]);
                        dialog.dismiss();
                    }
                }).show();
    }

    private void showMessageNegativeDialog() {
        new QMUIDialog
                .MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("确定要删除吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction(0, "删除", QMUIDialogAction.ACTION_PROP_NEGATIVE, new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        ToastUtils.showShortToast("删除成功!");
                        dialog.dismiss();
                    }
                }).show();
    }

    private void showMessagePositiveDialog() {
        new QMUIDialog
                .MessageDialogBuilder(this)
                .setTitle("标题")
                .setMessage("确定要发送吗？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确认", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        ToastUtils.showShortToast("发送成功！");
                    }
                }).show();
    }
}
