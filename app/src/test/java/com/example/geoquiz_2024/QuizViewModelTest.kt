package com.example.geoquiz_2024

import androidx.lifecycle.SavedStateHandle
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class QuizViewModelTest{
    @Test
    fun fournitBonTxtQuestionActuelle(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_1, quizViewModel.txtQuestionActuelle)
    }
    @Test
    fun finTabQuestions(){
        val savedStateHandle = SavedStateHandle(mapOf(INDEX_ACTUEL_KEY to 5))
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.question_5, quizViewModel.txtQuestionActuelle)
        quizViewModel.questionSuivante()
        assertEquals(R.string.question_1, quizViewModel.txtQuestionActuelle)
    }

}