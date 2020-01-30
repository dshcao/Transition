package com.osshare.transition.transition

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues
import java.util.ArrayList

class MyTransition : Transition {
    constructor() : super() {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        return super.createAnimator(sceneRoot, startValues, endValues)
    }


}