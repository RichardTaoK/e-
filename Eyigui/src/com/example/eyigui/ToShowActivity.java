package com.example.eyigui;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.example.eyigui.bean.Clothes;
import com.example.eyigui.bean.Info;
import com.example.eyigui.implementDemo.Xunzhao;
import com.example.eyigui.util.JsonUtil;
import com.example.eyigui.util.WriteJson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ToShowActivity extends Activity {
	private ListView listView;
	private EditText editText;
	private Button button;
	private Handler handler=new Handler(){
		
		@Override
		public void handleMessage(Message msg) {
			//这里得到返回来的数据在一面上显示
			if(msg.obj!=null){
				List<Info> clothes =(List<Info>) msg.obj;
				MyAdapter myAdapter =new MyAdapter(clothes, ToShowActivity.this);
				listView.setAdapter(myAdapter);
			}else{
				//这里就找不到合适的衣服，给用户一个提示
				
			}
			
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_show);
		init();
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String string =editText.getText().toString().trim();
				System.out.println(string);
				new Thread(){
					@Override
					public void run() {
						String string2 = new Xunzhao().getString(string);
						Message message=handler.obtainMessage();
						List<Info> list = (List<Info>) new JsonUtil().StringFromJson1(string2);
						message.obj=list;
						handler.sendMessage(message);
					}
					}.start();
				}
		});
	}

/*	private void action() {
		// TODO Auto-generated method stub
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String string =editText.getText().toString();
				new Thread(){
					public void run() {
						String string2 = new Xunzhao().getString(string);
						Message message=handler.obtainMessage();
						if (string2.equals("")||string2.equals(null)) {
							//说明没有拿到数据了
							message.arg1=-1;
							
						}else{
							List<Clothes> list = (List<Clothes>) new JsonUtil().StringFromJson(string2);
							message.obj=list;
							
						}
						handler.sendMessage(message);
					};
				}.start();
				String string = editText.getText().toString();
				// 得到查询的字符后回传给服务器端
				Mythread mythread = new Mythread(string);
				// 创建单一线程的线程池
				ExecutorService threadExecutor = Executors
						.newSingleThreadExecutor();
				Future<String> future = threadExecutor.submit(mythread);
				String result = null;
				try {
					// 获得从服务器端获得json数据后，在进行解析
					result = future.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (result == null || result.equals("")
						|| result.trim().length() == 0) {
					// 没有找到符合数据
				} else {
					List<Clothes> list = (List<Clothes>) new JsonUtil()
							.StringFromJson(result);
					// 这里就的到需要的数据
					MyAdapter adapter = new MyAdapter(list,ToShowActivity.this);
					listView.setAdapter(adapter);
				}
			}
				}
		});
	}*/

	private void init() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.lvxunzhao);
		editText = (EditText) findViewById(R.id.etxunzhao);
		button = (Button) findViewById(R.id.btxunzhao);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_show, menu);
		return true;
	}

	// 继承baseadapter实现对数据操作
	class MyAdapter extends BaseAdapter {
		List<Info> info;
		ToShowActivity context;
		Handler handler2 =null;
		Im im=null; 
		public MyAdapter() {
		}

		public MyAdapter(List<Info> info, ToShowActivity context) {
			this.info = info;
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return info.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return info.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			 ViewHolder holder = null;
			if (arg1 == null) {
				arg1 = View.inflate(context, R.layout.listitem, null);
				holder = new ViewHolder();
				holder.ivClothes = (ImageView) arg1
						.findViewById(R.id.ivClothes);
				holder.tvColor = (TextView) arg1.findViewById(R.id.tvColor);
				holder.tvType = (TextView) arg1.findViewById(R.id.tvType);
				holder.tvSeason = (TextView) arg1.findViewById(R.id.tvSeason);
				arg1.setTag(holder);
			} else {
				holder = (ViewHolder) arg1.getTag();
			}
			
			final Info infos=info.get(arg0);
		//这里需要下载图片然后才能显示出来呢
			if(!infos.getClothesurl().equals("")||!(infos.getClothesurl()==null)){
			
				//这里用异步加载
				new Thread(){
					@Override
					public void run() {
						Message message=Message.obtain();
						URL url=null;
						URLConnection openConnection=null;
						try {
							url = new URL(infos.getClothesurl());
							openConnection = url.openConnection();
							Bitmap bitmap = BitmapFactory.decodeStream(openConnection.getInputStream());
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}if(url!=null&&openConnection!=null){
						message.obj=openConnection;
						handler2.sendMessage(message);}
					};
					
				}.start();
				
				//图片加载在listview上
//				holder.ivClothes.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
			 }else {
				holder.ivClothes.setImageResource(R.drawable.logo);
			}
			handler2=new Handler(){
				@Override
				public void handleMessage(Message msg) {
					Bitmap decodeStream = BitmapFactory.decodeStream((InputStream) msg.obj);
					im=new Im(decodeStream);
				}
			};
			holder.ivClothes.setImageBitmap(im.bitmap);
			holder.tvColor.setText(infos.getClothescolor());
			holder.tvType.setText(infos.getClothestype());
			holder.tvSeason.setText(infos.getClothesseason());
			return arg1;
		}
	 class Im {
			private Bitmap bitmap;
			public Im(Bitmap bitmap){
				this.bitmap=bitmap;
			}
		}
	}

 class ViewHolder {
		ImageView ivClothes;
		TextView tvColor;
		TextView tvType;
		TextView tvSeason;
	}

}
