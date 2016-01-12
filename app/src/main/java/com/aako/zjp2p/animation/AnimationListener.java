package com.aako.zjp2p.animation;

import android.view.View;

/**
 * Created by aako on 16-1-12.
 */
public class AnimationListener {
    private AnimationListener() {
    }

    public interface Start {
        void onStart();
    }

    public interface Stop {
        void onStop();
    }

    public interface Update<V extends View> {
        void update(V view, float value);
    }
}
