<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Aulas;

class ControladorAulas extends Controller
{

	// Cargar vista de crear aula
    public function cargarFormularioAulas() {
    	return View('anyadirAula');
    }

    // Procesar formulario de crear aula
    public function procesarAnyadirAula(Request $request) {
    	
        // Validate
        $aulas = $request->validate([
            'aula' => 'required|numeric'
        ], [
            'aula.numeric' => 'La edad debe ser numérico'
        ]);


        $aula = new Aulas;

    	// modificar campos del modelo
    	$aula-> num_aula = $request-> input('aula');

    	// registro en la tabla
    	$aula-> save();

        return redirect('mostrarAulas')
        			->with('success','Aula creada correctamente');;
    }

    // Mostrar Aulas
    public function showAulas() {
    	// mostrar todos los usuarios
    	$lista = Aulas::all();

    	// pasar a la vista el array
    	return View('mostrarAulas', ['aulas' => $lista]);
    }

}
