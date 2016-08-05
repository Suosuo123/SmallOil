package com.hh.oil.activities;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hh.oil.R;
import com.hh.oil.widget.guidPages.MaterialTutorialFragment;
import com.hh.oil.widget.guidPages.TutorialItem;
import com.hh.oil.widget.guidPages.adapter.MaterialTutorialAdapter;
import com.hh.oil.widget.guidPages.tutorial.MaterialTutorialContract;
import com.hh.oil.widget.guidPages.tutorial.MaterialTutorialPresenter;
import com.hh.oil.widget.guidPages.view.CirclePageIndicator;
import com.lidroid.xutils.view.annotation.ContentView;

@SuppressLint({ "InlinedApi", "ResourceAsColor", "NewApi" })
@ContentView(R.layout.activity_help_tutorial)
public class GuideActivity extends BaseActivity implements MaterialTutorialContract.View {

	public static final String MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS = "tutorial_items";
	private ViewPager mHelpTutorialViewPager;
	private View mRootView;
	private TextView mTextViewSkip;
	private ImageButton mNextButton;
	private Button mDoneButton;
	private MaterialTutorialPresenter materialTutorialPresenter;

	private View.OnClickListener finishTutorialClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			materialTutorialPresenter.doneOrSkipClick();

		}
	};

	@Override
	protected void initView() {
		super.initView();

		mRootView = findViewById(R.id.activity_help_root);
		mHelpTutorialViewPager = (ViewPager) findViewById(R.id.activity_help_view_pager);
		mTextViewSkip = (TextView) findViewById(R.id.activity_help_skip_textview);
		mNextButton = (ImageButton) findViewById(R.id.activity_next_button);
		mDoneButton = (Button) findViewById(R.id.activity_tutorial_done);

		mTextViewSkip.setOnClickListener(finishTutorialClickListener);
		mDoneButton.setOnClickListener(finishTutorialClickListener);

		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				materialTutorialPresenter.nextClick();

			}
		});
	}

	@Override
	protected void initData() {
		super.initData();
		materialTutorialPresenter = new MaterialTutorialPresenter(this, this);

		// List<TutorialItem> tutorialItems = getIntent()
		// .getParcelableArrayListExtra(MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS);
		materialTutorialPresenter.loadViewPagerFragments(getTutorialItems());

		Window window = getWindow();
		if (android.os.Build.VERSION.SDK_INT >= 21) {

			window.setStatusBarColor(getResources().getColor(R.color.slide_1));
			window.setNavigationBarColor(getResources().getColor(R.color.slide_1));

		}
	}

	@Override
	public void showNextTutorial() {
		int currentItem = mHelpTutorialViewPager.getCurrentItem();
		if (currentItem < materialTutorialPresenter.getNumberOfTutorials()) {
			mHelpTutorialViewPager.setCurrentItem(mHelpTutorialViewPager.getCurrentItem() + 1);
		}
	}

	@Override
	public void showEndTutorial() {
		// setResult(RESULT_OK);
		finish();
		startActivity(new Intent(GuideActivity.this, MainActivity.class));
	}

	@Override
	public void setBackgroundColor(int color) {
		mRootView.setBackgroundColor(color);
	}

	@Override
	public void showDoneButton() {
		mTextViewSkip.setVisibility(View.INVISIBLE);
		mNextButton.setVisibility(View.GONE);
		mDoneButton.setVisibility(View.VISIBLE);
	}

	@Override
	public void showSkipButton() {
		mTextViewSkip.setVisibility(View.VISIBLE);
		mNextButton.setVisibility(View.VISIBLE);
		mDoneButton.setVisibility(View.GONE);
	}

	@Override
	public void setViewPagerFragments(List<MaterialTutorialFragment> materialTutorialFragments) {
		mHelpTutorialViewPager.setAdapter(new MaterialTutorialAdapter(getSupportFragmentManager(),
				materialTutorialFragments));
		CirclePageIndicator mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.activity_help_view_page_indicator);

		mCirclePageIndicator.setViewPager(mHelpTutorialViewPager);
		mCirclePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int i) {
				materialTutorialPresenter.onPageSelected(mHelpTutorialViewPager.getCurrentItem());

			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});
		mHelpTutorialViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
			@Override
			public void transformPage(View page, float position) {
				materialTutorialPresenter.transformPage(page, position);
			}
		}

		);
	}

	private ArrayList<TutorialItem> getTutorialItems() {
		TutorialItem tutorialItem1 = new TutorialItem(getString(R.string.slide_1_african_story_books),
				getString(R.string.slide_1_african_story_books_subtitle), R.color.slide_1, R.drawable.tut_page_1_front,
				R.drawable.tut_page_1_background);

		TutorialItem tutorialItem2 = new TutorialItem(getString(R.string.slide_2_volunteer_professionals),
				getString(R.string.slide_2_volunteer_professionals_subtitle), R.color.slide_2,
				R.drawable.tut_page_2_front, R.drawable.tut_page_2_background);

		TutorialItem tutorialItem3 = new TutorialItem(getString(R.string.slide_3_download_and_go), null,
				R.color.slide_3, R.drawable.tut_page_3_foreground);

		TutorialItem tutorialItem4 = new TutorialItem(getString(R.string.slide_4_different_languages),
				getString(R.string.slide_4_different_languages_subtitle), R.color.slide_4,
				R.drawable.tut_page_4_foreground, R.drawable.tut_page_4_background);

		ArrayList<TutorialItem> tutorialItems = new ArrayList<TutorialItem>();
		tutorialItems.add(tutorialItem1);
		tutorialItems.add(tutorialItem2);
		tutorialItems.add(tutorialItem3);
		tutorialItems.add(tutorialItem4);

		return tutorialItems;
	}
}
