package br.com.fiap.calculodejuros.juros

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class JurosScreenViewModel: ViewModel() {

    private val _capital = MutableLiveData<String>("")
    val capital: LiveData<String> = _capital //ok

    private val _taxa = MutableLiveData<String>("")
    val taxa: LiveData<String> = _taxa

    private val _tempo = MutableLiveData<String>("")
    val tempo: LiveData<String> = _tempo

    private val _juros = MutableLiveData<Double>(0.0)
    val juros: LiveData<Double> = _juros

    private val _montante = MutableLiveData<Double>(0.0)
    val montante: LiveData<Double> = _montante

    fun onCapitalChanged(newCapital: String) {
        _capital.value = newCapital
    }

    fun onTempoChanged(newTempo: String){
        _tempo.value = newTempo
    }

    fun onTaxaChanged(newTaxa: String){
        _taxa.value = newTaxa
    }

    fun calcularJurosViewModel(){
        _juros.value = br.com.fiap.calculodejuros.calculos.calcularJuros(
            capital = _capital.value!!.toDouble(),
            taxa = _taxa.value!!.toDouble(),
            tempo = _tempo.value!!.toDouble(),
        )
    }
    fun calcularMontanteViewModel(){
        _montante.value = br.com.fiap.calculodejuros.calculos.calcularMontante(
            capital = _capital.value!!.toDouble(),
            juros = _juros.value!!
        )
    }
}