package com.hh.oil.widget.guidPages.tutorial;

import java.util.ArrayList;
import java.util.List;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;

import com.hh.oil.widget.guidPages.MaterialTutorialFragment;
import com.hh.oil.widget.guidPages.TutorialItem;

/**
 * @author rebeccafranks
 * @since 15/11/09.
 */
public class MaterialTutorialPresenter implements MaterialTutorialContract.UserActionsListener {
	private final Context context;
	private MaterialTutorialContract.View tutorialView;
	private List<MaterialTutorialFragment> fragments;
	private List<TutorialItem> tutorialItems;

	Window window;

	public MaterialTutorialPresenter(Activity context, MaterialTutorialContract.View tutorialView) {
		this.tutorialView = tutorialView;
		this.context = context;

		window = context.getWindow();
	}

	@Override
	public void loadViewPagerFragments(List<TutorialItem> tutorialItems) {
		fragments = new ArrayList<MaterialTutorialFragment>();
		this.tutorialItems = tutorialItems;
		for (int i = 0; i < tutorialItems.size(); i++) {
			MaterialTutorialFragment helpTutorialImageFragment;
			helpTutorialImageFragment = MaterialTutorialFragment.newInstance(tutorialItems.get(i), i);
			fragments.add(helpTutorialImageFragment);
		}
		tutorialView.setViewPagerFragments(fragments);
	}

	@Override
	public void doneOrSkipClick() {
		tutorialView.showEndTutorial();
	}

	@Override
	public void nextClick() {
		tutorialView.showNextTutorial();
	}

	@Override
	public void onPageSelected(int pageNo) {
		if (pageNo >= fragments.size() - 1) {
			tutorialView.showDoneButton();
		} else {
			tutorialView.showSkipButton();
		}
	}

	@Override
	public void transformPage(View page, float position) {
		int pagePosition = (int) Integer.parseInt(page.getTag() + "");
		if (position <= -1.0f || position >= 1.0f) {

		} else if (position == 0.0f) {
			tutorialView.setBackgroundColor(ContextCompat.getColor(context, tutorialItems.get(pagePosition)
					.getBackgroundColor()));
		} else {
			fadeNewColorIn(pagePosition, position);
		}
	}

	@Override
	public int getNumberOfTutorials() {
		if (tutorialItems != null) {
			return tutorialItems.size();
		}
		return 0;
	}

	@SuppressLint("NewApi")
	private void fadeNewColorIn(int index, float multiplier) {
		if (multiplier < 0) {

			int colorStart = ContextCompat.getColor(context, tutorialItems.get(index).getBackgroundColor());
			if (index + 1 == fragments.size()) {
				tutorialView.setBackgroundColor(colorStart);
				return;
			}
			int colorEnd = ContextCompat.getColor(context, tutorialItems.get(index + 1).getBackgroundColor());
			int colorToSet = Integer.parseInt(new ArgbEvaluator().evaluate(Math.abs(multiplier), colorStart, colorEnd)
					+ "");
			tutorialView.setBackgroundColor(colorToSet);

			if (android.os.Build.VERSION.SDK_INT >= 21) {
				window.setStatusBarColor(colorToSet);
				window.setNavigationBarColor(colorToSet);

			}
		}

	}

}
