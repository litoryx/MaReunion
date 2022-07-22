package com.example.reunion;

import android.widget.DatePicker;

import com.example.reunion.list_activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {

    MainActivity mActivity;
    int ITEMS_COUNT = 3;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withContentDescription("listmymeeting"))
                .check(matches(hasMinimumChildCount(3)));
    }

    @Test
    public void should_addMeeting() {

        onView(ViewMatchers.withContentDescription("listmymeeting")).check(new RecyclerViewItemCountAssertion(ITEMS_COUNT));

        onView(withId(R.id.add_meeting))
                .perform(click());

        onView(withId(R.id.name))
                .perform(ViewActions.replaceText("Rob"));

        onView(withId(R.id.mails))
                .perform(ViewActions.replaceText("fjisvqo.hotmail.com,fnsoqoq.frieqfk.fr"));

        onView(withId(R.id.room))
                .perform(ViewActions.replaceText("Réunion 7"));

        onView(withId(R.id.subject))
                .perform(ViewActions.replaceText("Renvoi"));

        onView(withId(R.id.timeLyt))
                .perform(click());

        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1985, 07, 04));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.create))
                .perform(click());

        onView(ViewMatchers.withContentDescription("listmymeeting")).check(new RecyclerViewItemCountAssertion(ITEMS_COUNT+1));

        onView(ViewMatchers.withContentDescription("nom3")).check(matches(withText("Rob")));

    }

    @Test
    public void VerifDataList_ForMyMeetingWithSuccess() {

        onView(ViewMatchers.withContentDescription("nom0")).check(matches(withText("Jean")));

        onView(ViewMatchers.withContentDescription("room0")).check(matches(withText("Réunion 14")));

        onView(ViewMatchers.withContentDescription("emails0")).check(matches(withText("fuifiz@gmail.com,fpsjfdp@hotmail.bout")));
    }

    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 12
        onView(ViewMatchers.withContentDescription("listmymeeting")).check(new RecyclerViewItemCountAssertion(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withContentDescription("listmymeeting"))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withContentDescription("listmymeeting")).check(new RecyclerViewItemCountAssertion(ITEMS_COUNT - 1));
    }

    @Test
    public void myListMeetingsbyRoom_shouldExist(){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Lieux")).perform(click());

        onView(ViewMatchers.withText("Réunion 8"))
                .inRoot(isDialog())
                .perform(click());

        onView(withId(R.id.item_list_name)).check(matches(withText("Johansson")));
    }

    @Test
    public void myListMeetingsbyDate_shouldExist(){

        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Date")).perform(click());

        onView(isAssignableFrom(DatePicker.class)).perform(setDate(1995, 06, 12));

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.item_list_time)).check(matches(withText("12/06/1995")));

        onView(withId(R.id.item_list_name)).check(matches(withText("Jean")));

    }

}