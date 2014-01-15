package ac.jejunu.photify.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.origamilabs.library.views.StaggeredGridView;

import ac.jejunu.photify.R;
import ac.jejunu.photify.activity.MainActivity;
import ac.jejunu.photify.loader.ImageLoader;
import ac.jejunu.photify.view.ScaleImageView;

public class StaggeredGridViewTestFragment extends android.support.v4.app.Fragment {

	private String urls[] = {
			"http://farm7.staticflickr.com/6101/6853156632_6374976d38_c.jpg",
			"http://farm8.staticflickr.com/7232/6913504132_a0fce67a0e_c.jpg",
			"http://farm5.staticflickr.com/4133/5096108108_df62764fcc_b.jpg",
			"http://farm5.staticflickr.com/4074/4789681330_2e30dfcacb_b.jpg",
			"http://farm9.staticflickr.com/8208/8219397252_a04e2184b2.jpg",
			"http://farm9.staticflickr.com/8483/8218023445_02037c8fda.jpg",
			"http://farm9.staticflickr.com/8335/8144074340_38a4c622ab.jpg",
			"http://farm9.staticflickr.com/8060/8173387478_a117990661.jpg",
			"http://farm9.staticflickr.com/8056/8144042175_28c3564cd3.jpg",
			"http://farm9.staticflickr.com/8183/8088373701_c9281fc202.jpg",
			"http://farm9.staticflickr.com/8185/8081514424_270630b7a5.jpg",
			"http://farm9.staticflickr.com/8462/8005636463_0cb4ea6be2.jpg",
			"http://farm9.staticflickr.com/8306/7987149886_6535bf7055.jpg",
			"http://farm9.staticflickr.com/8444/7947923460_18ffdce3a5.jpg",
			"http://farm9.staticflickr.com/8182/7941954368_3c88ba4a28.jpg",
			"http://farm9.staticflickr.com/8304/7832284992_244762c43d.jpg",
			"http://farm9.staticflickr.com/8163/7709112696_3c7149a90a.jpg",
			"http://farm8.staticflickr.com/7127/7675112872_e92b1dbe35.jpg",
			"http://farm8.staticflickr.com/7111/7429651528_a23ebb0b8c.jpg",
			"http://farm9.staticflickr.com/8288/7525381378_aa2917fa0e.jpg",
			"http://farm6.staticflickr.com/5336/7384863678_5ef87814fe.jpg",
			"http://farm8.staticflickr.com/7102/7179457127_36e1cbaab7.jpg",
			"http://farm8.staticflickr.com/7086/7238812536_1334d78c05.jpg",
			"http://farm8.staticflickr.com/7243/7193236466_33a37765a4.jpg",
			"http://farm8.staticflickr.com/7251/7059629417_e0e96a4c46.jpg",
			"http://farm8.staticflickr.com/7084/6885444694_6272874cfc.jpg"
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sttaggeredgrid_test, container, false);

		StaggeredGridView gridView = (StaggeredGridView) rootView.findViewById(R.id.staggeredGridView1);

		int margin = 5; // getResources().getDimensionPixelSize(R.dimen.margin);

		gridView.setItemMargin(margin); // set the GridView margin

		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well

		StaggeredAdapter adapter = new StaggeredAdapter(getActivity(), R.id.imageView1, urls);

		gridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		return rootView;
	}

	public static class StaggeredAdapter extends ArrayAdapter<String> {

		private ImageLoader mLoader;

		public StaggeredAdapter(Context context, int textViewResourceId, String[] objects) {
			super(context, textViewResourceId, objects);
			mLoader = new ImageLoader(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;

			if (convertView == null) {
				LayoutInflater layoutInflator = LayoutInflater.from(getContext());
				convertView = layoutInflator.inflate(R.layout.row_staggered_demo, null);
				holder = new ViewHolder();
				holder.imageView = (ScaleImageView) convertView.findViewById(R.id.imageView1);
				convertView.setTag(holder);
			}

			holder = (ViewHolder) convertView.getTag();

			mLoader.DisplayImage(getItem(position), holder.imageView);

			return convertView;
		}

		static class ViewHolder {
			ScaleImageView imageView;
		}
	}
}
