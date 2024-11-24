package com.example.geoquiz_2024

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.geoquiz_2024.databinding.ActivityAideBinding

private const val EXTRA_REPONSE_VRAI = "com.exemple.geoquizz_2022.reponse_vrai"
const val EXTRA_REPONSE_AFFICHEE = "com.exemple.geoquizz_2022.reponse_affichee"

private const val TAG = "AideActivity"


class AideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAideBinding

    private val aideViewModel: AideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate appelée")

        binding = ActivityAideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "Lien fait avec QuizViewModels: $aideViewModel")

        val reponseCorrecte = intent.getBooleanExtra(EXTRA_REPONSE_VRAI, false)

        binding.btnAfficheAide.setOnClickListener {
            val txtAAfficher = when {
                reponseCorrecte -> R.string.toast_correct
                else -> R.string.toast_incorrect
            }
            binding.tvReponse.setText(txtAAfficher)
            setResultReponseAffichee(true)
        }
    }

    private fun setResultReponseAffichee(reponseAffiche: Boolean){
        val donnees = Intent().apply {
            putExtra(+, reponseAffiche)
        }
        setResult(Activity.RESULT_OK, donnees)
    }

    companion object{
        fun newIntent(packageContext: Context, reponseVrai: Boolean): Intent {
            return Intent(packageContext, AideActivity::class.java).apply {
                putExtra(EXTRA_REPONSE_VRAI, reponseVrai)
            }
        }
    }
}