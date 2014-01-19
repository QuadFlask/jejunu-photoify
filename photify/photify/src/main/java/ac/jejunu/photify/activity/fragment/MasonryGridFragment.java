package ac.jejunu.photify.activity.fragment;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ac.jejunu.photify.R;
import ac.jejunu.photify.view.UrlImageView;

@EFragment(R.layout.fragment_masonry)
public class MasonryGridFragment extends Fragment {

	@ViewById(R.id.column_container)
	LinearLayout columnContainer;

	@ViewById(R.id.scrollView)
	ScrollView scrollView;

	@ViewById(R.id.column0)
	LinearLayout column0;

	@ViewById(R.id.column1)
	LinearLayout column1;

	int col0Height, col1Height;

	private ArrayList<ImageView> imageViewArrayList;

	@AfterViews
	void afterViews() {
		try {
			imageViewArrayList = new ArrayList<ImageView>();

			for (int i = 0; i < 32; i++) {
				int height = (int) (80 + Math.random() * 250);
				ImageView sampleImageView = getSampleImageView(height);
				imageViewArrayList.add(sampleImageView);
				add(sampleImageView);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				recycleImages();
//			}
//		}, 3 * 1000, 5 * 1000);
	}

	private void add(ImageView sampleImageView) {
		if (col0Height <= col1Height) {
			column0.addView(sampleImageView);
			col0Height += sampleImageView.getLayoutParams().height;

		} else {
			column1.addView(sampleImageView);
			col1Height += sampleImageView.getLayoutParams().height;
		}
	}

	@Background
	void recycleImages() {
		Rect scrollBounds = new Rect();
		Log.e("MasonryGridFragment", "recycleImages");
		scrollView.getHitRect(scrollBounds);

		for (ImageView imageView : imageViewArrayList) {
			if (imageView.getLocalVisibleRect(scrollBounds)) {
//				imageView.reload();
			} else {
				BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
				if (drawable != null) {
//				    Bitmap bitmap = drawable.getBitmap();
//				    bitmap.recycle();
//				    imageView.setVisibility(View.INVISIBLE);
				}
//				imageView.recycle();
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private ImageView getSampleImageView(int height) throws MalformedURLException {
		UrlImageView imgView = new UrlImageView(this.getActivity());
		imgView.setImageURL(new URL("https://cdn2.iconfinder.com/data/icons/ios-7-icons/50/user_male2-256.png"));

		imgView.setMaxWidth(240);
		imgView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, height));
		return imgView;
	}

}
