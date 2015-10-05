package com.example.android.actionbar2;

import java.util.Locale;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener, ActionBar.OnNavigationListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private ActionBar mActionBar;
	private static final String TAG = "ActionBar2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						mActionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			mActionBar.addTab(mActionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}

		String[] listData = { "Item1", "Item2", "Item3", "Item4" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActionBarThemedContextCompat(),
				android.R.layout.simple_dropdown_item_1line, listData);
		mActionBar.setListNavigationCallbacks(adapter, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
		Log.d(TAG, "onTabSelected:" + tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		Log.d(TAG, "onTabUnselected:" + tab.getPosition());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		Log.d(TAG, "onTabReselected:" + tab.getPosition());
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Toast.makeText(this, "Position " + itemPosition +  " is selected", Toast.LENGTH_SHORT).show();
		return false;
	}

	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	public void onClick(View view) {
		int count = mSectionsPagerAdapter.getCount();
		int id = view.getId();
		Button b = (Button) view;
		if (id == R.id.buttonAddTab) {
			mActionBar.addTab(mActionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(count))
					.setTabListener(this));
			mSectionsPagerAdapter.setCount(count + 1);
		} else if (id == R.id.buttonRemoveTab) {
			if (count > 3) {
				mActionBar.removeTabAt(count - 1);
				mSectionsPagerAdapter.setCount(count - 1);
			}
		} else if (id == R.id.buttonSelectTab) {
			ActionBar.Tab tab = mActionBar.getTabAt(count - 1);
			if (System.currentTimeMillis() % 2 == 0) {
				tab.select();
			} else {
				mActionBar.selectTab(tab);
			}
		} else if (id == R.id.buttonShowIcon) {
			if (mActionBar.getNavigationMode() == ActionBar.NAVIGATION_MODE_TABS) {
				ActionBar.Tab tab = mActionBar.getSelectedTab();
				Drawable d = tab.getIcon();
				if (d == null) {
					tab.setIcon(R.drawable.ic_launcher);
					b.setText(R.string.label_clear_icon);
				} else {
					tab.setIcon(null);
					b.setText(R.string.label_set_icon);
				}
			}
		} else if (id == R.id.buttonSwitchMode) {
			if (mActionBar.getNavigationMode() == ActionBar.NAVIGATION_MODE_LIST) {
				mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				b.setText(R.string.label_mode_list);
			} else {
				mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
				b.setText(R.string.label_mode_tabs);
			}
		} else if (id == R.id.buttonSwitchView) {
			int options = mActionBar.getDisplayOptions();
			if ((options & ActionBar.DISPLAY_SHOW_CUSTOM) == ActionBar.DISPLAY_SHOW_CUSTOM) {
				mActionBar.setDisplayOptions(options ^ ActionBar.DISPLAY_SHOW_CUSTOM);
			} else {
				mActionBar.setDisplayOptions(options | ActionBar.DISPLAY_SHOW_CUSTOM);
				if (mActionBar.getCustomView() == null) {
					mActionBar.setCustomView(R.layout.custom_view);
					ViewGroup group = (ViewGroup) mActionBar.getCustomView();
					group.findViewById(R.id.buttonOnCustomView).setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							Toast.makeText(MainActivity.this, "Hello!", Toast.LENGTH_SHORT).show();
						}
					});
				}
			}
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private int mCount;
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			mCount = 3;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return mCount;
		}

		public void setCount(int count) {
			mCount = count;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			return getString(R.string.title_section).toUpperCase(l) + (position + 1);
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView;
			int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
			if (sectionNumber == 1) {
				rootView = inflater.inflate(R.layout.fragment_section1,
						container, false);
			} else {
				rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
				TextView dummyTextView = (TextView) rootView
						.findViewById(R.id.section_label);
				dummyTextView.setText(Integer.toString(sectionNumber));
			}
			return rootView;
		}
	}

}
