package com.example.geoquiz_2024

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val INDEX_ACTUEL_KEY = "INDEX_ACTUEL_KEY"
const val EST_TRICHEUR_KEY = "EST_TRICHEUR_KEY"

class QuizzViewModel(private val savedSrateHandle: SavedStateHandle) : ViewModel() {

    private val mTabQuestions = listOf(
        Question(R.string.question_1, true),
        Question(R.string.question_2, false),
        Question(R.string.question_3, false),
        Question(R.string.question_4, true),
        Question(R.string.question_5, true),
    )

    var estTricheur: Boolean
        get() = savedSrateHandle.get(EST_TRICHEUR_KEY) ?: false
        set(valeur) = savedSrateHandle.set(EST_TRICHEUR_KEY, valeur)

    private var mIndexActuel: Int
        get() = savedSrateHandle.get<Int>(INDEX_ACTUEL_KEY) ?: 0
        set(value) = savedSrateHandle.set(INDEX_ACTUEL_KEY, value)

    val repQuestionActuelle: Boolean
        get() = mTabQuestions[mIndexActuel].reponse

    val txtQuestionActuelle: Int
        get() = mTabQuestions[mIndexActuel].textResId

    fun questionSuivante(){
        mIndexActuel = (mIndexActuel + 1)%mTabQuestions.size
    }
}