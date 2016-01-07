package gradle.udacity.com.jokeractivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokerActivityFragment extends Fragment {

    private static final String EXTRA_JOKE = "com.udacity.gradle.builditbigger.extra_joke";
    public JokerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_joker, container, false);

        Intent intent = getActivity().getIntent();

        if(intent != null) {

            String strJoke = intent.getStringExtra(EXTRA_JOKE);

            TextView textView = (TextView)view.findViewById(R.id.jokeTextView);
            textView.setText(strJoke);
        }
        return view;
    }
}
