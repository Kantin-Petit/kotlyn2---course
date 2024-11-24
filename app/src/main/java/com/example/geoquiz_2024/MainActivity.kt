package com.example.geoquiz_2024

import android.app.Activity
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi

import com.example.geoquiz_2024.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizzViewModel: QuizzViewModel by viewModels()

    private val demarreTriche = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        resultat ->
        if (resultat.resultCode == Activity.RESULT_OK){
            quizzViewModel.estTricheur = resultat.data?.getBooleanExtra(EXTRA_REPONSE_AFFICHEE, false) ?: false
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate appelée")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Lien fait avec QuizViewModels: $quizzViewModel")

        binding.boutonVrai.setOnClickListener {
            verifieReponse(repUser = true)
        }

        binding.boutonFaux.setOnClickListener {
            verifieReponse(repUser = false)
        }

        binding.btnSuivant.setOnClickListener {
            quizzViewModel.questionSuivante()
            majQuestion()
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            {
                flouBtnTriche()
            }
        }
        majQuestion()

        binding.btnTriche.setOnClickListener {
            //val intention = Intent(this,AideActivity::class.java)
            val intention = AideActivity.newIntent(this@MainActivity, quizzViewModel.repQuestionActuelle)
            startActivity(intention)

            demarreTriche.launch(intention)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart appelée")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume appelée")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart appelée")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause appelée")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy appelée")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop appelée")
    }

    private fun majQuestion() {
        val questionTextResId = quizzViewModel.txtQuestionActuelle
        binding.tvQuestion.setText(questionTextResId)
    }

    private fun verifieReponse(repUser: Boolean) {
        val repCorrect = quizzViewModel.repQuestionActuelle
        /*val messageResId = if (repUser == repCorrect){
            R.string.toast_correct
        }else{
            R.string.toast_incorrect
        }*/
        val messageResId = when {
            quizzViewModel.estTricheur -> R.string.toast_triche
            repUser == repCorrect -> R.string.toast_correct
            else -> R.string.toast_incorrect
        }
        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun flouBtnTriche(){
        val effet = RenderEffect.createBlurEffect(
            10.0f,
            10.0f,
            Shader.TileMode.CLAMP
        )

        binding.btnTriche.setRenderEffect(effet)
    }
}